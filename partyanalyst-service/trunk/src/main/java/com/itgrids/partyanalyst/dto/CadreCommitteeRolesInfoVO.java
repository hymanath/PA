package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CadreCommitteeRolesInfoVO implements java.io.Serializable{

	private Long roleId;
	private String roleName;
	private Long maleCount;
	private Long femaleCount;
	private Long totalCount;
	private String casteCategory;
	private String casteCategoryGroup;
	private String caste;
	private String ageRange;
	private String locationId;
	private String locationName;
	
	private List<CadreCommitteeRolesInfoVO> casteCategoryWiseList = new ArrayList<CadreCommitteeRolesInfoVO>();
	private List<CadreCommitteeRolesInfoVO> casteCategoryGroupWiseList = new ArrayList<CadreCommitteeRolesInfoVO>();
	private List<CadreCommitteeRolesInfoVO> casteWiseList = new ArrayList<CadreCommitteeRolesInfoVO>();
	private List<CadreCommitteeRolesInfoVO> ageRangeWiseList = new ArrayList<CadreCommitteeRolesInfoVO>();
	private List<CadreCommitteeRolesInfoVO> locationWiseList = new ArrayList<CadreCommitteeRolesInfoVO>();
	
	private List<CadreCommitteeRolesInfoVO> cadreCommitteeRolesInfoVOList = new ArrayList<CadreCommitteeRolesInfoVO>();
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(Long maleCount) {
		this.maleCount = maleCount;
	}
	public Long getFemaleCount() {
		return femaleCount;
	}
	public void setFemaleCount(Long femaleCount) {
		this.femaleCount = femaleCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}
	public String getCasteCategoryGroup() {
		return casteCategoryGroup;
	}
	public void setCasteCategoryGroup(String casteCategoryGroup) {
		this.casteCategoryGroup = casteCategoryGroup;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public List<CadreCommitteeRolesInfoVO> getCasteCategoryWiseList() {
		return casteCategoryWiseList;
	}
	public void setCasteCategoryWiseList(
			List<CadreCommitteeRolesInfoVO> casteCategoryWiseList) {
		this.casteCategoryWiseList = casteCategoryWiseList;
	}
	public List<CadreCommitteeRolesInfoVO> getCasteCategoryGroupWiseList() {
		return casteCategoryGroupWiseList;
	}
	public void setCasteCategoryGroupWiseList(
			List<CadreCommitteeRolesInfoVO> casteCategoryGroupWiseList) {
		this.casteCategoryGroupWiseList = casteCategoryGroupWiseList;
	}
	public List<CadreCommitteeRolesInfoVO> getCasteWiseList() {
		return casteWiseList;
	}
	public void setCasteWiseList(List<CadreCommitteeRolesInfoVO> casteWiseList) {
		this.casteWiseList = casteWiseList;
	}
	public List<CadreCommitteeRolesInfoVO> getAgeRangeWiseList() {
		return ageRangeWiseList;
	}
	public void setAgeRangeWiseList(List<CadreCommitteeRolesInfoVO> ageRangeWiseList) {
		this.ageRangeWiseList = ageRangeWiseList;
	}
	public List<CadreCommitteeRolesInfoVO> getLocationWiseList() {
		return locationWiseList;
	}
	public void setLocationWiseList(List<CadreCommitteeRolesInfoVO> locationWiseList) {
		this.locationWiseList = locationWiseList;
	}
	public List<CadreCommitteeRolesInfoVO> getCadreCommitteeRolesInfoVOList() {
		return cadreCommitteeRolesInfoVOList;
	}
	public void setCadreCommitteeRolesInfoVOList(
			List<CadreCommitteeRolesInfoVO> cadreCommitteeRolesInfoVOList) {
		this.cadreCommitteeRolesInfoVOList = cadreCommitteeRolesInfoVOList;
	}
	
}
