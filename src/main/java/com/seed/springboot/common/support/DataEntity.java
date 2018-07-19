/**   
 * @Title: DataEntity.java 
 * @Package com.fosunling.common.core.support 
 * @version V1.0   
 */
package com.seed.springboot.common.support;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

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

	@Length(min = 0, max = 255)
	private String remarks; // 备注
	
	@Column(name = "create_by")
	private String createBy; // 创建者
	
	@Column(name = "create_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate; // 创建日期
	
	@Column(name = "update_by")
	private String updateBy; // 更新者
	
	@Column(name = "update_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateDate; // 更新日期
	
	@JsonIgnore
	@Length(min = 1, max = 1)
	private String status; // 删除标记（0：正常；1：删除；）
	
	@Transient
	private Integer pageNum;

	@Transient
	private Integer pageSize;

	@Transient
	private String orderBy;

	public DataEntity() {
		super();
		this.status = STATUS_NORMAL;
	}

	public DataEntity(String id) {
		super(id);
	}

	@Transient
	@JsonIgnore
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

	@Transient
	@JsonIgnore
	@Override
	public void preUpdate() {
		// TODO Auto-generated method stub
		this.updateDate = new Date();
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the pageNum
	 */
	public Integer getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	
}
