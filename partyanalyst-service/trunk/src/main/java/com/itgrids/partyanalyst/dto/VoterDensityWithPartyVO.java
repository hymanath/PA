package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class VoterDensityWithPartyVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long count = 0l;
	private Long minValue;
	private Long maxValue;
	private String type;
	
	private Long partyId;
	private String partyName;
	private List<VoterDensityWithPartyVO> partyWiseList;
	private List<VoterDensityWithPartyVO> densityList;
	private List<Long> panchayatIds;
	private List<Long> partyIds;
	
	private List<Long> ananymousPanchayatsIds;
	private List<VoterDensityWithPartyVO> ananymousDensity;
	
	private String mainHeading;
	private String subHeading;
	private String information;
	private String ananymousPanchayats;
	
	private String information2;
	
	
	
	public String getInformation2() {
		return information2;
	}
	public void setInformation2(String information2) {
		this.information2 = information2;
	}
	public String getAnanymousPanchayats() {
		return ananymousPanchayats;
	}
	public void setAnanymousPanchayats(String ananymousPanchayats) {
		this.ananymousPanchayats = ananymousPanchayats;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getMainHeading() {
		return mainHeading;
	}
	public void setMainHeading(String mainHeading) {
		this.mainHeading = mainHeading;
	}
	public String getSubHeading() {
		return subHeading;
	}
	public void setSubHeading(String subHeading) {
		this.subHeading = subHeading;
	}
	public List<VoterDensityWithPartyVO> getAnanymousDensity() {
		return ananymousDensity;
	}
	public void setAnanymousDensity(List<VoterDensityWithPartyVO> ananymousDensity) {
		this.ananymousDensity = ananymousDensity;
	}
	public List<Long> getAnanymousPanchayatsIds() {
		return ananymousPanchayatsIds;
	}
	public void setAnanymousPanchayatsIds(List<Long> ananymousPanchayatsIds) {
		this.ananymousPanchayatsIds = ananymousPanchayatsIds;
	}
	public List<Long> getPanchayatIds() {
		return panchayatIds;
	}
	public void setPanchayatIds(List<Long> panchayatIds) {
		this.panchayatIds = panchayatIds;
	}
	public List<Long> getPartyIds() {
		return partyIds;
	}
	public void setPartyIds(List<Long> partyIds) {
		this.partyIds = partyIds;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getMinValue() {
		return minValue;
	}
	public void setMinValue(Long minValue) {
		this.minValue = minValue;
	}
	public Long getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public List<VoterDensityWithPartyVO> getPartyWiseList() {
		return partyWiseList;
	}
	public void setPartyWiseList(List<VoterDensityWithPartyVO> partyWiseList) {
		this.partyWiseList = partyWiseList;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public List<VoterDensityWithPartyVO> getDensityList() {
		return densityList;
	}
	public void setDensityList(List<VoterDensityWithPartyVO> densityList) {
		this.densityList = densityList;
	}
	
	
	
}
