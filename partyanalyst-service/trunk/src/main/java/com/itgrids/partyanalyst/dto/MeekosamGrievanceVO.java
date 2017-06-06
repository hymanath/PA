/**
 * @author SRAVANTH
 * MAY 31, 2017
 * MeekosamGrievanceVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SRAVANTH
 * @date MAY 31, 2017
 */
public class MeekosamGrievanceVO {

	private Long id;
	private String name;
	
	private Long meekosamPetitionerId;
	private String petitionerName;
	private String petitionerRelativeName;
	private String petitionerGender;
	private String petitionerDOB;
	private Long petitionerAge;
	private String petitionerMobileNo;
	private String petitionerVoterCardNo;
	private String petitionerAadharCardNo;
	private String petitionerEmailId;
	private Long petitionerDistrictId;
	private Long petitionerTehsilId;
	private Long petitionerPanchayatId;
	private Long petitionerHamletId;
	private String petitionerHouseNO;
	private Long petitionerCaste;
	private Long petitionerOccupation;
	private Long petitionerAnnulaIncome;
	private Long petitionerArgeeCategory;
	
	private Long categoryId;
	private Long departmentId;
	private Long subDeptId;
	private Long issueTypeId;
	
	private String grievanceTitle;
	private String grievanceDesc;
	private Long grievanceImpactLevel;
	private Long grievanceLevelValue;
	private Long grievanceDistrictId;
	private Long grievanceTehsilId;
	private Long grievancePanchayatId;
	
	private Long referalTypeId;
	private Long referalDistrictId;
	private Long referalNameId;
	private List<Long> referCadreIds;
	
	private Long assignLevelId;
	private Long assignLevelValue;
	private Long designationId;
	private Long officerId;
	
	//ForRevenueDeprtment -- LanDetails
	private List<MeekosamLandDetailsVO> landDetailsList = new ArrayList<MeekosamLandDetailsVO>();
	private List<MeekosamDynamicVO> dynamicDataList = new ArrayList<MeekosamDynamicVO>();
	
	
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
	public String getPetitionerName() {
		return petitionerName;
	}
	public void setPetitionerName(String petitionerName) {
		this.petitionerName = petitionerName;
	}
	public String getPetitionerRelativeName() {
		return petitionerRelativeName;
	}
	public void setPetitionerRelativeName(String petitionerRelativeName) {
		this.petitionerRelativeName = petitionerRelativeName;
	}
	public String getPetitionerGender() {
		return petitionerGender;
	}
	public void setPetitionerGender(String petitionerGender) {
		this.petitionerGender = petitionerGender;
	}
	public Long getPetitionerAge() {
		return petitionerAge;
	}
	public void setPetitionerAge(Long petitionerAge) {
		this.petitionerAge = petitionerAge;
	}
	public String getPetitionerMobileNo() {
		return petitionerMobileNo;
	}
	public void setPetitionerMobileNo(String petitionerMobileNo) {
		this.petitionerMobileNo = petitionerMobileNo;
	}
	public String getPetitionerVoterCardNo() {
		return petitionerVoterCardNo;
	}
	public void setPetitionerVoterCardNo(String petitionerVoterCardNo) {
		this.petitionerVoterCardNo = petitionerVoterCardNo;
	}
	public String getPetitionerAadharCardNo() {
		return petitionerAadharCardNo;
	}
	public void setPetitionerAadharCardNo(String petitionerAadharCardNo) {
		this.petitionerAadharCardNo = petitionerAadharCardNo;
	}
	public String getPetitionerEmailId() {
		return petitionerEmailId;
	}
	public void setPetitionerEmailId(String petitionerEmailId) {
		this.petitionerEmailId = petitionerEmailId;
	}
	public Long getPetitionerDistrictId() {
		return petitionerDistrictId;
	}
	public void setPetitionerDistrictId(Long petitionerDistrictId) {
		this.petitionerDistrictId = petitionerDistrictId;
	}
	public Long getPetitionerTehsilId() {
		return petitionerTehsilId;
	}
	public void setPetitionerTehsilId(Long petitionerTehsilId) {
		this.petitionerTehsilId = petitionerTehsilId;
	}
	public Long getPetitionerPanchayatId() {
		return petitionerPanchayatId;
	}
	public void setPetitionerPanchayatId(Long petitionerPanchayatId) {
		this.petitionerPanchayatId = petitionerPanchayatId;
	}
	public Long getPetitionerHamletId() {
		return petitionerHamletId;
	}
	public void setPetitionerHamletId(Long petitionerHamletId) {
		this.petitionerHamletId = petitionerHamletId;
	}
	public String getPetitionerHouseNO() {
		return petitionerHouseNO;
	}
	public void setPetitionerHouseNO(String petitionerHouseNO) {
		this.petitionerHouseNO = petitionerHouseNO;
	}
	public Long getPetitionerCaste() {
		return petitionerCaste;
	}
	public void setPetitionerCaste(Long petitionerCaste) {
		this.petitionerCaste = petitionerCaste;
	}
	public Long getPetitionerOccupation() {
		return petitionerOccupation;
	}
	public void setPetitionerOccupation(Long petitionerOccupation) {
		this.petitionerOccupation = petitionerOccupation;
	}
	public Long getPetitionerAnnulaIncome() {
		return petitionerAnnulaIncome;
	}
	public void setPetitionerAnnulaIncome(Long petitionerAnnulaIncome) {
		this.petitionerAnnulaIncome = petitionerAnnulaIncome;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getSubDeptId() {
		return subDeptId;
	}
	public void setSubDeptId(Long subDeptId) {
		this.subDeptId = subDeptId;
	}
	public Long getIssueTypeId() {
		return issueTypeId;
	}
	public void setIssueTypeId(Long issueTypeId) {
		this.issueTypeId = issueTypeId;
	}
	public String getGrievanceTitle() {
		return grievanceTitle;
	}
	public void setGrievanceTitle(String grievanceTitle) {
		this.grievanceTitle = grievanceTitle;
	}
	public String getGrievanceDesc() {
		return grievanceDesc;
	}
	public void setGrievanceDesc(String grievanceDesc) {
		this.grievanceDesc = grievanceDesc;
	}
	public Long getAssignLevelId() {
		return assignLevelId;
	}
	public void setAssignLevelId(Long assignLevelId) {
		this.assignLevelId = assignLevelId;
	}
	public Long getAssignLevelValue() {
		return assignLevelValue;
	}
	public void setAssignLevelValue(Long assignLevelValue) {
		this.assignLevelValue = assignLevelValue;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public Long getOfficerId() {
		return officerId;
	}
	public void setOfficerId(Long officerId) {
		this.officerId = officerId;
	}
	public List<MeekosamLandDetailsVO> getLandDetailsList() {
		return landDetailsList;
	}
	public void setLandDetailsList(List<MeekosamLandDetailsVO> landDetailsList) {
		this.landDetailsList = landDetailsList;
	}
	public Long getGrievanceImpactLevel() {
		return grievanceImpactLevel;
	}
	public void setGrievanceImpactLevel(Long grievanceImpactLevel) {
		this.grievanceImpactLevel = grievanceImpactLevel;
	}
	public Long getGrievanceLevelValue() {
		return grievanceLevelValue;
	}
	public void setGrievanceLevelValue(Long grievanceLevelValue) {
		this.grievanceLevelValue = grievanceLevelValue;
	}
	public Long getGrievanceDistrictId() {
		return grievanceDistrictId;
	}
	public void setGrievanceDistrictId(Long grievanceDistrictId) {
		this.grievanceDistrictId = grievanceDistrictId;
	}
	public Long getGrievanceTehsilId() {
		return grievanceTehsilId;
	}
	public void setGrievanceTehsilId(Long grievanceTehsilId) {
		this.grievanceTehsilId = grievanceTehsilId;
	}
	public Long getGrievancePanchayatId() {
		return grievancePanchayatId;
	}
	public void setGrievancePanchayatId(Long grievancePanchayatId) {
		this.grievancePanchayatId = grievancePanchayatId;
	}
	public String getPetitionerDOB() {
		return petitionerDOB;
	}
	public void setPetitionerDOB(String petitionerDOB) {
		this.petitionerDOB = petitionerDOB;
	}
	public Long getPetitionerArgeeCategory() {
		return petitionerArgeeCategory;
	}
	public void setPetitionerArgeeCategory(Long petitionerArgeeCategory) {
		this.petitionerArgeeCategory = petitionerArgeeCategory;
	}
	public Long getMeekosamPetitionerId() {
		return meekosamPetitionerId;
	}
	public void setMeekosamPetitionerId(Long meekosamPetitionerId) {
		this.meekosamPetitionerId = meekosamPetitionerId;
	}
	public List<MeekosamDynamicVO> getDynamicDataList() {
		return dynamicDataList;
	}
	public void setDynamicDataList(List<MeekosamDynamicVO> dynamicDataList) {
		this.dynamicDataList = dynamicDataList;
	}
	public Long getReferalTypeId() {
		return referalTypeId;
	}
	public void setReferalTypeId(Long referalTypeId) {
		this.referalTypeId = referalTypeId;
	}
	public Long getReferalDistrictId() {
		return referalDistrictId;
	}
	public void setReferalDistrictId(Long referalDistrictId) {
		this.referalDistrictId = referalDistrictId;
	}
	public Long getReferalNameId() {
		return referalNameId;
	}
	public void setReferalNameId(Long referalNameId) {
		this.referalNameId = referalNameId;
	}
	public List<Long> getReferCadreIds() {
		return referCadreIds;
	}
	public void setReferCadreIds(List<Long> referCadreIds) {
		this.referCadreIds = referCadreIds;
	}
}
