/**   
 * @Title: ImageCodeProcessor.java 
 * @Package com.seed.springboot.common.validate.code.image 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code.image;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seed.springboot.common.utils.wrapper.WrapMapper;
import com.seed.springboot.common.validate.code.ValidateCodeGenerator;
import com.seed.springboot.common.validate.code.ValidateCodeProcessorAdapter;
import com.seed.springboot.common.validate.code.ValidateCodeRepository;

/** 
 * @ClassName: ImageCodeProcessor 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 下午3:46:16 
 *  
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends ValidateCodeProcessorAdapter<ImageCode> {

	@Resource
	private ObjectMapper objectMapper;
	
	public ImageCodeProcessor(Map<String, ValidateCodeGenerator> validateCodeGenerators, ValidateCodeRepository validateCodeRepository) {
		super(validateCodeGenerators, validateCodeRepository);
	}
	
	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(imageCode.getImage(), "JPEG", bos);

		HttpServletResponse response = request.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), WrapMapper.ok(bos.toByteArray()));
		
	}
}
