package com.itgrids.partyanalyst.service;


import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.DelimitationEffectVO;
import com.itgrids.partyanalyst.dto.ExceptCastsVO;
import com.itgrids.partyanalyst.dto.PanchayatVO;
import com.itgrids.partyanalyst.dto.PartyImpactVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.PartyTrendsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SuggestedLocationsVO;
import com.itgrids.partyanalyst.dto.VoterCountVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.dto.YouthLeaderSelectionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
public interface ISuggestiveModelService {
	
	public List<SelectOptionVO> getConstituenciesForUserAccessByStateId(List<SelectOptionVO> ConstituenciesForUserAccessed,Long electionId,Long electionYear);
	
	public List<SelectOptionVO> getPartyDetailsByMandal(Long mandalId);
	
	public List<SelectOptionVO> getElectionIdsAndYearsBytehsilId(List<Long> electionScope,Long partyId,Long tehsilId);
	
	public List<PartyPositionVO> getPartyPerformenceReport(Long constituencyId,Long partyId,Long locationId,String locationType,List<Long> electionId,String tempVar);
	
	public List<YouthLeaderSelectionVO> findingBoothInchargesForBoothLevel(Long userid,Long constituencyId,List<Long> casteIdsList,Map<Long,Double> casteMap,List<ExceptCastsVO> expCaseList,boolean expCaste,Long publicationId,List<ExceptCastsVO> expCaseListMuncipal);
	
	public List<PanchayatVO> getVotersGroupDetails(List<SelectOptionVO> groupVos,Long constituencyId,Long locationId,String type,List<Long> electionIds,Long userId,List<Long> casteIds,List<ExceptCastsVO> exptdCastes,List<ExceptCastsVO> exptdCastesMncpl);
	
	public List<SelectOptionVO> getUserAssignedVotersCasteDetailsByConstId(Long constituencyId,Long userId,Long publicationId);
	
	 public List<YouthLeaderSelectionVO> findingBoothInchargesForBoothLevelForMincipality(Long userId,Long constituencyId,List<Long> casteIdsList ,List<ExceptCastsVO> expCasteDetails,Boolean checkStatus,Long publicationId);
	
	public Map<String ,Map<String,PartyImpactVO>> getElectionResultsForSelectedElectionsForAConsttituency(Long constituencyId,String PartyName,Long publicationId);

	public List<PartyPositionVO> getPartyPerformenceReport1(Long constituencyId,Long partyId,List<Long> electionIds,String tempVar);
	
	public List<BasicVO> getHamletDetailsByPanchayatIds(Long constituencyId,Long publicationId,Long userId,List<Long> candidateCastes);
	
	public List<SelectOptionVO> getCasteAvaliableConstituencysService(List<SelectOptionVO> ConstituenciesForUserAccessed,Long electionId,Long electionYear,Long userId);
	
	public List<PartyPositionVO> getPollingPercentagesByParty(Long constituenycId,Long partyId,Long electionId,Long electionId1);
	 
	 public List<VoterVO> getAddedVotersDetailsByPartNo(Long ConstituencyId,Long partNo,Integer startIndex,Integer maxIndex);

	public List<VoterDataVO> getMandalsAndPanchayats(Long constituencyId);
	
	 
	 public List<YouthLeaderSelectionVO> getLeadersInUrbanConstituencyes(Long userId,Long constituencyId,List<Long> casteIdsList ,List<ExceptCastsVO> expCasteDetails,Boolean checkStatus,Long publicationId);
	 
	 public String getConstituencyType(Long constituencyId);
	 
	 public List<VoterCountVO> getVotersCountInPanchayats(Long constituencyId,Long publicationId);
	 
	 public List<SelectOptionVO> getSelectedCountPAnchayatsDetails(Long constituencyId,Long minValue,Long maxValue);
	 
	 public VotersInfoForMandalVO getVotersCount(Long constituencyId,Long publicationDateId,String locationType);
	 
	 public DelimitationEffectVO getDelimationEffectOnConstituency(Long constituencyId,Long partyId);
	 
	 public Long getLatestPublicationByConstituency(Long constituencyId);

	 public List<PartyTrendsVO> calculateOrderOfPriorityForConstituency(Long userId,List<Long> constituencyIds,List<Long> casteIdsList,List<ExceptCastsVO> exceptCasteList,List<SelectOptionVO> groups,List<ExceptCastsVO> exceptCasteMncplList,String party,List<Long> electionIds,Long partyId,SuggestedLocationsVO weigthPerc);
	 
	 public List<VoterHouseInfoVO> getFamilyDetailsForConstituency(Long constituencyId,Long publicationId,Long minValue,Long maxValue,Integer startIndex,Integer maxIndex,Long userId);
	 
	// public List<VoterHouseInfoVO> getFamilyDetailsForConstituency(Long constituencyId,Long publicationId,Long minValue,Long maxValue,Integer startIndex,Integer maxIndex,String path);
	 

}
