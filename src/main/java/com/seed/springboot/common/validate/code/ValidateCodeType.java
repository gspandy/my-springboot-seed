/**   
 * @Title: ValidateCodeType.java 
 * @Package com.seed.springboot.common.security.integration.code 
 * @version V1.0   
 */
package com.seed.springboot.common.validate.code;

import com.seed.springboot.common.SeedConstants;

/** 
 * @ClassName: ValidateCodeType 
 * @Description: TODO(校验码类型) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月13日 下午4:23:07 
 *  
 */
public enum ValidateCodeType {

	/**
	 * 短信验证码
	 */
	SMS {
		@Override
		public String getParamNameOnValidate() {
			return SeedConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
		}
	},
	/**
	 * 图片验证码
	 */
	IMAGE {
		@Override
		public String getParamNameOnValidate() {
			return SeedConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
		}
	},
	/**
	 * 邮箱验证码
	 */
	EMAIL {
		@Override
		public String getParamNameOnValidate() {
			return SeedConstants.DEFAULT_PARAMETER_NAME_CODE_EMAIL;
		}
	};

	/**
	 * 校验时从请求中获取的参数的名字
	 *
	 * @return param name on validate
	 */
	public abstract String getParamNameOnValidate();
}
