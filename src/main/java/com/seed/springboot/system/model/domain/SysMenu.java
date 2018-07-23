/**   
 * @Title: SysMenu.java 
 * @Package com.seed.springboot.system.model.domain 
 * @version V1.0   
 */
package com.seed.springboot.system.model.domain;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.ibatis.type.Alias;

import com.seed.springboot.common.support.TreeEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
 * @ClassName: SysMenu 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月19日 下午1:02:50 
 *  
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "sys_menu")
@Alias("SysMenu")
public class SysMenu extends TreeEntity<SysMenu>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8792387733567541160L;
	
	@Column(name = "menu_name")
	private String menuName;	// 菜单名称
	
	@Column(name = "menu_type")
	private String menuType;	// 菜单类型（1菜单 2权限 3开发）
	
	@Column(name = "menu_href")
	private String menuHref;	// 链接
	
	@Column(name = "menu_target")
	private String menuTarget;	// 目标
	
	@Column(name = "menu_icon")
	private String menuIcon;	// 图标
	
	@Column(name = "menu_color")
	private String menuColor;	// 颜色
	
	private String permission;	// 权限标识
	
	private Long weight;	// 菜单权重
	
	@Transient
	private String userId; //用户编码
	
	@Transient
	private String roleId; //角色编码
	
	@Transient
	private SysMenu parent; //父类对象
	
	public SysMenu(){
		super();
		this.setIsShow("1");
	}
	
	public SysMenu(String id){
		super(id);
	}
}
