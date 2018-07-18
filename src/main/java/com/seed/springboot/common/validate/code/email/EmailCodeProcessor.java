/**   
 * @Title: EmailCodeProcessor.java 
 * @Package com.seed.springboot.common.security.validate.code.email 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code.email;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seed.springboot.common.SeedConstants;
import com.seed.springboot.common.utils.wrapper.WrapMapper;
import com.seed.springboot.common.validate.code.ValidateCode;
import com.seed.springboot.common.validate.code.ValidateCodeGenerator;
import com.seed.springboot.common.validate.code.ValidateCodeProcessorAdapter;
import com.seed.springboot.common.validate.code.ValidateCodeRepository;

/** 
 * @ClassName: EmailCodeProcessor 
 * @Description: TODO(短信验证码处理器) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午6:59:18 
 *  
 */
@Component("emailValidateCodeProcessor")
public class EmailCodeProcessor extends ValidateCodeProcessorAdapter<ValidateCode> {

	@Resource
	private EmailCodeSender emailCodeSender;
	
	@Resource
	private ObjectMapper objectMapper;

	public EmailCodeProcessor(Map<String, ValidateCodeGenerator> validateCodeGenerators, ValidateCodeRepository validateCodeRepository) {
		super(validateCodeGenerators, validateCodeRepository);
	}

	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = SeedConstants.DEFAULT_PARAMETER_NAME_EMAIL;
		String email = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		emailCodeSender.send(email, validateCode.getCode());
		HttpServletResponse response = request.getResponse();
		response.setContentType("application/json");
		response.getOutputStream().print(objectMapper.writeValueAsString(WrapMapper.ok()));
	}
}
