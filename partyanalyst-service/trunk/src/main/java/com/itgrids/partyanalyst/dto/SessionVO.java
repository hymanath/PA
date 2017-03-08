package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class SessionVO implements Serializable{
	
	private Long id;
	private String name;
	private Long plannedCount =0l;
	private Long conductedCount=0l;
	private Long notConductedCount = 0l;
	private Long invitedCount =0l;
	private Long lateCount =0l;
	private Long absentCount = 0l;
	private Long nonInviteeCount =0l;
	private Long imagesCovered =0l;
	private Long imagesCount=0l;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPlannedCount() {
		return plannedCount;
	}
	public void setPlannedCount(Long plannedCount) {
		this.plannedCount = plannedCount;
	}
	public Long getConductedCount() {
		return conductedCount;
	}
	public void setConductedCount(Long conductedCount) {
		this.conductedCount = conductedCount;
	}
	public Long getNotConductedCount() {
		return notConductedCount;
	}
	public void setNotConductedCount(Long notConductedCount) {
		this.notConductedCount = notConductedCount;
	}
	public Long getInvitedCount() {
		return invitedCount;
	}
	public void setInvitedCount(Long invitedCount) {
		this.invitedCount = invitedCount;
	}
	public Long getLateCount() {
		return lateCount;
	}
	public void setLateCount(Long lateCount) {
		this.lateCount = lateCount;
	}
	public Long getAbsentCount() {
		return absentCount;
	}
	public void setAbsentCount(Long absentCount) {
		this.absentCount = absentCount;
	}
	public Long getNonInviteeCount() {
		return nonInviteeCount;
	}
	public void setNonInviteeCount(Long nonInviteeCount) {
		this.nonInviteeCount = nonInviteeCount;
	}
	public Long getImagesCovered() {
		return imagesCovered;
	}
	public void setImagesCovered(Long imagesCovered) {
		this.imagesCovered = imagesCovered;
	}
	public Long getImagesCount() {
		return imagesCount;
	}
	public void setImagesCount(Long imagesCount) {
		this.imagesCount = imagesCount;
	}
	
			
	
}
