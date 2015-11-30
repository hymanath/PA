package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "activity_info_file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityInfoFile extends BaseModel implements Serializable{
	
	private Long activityInfoFileId;
	private Long activityLocationInfoId;
	private String url;
	private String remarks;
	
	private ActivityLocationInfo activityLocationInfo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_info_file_id", unique = true, nullable = false)
	public Long getActivityInfoFileId() {
		return activityInfoFileId;
	}
	public void setActivityInfoFileId(Long activityInfoFileId) {
		this.activityInfoFileId = activityInfoFileId;
	}
	
	@Column(name="activity_location_info_id")
	public Long getActivityLocationInfoId() {
		return activityLocationInfoId;
	}
	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
	}
	
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_location_info_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityLocationInfo getActivityLocationInfo() {
		return activityLocationInfo;
	}
	public void setActivityLocationInfo(ActivityLocationInfo activityLocationInfo) {
		this.activityLocationInfo = activityLocationInfo;
	}
}
