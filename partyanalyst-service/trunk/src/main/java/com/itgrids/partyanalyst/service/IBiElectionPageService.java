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
import java.util.Set;

import com.itgrids.partyanalyst.dto.AllBoothsResultsForAPartyInAMandal;
import com.itgrids.partyanalyst.dto.BiElectionDistrictVO;
import com.itgrids.partyanalyst.dto.BiElectionResultsMainVO;
import com.itgrids.partyanalyst.dto.BiElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.ElectionResultsForMandalVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.MandalElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyTownshipResultsVO;
import com.itgrids.partyanalyst.dto.PartyVillageLevelAnalysisVO;
import com.itgrids.partyanalyst.dto.PartyVotesMarginResultsInMandal;
import com.itgrids.partyanalyst.dto.VotesMarginResultsMainVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;

public interface IBiElectionPageService {

	public List<BiElectionDistrictVO> getBiElectionConstituenciesDistrictWise();
	
	public MandalElectionResultVO getElectionResultForPartyInMandal(Long mandalId,String electionType,String electionYear);
	
	public ElectionResultsForMandalVO getElectionResultsForMandalsInAConstituencyForAElection(List<Long> mandalIds,String electionType,String electionYear);
	
	public List<BiElectionResultsVO> getMandalWiseResultsForAConstituency(Long constituencyId);
	
	public Map<String,Map<Long,List<BoothResultVO>>> getPartyMarginResultsInAMandalForAllElections(Long tehsilId,Long constiId,Long partyId,String electionYear,String electionType,String partyType,Long rank) throws Exception;
	
	public Map<String,Map<String,Map<Long,List<BoothResultVO>>>> getPartyVotesMarginResults(Long constituencyId,Long mandalId,Long partyId) throws Exception;
	
	public PartyVotesMarginResultsInMandal getBoothWisePartyResultsForAMandal(Long constituencyId,Long mandalId,Long partyId);
	
	public VotesMarginResultsMainVO getVotesMarginResultsCompleteDetails(Long constituencyId,Long partyId);
	
	public AllBoothsResultsForAPartyInAMandal getAllBoothsResultsInAConstituency(Long tehsilId, Long partyId, Long constituencyId, String electionYear, String electionType);
	
	public BiElectionResultsMainVO getMandalWiseResultsForSelectedPartiesInConstituency(Long constituencyId);
	
	public List<PartyResultVO> findRevenueVillageswiseResultsInElectionsOfMandal(Long tehsilId, String parties, 
			String elections, Boolean includeAlliance );	
	
	public Map<Long,List<PartyTownshipResultsVO>> getTownshipWiseAllPartyResults(Long tehsilId,String electionType,String electionYear,Boolean isPanchayatWise);
	
	public List<PartyVillageLevelAnalysisVO> villageLevelPArtyAnalysis(Long tehsilId,String electionType,String electionYear,int rank,Boolean isPanchayatWise);
	
	public ElectionWiseMandalPartyResultListVO getResultsOfRuralUrbanAreaBeasedOnSelection(Long constituencyId, 
			Set<String> parties, Set<String> elecTypeOrYear, Boolean isElecType, Boolean includeAlliance);
	
	public List<ConstituencyVO> getAllTelanganaConstituencieswisePartiesResultsBasedOnExpectedPercentage(String expePercent,Boolean includeLocalElec);
	
	public List<ConstituencyElectionResultsVO> getMainPartiesResultsInConstituency(Long constituencyId, String constituencyName); 
}
