/**   
 * @Title: AbstractValidateCodeProcessor.java 
 * @Package com.seed.springboot.common.security.validate.code.abs 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.seed.springboot.common.utils.lang.StringUtils;

/** 
 * @ClassName: AbstractValidateCodeProcessor 
 * @Description: TODO(验证码信息封装类) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午6:51:48 
 *  
 */
public abstract class ValidateCodeProcessorAdapter<C extends ValidateCode> implements ValidateCodeProcessor {

	/**
	 * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
	 */
	private final Map<String, ValidateCodeGenerator> validateCodeGenerators;

	private final ValidateCodeRepository validateCodeRepository;

	/**
	 * Instantiates a new Abstract validate code processor.
	 *
	 * @param validateCodeGenerators the validate code generators
	 * @param validateCodeRepository the validate code repository
	 */
	@Autowired
	public ValidateCodeProcessorAdapter(Map<String, ValidateCodeGenerator> validateCodeGenerators, ValidateCodeRepository validateCodeRepository) {
		this.validateCodeGenerators = validateCodeGenerators;
		this.validateCodeRepository = validateCodeRepository;
	}


	/**
	 * Create.
	 *
	 * @param request the request
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void create(ServletWebRequest request) throws Exception {
		C validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
	}

	/**
	 * 生成校验码
	 */
	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) {
		String type = getValidateCodeType().toString().toLowerCase();
		String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
		if (validateCodeGenerator == null) {
			throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
		}
		return (C) validateCodeGenerator.generate(request);
	}

	/**
	 * 保存校验码
	 */
	private void save(ServletWebRequest request, C validateCode) {
		ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpiration());
		validateCodeRepository.save(request, code, getValidateCodeType());
	}

	/**
	 * 发送校验码，由子类实现
	 *
	 * @param request      the request
	 * @param validateCode the validate code
	 *
	 * @throws Exception the exception
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

	/**
	 * 根据请求的url获取校验码的类型
	 */
	private ValidateCodeType getValidateCodeType() {
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
		return ValidateCodeType.valueOf(type.toUpperCase());
	}

	/**
	 * Validate.
	 *
	 * @param request the request
	 */
	@Override
	public void validate(ServletWebRequest request) {

		ValidateCodeType codeType = getValidateCodeType();
		this.check(request);
		validateCodeRepository.remove(request, codeType);

	}

	/**
	 * Check.
	 *
	 * @param request the request
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void check(ServletWebRequest request) {
		ValidateCodeType codeType = getValidateCodeType();

		C codeInSession = (C) validateCodeRepository.get(request, codeType);

		String codeInRequest;
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), codeType.getParamNameOnValidate());
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("获取验证码的值失败");
		}

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException(codeType + " 验证码的值不能为空");
		}

		if (codeInSession == null || codeInSession.isExpired()) {
			validateCodeRepository.remove(request, codeType);
			throw new ValidateCodeException(codeType + " 验证码已过期");
		}

		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException(codeType + " 验证码不匹配");
		}
	}

}
