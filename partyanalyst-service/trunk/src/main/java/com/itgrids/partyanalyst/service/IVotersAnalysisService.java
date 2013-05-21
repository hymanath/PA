package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.ImportantFamiliesInfoVo;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterInfo;

public interface IVotersAnalysisService {
	
	public List<SelectOptionVO> publicationDetailsBasedOnConstituency(Long constituencyId);
	
	/*public List<VoterVO> getVoterDetails(Long publicationDateId, Long boothId,
			Long panchayatId ,Long hamletId , Integer startIndex , Integer maxRecords , String order,
			String columnName,Long userId);*/
	public List<VoterVO> getVoterDetails(Long publicationDateId, Long boothId,
			Long panchayatId, Long hamletId,Integer startIndex , Integer maxRecords , String order,
			String columnName,Long userId,Long customwardId,Long constiId);
	
	public List<Long> getImpFamiles(Long id,Long publicationDateId,String name);
	
	public VoterCastInfoVO getVotersCastDetails(Long id,Long publicationDateId,String type);
	
	public VotersInfoForMandalVO getVotersCount(Long userId , String type,Long id,Long publicationDateId,Long constituencyId,String requestFor);
	
	public  List<VoterCastInfoVO> getVotersCastDetailsForSubLevels(Long id,Long publicationDateId,String type,Long userId,Long constituencyId,String buildType,String queryType);
	
	public List<VotersDetailsVO> getVotersDetailsByAgewise(Long constituencyId, Long tehsilId,Long panchayatId,Long boothId,
			 Long publicationDateId , String type);
	public ImportantFamiliesInfoVo getImportantFamiliesInfo(Long userId,String type,Long id,Long publicationDateId,Long constituencyId ,String resultFor,String buildType);
	
	//public List<VoterHouseInfoVO> getVoterHouseInfoDetails(Long id, Long publicationDateId,String checkedEle);
	public List<VoterHouseInfoVO> getVoterHouseInfoDetails(Long userId,Long id, Long publicationDateId,String checkedEle,String buildType,String requestFor);
	
	public List<VoterHouseInfoVO> getFamilyInfo(Long boothId, Long publicationDateId,String houseNo);
	
	public VoterHouseInfoVO getBoothDetailsForVoter(Long boothId);

	public List<VotersDetailsVO>  getAgewiseVotersDetailsForPanchayatisByTehsilId(Long tehsilId,Long publicationDateId);
	public List<VotersDetailsVO> getAgewiseVotersDetailsForBoothsByPanchayatId(Long panchayatId,Long publicationDateId);
	public List<VotersDetailsVO> getAgewiseVotersDetailsForBoothsByLocalElectionBodyId(Long localElectionBodyId,Long publicationDateId);
	public List<VotersDetailsVO> getAgewiseVotersDetailsForTehsilsByConstituencyId(Long constituencyId,Long publicationDateId);
	
    //public List<VoterHouseInfoVO> getVoterDetailsByCaste(Long id,Long publicationDateId,String caste);
	public List<VoterHouseInfoVO> getVoterDetailsByCaste(Long id,Long publicationDateId,Long casteStateId,String type,String buildType,Long userId,Long hamletId);

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
	
	public VoterCastInfoVO getVotersCastWiseDetailsInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId,String queryType);
	
	public List<SelectOptionVO> getcastCategoryGroups();
	
	public ResultStatus saveCasteName(Long userId, Long stateId, Long casteCategoryGroupId, String casteName);
	
	public List<SelectOptionVO> getBoothsInMunicipality(Long lclElecBodyId,Long publicationDateId,Long constituencyId);
	
	public ResultStatus updateVoterData(Long constituencyId,Integer startIndex, Integer maxResults);
	
	public List<VoterHouseInfoVO> getMultipleFamiliesInfo(List<VoterHouseInfoVO> familiesList);
	
	public VoterHouseInfoVO getVoterPersonalDetailsList(List<VoterHouseInfoVO> voterIds,Long userId);
	
	public boolean updateMultipleVoterDetails(List<VoterHouseInfoVO> voterHouseInfoVOs,String partyCast);
	
	public VoterHouseInfoVO getVotersInfoBySearchCriteria(VoterHouseInfoVO searchInfo,String type,Long id,List<Long> categories);
	
	public List<SelectOptionVO> getElectionIdAndTypeByPublicationId(Long publicationDateId);
	
	public List<PartyVotesEarnedVO> getPreviousElectionVotingTrends(Long id, Long publicationDateId,Long constituencyId, String type);

	public List<VotersDetailsVO> getCountList(Long publicationDateId,Long id,String type,Long constituencyId,Long tehsilId, Long userId);
	
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
	
	public List<VotersDetailsVO> getAgewiseVotersDetaForPanchayatisByTehsilId(Long tehsilId,Long publicationDateId, String type,Long constituencyId);
	
	public List<VotersDetailsVO> getAgewiseVotersDetForBoothsByLocalElectionBodyId(Long localElectionBodyId,Long publicationDateId, String type,Long constituencyId);
	
	public List<VotersDetailsVO> getAgewiseVotersDetForBoothsByPanchayatId(Long panchayatId,Long publicationDateId,String type,Long constituencyId);

	public List<SelectOptionVO> getElectionYearsByMandalId(String type,Long mandalId);
	
	public CrossVotingConsolidateVO getCrossVotingReportByMandalIdAndEleYear(String type, Long id, String year, String includeAliance);
	
	//public ResultStatus saveLocality(Long userId,Long hamletId,String name,Long localbody);
	public ResultStatus saveLocality(Long userId,Long hamletId,String name,Long localbody,Long wardId);

	public ResultStatus deleteVotersDataFromIntermediateTables(Long reportLevelValue, Long publicationDateId);
	
	public List<SelectOptionVO> getBoothsForConstituencyAndPublication(Long constituencyId,Long publicationId);
	
	public ResultStatus saveVoters(String name,String voterCardNo,String houseNo,String gaurdian,String relationShip,String gender,Long serianNo,Long age,Long boothId);
	
	public Long getParliamentConstituencyId(String type, Long id, Long year);
		
	public List<VotersDetailsVO> getAgewiseVotersDetForBoothsByWardId(Long id,Long publicationDateId,Long constituencyId);
	
	public List<VotersDetailsVO> getAgewiseVotersDetailsForWardsByLocalElectionBodyId(Long assemblyLocalBodyId,Long publicationDateId,Long constituencyId);
	
	public VoterCastInfoVO getCastWisePartyCount(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId);
	
	public VoterCastInfoVO getVotersPartyDetailsInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId,String queryType);
	
	public ResultStatus moveVotersModificationDataFromTempToMainTable(Long constituencyId,Long publicationDateId);
	
	public List<SelectOptionVO> getConstituenciesToBeMappedForVoterChanges();
	
	
	public List<VoterHouseInfoVO> getFamilyInformation(Long hamletId , Long boothId, Long publicationDateId,String houseNo,Long userId,String selectType);
	
	public List<VoterHouseInfoVO> getMultipleFamiliesInformation(List<VoterHouseInfoVO> familiesList,Long userId);
	 
	public List<SelectOptionVO> getUserCategoryValuesByUserId(Long userId);;
	
	public InfluencingPeopleBeanVO getDetailsByVoterId(Long voterId,Long userId);
	
	public CadreInfo getCadreDetailsByVoterId(Long voterId);

	public Long getReportLevelId(String type);
	
	public List<SelectOptionVO> getBoothBasicInfo(Long boothId);
	
	public List<InfluencingPeopleVO> getInfluencingPeopleBySearch(Long userId,
			InfluencingPeopleVO influencingPeopleVO);
	
	public void mapVoterAsInfluencingPerson(Long influencePeopleId , Long voterId);
	
	public ResultStatus checkForVoter(Long voterId,String type);
    
	public ResultStatus mapVoterDataFromOnePublicationToAnotherPublication(Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,Boolean boothCreateFlag);
	
	public List<Object[]>  getWardsInMuncipality(Long mincipalityId, Long PublicationId);
	 
	public List<Object[]>  getLocalitiesForHamlet(Long id);
	
	public List<SelectOptionVO> getLocalities(Long hamletId);
	public List<SelectOptionVO> getLocalities(Long hamletId,Long userId);
	
	public List<SelectOptionVO> getWards(Long muncipalityId,Long publicationId,Long constituencyId);
	
	public List<SelectOptionVO> getHamlets(Long panchayatId);
	//Updated by gayathri to get HamletLevel VotersBasicInfo
	
		public  VotersInfoForMandalVO	getVoterDetailsByVoterIdForHamlet(VoterVO voterVO,List<Object> voterIdList);
		
		public List<VoterVO> getAllPublicationsForHamlet(Long constituencyId);
		
		 public List<VotersInfoForMandalVO>  getPreviousVotersCountDetailsForHamlet( Long constituencyId, Long mandalId,Long  panchayatId,Long boothId,Long hamletId,Long userID ,String type);
		
	public List<VotersDetailsVO> getAgewiseVotersDetailsByHamletId(Long hamletId,Long publicationDateId,Long userId);
	public List<VotersDetailsVO> getAgewiseVotersDetailsForHamletByPanchayatId(Long panchayatId,Long publicationDateId,Long userId , String Type);
	
	public ResultStatus updateSerialNo(Long constituencyId,Long publicationDateId,Integer startIndex, Integer maxResults);
	public List<InfluencingPeopleBeanVO> getInfluencingPeopleCount(Long userId,Long locationValue,String type,Long publicationId);
	
	public List<VoterVO> getInfluencingPeopleVoterDetails(Long userId,Long locationValue,String type,String buttonName,
			Long publicationId,Integer startIndex , Integer maxRecords,String columnName,String order);
	
	public ImportantFamiliesInfoVo getImportantFamaliesDetailsForPanchayatByHamlet(Long userId,String type,Long id,Long publicationDateId,Long constituencyId);
	public ResultStatus deleteVotersCastDataFromIntermediateTables(Long id,Long publicationDateId,Long userId);
	public ResultStatus deleteVotersPartyDataFromIntermediateTables(Long constituencyId,Long publicationDateId,Long userId);
	public ResultStatus deleteVoterInfoFromIntermediateTablesByConstituencyId(Long constituencyId,Long publicationDateId);
	public List<VoterCastInfoVO> getCastAndGenderWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId);
	//public ResultStatus insertVotersCasteDataInIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId);
	public List<SelectOptionVO> getBoothsByPanchayatId(Long id,Long publicationDateId);
	public List<VoterCastInfoVO> getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId);
	public List<SelectOptionVO> getCastCategoryWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId);
	
    public List<SelectOptionVO> getConstituenciesToMapPublicationData(Long fromPublicationId,Long toPublicationId);
    
    public List<VotersDetailsVO> getAgewiseVotersDetailsForLocalAreaByHamletId(Long hamletId,Long publicationDateId,Long userId ,String type);
    
	public List<String> storeVoterDetails(Map<String , VoterVO> votersMap , Long boothId);
	
	public ResultStatus checkForVoterId(String voetrCardId);
	
	public List<Long> checkForSerialNos(List<Long> serialNos , Long boothId);
	
	public List<VoterHouseInfoVO> getVotersFamilyDetailsByConstituencyId(Long frompublicationId,Long toPublicationId,Long partNo,String hno,Long userId,Long constituencyId);
	
	public List<SelectOptionVO> getPublicationListForVoterData(Long constituencyId);
	
	 //public List<SelectOptionVO> getWardsMunicipality(Long lclElecBodyId,Long publicationDateId);
	 
	 public List<SelectOptionVO> getBoothForWard(Long wardId, Long publicationDateId);
	 
	 public List<VoterHouseInfoVO> getMultipleFamiliesInformationForHamlet(
				List<VoterHouseInfoVO> familiesList, Long userId, String selectType);
	 
	 public String checkLocalityDataExist(Long hamletId, Long userId,String type,Long publicationDateId);
	 
	 public List<VotersDetailsVO> getAgewiseVotersDetailsForHamletByBoothId(Long boothId,Long publicationDateId,Long userId,String Type);
	 
	 public ResultStatus updateVoterStatusInVoterModification(Long constituencyId);
	 
	 public VoterHouseInfoVO getSelectedVotersDetails(List<VoterHouseInfoVO> votersDetails , VoterHouseInfoVO parameters);
	 
	public boolean updateAllSelectedVotersDetails(List<VoterHouseInfoVO> votersDetailsList , String isMuncipality);
	 
	 public List<Long> getCountOfHamletAndBoothsInAPanchayat(Long panchayatId);
	 
	 public List<VoterVO> getVoterData(VoterDataVO voterDataVO ,Long userId , List<Long> categories);
	 
	 public List<SelectOptionVO> getCategoeryValuesService(Long userId,Long categoeryId);
	 
	 public ResultStatus storeCategoeryData(List<SelectOptionVO> categoeryValues,Long userId,Long categoeryId);
	 
	 public List<SelectOptionVO> checkForCategoeryValues(List<SelectOptionVO> categoeryValuesList , Long userId);

	 public List<SelectOptionVO> getLocalitiesForWards(Long wardId , Long userId ,String Query);
	 
	 public VotersInfoForMandalVO getVotersCountForCustomWard(Long id,Long publicationDateId,String reqType);
	 
	 public List<SelectOptionVO> getCasteWisePercentage(List<VoterCastInfoVO> list);
		 
	 public Long getLatestPublicationId();
	 
	 public List<SelectOptionVO> getMandalsInConstituency(Long constituencyId);
	 
	 public ConstituencyManagementVO getCasteWisePercentsInLocations(List<VoterCastInfoVO> list);
		
	 public VoterInfo getTotalVotersDetailsbyLocation(Long userId,Long reportLevelValue,String locationType,Long publicationDateId,Long constituencyId);
	 
	 public String getReportLevelById(Long id);
	 
}
