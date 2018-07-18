/**   
 * @Title: SmsCodeGenerator.java 
 * @Package com.seed.springboot.common.validate.code.sms 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.seed.springboot.common.SeedProperties;
import com.seed.springboot.common.utils.RandomUtils;
import com.seed.springboot.common.validate.code.ValidateCode;
import com.seed.springboot.common.validate.code.ValidateCodeGenerator;

/** 
 * @ClassName: SmsCodeGenerator 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 下午3:31:43 
 *  
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SeedProperties seedProperties;
	
	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomUtils.getRandNum(seedProperties.getValidateCode().getSms().getLength());
		return new ValidateCode(code, seedProperties.getValidateCode().getSms().getExpireIn());
	}

}
