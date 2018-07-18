/**   
 * @Title: EmailCodeSender.java 
 * @Package com.seed.springboot.common.security.validate.code.email 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code.email;

/** 
 * @ClassName: EmailCodeSender 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午6:59:58 
 *  
 */
public interface EmailCodeSender {

	/**
	 * Send.
	 *
	 * @param email the email
	 * @param code  the code
	 */
	void send(String email, String code);
}
