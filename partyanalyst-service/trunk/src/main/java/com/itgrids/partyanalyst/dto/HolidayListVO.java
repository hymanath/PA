package com.itgrids.partyanalyst.dto;

public class HolidayListVO {
	private String date;
	private String isHoliday;
	private Long presentCount = 0l;
	private Long absentCount = 0l;
	private Long holidayAbsentCount = 0l;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIsHoliday() {
		return isHoliday;
	}
	public void setIsHoliday(String isHoliday) {
		this.isHoliday = isHoliday;
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
	public Long getHolidayAbsentCount() {
		return holidayAbsentCount;
	}
	public void setHolidayAbsentCount(Long holidayAbsentCount) {
		this.holidayAbsentCount = holidayAbsentCount;
	}
	
}
