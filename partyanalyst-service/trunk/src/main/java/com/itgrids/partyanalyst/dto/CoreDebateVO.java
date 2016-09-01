package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CoreDebateVO implements Serializable {

	private Long id;
	private String name;	
	private Long count=0l;
	private Long debateCount=0l;
	private Long candidateCount;	
	private Double scale=0.00;
	private Double scalePerc=0.00;	
	private Long candidateId;
	private String candidateName;	
	private Long charecterId;
	private String charecterName;
	
	private List<CoreDebateVO> coreDebateVOList ;
	
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
	
	
}
