package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DebateVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long debateId;
	private String debateName;
	private List<String> debateNames;
	private String startTime;
	private String endTime;
	private String date;
	private Long channelId;
	private String channelName;
	private Long telecastTypeId;
	private String telecastTime;
	private List<String> observorsList;
	private List<SelectOptionVO> questionAnswersList;
	private List<SelectOptionVO> smsPoleList;
	private String debateSummery;
	private List<SelectOptionVO> candidateSummery;
	private List<ParticipantVO> participantsList;
	private List<String> expRoles;
	private List<SelectOptionVO> debateExpRolesList;
	private List<SelectOptionVO> debateRolesList;
	private Date StartDate;
	private Date endDate;
	public Long getDebateId() {
		return debateId;
	}
	public void setDebateId(Long debateId) {
		this.debateId = debateId;
	}
	public String getDebateName() {
		return debateName;
	}
	public void setDebateName(String debateName) {
		this.debateName = debateName;
	}
	public List<String> getDebateNames() {
		return debateNames;
	}
	public void setDebateNames(List<String> debateNames) {
		this.debateNames = debateNames;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTelecastTime() {
		return telecastTime;
	}
	public void setTelecastTime(String telecastTime) {
		this.telecastTime = telecastTime;
	}
	
	public List<String> getObservorsList() {
		return observorsList;
	}
	public void setObservorsList(List<String> observorsList) {
		this.observorsList = observorsList;
	}
	public List<SelectOptionVO> getQuestionAnswersList() {
		return questionAnswersList;
	}
	public void setQuestionAnswersList(List<SelectOptionVO> questionAnswersList) {
		this.questionAnswersList = questionAnswersList;
	}
	public List<SelectOptionVO> getSmsPoleList() {
		return smsPoleList;
	}
	public void setSmsPoleList(List<SelectOptionVO> smsPoleList) {
		this.smsPoleList = smsPoleList;
	}
	public String getDebateSummery() {
		return debateSummery;
	}
	public void setDebateSummery(String debateSummery) {
		this.debateSummery = debateSummery;
	}
	public List<SelectOptionVO> getCandidateSummery() {
		return candidateSummery;
	}
	public void setCandidateSummery(List<SelectOptionVO> candidateSummery) {
		this.candidateSummery = candidateSummery;
	}
	public List<ParticipantVO> getParticipantsList() {
		return participantsList;
	}
	public void setParticipantsList(List<ParticipantVO> participantsList) {
		this.participantsList = participantsList;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Long getTelecastTypeId() {
		return telecastTypeId;
	}
	public void setTelecastTypeId(Long telecastTypeId) {
		this.telecastTypeId = telecastTypeId;
	}
	public List<String> getExpRoles() {
		return expRoles;
	}
	public void setExpRoles(List<String> expRoles) {
		this.expRoles = expRoles;
	}
	public List<SelectOptionVO> getDebateExpRolesList() {
		return debateExpRolesList;
	}
	public void setDebateExpRolesList(List<SelectOptionVO> debateExpRolesList) {
		this.debateExpRolesList = debateExpRolesList;
	}
	public List<SelectOptionVO> getDebateRolesList() {
		return debateRolesList;
	}
	public void setDebateRolesList(List<SelectOptionVO> debateRolesList) {
		this.debateRolesList = debateRolesList;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
