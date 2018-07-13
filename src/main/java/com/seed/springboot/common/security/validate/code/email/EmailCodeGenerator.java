/**   
 * @Title: EmailCodeGenerator.java 
 * @Package com.seed.springboot.common.security.validate.code.email 
 * @version V1.0   
 */
package com.seed.springboot.common.security.validate.code.email;

import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.seed.springboot.common.security.validate.code.ValidateCode;
import com.seed.springboot.common.security.validate.code.ValidateCodeGenerator;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: EmailCodeGenerator 
 * @Description: TODO(验证码生成器) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午6:56:56 
 *  
 */
@Component("emailValidateCodeGenerator")
@Slf4j
public class EmailCodeGenerator implements ValidateCodeGenerator {

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.security.validate.code.ValidateCodeGenerator#generate(org.springframework.web.context.request.ServletWebRequest)
	 */
	@Override
	public ValidateCode generate(ServletWebRequest request) {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		Map<String, String[]> parameterMap = request.getParameterMap();
		String[] emails = parameterMap.get("email");
		log.info(Arrays.toString(emails));
		String code = Arrays.toString(emails);
		return new ValidateCode(code, 1000);
	}

}
