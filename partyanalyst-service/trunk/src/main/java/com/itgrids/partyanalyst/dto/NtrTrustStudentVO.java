package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class NtrTrustStudentVO implements Serializable{
	
	private Long id;
	private String name;
	
	private Long institutionId;
	private Long count;
	private Long totalCount;
	private Long courseId;
	private Long tdpCadreId;
	private Long membershipNo;
	private Long addressId;
	private Long pincodeLng;
	private Long designationId;
	
	private String institution;
	private String course;
	private String dateStr;
	private String yearOfJoining;
	private String casteStr;
	private String guardian;
	private String status;
	private String parentName;
	private String fatherName;
	private String motherName;
	private String relation;
	private String phoneNo;
	private String phoneType;

	private String startYear;
	private String startMonth;
	private String endYear;
	private String endMonth;
	
	private String stateStr;
	private String districtStr;
	private String constituencyStr;
	private String tehsilStr;
	private String localElectionBodyStr;
	private String panchayatStr;
	private String wardStr;
	private String locationStr;
	private String houseNoStr;
	private String streetStr;
	
	private String designation;
	private String cadreName;
	private Long cadreId;
	
	
	private List<NtrTrustStudentVO> cadreNtrTrustStudentVoList;
	private List<NtrTrustStudentVO> familyNtrTrustStudentVoList;
	
	private List<NtrTrustStudentVO> ntrTrustStudentVoList;
	private List<NtrTrustStudentVO> academicDetailsList;
	private List<NtrTrustStudentVO> addressDetailsList;
	private List<NtrTrustStudentVO> recomendationDetailsList;
	
	
	
	
	public List<NtrTrustStudentVO> getCadreNtrTrustStudentVoList() {
		return cadreNtrTrustStudentVoList;
	}
	public void setCadreNtrTrustStudentVoList(
			List<NtrTrustStudentVO> cadreNtrTrustStudentVoList) {
		this.cadreNtrTrustStudentVoList = cadreNtrTrustStudentVoList;
	}
	public List<NtrTrustStudentVO> getFamilyNtrTrustStudentVoList() {
		return familyNtrTrustStudentVoList;
	}
	public void setFamilyNtrTrustStudentVoList(
			List<NtrTrustStudentVO> familyNtrTrustStudentVoList) {
		this.familyNtrTrustStudentVoList = familyNtrTrustStudentVoList;
	}
	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	public List<NtrTrustStudentVO> getRecomendationDetailsList() {
		return recomendationDetailsList;
	}
	public void setRecomendationDetailsList(
			List<NtrTrustStudentVO> recomendationDetailsList) {
		this.recomendationDetailsList = recomendationDetailsList;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public List<NtrTrustStudentVO> getAddressDetailsList() {
		return addressDetailsList;
	}
	public void setAddressDetailsList(List<NtrTrustStudentVO> addressDetailsList) {
		this.addressDetailsList = addressDetailsList;
	}
	public String getStateStr() {
		return stateStr;
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	public String getDistrictStr() {
		return districtStr;
	}
	public void setDistrictStr(String districtStr) {
		this.districtStr = districtStr;
	}
	public String getConstituencyStr() {
		return constituencyStr;
	}
	public void setConstituencyStr(String constituencyStr) {
		this.constituencyStr = constituencyStr;
	}
	public String getTehsilStr() {
		return tehsilStr;
	}
	public void setTehsilStr(String tehsilStr) {
		this.tehsilStr = tehsilStr;
	}
	public String getLocalElectionBodyStr() {
		return localElectionBodyStr;
	}
	public void setLocalElectionBodyStr(String localElectionBodyStr) {
		this.localElectionBodyStr = localElectionBodyStr;
	}
	public String getPanchayatStr() {
		return panchayatStr;
	}
	public void setPanchayatStr(String panchayatStr) {
		this.panchayatStr = panchayatStr;
	}
	public String getWardStr() {
		return wardStr;
	}
	public void setWardStr(String wardStr) {
		this.wardStr = wardStr;
	}
	public String getLocationStr() {
		return locationStr;
	}
	public void setLocationStr(String locationStr) {
		this.locationStr = locationStr;
	}
	public String getHouseNoStr() {
		return houseNoStr;
	}
	public void setHouseNoStr(String houseNoStr) {
		this.houseNoStr = houseNoStr;
	}
	public String getStreetStr() {
		return streetStr;
	}
	public void setStreetStr(String streetStr) {
		this.streetStr = streetStr;
	}
	public Long getPincodeLng() {
		return pincodeLng;
	}
	public void setPincodeLng(Long pincodeLng) {
		this.pincodeLng = pincodeLng;
	}
	public List<NtrTrustStudentVO> getAcademicDetailsList() {
		return academicDetailsList;
	}
	public void setAcademicDetailsList(List<NtrTrustStudentVO> academicDetailsList) {
		this.academicDetailsList = academicDetailsList;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public List<NtrTrustStudentVO> getNtrTrustStudentVoList() {
		return ntrTrustStudentVoList;
	}
	public void setNtrTrustStudentVoList(
			List<NtrTrustStudentVO> ntrTrustStudentVoList) {
		this.ntrTrustStudentVoList = ntrTrustStudentVoList;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Long getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(Long membershipNo) {
		this.membershipNo = membershipNo;
	}
	public String getGuardian() {
		return guardian;
	}
	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(Long institutionId) {
		this.institutionId = institutionId;
	}
	public String getYearOfJoining() {
		return yearOfJoining;
	}
	public void setYearOfJoining(String yearOfJoining) {
		this.yearOfJoining = yearOfJoining;
	}
	public String getCasteStr() {
		return casteStr;
	}
	public void setCasteStr(String casteStr) {
		this.casteStr = casteStr;
	}
	
	
}
