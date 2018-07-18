/**   
 * @Title: SmsCodeAuthenticationFilter.java 
 * @Package com.seed.springboot.common.security.authentication.mobile 
 * @version V1.0   
 */
package com.seed.springboot.common.security.authentication.mobile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import com.seed.springboot.common.SeedConstants;

/** 
 * @ClassName: SmsCodeAuthenticationFilter 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 下午2:29:27 
 *  
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	private static final String POST = "POST";
	
	private String mobileParameter = SeedConstants.DEFAULT_PARAMETER_NAME_MOBILE;
	private boolean postOnly = true;
	
	public SmsCodeAuthenticationFilter() {
		super(new AntPathRequestMatcher(SeedConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		if (postOnly && !POST.equals(request.getMethod())) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String mobile = obtainMobile(request);

		if (mobile == null) {
			mobile = "";
		}

		mobile = mobile.trim();

		SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobileParameter);
	}
	
	protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}
	
	public void setMobileParameter(String usernameParameter) {
		Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
		this.mobileParameter = usernameParameter;
	}
	
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}
	
	public final String getMobileParameter() {
		return mobileParameter;
	}

}
