/**   
 * @Title: CustomOauth2ExceptionSerializer.java 
 * @Package com.seed.springboot.common.utils.exception.oauth 
 * @version V1.0   
 */
package com.seed.springboot.common.utils.exception.oauth;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.collect.Maps;
import com.seed.springboot.common.enums.ErrorCodeEnum;
import com.seed.springboot.common.utils.mapper.JsonMapper;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: CustomOauth2ExceptionSerializer 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月12日 下午12:05:15 
 *  
 */
@Slf4j
public class CustomOauth2ExceptionSerializer extends StdSerializer<CustomOAuth2Exception>{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomOauth2ExceptionSerializer() {
        super(CustomOAuth2Exception.class);
    }

    @Override
    public void serialize(CustomOAuth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        
        Map<String, Object> body = Maps.newLinkedHashMap();
		body.put("code", String.valueOf(ErrorCodeEnum.BA100400.getCode()));
		body.put("error", value.getMessage());
		body.put("error_description", value.getCause().getMessage());
        
		log.debug("status => {}", value.getHttpErrorCode());
		log.debug("body  => {}", JsonMapper.toJson(body));
        gen.writeObject(body);
    }

}
