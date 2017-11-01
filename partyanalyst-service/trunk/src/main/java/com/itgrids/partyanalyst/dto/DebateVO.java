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
	private Long totalCount;
	private Date StartDate;
	private Date endDate;
	private Long noTdpLeaders;;
	private List<SelectOptionVO> observerList;
	private String stDate;
	private String edDate;
	
	private Long totalDebates;
	private Long candidateId;
	
	private String youtubeUrl;
	
	private Long debateLocId;
	private String debateLocation;
	
	public String getYoutubeUrl() {
		return youtubeUrl;
	}
	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}
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
	public Long getNoTdpLeaders() {
		return noTdpLeaders;
	}
	public void setNoTdpLeaders(Long noTdpLeaders) {
		this.noTdpLeaders = noTdpLeaders;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public List<SelectOptionVO> getObserverList() {
		return observerList;
	}
	public void setObserverList(List<SelectOptionVO> observerList) {
		this.observerList = observerList;
	}
	public String getStDate() {
		return stDate;
	}
	public void setStDate(String stDate) {
		this.stDate = stDate;
	}
	public String getEdDate() {
		return edDate;
	}
	public void setEdDate(String edDate) {
		this.edDate = edDate;
	}
	public Long getTotalDebates() {
		return totalDebates;
	}
	public void setTotalDebates(Long totalDebates) {
		this.totalDebates = totalDebates;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public Long getDebateLocId() {
		return debateLocId;
	}
	public void setDebateLocId(Long debateLocId) {
		this.debateLocId = debateLocId;
	}
	public String getDebateLocation() {
		return debateLocation;
	}
	public void setDebateLocation(String debateLocation) {
		this.debateLocation = debateLocation;
	}
	
	
}
