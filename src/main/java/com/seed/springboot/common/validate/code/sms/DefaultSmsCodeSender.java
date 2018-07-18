/**   
 * @Title: DefaultSmsCodeSender.java 
 * @Package com.seed.springboot.common.validate.code.sms 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: DefaultSmsCodeSender 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 下午3:34:56 
 *  
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.validate.code.sms.SmsCodeSender#send(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void send(String mobile, String code, String ip) {
		log.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
		log.info("向手机" + mobile + "，发送短信验证码" + code + "，地址:" + ip);

	}

}
