package com.itgrids.voterdata.service.sqlite;

import java.io.File;
import java.util.Date;

public class CadreRegistrationVO {
	
	//personal details
	private Long             cadreId;
	private Long 			 tdpCadreId;
	private String           cadreName;
	private Long 			 voterId;
	private String 			 houseNo;
	private String			 gender;
	private Long 			 age;
	private String		 	 dateOfBirth;
	private String 			 mobileNumber;
	private Long 			 bloodGroupId;
	private Long             casteStateId;
	private Long 			 educationId;
	private Long             occupationId;
	private String 			 aadharNo;
	private String           imagePath;//new
	private String			 photoType;
	
	private String           isDeletedVoter;  //funct
	
	//users and time related
	private Date             insertedTime;
	private Date             updatedTime;
	private String           dataSourceType;
	private Long             webUserId;//insertedwebuserid updatedwebuserid web related.
	private String           syncType;
	private Long             userId;//created by updatedby tab related
	private Long             tabUserId;//tabuser_info_id
	private String  		 longititude;
	private String 			 latitude;
	private String 			 surveyTime;
	private String           uniqueId;
	
	//location details
	private Long             boothId;
	private Long             panchayatId;
	private Long             tehsilId;
	private Long             wardId;
	private Long             localElectionBodyId;
	private Long             constituencyId;
	private Long             districtId;
	private Long             stateId;
	
	//relative details
	private String 			relativeName;
	private String          relativeType;

	//family voter details
	private String          isFamilyVoter;
	private Long			familyVoterId;
	private Long 		    familyRelationTypeId;
	
	//nominee details
	private String          nomineeName;
	private String          nomineeGender;
	private Long            nomineeAge;
	private Long            nomineeRelationId;
	private String          nomineeAadharNo;
	
	private String 	        memberShipNo;
	private String          imageBase64String;
	
	
	private String          imageSaveStatus;



	private String          imageIssue;
	private boolean 		isUpdate;
	private File            uploadImage;
	private String          refNo;
	private Long            tabUserInfoId;
	private String 			imei;
	private String			deviceName;
	private String 			searchType;
	private String 			appVersion;

	public String getSimSerialNo() {
		return simSerialNo;
	}

	public void setSimSerialNo(String simSerialNo) {
		this.simSerialNo = simSerialNo;
	}

	private String          simSerialNo;
	private String isNewVoter;

	public String getIsNewVoter()
	{
		return isNewVoter;
	}

	public void setIsNewVoter(String isNewVoter)
	{
		this.isNewVoter = isNewVoter;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}



	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Long getTabPrimaryKey() {
		return tabPrimaryKey;
	}

	public void setTabPrimaryKey(Long tabPrimaryKey) {
		this.tabPrimaryKey = tabPrimaryKey;
	}

	private Long 			tabPrimaryKey;
	
	public Long getTabUserInfoId() {
		return tabUserInfoId;
	}

	public void setTabUserInfoId(Long tabUserInfoId) {
		this.tabUserInfoId = tabUserInfoId;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getImageIssue() {
		return imageIssue;
	}

	public void setImageIssue(String imageIssue) {
		this.imageIssue = imageIssue;
	}

	public String getImageSaveStatus() {
		return imageSaveStatus;
	}

	public void setImageSaveStatus(String imageSaveStatus) {
		this.imageSaveStatus = imageSaveStatus;
	}

	public String getImageBase64String() {
		return imageBase64String;
	}

	public void setImageBase64String(String imageBase64String) {
		this.imageBase64String = imageBase64String;
	}

	public String getMemberShipNo() {
		return memberShipNo;
	}
	
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	
	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
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
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Long getBloodGroupId() {
		return bloodGroupId;
	}
	public void setBloodGroupId(Long bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	
	public String getSyncType() {
		return syncType;
	}
	public void setSyncType(String syncType) {
		this.syncType = syncType;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getIsDeletedVoter() {
		return isDeletedVoter;
	}
	public void setIsDeletedVoter(String isDeletedVoter) {
		this.isDeletedVoter = isDeletedVoter;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTabUserId() {
		return tabUserId;
	}
	public void setTabUserId(Long tabUserId) {
		this.tabUserId = tabUserId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
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
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public Long getFamilyVoterId() {
		return familyVoterId;
	}
	public void setFamilyVoterId(Long familyVoterId) {
		this.familyVoterId = familyVoterId;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	
	public Long getFamilyRelationTypeId() {
		return familyRelationTypeId;
	}
	public void setFamilyRelationTypeId(Long familyRelationTypeId) {
		this.familyRelationTypeId = familyRelationTypeId;
	}
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public String getNomineeGender() {
		return nomineeGender;
	}
	public void setNomineeGender(String nomineeGender) {
		this.nomineeGender = nomineeGender;
	}
	public Long getNomineeAge() {
		return nomineeAge;
	}
	public void setNomineeAge(Long nomineeAge) {
		this.nomineeAge = nomineeAge;
	}
	public Long getNomineeRelationId() {
		return nomineeRelationId;
	}
	public void setNomineeRelationId(Long nomineeRelationId) {
		this.nomineeRelationId = nomineeRelationId;
	}
	public String getNomineeAadharNo() {
		return nomineeAadharNo;
	}
	public void setNomineeAadharNo(String nomineeAadharNo) {
		this.nomineeAadharNo = nomineeAadharNo;
	}
	public String getLongititude() {
		return longititude;
	}
	public void setLongititude(String longititude) {
		this.longititude = longititude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getSurveyTime() {
		return surveyTime;
	}
	public void setSurveyTime(String surveyTime) {
		this.surveyTime = surveyTime;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public Long getWebUserId() {
		return webUserId;
	}
	public void setWebUserId(Long webUserId) {
		this.webUserId = webUserId;
	}
	public String getRelativeType() {
		return relativeType;
	}
	public void setRelativeType(String relativeType) {
		this.relativeType = relativeType;
	}
	public String getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	public String getIsFamilyVoter() {
		return isFamilyVoter;
	}
	public void setIsFamilyVoter(String isFamilyVoter) {
		this.isFamilyVoter = isFamilyVoter;
	}
	public Long getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
}
