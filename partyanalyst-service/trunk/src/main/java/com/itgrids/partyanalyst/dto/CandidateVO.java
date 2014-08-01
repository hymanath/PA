package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CandidateVO {
	
	private Long id;
	private Long candidateId;
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
	private String scope;
	private String position;
	private Long totalSearchCount;
	private Long nominationId;
	private Long electionId;
	private String electionType;
	private String gender;
	private String education;
	private Long partyId;
	private Long noOfFriends;
	private Long noOfPosts;
	private Long districtId;
	private Long stateId;
	private String profileImg;
	private Long rank;
	private String caste;
	private Long casteId;
	private Long educationId;
	private Long workingForParty;
	private String mobileNo;
	private Long tehsilId;
	private Long localBodyId;
	private String tehsilName;
	private String localBodyName;
	private Double howLongWorkingInParty;
	private Double votesEarned;
	private List<GenericVO> candidatescastes=new ArrayList<GenericVO>();
	private List<GenericVO> candidatesEducations=new ArrayList<GenericVO>();
	
	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public Long getCasteId() {
		return casteId;
	}

	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}

	public Long getEducationId() {
		return educationId;
	}

	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}

	public Long getWorkingForParty() {
		return workingForParty;
	}

	public void setWorkingForParty(Long workingForParty) {
		this.workingForParty = workingForParty;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Long getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}

	public Long getLocalBodyId() {
		return localBodyId;
	}

	public void setLocalBodyId(Long localBodyId) {
		this.localBodyId = localBodyId;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getLocalBodyName() {
		return localBodyName;
	}

	public void setLocalBodyName(String localBodyName) {
		this.localBodyName = localBodyName;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

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

	public Long getTotalSearchCount() {
		return totalSearchCount;
	}

	public void setTotalSearchCount(Long totalSearchCount) {
		this.totalSearchCount = totalSearchCount;
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

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getRecepientId() {
		return recepientId;
	}

	public void setRecepientId(Long recepientId) {
		this.recepientId = recepientId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}	
	
	public Long getNominationId() {
		return nominationId;
	}

	public void setNominationId(Long nominationId) {
		this.nominationId = nominationId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Long getNoOfFriends() {
		return noOfFriends;
	}

	public void setNoOfFriends(Long noOfFriends) {
		this.noOfFriends = noOfFriends;
	}

	public Long getNoOfPosts() {
		return noOfPosts;
	}

	public void setNoOfPosts(Long noOfPosts) {
		this.noOfPosts = noOfPosts;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Double getHowLongWorkingInParty() {
		return howLongWorkingInParty;
	}

	public void setHowLongWorkingInParty(Double howLongWorkingInParty) {
		this.howLongWorkingInParty = howLongWorkingInParty;
	}

	public Double getVotesEarned() {
		return votesEarned;
	}

	public void setVotesEarned(Double votesEarned) {
		this.votesEarned = votesEarned;
	}

	public List<GenericVO> getCandidatescastes() {
		return candidatescastes;
	}

	public void setCandidatescastes(List<GenericVO> candidatescastes) {
		this.candidatescastes = candidatescastes;
	}

	public List<GenericVO> getCandidatesEducations() {
		return candidatesEducations;
	}

	public void setCandidatesEducations(List<GenericVO> candidatesEducations) {
		this.candidatesEducations = candidatesEducations;
	}
	
	

}
