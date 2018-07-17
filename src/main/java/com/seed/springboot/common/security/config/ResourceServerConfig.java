/**   
 * @Title: ResourceServerConfig.java 
 * @Package com.seed.springboot.common.security.config 
 * @version V1.0   
 */
package com.seed.springboot.common.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seed.springboot.common.enums.ErrorCodeEnum;
import com.seed.springboot.common.security.SecurityConstants;
import com.seed.springboot.common.security.validate.code.ValidateCodeSecurityConfig;
import com.seed.springboot.common.utils.wrapper.WrapMapper;

/**
 * @ClassName: ResourceServerConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月10日 下午5:59:58
 * 
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private OAuth2WebSecurityExpressionHandler appSecurityExpressionHandler;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private AccessDeniedHandler appAccessDeniedHandler;
	
	@Autowired
	private AuthenticationSuccessHandler appLoginSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler appLoginFailureHandler;
	
	
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint(){
		return new AuthenticationEntryPoint() {
			
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
				System.out.println("authenticationEntryPoint ==》 拦截了？");
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
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.expressionHandler(appSecurityExpressionHandler).authenticationEntryPoint(authenticationEntryPoint());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
    	.formLogin()
	    	.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
			.loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
    		.successHandler(appLoginSuccessHandler)
    		.failureHandler(appLoginFailureHandler);
		
	    http.headers().frameOptions().disable();
	    
		http
	    	.apply(validateCodeSecurityConfig)//验证码校验
	    .and()
	    	.headers().frameOptions().disable()
	    .and()
	    	.exceptionHandling().accessDeniedHandler(appAccessDeniedHandler)//异常处理
//	    .and()
//	    	.requestMatchers().antMatchers("/oauth/**")
	    .and()
	    	.authorizeRequests()
	    	.antMatchers("/auth/**").permitAll()
	    	.anyRequest().authenticated()
	    .and()
	    	.csrf().disable()
	    ;
	    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
	}
}
