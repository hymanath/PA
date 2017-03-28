/**
 * @author SRAVANTH
 * MAR 28, 2017
 * InsuranceLagDaysVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

/**
 * @author SRAVANTH
 * @date MAR 28, 2017
 */
public class InsuranceLagDaysVO {

	private Long id;
	private String name;
	
	private Long todayCount = 0l;
	private Long weekCount = 0l;
	private Long monthCount = 0l;
	private Long threeMnthsCount = 0l;
	private Long sixMnthsCount = 0l;
	private Long nineMonthsCount = 0l;
	private Long overAllCount = 0l;
	
	
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
	public Long getTodayCount() {
		return todayCount;
	}
	public void setTodayCount(Long todayCount) {
		this.todayCount = todayCount;
	}
	public Long getWeekCount() {
		return weekCount;
	}
	public void setWeekCount(Long weekCount) {
		this.weekCount = weekCount;
	}
	public Long getMonthCount() {
		return monthCount;
	}
	public void setMonthCount(Long monthCount) {
		this.monthCount = monthCount;
	}
	public Long getThreeMnthsCount() {
		return threeMnthsCount;
	}
	public void setThreeMnthsCount(Long threeMnthsCount) {
		this.threeMnthsCount = threeMnthsCount;
	}
	public Long getSixMnthsCount() {
		return sixMnthsCount;
	}
	public void setSixMnthsCount(Long sixMnthsCount) {
		this.sixMnthsCount = sixMnthsCount;
	}
	public Long getNineMonthsCount() {
		return nineMonthsCount;
	}
	public void setNineMonthsCount(Long nineMonthsCount) {
		this.nineMonthsCount = nineMonthsCount;
	}
	public Long getOverAllCount() {
		return overAllCount;
	}
	public void setOverAllCount(Long overAllCount) {
		this.overAllCount = overAllCount;
	}
}
