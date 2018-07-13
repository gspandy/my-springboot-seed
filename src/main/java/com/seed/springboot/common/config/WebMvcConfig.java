/**   
 * @Title: WebMvcConfig.java 
 * @Package com.seed.springboot.common.config 
 * @version V1.0   
 */
package com.seed.springboot.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seed.springboot.common.utils.mapper.JsonMapper;

/** 
 * @ClassName: WebMvcConfig 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月11日 下午1:52:17 
 *  
 */
@Configuration
@EnableConfigurationProperties(SeedProperties.class)
public class WebMvcConfig extends WebMvcConfigurerAdapter {


	@Bean
	public ObjectMapper objectMapper() {
		return new JsonMapper();
	}

	@Bean
	public HttpMessageConverter<Object> httpMessageConverter() {
		return new MappingJackson2HttpMessageConverter(this.objectMapper());
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// 系统对外暴露的 URL 不会识别和匹配 .* 后缀
		// 系统不区分 URL 的最后一个字符是否是斜杠 /
		configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
	}
}
