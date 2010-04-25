/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 24,2010
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ElectionBasicCommentsVO;
import com.itgrids.partyanalyst.dto.PartyAnalysisReportVO;

public interface IAnalysisReportService {
	
    public PartyAnalysisReportVO getAnalysisReportForAPartyInAnElection(String electionType,String electionYear,Long stateId,Long partyId);
    
    public List<ElectionBasicCommentsVO> getCandidateCommentDetailsInAnElection(Long electionId,Long partyId);
    
 	
}
