/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 12, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class BiElectionResultsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long districtId;
	private String districtName;
	
	private List<ElectionResultsForMandalVO> biElectionResultsVO;

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public List<ElectionResultsForMandalVO> getBiElectionResultsVO() {
		return biElectionResultsVO;
	}

	public void setBiElectionResultsVO(
			List<ElectionResultsForMandalVO> biElectionResultsVO) {
		this.biElectionResultsVO = biElectionResultsVO;
	}

}
