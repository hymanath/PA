package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SessionVO implements Serializable{
	
	private Long id;
	private String name;
	private Long plannedCount =0l;
	private Long conductedCount=0l;
	private Long notConductedCount = 0l;
	private Long invitedCount =0l;
	private Long lateCount =0l;
	private Long absentCount = 0l;
	private Long inviteeAttendedCount =0l;
	private Long nonInviteeCount =0l;
	private Long imagesCovered =0l;
	private Long imagesCount=0l;
	private String lateTime;
	private String attendedTime;
	private Set<Long> inviteeAttendedCadreIdsList = new HashSet<Long>();
	private Set<Long> nonInviteeAttendedCadreIdsList = new HashSet<Long>();
	private String isLate;
	
	
	public String getIsLate() {
		return isLate;
	}
	public void setIsLate(String isLate) {
		this.isLate = isLate;
	}
	public String getAttendedTime() {
		return attendedTime;
	}
	public void setAttendedTime(String attendedTime) {
		this.attendedTime = attendedTime;
	}
	public Long getInviteeAttendedCount() {
		return inviteeAttendedCount;
	}
	public void setInviteeAttendedCount(Long inviteeAttendedCount) {
		this.inviteeAttendedCount = inviteeAttendedCount;
	}
	public Set<Long> getInviteeAttendedCadreIdsList() {
		return inviteeAttendedCadreIdsList;
	}
	public void setInviteeAttendedCadreIdsList(Set<Long> inviteeAttendedCadreIdsList) {
		this.inviteeAttendedCadreIdsList = inviteeAttendedCadreIdsList;
	}
	public Set<Long> getNonInviteeAttendedCadreIdsList() {
		return nonInviteeAttendedCadreIdsList;
	}
	public void setNonInviteeAttendedCadreIdsList(
			Set<Long> nonInviteeAttendedCadreIdsList) {
		this.nonInviteeAttendedCadreIdsList = nonInviteeAttendedCadreIdsList;
	}
	public String getLateTime() {
		return lateTime;
	}
	public void setLateTime(String lateTime) {
		this.lateTime = lateTime;
	}
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
