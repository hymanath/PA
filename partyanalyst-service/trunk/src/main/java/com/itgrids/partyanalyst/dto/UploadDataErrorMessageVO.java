package com.itgrids.partyanalyst.dto;

import java.util.List;

public class UploadDataErrorMessageVO extends ResultStatus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> corrections;
	private List<Exception> exceptions;
	private List<String> constituenciesInfo;
	private List<String> districtsInfo;
	private List<String> tehsilsInfo;
	private List<String> townshipsInfo;
	private List<String> hamletsInfo;
	
	public UploadDataErrorMessageVO(){
		
	}
	
	public UploadDataErrorMessageVO(List<String> corrections,
			List<Exception> exceptions) {
		this.corrections = corrections;
		this.exceptions = exceptions;
	}

	public List<String> getCorrections() {
		return corrections;
	}
	public void setCorrections(List<String> corrections) {
		this.corrections = corrections;
	}
	public List<Exception> getExceptions() {
		return exceptions;
	}
	public void setExceptions(List<Exception> exceptions) {
		this.exceptions = exceptions;
	}

	public List<String> getConstituenciesInfo() {
		return constituenciesInfo;
	}

	public void setConstituenciesInfo(List<String> constituenciesInfo) {
		this.constituenciesInfo = constituenciesInfo;
	}

	public List<String> getDistrictsInfo() {
		return districtsInfo;
	}

	public void setDistrictsInfo(List<String> districtsInfo) {
		this.districtsInfo = districtsInfo;
	}

	public List<String> getTehsilsInfo() {
		return tehsilsInfo;
	}

	public void setTehsilsInfo(List<String> tehsilsInfo) {
		this.tehsilsInfo = tehsilsInfo;
	}

	public List<String> getTownshipsInfo() {
		return townshipsInfo;
	}

	public void setTownshipsInfo(List<String> townshipsInfo) {
		this.townshipsInfo = townshipsInfo;
	}

	public List<String> getHamletsInfo() {
		return hamletsInfo;
	}

	public void setHamletsInfo(List<String> hamletsInfo) {
		this.hamletsInfo = hamletsInfo;
	}
	
	
}
