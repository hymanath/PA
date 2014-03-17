package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.FlagVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VoterReportVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.InfluencingPeople;

public interface IVoterReportService {
	
	public VoterReportVO getVoterDetailsInaLocation(String range,Long rangeValue);
	
	public ResultStatus insertVotersPartyDataToIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId);
	
	public ResultStatus deletevotermodificationFromIntermediateTables(Long constituencyId,Long publicationId);
	
	public ResultStatus insertVotersCasteDataInIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId,boolean hamletChecked,boolean boothChecked,boolean hamletBoothChecked,boolean localityChecked,boolean wardChecked);
	
	 public ResultStatus deleteVoterModifiedData(Long constituencyId,Long publicationDateId);
	 
	public void  getVotersCastWiseDetailsInALocationFromIntermediateTable(Long userId,Long reportLvlId,Long locationId,Long publicationDateId,Long constituencyId,VoterCastInfoVO voterCastInfoVO);
		
	/** This Method is used to get voterInfo by using boothId */
	public List<VoterVO> getVoterDetailsForAdminEdit(Long boothId,Long userId,Long startIndex,Long endIndex);
	public void getPartyNGenderWiseVotersCountByPublIdInALocFromIntermedTable(Long userId,Long reportLvlId,Long levelValue,Long publicationDateId,Long constituencyId,VoterCastInfoVO mainVO);
	
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleMandal(List<SelectOptionVO> mandalsList,Long publicationDateId,Long userId,Long constituencyId);
	
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleValues(List<SelectOptionVO> subList,Long publicationDateId,Long userId,Long constituencyId,Long locationLvl);
	
	public VoterVO saveVoterDetailsList(List<VoterVO> voterIds,Long userId,Long boothId);
	  
	public List<VoterHouseInfoVO> getVoterInfoByBIdandVId(List<VoterHouseInfoVO> votersList,Long publicationDateId,Long userId);
	  
	public VoterVO saveVoterSearchDetailsList(List<VoterVO> voterIds,Long userId);
		
	public List<SelectOptionVO> getWardsInMunicipality(Long lclElecBodyId,Long publicationDateId);
	
	public List<VoterCastInfoVO> getVoterAttributeDetails(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId);
	
	public List<VoterCastInfoVO> getVoterAttributeSubDetails(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId);
	
	public List<SelectOptionVO> getPanchayatsByTehsilId(Long tehsilId);
	
	public List<SelectOptionVO> getBoothsByPanchayatIDConstiId(Long panchayatId,Long constituencyId);
	
	public Long getLatestPublicationIdByConstiId(Long constituencyId);
	
	public List<SelectOptionVO> getSelectedUserCategoeryDetails(Long userId,List<Long> ids , String type,String status,Long constituencyId,Long publicationId);
	
	public List<SelectOptionVO> getAllWardsInUrbanConstituency(Long constituencyId,Long publicationId);
	
	public List<SelectOptionVO> getUserCategoeryValuesForWards(Long userId,Long constituencyId,List<Long> ids ,String status,Long publicationId);
	
	public List<SelectOptionVO> getAllBoothsForSelectedWards(List<Long> ids,Long publicationId);
	
	public List<SelectOptionVO> getUserCategoeryValuesForMuncipalWards(Long userId,Long constituencyId,String type,List<Long> ids,Long publicationId);
	
	public List<InfluencingPeopleBeanVO>  getCountsOfSelectedLevel(Long userId,Long id,Long constituencyId,String selLevel,Long publicationDateId);
	 
	 public List<VoterVO> showVoterDetailsForSelcetedLevel(Long userId,Long id,Long constituencyId,String selLevel,Long publicationDateId,String type,Integer startIndex,Integer maxIndex,String order,String columnName);
	 
	 public List<VoterVO> showingVoterDetailsForSelectedHamlet(Long hamletId,Long userId,String selLevel,String type,Integer startIndex,Integer maxIndex,String order,String columnName);
	 
	 public List<InfluencingPeopleBeanVO> getcountForSelectedTypeInHamlet(Long hamletId,Long userId,String selLevel);
	 
	 public String getRegionNameBasedOnScope(String infScope,String regionId);
	 
	 public List<VotersDetailsVO> getAgeWiseDetailsOfBoothsInSelectedCustomWard(Long wardId,Long userId,Long publicationDateId,Long constituencyId);
	 
	 public List<SelectOptionVO> getTotalWardsInLocalBody(Long constituencyId);
	 public List<VoterVO> storeInfluencingPeopleDetails(List<InfluencingPeople> influencingData,String type,Long id,Long totalRecords,Long userId);
	 public List<VoterVO> storeCadrePeopleDetails(List<Cadre> cadreDetails,String type,Long id,Long totalRecords,Long userId);
	 public List<VoterVO> storeCandidateDetails(List<Candidate> candidateDetails,String type,Long id,Long totalRecords,Long userId);
	 
	 public ResultStatus insertVotersBasicInfoToIntermediateTables(Long reportLevelValue,Long publicationDateId,Long userId);
	 
	  public ResultStatus deleteVotersBasicInfoFromIntermediateTables(Long constituencyId);
	  
	  public ResultStatus insertVotingTrendzPanchayatInfoToIntermediateTables(final Long reportLevelValue,Long publicationDateId);
	  
	  public ResultStatus deletePreviousEleVotingIntoIntermediateTables(Long constituencyId);
	  public ResultStatus insertVotingTrendzToIntermediateTables(final Long reportLevelValue,Long publicationDateId,Long userId);
	  
	public List<VoterVO> getVoterDataForPanchayat(VoterDataVO voterDataVO , Long userId , List<Long> categories ,String searchColumn,String searchString);
    public List<VoterVO> getVoterDataForBooth(Long boothId,Long userId, Integer startIndex,Integer maxRecords, String order, String columnName,List<Long> categories ,String searchColumn , String searchString);
    public List<VoterVO> getVoterDataForHamlet(VoterDataVO voterDataVO , Long userId , List<Long> categories ,String searchColumn,String searchString);
    public List<VoterVO> getVoterDataForWard(VoterDataVO voterDataVO , Long userId , List<Long> categories ,String searchColumn,String searchString);
    public List<VotersDetailsVO> caluculateAgeWiseDetailsForPanchayatByHamlets(Long userId , List<Long> panchayatIds ,Long publicationDateId,boolean isSublevel);


    public List<VotersInfoForMandalVO> getDataForPartialPanchayats(Long constituencyId,Long mandalId,Map<Long,String> panchayatIds,Long publicationDateId,Long userId);

    public void getPartialAndNormalPanchayats(Long publicationDateId,Long id,Map<Long,String> panchayatIds,Map<Long,String> partialPancMap);
    
    public ResultStatus saveFlagDetails(String name,String desc,String color,Long userId,Long flagId);
    
    public List<VoterVO> getAllFlags();
    
    public ResultStatus deleteFlag(Long flagId);

    public void saveVoterInfoForCustomWards(List<Long> localElecBodyIds,Long userId,Long publicationId,Long constituencyId,Long reportLevelId,String type);
    
    public void saveVoterFamilyInfoForCustomWards(List<Long> localElecBodyIds,Long userId,Long publicationId,Long constituencyId,Long reportLevelId,String type);
    
    public void saveVoterAgeInfoForCustomWards(List<Long> localElecBodyIds,Long userId,Long publicationId,Long constituencyId,Long reportLevelId,String type);
    
    public void saveWardBoothData(List<Long> localElecIds,Long reportLevelValue,Long publicationDateId,String type);
    
    public List<VoterVO> getVoterFlagDetailsForALocation(Long locationId,Long constitunecyId,String type,Long publicationId,String requestFor);
    
    public List<SelectOptionVO> getFlagsList(Long voterId);
    
    public ResultStatus addFlagToVoter(Long voterId,List<Long> checkedflagIds,List<Long> uncheckedflagIds,Long userId);
    public Long getVoterCasteCategoryIdByUserId(Long userId,Long voterId);
	 public void addFlagToVoterFromMobileApp(final List<FlagVO> flagDetails,String uniqueCode);
	 
	 public String updateVoterMobileNumberAndCaste(String voterID,
				Long casteStateId,
				String mobileNo,String uniqueId);
	 
	 public ResultStatus deletevotermodificationFromIntermediateTablesForDistrict(Long districtId, Long publicationDateId);
	 
	 public ResultStatus updateVoterNamesAndRelativeNames();
	 
	 public ResultStatus getCasteVotersAvailableConstituencyIds();

    
}
