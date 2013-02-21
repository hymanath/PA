package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.ImportantFamiliesInfoVo;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;

public interface IVotersAnalysisService {
	
	public List<SelectOptionVO> publicationDetailsBasedOnConstituency(Long constituencyId);
	
	public List<VoterVO> getVoterDetails(Long publicationDateId, Long boothId,
			Long panchayatId ,Integer startIndex , Integer maxRecords , String order,
			String columnName);
	
	public List<Long> getImpFamiles(Long id,Long publicationDateId,String name);
	
	public VoterCastInfoVO getVotersCastDetails(Long id,Long publicationDateId,String type);
	
	public VotersInfoForMandalVO getVotersCount(String type,Long id,Long publicationDateId,Long constituencyId);
	
	public  List<VoterCastInfoVO> getVotersCastDetailsForSubLevels(Long id,Long publicationDateId,String type,Long userId);
	
	public List<VotersDetailsVO> getVotersDetailsByAgewise(Long constituencyId, Long tehsilId,Long panchayatId,Long boothId,
			 Long publicationDateId , String type);
	public ImportantFamiliesInfoVo getImportantFamiliesInfo(String type,Long id,Long publicationDateId);
	
	//public List<VoterHouseInfoVO> getVoterHouseInfoDetails(Long id, Long publicationDateId,String checkedEle);
	public List<VoterHouseInfoVO> getVoterHouseInfoDetails(Long userId,Long id, Long publicationDateId,String checkedEle);
	
	public List<VoterHouseInfoVO> getFamilyInfo(Long boothId, Long publicationDateId,String houseNo);
	
	public VoterHouseInfoVO getBoothDetailsForVoter(Long boothId);

	public List<VotersDetailsVO>  getAgewiseVotersDetailsForPanchayatisByTehsilId(Long tehsilId,Long publicationDateId);
	public List<VotersDetailsVO> getAgewiseVotersDetailsForBoothsByPanchayatId(Long panchayatId,Long publicationDateId);
	public List<VotersDetailsVO> getAgewiseVotersDetailsForBoothsByLocalElectionBodyId(Long localElectionBodyId,Long publicationDateId);
	public List<VotersDetailsVO> getAgewiseVotersDetailsForTehsilsByConstituencyId(Long constituencyId,Long publicationDateId);
	
    //public List<VoterHouseInfoVO> getVoterDetailsByCaste(Long id,Long publicationDateId,String caste);
	public List<VoterHouseInfoVO> getVoterDetailsByCaste(Long id,Long publicationDateId,Long casteStateId,String type);

	public VoterHouseInfoVO getVoterPersonalDetailsByVoterId(Long voterId,Long user);
	
	public void updateVoterDetails(VoterHouseInfoVO voterHouseInfoVO,String partyCast);
	
	public ResultStatus insertVoterData(Long constituencyId,Long publicationDateId,Integer startIndex, Integer maxResults);
	
	public List<VoterHouseInfoVO> getUserCategoryValues();
	
	public List<SelectOptionVO> getVoterCategoryValues(Long voterCategoryId,String letters);
	
	public SelectOptionVO storeGroupName(Long userId ,String name);
	
	public List<SelectOptionVO> findVoterCategoryValues(Long userId);
	
	public SelectOptionVO storeCategoryVakues(Long userId,String name, Long id);
	
	public List<SelectOptionVO> getAllPublicationDates();
	
	public List<SelectOptionVO> getConstituenciesList();
	
	public VoterCastInfoVO getVotersCastWiseDetailsInALocation(Long userId,String locationType,Long locationId,Long publicationDateId);
	
	public List<SelectOptionVO> getcastCategoryGroups();
	
	public ResultStatus saveCasteName(Long userId, Long stateId, Long casteCategoryGroupId, String casteName);
	
	public List<SelectOptionVO> getBoothsInMunicipality(Long lclElecBodyId,Long publicationDateId);
	
	public ResultStatus updateVoterData(Long constituencyId,Integer startIndex, Integer maxResults);
	
	public List<VoterHouseInfoVO> getMultipleFamiliesInfo(List<VoterHouseInfoVO> familiesList);
	
	public VoterHouseInfoVO getVoterPersonalDetailsList(List<VoterHouseInfoVO> voterIds,Long userId);
	
	public boolean updateMultipleVoterDetails(List<VoterHouseInfoVO> voterHouseInfoVOs,String partyCast);
	
	public VoterHouseInfoVO getVotersInfoBySearchCriteria(VoterHouseInfoVO searchInfo,String type,Long id,List<Long> categories);
	
	public List<SelectOptionVO> getElectionIdAndTypeByPublicationId(Long publicationDateId);
	
	public List<PartyVotesEarnedVO> getPreviousElectionVotingTrends(Long id, Long publicationDateId,Long constituencyId, String type);

	public List<VotersDetailsVO> getCountList(Long publicationDateId,Long id,String type);
	
	public List<SelectOptionVO> getConstituenciesFromBoothPublicationVoter();
	
	public VoterHouseInfoVO getUserVoterCategories(Long userId);
	
	public VoterHouseInfoVO getSelectedCategoryOptions(VoterHouseInfoVO parameters);
	
	public VoterHouseInfoVO getSelectedCategoryOptionsForIndividual(List<VoterHouseInfoVO> voterIds,VoterHouseInfoVO parameters);
	
	public void updateSelectedFieldsForAllVoters(VoterHouseInfoVO voterHouseInfoVO,String[] voterIds,String partyCast);
	
	//public ResultStatus insertVotersDataToIntermediateTables(Long reportLevelValue, Long publicationDateId);
	
	public List<SelectOptionVO> getConstituencyList(List<SelectOptionVO> userAccessConstituencyList);
	
	public List<SelectOptionVO> getAllElectionsInAConsti(Long electionTypeId,Long constiId);
	
	//public List<VoterVO> getAllElectionAndPublicationsForConstituencyId(Long constituencyId);
	
	public List<VotersInfoForMandalVO> getPreviousVotersCountDetailsForAllLevels(
			 Long constituencyId,Long mandalId, Long panchayatId, Long boothId , String type);
	
	public ResultStatus insertVotersDataInIntermediateTables(Long reportLevelValue, Long publicationDateId);
	
	public List<VotersDetailsVO> getVoterAgeWiseDetails(Long constituencyId, Long mandalId,
			 Long panchayatId , Long boothId, Long publicationDateId, String type);
	
	public List<VotersDetailsVO> getAgewiseVotersDetForTehsilsByConstituencyId(Long constituencyId,Long publicationDateId, String type);
	
	public List<VotersDetailsVO> getAgewiseVotersDetaForPanchayatisByTehsilId(Long tehsilId,Long publicationDateId, String type);
	
	public List<VotersDetailsVO> getAgewiseVotersDetForBoothsByLocalElectionBodyId(Long localElectionBodyId,Long publicationDateId, String type);
	
	public List<VotersDetailsVO> getAgewiseVotersDetForBoothsByPanchayatId(Long panchayatId,Long publicationDateId, String type);

	public List<SelectOptionVO> getElectionYearsByMandalId(String type,Long mandalId);
	
	public CrossVotingConsolidateVO getCrossVotingReportByMandalIdAndEleYear(String type, Long id, String year, String includeAliance);
	
	public ResultStatus saveLocality(Long userId,Long hamletId,String name,Long localbody);

	public ResultStatus deleteVotersDataFromIntermediateTables(Long reportLevelValue, Long publicationDateId);
	
	public List<SelectOptionVO> getBoothsForConstituencyAndPublication(Long constituencyId,Long publicationId);
	
	public ResultStatus saveVoters(String name,String voterCardNo,String houseNo,String gaurdian,String relationShip,String gender,String mobileNo,Long age,Long boothId);
	
	public Long getParliamentConstituencyId(String type, Long id, Long year);
		
	public List<VotersDetailsVO> getAgewiseVotersDetForBoothsByWardId(Long id,Long publicationDateId);
	
	public List<VotersDetailsVO> getAgewiseVotersDetailsForWardsByLocalElectionBodyId(Long assemblyLocalBodyId,Long publicationDateId);
	
	public VoterCastInfoVO getCastWisePartyCount(Long userId,String locationType,Long locationId,Long publicationDateId);
	
	public VoterCastInfoVO getVotersPartyDetailsInALocation(Long userId,String locationType,Long locationId,Long publicationDateId);
	
	public ResultStatus moveVotersModificationDataFromTempToMainTable(Long constituencyId,Long publicationDateId);
	
	public List<SelectOptionVO> getConstituenciesToBeMappedForVoterChanges();
	
	
	public List<VoterHouseInfoVO> getFamilyInformation(Long boothId, Long publicationDateId,String houseNo,Long userId);
	
	public List<VoterHouseInfoVO> getMultipleFamiliesInformation(List<VoterHouseInfoVO> familiesList,Long userId);
	 
	public List<SelectOptionVO> getUserCategoryValuesByUserId(Long userId);;
	
	public InfluencingPeopleBeanVO getDetailsByVoterId(Long voterId,Long userId);
	
	public CadreInfo getCadreDetailsByVoterId(Long voterId);

	public Long getReportLevelId(String type);
	
	public SelectOptionVO getBoothBasicInfo(Long boothId);
	
	public List<InfluencingPeopleVO> getInfluencingPeopleBySearch(Long userId,
			InfluencingPeopleVO influencingPeopleVO);
	
	public void mapVoterAsInfluencingPerson(Long influencePeopleId , Long voterId);
}
