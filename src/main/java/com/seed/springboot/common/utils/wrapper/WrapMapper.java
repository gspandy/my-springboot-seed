/**   
 * @Title: WrapMapper.java 
 * @Package com.skyes.common.wrapper 
 * @version V1.0   
 */
package com.seed.springboot.common.utils.wrapper;

import com.seed.springboot.common.enums.ErrorCodeEnum;
import com.seed.springboot.common.utils.exception.BusinessException;
import com.seed.springboot.common.utils.lang.StringUtils;

/**
 * @ClassName: WrapMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年5月16日 下午3:53:55
 * 
 */
public class WrapMapper {

	private WrapMapper() {
	}

	public static <E> Wrapper<E> wrap(int code, String error, String error_description, E data) {
		return new Wrapper<>(code, error, error_description, data);
	}

	public static <E> Wrapper<E> wrap(int code, String error) {
		return wrap(code, error, null, null);
	}
	
	public static <E> Wrapper<E> wrap(ErrorCodeEnum errorCodeEnum, Object... args) {
		return wrap(errorCodeEnum.getCode(), String.format(errorCodeEnum.getMessage(), args));
	}
	
	public static <E> Wrapper<E> wrap(ErrorCodeEnum errorCodeEnum, String error) {
		return wrap(errorCodeEnum.getCode(), StringUtils.isBlank(error) ?errorCodeEnum.getMessage() : error);
	}
	
	public static <E> Wrapper<E> wrap(ErrorCodeEnum errorCodeEnum, Exception e) {
		return wrap(errorCodeEnum.getCode(), errorCodeEnum.getMessage(), e.getMessage(), null);
	}
	
	public static <E> Wrapper<E> wrap(BusinessException bex) {
		return wrap(bex.getCode(), bex.getMessage(), bex.getCause().getMessage(), null);
	}

	public static <E> E unWrap(Wrapper<E> wrapper) {
		return wrapper.getData();
	}
	
	public static <E> Wrapper<E> ok() {
		return new Wrapper<>();
	}

	public static <E> Wrapper<E> ok(E data) {
		Wrapper<E> wrapper = new Wrapper<>();
		wrapper.setData(data);
		return wrapper;
	}
}
