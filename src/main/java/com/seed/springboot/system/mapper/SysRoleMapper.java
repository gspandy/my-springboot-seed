/**   
 * @Title: SysRoleMapper.java 
 * @Package com.seed.springboot.system.mapper 
 * @version V1.0   
 */
package com.seed.springboot.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.seed.springboot.common.support.BaseMapper;
import com.seed.springboot.system.model.domain.SysRole;

/** 
 * @ClassName: SysRoleMapper 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月19日 下午1:22:18 
 *  
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

	/**
	 * 返回用户角色列表
	 * @param paramRole
	 * @return
	 */
	public List<SysRole> findListByUserId(SysRole paramRole);
}
