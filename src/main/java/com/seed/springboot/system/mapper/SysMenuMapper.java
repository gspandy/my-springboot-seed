/**   
 * @Title: SysMenuMapper.java 
 * @Package com.seed.springboot.system.mapper 
 * @version V1.0   
 */
package com.seed.springboot.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.seed.springboot.common.support.BaseMapper;
import com.seed.springboot.system.model.domain.SysMenu;

/**
 * @ClassName: SysMenuMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月19日 下午2:19:05
 * 
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 返回用户角色权限
	 * @param userCode 用户编码
	 * @return
	 */
	public List<SysMenu> findListByUserCode(String userCode);
	
	/**
	 * 返回用户角色权限
	 * @param roleCode 角色编码
	 * @return
	 */
	public List<SysMenu> findListByRoleCode(String roleCode);
}