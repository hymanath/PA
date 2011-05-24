/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 31,2010
 */
package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ElectionResultsReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;

public interface IElectionReportService {

	public ElectionResultsReportVO getBasicResultsForAnElection(String electionType,String electionYear,Long stateId,String votesPercentMargin);
	
	public PartyPositionsVO getCompleteStatewiseVotersInfoForAnElection(Long electionId);
	
}
