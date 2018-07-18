/**   
 * @Title: SecurityProperties.java 
 * @Package com.seed.springboot.common.security 
 * @version V1.0   
 */
package com.seed.springboot.common.security;

import lombok.Data;

/**
 * @ClassName: SecurityProperties
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 上午10:04:30
 * 
 */
@Data
public class JwtProperties {

	// 密钥
	private String secret;

	// 过期时间
	private int expireIn;

	private Prefix prefix = new Prefix();

	private Token token = new Token();

	@Data
	public class Prefix {

		private String auth;

		private String user;
	}

	@Data
	public class Token {
		
		private String header;

		private String type;
		
		private int length;

	}
}
