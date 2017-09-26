package com.itgrids.dto;

import java.util.List;

public class EofficeDtlsVO {

	private Long totalPendencyCount;
	private Long id;
	private String name;
	private Long totalFileCount;
	private Long pendingFileCount;
	private Double pendingPer;
	private Long oneToSeventDaysCount;
	private Long eightToFifteenDaysCount;
	private Long sixteenToThiryDaysCount;
	private Long thirtyOneToSixtyDaysCount;
	private Long moreThanSixtyDaysCount;
	
	private List<EofficeDtlsVO> subList1;
	private List<EofficeDtlsVO> subList2;
	
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
	public Long getTotalFileCount() {
		return totalFileCount;
	}
	public void setTotalFileCount(Long totalFileCount) {
		this.totalFileCount = totalFileCount;
	}
	public Long getPendingFileCount() {
		return pendingFileCount;
	}
	public void setPendingFileCount(Long pendingFileCount) {
		this.pendingFileCount = pendingFileCount;
	}
	public Double getPendingPer() {
		return pendingPer;
	}
	public void setPendingPer(Double pendingPer) {
		this.pendingPer = pendingPer;
	}
	public List<EofficeDtlsVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<EofficeDtlsVO> subList1) {
		this.subList1 = subList1;
	}
	public List<EofficeDtlsVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<EofficeDtlsVO> subList2) {
		this.subList2 = subList2;
	}
	public Long getTotalPendencyCount() {
		return totalPendencyCount;
	}
	public void setTotalPendencyCount(Long totalPendencyCount) {
		this.totalPendencyCount = totalPendencyCount;
	}
	public Long getOneToSeventDaysCount() {
		return oneToSeventDaysCount;
	}
	public void setOneToSeventDaysCount(Long oneToSeventDaysCount) {
		this.oneToSeventDaysCount = oneToSeventDaysCount;
	}
	public Long getEightToFifteenDaysCount() {
		return eightToFifteenDaysCount;
	}
	public void setEightToFifteenDaysCount(Long eightToFifteenDaysCount) {
		this.eightToFifteenDaysCount = eightToFifteenDaysCount;
	}
	public Long getSixteenToThiryDaysCount() {
		return sixteenToThiryDaysCount;
	}
	public void setSixteenToThiryDaysCount(Long sixteenToThiryDaysCount) {
		this.sixteenToThiryDaysCount = sixteenToThiryDaysCount;
	}
	public Long getThirtyOneToSixtyDaysCount() {
		return thirtyOneToSixtyDaysCount;
	}
	public void setThirtyOneToSixtyDaysCount(Long thirtyOneToSixtyDaysCount) {
		this.thirtyOneToSixtyDaysCount = thirtyOneToSixtyDaysCount;
	}
	public Long getMoreThanSixtyDaysCount() {
		return moreThanSixtyDaysCount;
	}
	public void setMoreThanSixtyDaysCount(Long moreThanSixtyDaysCount) {
		this.moreThanSixtyDaysCount = moreThanSixtyDaysCount;
	}
	
	
}
