/**   
 * @Title: WebSecurityConfig.java 
 * @Package com.seed.springboot.common.security.config 
 * @version V1.0   
 */
package com.seed.springboot.common.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seed.springboot.common.SeedConstants;
import com.seed.springboot.common.enums.ErrorCodeEnum;
import com.seed.springboot.common.security.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.seed.springboot.common.utils.wrapper.WrapMapper;
import com.seed.springboot.common.validate.code.ValidateCodeSecurityConfig;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: WebSecurityConfig 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月10日 下午6:08:25 
 *  
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private AuthenticationSuccessHandler appLoginSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler appLoginFailureHandler;
	
	@Autowired
	private Filter authenticationTokenFilter;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
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
	
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint(){
		return new AuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
				log.debug("[WebSecurityConfig] authenticationEntryPoint ==》 认证失败？");
				response.setContentType("application/json");
		        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		        try {
		            ObjectMapper mapper = new ObjectMapper();
		            mapper.writeValue(response.getOutputStream(), WrapMapper.wrap(ErrorCodeEnum.BA100401, ex));
		        } catch (Exception e) {
		            throw new ServletException();
		        }
			}
		};
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        //忽略权限校验的访问路径
        web
    	.ignoring()
        .antMatchers(
            "/favicon.ico",
            "/swagger**/**",
            "/*/api-docs",
            "/webjars/**",
            "/druid/**",
            "/test/**"
        )
        .antMatchers(HttpMethod.POST, "/*/user")
        ;
    }
	
	/**
	 * 规则1. 如果异常是 AuthenticationException，使用 AuthenticationEntryPoint 处理  401
	 * 规则2. 如果异常是 AccessDeniedException 且用户是匿名用户，使用 AuthenticationEntryPoint 处理  401
	 * 规则3. 如果异常是 AccessDeniedException 且用户不是匿名用户，如果否则交给 AccessDeniedHandler 处理  403
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
    	.formLogin()
	    	.loginPage(SeedConstants.DEFAULT_UNAUTHENTICATION_URL)
			.loginProcessingUrl(SeedConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
    		.successHandler(appLoginSuccessHandler)
    		.failureHandler(appLoginFailureHandler);
		
		http
	        .csrf().disable()
	        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).accessDeniedHandler(accessDeniedHandler)
	        .and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	        .and().apply(validateCodeSecurityConfig)
//	        .and().apply(smsCodeAuthenticationSecurityConfig)
	        .and()
	        .authorizeRequests()
	        .antMatchers("/auth/**").permitAll()
	        .antMatchers(HttpMethod.POST, "/auth/form").authenticated()
	        .anyRequest().authenticated();
		
		 // Custom JWT based security filter
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
