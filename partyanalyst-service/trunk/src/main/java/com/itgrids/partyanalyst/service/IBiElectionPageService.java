/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 10, 2010
 */
package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.BiElectionDistrictVO;
import com.itgrids.partyanalyst.dto.BiElectionResultsVO;
import com.itgrids.partyanalyst.dto.ElectionResultsForMandalVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionResultsVO;
import com.itgrids.partyanalyst.dto.MandalElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyVotesMarginResultsInMandal;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotesMarginResultsMainVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;

public interface IBiElectionPageService {

	public List<BiElectionDistrictVO> getBiElectionConstituenciesDistrictWise();
	
	public MandalElectionResultVO getElectionResultForPartyInMandal(Long mandalId,String electionType,String electionYear);
	
	public ElectionResultsForMandalVO getElectionResultsForMandalsInAConstituencyForAElection(List<Long> mandalIds,String electionType,String electionYear);
	
	public List<BiElectionResultsVO> getMandalWiseResultsForAConstituency(Long constituencyId);
	
	public Map<String,Map<Long,List<BoothResultVO>>> getPartyMarginResultsInAMandalForAllElections(Long tehsilId,Long partyId,String electionYear,String electionType,String partyType,Long rank) throws Exception;
	
	public Map<String,Map<String,Map<Long,List<BoothResultVO>>>> getPartyVotesMarginResults(Long constituencyId,Long mandalId,Long partyId) throws Exception;
	
	public PartyVotesMarginResultsInMandal getBoothWisePartyResultsForAMandal(Long constituencyId,Long mandalId,Long partyId);
	
	public VotesMarginResultsMainVO getVotesMarginResultsCompleteDetails(Long constituencyId,Long partyId);
}
