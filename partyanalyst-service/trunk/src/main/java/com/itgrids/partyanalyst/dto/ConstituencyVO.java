package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;


public class ConstituencyVO {
	
	private Long id;
	private String name;
	private String stateName;
	private String districtName;
	private String electionType;
	private Date delemitationInfo;
	private List<VotersWithDelimitationInfoVO> assembliesOfParliamentInfo;
	private String[] pieChartNames;
	
	public ConstituencyVO(){
		
	}
	public ConstituencyVO(Long id, String name, String stateName, String districtName,
			String electionType, Date delemitationInfo) {
		this.id = id;
		this.name = name;
		this.stateName = stateName;
		this.districtName = districtName;
		this.electionType = electionType;
		this.delemitationInfo = delemitationInfo;
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
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public Date getDelemitationInfo() {
		return delemitationInfo;
	}
	public void setDelemitationInfo(Date delemitationInfo) {
		this.delemitationInfo = delemitationInfo;
	}
	public List<VotersWithDelimitationInfoVO> getAssembliesOfParliamentInfo() {
		return assembliesOfParliamentInfo;
	}
	public void setAssembliesOfParliamentInfo(
			List<VotersWithDelimitationInfoVO> assembliesOfParliamentInfo) {
		this.assembliesOfParliamentInfo = assembliesOfParliamentInfo;
	}
	public String[] getPieChartNames() {
		return pieChartNames;
	}
	public void setPieChartNames(String[] pieChartNames) {
		this.pieChartNames = pieChartNames;
	}
	
}
