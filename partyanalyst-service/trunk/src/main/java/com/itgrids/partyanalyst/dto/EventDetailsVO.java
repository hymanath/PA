package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class EventDetailsVO {

	private Long id;
	
	private String name;
	private Long inviteeCount=0l;
	private Long inviteeAttendedCount=0l;
	private Long inviteeNotAttendedCount=0l;
	private Long attendedCount=0l;
	private Long nonInviteeAttendedCount=0l;
	
	private Double inviteeAttendedCounPer=0.0d;
	private Double inviteeNotAttendedCountPer=0.0d;
	private Double attendedCountPer=0.0d;
	private Double nonInviteeAttendedCountPer=0.0d;
	
	private List<EventDetailsVO> locationList;
	
	private List<EventDetailsVO> districtList;
	private List<EventDetailsVO> constituencyList;
	private List<EventDetailsVO> mandalTwnDivisionList;
	private List<EventDetailsVO> villageWardList;
	private Long infoCellAttendedCount=0L;
	
	private Long imagesCovered=0l;
	private Long totalImages=0l;
	private Long yesCount = 0l;
	private Long noCount = 0l;
	private Long mayBeCount = 0l;
	private Long notUpdatedCount = 0l;
	private Long scopeId;
	private Long conductedCount = 0l;
	private String type;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getInfoCellAttendedCount() {
		return infoCellAttendedCount;
	}
	public void setInfoCellAttendedCount(Long infoCellAttendedCount) {
		this.infoCellAttendedCount = infoCellAttendedCount;
	}
	public Long getImagesCovered() {
		return imagesCovered;
	}
	public void setImagesCovered(Long imagesCovered) {
		this.imagesCovered = imagesCovered;
	}
	public Long getTotalImages() {
		return totalImages;
	}
	public void setTotalImages(Long totalImages) {
		this.totalImages = totalImages;
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
	public Long getInviteeCount() {
		return inviteeCount;
	}
	public void setInviteeCount(Long inviteeCount) {
		this.inviteeCount = inviteeCount;
	}
	public Long getInviteeAttendedCount() {
		return inviteeAttendedCount;
	}
	public void setInviteeAttendedCount(Long inviteeAttendedCount) {
		this.inviteeAttendedCount = inviteeAttendedCount;
	}
	public Long getInviteeNotAttendedCount() {
		return inviteeNotAttendedCount;
	}
	public void setInviteeNotAttendedCount(Long inviteeNotAttendedCount) {
		this.inviteeNotAttendedCount = inviteeNotAttendedCount;
	}
	public Long getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	public Long getNonInviteeAttendedCount() {
		return nonInviteeAttendedCount;
	}
	public void setNonInviteeAttendedCount(Long nonInviteeAttendedCount) {
		this.nonInviteeAttendedCount = nonInviteeAttendedCount;
	}
	public Double getInviteeAttendedCounPer() {
		return inviteeAttendedCounPer;
	}
	public void setInviteeAttendedCounPer(Double inviteeAttendedCounPer) {
		this.inviteeAttendedCounPer = inviteeAttendedCounPer;
	}
	public Double getInviteeNotAttendedCountPer() {
		return inviteeNotAttendedCountPer;
	}
	public void setInviteeNotAttendedCountPer(Double inviteeNotAttendedCountPer) {
		this.inviteeNotAttendedCountPer = inviteeNotAttendedCountPer;
	}
	public Double getAttendedCountPer() {
		return attendedCountPer;
	}
	public void setAttendedCountPer(Double attendedCountPer) {
		this.attendedCountPer = attendedCountPer;
	}
	public Double getNonInviteeAttendedCountPer() {
		return nonInviteeAttendedCountPer;
	}
	public void setNonInviteeAttendedCountPer(Double nonInviteeAttendedCountPer) {
		this.nonInviteeAttendedCountPer = nonInviteeAttendedCountPer;
	}
	public List<EventDetailsVO> getLocationList() {
		if(locationList == null){
			locationList = new ArrayList<EventDetailsVO>(0);
		}
		return locationList;
	}
	public List<EventDetailsVO> getDistrictList() {
		if(districtList == null){
			districtList = new ArrayList<EventDetailsVO>();
		}
		return districtList;
	}
	public List<EventDetailsVO> getConstituencyList() {
		if(constituencyList == null){
		 constituencyList = new ArrayList<EventDetailsVO>();
		}
		return constituencyList;
	}
	public List<EventDetailsVO> getMandalTwnDivisionList() {
		if(mandalTwnDivisionList == null){
			mandalTwnDivisionList = new ArrayList<EventDetailsVO>();
		}
		return mandalTwnDivisionList;
	}
	public List<EventDetailsVO> getVillageWardList() {
		if(villageWardList == null ){
			villageWardList = new ArrayList<EventDetailsVO>();
		}
		return villageWardList;
	}
	public Long getYesCount() {
		return yesCount;
	}
	public void setYesCount(Long yesCount) {
		this.yesCount = yesCount;
	}
	public Long getNoCount() {
		return noCount;
	}
	public void setNoCount(Long noCount) {
		this.noCount = noCount;
	}
	public Long getMayBeCount() {
		return mayBeCount;
	}
	public void setMayBeCount(Long mayBeCount) {
		this.mayBeCount = mayBeCount;
	}
	public Long getNotUpdatedCount() {
		return notUpdatedCount;
	}
	public void setNotUpdatedCount(Long notUpdatedCount) {
		this.notUpdatedCount = notUpdatedCount;
	}
	public Long getScopeId() {
		return scopeId;
	}
	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}
	public Long getConductedCount() {
		return conductedCount;
	}
	public void setConductedCount(Long conductedCount) {
		this.conductedCount = conductedCount;
	}
	
  	
   	
   
}
