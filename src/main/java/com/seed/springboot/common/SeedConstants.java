/**   
 * @Title: GlobalConstants.java 
 * @Package com.seed.springboot.common 
 * @version V1.0   
 */
package com.seed.springboot.common;

/** 
 * @ClassName: GlobalConstants 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月10日 下午3:49:57 
 *  
 */
public final class SeedConstants {

	/**
     * Spring profiles for development, test and production
     */
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    
    /**
     * Spring profile used to disable swagger
     */
    public static final String SPRING_PROFILE_SWAGGER = "swagger";
    
    public static final String SEED_PREFIX = "seed";
    
    /**
	 * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
	 */
    public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
	/**
	 * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
	 */
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
	/**
	 * 验证邮箱验证码时，http请求中默认的携带短信验证码信息的参数的名称
	 */
    public static final String DEFAULT_PARAMETER_NAME_CODE_EMAIL = "emailCode";
	
	
	/**
	 * 当请求需要身份认证时，默认跳转的url
	 */
    public static final String DEFAULT_UNAUTHENTICATION_URL = "/auth/require";
	/**
	 * 默认的处理验证码的url前缀
	 */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/auth/code";
	
	/**
	 * 默认的用户名密码登录请求处理url
	 */
    public static final String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/auth/form";
	/**
	 * 默认的手机验证码登录请求处理url
	 */
    public static final String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/auth/mobile";
	
	/**
	 * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
	 */
    public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

	/**
	 * 发送邮箱验证码 或 验证邮箱验证码时，传递邮箱的参数的名称
	 */
    public static final String DEFAULT_PARAMETER_NAME_EMAIL = "email";
}
