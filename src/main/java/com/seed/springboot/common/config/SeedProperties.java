/**   
 * @Title: SeedProperties.java 
 * @Package com.seed.springboot.common.config 
 * @version V1.0   
 */
package com.seed.springboot.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.seed.springboot.common.GlobalConstants;

import lombok.Data;

/** 
 * @ClassName: SeedProperties 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月11日 下午1:36:50 
 *  
 */
@ConfigurationProperties(prefix = GlobalConstants.SEED_PREFIX, ignoreUnknownFields = false)
@Data
public class SeedProperties {
	
	private String seedName;
}
