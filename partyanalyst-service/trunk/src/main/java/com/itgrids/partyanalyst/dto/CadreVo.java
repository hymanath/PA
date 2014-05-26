package com.itgrids.partyanalyst.dto;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadreVo implements Serializable{
	
	private Long userId;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String gender;
	private Long age;
	private String bloodGroup;
	private String noOfFamilyMembers;
	private String fatherName;
	private Long bloodGroupId;
	private String noOfVoters;
	private String landNo;
	
	private String emailId;
	private String hno;
	private String street;
	private Long mandalId;
	private Long districtId;
	private Long constituencyId;
	private Long boothNo;
	private String pinCode;
	private Long villageId;
	private Long casteCategory;
	private String memberType;
	private Long professionId;
	private Long educationId;
	private String sourceIncome;
	private String annualIncome;
	private Long education;
	private String image;
	private List<CadreVo> cadreVOList = new ArrayList<CadreVo>();
	
	private String mandalName;
	private String districtName;
	private String constituencyName;
	private String villageName;
	private String localelectionName;
	private Long count;	
	private String activeDateField;
	private List<Long> partyDesignationList,govtDesignationList;
	private String address;
	private Long cadreId;
	private File uploadImage;
	private String uploadImageContentType;
	private String uploadImageFileName;
	private String path;
	private String bloodGroupStr;
	
	private String booth;
	private String boothName;
	private String educationStr;
	private String professionStr;
	private String casteCategoryName;
	private String voterCardId;
	private Long isVerified;
	private List<SelectOptionVO> districts,constituencies,booths;
	private String base64Image;
	

	
	public List<SelectOptionVO> getDistricts() {
		return districts;
	}

	public void setDistricts(List<SelectOptionVO> districts) {
		this.districts = districts;
	}

	public List<SelectOptionVO> getConstituencies() {
		return constituencies;
	}

	public void setConstituencies(List<SelectOptionVO> constituencies) {
		this.constituencies = constituencies;
	}

	public List<SelectOptionVO> getBooths() {
		return booths;
	}

	public void setBooths(List<SelectOptionVO> booths) {
		this.booths = booths;
	}

	public Long getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Long isVerified) {
		this.isVerified = isVerified;
	}

	public String getVoterCardId() {
		return voterCardId;
	}

	public void setVoterCardId(String voterCardId) {
		this.voterCardId = voterCardId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadImageContentType() {
		return uploadImageContentType;
	}

	public void setUploadImageContentType(String uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}

	public String getUploadImageFileName() {
		return uploadImageFileName;
	}

	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}

	public String getCasteCategoryName() {
		return casteCategoryName;
	}

	public void setCasteCategoryName(String casteCategoryName) {
		this.casteCategoryName = casteCategoryName;
	}

	public String getEducationStr() {
		return educationStr;
	}

	public void setEducationStr(String educationStr) {
		this.educationStr = educationStr;
	}

	public String getProfessionStr() {
		return professionStr;
	}

	public void setProfessionStr(String professionStr) {
		this.professionStr = professionStr;
	}

	public String getBooth() {
		return booth;
	}

	public void setBooth(String booth) {
		this.booth = booth;
	}

	public String getBoothName() {
		return boothName;
	}

	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}


	public String getBloodGroupStr() {
		return bloodGroupStr;
	}

	public void setBloodGroupStr(String bloodGroupStr) {
		this.bloodGroupStr = bloodGroupStr;
	}

	public Long getCadreId() {
		return cadreId;
	}

	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	

	public String getNoOfFamilyMembers() {
		return noOfFamilyMembers;
	}

	public void setNoOfFamilyMembers(String noOfFamilyMembers) {
		this.noOfFamilyMembers = noOfFamilyMembers;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	
	public String getNoOfVoters() {
		return noOfVoters;
	}

	public void setNoOfVoters(String noOfVoters) {
		this.noOfVoters = noOfVoters;
	}

	public String getLandNo() {
		return landNo;
	}

	public void setLandNo(String landNo) {
		this.landNo = landNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getHno() {
		return hno;
	}

	public void setHno(String hno) {
		this.hno = hno;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getMandalId() {
		return mandalId;
	}

	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getBoothNo() {
		return boothNo;
	}

	public void setBoothNo(Long boothNo) {
		this.boothNo = boothNo;
	}


	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Long getVillageId() {
		return villageId;
	}

	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}

	public Long getCasteCategory() {
		return casteCategory;
	}

	public void setCasteCategory(Long casteCategory) {
		this.casteCategory = casteCategory;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public Long getProfessionId() {
		return professionId;
	}

	public void setProfessionId(Long professionId) {
		this.professionId = professionId;
	}

	public Long getEducationId() {
		return educationId;
	}

	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}

	

	

	public String getSourceIncome() {
		return sourceIncome;
	}

	public void setSourceIncome(String sourceIncome) {
		this.sourceIncome = sourceIncome;
	}

	public String getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	public Long getEducation() {
		return education;
	}

	public void setEducation(Long education) {
		this.education = education;
	}

	public List<Long> getPartyDesignationList() {
		return partyDesignationList;
	}

	public void setPartyDesignationList(List<Long> partyDesignationList) {
		this.partyDesignationList = partyDesignationList;
	}

	public List<Long> getGovtDesignationList() {
		return govtDesignationList;
	}

	public void setGovtDesignationList(List<Long> govtDesignationList) {
		this.govtDesignationList = govtDesignationList;
	}

	public Long getBloodGroupId() {
		return bloodGroupId;
	}

	public void setBloodGroupId(Long bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}

	public String getActiveDateField() {
		return activeDateField;
	}

	public void setActiveDateField(String activeDateField) {
		this.activeDateField = activeDateField;
	}

	public List<CadreVo> getCadreVOList() {
		return cadreVOList;
	}

	public void setCadreVOList(List<CadreVo> cadreVOList) {
		this.cadreVOList = cadreVOList;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getLocalelectionName() {
		return localelectionName;
	}

	public void setLocalelectionName(String localelectionName) {
		this.localelectionName = localelectionName;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
	
}
