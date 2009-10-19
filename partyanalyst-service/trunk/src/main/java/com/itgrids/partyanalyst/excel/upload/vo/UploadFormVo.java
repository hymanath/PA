package com.itgrids.partyanalyst.excel.upload.vo;

import java.util.List;

import com.itgrids.partyanalyst.excel.ConstituencyBlock;
import com.itgrids.partyanalyst.model.Constituency;

public class UploadFormVo {
	private String electionType;
	private String electionScope;
	private String inputFile;
	private String district;
	private String country;
	private String electionYear;
	private List<ConstituencyBlock> constituencies;
	public UploadFormVo(){
		
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getElectionScope() {
		return electionScope;
	}
	public void setElectionScope(String electionScope) {
		this.electionScope = electionScope;
	}
	public String getInputFile() {
		return inputFile;
	}
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public List<ConstituencyBlock> getConstituencies() {
		return constituencies;
	}
	public void setConstituencies(List<ConstituencyBlock> constituencies) {
		this.constituencies = constituencies;
	}
}
