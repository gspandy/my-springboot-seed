/**   
 * @Title: AppAccessDeniedHandler.java 
 * @Package com.seed.springboot.common.security.handler 
 * @version V1.0   
 */
package com.seed.springboot.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seed.springboot.common.enums.ErrorCodeEnum;
import com.seed.springboot.common.utils.wrapper.WrapMapper;

/** 
 * @ClassName: AppAccessDeniedHandler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月17日 下午1:41:13 
 *  
 */
@Configuration("appAccessDeniedHandler")
public class AppAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		System.out.println("appAccessDeniedHandler ==》 拦截了？");
		response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), WrapMapper.wrap(ErrorCodeEnum.BA100401, accessDeniedException));
        } catch (Exception e) {
            throw new ServletException();
        }
	}

}
