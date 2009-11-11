package com.itgrids.partyanalyst.excel.booth;

public class BoothInfo {
	
	private String districtName;
	private Long districtId;
	private String partNo;
	private String partName;
	private String location;
	private String villagesCovered;
	private String mandalName;
	private Long maleVoters;
	private Long femaleVoters;
	private Long totalVoters;
	private String constituencyName;
	private Long constituencyNo;
	private String censusCode;
	
	public BoothInfo(){
		
	}

	public BoothInfo(String districtName, Long districtId, String partNo,
			String partName, String location, String villagesCovered,
			String mandalName, Long maleVoters, Long femaleVoters,
			Long totalVoters, String constituencyName, Long constituencyNo,
			String censusCode) {
		super();
		this.districtName = districtName;
		this.districtId = districtId;
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

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
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

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public Long getConstituencyNo() {
		return constituencyNo;
	}

	public void setConstituencyNo(Long constituencyNo) {
		this.constituencyNo = constituencyNo;
	}

	public String getCensusCode() {
		return censusCode;
	}

	public void setCensusCode(String censusCode) {
		this.censusCode = censusCode;
	}
	
		
}
