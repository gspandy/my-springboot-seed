/**   
 * @Title: TreeMapper.java 
 * @Package com.seed.springboot.common.support 
 * @version V1.0   
 */
package com.seed.springboot.common.support;

import java.util.List;

/** 
 * @ClassName: TreeMapper 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月20日 下午12:34:52 
 *  
 */
public interface TreeMapper<T extends TreeEntity<T>> extends BaseMapper<T> {

	/**
	 * 找到所有子节点
	 * @param entity
	 * @return
	 */
	public List<T> findByParentIdsLike(T entity);

	/**
	 * 更新所有父节点字段
	 * @param entity
	 * @return
	 */
	public int updateParentIds(T entity);
	
	/**
	 * 更新所有父节点 为非末尾节点
	 * @param parentIds
	 * @return
	 */
	public int updateTreeLeaf(String parentIds);
}
