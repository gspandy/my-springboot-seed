/**   
 * @Title: SysMenuService.java 
 * @Package com.seed.springboot.system.service 
 * @version V1.0   
 */
package com.seed.springboot.system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seed.springboot.common.support.BaseService;
import com.seed.springboot.system.mapper.SysMenuMapper;
import com.seed.springboot.system.model.domain.SysMenu;

/** 
 * @ClassName: SysMenuService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月19日 下午2:31:16 
 *  
 */
@Service
@Transactional(readOnly = true)
public class SysMenuService extends BaseService<SysMenuMapper, SysMenu>{

	/**
	 * 返回用户角色权限
	 * @param userCode 用户编码
	 * @return
	 */
	public List<SysMenu> findListByUserCode(String userCode){
		return this.getMapper().findListByUserCode(userCode);
	}
	
	/**
	 * 返回用户角色权限
	 * @param roleCode 角色编码
	 * @return
	 */
	public List<SysMenu> findListByRoleCode(String roleCode){
		return this.getMapper().findListByRoleCode(roleCode);
	}
}
