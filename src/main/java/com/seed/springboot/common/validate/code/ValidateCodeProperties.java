/**   
 * @Title: ValidateCodeProperties.java 
 * @Package com.seed.springboot.common.validate.code 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code;

import lombok.Data;

/** 
 * @ClassName: ValidateCodeProperties 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 下午2:14:34 
 *  
 */
@Data
public class ValidateCodeProperties {
	
	private Image image = new Image();
	private Sms sms = new Sms();
	private Email email = new Email();

	@Data
	public class Image{
		private String urls;
		private int expireIn;
	}
	
	@Data
	public class Sms{
		private String urls;
		private int expireIn;
		private int length;
		
		/**
		 * 每天每个手机号最大送送短信数量
		 */
		private int mobileMaxSendCount;
		/**
		 * 每天每个IP最大送送短信数量
		 */
		private int ipMaxSendCount;
		/**
		 * 每天最大送送短信数量
		 */
		private int totalMaxSendCount;
	}
	
	@Data
	public class Email{
		private String urls;
		private int expireIn;
		private int length;
	}
}
