/**   
 * @Title: ValidateCodeProcessorHolder.java 
 * @Package com.seed.springboot.common.security.validate.code 
 * @version V1.0   
 */
package com.seed.springboot.common.security.validate.code;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: ValidateCodeProcessorHolder 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午6:24:50 
 *  
 */
@Component("validateCodeProcessorHolder")
public class ValidateCodeProcessorHolder {

	private final Map<String, ValidateCodeProcessor> validateCodeProcessors;

	/**
	 * Instantiates a new Validate code processor holder.
	 *
	 * @param validateCodeProcessors the validate code processors
	 */
	@Autowired
	public ValidateCodeProcessorHolder(Map<String, ValidateCodeProcessor> validateCodeProcessors) {
		this.validateCodeProcessors = validateCodeProcessors;
	}

	/**
	 * Find validate code processor validate code processor.
	 *
	 * @param type the type
	 *
	 * @return validate code processor
	 */
	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
		return findValidateCodeProcessor(type.toString().toLowerCase());
	}

	/**
	 * Find validate code processor validate code processor.
	 *
	 * @param type the type
	 *
	 * @return validate code processor
	 * @throws ValidateCodeException 
	 */
	public ValidateCodeProcessor findValidateCodeProcessor(String type) {
		String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
		ValidateCodeProcessor processor = validateCodeProcessors.get(name);
		if (processor == null) {
			throw new ValidateCodeException("验证码处理器" + name + "不存在");
		}
		return processor;
	}
	
}
