package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "news_bullition_news_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NewsBullitionNewsType extends BaseModel implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long newsBullitionNewsTypeId;
	private String description;
	private Date insertedTime;
	private Date updateTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "news_bullition_news_type_id", unique = true, nullable = false)
	public Long getNewsBullitionNewsTypeId() {
		return newsBullitionNewsTypeId;
	}
	public void setNewsBullitionNewsTypeId(Long newsBullitionNewsTypeId) {
		this.newsBullitionNewsTypeId = newsBullitionNewsTypeId;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
