/**   
 * @Title: EmailCodeGenerator.java 
 * @Package com.seed.springboot.common.security.validate.code.email 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.seed.springboot.common.SeedProperties;
import com.seed.springboot.common.utils.RandomUtils;
import com.seed.springboot.common.validate.code.ValidateCode;
import com.seed.springboot.common.validate.code.ValidateCodeGenerator;

/** 
 * @ClassName: EmailCodeGenerator 
 * @Description: TODO(验证码生成器) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午6:56:56 
 *  
 */
@Component("emailValidateCodeGenerator")
//@Slf4j
public class EmailCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SeedProperties seedProperties;
	
	@Override
	public ValidateCode generate(ServletWebRequest request) {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		Map<String, String[]> parameterMap = request.getParameterMap();
//		String[] emails = parameterMap.get("email");
//		log.info(Arrays.toString(emails));
		String code = RandomUtils.getRandNum(seedProperties.getValidateCode().getEmail().getLength());
		
		return new ValidateCode(code, seedProperties.getValidateCode().getEmail().getExpireIn());
	}

}
