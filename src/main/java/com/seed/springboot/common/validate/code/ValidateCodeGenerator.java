/**   
 * @Title: ValidateCodeGenerator.java 
 * @Package com.seed.springboot.common.security.validate.code 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/** 
 * @ClassName: ValidateCodeGenerator 
 * @Description: TODO(校验码生成器) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午6:44:05 
 *  
 */
public interface ValidateCodeGenerator {

	/**
	 * 生成校验码
	 *
	 * @param request the request
	 *
	 * @return validate code
	 */
	ValidateCode generate(ServletWebRequest request);
}
