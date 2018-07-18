/**   
 * @Title: RedisValidateCodeRepository.java 
 * @Package com.seed.springboot.common.security.validate.code.repository 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/** 
 * @ClassName: RedisValidateCodeRepository 
 * @Description: TODO(基于redis的验证码存取器，避免由于没有session导致无法存取验证码的问题) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午6:50:42 
 *  
 */
@Component
public class ValidateCodeRedisRepository implements ValidateCodeRepository {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * Save.
	 *
	 * @param request the request
	 * @param code    the code
	 * @param type    the type
	 */
	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
		String key = buildKey(request, type);
		redisTemplate.opsForValue().set(key, code, 3, TimeUnit.MINUTES);
	}

	/**
	 * Get validate code.
	 *
	 * @param request the request
	 * @param type    the type
	 *
	 * @return the validate code
	 */
	@Override
	public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
		Object value = redisTemplate.opsForValue().get(buildKey(request, type));
		if (value == null) {
			return null;
		}
		return (ValidateCode) value;
	}

	/**
	 * Remove.
	 *
	 * @param request the request
	 * @param type    the type
	 */
	@Override
	public void remove(ServletWebRequest request, ValidateCodeType type) {
		redisTemplate.delete(buildKey(request, type));
	}

	private String buildKey(ServletWebRequest request, ValidateCodeType type) {
		String deviceId = "2018";
//		String deviceId = request.getHeader("deviceId");
//		if (StringUtils.isBlank(deviceId)) {
//			throw new BusinessException("请在请求头中携带deviceId参数");
//		}
		return "vlidate-code:" + type.toString().toLowerCase() + ":" + deviceId;
	}

}
