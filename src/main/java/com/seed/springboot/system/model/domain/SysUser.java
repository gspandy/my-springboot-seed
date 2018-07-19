/**   
 * @Title: SysUser.java 
 * @Package com.seed.springboot.system.model.domain 
 * @version V1.0   
 */
package com.seed.springboot.system.model.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seed.springboot.common.support.DataEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
 * @ClassName: SysUser 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月19日 上午9:42:17 
 *  
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "sys_user")
@Alias("SysUser")
public class SysUser extends DataEntity<SysUser> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1725824683795945703L;

	/**
	 * 用户编号
	 */
	@Id
	@Column(name = "user_code")
	private String userCode;
	
	@Column(name = "login_name")
	private String loginName;
	
	private String password;
	
	private String mobile;
	
	private String email;
	
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private String birthday;
	
	private String address;
	
	@Column(name = "user_type")
	private String userType;
	
	@Transient
	public boolean getEnabled(){
		return STATUS_NORMAL.equalsIgnoreCase(this.getStatus()) ;
	}
}
