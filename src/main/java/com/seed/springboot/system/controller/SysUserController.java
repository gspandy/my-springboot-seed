/**   
 * @Title: SysUserController.java 
 * @Package com.seed.springboot.system.controller 
 * @version V1.0   
 */
package com.seed.springboot.system.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seed.springboot.common.support.BaseController;
import com.seed.springboot.common.utils.wrapper.WrapMapper;
import com.seed.springboot.common.utils.wrapper.Wrapper;

/** 
 * @ClassName: SysUserController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月19日 下午5:28:11 
 *  
 */
@Validated
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController{

	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public Wrapper<Object> info(){
		return WrapMapper.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}
}
