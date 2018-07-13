/**   
 * @Title: BusinessException.java 
 * @Package com.seed.springboot.common.utils.exception 
 * @version V1.0   
 */
package com.seed.springboot.common.utils.exception;

import com.seed.springboot.common.enums.ErrorCodeEnum;

/** 
 * @ClassName: BusinessException 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月11日 下午2:03:31 
 *  
 */
public class BusinessException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int code;
	
	public BusinessException() {
		super();
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(int code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
	}

	public BusinessException(ErrorCodeEnum errorCodeEnum, Object... args) {
		super(String.format(errorCodeEnum.getMessage(), args));
		this.code = errorCodeEnum.getCode();
	}

	public int getCode() {
		return code;
	}
}
