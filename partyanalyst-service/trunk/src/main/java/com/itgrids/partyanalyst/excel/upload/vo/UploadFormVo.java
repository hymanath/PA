package com.itgrids.partyanalyst.excel.upload.vo;

import java.io.File;
import java.util.List;

import com.itgrids.partyanalyst.excel.ConstituencyBlock;
import com.itgrids.partyanalyst.model.Constituency;

public class UploadFormVo {
	private File inputFile;
	private String electionType;
	private String electionScope;
	private String district;
	private String country;
	private String electionYear;
	private String elecSubtype;
	private List<ConstituencyBlock> constituencyBlocks;
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
	public List<ConstituencyBlock> getConstituencyBlocks() {
		return constituencyBlocks;
	}
	public void setConstituencyBlocks(List<ConstituencyBlock> constituencyBlocks) {
		this.constituencyBlocks = constituencyBlocks;
	}
	public File getInputFile() {
		return inputFile;
	}
	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}
	public String getElecSubtype() {
		return elecSubtype;
	}
	public void setElecSubtype(String elecSubtype) {
		this.elecSubtype = elecSubtype;
	}
	
	
}
