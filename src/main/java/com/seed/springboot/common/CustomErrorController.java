/**   
 * @Title: CustomErrorController.java 
 * @Package com.fosunling.skye.server.common 
 * @version V1.0   
 */
package com.seed.springboot.common;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.seed.springboot.common.enums.ErrorCodeEnum;
import com.seed.springboot.common.utils.mapper.JsonMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: CustomErrorController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年5月29日 下午3:58:26
 * 
 */
@RestController
@RequestMapping(value = "/error")
@Slf4j
public class CustomErrorController extends BasicErrorController {

	public CustomErrorController() {
		super(new DefaultErrorAttributes(), new ErrorProperties());
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Override
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> map = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
		HttpStatus status = this.getStatus(request);
		Map<String, Object> body = Maps.newLinkedHashMap();
		body.put("code", STATUS_CODE_MAP.get(status.value()).getCode());
		body.put("error", STATUS_CODE_MAP.get(status.value()).getMessage());
		body.put("error_description", map.get("error"));
		
		log.debug("status => {}", status.value());
		log.debug("body  => {}", JsonMapper.toJson(body));
		return ResponseEntity.status(status).body(body);
	}
	
	
	@RequestMapping(produces = "text/html")
	@Override
	public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ModelAndView result = super.errorHtml(request, response);
		log.debug("status => {}", JsonMapper.toJson(result));
		return result;
	}



	/**
	 * HTTP状态码与系统错误代码对应关系
	 */
	public static final Map<Integer, ErrorCodeEnum> STATUS_CODE_MAP;

	static {
		Map<Integer, ErrorCodeEnum> codeMappingMap = Maps.newLinkedHashMap();
		codeMappingMap.put(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodeEnum.EA500);
		codeMappingMap.put(HttpStatus.BAD_REQUEST.value(), ErrorCodeEnum.BA100400);
		codeMappingMap.put(HttpStatus.NOT_FOUND.value(), ErrorCodeEnum.BA100404);
		codeMappingMap.put(HttpStatus.UNAUTHORIZED.value(), ErrorCodeEnum.BA100401);
		codeMappingMap.put(HttpStatus.FORBIDDEN.value(), ErrorCodeEnum.BA100403);

		STATUS_CODE_MAP = Collections.unmodifiableMap(codeMappingMap);
	}

}
