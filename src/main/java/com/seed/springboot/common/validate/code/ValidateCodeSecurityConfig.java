/**   
 * @Title: ValidateCodeSecurityConfig.java 
 * @Package com.seed.springboot.common.validate.code 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: ValidateCodeSecurityConfig 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 下午2:48:47 
 *  
 */
@Component
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private Filter validateCodeFilter;

	/**
	 * Configure.
	 *
	 * @param http the http
	 */
	@Override
	public void configure(HttpSecurity http) {
		http.addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
	}
}
