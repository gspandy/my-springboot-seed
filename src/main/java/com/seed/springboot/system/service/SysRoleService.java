/**   
 * @Title: SysRoleService.java 
 * @Package com.seed.springboot.system.service 
 * @version V1.0   
 */
package com.seed.springboot.system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seed.springboot.common.support.BaseService;
import com.seed.springboot.system.mapper.SysRoleMapper;
import com.seed.springboot.system.model.domain.SysRole;

/** 
 * @ClassName: SysRoleService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月19日 下午1:41:32 
 *  
 */
@Service
@Transactional(readOnly = true)
public class SysRoleService extends BaseService<SysRoleMapper, SysRole>{
	
	/**
	 * 返回用户角色列表
	 * @param userCode
	 * @return 
	 */
	public List<SysRole> findListByUserCode(String userCode){
		return this.getMapper().findListByUserCode(userCode);
	}
}
