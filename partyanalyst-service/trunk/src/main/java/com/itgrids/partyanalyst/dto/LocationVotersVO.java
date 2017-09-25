package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class LocationVotersVO {
	public Long ageRangeId;
	public String ageRange;
	public Long totalVoters=0l;
	public String totalVotersPerc="";
	public Long totalCadres=0l;
	public String totalCadrePerc="";
	public Long maleVoters=0l;
	public String maleVotersPerc="";
	public Long maleCadres=0l;
	public String maleCadrePerc="";
	public Long femaleVoters=0l;
	public String femaleVotersPerc="";
	public Long femaleCadres=0l;
	public String femaleCadrePerc="";
	public List<LocationVotersVO> locationVotersVOList = new ArrayList<LocationVotersVO>(0);
	public List<LocationVotersVO> locationVotersVOList1 = new ArrayList<LocationVotersVO>(0);
	public Long casteGroupId;
	public String castgroup;
	public Double voterPercentage=0.0d;

	public Long getAgeRangeId() {
		return ageRangeId;
	}
	public void setAgeRangeId(Long ageRangeId) {
		this.ageRangeId = ageRangeId;
	}
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public String getTotalVotersPerc() {
		return totalVotersPerc;
	}
	public void setTotalVotersPerc(String totalVotersPerc) {
		this.totalVotersPerc = totalVotersPerc;
	}
	public Long getMaleVoters() {
		return maleVoters;
	}
	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}
	
	public Long getFemaleVoters() {
		return femaleVoters;
	}
	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}
	public String getMaleVotersPerc() {
		return maleVotersPerc;
	}
	public void setMaleVotersPerc(String maleVotersPerc) {
		this.maleVotersPerc = maleVotersPerc;
	}
	public String getFemaleVotersPerc() {
		return femaleVotersPerc;
	}
	public void setFemaleVotersPerc(String femaleVotersPerc) {
		this.femaleVotersPerc = femaleVotersPerc;
	}
	public Long getTotalCadres() {
		return totalCadres;
	}
	public void setTotalCadres(Long totalCadres) {
		this.totalCadres = totalCadres;
	}
	public String getTotalCadrePerc() {
		return totalCadrePerc;
	}
	public void setTotalCadrePerc(String totalCadrePerc) {
		this.totalCadrePerc = totalCadrePerc;
	}
	public Long getMaleCadres() {
		return maleCadres;
	}
	public void setMaleCadres(Long maleCadres) {
		this.maleCadres = maleCadres;
	}
	public String getMaleCadrePerc() {
		return maleCadrePerc;
	}
	public void setMaleCadrePerc(String maleCadrePerc) {
		this.maleCadrePerc = maleCadrePerc;
	}
	public Long getFemaleCadres() {
		return femaleCadres;
	}
	public void setFemaleCadres(Long femaleCadres) {
		this.femaleCadres = femaleCadres;
	}
	public String getFemaleCadrePerc() {
		return femaleCadrePerc;
	}
	public void setFemaleCadrePerc(String femaleCadrePerc) {
		this.femaleCadrePerc = femaleCadrePerc;
	}
	public List<LocationVotersVO> getLocationVotersVOList() {
		return locationVotersVOList;
	}
	public void setLocationVotersVOList(List<LocationVotersVO> locationVotersVOList) {
		this.locationVotersVOList = locationVotersVOList;
	}
	public List<LocationVotersVO> getLocationVotersVOList1() {
		return locationVotersVOList1;
	}
	public void setLocationVotersVOList1(
			List<LocationVotersVO> locationVotersVOList1) {
		this.locationVotersVOList1 = locationVotersVOList1;
	}
	public Long getCasteGroupId() {
		return casteGroupId;
	}
	public void setCasteGroupId(Long casteGroupId) {
		this.casteGroupId = casteGroupId;
	}
	public String getCastgroup() {
		return castgroup;
	}
	public void setCastgroup(String castgroup) {
		this.castgroup = castgroup;
	}
	public Double getVoterPercentage() {
		return voterPercentage;
	}
	public void setVoterPercentage(Double voterPercentage) {
		this.voterPercentage = voterPercentage;
	}
	
	
}
