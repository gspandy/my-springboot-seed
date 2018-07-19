/**   
 * @Title: SysMenu.java 
 * @Package com.seed.springboot.system.model.domain 
 * @version V1.0   
 */
package com.seed.springboot.system.model.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import com.seed.springboot.common.support.DataEntity;

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
public class SysMenu extends DataEntity<SysMenu>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8792387733567541160L;
	
	@Id
	@Column(name = "menu_code")
	private String menuCode;	// 菜单编码
	
	@Column(name = "parent_code")
	private String parentCode;	// 父级编号
	
	@Column(name = "parent_codes")
	private String parentCodes;	// 所有父级编号
	
	@Column(name = "tree_sort")
	private Long treeSort;	// 本级排序号（升序）
	
	@Column(name = "tree_sorts")
	private String treeSorts;	// 所有级别排序号
	
	@Column(name = "tree_leaf")
	private String treeLeaf;	// 是否最末级
	
	@Column(name = "tree_level")
	private Long treeLevel;	// 层次级别
	
	@Column(name = "tree_names")
	private String treeNames;	// 全节点名
	
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
	
	@Column(name = "is_show")
	private String isShow;	// 是否显示（1显示 0隐藏）

}
