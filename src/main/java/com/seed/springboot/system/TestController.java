/**   
 * @Title: TestController.java 
 * @Package com.seed.springboot.system 
 * @version V1.0   
 */
package com.seed.springboot.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seed.springboot.common.support.BaseController;
import com.seed.springboot.common.utils.wrapper.WrapMapper;
import com.seed.springboot.common.utils.wrapper.Wrapper;
import com.seed.springboot.system.mapper.SysUserMapper;

/** 
 * @ClassName: TestController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月18日 下午12:42:13 
 *  
 */
@RestController
public class TestController extends BaseController {

	@Autowired
	SysUserMapper sysUserMapper;
	
	@GetMapping("/test")
	public Wrapper<Object> index(){
		System.out.println(sysUserMapper.selectAll());
		return WrapMapper.ok();
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/info")
	public Wrapper<Object> info(){
		return WrapMapper.ok();
	}
	
	@PreAuthorize("hasAuthority('sys:user:view')")
	@GetMapping("/list")
	public Wrapper<Object> list(){
		return WrapMapper.ok();
	}
	
	@PreAuthorize("hasAuthority('sys:role:view')")
	@GetMapping("/role")
	public Wrapper<Object> role(){
		return WrapMapper.ok();
	}
}
