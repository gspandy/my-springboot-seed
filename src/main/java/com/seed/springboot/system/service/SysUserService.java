/**   
 * @Title: SysUserService.java 
 * @Package com.seed.springboot.system.service 
 * @version V1.0   
 */
package com.seed.springboot.system.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seed.springboot.common.support.BaseService;
import com.seed.springboot.common.utils.EmailUtils;
import com.seed.springboot.common.utils.PhoneUtils;
import com.seed.springboot.system.mapper.SysUserMapper;
import com.seed.springboot.system.model.domain.SysUser;
import com.seed.springboot.system.model.dto.UserLoginDto;

/** 
 * @ClassName: SysUserService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月19日 上午11:16:32 
 *  
 */

@Service
@Transactional(readOnly = true)
public class SysUserService extends BaseService<SysUserMapper, SysUser> {

	public SysUser getByLogin(String value) {
		UserLoginDto userLoginDto =  new UserLoginDto();
		if(PhoneUtils.isMobileNO(value)){
			userLoginDto.setMobile(value);
		}else if(EmailUtils.isEmailNO(value)){
			userLoginDto.setEmail(value);
		}else{
			userLoginDto.setLoginName(value);
		}
		return getByUserLoginDto(userLoginDto);
	}
	
	public SysUser getByUserLoginDto(UserLoginDto userLoginDto){
		SysUser record = new SysUser();
		BeanUtils.copyProperties(userLoginDto, record);
		record.setStatus(null);//查询所有数据
		return this.getMapper().selectOne(record);
	}
}
