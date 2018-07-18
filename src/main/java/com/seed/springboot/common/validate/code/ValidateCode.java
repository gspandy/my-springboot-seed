/**   
 * @Title: ValidateCode.java 
 * @Package com.seed.springboot.common.security.validate.code 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;

/** 
 * @ClassName: ValidateCode 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午6:44:23 
 *  
 */
@Data
public class ValidateCode implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String code;

	private String type;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime expiration;

	/**
	 * Instantiates a new Validate code.
	 */
	public ValidateCode() {
	}

	public ValidateCode(String code, int expiration) {
		this.code = code;
		this.expiration = LocalDateTime.now().plusSeconds(expiration);
	}

	public ValidateCode(String code, LocalDateTime expiration) {
		this.code = code;
		this.expiration = expiration;
	}
	
	@JsonIgnore
	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expiration);
	}
}
