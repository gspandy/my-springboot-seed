/**   
 * @Title: AuthenticationTokenFilter.java 
 * @Package com.seed.springboot.common.security.filter 
 * @version V1.0   
 */
package com.seed.springboot.common.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.seed.springboot.common.security.JwtTokenUtils;
import com.seed.springboot.common.security.MyUserDetails;
import com.seed.springboot.common.utils.lang.StringUtils;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: AuthenticationTokenFilter 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 下午1:11:14 
 *  
 */
@Component
@Slf4j
public class AuthenticationTokenFilter extends OncePerRequestFilter  {

	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		log.debug("[AuthenticationTokenFilter] doFilterInternal ==》 认证过滤？");
		// TODO Auto-generated method stub
        String authHeader = request.getHeader(jwtTokenUtils.getToken().getHeader());

        if (authHeader == null || !authHeader.startsWith(jwtTokenUtils.getToken().getType())) {
        	filterChain.doFilter(request, response);
            return;
        }
        
        final String authToken = StringUtils.substring(authHeader, jwtTokenUtils.getToken().getLength());
        String username = StringUtils.isNotBlank(authToken) ? jwtTokenUtils.getUsernameFromToken(authToken) : null;

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null && jwtTokenUtils.validateToken(authToken)) {
        	String userDetailsString = jwtTokenUtils.getUserDetailsString(authToken);
        	UserDetails userDetails = null;
        	if (userDetailsString != null) {
        		userDetails =  new Gson().fromJson(userDetailsString, MyUserDetails.class);
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        filterChain.doFilter(request, response);
	}


}
