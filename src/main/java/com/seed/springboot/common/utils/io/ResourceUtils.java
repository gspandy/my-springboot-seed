/**   
 * @Title: ResourceUtils.java 
 * @Package com.skyes.common.io 
 * @version V1.0   
 */
package com.seed.springboot.common.utils.io;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.seed.springboot.common.utils.lang.ExceptionUtils;

/**
 * @ClassName: ResourceUtils
 * @Description: TODO(资源供给类)
 * @author RuYang ruyang@fosun.com
 * @date 2018年5月16日 下午2:59:31
 * 
 */
public class ResourceUtils extends org.springframework.util.ResourceUtils {

	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	/**
	 * 获取资源加载器（可读取jar内的文件）
	 * 
	 * @author ThinkGem
	 */
	public static ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	/**
	 * 获取ClassLoader
	 */
	public static ClassLoader getClassLoader() {
		return resourceLoader.getClassLoader();
	}

	/**
	 * 获取资源加载器（可读取jar内的文件）
	 */
	public static Resource getResource(String location) {
		return resourceLoader.getResource(location);
	}

	/**
	 * 获取资源文件流（用后记得关闭）
	 * 
	 * @param location
	 * @author ThinkGem
	 * @throws IOException
	 */
	public static InputStream getResourceFileStream(String location) throws IOException {
		Resource resource = resourceLoader.getResource(location);
		return resource.getInputStream();
	}

	/**
	 * 获取资源文件内容
	 * 
	 * @param location
	 * @author ThinkGem
	 */
	public static String getResourceFileContent(String location) {
		InputStream is = null;
		try {
			is = ResourceUtils.getResourceFileStream(location);
			return IOUtils.toString(is, "UTF-8");
		} catch (IOException e) {
			throw ExceptionUtils.unchecked(e);
		} finally {
			try {
				is.close();
				is = null;
			} catch (IOException e) {
				throw ExceptionUtils.unchecked(e);
			}
		}
	}

	/**
	 * Spring 搜索资源文件
	 * 
	 * @param locationPattern
	 * @author ThinkGem
	 */
	public static Resource[] getResources(String locationPattern) {
		try {
			Resource[] resources = new PathMatchingResourcePatternResolver().getResources(locationPattern);
			// System.out.println("===========\n===========");
			// System.out.println(locationPattern + " : " + resources.length);
			// System.out.println("===========\n===========");
			return resources;
		} catch (IOException e) {
			throw ExceptionUtils.unchecked(e);
		}
	}
}
