/**   
 * @Title: CaptchaConfig.java 
 * @Package com.seed.springboot.common.config 
 * @version V1.0   
 */
package com.seed.springboot.common.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/** 
 * @ClassName: CaptchaConfig 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 下午3:53:00 
 *  
 */
@Configuration
public class CaptchaConfig {
	
	@Bean(name = "captchaProducer")
	public DefaultKaptcha getKaptchaBean() {
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		Properties properties = new Properties();
		properties.setProperty("kaptcha.border", "no");
		properties.setProperty("kaptcha.border.color", "no");
		properties.setProperty("kaptcha.border.color", "220,227,232");
		properties.setProperty("kaptcha.textproducer.char.string", "2345689");
		properties.setProperty("kaptcha.textproducer.font.color", "black");
		properties.setProperty("kaptcha.textproducer.font.size", "16");
		properties.setProperty("kaptcha.image.width", "106");
		properties.setProperty("kaptcha.image.height", "30");
		properties.setProperty("kaptcha.session.key", "kaptchaCode");
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		properties.setProperty("kaptcha.background.clear.from", "white");
		properties.setProperty("kaptcha.background.clear.to", "white");
		properties.setProperty("kaptcha.textproducer.char.space", "5");
		properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
		properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
		properties.setProperty("kaptcha.textproducer.font.names", "Verdana");
		Config config = new Config(properties);
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}
}
