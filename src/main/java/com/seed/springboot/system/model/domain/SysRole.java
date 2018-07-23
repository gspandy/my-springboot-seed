/**   
 * @Title: SysRole.java 
 * @Package com.seed.springboot.system.model.domain 
 * @version V1.0   
 */
package com.seed.springboot.system.model.domain;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.ibatis.type.Alias;

import com.seed.springboot.common.support.DataEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: SysRole
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月19日 下午1:02:42
 * 
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "sys_role")
@Alias("SysRole")
public class SysRole extends DataEntity<SysRole> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2496814008420037252L;

	@Column(name = "role_name")
	private String roleName;	// 角色名称
	
	@Column(name = "role_type")
	private String roleType;	// 角色分类（高管、中层、基层、其它）
	
	@Column(name = "role_sort")
	private Long roleSort;	// 角色排序（升序）
	
	@Column(name = "user_type")
	private String userType;	// 用户类型（employee员工 member会员）
	
	@Transient
	public boolean getEnabled(){
		return STATUS_NORMAL.equalsIgnoreCase(this.getStatus()) ;
	}
	
	@Transient
	private String userId;
	
}
