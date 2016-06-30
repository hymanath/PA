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
	private Long districtTotVoters;
	private Long districtCount;
	private Long parConsTotVoters;
	private Long parConsCount;
	
	private String boothPerc;
	private String panchPerc;
	private String mandalPerc;
	private String constiPerc;
	private String munciPerc;
	private String munPerc;
	private String districtPerc;
	private String parConsPerc;
	private String areaType;
	private String cadreLocation;
	private String reasonStr;
	private String mandalNameStr;
	
	
	public String getMandalNameStr() {
		return mandalNameStr;
	}
	public void setMandalNameStr(String mandalNameStr) {
		this.mandalNameStr = mandalNameStr;
	}
	public String getReasonStr() {
		return reasonStr;
	}
	public void setReasonStr(String reasonStr) {
		this.reasonStr = reasonStr;
	}
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
	public Long getDistrictTotVoters() {
		return districtTotVoters;
	}
	public void setDistrictTotVoters(Long districtTotVoters) {
		this.districtTotVoters = districtTotVoters;
	}
	public Long getDistrictCount() {
		return districtCount;
	}
	public void setDistrictCount(Long districtCount) {
		this.districtCount = districtCount;
	}
	public Long getParConsTotVoters() {
		return parConsTotVoters;
	}
	public void setParConsTotVoters(Long parConsTotVoters) {
		this.parConsTotVoters = parConsTotVoters;
	}
	public Long getParConsCount() {
		return parConsCount;
	}
	public void setParConsCount(Long parConsCount) {
		this.parConsCount = parConsCount;
	}
	public String getDistrictPerc() {
		return districtPerc;
	}
	public void setDistrictPerc(String districtPerc) {
		this.districtPerc = districtPerc;
	}
	public String getParConsPerc() {
		return parConsPerc;
	}
	public void setParConsPerc(String parConsPerc) {
		this.parConsPerc = parConsPerc;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getCadreLocation() {
		return cadreLocation;
	}
	public void setCadreLocation(String cadreLocation) {
		this.cadreLocation = cadreLocation;
	}
	
	
	

}
