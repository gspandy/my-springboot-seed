/**   
 * @Title: PhoneUtils.java 
 * @Package com.seed.springboot.common.utils 
 * @version V1.0   
 */
package com.seed.springboot.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/** 
 * @ClassName: PhoneUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月19日 上午11:26:43 
 *  
 */
public class PhoneUtils extends StringUtils{

	/**
	 * 是否移动电话号码
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles){ 
		String reg = "^((13[0-9])|(14[5-7])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		Pattern p = Pattern.compile(reg);  
		Matcher m = p.matcher(mobiles);  
		return m.matches();  
	}
}
