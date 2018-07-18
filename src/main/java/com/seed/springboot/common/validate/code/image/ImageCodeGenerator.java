/**   
 * @Title: ImageCodeGenerator.java 
 * @Package com.seed.springboot.common.validate.code.image 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code.image;

import java.awt.image.BufferedImage;

import org.springframework.web.context.request.ServletWebRequest;

import com.google.code.kaptcha.Producer;
import com.seed.springboot.common.SeedProperties;
import com.seed.springboot.common.validate.code.ValidateCodeGenerator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: ImageCodeGenerator 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 下午3:47:55 
 *  
 */
@Data
@Slf4j
public class ImageCodeGenerator implements ValidateCodeGenerator {

	private SeedProperties seedProperties;
	
	private Producer captchaProducer;
	
	@Override
	public ImageCode generate(ServletWebRequest request) {
		String kaptchaCode = captchaProducer.createText();
		log.debug("[ImageCodeGenerator] generate kaptchaCode => {}", kaptchaCode);
		BufferedImage image = captchaProducer.createImage(kaptchaCode);
		return new ImageCode(image, kaptchaCode, seedProperties.getValidateCode().getImage().getExpireIn());
	}
	
	

}
