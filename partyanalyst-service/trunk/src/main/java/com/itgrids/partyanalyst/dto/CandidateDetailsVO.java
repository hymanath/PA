/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 2, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class CandidateDetailsVO implements Serializable , Comparable<CandidateDetailsVO>{

	private static final long serialVersionUID = 1L;
	
	private Long candidateId;
	private String candidateName;
	private Long age;
	private String gender;
	private String partyName;
	private Long electionId;
	private String electionType;
	private String electionYear;
	private String constituencyName;
	private Long constituencyId;
	private Long districtId;
	private String districtName;
	private Long stateId;
	private String stateName;
	private Long rank;
	private String votesEarned;
	private Long candidateVotesEarned;
	private String votesPercentage;
	private Boolean status;
	private String image;
	private String result;
	private String partyFlag;
	private Long commentsCount;
	private List<CandidateOppositionVO> oppositionCandidates;
	private List<SelectOptionVO> latestConstituencies ;
	private List<SelectOptionVO> getAllStates ;
	private List<SelectOptionVO> getDistricts ;
	private List<CandidateDetailsVO> candidateDetails ;
	private Long dataAvailabilityFlag;
	private String moreDetails;
	private Float votesDifference;
	private BigDecimal marginVotesPercentage;
	private MandalAllElectionDetailsVO mandalAllElectionDetailsVO;
	private Long currentRowNo;
	private String localBodyElectionsConstituencyName;
	private String reservationZone;
	private Long totalSearchCount;
	private String education;
	private String shortName;
	private Long parliamentConstituencyId;
	private String parliamentConstituencyName;
	private Long partyId;
	private String candidateRole;
	private Long voterId;
	
	
	// Candidate update form properties
	
	private String newCaste;
	private String emailId;
	private String websiteAddress;
	private String twitterUrl;
	private String facebookUrl;
	private String casteCategory1;
	private Long casteGroupId;
	private Long casteId;
	private Long phoneType;
	
	private Long casteCategoryId;
	private List<SelectOptionVO> casteGroupList;
	private List<SelectOptionVO> casteGroupNameList;
	//private boolean isSaved;
	
	private Long candidatecasteId;
	private Long socialNetworkSiteId;
	private Long websiteId;
	private Long casteStateId;
	private Long candidateSocialTwitterId;
	private Long candidateSocialFacebookId;
	
	private String voteAge;
	private String voteName;
	private String guardianName;
	private String relationship;
	private String houseNo;
	private String caste;
	private String casteCategory;
		
	public String getVoteAge() {
		return voteAge;
	}
	public void setVoteAge(String voteAge) {
		this.voteAge = voteAge;
	}
	public String getVoteName() {
		return voteName;
	}
	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}
	public Long getCandidateSocialFacebookId() {
		return candidateSocialFacebookId;
	}
	public void setCandidateSocialFacebookId(Long candidateSocialFacebookId) {
		this.candidateSocialFacebookId = candidateSocialFacebookId;
	}
	public Long getCandidateSocialTwitterId() {
		return candidateSocialTwitterId;
	}
	public void setCandidateSocialTwitterId(Long candidateSocialTwitterId) {
		this.candidateSocialTwitterId = candidateSocialTwitterId;
	}
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}

	public Long getCandidatecasteId() {
		return candidatecasteId;
	}

	public void setCandidatecasteId(Long candidatecasteId) {
		this.candidatecasteId = candidatecasteId;
	}

	public Long getSocialNetworkSiteId() {
		return socialNetworkSiteId;
	}

	public void setSocialNetworkSiteId(Long socialNetworkSiteId) {
		this.socialNetworkSiteId = socialNetworkSiteId;
	}

	public Long getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(Long websiteId) {
		this.websiteId = websiteId;
	}
/*
	public boolean isSaved() {
		return isSaved;
	}

	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}*/

	public List<SelectOptionVO> getCasteGroupNameList() {
		return casteGroupNameList;
	}

	public void setCasteGroupNameList(List<SelectOptionVO> casteGroupNameList) {
		this.casteGroupNameList = casteGroupNameList;
	}

	public List<SelectOptionVO> getCasteGroupList() {
		return casteGroupList;
	}

	public void setCasteGroupList(List<SelectOptionVO> casteGroupList) {
		this.casteGroupList = casteGroupList;
	}

	public Long getCasteCategoryId() {
		return casteCategoryId;
	}

	public void setCasteCategoryId(Long casteCategoryId) {
		this.casteCategoryId = casteCategoryId;
	}



	private String casteGroupName;
	
   private List<AddressVO> addressList;
	
	public List<AddressVO> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressVO> addressList) {
		this.addressList = addressList;
	}

	
	
	//private AddressVO address;
	
	


	private String casteName;
	

	
	

	
	

	public String getCasteCategory1() {
		return casteCategory1;
	}
	public void setCasteCategory1(String casteCategory1) {
		this.casteCategory1 = casteCategory1;
	}
	public String getCasteGroupName() {
		return casteGroupName;
	}
	public void setCasteGroupName(String casteGroupName) {
		this.casteGroupName = casteGroupName;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}

	public Long getPhoneType() {
		return phoneType;
	}

	public String getNewCaste() {
		return newCaste;
	}
	public void setNewCaste(String newCaste) {
		this.newCaste = newCaste;
	}

	public void setPhoneType(Long phoneType) {
		this.phoneType = phoneType;
	}
	
	public Long getCasteGroupId() {
		return casteGroupId;
	}
	public void setCasteGroupId(Long casteGroupId) {
		this.casteGroupId = casteGroupId;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}



	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getWebsiteAddress() {
		return websiteAddress;
	}
	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}
	public String getTwitterUrl() {
		return twitterUrl;
	}
	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}
	public String getFacebookUrl() {
		return facebookUrl;
	}
	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	
	//getters and setters
	
	public void setParliamentConstituencyName(String parliamentConstituencyName) {
		this.parliamentConstituencyName = parliamentConstituencyName;
	}
	public String getParliamentConstituencyName() {
		return parliamentConstituencyName;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getShortName() {
		return shortName;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Long getTotalSearchCount() {
		return totalSearchCount;
	}
	public void setTotalSearchCount(Long totalSearchCount) {
		this.totalSearchCount = totalSearchCount;
	}
	public String getReservationZone() {
		return reservationZone;
	}
	public void setReservationZone(String reservationZone) {
		this.reservationZone = reservationZone;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getDataAvailabilityFlag() {
		return dataAvailabilityFlag;
	}
	public void setDataAvailabilityFlag(Long dataAvailabilityFlag) {
		this.dataAvailabilityFlag = dataAvailabilityFlag;
	}
	public List<CandidateDetailsVO> getCandidateDetails() {
		return candidateDetails;
	}
	public void setCandidateDetails(List<CandidateDetailsVO> candidateDetails) {
		this.candidateDetails = candidateDetails;
	}
	public String getResult() {
		return result;
	}
	public List<SelectOptionVO> getLatestConstituencies() {
		return latestConstituencies;
	}
	public void setLatestConstituencies(List<SelectOptionVO> latestConstituencies) {
		this.latestConstituencies = latestConstituencies;
	}
	public List<SelectOptionVO> getGetAllStates() {
		return getAllStates;
	}
	public void setGetAllStates(List<SelectOptionVO> getAllStates) {
		this.getAllStates = getAllStates;
	}
	public void setResult(String result) {
		this.result = result;
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
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
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
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public String getPartyFlag() {
		return partyFlag;
	}
	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public BigDecimal getMarginVotesPercentage() {
		return marginVotesPercentage;
	}
	public void setMarginVotesPercentage(BigDecimal marginVotesPercentage) {
		this.marginVotesPercentage = marginVotesPercentage;
	}
	public MandalAllElectionDetailsVO getMandalAllElectionDetailsVO() {
		return mandalAllElectionDetailsVO;
	}
	public void setMandalAllElectionDetailsVO(
			MandalAllElectionDetailsVO mandalAllElectionDetailsVO) {
		this.mandalAllElectionDetailsVO = mandalAllElectionDetailsVO;
	}
	public String getMoreDetails() {
		return moreDetails;
	}
	public void setMoreDetails(String moreDetails) {
		this.moreDetails = moreDetails;
	}
	public Float getVotesDifference() {
		return votesDifference;
	}
	public void setVotesDifference(Float votesDifference) {
		this.votesDifference = votesDifference;
	}
	public List<SelectOptionVO> getGetDistricts() {
		return getDistricts;
	}
	public void setGetDistricts(List<SelectOptionVO> getDistricts) {
		this.getDistricts = getDistricts;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public String getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(String votesEarned) {
		this.votesEarned = votesEarned;
	}
	public String getVotesPercentage() {
		return votesPercentage;
	}
	public void setVotesPercentage(String votesPercentage) {
		this.votesPercentage = votesPercentage;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<CandidateOppositionVO> getOppositionCandidates() {
		return oppositionCandidates;
	}
	public void setOppositionCandidates(
			List<CandidateOppositionVO> oppositionCandidates) {
		this.oppositionCandidates = oppositionCandidates;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getCandidateVotesEarned() {
		return candidateVotesEarned;
	}
	public void setCandidateVotesEarned(Long candidateVotesEarned) {
		this.candidateVotesEarned = candidateVotesEarned;
	}
	public Long getCurrentRowNo() {
		return currentRowNo;
	}
	public void setCurrentRowNo(Long currentRowNo) {
		this.currentRowNo = currentRowNo;
	}
		
	public Long getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(Long commentsCount) {
		this.commentsCount = commentsCount;
	}
	public String getLocalBodyElectionsConstituencyName() {
		return localBodyElectionsConstituencyName;
	}
	public void setLocalBodyElectionsConstituencyName(
			String localBodyElectionsConstituencyName) {
		this.localBodyElectionsConstituencyName = localBodyElectionsConstituencyName;
	}
	public void setParliamentConstituencyId(Long parliamentConstituencyId) {
		this.parliamentConstituencyId = parliamentConstituencyId;
	}
	public Long getParliamentConstituencyId() {
		return parliamentConstituencyId;
	}
	public int compareTo(CandidateDetailsVO obj) {
		if(obj instanceof CandidateDetailsVO){
			CandidateDetailsVO vo = (CandidateDetailsVO) obj;
			return electionYear.compareToIgnoreCase(vo.getElectionYear());
		}
		else
			return 0;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getCandidateRole() {
		return candidateRole;
	}
	public void setCandidateRole(String candidateRole) {
		this.candidateRole = candidateRole;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}	
	
}
