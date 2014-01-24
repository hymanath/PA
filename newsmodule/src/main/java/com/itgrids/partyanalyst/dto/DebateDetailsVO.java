package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DebateDetailsVO implements Serializable {

	private Long debateId;
	private Date startDate;
	private Date endDate;
	private Long channelId;
	private Long telecasteTypeId;
	private String debateSummery;
	private List<SelectOptionVO> observerList;
	private List<SelectOptionVO> subjectList;
	private List<ParticipantVO>  participentsList;
	private List<SelectOptionVO> questionsList;
	private List<SelectOptionVO> smsQuestionList;
	private List<SelectOptionVO> smaOptionsList;
	
	public Long getDebateId() {
		return debateId;
	}
	public void setDebateId(Long debateId) {
		this.debateId = debateId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public Long getTelecasteTypeId() {
		return telecasteTypeId;
	}
	public void setTelecasteTypeId(Long telecasteTypeId) {
		this.telecasteTypeId = telecasteTypeId;
	}
	public List<SelectOptionVO> getObserverList() {
		return observerList;
	}
	public void setObserverList(List<SelectOptionVO> observerList) {
		this.observerList = observerList;
	}
	public List<SelectOptionVO> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<SelectOptionVO> subjectList) {
		this.subjectList = subjectList;
	}
	public String getDebateSummery() {
		return debateSummery;
	}
	public void setDebateSummery(String debateSummery) {
		this.debateSummery = debateSummery;
	}
	public List<ParticipantVO> getParticipentsList() {
		return participentsList;
	}
	public void setParticipentsList(List<ParticipantVO> participentsList) {
		this.participentsList = participentsList;
	}
	public List<SelectOptionVO> getQuestionsList() {
		return questionsList;
	}
	public void setQuestionsList(List<SelectOptionVO> questionsList) {
		this.questionsList = questionsList;
	}
	public List<SelectOptionVO> getSmsQuestionList() {
		return smsQuestionList;
	}
	public void setSmsQuestionList(List<SelectOptionVO> smsQuestionList) {
		this.smsQuestionList = smsQuestionList;
	}
	public List<SelectOptionVO> getSmaOptionsList() {
		return smaOptionsList;
	}
	public void setSmaOptionsList(List<SelectOptionVO> smaOptionsList) {
		this.smaOptionsList = smaOptionsList;
	}
	
	
	
}
