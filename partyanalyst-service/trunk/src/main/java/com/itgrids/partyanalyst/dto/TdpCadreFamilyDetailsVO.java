package com.itgrids.partyanalyst.dto;


public class TdpCadreFamilyDetailsVO {
	
	private Long tdpCadreId;
	private String votercardNo;
	private String name;
	private Long age;
	private String mobileNo;
	private String gender;
	private Long educationId;
	private Long casteStateId;
	private Long occupationId;
	private Long bloodGroupId;
	private String dob;
	private String email;
	private String marriageDay;
	private String partyMemberSince;
	private String whatsappStatus;
	private Long relationId;
	private Long voterId;
	
	private AddressVO addressVo;
	private String occupation;
	private String education;
	private String relation;
	private Long count;
	private String relativeName;
	private String membershipNo;
	private String publicRepresentativeStr;
	private String partyPositionStr;
	
	private Long familySurveyCount=0L;
	private Long partyRepresentativesCount=0L;
	private Long partyPositionsCount=0L;
	private String imagePath;
	private String facebookUrl;
	
	private String deletedStatus;
	private String deletedReason;
	private String isRenewal;
	private Long enrollmntId;
	
	
	public String getIsRenewal() {
		return isRenewal;
	}
	public void setIsRenewal(String isRenewal) {
		this.isRenewal = isRenewal;
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
	public String getFacebookUrl() {
		return facebookUrl;
	}
	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Long getFamilySurveyCount() {
		return familySurveyCount;
	}
	public Long getPartyRepresentativesCount() {
		return partyRepresentativesCount;
	}
	public Long getPartyPositionsCount() {
		return partyPositionsCount;
	}
	public void setFamilySurveyCount(Long familySurveyCount) {
		this.familySurveyCount = familySurveyCount;
	}
	public void setPartyRepresentativesCount(Long partyRepresentativesCount) {
		this.partyRepresentativesCount = partyRepresentativesCount;
	}
	public void setPartyPositionsCount(Long partyPositionsCount) {
		this.partyPositionsCount = partyPositionsCount;
	}
	public String getPublicRepresentativeStr() {
		return publicRepresentativeStr;
	}
	public String getPartyPositionStr() {
		return partyPositionStr;
	}
	public void setPublicRepresentativeStr(String publicRepresentativeStr) {
		this.publicRepresentativeStr = publicRepresentativeStr;
	}
	public void setPartyPositionStr(String partyPositionStr) {
		this.partyPositionStr = partyPositionStr;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getRelationId() {
		return relationId;
	}
	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getVotercardNo() {
		return votercardNo;
	}
	public void setVotercardNo(String votercardNo) {
		this.votercardNo = votercardNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	public Long getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}
	public Long getBloodGroupId() {
		return bloodGroupId;
	}
	public void setBloodGroupId(Long bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMarriageDay() {
		return marriageDay;
	}
	public void setMarriageDay(String marriageDay) {
		this.marriageDay = marriageDay;
	}
	public String getPartyMemberSince() {
		return partyMemberSince;
	}
	public void setPartyMemberSince(String partyMemberSince) {
		this.partyMemberSince = partyMemberSince;
	}
	public String getWhatsappStatus() {
		return whatsappStatus;
	}
	public void setWhatsappStatus(String whatsappStatus) {
		this.whatsappStatus = whatsappStatus;
	}
	public AddressVO getAddressVo() {
		return addressVo;
	}
	public void setAddressVo(AddressVO addressVo) {
		this.addressVo = addressVo;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public Long getEnrollmntId() {
		return enrollmntId;
	}
	public void setEnrollmntId(Long enrollmntId) {
		this.enrollmntId = enrollmntId;
	}
	
	
	
	
	
	
	
	
	

}
