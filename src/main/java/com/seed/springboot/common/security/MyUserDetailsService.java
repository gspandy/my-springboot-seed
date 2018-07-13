/**   
 * @Title: MyUserDetailsService.java 
 * @Package com.seed.springboot.common.security 
 * @version V1.0   
 */
package com.seed.springboot.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// return new User(username, password, authorities);
		// String password = new BCryptPasswordEncoder(8).encode("user");
		// List<SimpleGrantedAuthority> authorities =
		// createAuthorities("admin_role,user_role");
		// return new User("user", password, authorities);
		return new User(username, passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
	}
	
	/**
     * 权限转换
     *
     * @param sysRoles 角色列表
     * @param sysMenus 菜单列表
     * @return 权限列表
     */
//    private static List<SimpleGrantedAuthority> mapToGrantedAuthorities(List<SysRole> sysRoles, List<SysMenu> sysMenus) {
//
//        List<SimpleGrantedAuthority> authorities =
//            sysRoles.stream().filter(SysRole::getEnabled)
//                .map(sysRole -> new SimpleGrantedAuthority(sysRole.getName())).collect(Collectors.toList());
//
//        // 添加基于Permission的权限信息
//        sysMenus.stream().filter(menu -> StringHelper.isNotBlank(menu.getPermission())).forEach(menu -> {
//            // 添加基于Permission的权限信息
//            for (String permission : StringHelper.split(menu.getPermission(), ",")) {
//                authorities.add(new SimpleGrantedAuthority(permission));
//            }
//        });
//
//        return authorities;
//    }

}
