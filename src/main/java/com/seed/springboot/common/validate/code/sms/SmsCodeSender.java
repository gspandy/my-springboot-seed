/**   
 * @Title: SmsCodeSender.java 
 * @Package com.seed.springboot.common.validate.code.sms 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code.sms;

/** 
 * @ClassName: SmsCodeSender 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 下午3:31:11 
 *  
 */
public interface SmsCodeSender {

	void send(String mobile, String code, String ip);
}
