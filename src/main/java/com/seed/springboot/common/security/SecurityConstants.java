/**   
 * @Title: SecurityConstants.java 
 * @Package com.seed.springboot.common.security 
 * @version V1.0   
 */
package com.seed.springboot.common.security;

/** 
 * @ClassName: SecurityConstants 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午4:23:53 
 *  
 */
public interface SecurityConstants {

	/**
	 * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
	/**
	 * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
	/**
	 * 验证邮箱验证码时，http请求中默认的携带短信验证码信息的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_CODE_EMAIL = "emailCode";
	
	
	/**
	 * 当请求需要身份认证时，默认跳转的url
	 */
	String DEFAULT_UNAUTHENTICATION_URL = "/auth/require";
	/**
	 * 默认的处理验证码的url前缀
	 */
	String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/auth/code";
	
	/**
	 * 默认的用户名密码登录请求处理url
	 */
	String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/auth/form";
	/**
	 * 默认的手机验证码登录请求处理url
	 */
	String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/auth/mobile";
	
	/**
	 * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

	/**
	 * 发送邮箱验证码 或 验证邮箱验证码时，传递邮箱的参数的名称
	 */
	String DEFAULT_PARAMETER_NAME_EMAIL = "email";
}
