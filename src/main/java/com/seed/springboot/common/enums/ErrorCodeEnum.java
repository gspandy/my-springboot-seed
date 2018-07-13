/**   
 * @Title: ErrorCodeEnum.java 
 * @Package com.seed.springboot.common.enums 
 * @version V1.0   
 */
package com.seed.springboot.common.enums;

import lombok.Getter;

/** 
 * @ClassName: ErrorCodeEnum 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月11日 下午2:02:23 
 *  
 */
public enum ErrorCodeEnum {
	
	/**
	 * 500, "未知异常"
	 */
	EA500(500, "未知异常"),

	/**
	 * 100400, "参数异常"
	 */
	BA100400(100400, "参数异常"),
	/**
	 * 100401, "无访问权限"
	 */
	BA100401(100401, "无访问权限"),
	/**
	 * 100403, "无权访问"
	 */
	BA100403(100403, "无权访问"),
	/**
	 * 100404, "找不到指定资源"
	 */
	BA100404(100404, "找不到指定资源"),
	
	;
	
	@Getter
	private int code;
	
	@Getter
	private String message;

	ErrorCodeEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static ErrorCodeEnum getEnum(int code) {
		for (ErrorCodeEnum ele : ErrorCodeEnum.values()) {
			if (ele.getCode() == code) {
				return ele;
			}
		}
		return null;
	}
}
