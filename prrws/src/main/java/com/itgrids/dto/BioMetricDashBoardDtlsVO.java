package com.itgrids.dto;

import java.util.List;
import java.util.Set;

public class BioMetricDashBoardDtlsVO {

	private Long totalWorkingDays = 0l;
	private Long nonWorkingDays = 0l;
	private String empId;
	private String mobileNo;
	private String designation;
	private Long totalCount = 0l;
	private Long presentCount = 0l;
	private Long nonWorkingDayPresent = 0l;
	private Long absentCount = 0l;
	private Long bioMetricDeviceCount = 0l;
	private String name;
	private String dayOfWeek;
	private String order;
	private String isPresent;
	private String type;
	private Long fromTimeMiliSecond;
	private Long toTimeMiliSecond;
	private String deptCode;
	private String deptName;
	private String date;
	private String time;
	
	private List<BioMetricDashBoardDtlsVO> subList1;
	private List<BioMetricDashBoardDtlsVO> subList2;
	private Set<String> set;
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getPresentCount() {
		return presentCount;
	}
	public void setPresentCount(Long presentCount) {
		this.presentCount = presentCount;
	}
	public Long getAbsentCount() {
		return absentCount;
	}
	public void setAbsentCount(Long absentCount) {
		this.absentCount = absentCount;
	}
	public Long getBioMetricDeviceCount() {
		return bioMetricDeviceCount;
	}
	public void setBioMetricDeviceCount(Long bioMetricDeviceCount) {
		this.bioMetricDeviceCount = bioMetricDeviceCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Long getTotalWorkingDays() {
		return totalWorkingDays;
	}
	public void setTotalWorkingDays(Long totalWorkingDays) {
		this.totalWorkingDays = totalWorkingDays;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public List<BioMetricDashBoardDtlsVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<BioMetricDashBoardDtlsVO> subList1) {
		this.subList1 = subList1;
	}
	public List<BioMetricDashBoardDtlsVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<BioMetricDashBoardDtlsVO> subList2) {
		this.subList2 = subList2;
	}
	public String getIsPresent() {
		return isPresent;
	}
	public void setIsPresent(String isPresent) {
		this.isPresent = isPresent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getFromTimeMiliSecond() {
		return fromTimeMiliSecond;
	}
	public void setFromTimeMiliSecond(Long fromTimeMiliSecond) {
		this.fromTimeMiliSecond = fromTimeMiliSecond;
	}
	public Long getToTimeMiliSecond() {
		return toTimeMiliSecond;
	}
	public void setToTimeMiliSecond(Long toTimeMiliSecond) {
		this.toTimeMiliSecond = toTimeMiliSecond;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Set<String> getSet() {
		return set;
	}
	public void setSet(Set<String> set) {
		this.set = set;
	}
	public Long getNonWorkingDays() {
		return nonWorkingDays;
	}
	public void setNonWorkingDays(Long nonWorkingDays) {
		this.nonWorkingDays = nonWorkingDays;
	}
	public Long getNonWorkingDayPresent() {
		return nonWorkingDayPresent;
	}
	public void setNonWorkingDayPresent(Long nonWorkingDayPresent) {
		this.nonWorkingDayPresent = nonWorkingDayPresent;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
