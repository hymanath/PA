package com.itgrids.partyanalyst.excel;

public class BoothInfo {
	
	private String districtName;
	private String partNo;
	private String partName;
	private String location;
	private String villagesCovered;
	private String mandalName;
	private String maleVoters;
	private String femaleVoters;
	private String totalVoters;
	private String constituencyName;
	private String constituencyNo;
	private String censusCode;
	
	public BoothInfo(){
		
	}
	
	public BoothInfo(String districtName, String partNo,
			String partName, String location, String villagesCovered,
			String mandalName, String maleVoters, String femaleVoters,
			String totalVoters, String constituencyName, String constituencyNo,
			String censusCode) {
		super();
		this.districtName = districtName;
		this.partNo = partNo;
		this.partName = partName;
		this.location = location;
		this.villagesCovered = villagesCovered;
		this.mandalName = mandalName;
		this.maleVoters = maleVoters;
		this.femaleVoters = femaleVoters;
		this.totalVoters = totalVoters;
		this.constituencyName = constituencyName;
		this.constituencyNo = constituencyNo;
		this.censusCode = censusCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getVillagesCovered() {
		return villagesCovered;
	}

	public void setVillagesCovered(String villagesCovered) {
		this.villagesCovered = villagesCovered;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public String getMaleVoters() {
		return maleVoters;
	}

	public void setMaleVoters(String maleVoters) {
		this.maleVoters = maleVoters;
	}

	public String getFemaleVoters() {
		return femaleVoters;
	}

	public void setFemaleVoters(String femaleVoters) {
		this.femaleVoters = femaleVoters;
	}

	public String getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(String totalVoters) {
		this.totalVoters = totalVoters;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getConstituencyNo() {
		return constituencyNo;
	}

	public void setConstituencyNo(String constituencyNo) {
		this.constituencyNo = constituencyNo;
	}

	public String getCensusCode() {
		return censusCode;
	}

	public void setCensusCode(String censusCode) {
		this.censusCode = censusCode;
	}
	
		
}
