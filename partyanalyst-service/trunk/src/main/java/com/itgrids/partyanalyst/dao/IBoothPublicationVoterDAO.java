package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SmsVO;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.Voter;

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
				Long panchayatId, Long publicationDateId, Integer startIndex,
				Integer maxRecords, String order, String columnName,String queryString,String queryForCategories,String queryForselect,boolean isCount);
	
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
	
	public List<Object[]> getVotersDetailsBySearchCriteria(Long publicationDateId,Long id,Integer startRecord,Integer maxRecords,String queryString);
	
	public List<Object[]> getCastWiseCount(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId);
	 
	public List<Object[]> getPartyWiseCount(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId); 
	
	public List<Object[]> getParties(Long userId,String locationType,Long locationId,Long publicationDateId,Long constituencyId);
	
	public List<Long> getVotersCountBySearchCriteria(Long publicationDateId,Long id,String queryString);
	
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
	  public List<Object[]> getVotersDetailsAndCountDetailsByBoothId(Long boothId, Integer startIndex,
				Integer maxRecords, String order, String columnName,String queryString,String queryForCategories,String queryForselect,boolean isCount);
	   
}
