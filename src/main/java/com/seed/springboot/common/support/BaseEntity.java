/**   
 * @Title: BaseEntity.java 
 * @Package com.fosunling.common.core.entity 
 * @version V1.0   
 */
package com.seed.springboot.common.support;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seed.springboot.common.utils.lang.StringUtils;

/**
 * @ClassName: BaseEntity
 * @Description: TODO(Entity支持类)
 * @author RuYang ruyang@fosun.com
 * @date 2018年5月15日 上午11:32:11
 * 
 */
public abstract class BaseEntity<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5422395898065364085L;

	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;

	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	private boolean isNewRecord = false;

	public BaseEntity() {

	}

	public BaseEntity(String id) {
		this();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 插入之前执行方法，子类实现
	 */
	public abstract void preInsert();

	/**
	 * 更新之前执行方法，子类实现
	 */
	public abstract void preUpdate();

	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 *
	 * @return 是否是新记录
	 */
	@JsonIgnore
	public boolean getIsNewRecord() {
		return isNewRecord || StringUtils.isBlank(getId());
	}

	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 *
	 * @param isNewRecord
	 *            是否为新数据
	 */
	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		BaseEntity<?> that = (BaseEntity<?>) obj;
		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String STATUS_NORMAL = "0";
	public static final String STATUS_DELETE = "1";
	public static final String STATUS_AUDIT = "2";

}
