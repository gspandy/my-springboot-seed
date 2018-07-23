/**   
 * @Title: TreeEntity.java 
 * @Package com.seed.springboot.common.support 
 * @version V1.0   
 */
package com.seed.springboot.common.support;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.google.common.collect.Lists;

/**
 * @ClassName: TreeEntity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2018年7月20日 上午10:02:47
 * 
 */
public abstract class TreeEntity<T> extends DataEntity<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4081512518401753594L;

	@Column(name = "parent_id")
	private String parentId; // 父级编号

	@Column(name = "parent_ids")
	private String parentIds; // 所有父级编号

	@Column(name = "tree_sort")
	private String treeSort; // 本级排序号（升序）

	@Column(name = "tree_leaf")
	private String treeLeaf; // 是否最末级

	@Column(name = "tree_level")
	private Long treeLevel; // 层次级别
	
	@Column(name = "is_show")
	private String isShow;	// 是否显示（1显示 0隐藏）

	@Transient
	private List<T> children = Lists.newLinkedList();

	public TreeEntity() {
		super();
		this.setTreeSort("30");
	}

	public TreeEntity(String id) {
		super(id);
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the parentIds
	 */
	public String getParentIds() {
		return parentIds;
	}

	/**
	 * @param parentIds
	 *            the parentIds to set
	 */
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/**
	 * @return the treeSort
	 */
	public String getTreeSort() {
		return treeSort;
	}

	/**
	 * @param treeSort
	 *            the treeSort to set
	 */
	public void setTreeSort(String treeSort) {
		this.treeSort = treeSort;
	}

	/**
	 * @return the treeLeaf
	 */
	public String getTreeLeaf() {
		return treeLeaf;
	}

	/**
	 * @param treeLeaf
	 *            the treeLeaf to set
	 */
	public void setTreeLeaf(String treeLeaf) {
		this.treeLeaf = treeLeaf;
	}

	/**
	 * @return the treeLevel
	 */
	public Long getTreeLevel() {
		return treeLevel;
	}

	/**
	 * @param treeLevel
	 *            the treeLevel to set
	 */
	public void setTreeLevel(Long treeLevel) {
		this.treeLevel = treeLevel;
	}

	/**
	 * @return the children
	 */
	public List<T> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<T> children) {
		this.children = children;
	}

	/**
	 * 添加子节点
	 *
	 * @param node
	 *            菜单节点
	 */
	public void addChild(T node) {
		this.children.add(node);
	}

	/**
	 * @return the isShow
	 */
	public String getIsShow() {
		return isShow;
	}

	/**
	 * @param isShow the isShow to set
	 */
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	public boolean useShow(){
		return isShow.equals("1");
	}

}
