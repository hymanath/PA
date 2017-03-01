package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CadreStatsVO implements java.io.Serializable{

	private Long id;
	private String name;
	private String locationType;//own or participated
	private String districtPerc;
	private String parliamenPerc;
	private String assemblyPerc;
	private String mandalORMuncORUrbanPerc;
	private String panchayatORWardPerc;
	private String boothPerc;
	private String boothInfuPerc;
	private String year;
	private List<CadreStatsVO> subList = new ArrayList<CadreStatsVO>(0);
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
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
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getDistrictPerc() {
		return districtPerc;
	}
	public void setDistrictPerc(String districtPerc) {
		this.districtPerc = districtPerc;
	}
	public String getParliamenPerc() {
		return parliamenPerc;
	}
	public void setParliamenPerc(String parliamenPerc) {
		this.parliamenPerc = parliamenPerc;
	}
	public String getAssemblyPerc() {
		return assemblyPerc;
	}
	public void setAssemblyPerc(String assemblyPerc) {
		this.assemblyPerc = assemblyPerc;
	}
	public String getMandalORMuncORUrbanPerc() {
		return mandalORMuncORUrbanPerc;
	}
	public void setMandalORMuncORUrbanPerc(String mandalORMuncORUrbanPerc) {
		this.mandalORMuncORUrbanPerc = mandalORMuncORUrbanPerc;
	}
	public String getPanchayatORWardPerc() {
		return panchayatORWardPerc;
	}
	public void setPanchayatORWardPerc(String panchayatORWardPerc) {
		this.panchayatORWardPerc = panchayatORWardPerc;
	}
	public String getBoothPerc() {
		return boothPerc;
	}
	public void setBoothPerc(String boothPerc) {
		this.boothPerc = boothPerc;
	}
	public List<CadreStatsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CadreStatsVO> subList) {
		this.subList = subList;
	}
	public String getBoothInfuPerc() {
		return boothInfuPerc;
	}
	public void setBoothInfuPerc(String boothInfuPerc) {
		this.boothInfuPerc = boothInfuPerc;
	}
	
}
