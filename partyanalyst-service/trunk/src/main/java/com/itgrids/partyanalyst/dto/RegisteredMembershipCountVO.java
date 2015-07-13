package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class RegisteredMembershipCountVO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long constituencyCount ;
	private Long mandalCount ;
	private Long panchayatCount;
	private Long boothCount ;
	private Long munCount;
	private String year;
	private Long consTotalVoters ;
	private Long mandalTotVoters ;
	private Long panchayatTotVoters;
	private Long boothTotVoters;
	private Long munTotVoters;
	
	private String boothPerc;
	private String panchPerc;
	private String mandalPerc;
	private String constiPerc;
	private String munciPerc;
	private String munPerc;
	private String areaType;
	
	
	public Long getConstituencyCount() {
		return constituencyCount;
	}
	public void setConstituencyCount(Long constituencyCount) {
		this.constituencyCount = constituencyCount;
	}
	public Long getMandalCount() {
		return mandalCount;
	}
	public void setMandalCount(Long mandalCount) {
		this.mandalCount = mandalCount;
	}
	public Long getPanchayatCount() {
		return panchayatCount;
	}
	public void setPanchayatCount(Long panchayatCount) {
		this.panchayatCount = panchayatCount;
	}
	public Long getBoothCount() {
		return boothCount;
	}
	public void setBoothCount(Long boothCount) {
		this.boothCount = boothCount;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Long getConsTotalVoters() {
		return consTotalVoters;
	}
	public void setConsTotalVoters(Long consTotalVoters) {
		this.consTotalVoters = consTotalVoters;
	}
	public Long getMandalTotVoters() {
		return mandalTotVoters;
	}
	public void setMandalTotVoters(Long mandalTotVoters) {
		this.mandalTotVoters = mandalTotVoters;
	}
	public Long getPanchayatTotVoters() {
		return panchayatTotVoters;
	}
	public void setPanchayatTotVoters(Long panchayatTotVoters) {
		this.panchayatTotVoters = panchayatTotVoters;
	}
	public Long getBoothTotVoters() {
		return boothTotVoters;
	}
	public void setBoothTotVoters(Long boothTotVoters) {
		this.boothTotVoters = boothTotVoters;
	}
	
	public String getBoothPerc() {
		return boothPerc;
	}
	public void setBoothPerc(String boothPerc) {
		this.boothPerc = boothPerc;
	}
	public String getPanchPerc() {
		return panchPerc;
	}
	public void setPanchPerc(String panchPerc) {
		this.panchPerc = panchPerc;
	}
	public String getMandalPerc() {
		return mandalPerc;
	}
	public void setMandalPerc(String mandalPerc) {
		this.mandalPerc = mandalPerc;
	}
	public String getConstiPerc() {
		return constiPerc;
	}
	public void setConstiPerc(String constiPerc) {
		this.constiPerc = constiPerc;
	}
	public String getMunciPerc() {
		return munciPerc;
	}
	public void setMunciPerc(String munciPerc) {
		this.munciPerc = munciPerc;
	}
	public Long getMunCount() {
		return munCount;
	}
	public void setMunCount(Long munCount) {
		this.munCount = munCount;
	}
	
	public Long getMunTotVoters() {
		return munTotVoters;
	}
	public void setMunTotVoters(Long munTotVoters) {
		this.munTotVoters = munTotVoters;
	}
	public String getMunPerc() {
		return munPerc;
	}
	public void setMunPerc(String munPerc) {
		this.munPerc = munPerc;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	

}
