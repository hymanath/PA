package com.itgrids.partyanalyst.excel.booth;

public class DataValidationVO {

	private Long tehsilId;
	private String tehsilName;
	private Long totalVoters =0l;
	
	private String panchayatName;
	
	private Long panchayatId;
	
	private Long hamletAssignedVoters =0l;
	
	private Long totalHamlets;
	
	private Long hamletsNotAssignedVoters;

	public Long getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}

	public Long getHamletAssignedVoters() {
		return hamletAssignedVoters;
	}

	public void setHamletAssignedVoters(Long hamletAssignedVoters) {
		this.hamletAssignedVoters = hamletAssignedVoters;
	}

	public Long getTotalHamlets() {
		return totalHamlets;
	}

	public void setTotalHamlets(Long totalHamlets) {
		this.totalHamlets = totalHamlets;
	}

	public Long getHamletsNotAssignedVoters() {
		return hamletsNotAssignedVoters;
	}

	public void setHamletsNotAssignedVoters(Long hamletsNotAssignedVoters) {
		this.hamletsNotAssignedVoters = hamletsNotAssignedVoters;
	}
	
	
	
	
}
