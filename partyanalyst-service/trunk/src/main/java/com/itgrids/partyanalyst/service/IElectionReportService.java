/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 31,2010
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ConstituencyUrbanDetailsVO;
import com.itgrids.partyanalyst.dto.ElectionResultsReportVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;

public interface IElectionReportService {

	public ElectionResultsReportVO getBasicResultsForAnElection(String electionType,String electionYear,Long stateId,String votesPercentMargin);
	
	public PartyPositionsVO getCompleteStatewiseVotersInfoForAnElection(Long electionId);
	
	public List<PartyPositionsVO> getVotersDataOfTwoElections(Long electionId);
	
	public List<PartyElectionResultVO> getPartyBasicDetailsWithGenderInfoForAnElection(Long electionId);
	
	public List<PartyElectionResultVO> getPartyElectionResultWithConstituencyAreaType(Long electionId);
		
	public List<PartyElectionResultVO> getTopVotesMarginVotesDetails(Long electionId,int maxResult,String type,Long partyId);
	
	public List<ConstituencyUrbanDetailsVO> getConstituencyAreaTypePercentageWiseElectionResultOfParties(Long electionId,String censusYear,List<Long> partiesList);
	
}
