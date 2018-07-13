/**   
 * @Title: GlobalExceptionHandler.java 
 * @Package com.fosunling.invest.web.common 
 * @version V1.0   
 */
package com.seed.springboot.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.seed.springboot.common.enums.ErrorCodeEnum;
import com.seed.springboot.common.utils.exception.BusinessException;
import com.seed.springboot.common.utils.wrapper.WrapMapper;
import com.seed.springboot.common.utils.wrapper.Wrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年5月16日 下午3:43:50
 * 
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Wrapper<Object> illegalArgumentException(IllegalArgumentException e) {
		log.error("参数非法异常 => {}", e.getMessage(), e);
		return WrapMapper.wrap(ErrorCodeEnum.BA100400, e);
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Wrapper<Object> businessException(BusinessException e) {
		log.error("业务异常 => {}", e.getMessage(), e);
		return WrapMapper.wrap(e);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Wrapper<Object> exception(Exception e) throws Exception {
		log.info("保存全局异常信息 ex => {}", e.getMessage(), e);
		return WrapMapper.wrap(ErrorCodeEnum.EA500, e);
	}
}
