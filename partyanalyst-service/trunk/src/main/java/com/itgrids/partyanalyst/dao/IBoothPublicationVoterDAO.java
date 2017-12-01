
package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SmsVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.SurveyDetailsInfo;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterFlag;

public interface IBoothPublicationVoterDAO extends
		GenericDao<BoothPublicationVoter, Long> {
	
	
	public List<BoothPublicationVoter> checkForVoterExistenceInBoothPublicationVoter(
			Long boothId, Long voterId);
	
	public List<Object[]> getVotersDetailsByBoothId(Long boothId, Integer startIndex,
			Integer maxRecords, String order, String columnName);
	
	public List<Object[]> getVotersDetailsForPanchayatByPublicationId(
			Long panchayatId, Long publicationDateId, Integer startIndex,
			Integer maxRecords, String order, String columnName);
	
	  public List<Object[]> getVotersDetailsAnCountDetailsForPanchayatByPublicationId(
				Long userId , Long panchayatId, Long publicationDateId, Integer startIndex,
				Integer maxRecords, String order, String columnName,String queryString,String queryForCategories,String queryForselect,boolean isCount,List<Long> boothIds);
	
	public List getVotersCountByBoothId(Long boothId);
	public List getVotersCountForPanchayat(Long panchayatId,Long publicationDateId);
	public List<Object[]> getVotersCountByPublicationId(String type,Long id,Long publicationDateId,Long constituencyId);
	public List<Object[]> getVotersCountForPanchayatByPublicationId(Long panchayatId,Long publicationDateId);
	public List<Object[]> getVotersCountFromLocalElectionBody(Long assemblyLclElecBodyId,Long publicationDateId,Long constituencyId);
	public List<Object[]> getPublicationDetailsBasedOnConstituency(Long constituencyId);
	
	public List<Object[]> findImpFamilesBasedOnPanchayat(Long PanchayatId,Long publicationDateId);
	
	public List<Object[]> findImpFamilesBasedOnConstituencyId(Long constituencyId,Long publicationDateId);
	
	public List<Object[]> findImpFamilesBasedOnBoothId(Long boothId,Long publicationDateId);
	
	public Long getTotalVotersCount(Long id,Long publicationDateId,String type);
	
	public Long getVotersCountForLocalElectionBody(Long assemblyLclElecBodyId,Long publicationDateId);
	
	public List<Object[]> findAllImpFamiles(Long id,Long publicationDateId,String type,String queryString,Long constituencyId);

	public List<Object[]> getVotersImpFamilesForLocalElectionBody(Long assemblyLclElecBodyId,Long publicationDateId,String queryString,Long constituencyId);
	
	public Long findTotalVotersCastInfoByConstituencyAndPublicationDate(Long constituencyId, Long publicationDate);
	
	public List findVotersCastInfoByMandalAndPublicationDate(Long mandalId, Long publicationDate);
	
	public List findVotersCastInfoByPanchayatAndPublicationDate(Long panchayatId, Long publicationDateId);
	
	public List findVotersCastInfoByBoothIdAndPublicationDate(Long boothId, Long publicationDateId);
	
	public List getGenderWiseCountInConstituency(Long constituencyId, Long publicationDate);
	
	public List<Object[]> getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
			 Long constituencyId ,Long publicationDateId, Long startAge,
			Long endAge);

	public List<Object[]> getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
			Long tehsilId, Long publicationDateId, Long startAge,
			Long endAge,Long constituencyId);
	
	public List<Object[]> getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
			Long panchayatId, Long publicationDateId , Long startAge, Long endAge);
	
	public List<Object[]> getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
			Long boothId, Long publicationDateId, Long startAge, Long endAge) ;
	
	public List<Object[]> getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
			Long localElectionBodyId, Long publicationDateId, Long startAge, Long endAge,Long constituencyId);

	public List<Object[]> getImpFamilesForPanchayatByPublicationId(Long panchayatId,Long publicationDateId,String queryString);
	
	public List getVotersCastInfoFromLocalElectionBody(Long assemblyLclElecBodyId,Long publicationDateId);
	
	public List findFamiliesVotersInfoForPanchayat(Long id,Long publicationDateId);
	
	public List findFamiliesVotersInfoForBooth(Long id,Long publicationDateId);
	
	public List<Voter> findFamiliesInfo(Long boothId,Long publicationDateId,String houseNo);
	
	public List<Voter> getVoterDetailsByCaste(Long boothid,Long publicationDateId,String caste);
	
	public Long findVotersCountByPublicationIdInALocation(String locationType,Long locationId,Long publicationDateId);
	
	public List<Object[]> getCastCategoryWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId);
	
	public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId);
	 
	public List<Long> getVoterStateId(Long voterId);
	
	public List<Object[]> getVoterDetailsByCasteStateForBooth(Long boothid,Long publicationDateId,Long casteStateId,Long userId,Long constituencyId);
	
	public List<Object[]> getVoterDetailsByCasteStateForPanchayat(Long panchayatId,Long publicationDateId,Long casteStateId,Long userId);
	
	public List<Object[]> getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId);
	
	public List<Object[]> getVotersDetailsBySearchCriteria(Long publicationDateId,Long id,Integer startRecord,Integer maxRecords,String queryString,String locationType,List<Long> boothIds);
	
	public List<Object[]> getCastWiseCount(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId);
	 
	public List<Object[]> getPartyWiseCount(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId); 
	
	public List<Object[]> getParties(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId);
	
	public List<Long> getVotersCountBySearchCriteria(Long publicationDateId,Long id,String queryString,List<Long> boothIds,String locationType);
	
	 public List<Object[]> getConstituencies();
	 
	 public List<Long> getConstituenciesIds();
	 
	 public List<Object[]> findVotersGenderWiseCountByPublicationIdInALocation(String locationType,Long locationId,Long publicationDateId,Long constituencyId);
	 
	 public List<Long> findFamiliesCountByPublicationIdInALocation(String locationType,Long locationId,Long publicationDateId);
	 
	 public List<Long> getAllImpFamilesCount(String locationType, Long locationValue,Long publicationDateId,Long constituencyId);
	 
	 public Long getVotersCountInAAgeRange(String locationType, Long locationValue,Long publicationDateId,Long ageFrom, Long ageTo,String gender,Long constituencyId);
	 
	 public List<Object[]> getVotersDetailsForBoothForPublication(Long publicationId,String partNo,Long tehsilId);
	 
	 public List<Long> getVotersCountForMultipleBooths(List<Long> ids,Long publicationDateId);
	 
	 public List<Object[]> getGenderWiseVotersCountForWard(Long wardId,Long publicationDateId);
	 
	 public List<Object[]> getImpFamilesForWard(List<Long> boothids,Long publicationDateId,String queryString);
	 
	 public List<Object[]> getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
				List<Long> boothIds, Long publicationDateId, Long startAge, Long endAge);
	
	 public List<BoothPublicationVoter> findVoterContactDetails(Long voterId);
	 
	 public List<Long> getFamilyMemberCount(String houseNo,Long boothId);
	 
	 public List<Object[]> getFamileyMembersDetailsForHouseNo(String houseNo,Long boothId,Long voterId);
	 
	 public List<Long> getVoterPublicationIdsBetweenTwoPublications(Long fromPublicationDateId, Long toPublicationdateId);
	 
	 public List<Long> getPreviousPublicationIds(Long publicationDateId); 
	 
	 //start
	  
	 public List<Object[]>  getHamletsForConstituency(Long id);
	 public List<Object[]>  getLocalitiesForConstituency(Long id);
	 public List<Object[]>  getLocalitiesForMandals(Long id);
	 public List<Object[]>  getLocalitiesForPanchayat(Long id);
	 public List<Object[]>  getLocalitiesForBooth(Long id);
	 public List<Object[]>  getHamletsForBooth(Long id);
	 public List<Object[]>  getLocalitiesForHamlet(Long id);
	 public List<Object[]>  getLocalitiesForHamlet(Long id,Long userId);
	 public List<Object[]> getVotersCountDetailsInSpecifiedRangeForHamletByPublicationId(
				Long hamletId, Long publicationDateId , Long startAge, Long endAge); 
	 public List<Object[]> getVotersCountInSpecifiedRangeForHamletByPublicationId(
				Long hamletId, Long publicationDateId , Long startAge, Long endAge,Long userId);
	 
	 
	 public List<Voter> getVotersByBoothId(Long boothId);
	 
	 public List<Object[]> getPartNoAndVoterIdByConstituencyInAPublication(Long constituencyId,Long publicationDateId);
  
	 public List<Object> getVoterIdsBasedOnHamletId(Long hamletId, Long userId , String type);
	 public List<Object[]> getVotersBasedOnVoterIdsAndPublication(
			 Long publicationDateId , Long startAge, Long endAge, List<?> voterIds);
	 
	

	 public List<Object[]> getVotersBasedOnVoterIdsAndPublication(
	 		 Long publicationDateId , List<?> voterIds);
	 
	 public List<Object[]> getSerialNoByVoterIdsList(List<Long> voterIdsList);
	 
	 public Integer updateSerialNoByVoterId(Long serialNo, Long voterId);
	 
	 public List<Voter> getVotersInABoothsList(List<String> partNosList, Long constituencyId, Long publicationDateId);
	 
	 public List getLocationNameByLocationValue(String type, Long locationValue);
	 

	 public List<Long> getCandidateCount(List<Long> locationValue,Long publicationDateId,String type);
	 
	 public List<Object[]> getPoliticianDetails(List<Long> locationValue,Long publicationDateId,String type,Integer startIndex,
				Integer maxRecords,String columnName,String order);
	 
	 public Long getTotalVotersCountForHamlet(Long userId ,Long id,Long publicationDateId,String type);
	 
	public List<Object[]> getImpFamilesForHamlet(Long userId,Long hamletId,
			Long publicationDateId, String queryString);
	
	public List<Object[]> getVotersCountByPublicationIdForPanchayatByHamlet(Long userId,String type,Long id,Long publicationDateId,Long constituency);
	
	public List<Object[]> findFamiliesVotersInfoForPanchayatByHamlet(List<Long> hamletIds,Long publicationDateId);
	
	//public List<Object[]> getImpFamilesForPanchayatByPublicationIdAndHamlet(Long userId , Long panchayatId,Long publicationDateId,String queryString);
	
	//public Long getTotalVotersCountForHamlet(Long userId , Long id,Long publicationDateId);
	
	public List<Long> getVotersInHamletForUser(Long userId , Long hamletId);
	
	public List<Object[]> getFamilyDetailsForHamlet(Long userId , List<Long> voterIds , Long publicationDateId,String queryString);
	
	public List<Object[]> getVotersCountForHamlet(Long hamletId, Long userId , Long publicationDateId);
	
	public List<Long > getVoterIdsForuserByHamletIds(Long userId,List<Long> hamletIds);
	
	public List<Object[]> getAllVoterDetailsByPublicationAndVoterId(Long publicationDateId , List<Long> voterIds);
	
	public List<Object[]> getImpFamilesForPanchayatByPublicationIdAndVoters(Long publicationDateId ,Long userId,String type,List<Long> ids);
	
	public List<Object[]> getVoterDetailsByVoterIds(List<Long> voterIds,Long publicationDateId);
	
	public List<Long> getTotalVotersCountForHamletByVoterIds(List<Long> voterIds,Long publicationDateId);
	
	public List<Long> getBoothPublicationVoterIdsByVoterIdsList(String partNo,List<Long> voterIdsList, Long publicationDateId);
	
	public List<Object[]> getVotersBasedOnVoterIdsAndPublicationAndGender(Long publicationDateId , List<?> voterIds);
	
	public List<Object[]> getUnassignedVotersInPanchayat(Long userId);
	
	public List<Object[]> getVotersListInPanchayat(Long publicationDateId,Long panchayatId,Long userId);
	
	public List<Object[]> getAssignedAndUnassignedVtrsOfLclBdy(Long id,Long userId,String type);
	
	public Integer deleteByIdsList(List<Long> idsList);

	 public List getCadreMobileDetails(Long userId,List<Long> scopeId,String scope);
	 public List getInfluencePeopleMobileDetails(Long userId,List<String> scopeId,String scope);
	 public List getVoterMobileDetails(Long userId,Long scopeId,String scope);
	 public Long getLatestpublicationDate();
	 public List<Long > getVoterIdsForuserByHamletForLocalities(Long userId,Long hamletIds);
	 
		public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdForLocality(Long userId,Long localityId,Long  hamletId,Long  publicationDateId,String type);
		
		public List<Object[]> getVoterDetailsByCasteStateForPanchayatByHamlet(List<Long> voterIds,Long publicationDateId);
		 public List<Object[]> getConstituenciesToMapPublicationData(Long fromPubliationId,Long toPublicationId);
		 
		 public List<Object[]>  getLocalitiesForBooth(Long id,Long userId);
		 
	public List<Long> checkForSerialNosDao(List<Long> serialNos , Long boothId);
	 
	 public List<Object[]> getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(Long userId,String locationType,List<Long> locationId,Long publicationDateId,Long constituencyId);
	 
	 public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdMultipleALocation(Long userId,String locationType,List<Long> locationIds,Long publicationDateId,Long constituencyId);
	 
	 public Long getTotalCastCountInALocation(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId);
		
	 public List<Voter> findFamiliesInfoBypartNo(String partNo,Long publicationDateId,String houseNo,Long constituencyId);
		
	public List<Object[]> getVIdsAndSerialNoByBoothId(Long boothId,Long startIndex,Long endIndex);
	
	public Integer updateSerialNoByVIdBId(Long serialNo, Long voterId,Long boothId);
	
	public List<Long> checkSerialNoandVoterIdDuplicates(Long serialNo, Long voterId,Long boothId);
	
	public List<BoothPublicationVoter> getAllVoterDetailsByVoterIds(List<Long> voterIds,Long publicationDateId);
	 public List<Object[]> getPublicationDetailsBasedOnConstituencyId(Long constituencyId);

	 public Long getTotalVotersCountForHamletByBooth(Long userId , Long id,Long publicationDateId,String type , Long boothId ,String myType);
	 public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdInALocationByBooth(Long userId,String locationType,Long locationId,Long publicationDateId,Long boothId,String type);
	 public List<Object[]> getVoterDetailsByPanchayatIds(Long panchayatId,Long publicationDateId,Long userId);
	 public List<Object[]> getVoterDetailsByHamletId(Long hamletId,Long publicationDateId,Long userId ,String cond);	 
	 public List<Object[]> getVoterPersonalDetailsByVoterIdAndPuclicationId(Long voterId,Long publicationDateId);
	
	 public Long getTotalVotersCountByReportlevelIdsList(List<Long> reportlevelIdsList,Long publicationDateId,String type);
	 
	 public List<Object[]> getVotersCountByAssemblyIdsList(List<Long> assemblyIdsList,Long publicationDateId);
	 
	 public Long getTotalVotersCountForParliament(List<Long> assemblyIdsList,Long publicationDateId);
	 
	 public List<Object[]> findAllImpFamilesForParliament(List<Long> assemblyIdsList, Long publicationDateId, String queryString);
	 
	 public List<Object[]> getPublicationUserCount(Long userId,Long publicationDateId , Long Id,String type);
	 
		public List<Voter> findFamiliesInfoForHamlet(Long hamletId,Long boothId,Long publicationDateId,String houseNo);
		
	    public List<Voter> findFamiliesInfoForLocalBody(Long hamletId,Long boothId,Long publicationDateId,String houseNo);
	    
		public List<Long> getAllBoothsInHamletByUser(Long userId,Long hamletId,Long publicationDateId , Long constituencyId ,String cond);

		public List<Object[]> getFamiliesInBooth(Long userId,Long hamletId,Long boothId , Long publicationDateId , Long constituencyId ,String cond);
		
		public List<Object[]> getVotersCountByGenderInBooth(Long userId ,Long hamletId , Long  boothId ,Long  publicationDateId,Long constituencyId,String cond);
		
		public List<Voter> getVoterDetailsByHamletForUser(Long userId,Long id,Long publicationDateId,String cond);
		
  public List<Object[]> findFamiliesVotersInfoForBoothForUser(Long id,Long publicationDateId,Long userId);
  
  public List<Object[]> getVoterDataForPanchayat(Long panchayatId , Long publicationId ,Long startIndex, Long maxIndex , String sort,String order );
  
  public List<Object[]> getVoterDataForBooth(Long boothId , Long publicationId ,Long startIndex, Long maxIndex , String sort,String order );

  public List<Object[]> getVoterAttributeDetails(Long userId,List<Long> attributeIds,String locationType,Long locationValue,Long constituencyId,Long publicationId);
  
  public List<Object[]> getVoterAttributeDetailsForHamlet(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId);
  
  public List<Object[]> getAgeWiseDetails(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId,Long startAge,
			Long endAge);
  
  public List<Object[]> getAgeWiseDetailsForHamlet(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId,Long startAge,
			Long endAge);
	
  public List<Object[]> getCategoryWiseVoterDetailsByCategoryId(String locationType, Long locationValue, Long constituencyId, Long categoryId,Long publicationId,Long userId,Integer startIndex,
			Integer maxRecords, String order, String columnName);
  
  public List getcategoryWiseVotersCount(String locationType, Long locationValue, Long constituencyId, Long categoryId,Long publicationId,Long userId);
  
  public List<Object[]> getCategoryWiseVoterDetailsByHamletId(Long locationValue, Long constituencyId, Long categoryId,Long publicationId,Long userId,Integer startIndex,
			Integer maxRecords, String order, String columnName);
  	
  public List<Object[]> getVoterAttributeDetailsForDifferentLocations(Long userId,Long attributeId,String locationType,Set<Long> locationIds,Long constituencyId,Long publicationId);
  
  public List<Object[]> getVoterAttributeDetailsForHamletForDifferentLocations(Long userId,Long attributeId,String locationType,Set<Long> locationIds,Long constituencyId,Long publicationId);
  
  public List<Object[]> getVotersCountForCustomWardByPublicationId(Long customwardId,Long publicationDateId,Long constituencyId,Long userId);
  
  public List<Object[]> getVotersDetailsByCustomWardId(Long wardId,Long publicationDateId,Long constituencyId,Long userId, Integer startIndex,
			Integer maxRecords, String order, String columnName);
  
  public List getVotersCountByCustomWardId(Long wardId,Long publicationDateId,Long constituencyId,Long userId);
  
  public List<Object[]> getVotersCountForCustomWardBooths(Long constituencyId,Long wardId,Long publicationDateId,Long userId);
  public List<Object[]> getFamiliesInWard(Long userId, Long id ,Long publicationDateId , Long constituencyId , String type);
  public List<Object[]> getVotersCountByGender(Long userId ,Long id ,Long  publicationDateId,Long constituencyId ,String type);

  public List<Object[]> getCustomWardWiseVotersInfoByLocalEleId(Long constituencyId,Long publicationId,Long localEleBodyId,Long userId);
  
  public Long getCasteCountForLocalEleBody(Long userId,Long publicationId,Long constituencyId,Long localEleBodyId);
  
  public List<Object[]> getWardsByLocalEleBodyIdId(Long userId,Long publicationId,Long localEleBodyId,Long constituencyId);
  
  public List<Object[]> getVoterDetailsForCustomWard(Long wardId, Long publicationDateId, Long userId,Long casteStateId);
  
  public List<Object[]> getVoterDetailsForCustomWardBooths(Long customWardId,Long boothId,Long userId,Long publicationDateId,Long casteStateId);
  
  public List<Object[]> getPartysOrCatstesForSelectedLevel(Long userId,List<Long> ids ,String type,String status,Long publicationId);
  
  public List<Object[]> getCastesListForSelectedMuncipality(Long userId , Long id,Long constituencyId,String status);
  
  public List<Object[]> getAllCastesOrPartesForSelectedWards(Long userId , List<Long> ids ,Long constituencyId,String status);
      
  public List<Object[]> getVoterCastAndPartyCountForDifferentLocations(Long userId,List<Long> attributeIds,String locationType,List<Long> locationIds,Long constituencyId,Long publicationId,String type,List<String> partNos);
  
  public List<Object[]> getVoterAttributeCountForDifferentLocations(Long userId,List<Long> attributeIds,String locationType,List<Long> locationIds,Long constituencyId,Long publicationId,List<String> partNos);
  
  public List<Object[]> getVoterCastAndPartyCount(Long userId,List<Long> attributeIds,String locationType,List<Long> locationIds,Long constituencyId,Long publicationId,String type,List<String> partNos);
  
  public List<Object[]> getVoterAttributeCount(Long userId,List<Long> attributeIds,String locationType,List<Long> locationIds,Long constituencyId,Long publicationId,List<String> partNos);
  
  //public List<Object[]> test1(Long userId,List<Long> attributeIds,String locationType,List<Long> locationIds,Long constituencyId,Long publicationId,String type,List<String> partNos);
  
  public List<Object[]> getUserCategValuesForSelectedMuncipalWards(Long userId,Long constituencyId,String type,List<Long>  ids,Long publicationId);
  
  public List<Object[]> getVotersCountByGenderInHamlet(Long userId ,Long hametId ,Long  publicationDateId,Long constituencyId);
  
	public List<Object[]> getCasteWiseDetails(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId);
	public List<Object[]> getCasteWiseDetailsForHamlet(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId);
	
	 public List<Object[]> getCatstesForBooths(Long userId,List<Long> boothIds ,Long publicationId);
	 
	 public List<Long> getCadreCountForSelectedLevel(List<Long> boothIds ,long constituencyId,Long userId);
	 
	 public List<Long> getInfluencingPeopleCountForSelectedLevel(List<Long> boothIds,Long constituencyId,Long userId);
	 
	 public List<Long> getPoliticianCountForSelectedLevel(List<Long> boothIds , long constituencyId);
	 
	 public List<Cadre> getCadreDetailsForSelectedlevel(List<Long> boothIds , Long constituencyId , Long userId,Integer startIndex,Integer maxIndex,String order,String columnName);
	 
	 public List<InfluencingPeople> getInfluencingPeopleDetailsForSelectedlevel(List<Long> boothIds , Long constituencyId , Long userId,Integer startIndex,Integer maxIndex,String order,String columnName);
	 
	 public List<Candidate> getPoliticanDetailsForSelectedlevel(List<Long> boothIds , Long constituencyId,Integer startIndex,Integer maxIndex,String order,String columnName);
	 
	 public List<Object[]> getAgeWiseCustomVoterDetails(Long constituencyId,Long locationValue,Long publicationDateId,String areaType,Long userId,String age);
	 public List<Long> getVotersCountForAttribute(Long userId,Long categoryValueId,Long casteId,String gender,String locationType,Long locationId,Long publicationId);
	 public List<Object[]> getVoterDetailsByAttributeIdAndCasteId(Long userId,Long categoryValueId,Long casteId,String gender,Integer startIndex,
				Integer maxRecords, String order, String columnName,String locationType,Long locationId,Long publicationId);
	 
	  public List<Object[]> getTotalVotersCountbyCategoryAndCaste(Long categoryValueId,Long casteId,Long locationId,Long publicationId,String locationType,Long userId);
	  
	  public List<Long> getInfluencingPeopleCountByCategoryAndCaste(Long categoryValueId,Long casteId,Long locationId,Long publicationId,String locationType,Long userId,String gender,String type);
	  
	  public List<InfluencingPeople> getInfluencingPeopleDataByCategoryAndCaste(Long categoryValueId,Long casteId,Long locationId,Long publicationId,String locationType,Long userId,String gender,String type,Integer startIndex,Integer maxIndex,String order,String columnName);
	  public List<Candidate> getPoliticianDataByCategoryAndCaste(Long categoryValueId,Long casteId,Long locationId,Long publicationId,String locationType,Long userId,String gender,String type,Integer startIndex,Integer maxIndex,String order,String columnName);
	  public List<Cadre> getCadreDataByCategoryAndCaste(Long categoryValueId,Long casteId,Long locationId,Long publicationId,String locationType,Long userId,String gender,String type,Integer startIndex,Integer maxIndex,String order,String columnName);
	  
	  public List<Object[]> getVoterDetailsByCasteStateForHamlet(Long hamletId,Long publicationDateId,Long casteStateId,Long userId,Long constituencyId);
	  
	  public List<Object[]> getBoothWiseTotalVoters(Long constituencyId,Long publicationId,String type);
	  
	  public List<Object[]> getBoothWiseTotalVotersInALocalEleBody(Long constituencyId,Long publicationId);
	  
	  public List<Object[]> getWardWiseTotalVotersCount(Long constituencyId,Long publicationDateId,Long localEleBodyId);
	  
	  public List<Object[]> getPanchayatsCountByMandalIdsList(List<Long> mandalIdsList,Long constituencyId,Long publicationId,String type);
	  
	  public List<Object[]> getBoothsCountByPanchayatIdsList(List<Long> panchayatIdsList,Long constituencyId,Long publicationId,String type);
	  
	  public List<Object[]> getMuncipalityWardsCount(Long constituencyId,Long publicationDateId,List<Long> muncipalityIdsList);
	  
	  public List<Object[]> getVotersCountAgeWise(Long fromAge,Long toAge,List<Long> boothIds);
	  
	  public List<Object[]> getVotersCasteDetailsForAgeRange(Long fromAge,Long toAge,List<Long> boothIds,Long userId);
	  
	  public List<Object[]> getVotersCountAgeWiseForPanchayat(Long fromAge,Long toAge,List<Long> boothIds);
	  
	  public Long getTotalVoters(Long boothId);
	  
	  public List<Object[]> getVotersCasteDetailsForAgeRangeInBooth(Long fromAge,Long toAge,Long boothId,Long userId);
	  
	  public List<Object[]> getVotersCountAgeWiseInBooth(Long fromAge,Long toAge,Long boothIds);
	  
	  public List<Long> getVotersCountForBooths(Long ids,Long publicationDateId);
	  
	  public List<Object[]> getTotalVotersByBooths(List<Long> boothIds);
	  
	  public List<Object[]> getVotersCasteDetailsForAgeRangeInBoothForSelectedCastes(Long fromAge,Long toAge,Long boothId,Long userId,List<Long> casteIds);
	  
	  public List<Object[]> getVotersCasteDetailsForAgeRangeForSelectedCastes(Long fromAge,Long toAge,List<Long> boothIds,Long userId,List<Long> casteIds);

	  public List<Long> findFamiliesCountByPublicationIdInAHamlet(Long locationValue,Long publicationDateId,Long userId);
	  
	  public List<Long> getAllImpFamilesCountForHamlet(Long locationValue,Long publicationDateId,Long userId);
	  
	  public Long getVotersCountInAAgeRangeByHamlet(Long hamletId,Long publicationDateId,Long userId,Long ageFrom, Long ageTo,String gender);
	  
	  public List<Object[]> getVoterDetailsForMessageCenter(Long constituencyId,Long publicationDateId,Long locationValue,String queryStr,Integer startIndex, Integer maxIndex,Long userId);
	  
	  public List<Object[]> getRecordsFromBoothPublicationVoter(Long constituencyId, Long publicationDateId);
	  
	  public List<Object[]> getVoterDetailsOfAConstituency(Long constituencyId, Long publicationDateId, Long userId);
	  
	  public List<Object[]> getVoterAgeDetailsForSelectedLocation(Long constituencyId,Long publicationDateId,List<Long> locationIdsList,String locationType);
	  
	  public List<Object[]> getAgeAndGenderWiseVotersCountInPanchayatOfConstituency(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo,String type,Long userId);
	  
	  public List<Object[]> getTotalVotersInPanchayatOfConstituency(Long constituencyId,Long publicationDateId,String type);
	  
	  public List<Object[]> getTotalVotersInPanchayatOfConstituencyByAge(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo,String type);
	  
	  public List<Object[]> getTotalVotersInBoothOfMuncipalityByConstituencyId(Long constituencyId,Long publicationDateId);
	  
	  public List<Object[]> getTotalVotersInBoothOfMuncipalityOfConstituencyByAge(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo);
	  
	  public List<Object[]> getAgeAndGenderWiseVotersCountInBoothsOfMuncipalityOfConstituency(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo,Long userId);
	  
	  public List<Object[]> getVotersDetailsAndCountDetailsByBoothId(Long userId , Long boothId, Integer startIndex,
				Integer maxRecords, String order, String columnName,String queryString,String queryForCategories,String queryForselect,boolean isCount);
	  
	  
	public List<Object[]> getVotersDetailsAnCountDetailsForHamletByPublicationId(
			Long userId , Long hamletId, Long publicationdateId, int startIndex,
			int maxIndex, String direction, String columnName, String queryStr,
			String queryForCategories, String queryForselect, boolean isCount);
	
	public List getVotersDetailsAnCountDetailsForWardByPublicationId(Long wardId,Long userId,Long constituencyId,Long publicationId,int startIndex,int  maxIndex,String order,String columnName,String queryStr,
			String queryForCategories, String queryForselect, boolean isCount);
	
    public List<Object[]> getVotersCountForPanchayatByPublicationIdAndHamlet(Long panchayatId,Long publicationDateId,Long userId);
	public List<Object[]> getPartyAndGenderWiseVotersCountByPublicationIdInAndPanchayatWithHamlets(Long userId , Long panchayatId ,Long publicationDateId);
    public List<Object[]> getPartyWiseCountForPanchayatByHamlets(Long userId,Long locationId,Long publicationDateId);
	public List<Object[]> getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationIdInHamlets(Long panchayatId,Long publicationDateId,Long startAge,Long endAge ,Long userId);



	  public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdForPartialPanchayat(Long userId,List<Long> locationIds,Long publicationDateId,Long constituencyId);
	  
	  public List<Object[]> getPartyWiseCastAndGenderWiseVotersCountByPublicationIdForPartialPanchayat(Long userId,List<Long> locationIds,Long publicationDateId,Long constituencyId);
	  
	  public List<Object[]> getVotersCountByHamletForPartialPanchayat(Long panchayatId, Long userId , Long publicationDateId,Long constituencyId);
	  
	  public List<Long> getAllImpFamilesCountForPartialPanchayat(Long panchayatId,Long publicationDateId,Long userId,Long constituencyId);
		
	  public List<Object[]>  getHamletsForPartialBooth(Long id);
	  
	  public List<Object[]> getPartialBoothHamlets(Long panchayatId,Long publicationId);
	  
	  public Long findVotersCountByPublicationIdForPartialPanchayat(Long userId,Long panchayatId,Long publicationDateId);
	  
	  public List<Object[]> getCastCategoryWiseVotersCountByPublicationIdForPartialPanchayat(Long userId,Long panchayatId,Long publicationDateId);
	  
	  public List<Object[]> getCastAndGenderWiseVotersCountByPublicationIdForPartialPanchayat(Long userId,Long panchayatId,Long publicationDateId);
	  
	  public Long getTotalCastCountForPartialPanchayat(Long userId,Long panchayatId,Long publicationDateId);
	  
	  public List<Object[]> getImpFamilesForPartialPanchayatByPublicationId(Long panchayatId,Long publicationDateId,Long userId);
	  
	  public List<Object[]> getVoterDetailsByCasteStateForPartialPanchayat(Long userId,Long panchayatId,Long publicationDateId,Long casteStateId);
	
	  public List<Object[]> getBoothAndHamletIdsByConstituencyId(Long constituencyId,Long publicationDateId,Long userId);
	  
	  public List<Object[]> getVoterAgeDetailsForPartialPanchayats(Long userId,Long publicationDateId,List<Long> panchayatIds);
	  
	  public List<Object[]> getVotersCountForMultiplePartialPanchayats(Set<Long> panchayatIds,Long publicationDateId,Long userId);
	  
	  public List<Object[]> getVoterAttributeDetailsForPartialPanchayat(Long userId,List<Long> attributeIds,Long locationId,Long constituencyId,Long publicationId);
	  
	  public List<Object[]> getVoterAttributeDetailsForPartialPanchayatByHamlet(Long userId,List<Long> attributeIds,List<Long> locationIds,Long constituencyId,Long publicationId);
	  
	  public List<Object[]> getVoterAttributeDetailsForPartialPanchayat(Long userId,Long attributeId,String locationType,Set<Long> locationIds,Long constituencyId,Long publicationId);
	  
	  public List<Object[]> getAgeWiseDetailsForPartialPanchayat(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId,Long startAge,Long endAge);
	
	  public List<Object[]> getPanchayatAgeWiseDetailsByHamletWise(Long userId , Long publicationDateId , List<Long> panchayatId);

	  public List<Object[]> getYoungVotersCount(Long constituencyId,Long publicationDateId,List<Long> locationIdsList,String locationType,Long ageFrom,Long ageTo);
	  
	  public List<Object[]> getCasteWiseDetailsForPartialPanchayat(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId);
	  
	  public List<Object[]> getAgeAndGenderWiseVotersCountInBoothsOfMuncipality(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo,Long userId);
	  
	  public Long getAgeAndGenderWisesMuncipaltiyVotersCount(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo,Long userId);
	  
	  public List<Object[]> getExpCasteForAgeAndGenderWisesMuncipaltiyVotersCount(Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo,Long userId,List<Long> casteIds);
	  
	  public List<Object[]> getAddedVotersBetweenTwoPublications(Long constituencyId,Long fromPublicationDateId, Long toPublicationDateId);
	  
	  public List<Object[]> getDeletedVotersBetweenTwoPublications(Long constituencyId,Long fromPublicationDateId, Long toPublicationDateId);
	  
	  public List<Object[]> getCustomWardWiseTotalVotersCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId);
	  
	  public List<Object[]>  getCasteGroupContsByCustomWardWise(Long userId,Long localElectionBodyId,Long publicationDateId,Long constituencyId);
	  
	  public List<Object[]>  getCasteWiseGenderWiseContsByCustomWardWise(Long userId,Long localElectionBodyId,Long publicationDateId,Long constituencyId);
	  
	  public List<Object[]> getCustomWardWiseTotalMaleFemaleVotersCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId);
	  
	  public List<Object[]> getCustomWardWiseFamilyVotersCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId);
	  
	  public List<Object[]> getCustomWardAgeCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId);
	  
	  public List<Object[]> getCustomWard18To22AgeCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId);
	  
	  //public List<Object[]> addedDeletedVoters(String locationType,Long locationId,Long fromPublicationDateId,Long toPublicationDateId,Long constituencyId);
	  

	
	  
	  public List<Object[]> getWardBoothWiseFamilyVotersCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId);
	  
	  public List<Object[]> getWardBoothTotalMaleFemaleVotersCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId);
	  
	  public List<Object[]> getWardBoothAgeCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId);
	  
	  public List<Object[]> getWard18BoothTo22AgeCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId);
		
	  public List<Object[]> getWardBoothWiseTotalVotersCount(Long localElectionBodyId, Long userId,Long publicationId,Long constituencyId);
	  
	  public List<Object[]>  getCasteGroupContsByWardBoothWise(Long userId,Long localElectionBodyId,Long publicationDateId,Long constituencyId);
			
	  public List<Object[]>  getCasteWiseGenderWiseContsByWardBoothWise(Long userId,Long localElectionBodyId,Long publicationDateId,Long constituencyId);
	  public List<VoterFlag> getFlagDetailsForSelectedlevel(List<Long> boothIds , Long constituencyId,Integer startIndex,Integer maxIndex,String order,String columnName);
	  /**
		 * @param  List of boothIds
		 * @param ConstituencyId
		 * @return List<?> 
		 */
		public List<?> getVoterDetailsByBoothAndConstituency(List<Long> boothId,long ConstituencyId);
		 /**
			 * @param boothId
			 * @param ConstituencyId
			 * @return List<?> 
			 */
		public List<?> getVoterDetailsByBoothAndConstituency(long boothId,long constituencyId);

		public List<Object[]> getHouseNosForBooth(Long constituencyId,Long publicationId,Long minVal,Long maxVal,Integer startIndex,Integer maxIndex);
		public List<Object[]> getFamilyWiseInfoForBooth(Long boothId,List<String> hnos);
		
		public List<Object[]> getConstyPublicationIdByVoterId(String voterCardNo);
		
	  public BoothPublicationVoter getVoterBySerialNo(Long constituencyId,String partNo,Long serialNo);
	  
	  public List<Object[]> getlist1();
	  public List<Object[]> getlist2(Long boothId,String houseNo);
	  public List<Object[]> getlist3(Long boothId,String houseNo);
	  public List<String> getVoterNamesOfAConstituency(Long constituencyId,Long publicationDateId);
	  
	  public List<Object[]> getVoterDetailsOfAConstituencyAndPublication(Long constituencyId, Long publicationDateId);
	  
	  public List<Voter> getVoterInfoByVoterIds(Long constituencyId,Long publicationDateId,List<Long> voterIds);	  
	  public List<Object[]> getConstiPanchayatVoters(Long constituencyId,Long publicationId,Set<Long> partialPanchatyats,boolean isYoungVoters);
	  
	  public List<Object[]> getConstiPanchayatVotersForPartial(Long constituencyId,Long publicationId,Set<Long> partialPanchatyats,boolean isYoungVoters);
	  
	  public List<Object[]> getConstiLEBVoters(Long constituencyId,Long publicationId,boolean isYoungVoters);
	  
	  public List<Object[]> getpanchayts(Long constituencyId,Long publicationDateId);
	  
	  public List<Object[]> getPdfsForVotersAddress();	  
		public List<Object[]>  getHamletsForPartialBooth1(Long boothId);
		 
		public List<Object[]> getPartialBoothHamlets1(Long panchayatId,Long publicationId);
		 
		public List<Object[]> getPartialBoothHamlets2(Long panchayatId,Long publicationId);
		 
		public List<Object[]> getPartialBoothHamlet(Long panchayatId,Long publicationId);
	  
	  public List<Object[]> getTotalVotersOfBoothByConstituencyId(Long constituencyId,Long publicationDateId);
	  
	  public Long getMaxSerialNoOfABooth(Long boothId);
	  public Long getVoterCountForToPublication(Long constituencyId,Long publicationDateId);

	  public List<Object[]> getConstituencyDetails(Long constituencyId,Long publicationDateId,String type);
	  
	  public List<Object[]> getCasteCount(Long publicationId,Long constituencyId,List<Long> panchayatIds,Long ageFrom,Long ageTo);
	  
	  public List<Object[]> getCasteCountForPartial(Long publicationId,Long constituencyId,List<Long> panchayatIds,Long ageFrom,Long ageTo);
	  
	  public List<Object[]> getTotalVotersByAge(Long publicationId,Long constituencyId,List<Long> panchayatIds,Long ageFrom,Long ageTo);
	  
	  public List<Object[]> getTotalVotersCountForPartial(Long publicationId,Long constituencyId,List<Long> panchayatIds,Long ageFrom,Long ageTo);
	  
	  public List<Object[]> getTotalVotersByAgeForMunicipality(Long publicationId,Long constituencyId,Long ageFrom,Long ageTo);
	  
	  public List<Object[]> getCasteCountForMunicipality(Long publicationId,Long constituencyId,Long ageFrom,Long ageTo);
	  
	  public List<Object[]> getPanchayatwiseImpFamiles(Long publicationId,Long panchayatId);
	  
	  public List<Object[]> getElderPersonDetails(Long publicationId,Long boothId,String houseNo);
	  
	  public List<Object[]> getImpFamilesForMuncipality(Long publicationId,Long muncipalityId);
	  
	  public List<Object[]> getVoterDetaildsByBoothWise(Long boothId);
	  
	  public List<Object[]> getVoterTeluguNames(Long boothId);
	  
	 public List<Object[]> searchVoterdetailsByBoothAndName(Long boothId,String searchName,String searchType,Long publicationDateId,int firstRecord,int maxResult,String queryType);
	  
	  public List<Object[]> getDetailsByVoterIdCardNo(String voterIdCard,Long publicationId);
	  
	  public Long searchVoterdetailsByBoothAndNameCount(Long boothId,String searchName,String searchType,Long publicationDateId,int firstRecord,int maxResult,String queryType);
	  
	  public List<Long> getBoothIdsOfVoterIds(List<Long> voterIds,Long publicationDateId);
	  
	  public List<Object[]> getVotersDetailsForPanchayatByPublicationIdAndPAnchayatId(
				Long panchayatId, Long publicationDateId);
	  public List<Object[]> getVotersDetailsForConstId(Long constId, Long publicationDateId);
	  public List<Object[]> getVotersDetailsForConstIdBasedOnName(Long constId, Long publicationDateId,String voterName) ;
	  public List<Object[]> checkVoterInState(Long publicationDateId,String voterName,String relativeName,String gender,int age);
	
 public List<Object[]> getvoterDetailsInAPanchyat(Long panchayatId);
	  
	  public List<Object[]> getCasteDetailsForACaste(Long constituencyId,Long publicationDateId,Long casteStateId);
	  public List<Object[]> getUrbanVotersDetails(
				Long constituencyId, Long publicationDateId);
	  public List<Object[]> getTotalVotersByBoothsForVerfier(Long boothIds,Long publicationDate);
	  
	 public List<SurveyDetailsInfo> getVotersDetailsByBoothId(Long boothId);

	  
	  
	  
	  public List<Object[]> getAllBoothsInConstituency(Long constId,Long publicationDateId);
	 // public List<Object[]> getVotersDetailsForBoothByPublicationIdAndBoothId(Long boothId, Long publicationDateId);
	  public List<Object[]> getVotersDetailsForBoothByPublicationIdAndBoothId(Long boothId, Long publicationDateId,int min,int max) ;
   
	  public List<Object[]> getVotersDetailsByPublicationIdAndBoothIds(List<?> boothIds, Long publicationDateId,Long constId) ;

	  public List<Object[]> getVotersDetailsByPublicationIdAndAvoidingBoothIds(List<?> boothIds, Long publicationDateId,Long constId) ;
	  public List<Object[]> getVotersDetailsByPublicationIdAndCOnstituencyIds(Long publicationDateId,Long constId) ;
	  public List<Long> getAllVoterIdsByBoothIdsAndPublicationDateId(List<Long> boothIds , Long publicationDateId);
	  public List<Object[]> getBoothIdsDetailsOfVoterIds(List<Long> voterIds,Long publicationDateId);

	  public List<Object[]> getBoothWiseCasteDetails(Long boothId);
	  
	  public List<Object[]> getLatestBoothDetailsOfConstituency(Long constituencyId);
	  
	  public List<Object[]> getBoothWiseVoterDetails(List<Long> boothIds);
	  public List<Object[]> getVoterDetailsByBoothID(List<Long> boothId);
	  public List<Object[]> getTotalVoters(List<Long> boothIds);
	  public Long getTotalVotersForPanchayat(List<Long> boothIds);
	  public Long getTotalVotersForConstituency(Long constituencyId);
	  public List<Object[]> getTotalVotersForAllConstituencies(List<Long> constituencyIds);
	  public Long getTotalVotersForBoothId(Long boothId);
	  public List<Object[]>  getTotalVotersForConstituencyByBoothWise(Long constituencyId);
	  
	  public List<Object[]> getTotalVotersAndBoothTypeForConstituencyByBoothWise(Long constituencyId);
	  public List<Object[]> getConstyPublicationIdByVoterId1(String voterCardNo);
	  public Long getTotalVoterByBooths(List<Long> boothIds);
	  public List<Object[]> getBoothWiseTotalVotersByConstituencyId(Long constituencyId);

	  public List getVotersDetailsForCadreRegistratiobByconstituencId(Long constituencyId, Long publicationDate,String queryStr,Long panchayatId,Long boothId,String villagesCovered,Integer startIndex,Integer maxIndex);
	  public List getVotersDetailsForCadreRegistratiobByconstituencIdRTC(Long constituencyId, Long publicationDate,String queryStr,Long panchayatId,Long boothId,String villagesCovered,Integer startIndex,Integer maxIndex);
	  
	  public List<Object[]> getCTPVoterDetailsByBooth(Long boothId);
	  
	  public List<Booth> getVoterAddressDetails(Long voterId);
	  
	  public List<Object[]> getFamilyDetaislByHouseNoAndBoothId(Long boothId,String houseNo);
	  public List<Object[]> getVotersDetailsByCTPSearchCriteria(Long publicationDateId,Long id,String queryString,Long userId);
	  
	  public List<String> getPartNo(Long constituencyId,Long voterId);
	  public List<Object[]> getLocationWiseVoterAgeRangeCount(List<Long> locationIdsList,String locationType,Long publicationDateId);
	  public Long getVotersDetailsForCadreRegistratiobByconstituencIdCount(Long constituencyId, Long publicationDate,String queryStr,Long panchayatId,Long boothId,String villageCovered);
	  public Long getVotersDetailsForCadreRegistratiobByconstituencIdCountRTC(Long constituencyId, Long publicationDate,String queryStr,Long panchayatId,Long boothId,String villageCovered);
	  
	  public List<Long> getVoterIdsInABoothOfGivenHouseNos(Long constituencyId,Long publicationDateId,String partNo,List<String> houseNos);
	  public List<Object[]> getFamilyWiseVotersInABooth(Long constituencyId,Long publicationDateId,String partNo);
	  public List<Object[]> getVotersCountByPublicationIdLocationIds(String type,Set<Long> ids,Long publicationDateId,Long constituencyId);
	  public List<Object[]> getTotalVotersByBoothsForVerfierForCTP(Long boothId);
	  
	  public List<Object[]> getVoterCadreCasteDetailsBySearchCriteria(Long stateId,String locationType,Long locationId,Long casteStateId,String nameStr);
	  public List<Object[]> getVoterCadreCasteDetailsByName(Long stateId,String locationType,Long locationId,Long casteStateId,String nameStr);
	  public List<Object[]> getVoterCasteWiseCountDetailsByName(Long stateId,String locationType,Long locationId,Long casteStateId,String nameStr);
	  
	  public List<Object[]> getVotersDetailsForCadreRegistratiobByLocationIds(Long stateId,Long constituencyId, Long publicationDate,String queryStr,
				Long tehsilId,Long boothId,Integer startIndex,Integer maxIndex);
	  public Long getVotersDetailsForCadreRegistratiobByLocationIdsCount(Long stateId,Long constituencyId, Long publicationDate,String queryStr,Long tehsilId,Long boothId);
	  
	  public List<Booth> getOtherStateVoterAddressDetails(Long voterId);
	  public Object getBoothPartNumberByVoterId(String voterId);
	  public List<Object[]> getCadreVoterInfo(Long wardId,Integer firstResult,Integer maxResults);
	  
	  public List<Object[]> getBoothVoterDetails(Long boothId,Long voterId);
	  
	  public List<Object[]> getBoothVoterDetails(Long voterId);
	  public List<Object[]> getVoterInfo(List<String> mobileNos);
	  public List<Object[]> getConstyPublicationIdByVoterIdPublicationId(String voterCardNo,Long publicationId);
	  
	  public List<Object[]> getVoterImagesByVoterIds(List<Long> voterIds,Long publicationId);
	  
	  public List<Object[]> getVoterImageDetailsByVoterId(Long voterId);
	  public List<Object[]> getVoterDetailsVoterId(String voterIDCardNo);
	  public List<Object[]> getVoterAddressDetailsVoterId(Long voterId);
	  public List<Object[]> getVoterDetailsByVoterCardNumber(String voterIDCardNo,Long constId);
	  
	  public List<Object[]> getVotersConstituencyDetails(List<Long> voterIds,Long publicationId);
	  public List<Object[]> getVoterImagesVoterIdcardNo(List<String> voterIDCardNos);
	  public Long getTotalAvailableVotesByLocationId(Long locationId,String locationType,Long boothPublicationDateId,Long constituencyId,List<Long> constituencyIdsList);
	  public Object[] getBoothDetailsByVoterId(String voterId,Long publicationId);
	  public List<String> getPartNoForRTCRegistration(Long constituencyId,Long voterId);
	  public List<Object[]> getVotersBySearch(Long searchVal,String searchType,String name,String hNo,String voterCardNo,List<Long> assignedBoothIds);
	  public List<Object[]> getRegisteredCadresForVoterIds(List<Long> voterIds);
	  public List<Object[]> getVoterDetails(Long voterId,Long publicationDateId); 
	  public List<Object[]> getFamilyVoterDetails(Long boothId,String houseNo);
	  public List<Object[]> getOnliCadRegistrSearchVoteDetails(Long searchVal,String searchType,String type,String typeVal);
	  public List<Object[]> getVoterLocationDetailsByVotersIdsList(List<Long> voterIdsList);
	  public List<Long> getLocalElectionBodyByVoterId(Long voterId);
	  public Voter getVoterByVoterIDCardNo(String voterCardNo);
	  public List<Object[]> getTotalVoterGroupByLocation(String location, Long constId );
	  public List<Object[]> getVoterIdDetailsByPublicationIdAndCardNo(String voterCardNo,Long publicationId);
	  public List<Object[]> getTotalVoterGroupByLocationUrb(String location, Long constId );
	  public List<Object[]> getTotalVoterGroupByLocationUrbWard(String location, Long constId );
	  public List<Object[]> getBoothsOfVoterIds(List<Long> voterIds,Long publicationDateId);
	  public Long getDivisionWiseVoters(Long locationId,Long levelId, Long publicationDateId);
	  public Long getPublicationDateIdByVoterIDCardNo(String voterCardNo);
	  public Long getPublicationDateIdByVoterID(Long voterId);
	  public List<Object[]> getVoteDetailsByLocation(Long searchVal,Long locationLevel,String type,String typeVal);
}
