/**   
 * @Title: WebSecurityConfig.java 
 * @Package com.seed.springboot.common.security.config 
 * @version V1.0   
 */
package com.seed.springboot.common.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** 
 * @ClassName: WebSecurityConfig 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月10日 下午6:08:25 
 *  
 */
@Configuration
@Order(1)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)  
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(8);
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
	    	.headers().frameOptions().disable()
	    .and()
			.requestMatchers().antMatchers("/auth/**")
        .and()
        	.authorizeRequests().antMatchers("/oauth/**").authenticated()
//        .and()
//        	.formLogin()
//        		.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
//        		.loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
//        		.successHandler((request, response, authException) -> response.sendError(HttpServletResponse.SC_NOT_MODIFIED))
//        		.failureHandler((request, response, authException) -> response.sendError(HttpServletResponse.SC_NOT_MODIFIED))
	    ;
	}
}
