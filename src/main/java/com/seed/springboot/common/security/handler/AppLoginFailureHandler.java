/**   
 * @Title: AppLoginFailureHandler.java 
 * @Package com.seed.springboot.common.security.handler 
 * @version V1.0   
 */
package com.seed.springboot.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seed.springboot.common.enums.ErrorCodeEnum;
import com.seed.springboot.common.utils.wrapper.WrapMapper;

/** 
 * @ClassName: AppLoginFailureHandler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月17日 下午4:36:21 
 *  
 */
@Component
public class AppLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException ex) throws IOException, ServletException {
		System.out.println("appLoginFailureHandler ==》 拦截了？");
		response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), WrapMapper.wrap(ErrorCodeEnum.BA100401, ex));
        } catch (Exception e) {
            throw new ServletException();
        }
	}

	
}
