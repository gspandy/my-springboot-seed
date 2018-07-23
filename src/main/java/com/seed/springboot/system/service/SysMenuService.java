/**   
 * @Title: SysMenuService.java 
 * @Package com.seed.springboot.system.service 
 * @version V1.0   
 */
package com.seed.springboot.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.seed.springboot.common.SeedProperties;
import com.seed.springboot.common.support.TreeService;
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
public class SysMenuService extends TreeService<SysMenuMapper, SysMenu>{
	
	@Autowired
	private SeedProperties seedProperties;

	/**
	 * 返回用户角色权限
	 * @param userId 用户编码
	 * @return
	 */
	public List<SysMenu> findListByUserId(String userId){
		if(userId.equals(seedProperties.getUser().getSuperAdminCode())){
    		return this.selectAll();
    	}
		SysMenu paramMenu = new SysMenu();
		paramMenu.setUserId(userId);
		return this.getMapper().findListByUserId(paramMenu);
	}
	
	/**
	 * 返回用户角色权限
	 * @param roleId 角色编码
	 * @return
	 */
	public List<SysMenu> findListByRoleId(String roleId){
		SysMenu paramMenu = new SysMenu();
		paramMenu.setRoleId(roleId);
		return this.getMapper().findListByRoleId(paramMenu);
	}
	
	/**
	 * 返回树状菜单
	 * @param userId 用户编码
	 * @param useShow 是否开启菜单显示控制 true  开启， false  关闭
	 * @return
	 */
    public List<SysMenu> findMenuListTree(String userId, boolean useShow) {
        return makeTree(findListByUserId(userId), useShow);
    }

    /**
     * 返回排序后的菜单列表
     * @param userId
     * @return
     */
    public List<SysMenu> getMenuList(String userId) {
        List<SysMenu> resultList = Lists.newArrayList();
        //按父子顺序排列菜单列表
        sortList(resultList, findListByUserId(userId), "");
        return resultList;
    }
}
