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
import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyNominationsVO;
import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
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
	
	public ConstituencyRevenueVillagesVO getMandalElectionInfoForAConstituency(Long constituencyId,String electionYear,String electionType, Boolean includeOthers);
		
	public void getAssembliesVotersInfoOfParliament(ConstituencyVO constituencyVO);
	
	public List<TeshilPartyInfoVO> getPartyWiseZptcOrMptcElectionDataForAConstituency(Long constituencyId,String electionYear,String electionType,String constituencyType);
	
	public MandalAllElectionDetailsVO getAllTehsilElectionLevelWinnersForAConstituency(Long constituencyId,String candidateDetailsType,Long partyId,String electionType,String electionYear,String constituencyType);

	public List<TeshilPartyInfoVO> getTehsilPartyInfoForAConstituency(StringBuilder tehsilIds,String electionYear,String electionType,Long constituencyId);
	
	public ConstituencyRevenueVillagesVO getRevenuevillagesWiseElectionResultsOfPartyInMandal(Long partyId, Long tehsilId);
	
	public List<PartyResultVO> getMandalsResultsInAnElection(String mandalIds, String electionYear, String electionType);
	
	public List<PartyElectionResultVO> OtherVotesDataForAConstituency(Long constituencyId,String electionYear,String electionType);
	
	public DataTransferVO getPreviousAndPresentElectionYearsGraphsForAConstituency(Long constituencyId);
	
	public ConstituencyRevenueVillagesVO getMandalsResultsInAnElectionForChart(String mandalIds, String electionYear, String electionType);
	
	public List<CensusVO> getCensusDetailsForAssemblyConstituency(Long constituencyId,Long delimitationYear,Long censusYear);
	
	public List<Object[]> getLatestTownsForADelimitationConstituency(Long delimitationConstituencyId);
	
	public ConstituencyRevenueVillagesVO getMandalElectionInfoForAssemblyConstituencyForCensus(Long constituencyId,String electionYear,String electionType);
	
	public CensusVO getCensusDetailsForATehsil(Object[] params,Long censusYear,Long delimitationConstituencyId);
	
	public CensusVO getVillageWiseCensusDetailsForPartialTehsil(Long stateId,Long districtId,Long dcmId,Long censusYear);
	
	public List<CensusVO> getCensusDetailsOfATowmInAPartialTehsil(Long delimitationConstituencyId,Long mandalId,Long censusYear);
	
	public CensusVO getCensusDetailsForATown(Object[] params,Long censusYear);
	
	public CensusVO setCensusDetailsToVO(Object[] details);
	
	public String getWardIdsOfAPartialTownship(Long dctId);
	
	public String getVillageIdsOfAPartialTehsil(Long dcmId);
	
	public CensusVO addCensusDataToSingleVO(List<CensusVO> censusVOList);
	
	public  List<CensusVO> calculateCensusPercentage(List<CensusVO> censusVOList);
	
	public List<CensusVO> getCensusDetailsForAParliamentConstituency(Long parliamentId,Long electionYear,Long delimitationYear,Long censusYear);
	
	public List<CensusVO> removeMissingConstituencies(List<CensusVO> censusVOList,ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO);
	
	public List<CensusVO> setCensusVO(List<CensusVO> censusVOList,ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO);
	
	public boolean checkForConstituencyExistance(Long ConstituencyId);
	
	public List<Object[]> findConstituencyWiseCensusDetails(Long constituencyId,Long censusYear);
	
	public CensusVO getCompleteCensusDetailsForATehsil(Object[] params,Long censusYear,Long delimitationConstituencyId);
	
	public CensusVO setCompleteCensusDetailsToVO(Object[] details);
	
	public CensusVO getVillageWiseCompleteCensusDetailsForPartialTehsil(Long stateId,Long districtId,Long dcmId,Long censusYear);
	
	public int findNoOfParts(String str);
	
	public CensusVO setSumCompleteCensusDetailsToVO(Object[] details,int parts);
	
	public List<CensusVO> getCompleteCensusDetailsOfATownInAPartialTehsil(Long delimitationConstituencyId,Long mandalId,Long censusYear);
	
	public CensusVO mapConstituencyWiseCensusDetails(Long stateId,Long districtId,Long delimitationYear,Long censusYear,String mappingLevel,String update);
	
	public String saveCensusToConstituencyCensusDetails(final CensusVO censusVO,final Long constituencyId,final Long censusYear,final String update);
	
	public ConstituencyNominationsVO getCandidateNominationCompleteDetailsInConstituencyForLatestElection(Long constituencyId);
	
	public ConstituencyNominationsVO getCandidateNominations(Long constiElecId);
	
	public MandalAndRevenueVillagesInfoVO getPanchayatWiseBoothDetailsForTehsil(Long tehsilId, Long electionId);
	
	public List<PartyVotesEarnedVO> getPanchayatWiseElectionsForTehsil(String boothIdStr,Long electionId);
	
	public List<ConstituencyRevenueVillagesVO> getPartiesResultsInPanchayatsGroupByMandal(Long tehsilId, Long electionId);	
}
