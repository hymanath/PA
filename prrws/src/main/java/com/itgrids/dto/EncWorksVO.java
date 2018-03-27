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
	private Long nonTechinicalSanctioned=0l;
	private Long notEntrusted=0l;
	private Long NotGroundedExceededCount=0l;
	private Long ongoingExceededCount=0l;
	private Long completedExceededCount=0l;
	private String districtName;
	private Long districtId;
	private Long panchayathId;
	private String panchaythName;
	private String habCode;
	private String habName;
	private String adminDate;
	private String targetDate;
	private String completedDate;
	private String groundedDate;
	private Long sanctionedAmount;
	private String workId;
	private String workName;
	private Long grantId;
	private String grantName;
	private Long subGrantId;
	private String subGrantName;
		
	
	
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
	public Long getNonTechinicalSanctioned() {
		return nonTechinicalSanctioned;
	}
	public void setNonTechinicalSanctioned(Long nonTechinicalSanctioned) {
		this.nonTechinicalSanctioned = nonTechinicalSanctioned;
	}
	public Long getNotEntrusted() {
		return notEntrusted;
	}
	public void setNotEntrusted(Long notEntrusted) {
		this.notEntrusted = notEntrusted;
	}
	public Long getNotGroundedExceededCount() {
		return NotGroundedExceededCount;
	}
	public void setNotGroundedExceededCount(Long notGroundedExceededCount) {
		NotGroundedExceededCount = notGroundedExceededCount;
	}
	public Long getOngoingExceededCount() {
		return ongoingExceededCount;
	}
	public void setOngoingExceededCount(Long ongoingExceededCount) {
		this.ongoingExceededCount = ongoingExceededCount;
	}
	public Long getCompletedExceededCount() {
		return completedExceededCount;
	}
	public void setCompletedExceededCount(Long completedExceededCount) {
		this.completedExceededCount = completedExceededCount;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getPanchayathId() {
		return panchayathId;
	}
	public void setPanchayathId(Long panchayathId) {
		this.panchayathId = panchayathId;
	}
	public String getPanchaythName() {
		return panchaythName;
	}
	public void setPanchaythName(String panchaythName) {
		this.panchaythName = panchaythName;
	}
	public String getHabCode() {
		return habCode;
	}
	public void setHabCode(String habCode) {
		this.habCode = habCode;
	}
	public String getHabName() {
		return habName;
	}
	public void setHabName(String habName) {
		this.habName = habName;
	}
	public String getAdminDate() {
		return adminDate;
	}
	public void setAdminDate(String adminDate) {
		this.adminDate = adminDate;
	}
	public String getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
	public String getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}
	public String getGroundedDate() {
		return groundedDate;
	}
	public void setGroundedDate(String groundedDate) {
		this.groundedDate = groundedDate;
	}
	public Long getSanctionedAmount() {
		return sanctionedAmount;
	}
	public void setSanctionedAmount(Long sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public Long getGrantId() {
		return grantId;
	}
	public void setGrantId(Long grantId) {
		this.grantId = grantId;
	}
	public String getGrantName() {
		return grantName;
	}
	public void setGrantName(String grantName) {
		this.grantName = grantName;
	}
	public Long getSubGrantId() {
		return subGrantId;
	}
	public void setSubGrantId(Long subGrantId) {
		this.subGrantId = subGrantId;
	}
	public String getSubGrantName() {
		return subGrantName;
	}
	public void setSubGrantName(String subGrantName) {
		this.subGrantName = subGrantName;
	}
	
	
}
