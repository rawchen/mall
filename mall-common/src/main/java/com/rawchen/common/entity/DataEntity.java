package com.rawchen.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.util.Map;

/**
 * @author RawChen
 * @since 2021-10-30 23:56
 */
public abstract class DataEntity<T> {

	@ApiModelProperty(hidden = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	protected Date createTime;

	@ApiModelProperty(hidden = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	protected Date updateTime;

	protected Page<T> page;

	protected Map<String, String> sqlMap;

	public DataEntity() {
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@JsonIgnore
	@XmlTransient
	public Page<T> getPage() {
		if (this.page == null) {
			this.page = new Page();
		}

		return this.page;
	}

	public Page<T> setPage(Page<T> page) {
		this.page = page;
		return page;
	}

	@JsonIgnore
	@XmlTransient
	public Map<String, String> getSqlMap() {
		if (this.sqlMap == null) {
			this.sqlMap = Maps.newHashMap();
		}

		return this.sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}

	public void preInsert() {
		this.updateTime = new Date();
		this.createTime = this.updateTime;
	}

	public void preUpdate() {
		this.updateTime = new Date();
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}