/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 24, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.HamletAndBoothVO;
import com.itgrids.partyanalyst.dto.MandalAndRevenueVillagesInfoVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;

public interface IConstituencyPageService {

	public List<ConstituencyElectionResultsVO> getConstituencyElectionResults(Long constituencyId);
	  
	public ConstituencyInfoVO getConstituencyDetails(Long constituencyId);
	
	public ResultWithExceptionVO getAllMandalLevelLeaders(Long tehsilId);
	
	public MandalAndRevenueVillagesInfoVO getTownshipWiseBoothDetailsForTehsil(Long tehsilId, Long electionId);
	
	public List<VotersWithDelimitationInfoVO> getVotersInfoInMandalsForConstituency(Long constituencyId);
	
	public ResultWithExceptionVO saveAndUpdateHamletAndBoothInfo(HamletAndBoothVO hamletWithBoothIds);
	
	public ResultWithExceptionVO deleteVillageBoothElectionRecord(HamletAndBoothVO villageBoothElectionId);
	
	public CandidateDetailsForConstituencyTypesVO getCandidateAndPartyInfoForConstituency(Long constituencyId);

	public List<PartyVotesEarnedVO> getTownshipWiseElectionsForTehsil(Long townshipId, Long electionId);
	
	public List<ConstituencyRevenueVillagesVO> getPartiesResultsInVillagesGroupByMandal(Long tehsilId, Long electionId);
	
	public ConstituencyRevenueVillagesVO getMandalElectionInfoForAConstituency(Long constituencyId,String electionYear);
}
