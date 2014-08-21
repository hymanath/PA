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
	private Long id;
	private String casteErrorRate;
	private String mobileErrorRate;
	private Long casteActiveVoters =0l;
	private Long casteInActiveVoters=0l;
	private Long mobileActiveVoters =0l;
	private Long mobileInActiveVoters=0l;
	private String name;
	private Long boothCount;
	private Long casteTagedBooths;
	private Long mobileTagedBooths;
	private Long casteVerifiedBooths;
	private Long mobileVerifiedBooths;
	
	
	public Long getCasteVerifiedBooths() {
		return casteVerifiedBooths;
	}
	public void setCasteVerifiedBooths(Long casteVerifiedBooths) {
		this.casteVerifiedBooths = casteVerifiedBooths;
	}
	public Long getMobileVerifiedBooths() {
		return mobileVerifiedBooths;
	}
	public void setMobileVerifiedBooths(Long mobileVerifiedBooths) {
		this.mobileVerifiedBooths = mobileVerifiedBooths;
	}
	public Long getCasteTagedBooths() {
		return casteTagedBooths;
	}
	public void setCasteTagedBooths(Long casteTagedBooths) {
		this.casteTagedBooths = casteTagedBooths;
	}
	public Long getMobileTagedBooths() {
		return mobileTagedBooths;
	}
	public void setMobileTagedBooths(Long mobileTagedBooths) {
		this.mobileTagedBooths = mobileTagedBooths;
	}
	
	public Long getBoothCount() {
		return boothCount;
	}
	public void setBoothCount(Long boothCount) {
		this.boothCount = boothCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCasteActiveVoters() {
		return casteActiveVoters;
	}
	public void setCasteActiveVoters(Long casteActiveVoters) {
		this.casteActiveVoters = casteActiveVoters;
	}
	public Long getCasteInActiveVoters() {
		return casteInActiveVoters;
	}
	public void setCasteInActiveVoters(Long casteInActiveVoters) {
		this.casteInActiveVoters = casteInActiveVoters;
	}
	public Long getMobileActiveVoters() {
		return mobileActiveVoters;
	}
	public void setMobileActiveVoters(Long mobileActiveVoters) {
		this.mobileActiveVoters = mobileActiveVoters;
	}
	public Long getMobileInActiveVoters() {
		return mobileInActiveVoters;
	}
	public void setMobileInActiveVoters(Long mobileInActiveVoters) {
		this.mobileInActiveVoters = mobileInActiveVoters;
	}
	public String getCasteErrorRate() {
		return casteErrorRate;
	}
	public void setCasteErrorRate(String casteErrorRate) {
		this.casteErrorRate = casteErrorRate;
	}
	public String getMobileErrorRate() {
		return mobileErrorRate;
	}
	public void setMobileErrorRate(String mobileErrorRate) {
		this.mobileErrorRate = mobileErrorRate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String wmErrorRate;
	private String verifierErrorRate;
	
	
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
	public String getWmErrorRate() {
		return wmErrorRate;
	}
	public void setWmErrorRate(String wmErrorRate) {
		this.wmErrorRate = wmErrorRate;
	}
	public String getVerifierErrorRate() {
		return verifierErrorRate;
	}
	public void setVerifierErrorRate(String verifierErrorRate) {
		this.verifierErrorRate = verifierErrorRate;
	}
	
	
}
