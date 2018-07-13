/**   
 * @Title: Wrapper.java 
 * @Package com.skyes.common.wrapper 
 * @version V1.0   
 */
package com.seed.springboot.common.utils.wrapper;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * @ClassName: Wrapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年5月16日 下午3:49:43
 * 
 */
@JsonInclude(value = Include.NON_NULL)
@Data
public class Wrapper<T> implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 4893280118017319089L;

	/**
	 * 编码
	 */
	private int code;

	/**
	 * 错误信息
	 */
	private String error;

	/**
	 * 错误描述
	 */
	private String error_description;

	/**
	 * 结果数据
	 */
	private T data;

	Wrapper() {
	}

	Wrapper(int code, String error) {
		this(code, error, null);
	}

	Wrapper(int code, String error, String error_description) {
		this(code, error, error_description, null);
	}

	Wrapper(int code, String error, String error_description, T data) {
		super();
		this.code(code).error(error).error_description(error_description).data(data);
	}

	private Wrapper<T> code(int code) {
		this.setCode(code);
		return this;
	}

	private Wrapper<T> error(String error) {
		this.setError(error);
		return this;
	}

	private Wrapper<T> error_description(String error_description) {
		this.setError_description(error_description);
		return this;
	}

	public Wrapper<T> data(T data) {
		this.setData(data);
		return this;
	}

}
