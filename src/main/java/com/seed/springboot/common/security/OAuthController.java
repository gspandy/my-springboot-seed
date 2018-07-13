/**   
 * @Title: LoginController.java 
 * @Package com.seed.springboot.common.security 
 * @version V1.0   
 */
package com.seed.springboot.common.security;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.HtmlUtils;

/**
 * @ClassName: LoginController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月11日 上午8:41:33
 * 
 */
@Controller
@SessionAttributes({ "authorizationRequest" })
public class OAuthController {
	
	
	@RequestMapping("/current")
	@ResponseBody
    public Principal getUser(Principal principal) {
        return principal;
    }
	
	@RequestMapping("/oauth/my_approval_page")
	public String approval(Map<String, Object> model, HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ? model.get("scopes") : request.getAttribute("scopes"));  
        List<String> scopeList = new ArrayList<String>();  
        for (String scope : scopes.keySet()) {  
            scopeList.add(scope);  
        }  
        model.put("scopeList", scopeList);  
		return "my_approval_page";
	}

	@RequestMapping("/oauth/my_error_page")
	public String error(Map<String, Object> model, HttpServletRequest request) {
		Object error = request.getAttribute("error");  
        String errorSummary;
        if (error instanceof OAuth2Exception) {  
            OAuth2Exception oauthError = (OAuth2Exception) error;  
            errorSummary = HtmlUtils.htmlEscape(oauthError.getSummary());  
        } else {  
            errorSummary = "Unknown error";  
        }  
        model.put("errorSummary", errorSummary);  
		return "my_error_page";
	}
}
