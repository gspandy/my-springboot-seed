/**   
 * @Title: AuthenticationSuccessHandler.java 
 * @Package com.seed.springboot.common.security.handler 
 * @version V1.0   
 */
package com.seed.springboot.common.security.handler;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.seed.springboot.common.security.JwtTokenUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: AuthenticationSuccessHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月10日 下午6:05:05
 * 
 */
@Component
@Slf4j
public class AppLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		log.debug("[AppLoginSuccessHandler] onAuthenticationSuccess ==》 认证通过？");

		SecurityContextHolder.getContext().setAuthentication(authentication);

		final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		final String token = jwtTokenUtils.generateToken(userDetails); // 生成Token

		Map<String, Object> tokenMap = Maps.newLinkedHashMap();
		tokenMap.put("access_token", token);
		tokenMap.put("expires_in", jwtTokenUtils.getJwt().getExpireIn());
		tokenMap.put("token_type", jwtTokenUtils.getJwt().getToken().getType() + " ");

		log.debug("用户[{}]记录登录日志", userDetails.getUsername());

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write((objectMapper.writeValueAsString(tokenMap)));
	}
}
