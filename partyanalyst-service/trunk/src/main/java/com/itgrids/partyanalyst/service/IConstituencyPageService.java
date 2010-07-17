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
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.HamletAndBoothVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.MandalAndRevenueVillagesInfoVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;

public interface IConstituencyPageService {

	public List<ConstituencyElectionResultsVO> getConstituencyElectionResults(Long constituencyId);
	  
	public ConstituencyInfoVO getConstituencyDetails(Long constituencyId);
	
	public ResultWithExceptionVO getAllMandalLevelLeaders(Long tehsilId);
	
	public MandalAndRevenueVillagesInfoVO getTownshipWiseBoothDetailsForTehsil(Long tehsilId, Long electionId);
	
	public ConstituencyVO getVotersInfoInMandalsForConstituency(Long constituencyId);
	
	public ResultWithExceptionVO saveAndUpdateHamletAndBoothInfo(HamletAndBoothVO hamletWithBoothIds);
	
	public ResultWithExceptionVO deleteVillageBoothElectionRecord(HamletAndBoothVO villageBoothElectionId);
	
	public CandidateDetailsForConstituencyTypesVO getCandidateAndPartyInfoForConstituency(Long constituencyId);

	public List<PartyVotesEarnedVO> getTownshipWiseElectionsForTehsil(Long townshipId, Long electionId);
	
	public List<ConstituencyRevenueVillagesVO> getPartiesResultsInVillagesGroupByMandal(Long tehsilId, Long electionId);
	
	public ConstituencyRevenueVillagesVO getConstituencyElecResults(Long constituencyId, String electionYear,Boolean includeOthers);
	
	public ConstituencyRevenueVillagesVO getMandalElectionInfoForAConstituency(Long constituencyId,String electionYear,String electionType,Boolean includeOthers);
		
	public List<ConstituencyRevenueVillagesVO> getMandalElectionInfoForAParliamentConstituency(Long constituencyId,String electionYear);
	
	public void getAssembliesVotersInfoOfParliament(ConstituencyVO constituencyVO);
	
	public List<TeshilPartyInfoVO> getPartyWiseZptcOrMptcElectionDataForAConstituency(Long constituencyId,String electionYear,String electionType,String constituencyType);
	
	public MandalAllElectionDetailsVO getAllTehsilElectionLevelWinnersForAConstituency(Long constituencyId,String candidateDetailsType,Long partyId,String electionType,String electionYear,String constituencyType);

	public List<TeshilPartyInfoVO> getTehsilPartyInfoForAConstituency(StringBuilder tehsilIds,String electionYear,String electionType,Long constituencyId);
	
	public ConstituencyRevenueVillagesVO getRevenuevillagesWiseElectionResultsOfPartyInMandal(Long partyId, Long tehsilId);
	
	public List<PartyResultVO> getMandalsResultsInAnElection(String mandalIds, String electionYear, String electionType);
	
	public List<PartyElectionResultVO> OtherVotesDataForAConstituency(Long constituencyId,String electionYear,String electionType);
	
	
}
