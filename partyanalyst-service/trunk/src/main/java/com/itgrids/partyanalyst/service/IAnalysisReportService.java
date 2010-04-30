/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 24,2010
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionBasicCommentsVO;
import com.itgrids.partyanalyst.dto.PartyAnalysisReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionAnalysisResultVO;

public interface IAnalysisReportService {
	
    public PartyAnalysisReportVO getAnalysisReportForAPartyInAnElection(String electionType,String electionYear,Long stateId,Long partyId);
    
    public List<ElectionBasicCommentsVO> getCandidateCommentDetailsInAnElection(Long electionId,Long partyId);
    
 	public PartyPositionAnalysisResultVO getAnalysisCategoryResultForAPartyInAnElection(String electionType,String electionYear,Long electionId,Long stateId,Long partyId,String analysisCategory,Boolean includeAllianc);
 	
 	public List<CandidateElectionResultVO> getElectionResultsForNotAnalyzedConstituencies(Long electionId, Long partyId,Long stateId);
}
