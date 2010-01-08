/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 2, 2010
 */
package com.itgrids.partyanalyst.excel.problem;

import java.util.List;

public class PollingBoothPartVO {

	private String partNo;
	private String partName;
	private List<HamletProblemUploadVO> hamletProblemUploadVO;
	
	//No Argument Constructor
	public PollingBoothPartVO(){
		
	}

	// Getters And Setters
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

	public List<HamletProblemUploadVO> getHamletProblemUploadVO() {
		return hamletProblemUploadVO;
	}

	public void setHamletProblemUploadVO(
			List<HamletProblemUploadVO> hamletProblemUploadVO) {
		this.hamletProblemUploadVO = hamletProblemUploadVO;
	}
}
