package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class TrainingCampFeedBackDetailsVO {
	
	private Long id;
	private Long progranmmId;
	private String programName;
	private Long batchId;
	private String batchName;
	private Long campId;
	private String campName;
	private Long cadreId;
	private Long leadershiplevelId;
	private String leadershiplevel;
	private Long  leaderShipSkillsId;
	private String leaderShipStatus;
	private Long communicationSkillsId;
	private String communicationSkillsStatus;
	private String healthStatus;
	private String healthCardAttachment;
	private String remarks;
	private String smartPhoneExist;
	private String watsappUsing;
	private String  watsappShare;
	private String facebookUsing;
	private String year;
	private Long yearId;
	private List<TrainingCampFeedBackDetailsVO> subList = new ArrayList<TrainingCampFeedBackDetailsVO>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProgranmmId() {
		return progranmmId;
	}
	public void setProgranmmId(Long progranmmId) {
		this.progranmmId = progranmmId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public Long getCampId() {
		return campId;
	}
	public void setCampId(Long campId) {
		this.campId = campId;
	}
	public String getCampName() {
		return campName;
	}
	public void setCampName(String campName) {
		this.campName = campName;
	}
	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public Long getLeadershiplevelId() {
		return leadershiplevelId;
	}
	public void setLeadershiplevelId(Long leadershiplevelId) {
		this.leadershiplevelId = leadershiplevelId;
	}
	public String getLeadershiplevel() {
		return leadershiplevel;
	}
	public void setLeadershiplevel(String leadershiplevel) {
		this.leadershiplevel = leadershiplevel;
	}
	public Long getLeaderShipSkillsId() {
		return leaderShipSkillsId;
	}
	public void setLeaderShipSkillsId(Long leaderShipSkillsId) {
		this.leaderShipSkillsId = leaderShipSkillsId;
	}
	public String getLeaderShipStatus() {
		return leaderShipStatus;
	}
	public void setLeaderShipStatus(String leaderShipStatus) {
		this.leaderShipStatus = leaderShipStatus;
	}
	public Long getCommunicationSkillsId() {
		return communicationSkillsId;
	}
	public void setCommunicationSkillsId(Long communicationSkillsId) {
		this.communicationSkillsId = communicationSkillsId;
	}
	public String getCommunicationSkillsStatus() {
		return communicationSkillsStatus;
	}
	public void setCommunicationSkillsStatus(String communicationSkillsStatus) {
		this.communicationSkillsStatus = communicationSkillsStatus;
	}
	public String getHealthStatus() {
		return healthStatus;
	}
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSmartPhoneExist() {
		return smartPhoneExist;
	}
	public void setSmartPhoneExist(String smartPhoneExist) {
		this.smartPhoneExist = smartPhoneExist;
	}
	public String getWatsappUsing() {
		return watsappUsing;
	}
	public void setWatsappUsing(String watsappUsing) {
		this.watsappUsing = watsappUsing;
	}
	public String getWatsappShare() {
		return watsappShare;
	}
	public void setWatsappShare(String watsappShare) {
		this.watsappShare = watsappShare;
	}
	public String getFacebookUsing() {
		return facebookUsing;
	}
	public void setFacebookUsing(String facebookUsing) {
		this.facebookUsing = facebookUsing;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Long getYearId() {
		return yearId;
	}
	public void setYearId(Long yearId) {
		this.yearId = yearId;
	}
	public List<TrainingCampFeedBackDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<TrainingCampFeedBackDetailsVO> subList) {
		this.subList = subList;
	}
	public String getHealthCardAttachment() {
		return healthCardAttachment;
	}
	public void setHealthCardAttachment(String healthCardAttachment) {
		this.healthCardAttachment = healthCardAttachment;
	}

	
	
}
