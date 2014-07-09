package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class ConstituencyDetailReportVO implements java.io.Serializable{

	private Long Id;
	private String name;
	
	private Long constituencyTotalVoters;
	private Long totalColelctedVoters;
	private Long totalVerifiedVoters;
	
	private Long dataCollectedCount;
	private Long casteCollectedCount;
	private Long hamletCollectedCount;
	private Long cadreCollectedCount;
	private Long influencePeopleCollectedCount;
	private Long mobileNoCollectedCount;
	private Long notCollectedVoters;
	
	private Long dataVerifiedCount;
	private Long casteVerifiedCount;
	private Long hamletVerifiedCount;
	private Long cadreVerifiedCount;
	private Long influencePeopleVerifiedCount;
	private Long mobileNoVerifiedCount;
	private Long notVerifiedVoters;
	
	private Long localAreaCount;
	
	private List<GenericVO> boothsList = new ArrayList<GenericVO>();
			
	
	public List<GenericVO> getBoothsList() {
		return boothsList;
	}

	public void setBoothsList(List<GenericVO> boothsList) {
		this.boothsList = boothsList;
	}

	public Long getConstituencyTotalVoters() {
		return constituencyTotalVoters;
	}

	public void setConstituencyTotalVoters(Long constituencyTotalVoters) {
		this.constituencyTotalVoters = constituencyTotalVoters;
	}

	public Long getTotalColelctedVoters() {
		return totalColelctedVoters;
	}

	public void setTotalColelctedVoters(Long totalColelctedVoters) {
		this.totalColelctedVoters = totalColelctedVoters;
	}

	public Long getTotalVerifiedVoters() {
		return totalVerifiedVoters;
	}

	public void setTotalVerifiedVoters(Long totalVerifiedVoters) {
		this.totalVerifiedVoters = totalVerifiedVoters;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDataCollectedCount() {
		return dataCollectedCount;
	}

	public void setDataCollectedCount(Long dataCollectedCount) {
		this.dataCollectedCount = dataCollectedCount;
	}

	public Long getCasteCollectedCount() {
		return casteCollectedCount;
	}

	public void setCasteCollectedCount(Long casteCollectedCount) {
		this.casteCollectedCount = casteCollectedCount;
	}

	public Long getHamletCollectedCount() {
		return hamletCollectedCount;
	}

	public void setHamletCollectedCount(Long hamletCollectedCount) {
		this.hamletCollectedCount = hamletCollectedCount;
	}

	public Long getCadreCollectedCount() {
		return cadreCollectedCount;
	}

	public void setCadreCollectedCount(Long cadreCollectedCount) {
		this.cadreCollectedCount = cadreCollectedCount;
	}

	public Long getInfluencePeopleCollectedCount() {
		return influencePeopleCollectedCount;
	}

	public void setInfluencePeopleCollectedCount(Long influencePeopleCollectedCount) {
		this.influencePeopleCollectedCount = influencePeopleCollectedCount;
	}

	public Long getMobileNoCollectedCount() {
		return mobileNoCollectedCount;
	}

	public void setMobileNoCollectedCount(Long mobileNoCollectedCount) {
		this.mobileNoCollectedCount = mobileNoCollectedCount;
	}

	public Long getNotCollectedVoters() {
		return notCollectedVoters;
	}

	public void setNotCollectedVoters(Long notCollectedVoters) {
		this.notCollectedVoters = notCollectedVoters;
	}

	public Long getDataVerifiedCount() {
		return dataVerifiedCount;
	}

	public void setDataVerifiedCount(Long dataVerifiedCount) {
		this.dataVerifiedCount = dataVerifiedCount;
	}

	public Long getCasteVerifiedCount() {
		return casteVerifiedCount;
	}

	public void setCasteVerifiedCount(Long casteVerifiedCount) {
		this.casteVerifiedCount = casteVerifiedCount;
	}

	public Long getHamletVerifiedCount() {
		return hamletVerifiedCount;
	}

	public void setHamletVerifiedCount(Long hamletVerifiedCount) {
		this.hamletVerifiedCount = hamletVerifiedCount;
	}

	public Long getCadreVerifiedCount() {
		return cadreVerifiedCount;
	}

	public void setCadreVerifiedCount(Long cadreVerifiedCount) {
		this.cadreVerifiedCount = cadreVerifiedCount;
	}

	public Long getInfluencePeopleVerifiedCount() {
		return influencePeopleVerifiedCount;
	}

	public void setInfluencePeopleVerifiedCount(Long influencePeopleVerifiedCount) {
		this.influencePeopleVerifiedCount = influencePeopleVerifiedCount;
	}

	public Long getMobileNoVerifiedCount() {
		return mobileNoVerifiedCount;
	}

	public void setMobileNoVerifiedCount(Long mobileNoVerifiedCount) {
		this.mobileNoVerifiedCount = mobileNoVerifiedCount;
	}

	public Long getNotVerifiedVoters() {
		return notVerifiedVoters;
	}

	public void setNotVerifiedVoters(Long notVerifiedVoters) {
		this.notVerifiedVoters = notVerifiedVoters;
	}

	public Long getLocalAreaCount() {
		return localAreaCount;
	}

	public void setLocalAreaCount(Long localAreaCount) {
		this.localAreaCount = localAreaCount;
	}
	
}
