/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 13,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author Sai Krishna
 *
 */
public class DelimitationMappingUploadVO extends ResultStatus implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String delimitationYear;
	private Set<String> mappedConstituencies;
	private Integer totalConstiMappedCount = 0;
	private Integer currentSheet = 0;
	private Integer currentRow = 0;
	private Boolean exceptionEncounterStatus;
	
	private List<DelimitationUploadValidationVO> delimitationUploadValidationVO;
	
	/**
	 * @return the delimitationYear
	 */
	public String getDelimitationYear() {
		return delimitationYear;
	}
	/**
	 * @param delimitationYear the delimitationYear to set
	 */
	public void setDelimitationYear(String delimitationYear) {
		this.delimitationYear = delimitationYear;
	}
	/**
	 * @return the mappedConstituencies
	 */
	public Set<String> getMappedConstituencies() {
		return mappedConstituencies;
	}
	/**
	 * @param mappedConstituencies the mappedConstituencies to set
	 */
	public void setMappedConstituencies(Set<String> mappedConstituencies) {
		this.mappedConstituencies = mappedConstituencies;
	}
	/**
	 * @return the totalConstiMappedCount
	 */
	public Integer getTotalConstiMappedCount() {
		return totalConstiMappedCount;
	}
	/**
	 * @param totalConstiMappedCount the totalConstiMappedCount to set
	 */
	public void setTotalConstiMappedCount(Integer totalConstiMappedCount) {
		this.totalConstiMappedCount = totalConstiMappedCount;
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
	 * @return the currentRow
	 */
	public Integer getCurrentRow() {
		return currentRow;
	}
	/**
	 * @param currentRow the currentRow to set
	 */
	public void setCurrentRow(Integer currentRow) {
		this.currentRow = currentRow;
	}
	/**
	 * @return the exceptionEncounterStatus
	 */
	public Boolean getExceptionEncounterStatus() {
		return exceptionEncounterStatus;
	}
	/**
	 * @param exceptionEncounterStatus the exceptionEncounterStatus to set
	 */
	public void setExceptionEncounterStatus(Boolean exceptionEncounterStatus) {
		this.exceptionEncounterStatus = exceptionEncounterStatus;
	}
	/**
	 * @return the delimitationUploadValidationVO
	 */
	public List<DelimitationUploadValidationVO> getDelimitationUploadValidationVO() {
		return delimitationUploadValidationVO;
	}
	/**
	 * @param delimitationUploadValidationVO the delimitationUploadValidationVO to set
	 */
	public void setDelimitationUploadValidationVO(
			List<DelimitationUploadValidationVO> delimitationUploadValidationVO) {
		this.delimitationUploadValidationVO = delimitationUploadValidationVO;
	}
		
    
}
