package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

import com.itgrids.partyanalyst.model.AddressType;

public class PanchayatVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long panchayatId;
	private String panchayatName;
	private int totalVoters;
	private int maleVoters;
	private int femaleVoters;
	private String ageRange;
	private List<PanchayatVO> panchayatList;
	private float percentage;
	private int totalPanchayatVoters;
	private List<CastVO> topCastes;
	
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public int getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(int totalVoters) {
		this.totalVoters = totalVoters;
	}
	public int getMaleVoters() {
		return maleVoters;
	}
	public void setMaleVoters(int maleVoters) {
		this.maleVoters = maleVoters;
	}
	public int getFemaleVoters() {
		return femaleVoters;
	}
	public void setFemaleVoters(int femaleVoters) {
		this.femaleVoters = femaleVoters;
	}
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public List<PanchayatVO> getPanchayatList() {
		return panchayatList;
	}
	public void setPanchayatList(List<PanchayatVO> panchayatList) {
		this.panchayatList = panchayatList;
	}
	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	public int getTotalPanchayatVoters() {
		return totalPanchayatVoters;
	}
	public void setTotalPanchayatVoters(int totalPanchayatVoters) {
		this.totalPanchayatVoters = totalPanchayatVoters;
	}
	public List<CastVO> getTopCastes() {
		return topCastes;
	}
	public void setTopCastes(List<CastVO> topCastes) {
		this.topCastes = topCastes;
	}
	
	
	
}
