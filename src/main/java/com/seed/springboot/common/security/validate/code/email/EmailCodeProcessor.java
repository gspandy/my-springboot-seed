/**   
 * @Title: EmailCodeProcessor.java 
 * @Package com.seed.springboot.common.security.validate.code.email 
 * @version V1.0   
 */
package com.seed.springboot.common.security.validate.code.email;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.seed.springboot.common.security.SecurityConstants;
import com.seed.springboot.common.security.validate.code.ValidateCode;
import com.seed.springboot.common.security.validate.code.ValidateCodeGenerator;
import com.seed.springboot.common.security.validate.code.ValidateCodeProcessorAdapter;
import com.seed.springboot.common.security.validate.code.ValidateCodeRepository;

/** 
 * @ClassName: EmailCodeProcessor 
 * @Description: TODO(短信验证码处理器) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午6:59:18 
 *  
 */
@Component("emailValidateCodeProcessor")
public class EmailCodeProcessor extends ValidateCodeProcessorAdapter<ValidateCode> {

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private EmailCodeSender emailCodeSender;

	/**
	 * Instantiates a new Abstract validate code processor.
	 *
	 * @param validateCodeGenerators the validate code generators
	 * @param validateCodeRepository the validate code repository
	 */
	public EmailCodeProcessor(Map<String, ValidateCodeGenerator> validateCodeGenerators, ValidateCodeRepository validateCodeRepository) {
		super(validateCodeGenerators, validateCodeRepository);
	}

	/**
	 * Send.
	 *
	 * @param request      the request
	 * @param validateCode the validate code
	 *
	 * @throws Exception the exception
	 */
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_EMAIL;
		String email = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		emailCodeSender.send(email, validateCode.getCode());
	}
}
