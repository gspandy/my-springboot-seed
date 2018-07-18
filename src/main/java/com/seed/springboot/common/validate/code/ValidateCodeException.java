/**   
 * @Title: ValidateCodeException.java 
 * @Package com.seed.springboot.common.utils.exception 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code;

import com.seed.springboot.common.enums.ErrorCodeEnum;
import com.seed.springboot.common.utils.exception.BusinessException;

/** 
 * @ClassName: ValidateCodeException 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 下午4:23:14 
 *  
 */
public class ValidateCodeException extends BusinessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3542285737122210112L;

	/**
	 * 
	 */
	public ValidateCodeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCodeEnum
	 * @param args
	 */
	public ValidateCodeException(ErrorCodeEnum errorCodeEnum, Object... args) {
		super(errorCodeEnum, args);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 * @param msgFormat
	 * @param args
	 */
	public ValidateCodeException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 * @param message
	 */
	public ValidateCodeException(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValidateCodeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ValidateCodeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ValidateCodeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
	
}
