package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DebateCandidateCharcVO implements Serializable {
	
	
	private Long debateSubjectId;
	private String debateSubject;
	private Long partyId;
	private String partyName;
	private Long candidateId;
	private String candidateName;
	private List<DebateCandidateCharcVO> partyList;
	private List<DebateCandidateCharcVO> debateSubjectList;
	private String characteristics;
	private String scale;
	private List<DebateCandidateCharcVO> candidatesList= new ArrayList<DebateCandidateCharcVO>();
	private List<DebateCandidateCharcVO> characList =  new ArrayList<DebateCandidateCharcVO>();
	private List<Long> candidateIds = new ArrayList<Long>();
	private Long characteristicsId;
	
	
	public Long getDebateSubjectId() {
		return debateSubjectId;
	}
	public void setDebateSubjectId(Long debateSubjectId) {
		this.debateSubjectId = debateSubjectId;
	}
	public String getDebateSubject() {
		return debateSubject;
	}
	public void setDebateSubject(String debateSubject) {
		this.debateSubject = debateSubject;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
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

	public List<DebateCandidateCharcVO> getDebateSubjectList() {
		return debateSubjectList;
	}
	public void setDebateSubjectList(List<DebateCandidateCharcVO> debateSubjectList) {
		this.debateSubjectList = debateSubjectList;
	}
	public List<DebateCandidateCharcVO> getPartyList() {
		return partyList;
	}
	public void setPartyList(List<DebateCandidateCharcVO> partyList) {
		this.partyList = partyList;
	}
	public String getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public List<DebateCandidateCharcVO> getCandidatesList() {
		return candidatesList;
	}
	public void setCandidatesList(List<DebateCandidateCharcVO> candidatesList) {
		this.candidatesList = candidatesList;
	}
	public List<DebateCandidateCharcVO> getCharacList() {
		return characList;
	}
	public void setCharacList(List<DebateCandidateCharcVO> characList) {
		this.characList = characList;
	}
	public List<Long> getCandidateIds() {
		return candidateIds;
	}
	public void setCandidateIds(List<Long> candidateIds) {
		this.candidateIds = candidateIds;
	}
	public Long getCharacteristicsId() {
		return characteristicsId;
	}
	public void setCharacteristicsId(Long characteristicsId) {
		this.characteristicsId = characteristicsId;
	}
	

}
