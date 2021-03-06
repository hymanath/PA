package com.itgrids.dto;

import java.util.List;

public class TdpResolutionVo {
	
	private Long tdpResoultionId;

	private String subject;
	
	private String description;
	
	private String videourl;
	
	private boolean isDeleted;
	
	private String InsertedTime;
	
	private String UpdatedTime;
	
	private Long membershipId;
	
	private List<String> listSubjects;

	public List<String> getListSubjects() {
		return listSubjects;
	}

	public void setListSubjects(List<String> listSubjects) {
		this.listSubjects = listSubjects;
	}

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
	

	public Long getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(Long membershipId) {
		this.membershipId = membershipId;
	}

	
}
