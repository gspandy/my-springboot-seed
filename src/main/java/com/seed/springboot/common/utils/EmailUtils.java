/**   
 * @Title: EmailUtils.java 
 * @Package com.seed.springboot.common.utils 
 * @version V1.0   
 */
package com.seed.springboot.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.seed.springboot.common.utils.lang.StringUtils;

/** 
 * @ClassName: EmailUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月19日 上午11:27:16 
 *  
 */
public class EmailUtils extends StringUtils {

	/**
	 * 是否为电子邮件
	 * @param emails
	 * @return
	 */
	public static boolean isEmailNO(String emails){ 
		String reg = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(reg);  
		Matcher m = p.matcher(emails);  
		return m.matches();  
	}
}
