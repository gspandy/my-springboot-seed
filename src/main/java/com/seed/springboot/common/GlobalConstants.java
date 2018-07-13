/**   
 * @Title: GlobalConstants.java 
 * @Package com.seed.springboot.common 
 * @version V1.0   
 */
package com.seed.springboot.common;

/** 
 * @ClassName: GlobalConstants 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月10日 下午3:49:57 
 *  
 */
public final class GlobalConstants {

	/**
     * Spring profiles for development, test and production
     */
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    
    /**
     * Spring profile used to disable swagger
     */
    public static final String SPRING_PROFILE_SWAGGER = "swagger";
    
    public static final String SEED_PREFIX = "seed";
}
