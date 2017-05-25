package com.itgrids.dto;

import java.util.Date;

public class TdpResolutionVo {
	
	private Long tdpResoultionId;

	private String subject;
	
	private String description;
	
	private String videourl;
	
	private boolean isDeleted;
	
	private String InsertedTime;
	
	private String UpdatedTime;

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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getInsertedTime() {
		return InsertedTime;
	}

	public void setInsertedTime(String insertedTime) {
		InsertedTime = insertedTime;
	}

	public String getUpdatedTime() {
		return UpdatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		UpdatedTime = updatedTime;
	}

	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}
	

}
