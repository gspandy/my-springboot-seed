/**   
 * @Title: BaseMapper.java 
 * @Package com.fosunling.common.base.mapper 
 * @version V1.0   
 */
package com.seed.springboot.common.support;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @ClassName: BaseMapper
 * @Description: TODO(继承自tk.mapper)
 * @author RuYang ruyang@fosun.com
 * @date 2018年5月17日 下午5:22:25
 * 
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>{

}
