/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 2, 2010
 */
package com.itgrids.partyanalyst.excel.problem;

import java.util.List;

public class ProblemDataUploadVO {

	private String mandalName;
	private String constituencyName;
	private String townshipName;
	private String areaType;
	private String problemDate;
	private String fileName;
	private Long districtId;
	private List<PollingBoothPartVO> pollingBoothPartVO;
	
	//No Argument Constructor
	public ProblemDataUploadVO(){
		
	}

	// Getters And Setters
	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getTownshipName() {
		return townshipName;
	}

	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getProblemDate() {
		return problemDate;
	}

	public void setProblemDate(String problemDate) {
		this.problemDate = problemDate;
	}

	public List<PollingBoothPartVO> getPollingBoothPartVO() {
		return pollingBoothPartVO;
	}

	public void setPollingBoothPartVO(List<PollingBoothPartVO> pollingBoothPartVO) {
		this.pollingBoothPartVO = pollingBoothPartVO;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	
}
