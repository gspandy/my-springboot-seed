/**   
 * @Title: ValidateCodeFilter.java 
 * @Package com.seed.springboot.common.security.integration.code 
 * @version V1.0   
 */
package com.seed.springboot.common.security.validate.code;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.collect.Maps;
import com.seed.springboot.common.security.SecurityConstants;
import com.seed.springboot.common.utils.lang.StringUtils;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: ValidateCodeFilter 
 * @Description: TODO(校验验证码的过滤器) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午4:14:15 
 *  
 */
@Component("validateCodeFilter")
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
	
	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;
	
	//存放所有需要校验验证码的url
	private Map<String, ValidateCodeType> urlMap = Maps.newLinkedHashMap();

	//验证请求url与配置的url是否匹配的工具类
	private AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();

		urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
//		addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);

		urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
//		addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
		
//		addUrlToMap(securityProperties.getCode().getEmail().getUrl(), ValidateCodeType.EMAIL);
	}
	
	//将系统中配置的需要校验验证码的URL根据校验的类型放入map
//	private void addUrlToMap(String urlString, ValidateCodeType type) {
//		if (StringUtils.isNotBlank(urlString)) {
//			String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
//			for (String url : urls) {
//				urlMap.put(url, type);
//			}
//		}
//	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		ValidateCodeType type = getValidateCodeType(request);
		log.info("[ValidateCodeFilter] doFilterInternal ValidateCodeType={}", type);
		if (type != null) {
			log.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
			try {
				validateCodeProcessorHolder.findValidateCodeProcessor(type).validate(new ServletWebRequest(request, response));
				log.info("验证码校验通过");
			} catch (ValidateCodeException e) {
//				authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
				throw e;
			}
		}

		chain.doFilter(request, response);
	}
	
	// 获取校验码的类型，如果当前请求不需要校验，则返回null
	private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
		ValidateCodeType result = null;
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
			Set<String> urls = urlMap.keySet();
			for (String url : urls) {
				if (pathMatcher.match(url, request.getRequestURI())) {
					result = urlMap.get(url);
				}
			}
		}
		return result;
	}

}
