/**   
 * @Title: UserLoginDto.java 
 * @Package com.seed.springboot.system.model.dto 
 * @version V1.0   
 */
package com.seed.springboot.system.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/** 
 * @ClassName: UserLoginDto 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月19日 上午11:18:10 
 *  
 */
@JsonInclude(value = Include.NON_NULL)
@Data
public class UserLoginDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 764294787236731696L;
	
	private String loginName;

	private String mobile;

	private String email;
	
	private String password;

}
