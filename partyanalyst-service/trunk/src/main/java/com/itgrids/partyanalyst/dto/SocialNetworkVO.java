package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class SocialNetworkVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String cname;
	private String url;
	private String curl;
	private String category;
	private String profileId;
	private String accountType;
	private Long casteId;
	private String casteName;
	private Long districtId;
	

	/* NominationDAO for candidate Info */ 
	
	private Long constId;
	private Long candId;
	private String firstName;
	private String lastName;
	private String middleName;
	private Long partyId;
	private String shortName;
	private String deformDate;
	private String flag;
	private String election;
	private String reservationzone;
	
	/*CasteCategoryDAO for category Info*/

	private String casteCategoryName;
	private String casteCategoryGroupName;
	private String casteNames;
	private Long casteCategoryId;
	private Long casteCategoryGroupId;
	private List<SelectOptionVO> phoneCategoryList;
	private List<SelectOptionVO> phoneTypeList;
	//for addressVO properties of phone
	private Long phoneType;
	private Long phoneCategory;
	private Long phoneNumber;

	private Long phoneNumberId;
	private Long candidatePhoneId;
	private String addressName;
	
	
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public Long getCandidatePhoneId() {
		return candidatePhoneId;
	}
	public void setCandidatePhoneId(Long candidatePhoneId) {
		this.candidatePhoneId = candidatePhoneId;
	}
	public Long getPhoneNumberId() {
		return phoneNumberId;
	}
	public void setPhoneNumberId(Long phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}
	public List<SelectOptionVO> getPhoneCategoryList() {
		return phoneCategoryList;
	}
	public void setPhoneCategoryList(List<SelectOptionVO> phoneCategoryList) {
		this.phoneCategoryList = phoneCategoryList;
	}
	public List<SelectOptionVO> getPhoneTypeList() {
		return phoneTypeList;
	}
	public void setPhoneTypeList(List<SelectOptionVO> phoneTypeList) {
		this.phoneTypeList = phoneTypeList;
	}
	public Long getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(Long phoneType) {
		this.phoneType = phoneType;
	}
	public Long getPhoneCategory() {
		return phoneCategory;
	}
	public void setPhoneCategory(Long phoneCategory) {
		this.phoneCategory = phoneCategory;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
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
	public Long getCasteCategoryGroupId() {
		return casteCategoryGroupId;
	}
	public void setCasteCategoryGroupId(Long casteCategoryGroupId) {
		this.casteCategoryGroupId = casteCategoryGroupId;
	}
	public Long getCasteCategoryId() {
		return casteCategoryId;
	}
	public void setCasteCategoryId(Long casteCategoryId) {
		this.casteCategoryId = casteCategoryId;
	}
	public String getCasteNames() {
		return casteNames;
	}
	public void setCasteNames(String casteNames) {
		this.casteNames = casteNames;
	}

	public String getCasteCategoryGroupName() {
		return casteCategoryGroupName;
	}
	public void setCasteCategoryGroupName(String casteCategoryGroupName) {
		this.casteCategoryGroupName = casteCategoryGroupName;
	}
	public String getCasteCategoryName() {
		return casteCategoryName;
	}
	public void setCasteCategoryName(String casteCategoryName) {
		this.casteCategoryName = casteCategoryName;
	}
	public Long getConstId() {
		return constId;
	}
	public void setConstId(Long constId) {
		this.constId = constId;
	}
	
	public Long getCandId() {
		return candId;
	}
	public void setCandId(Long candId) {
		this.candId = candId;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	

	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getDeformDate() {
		return deformDate;
	}
	public void setDeformDate(String deformDate) {
		this.deformDate = deformDate;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getElection() {
		return election;
	}
	public void setElection(String election) {
		this.election = election;
	}
	public String getReservationzone() {
		return reservationzone;
	}
	public void setReservationzone(String reservationzone) {
		this.reservationzone = reservationzone;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCurl() {
		return curl;
	}
	public void setCurl(String curl) {
		this.curl = curl;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	
}
