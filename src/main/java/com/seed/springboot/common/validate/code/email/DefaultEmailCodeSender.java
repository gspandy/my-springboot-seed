/**   
 * @Title: DefaultEmailCodeSender.java 
 * @Package com.seed.springboot.common.security.validate.code.email 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code.email;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: DefaultEmailCodeSender 
 * @Description: TODO(默认的短信验证码发送器) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午7:00:27 
 *  
 */
@Slf4j
public class DefaultEmailCodeSender implements EmailCodeSender {

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.security.validate.code.email.EmailCodeSender#send(java.lang.String, java.lang.String)
	 */
	@Override
	public void send(String email, String code) {
		// TODO Auto-generated method stub
		log.warn("请配置真实的邮件验证码发送器(SmsCodeSender)");
		log.info("向邮件" + email + "，发送短信验证码" + code);
	}

}
