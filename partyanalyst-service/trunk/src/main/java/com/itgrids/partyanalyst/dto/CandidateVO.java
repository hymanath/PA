package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;

public class CandidateVO {
	
	private Long id;
	private String candidateName;
	private String data;
	private String image;
	private String year;
	private String party;
	private String status;
	private String message;
	private Long constituencyId;
	private String constituencyName;
	private ResultStatus resultStatus;
	private List<CandidateElectionVO> candidateElectionVOs;
	private String state;
	private String district;
	private String postedDate;
	private Long costumMessageId;
	private Long recepientId;
	private Long nominationId;
	private Long electionId;
	private String electionType;
	
	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public CandidateVO(){
		
	}

	public CandidateVO(Long id, String candidateName,String image, String year,
			List<CandidateElectionVO> candidateElectionVOs, String party) {
		this.id = id;
		this.candidateName = candidateName;
		this.year = year;
		this.candidateElectionVOs = candidateElectionVOs;
		this.party = party;
		this.image = image;
	}

		
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<CandidateElectionVO> getCandidateElectionVOs() {
		return candidateElectionVOs;
	}

	public void setCandidateElectionVOs(
			List<CandidateElectionVO> candidateElectionVOs) {
		this.candidateElectionVOs = candidateElectionVOs;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public Long getCostumMessageId() {
		return costumMessageId;
	}

	public void setCostumMessageId(Long costumMessageId) {
		this.costumMessageId = costumMessageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getRecepientId() {
		return recepientId;
	}

	public void setRecepientId(Long recepientId) {
		this.recepientId = recepientId;
	}	
	
	public Long getNominationId() {
		return nominationId;
	}

	public void setNominationId(Long nominationId) {
		this.nominationId = nominationId;
	}

}
