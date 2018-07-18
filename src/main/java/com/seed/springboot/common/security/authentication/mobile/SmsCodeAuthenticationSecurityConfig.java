/**   
 * @Title: SmsCodeAuthenticationSecurityConfig.java 
 * @Package com.seed.springboot.common.security.authentication.mobile 
 * @version V1.0   
 */
package com.seed.springboot.common.security.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: SmsCodeAuthenticationSecurityConfig 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 下午2:39:10 
 *  
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private AuthenticationSuccessHandler appLoginSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler appLoginFailureHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
//	@Autowired
//	private PersistentTokenRepository persistentTokenRepository;
	
	@Override
	public void configure(HttpSecurity http) {

		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(appLoginSuccessHandler);
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(appLoginFailureHandler);
//		String key = UUID.randomUUID().toString();
//		smsCodeAuthenticationFilter.setRememberMeServices(new PersistentTokenBasedRememberMeServices(key, userDetailsService, persistentTokenRepository));

		SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
		smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

		http
			.authenticationProvider(smsCodeAuthenticationProvider)
			.addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//		

	}
	
}
