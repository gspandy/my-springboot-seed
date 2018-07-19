/**   
 * @Title: SysUserMapper.java 
 * @Package com.seed.springboot.system.mapper 
 * @version V1.0   
 */
package com.seed.springboot.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.seed.springboot.common.support.BaseMapper;
import com.seed.springboot.system.model.domain.SysUser;

/** 
 * @ClassName: SysUserMapper 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月19日 上午9:49:57 
 *  
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
