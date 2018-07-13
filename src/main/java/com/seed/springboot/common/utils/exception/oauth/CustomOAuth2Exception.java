/**   
 * @Title: CustomOAuth2Exception.java 
 * @Package com.seed.springboot.common.utils.exception 
 * @version V1.0   
 */
package com.seed.springboot.common.utils.exception.oauth;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/** 
 * @ClassName: CustomOAuth2Exception 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月12日 上午11:18:16 
 *  
 */
@JsonSerialize(using = CustomOauth2ExceptionSerializer.class)
public class CustomOAuth2Exception extends OAuth2Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7469385952369663183L;

	/**
	 * @param msg
	 */
	public CustomOAuth2Exception(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param msg
	 * @param t
	 */
	public CustomOAuth2Exception(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}
	

}
