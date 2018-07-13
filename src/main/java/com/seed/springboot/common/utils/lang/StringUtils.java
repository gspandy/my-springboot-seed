/**   
 * @Title: StringUtils.java 
 * @Package com.skyes.common.lang 
 * @version V1.0   
 */
package com.seed.springboot.common.utils.lang;

import java.util.Collection;
import java.util.Map;

/**
 * @ClassName: StringUtils
 * @Description: TODO(字符串工具类, 继承org.apache.commons.lang3.StringUtils类)
 * @author RuYang ruyang@fosun.com
 * @date 2018年5月15日 下午4:19:14
 * 
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * 获取树节点名字
	 * 
	 * @param isShowCode
	 *            是否显示编码<br>
	 *            true or 1：显示在左侧：(code)name<br>
	 *            2：显示在右侧：name(code)<br>
	 *            false or null：不显示编码：name
	 * @param code
	 *            编码
	 * @param name
	 *            名称
	 * @return
	 */
	public static String getTreeNodeName(String isShowCode, String code, String name) {
		if ("true".equals(isShowCode) || "1".equals(isShowCode)) {
			return "(" + code + ") " + StringUtils.replace(name, " ", "");
		} else if ("2".equals(isShowCode)) {
			return StringUtils.replace(name, " ", "") + " (" + code + ")";
		} else {
			return StringUtils.replace(name, " ", "");
		}
	}

	/**
	 * 判断对象是否Empty(null或元素为0) 实用于对如下对象做判断:String Collection及其子类 Map及其子类
	 *
	 * @param o
	 *            待检查对象
	 *
	 * @return boolean 返回的布尔值
	 */
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if (o == "") {
			return true;
		}
		if (o instanceof String) {
			return ((String) o).length() == 0;
		} else if (o instanceof Collection) {
			return ((Collection<?>) o).isEmpty();
		} else if (o instanceof Map) {
			return ((Map<?, ?>) o).size() == 0;
		}
		return false;
	}

	/**
	 * 判断对象是否为NotEmpty(!null或元素大于0) 实用于对如下对象做判断:String Collection及其子类 Map及其子类
	 *
	 * @param o
	 *            待检查对象
	 *
	 * @return boolean 返回的布尔值
	 */
	public static boolean isNotEmpty(Object o) {
		if (o == null) {
			return false;
		}
		if (o == "") {
			return false;
		}
		if (o instanceof String) {
			return ((String) o).length() != 0;
		} else if (o instanceof Collection) {
			return !((Collection<?>) o).isEmpty();
		} else if (o instanceof Map) {
			return ((Map<?, ?>) o).size() != 0;
		}
		return true;
	}
	
	/**
	 * 是否包含字符串
	 * 
	 * @param str
	 *            验证字符串
	 * @param strs
	 *            字符串组
	 * @return 包含返回true
	 */
	public static boolean inString(String str, String... strs) {
		if (str != null && strs != null) {
			for (String s : strs) {
				if (str.equals(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 是否包含字符串
	 * 
	 * @param str
	 *            验证字符串
	 * @param strs
	 *            字符串组
	 * @return 包含返回true
	 */
	public static boolean inStringIgnoreCase(String str, String... strs) {
		if (str != null && strs != null) {
			for (String s : strs) {
				if (str.equalsIgnoreCase(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}
}
