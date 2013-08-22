package com.itgrids.partyanalyst.service;


import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.PanchayatVO;
import com.itgrids.partyanalyst.dto.PartyImpactVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.YouthLeaderSelectionVO;
public interface ISuggestiveModelService {
	
	public List<SelectOptionVO> getConstituenciesForUserAccessByStateId(List<SelectOptionVO> ConstituenciesForUserAccessed,Long electionId,Long electionYear);
	
	public List<SelectOptionVO> getPartyDetailsByMandal(Long mandalId);
	
	public List<SelectOptionVO> getElectionIdsAndYearsBytehsilId(List<Long> electionScope,Long partyId,Long tehsilId);
	
	public List<PartyPositionVO> getPartyPerformenceReport(Long constituencyId,Long partyId,Long locationId,String locationType,List<Long> electionId,String tempVar);
	
	public List<YouthLeaderSelectionVO> findingBoothInchargesForBoothLevel(Long userid,Long constituencyId,List<Long> casteIdsList);
	
	public List<PanchayatVO> getVotersGroupDetails(List<SelectOptionVO> groupVos,Long constituencyId,Long locationId,String type,List<Long> electionIds,Long userId,List<Long> casteIds);
	
	public List<SelectOptionVO> getUserAssignedVotersCasteDetailsByConstId(Long constituencyId,Long userId);
	
	public List<YouthLeaderSelectionVO> findingBoothInchargesForBoothLevelForMincipality(Long userId,Long constituencyId,List<Long> casteIdsList);
	
	public Map<String ,Map<String,PartyImpactVO>> getElectionResultsForSelectedElectionsForAConsttituency(Long constituencyId,String PartyName);

	public List<PartyPositionVO> getPartyPerformenceReport1(Long constituencyId,Long partyId,List<Long> electionIds,String tempVar);
	
	public List<BasicVO> getHamletDetailsByPanchayatIds(Long constituencyId,Long publicationId,Long userId,List<Long> candidateCastes);
	
	public List<SelectOptionVO> getCasteAvaliableConstituencysService(List<SelectOptionVO> ConstituenciesForUserAccessed,Long electionId,Long electionYear,Long userId);
}
