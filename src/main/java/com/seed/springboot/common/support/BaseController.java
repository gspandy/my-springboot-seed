/**   
 * @Title: BaseController.java 
 * @Package com.fosunling.skye.server.common 
 * @version V1.0   
 */
package com.seed.springboot.common.support;

import com.seed.springboot.common.enums.ErrorCodeEnum;
import com.seed.springboot.common.utils.wrapper.WrapMapper;
import com.seed.springboot.common.utils.wrapper.Wrapper;

/**
 * @ClassName: BaseController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年5月29日 下午3:05:37
 * 
 */
public abstract class BaseController {

	protected <T> Wrapper<T> handleResult(ErrorCodeEnum errorCodeEnum, Object... args) {
		return WrapMapper.wrap(errorCodeEnum, args);
	}
}
