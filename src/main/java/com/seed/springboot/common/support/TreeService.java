/**   
 * @Title: TreeService.java 
 * @Package com.seed.springboot.common.support 
 * @version V1.0   
 */
package com.seed.springboot.common.support;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.seed.springboot.common.utils.lang.StringUtils;
import com.seed.springboot.common.utils.reflect.ReflectUtils;

/**
 * @ClassName: TreeService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月20日 上午10:44:55
 * 
 */
@Transactional(readOnly = true)
public abstract class TreeService<M extends TreeMapper<T>, T extends TreeEntity<T>> extends BaseService<M, T> {
	
	@Transactional(readOnly = false)
	@Override
	public int save(T record) {
		String oldParentIds = record.getParentIds(); 
		@SuppressWarnings("unchecked")
		Class<T> entityClass = ReflectUtils.getClassGenricType(getClass(), 1);
		// 如果没有设置父节点，则代表为跟节点，有则获取父节点实体
		if (StringUtils.isBlank(record.getParentId()) || "0".equals(record.getParentId())) {
			record.setParentId("0");
			record.setParentIds("0,");
			record.setTreeLeaf("1");
			record.setTreeLevel(Long.valueOf(0));
			
		} else {
			T t = super.selectByKey(record.getParentId());
			
			// 设置新的父节点串
			record.setParentIds(t.getParentIds() + t.getId() + ",");
			
			record.setTreeLeaf("1");
			
			record.setTreeLevel(Long.valueOf(record.getParentIds().split(",").length-1));
			
			//修改所有父级节点
			this.getMapper().updateTreeLeaf(t.getParentIds());
			
		}

		
		
		//
		// // 通过 EntityHelper.getColumns(entityClass) 方法来获取实体类的全部信息。
		// Set<EntityColumn> c = EntityHelper.getColumns(entityClass);
		// for (EntityColumn entityColumn : c) {
		// // 判断是否有某个注解
		// System.out.println(entityColumn.getEntityField().isAnnotationPresent(Id.class));
		// // 通过下面的代码可以获取注解信息
		// System.out.println(entityColumn.getEntityField().getAnnotation(Id.class));
		// }
		super.save(record);
//
//		// 更新子节点 parentCodes
		T o = null;
		try {
			o = entityClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("实例对象异常");
		}
		o.setParentIds("%," + record.getId() + ",%");
		List<T> list = this.getMapper().findByParentIdsLike(o);
		for (T e : list) {
			if (e.getParentIds() != null && oldParentIds != null) {
				e.setParentIds(e.getParentIds().replace(oldParentIds, record.getParentIds()));
				e.setTreeLevel(Long.valueOf(e.getParentIds().split(",").length-1));
				preUpdateChild(record, e);
				this.getMapper().updateParentIds(e);
			}
		}
		return 0;
	}

	/**
	 * 预留接口，用户更新子节前调用
	 * 
	 * @param childEntity
	 */
	protected void preUpdateChild(T entity, T childEntity) {

	}
	
	
	
	/**
     * 父子节点排序
     *
     * @param list       目标列表
     * @param sourceList 原始列表
     * @param parentId   父级编号
     */
	protected void sortList(List<T> list, List<T> sourceList, String parentId) {
        sourceList.stream()
            .filter(menu -> menu.getParentId() != null && menu.getParentId().equals(parentId))
            .forEach(menu -> {
                list.add(menu);
                // 判断是否还有子节点, 有则继续获取子节点
                sourceList.stream()
                    .filter(child -> child.getParentId() != null && child.getParentId().equals(menu.getId()))
                    .peek(child -> sortList(list, sourceList, menu.getId()))
                    .findFirst();
            });
    }
	
	/**
     * 构建树形结构
     *
     * @param originals 原始数据
     * @param useShow   是否使用开关控制菜单显示隐藏   false 关闭   true 打开
     * @return 菜单列表
     */
	protected List<T> makeTree(List<T> originals, boolean useShow) {

        // 构建一个Map,把所有原始数据的ID作为Key,原始数据对象作为VALUE
        Map<String, T> dtoMap = Maps.newHashMap();
        for (T node : originals) {
            // 原始数据对象为Node，放入dtoMap中。
            String id = node.getId();
            if (node.useShow() || !useShow) {
                dtoMap.put(id, node);
            }
        }

        List<T> result = Lists.newLinkedList();
        for (Map.Entry<String, T> entry : dtoMap.entrySet()) {
            T node = entry.getValue();
            String parentId = node.getParentId();
            if (dtoMap.get(parentId) == null) {
                // 如果是顶层节点，直接添加到结果集合中
                result.add(node);
            } else {
                // 如果不是顶层节点，有父节点，然后添加到父节点的子节点中
                T parent = dtoMap.get(parentId);
                parent.addChild(node);
            }
        }

        return result;
    }

}
