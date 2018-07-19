/**   
 * @Title: MyUserDetailsService.java 
 * @Package com.seed.springboot.common.security 
 * @version V1.0   
 */
package com.seed.springboot.common.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.seed.springboot.common.utils.lang.StringUtils;
import com.seed.springboot.system.model.domain.SysMenu;
import com.seed.springboot.system.model.domain.SysRole;
import com.seed.springboot.system.model.domain.SysUser;
import com.seed.springboot.system.service.SysMenuService;
import com.seed.springboot.system.service.SysRoleService;
import com.seed.springboot.system.service.SysUserService;

/**
 * @ClassName: MyUserDetailsService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月10日 下午6:15:17
 * 
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysMenuService sysMenuService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = sysUserService.getByLogin(username);
		if(sysUser == null){
			throw new UsernameNotFoundException("用户信息不存在");
		}
		
		MyUserDetails myUserDetails = new MyUserDetails();
		BeanUtils.copyProperties(sysUser, myUserDetails);
		myUserDetails.setAuthorities(mapToGrantedAuthorities(myUserDetails.getUserCode()));
		return myUserDetails;
	}
	
	/**
	 * 权限转换
	 * @param userCode 用户编码
	 * @return
	 */
    public List<SimpleGrantedAuthority> mapToGrantedAuthorities(String userCode) {
    	List<SysRole> sysRoles = sysRoleService.findListByUserCode(userCode);
    	List<SysMenu> sysMenus = sysMenuService.findListByUserCode(userCode);
    	
        List<SimpleGrantedAuthority> authorities =
            sysRoles.stream().filter(SysRole::getEnabled)
                .map(sysRole -> new SimpleGrantedAuthority(sysRole.getRoleName())).collect(Collectors.toList());

        // 添加基于Permission的权限信息
        sysMenus.stream().filter(menu -> StringUtils.isNotBlank(menu.getPermission())).forEach(menu -> {
            // 添加基于Permission的权限信息
            for (String permission : StringUtils.split(menu.getPermission(), ",")) {
                authorities.add(new SimpleGrantedAuthority(permission));
            }
        });
//    	List<SimpleGrantedAuthority> authorities = Lists.newLinkedList();
//		authorities.add(new SimpleGrantedAuthority("USER_ROLE"));
		return authorities;
    }

}
