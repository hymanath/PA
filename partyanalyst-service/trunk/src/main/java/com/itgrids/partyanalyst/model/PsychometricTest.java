package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
@Table(name = "psychometric_test")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PsychometricTest extends BaseModel implements Serializable{
	
	private Long psychometricTestId;
	private String testUrl;
	private String uniqueId;
	private String isUsed;
	private Date insertedTime;
	private Date updatedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "psychometric_test_id", unique = true, nullable = false)
	public Long getPsychometricTestId() {
		return psychometricTestId;
	}
	public void setPsychometricTestId(Long psychometricTestId) {
		this.psychometricTestId = psychometricTestId;
	}
	
	@Column(name = "test_url", length = 100)
	public String getTestUrl() {
		return testUrl;
	}
	public void setTestUrl(String testUrl) {
		this.testUrl = testUrl;
	}
	
	@Column(name = "unique_id", length = 100)
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	@Column(name = "is_used", length = 100)
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	
	@Column(name = "inserted_time", length = 100)
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "updated_time", length = 100)
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}
