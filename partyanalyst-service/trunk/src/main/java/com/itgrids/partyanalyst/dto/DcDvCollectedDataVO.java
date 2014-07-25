package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class DcDvCollectedDataVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long boothId;
	private String partNo;
	private String date;
	private String constituency;
	private Long totalCount = 0l;
	private Long mobileCount = 0l;;
	private Long hamletCount = 0l;
	private Long casteCount = 0l;
	private Long cadreCount = 0l;
	private Long influencePeopleCount = 0l;
	private Long localAreaCount = 0l;
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getMobileCount() {
		return mobileCount;
	}
	public void setMobileCount(Long mobileCount) {
		this.mobileCount = mobileCount;
	}
	public Long getHamletCount() {
		return hamletCount;
	}
	public void setHamletCount(Long hamletCount) {
		this.hamletCount = hamletCount;
	}
	public Long getCasteCount() {
		return casteCount;
	}
	public void setCasteCount(Long casteCount) {
		this.casteCount = casteCount;
	}
	public Long getCadreCount() {
		return cadreCount;
	}
	public void setCadreCount(Long cadreCount) {
		this.cadreCount = cadreCount;
	}
	public Long getInfluencePeopleCount() {
		return influencePeopleCount;
	}
	public void setInfluencePeopleCount(Long influencePeopleCount) {
		this.influencePeopleCount = influencePeopleCount;
	}
	public Long getLocalAreaCount() {
		return localAreaCount;
	}
	public void setLocalAreaCount(Long localAreaCount) {
		this.localAreaCount = localAreaCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	
	
	
}
