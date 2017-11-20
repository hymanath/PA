package com.itgrids.partyanalyst.dto;

import java.util.List;

public class BoothInchargeDetailsVO {
	
	private Long locationId;
	private String locationIdStr;
	private String locationName;
	private Long totalBoothCount=0l;
	private Long startedBoothCount=0l;
	private Long notStartedBoothCount=0l;
	private Long completedBoothCount=0l;
	private Long roleId;
	private String roleName;
	private Long count=0l;
	private Long minMemberCount=0l;
	private Long maxMemberCount=0l;
	private Long addedMemberCount=0l;
	private String status;
	private Long roleMappingId;
	private Long vacancyCount=0l;
	
	private List<BoothInchargeDetailsVO> subList;
	private BoothAddressVO boothAddressVO;
	
	public BoothInchargeDetailsVO(){}
	public BoothInchargeDetailsVO(Long locationId,String locationName){
		this.locationId = locationId;
		this.locationName = locationName;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getTotalBoothCount() {
		return totalBoothCount;
	}
	public void setTotalBoothCount(Long totalBoothCount) {
		this.totalBoothCount = totalBoothCount;
	}
	public Long getStartedBoothCount() {
		return startedBoothCount;
	}
	public void setStartedBoothCount(Long startedBoothCount) {
		this.startedBoothCount = startedBoothCount;
	}
	public Long getNotStartedBoothCount() {
		return notStartedBoothCount;
	}
	public void setNotStartedBoothCount(Long notStartedBoothCount) {
		this.notStartedBoothCount = notStartedBoothCount;
	}
	public Long getCompletedBoothCount() {
		return completedBoothCount;
	}
	public void setCompletedBoothCount(Long completedBoothCount) {
		this.completedBoothCount = completedBoothCount;
	}
	public BoothAddressVO getBoothAddressVO() {
		return boothAddressVO;
	}
	public void setBoothAddressVO(BoothAddressVO boothAddressVO) {
		this.boothAddressVO = boothAddressVO;
	}
	public String getLocationIdStr() {
		return locationIdStr;
	}
	public void setLocationIdStr(String locationIdStr) {
		this.locationIdStr = locationIdStr;
	}
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
	public Long getMinMemberCount() {
		return minMemberCount;
	}
	public void setMinMemberCount(Long minMemberCount) {
		this.minMemberCount = minMemberCount;
	}
	public Long getMaxMemberCount() {
		return maxMemberCount;
	}
	public void setMaxMemberCount(Long maxMemberCount) {
		this.maxMemberCount = maxMemberCount;
	}
	public Long getAddedMemberCount() {
		return addedMemberCount;
	}
	public void setAddedMemberCount(Long addedMemberCount) {
		this.addedMemberCount = addedMemberCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<BoothInchargeDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<BoothInchargeDetailsVO> subList) {
		this.subList = subList;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getRoleMappingId() {
		return roleMappingId;
	}
	public void setRoleMappingId(Long roleMappingId) {
		this.roleMappingId = roleMappingId;
	}
	public Long getVacancyCount() {
		return vacancyCount;
	}
	public void setVacancyCount(Long vacancyCount) {
		this.vacancyCount = vacancyCount;
	}
	
	
}
