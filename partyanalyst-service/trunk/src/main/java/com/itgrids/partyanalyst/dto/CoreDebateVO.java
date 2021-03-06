package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CoreDebateVO implements Serializable {

	private Long id;
	private String name;	
	private Long count=0l;
	private Long debateCount=0l;
	private Long candidateCount=0l;	
	private Double scale=0.00;
	private Double scalePerc=0.00;	
	private Long candidateId;
	private String candidateName;	
	private Long charecterId;
	private String charecterName;
	private Double overAllPerc=0.00;
	private Long overAllDebateCount=0l;
	
	private String recentTime;
	
	private String startTime;
	private String endTime;
	private List<String> debateSubject;
	private Long observerId;
	private String observerName;
	private Long debateLocationId;
	private String debateLocation;
	private List<CoreDebateVO> coreDebateVOList;
	private Long debatesCount=0l;
	private Double scalesCount=0.00;
	private String candidateDesignation;
	private Long designationId;
	private String partyName;
	private Long debateBaseCandidateCount=0l;
	private Long casteId;
	private String casteName;
	private Long casteDebateCount=0l;
	private Long casteCandidateCount =0l;
	private Double debatePer=0.00;
	private Double participantPer=0.00;
	private String subCastName;
	//Newly added for SMS poll
	private List<CoreDebateVO> list = new ArrayList<CoreDebateVO>(0); 
	private String option;
	private String percentage;
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getDebateCount() {
		return debateCount;
	}
	public void setDebateCount(Long debateCount) {
		this.debateCount = debateCount;
	}
	public Long getCandidateCount() {
		return candidateCount;
	}
	public void setCandidateCount(Long candidateCount) {
		this.candidateCount = candidateCount;
	}
	public Double getScale() {
		return scale;
	}
	public void setScale(Double scale) {
		this.scale = scale;
	}
	public Double getScalePerc() {
		return scalePerc;
	}
	public void setScalePerc(Double scalePerc) {
		this.scalePerc = scalePerc;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public List<CoreDebateVO> getCoreDebateVOList() {
		return coreDebateVOList;
	}
	public void setCoreDebateVOList(List<CoreDebateVO> coreDebateVOList) {
		this.coreDebateVOList = coreDebateVOList;
	}
	public Long getCharecterId() {
		return charecterId;
	}
	public void setCharecterId(Long charecterId) {
		this.charecterId = charecterId;
	}
	public String getCharecterName() {
		return charecterName;
	}
	public void setCharecterName(String charecterName) {
		this.charecterName = charecterName;
	}
	public Double getOverAllPerc() {
		return overAllPerc;
	}
	public void setOverAllPerc(Double overAllPerc) {
		this.overAllPerc = overAllPerc;
	}
	public String getRecentTime() {
		return recentTime;
	}
	public void setRecentTime(String recentTime) {
		this.recentTime = recentTime;
	}
	public Long getOverAllDebateCount() {
		return overAllDebateCount;
	}
	public void setOverAllDebateCount(Long overAllDebateCount) {
		this.overAllDebateCount = overAllDebateCount;
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
	public List<String> getDebateSubject() {
		return debateSubject;
	}
	public void setDebateSubject(List<String> debateSubject) {
		this.debateSubject = debateSubject;
	}
	public Long getObserverId() {
		return observerId;
	}
	public void setObserverId(Long observerId) {
		this.observerId = observerId;
	}
	public String getObserverName() {
		return observerName;
	}
	public void setObserverName(String observerName) {
		this.observerName = observerName;
	}
	public Long getDebateLocationId() {
		return debateLocationId;
	}
	public void setDebateLocationId(Long debateLocationId) {
		this.debateLocationId = debateLocationId;
	}
	public String getDebateLocation() {
		return debateLocation;
	}
	public void setDebateLocation(String debateLocation) {
		this.debateLocation = debateLocation;
	}
	public Long getDebatesCount() {
		return debatesCount;
	}
	public void setDebatesCount(Long debatesCount) {
		this.debatesCount = debatesCount;
	}
	public Double getScalesCount() {
		return scalesCount;
	}
	public void setScalesCount(Double scalesCount) {
		this.scalesCount = scalesCount;
	}
	public String getCandidateDesignation() {
		return candidateDesignation;
	}
	public void setCandidateDesignation(String candidateDesignation) {
		this.candidateDesignation = candidateDesignation;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Long getDebateBaseCandidateCount() {
		return debateBaseCandidateCount;
	}
	public void setDebateBaseCandidateCount(Long debateBaseCandidateCount) {
		this.debateBaseCandidateCount = debateBaseCandidateCount;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public Long getCasteDebateCount() {
		return casteDebateCount;
	}
	public void setCasteDebateCount(Long casteDebateCount) {
		this.casteDebateCount = casteDebateCount;
	}
	public Long getCasteCandidateCount() {
		return casteCandidateCount;
	}
	public void setCasteCandidateCount(Long casteCandidateCount) {
		this.casteCandidateCount = casteCandidateCount;
	}
	public Double getDebatePer() {
		return debatePer;
	}
	public void setDebatePer(Double debatePer) {
		this.debatePer = debatePer;
	}
	public Double getParticipantPer() {
		return participantPer;
	}
	public void setParticipantPer(Double participantPer) {
		this.participantPer = participantPer;
	}
	public String getSubCastName() {
		return subCastName;
	}
	public void setSubCastName(String subCastName) {
		this.subCastName = subCastName;
	}
	public List<CoreDebateVO> getList() {
		return list;
	}
	public void setList(List<CoreDebateVO> list) {
		this.list = list;
	}

	
	//
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
}
