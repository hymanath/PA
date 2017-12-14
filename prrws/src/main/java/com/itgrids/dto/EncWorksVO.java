package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class EncWorksVO {

	private Long locationId;
	private String locationName;
	private Long underProcessCount;
	private Long adminSanctionCount;
	private Long technicallySanctionedCount;
	private Long CompletedCount;
	private Long notGrounded;
	private Long districtCode;
	private String districtName;
	private Long mandalCode;
	private String mandalName;
	private List<EncWorksVO> subList = new ArrayList<EncWorksVO>();
	
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationValue() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getUnderProcessCount() {
		return underProcessCount;
	}
	public void setUnderProcessCount(Long underProcessCount) {
		this.underProcessCount = underProcessCount;
	}
	public Long getAdminSanctionCount() {
		return adminSanctionCount;
	}
	public void setAdminSanctionCount(Long adminSanctionCount) {
		this.adminSanctionCount = adminSanctionCount;
	}
	public Long getTechnicallySanctionedCount() {
		return technicallySanctionedCount;
	}
	public void setTechnicallySanctionedCount(Long technicallySanctionedCount) {
		this.technicallySanctionedCount = technicallySanctionedCount;
	}
	public Long getCompletedCount() {
		return CompletedCount;
	}
	public void setCompletedCount(Long completedCount) {
		CompletedCount = completedCount;
	}
	public Long getNotGrounded() {
		return notGrounded;
	}
	public void setNotGrounded(Long notGrounded) {
		this.notGrounded = notGrounded;
	}
	
	public Long getMandalCode() {
		return mandalCode;
	}
	public void setMandalCode(Long mandalCode) {
		this.mandalCode = mandalCode;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public Long getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Long districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public List<EncWorksVO> getSubList() {
		return subList;
	}
	public void setSubList(List<EncWorksVO> subList) {
		this.subList = subList;
	}
	
	
}
