/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 15,2010.
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ComparedReportVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyResultVO;
import com.itgrids.partyanalyst.dto.ElectionComparisonReportVO;

public interface IElectionComparisonReportService {

	public ElectionComparisonReportVO getDistrictWiseElectionResultsForAParty(Long electionType,Long partyId,Long stateId,String firstYear,String secondYear,Boolean hasAlliances);
	
	public ComparedReportVO getComparedElectionResults(Long electionType,Long stateId,Long partyId,String yearOne,String yearTwo,Long districtId,Boolean hasAlliances);
}
