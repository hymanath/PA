package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class EncWorksVO {

	private Long totalWorksEntrusted = 0l;
	private Long locationId;
	private String locationName;
	private Long underProcessCount=0l;
	private Long adminSanctionCount=0l;
	private Long technicallySanctionedCount=0l;
	private Long CompletedCount=0l;
	private Long notGrounded=0l;
	private Long constituencyId;
	private String constituencyname;
	private Long mandalCode;
	private String mandalName;
	private List<EncWorksVO> subList = new ArrayList<EncWorksVO>();
	private Long groundedCount=0l;
	
	
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
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyname() {
		return constituencyname;
	}
	public void setConstituencyname(String constituencyname) {
		this.constituencyname = constituencyname;
	}
	public List<EncWorksVO> getSubList() {
		return subList;
	}
	public void setSubList(List<EncWorksVO> subList) {
		this.subList = subList;
	}
	public Long setGroundedCount() {

		return groundedCount;
	}
	public Long getGroundedCount() {
		return groundedCount;
	}
	public void setGroundedCount(Long groundedCount) {
		this.groundedCount = groundedCount;
	}
	public String getLocationName() {
		return locationName;
	}
	public Long getTotalWorksEntrusted() {
		return totalWorksEntrusted;
	}
	public void setTotalWorksEntrusted(Long totalWorksEntrusted) {
		this.totalWorksEntrusted = totalWorksEntrusted;
	}
	
	
}
