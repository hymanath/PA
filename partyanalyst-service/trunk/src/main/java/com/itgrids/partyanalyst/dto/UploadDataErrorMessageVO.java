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
	
	private Integer totalRows;
	private Integer currentSheet;
	private Integer totalSheets;
	private Integer totalRowsExecuted;
	private String uploadStatus;
	
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

	/**
	 * @return the totalRows
	 */
	public Integer getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @return the currentSheet
	 */
	public Integer getCurrentSheet() {
		return currentSheet;
	}

	/**
	 * @param currentSheet the currentSheet to set
	 */
	public void setCurrentSheet(Integer currentSheet) {
		this.currentSheet = currentSheet;
	}

	/**
	 * @return the totalSheets
	 */
	public Integer getTotalSheets() {
		return totalSheets;
	}

	/**
	 * @param totalSheets the totalSheets to set
	 */
	public void setTotalSheets(Integer totalSheets) {
		this.totalSheets = totalSheets;
	}

	/**
	 * @return the totalRowsExecuted
	 */
	public Integer getTotalRowsExecuted() {
		return totalRowsExecuted;
	}

	/**
	 * @param totalRowsExecuted the totalRowsExecuted to set
	 */
	public void setTotalRowsExecuted(Integer totalRowsExecuted) {
		this.totalRowsExecuted = totalRowsExecuted;
	}

	/**
	 * @return the uploadStatus
	 */
	public String getUploadStatus() {
		return uploadStatus;
	}

	/**
	 * @param uploadStatus the uploadStatus to set
	 */
	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	
	
}
