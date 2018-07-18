/**   
 * @Title: ValidateCodeBeanConfig.java 
 * @Package com.seed.springboot.common.security.validate.code 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.Producer;
import com.seed.springboot.common.SeedProperties;
import com.seed.springboot.common.validate.code.email.DefaultEmailCodeSender;
import com.seed.springboot.common.validate.code.email.EmailCodeSender;
import com.seed.springboot.common.validate.code.image.ImageCodeGenerator;
import com.seed.springboot.common.validate.code.sms.DefaultSmsCodeSender;
import com.seed.springboot.common.validate.code.sms.SmsCodeSender;

/** 
 * @ClassName: ValidateCodeBeanConfig 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午7:03:31 
 *  
 *  验证码相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 *  模块默认的配置。
 *  
 */
@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private SeedProperties seedProperties;
	
	@Autowired
	private Producer captchaProducer;
	
	/**
	 * 图片验证码图片生成器
	 *
	 * @return validate code generator
	 */
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSeedProperties(seedProperties);
		codeGenerator.setCaptchaProducer(captchaProducer);
		return codeGenerator;
	}
	
	/**
	 * 短信验证码发送器
	 *
	 * @return sms code sender
	 */
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}

	/**
	 * 邮箱验证码发送器
	 *
	 * @return sms code sender
	 */
	@Bean
	@ConditionalOnMissingBean(EmailCodeSender.class)
	public EmailCodeSender emailCodeSender() {
		return new DefaultEmailCodeSender();
	}
}
