/**   
 * @Title: ValidateCodeController.java 
 * @Package com.seed.springboot.common.security.validate.code 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.seed.springboot.common.SeedConstants;
import com.seed.springboot.common.support.BaseController;

/** 
 * @ClassName: ValidateCodeController 
 * @Description: TODO(校验码的请求处理器) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午6:24:06 
 *  
 */
@RestController
public class ValidateCodeController extends BaseController{

	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;
	
	/**
	 * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
	 *
	 * @param request  the request
	 * @param response the response
	 * @param type     the type
	 *
	 * @throws Exception the exception
	 */
	@PostMapping(SeedConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
		validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
	}

	/**
	 * Check code object.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param type     the type
	 *
	 * @return the object
	 */
	@GetMapping(SeedConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void checkCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
		validateCodeProcessorHolder.findValidateCodeProcessor(type).check(new ServletWebRequest(request, response));
	}
}
