/**   
 * @Title: DataEntity.java 
 * @Package com.fosunling.common.core.support 
 * @version V1.0   
 */
package com.seed.springboot.common.support;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seed.springboot.common.utils.idgen.IdGenerate;
import com.seed.springboot.common.utils.lang.StringUtils;

/**
 * @ClassName: DataEntity
 * @Description: TODO(数据Entity类)
 * @author RuYang ruyang@fosun.com
 * @date 2018年5月25日 上午9:39:14
 * 
 */
public class DataEntity<T> extends BaseEntity<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8606970070168863953L;

	protected String remarks; // 备注
	protected String createBy; // 创建者
	protected Date createDate; // 创建日期
	protected String updateBy; // 更新者
	protected Date updateDate; // 更新日期
	protected String status; // 删除标记（0：正常；1：删除；2：审核）

	public DataEntity() {
		super();
		this.status = STATUS_NORMAL;
	}

	public DataEntity(String id) {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fosunling.common.core.support.BaseEntity#preInsert()
	 */
	@Override
	public void preInsert() {
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (StringUtils.isBlank(getId())) {
			setId(IdGenerate.nextId());
		}

		if (this.status == null) {
			this.status = STATUS_NORMAL;
		}

		if (StringUtils.isNotBlank(this.createBy)) {
			this.updateBy = this.createBy;
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fosunling.common.core.support.BaseEntity#preUpdate()
	 */
	@Override
	public void preUpdate() {
		// TODO Auto-generated method stub
		this.updateDate = new Date();
	}

	@Length(min = 0, max = 255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonIgnore
	@Length(min = 1, max = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy the createBy to set
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return the updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	

}
