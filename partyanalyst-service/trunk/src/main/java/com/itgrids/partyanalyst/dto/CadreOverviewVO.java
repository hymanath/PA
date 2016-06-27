package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CadreOverviewVO implements java.io.Serializable{

	private String age;
	private String areaType;
	private Long boothId;
	private Long candidateId;
	private Long cadreId;
	private String casteName;
	private String dob;
	private String email;
	private String enrollmentYears;
	private String houseNo;
	private String imagePath;
	private Long membershipNo;
	private String mobileNo;
	private String candidateName;
	private String occupation;
	private String partyPosition;
	private String qualification;
	private String registeredAt;
	private String registeredTime;
	private String representativeType;
	private Long voterId;
	private String voterCardNo;
	private String relativeName;
	private String relationType;
	private String gender;
	
	private Long partNo;
	private String panchayatName;
	private Long panchayatId;
	private Long tehsilId;
	private String tehsilName;
	private String localElectionBodyId;
	private String localElectionBodyName;
	private String wardId;
	private String wardName;
	private Long constituencyId;
	private String constituencyName;
	private Long parliamentId;
	private String parlimentName;
	private Long districtId;
	private String districtName;
	private Long stateId;
	private String stateName;
	private Long meetingInvitationCount=0L;
	private Long meetingAttendenceCount=0L;
	private Long meetingAbsentCount=0L;
	private Long eventInvitationCount=0L;
	private Long eventAttendedCount=0L;
	private Long eventAbsentCount=0L;
	private Long trainingInvititationCount=0L;
	private Long trainingAttendedCount=0L;
	private Long trainingAbsentCount=0L;
	private Long familyMembersSurveyCount = 0L;
	private Long publicRepresentativesCount = 0L;
	private Long partyPositionsCount =0L;
	private String deletedStatus;
	private String deletedReason;
	
	private  List<TdpCadreFamilyDetailsVO> familyMembersList = new ArrayList<TdpCadreFamilyDetailsVO>(0);
	private List<RegisteredMembershipCountVO> electionResultsPerfList = new ArrayList<RegisteredMembershipCountVO>(0);
	private List<RegisteredMembershipCountVO> membershipRegistrationPerfList = new ArrayList<RegisteredMembershipCountVO>(0);
	private String message;
	private String familyVoterCardNum;
	private boolean voterExists;
	private String	familyVoterId;
	private String memberType;
	private Long memberTypeId;
	private String boothLocation;
	
	
	
	public String getBoothLocation() {
		return boothLocation;
	}
	public void setBoothLocation(String boothLocation) {
		this.boothLocation = boothLocation;
	}
	public Long getMemberTypeId() {
		return memberTypeId;
	}
	public void setMemberTypeId(Long memberTypeId) {
		this.memberTypeId = memberTypeId;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getFamilyVoterId() {
		return familyVoterId;
	}
	public void setFamilyVoterId(String familyVoterId) {
		this.familyVoterId = familyVoterId;
	}
	public boolean isVoterExists() {
		return voterExists;
	}
	public void setVoterExists(boolean voterExists) {
		this.voterExists = voterExists;
	}
	public String getFamilyVoterCardNum() {
		return familyVoterCardNum;
	}
	public void setFamilyVoterCardNum(String familyVoterCardNum) {
		this.familyVoterCardNum = familyVoterCardNum;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(String deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	public String getDeletedReason() {
		return deletedReason;
	}
	public void setDeletedReason(String deletedReason) {
		this.deletedReason = deletedReason;
	}
	public Long getFamilyMembersSurveyCount() {
		return familyMembersSurveyCount;
	}
	public Long getPublicRepresentativesCount() {
		return publicRepresentativesCount;
	}
	public Long getPartyPositionsCount() {
		return partyPositionsCount;
	}
	public void setFamilyMembersSurveyCount(Long familyMembersSurveyCount) {
		this.familyMembersSurveyCount = familyMembersSurveyCount;
	}
	public void setPublicRepresentativesCount(Long publicRepresentativesCount) {
		this.publicRepresentativesCount = publicRepresentativesCount;
	}
	public void setPartyPositionsCount(Long partyPositionsCount) {
		this.partyPositionsCount = partyPositionsCount;
	}
	public String getAge() {
		return age;
	}
	public String getAreaType() {
		return areaType;
	}
	public Long getBoothId() {
		return boothId;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public Long getCadreId() {
		return cadreId;
	}
	public String getCasteName() {
		return casteName;
	}
	public String getDob() {
		return dob;
	}
	public String getEmail() {
		return email;
	}
	public String getEnrollmentYears() {
		return enrollmentYears;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public String getImagePath() {
		return imagePath;
	}
	public Long getMembershipNo() {
		return membershipNo;
	}
	
	public String getCandidateName() {
		return candidateName;
	}
	public String getOccupation() {
		return occupation;
	}
	public String getPartyPosition() {
		return partyPosition;
	}
	public String getQualification() {
		return qualification;
	}
	public String getRegisteredAt() {
		return registeredAt;
	}
	public String getRegisteredTime() {
		return registeredTime;
	}
	public String getRepresentativeType() {
		return representativeType;
	}
	public Long getVoterId() {
		return voterId;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public Long getPartNo() {
		return partNo;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public String getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public Long getParliamentId() {
		return parliamentId;
	}
	public String getParlimentName() {
		return parlimentName;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public Long getStateId() {
		return stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEnrollmentYears(String enrollmentYears) {
		this.enrollmentYears = enrollmentYears;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public void setMembershipNo(Long membershipNo) {
		this.membershipNo = membershipNo;
	}
	
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public void setPartyPosition(String partyPosition) {
		this.partyPosition = partyPosition;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public void setRegisteredAt(String registeredAt) {
		this.registeredAt = registeredAt;
	}
	public void setRegisteredTime(String registeredTime) {
		this.registeredTime = registeredTime;
	}
	public void setRepresentativeType(String representativeType) {
		this.representativeType = representativeType;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public void setPartNo(Long partNo) {
		this.partNo = partNo;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public void setLocalElectionBodyId(String localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	public void setParlimentName(String parlimentName) {
		this.parlimentName = parlimentName;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public List<TdpCadreFamilyDetailsVO> getFamilyMembersList() {
		return familyMembersList;
	}
	public void setFamilyMembersList(List<TdpCadreFamilyDetailsVO> familyMembersList) {
		this.familyMembersList = familyMembersList;
	}
	public List<RegisteredMembershipCountVO> getElectionResultsPerfList() {
		return electionResultsPerfList;
	}
	public List<RegisteredMembershipCountVO> getMembershipRegistrationPerfList() {
		return membershipRegistrationPerfList;
	}
	public void setElectionResultsPerfList(
			List<RegisteredMembershipCountVO> electionResultsPerfList) {
		this.electionResultsPerfList = electionResultsPerfList;
	}
	public void setMembershipRegistrationPerfList(
			List<RegisteredMembershipCountVO> membershipRegistrationPerfList) {
		this.membershipRegistrationPerfList = membershipRegistrationPerfList;
	}

	public Long getMeetingInvitationCount() {
		return meetingInvitationCount;
	}
	public Long getMeetingAttendenceCount() {
		return meetingAttendenceCount;
	}
	public Long getMeetingAbsentCount() {
		return meetingAbsentCount;
	}
	public Long getEventInvitationCount() {
		return eventInvitationCount;
	}
	public Long getEventAttendedCount() {
		return eventAttendedCount;
	}
	public Long getEventAbsentCount() {
		return eventAbsentCount;
	}
	public Long getTrainingInvititationCount() {
		return trainingInvititationCount;
	}
	public Long getTrainingAttendedCount() {
		return trainingAttendedCount;
	}
	public Long getTrainingAbsentCount() {
		return trainingAbsentCount;
	}
	public void setMeetingInvitationCount(Long meetingInvitationCount) {
		this.meetingInvitationCount = meetingInvitationCount;
	}
	public void setMeetingAttendenceCount(Long meetingAttendenceCount) {
		this.meetingAttendenceCount = meetingAttendenceCount;
	}
	public void setMeetingAbsentCount(Long meetingAbsentCount) {
		this.meetingAbsentCount = meetingAbsentCount;
	}
	public void setEventInvitationCount(Long eventInvitationCount) {
		this.eventInvitationCount = eventInvitationCount;
	}
	public void setEventAttendedCount(Long eventAttendedCount) {
		this.eventAttendedCount = eventAttendedCount;
	}
	public void setEventAbsentCount(Long eventAbsentCount) {
		this.eventAbsentCount = eventAbsentCount;
	}
	public void setTrainingInvititationCount(Long trainingInvititationCount) {
		this.trainingInvititationCount = trainingInvititationCount;
	}
	public void setTrainingAttendedCount(Long trainingAttendedCount) {
		this.trainingAttendedCount = trainingAttendedCount;
	}
	public void setTrainingAbsentCount(Long trainingAbsentCount) {
		this.trainingAbsentCount = trainingAbsentCount;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getLocalElectionBodyName() {
		return localElectionBodyName;
	}
	public void setLocalElectionBodyName(String localElectionBodyName) {
		this.localElectionBodyName = localElectionBodyName;
	}
	public String getWardId() {
		return wardId;
	}
	public void setWardId(String wardId) {
		this.wardId = wardId;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

}
