/**   
 * @Title: WebSecurityConfig.java 
 * @Package com.seed.springboot.common.security.config 
 * @version V1.0   
 */
package com.seed.springboot.common.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
@EnableWebSecurity
//@Order(1)
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
	    	.headers().frameOptions().disable()
	    .and()
			.requestMatchers().antMatchers("/oauth/**")
        .and()
        	.authorizeRequests()
//        		.antMatchers("/oauth/authorize").permitAll()//有问题 还未解决
        		.anyRequest().authenticated()
        .and()
        	.csrf().disable();
	}
}
