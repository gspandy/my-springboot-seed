/**   
* @Title: SystemPathUtils.java 
* @Package com.fosunling.portal.utils 
* @version V1.0   
*/
package com.seed.springboot.common.utils;

import com.seed.springboot.common.utils.lang.StringUtils;

/**
 * @ClassName: SystemPathUtils
 * @Description: TODO(得到当前应用的系统路径)
 * @author RuYang ruyang@fosun.com
 * @date 2017年11月29日 上午10:46:18
 * 
 */
public class PathUtils {

	public static String getSysPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}
	
	/**
     * 判断请求的URL是否包含在排除路径之中
     * 
     * @param excludePaths
     *            排除路径
     * @param requestUrl
     *            请求的URL
     * @return
     */
    public static boolean isExclude(String excludePaths, String requestUrl) {
        if (StringUtils.isBlank(excludePaths) || StringUtils.isBlank(requestUrl)) {
            return false;
        }

        String[] excludeArray = excludePaths.split(",");
        for (String exclude : excludeArray) {
            if (requestUrl.contains(exclude)) {
                return true;
            }
        }

        return false;
    }
}
