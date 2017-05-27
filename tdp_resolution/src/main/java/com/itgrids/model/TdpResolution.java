package com.itgrids.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tdp_resolution")
public class TdpResolution {

	@Id
	@Column(name="tdp_resolution_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long tdpResoultionId;

	@Column(name="subject")
	private String subject;
	
	@Column(name="description")
	private String description;
	
	@Column(name="video_url")
	private String videourl;
	
	@Column(name="is_deleted")
	private String isDeleted;
	
	@Column(name="inserted_time")
	private Date InsertedTime;
	
	@Column(name="updated_time")
	private Date UpdatedTime;
	
	@Column(name="membership_id")
	private Long membershipId;

	public Long getTdpResoultionId() {
		return tdpResoultionId;
	}

	public void setTdpResoultionId(Long tdpResoultionId) {
		this.tdpResoultionId = tdpResoultionId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getInsertedTime() {
		return InsertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		InsertedTime = insertedTime;
	}

	public Date getUpdatedTime() {
		return UpdatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		UpdatedTime = updatedTime;
	}
	
	public Long getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(Long membershipId) {
		this.membershipId = membershipId;
	}

	
}
