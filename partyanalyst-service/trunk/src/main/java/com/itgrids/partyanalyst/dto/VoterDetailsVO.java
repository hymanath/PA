package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class VoterDetailsVO {
	private String voterName;
	private Long voterId;
	private String age;
	private String gender;
	private Long constituencyId;
	private Long boothId;
	private Long publicationDateId;
	private Long casteGroupId;
	private String caste;
	private Long hamletId;
	private Long wardId;
	private Long tehsilId;
	private Long localElectionBodyId;
	private Long districtId;
	private String contactNo;
	private String email;
	private Long partyId;
	private String influencePeoplePosition;
	private String relativeName;
	private String mobileNo;
	private String houseNo;
	private String wardName;
	private String image;
	//private Long mobileUserId;
	
	private Long casteStateId;
	private Long cadreLevelId;
	private String mobileNumber;
	private String uniqueId;
	private Long infleunceLevelId;
	private List<VoterDetailsVO> constyPublicationIds;
	private List<SelectOptionVO> publicationDatesList;
	private List<SelectOptionVO> boothList;
	
	private Long leaderBookNo;
	private String voterIDCardNo;
	private String dateOfBirth;
	private String partNo;
	private String districtName;
	private String constituencyName;
	private String tehsil;
	private String leb;
	private List<VoterDetailsVO> subList = new ArrayList<VoterDetailsVO>(0);
	private String alreadyRegistered;
	private String paymentStatus;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getAlreadyRegistered() {
		return alreadyRegistered;
	}
	public void setAlreadyRegistered(String alreadyRegistered) {
		this.alreadyRegistered = alreadyRegistered;
	}
	public List<VoterDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<VoterDetailsVO> subList) {
		this.subList = subList;
	}
	public String getTehsil() {
		return tehsil;
	}
	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}
	public String getLeb() {
		return leb;
	}
	public void setLeb(String leb) {
		this.leb = leb;
	}
	public String getConstituencyName() {
		return constituencyName;
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
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getVoterIDCardNo() {
		return voterIDCardNo;
	}
	public void setVoterIDCardNo(String voterIDCardNo) {
		this.voterIDCardNo = voterIDCardNo;
	}
	public Long getLeaderBookNo() {
		return leaderBookNo;
	}
	public void setLeaderBookNo(Long leaderBookNo) {
		this.leaderBookNo = leaderBookNo;
	}
	public List<SelectOptionVO> getPublicationDatesList() {
		return publicationDatesList;
	}
	public void setPublicationDatesList(List<SelectOptionVO> publicationDatesList) {
		this.publicationDatesList = publicationDatesList;
	}

	public List<SelectOptionVO> getBoothList() {
		return boothList;
	}
	public void setBoothList(List<SelectOptionVO> boothList) {
		this.boothList = boothList;
	}
	public List<VoterDetailsVO> getConstyPublicationIds() {
		return constyPublicationIds;
	}
	public void setConstyPublicationIds(List<VoterDetailsVO> constyPublicationIds) {
		this.constyPublicationIds = constyPublicationIds;
	}

	public Long getInfleunceLevelId() {
		return infleunceLevelId;
	}
	public void setInfleunceLevelId(Long infleunceLevelId) {
		this.infleunceLevelId = infleunceLevelId;
	}
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	public Long getCadreLevelId() {
		return cadreLevelId;
	}
	public void setCadreLevelId(Long cadreLevelId) {
		this.cadreLevelId = cadreLevelId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	/*public Long getMobileUserId() {
		return mobileUserId;
	}
	public void setMobileUserId(Long mobileUserId) {
		this.mobileUserId = mobileUserId;
	}*/
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getInfluencePeoplePosition() {
		return influencePeoplePosition;
	}
	public void setInfluencePeoplePosition(String influencePeoplePosition) {
		this.influencePeoplePosition = influencePeoplePosition;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	public Long getCasteGroupId() {
		return casteGroupId;
	}
	public void setCasteGroupId(Long casteGroupId) {
		this.casteGroupId = casteGroupId;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public Long getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	
	
}
