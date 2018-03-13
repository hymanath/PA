package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class TdpCadreVO implements java.io.Serializable
{
	private Long id;
	private String name;
	private String cadreName;
	private String relativeName;
	private String memberShipNo;
	private String trNo;
	private String gender;
	private Long age;
	private String voterCardNo;
	private Long voterId;
	private String mobileNo;
	private String houseNo;
	private String imageURL;
	private String errorStr;
	private String occupation;
	private Long totalCount;
	private String state;
	private String stateId;
	private Long constituencyId;
	private String constituency;
	private Long constituencyNo;
	private Long tehsilId;
	private String tehsil;
	private Long localElectionBodyId;
	private String localElectionBody;
	private Long panchayatId;
	private String panchayat;
	private Long districtId;
	private String district;
	private String casteName;
	private String responseStatus;
	private String responseCode;
	private String aadharNo;
	private String dataSourceType;
	private String deletedStatus;
	private String deleteReason;
	private String importantLeaderType;
	private String importantLeaderLevel;
	private String importantLeaderLocation;
	private Long importantLeaderCadreId;
	private String fromYear;
	private String toYear;
	private String dateOfBirth;
	private String designation;
	private String wished = "false";
	private String relativeType;
	private String parliament;
	private String parliamentId;
	private List<TdpCadreVO> tdpCadreDetailsList = new ArrayList<TdpCadreVO>();
	private List<TdpCadreVO> cadreSearchList = new ArrayList<TdpCadreVO>();
	private List<TdpCadreVO> voterSearchList = new ArrayList<TdpCadreVO>();
	private List<CadreCommitteeVO> cadreComitteeVOList = new ArrayList<CadreCommitteeVO>();

	private Long webCount;
	private Long tabCount;
	private String alreadyRegistered;
	private String paymentStatus;
	
	private Long onlineCount;
	private Long familyVoterId;
	private String familyVoterCardNo;
	private Long enrollmentYearId;
	private String status;
	private String totalImagePathStr;
	private String isCsd;
	private String year;
	private String enrollmentYears;
	private String enrollmentYearIdStr;
	private List<String> enrollmentYearList = new ArrayList<String>();
	private String isDeleted;
	
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public Long getConstituencyNo() {
		return constituencyNo;
	}
	public void setConstituencyNo(Long constituencyNo) {
		this.constituencyNo = constituencyNo;
	}
	public String getParliament() {
		return parliament;
	}
	public void setParliament(String parliament) {
		this.parliament = parliament;
	}
	public String getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(String parliamentId) {
		this.parliamentId = parliamentId;
	}
	public List<String> getEnrollmentYearList() {
		return enrollmentYearList;
	}
	public void setEnrollmentYearList(List<String> enrollmentYearList) {
		this.enrollmentYearList = enrollmentYearList;
	}
	public String getEnrollmentYearIdStr() {
		return enrollmentYearIdStr;
	}
	public void setEnrollmentYearIdStr(String enrollmentYearIdStr) {
		this.enrollmentYearIdStr = enrollmentYearIdStr;
	}
	public String getEnrollmentYears() {
		return enrollmentYears;
	}
	public void setEnrollmentYears(String enrollmentYears) {
		this.enrollmentYears = enrollmentYears;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getIsCsd() {
		return isCsd;
	}
	public void setIsCsd(String isCsd) {
		this.isCsd = isCsd;
	}
	public String getTotalImagePathStr() {
		return totalImagePathStr;
	}
	public void setTotalImagePathStr(String totalImagePathStr) {
		this.totalImagePathStr = totalImagePathStr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getEnrollmentYearId() {
		return enrollmentYearId;
	}
	public void setEnrollmentYearId(Long enrollmentYearId) {
		this.enrollmentYearId = enrollmentYearId;
	}
	public Long getFamilyVoterId() {
		return familyVoterId;
	}
	public void setFamilyVoterId(Long familyVoterId) {
		this.familyVoterId = familyVoterId;
	}
	public String getFamilyVoterCardNo() {
		return familyVoterCardNo;
	}
	public void setFamilyVoterCardNo(String familyVoterCardNo) {
		this.familyVoterCardNo = familyVoterCardNo;
	}
	public String getRelativeType() {
		return relativeType;
	}
	public void setRelativeType(String relativeType) {
		this.relativeType = relativeType;
	}
	public String getAlreadyRegistered() {
		return alreadyRegistered;
	}
	public void setAlreadyRegistered(String alreadyRegistered) {
		this.alreadyRegistered = alreadyRegistered;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getWebCount() {
		return webCount;
	}
	public void setWebCount(Long webCount) {
		this.webCount = webCount;
	}
	public Long getTabCount() {
		return tabCount;
	}
	public void setTabCount(Long tabCount) {
		this.tabCount = tabCount;
	}
	public String getDeleteReason() {
		return deleteReason;
	}
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	public String getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(String deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	public List<CadreCommitteeVO> getCadreComitteeVOList() {
		return cadreComitteeVOList;
	}
	public void setCadreComitteeVOList(List<CadreCommitteeVO> cadreComitteeVOList) {
		this.cadreComitteeVOList = cadreComitteeVOList;
	}
	public List<TdpCadreVO> getCadreSearchList() {
		return cadreSearchList;
	}
	public List<TdpCadreVO> getVoterSearchList() {
		return voterSearchList;
	}
	public void setCadreSearchList(List<TdpCadreVO> cadreSearchList) {
		this.cadreSearchList = cadreSearchList;
	}
	public void setVoterSearchList(List<TdpCadreVO> voterSearchList) {
		this.voterSearchList = voterSearchList;
	}
	public String getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	public String getLocalElectionBody() {
		return localElectionBody;
	}
	public void setLocalElectionBody(String localElectionBody) {
		this.localElectionBody = localElectionBody;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getTehsil() {
		return tehsil;
	}
	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}
	public String getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getErrorStr() {
		return errorStr;
	}
	public void setErrorStr(String errorStr) {
		this.errorStr = errorStr;
	}
	public List<TdpCadreVO> getTdpCadreDetailsList() {
		return tdpCadreDetailsList;
	}
	public void setTdpCadreDetailsList(List<TdpCadreVO> tdpCadreDetailsList) {
		this.tdpCadreDetailsList = tdpCadreDetailsList;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public String getTrNo() {
		return trNo;
	}
	public void setTrNo(String trNo) {
		this.trNo = trNo;
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
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public Long getOnlineCount() {
		return onlineCount;
	}
	public void setOnlineCount(Long onlineCount) {
		this.onlineCount = onlineCount;
	}
	public String getImportantLeaderType() {
		return importantLeaderType;
	}
	public void setImportantLeaderType(String importantLeaderType) {
		this.importantLeaderType = importantLeaderType;
	}
	public String getImportantLeaderLevel() {
		return importantLeaderLevel;
	}
	public void setImportantLeaderLevel(String importantLeaderLevel) {
		this.importantLeaderLevel = importantLeaderLevel;
	}
	public String getImportantLeaderLocation() {
		return importantLeaderLocation;
	}
	public void setImportantLeaderLocation(String importantLeaderLocation) {
		this.importantLeaderLocation = importantLeaderLocation;
	}
	public Long getImportantLeaderCadreId() {
		return importantLeaderCadreId;
	}
	public void setImportantLeaderCadreId(Long importantLeaderCadreId) {
		this.importantLeaderCadreId = importantLeaderCadreId;
	}
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String string) {
		this.dateOfBirth = string;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getWished() {
		return wished;
	}
	public void setWished(String wished) {
		this.wished = wished;
	}
	
	
}
