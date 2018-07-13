/**   
 * @Title: ValidateCodeProcessor.java 
 * @Package com.seed.springboot.common.security.validate.code 
 * @version V1.0   
 */
package com.seed.springboot.common.security.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/** 
 * @ClassName: ValidateCodeProcessor 
 * @Description: TODO(校验码处理器，封装不同校验码的处理逻辑) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午6:25:51 
 *  
 */
public interface ValidateCodeProcessor {

	/**
	 * 创建校验码
	 *
	 * @param request the request
	 *
	 * @throws Exception the exception
	 */
	void create(ServletWebRequest request) throws Exception;

	/**
	 * 校验验证码(验证后删除)
	 *
	 * @param servletWebRequest the servlet web request
	 */
	void validate(ServletWebRequest servletWebRequest);

	/**
	 * 校验验证码(验证后不删除)
	 *
	 * @param servletWebRequest the servlet web request
	 */
	void check(ServletWebRequest servletWebRequest);
}
