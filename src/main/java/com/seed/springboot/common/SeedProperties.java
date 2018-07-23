/**   
 * @Title: SeedProperties.java 
 * @Package com.seed.springboot.common.config 
 * @version V1.0   
 */
package com.seed.springboot.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.seed.springboot.common.security.JwtProperties;
import com.seed.springboot.common.validate.code.ValidateCodeProperties;

import lombok.Data;

/** 
 * @ClassName: SeedProperties 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月11日 下午1:36:50 
 *  
 */
@ConfigurationProperties(prefix = SeedConstants.SEED_PREFIX, ignoreUnknownFields = false)
@Data
public class SeedProperties {
	
	private JwtProperties jwt = new JwtProperties();
	
	private ValidateCodeProperties validateCode = new ValidateCodeProperties();
	
	private User user = new User();
	
	private String seedName;
	
	@Data
	public class User{
		
		//指定超级管理员编号（研发团队使用的账号）
		private String superAdminCode;
		
		// 超级管理员获取菜单的最小权重（默认20；>=40二级管理员；>=60系统管理员；>=80超级管理员）
		private Integer superAdminGetMenuMinWeight;
		
		
	}
}
