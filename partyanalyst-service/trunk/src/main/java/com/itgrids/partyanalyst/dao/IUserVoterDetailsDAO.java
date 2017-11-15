/* 
 * Copyright (c) 2009 IT Grids.

 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SMSSearchCriteriaVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.Locality;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterFlag;

public interface IUserVoterDetailsDAO extends GenericDao<UserVoterDetails, Long>{

	public List<UserVoterDetails> getUserVoterDetails(Long voterId,Long userId);
	
	public void updateUserVoterDetails(Long voterId,Long userId,Long partyId,Long castStateId);
	
	public List<Object[]> getCasteByVoterId(Long userId,List<Long> voterId);
	
	public void updateUserVoterPartyDetails(Long voterId,Long userId,Long partyId,Long castStateId);
	
	public void updateUserVoterCastDetails(Long voterId,Long userId,Long partyId,Long castStateId);

	public List<UserVoterDetails> getAllUserVoterDetails(List<Long> voterIds,Long userId);
	
	public List<Object[]> getUserVoterDetailsByVoterIds(List<Long> voterIds,Long userId);
	
	public List<UserVoterDetails> getUserVoterDtlsVoterIds(List<Long> voterIds,Long userId);
	
	public List<Object[]> getPartyAndCasteDetails(Long voterId,Long userId);
	
	public void updateUserVoterDetails(Long voterId,Long userId,Long partyId,Long castStateId,Long localitityId, Long hamletId);
	public List<Object[]> getHamletsIdsForUser(Long panchayatId , Long userId);


	
	
	/** This Method is used to get VoterIds based on hamletId and UserId  */
	

	  public List<Object> getVoterIdsBasedOnHamletId(Long hamletId, Long userId);
	
	
	
	public List<Object[]> getVotersCountByGenderForHamlet(Long userId , Long hamletId);
	
	public List<Voter> getVoterDetailsByHamletId(Long hamletId , Long userId);
	
	public List<Long> getUserHamletsByPanchayatId(Long userId , Long panchayatId);
	public void updateUserVoterDetails2(Long voterId,Long userId,Long partyId,Long localitityId,Long hamletId);
	public void updateUserVoterDetails3(Long voterId,Long userId,Long castStateId,Long localitityId,Long hamletId);
	public void updateUserVoterDetailsForLocality(Long voterId,Long userId,Long localitityId, Long hamletId);
   
    public List<Long> getVotersCountForALocality(Long hamletId,Long id,Long userId, String query , Long publicationId);
	
	public List<Object[]> getVotersCountByGenderForLocalityInHamlet(Long userId , Long hamletId , Long localityId,Long publicationDateId,String query1);
	public List<Long> getVoterIdsBasedOnHamletAndLocality(Long hamletId ,Long localityId , Long userId ,String cond);
	
	public Long getTotalVotersCountInALocality(Long userId ,Long hamletId,Long localityId,Long publicationDateId);
	public List<Long> getVoterIdsForuserByHamletIdsByCaste(Long userId ,Long hamletId,Long casteStateId);
	
	public List<Object[]> getVotersCountByGenderForLocalAreas(List<?> voterIds , Long userId);
	public List<Object[]> getLocalityIdsForUser(Long hamletId , Long userId,List<?> voterIds);
	public List<?> getVoterIdsBasedOnVoterIdsAndPublication(
			Long publicationDateId ,Long id,Long userId,String type);
	public List<?> getVotersIdsByHamletId(Long hamletId,Long userId,String locationType);
	
	public List<Long> getVoterIdsByLocalityForUser(Long localisyId,Long hamletId,Long userId,Long casteStateId,String queryStr);
	public List<Object[]> getAgeWiseInfoForUser(List<?> voterIds);
	public List<Object> getHamletsIdsForUserByPanchayat(Long panchayatId , Long userId );
	public List<Object[]> getAgeDataForPanchayatUser(List<?> voterIds );
	 public List<?> getVotersDetailsByHamletPublication(Long hamletId, Long userId, Integer startIndex,
				Integer maxRecords, String order, String columnName);
	 public List<?> getVotersBasedOnVoterIdsAndPublication(Long publicationDateId , List<?> voterIds,String coloumName , String order);
	 public List<?> getVotersCountByHamlet(Long hamletId,Long userID);
	 
	 public List<Object[]> getTotalVotersCountInABooth(Long userId ,Long boothId,Long publicationDateId);
	 public List<Long> getUserHamletsByBoothId(Long userId , Long boothId , Long pubId);
	 public List<Object[]> getTotalVotersCountInABoothForHamlet(Long userId ,Long id,Long publicationDateId,String type,Long constituencyId);
	 public List<Long> getUserBoothsByHamletId(Long userId , Long hamletId , Long pubId , String condition);
	 public List<Object[]> getVoterIdsForuserinHamletByBoothsandByCasteId(Long userId ,Long hamletId,Long casteStateId ,long boothId,long publicationId);
	 public List<Object[]> getAgeDataForBoothByHamlets(Long userId,Long publicationDateId,Long boothId,String type );
	 public List<Long> getHamletsExistedInABoothForUser(Long userId,Long  id,Long  publicationDateId,Long  constituencyId);
	 
	 public List<Object[]> getVoterDataForHamlet(Long hamletId , Long publicationId ,Long startIndex, Long maxIndex , String sort,String order );

	// public List<Object[]> getAgeDataForPanchayatUser(List<?> voterIds,Long userId,String type,String male,String female,long ...age);
	public List<Object[]> getAgeDataForPanchayatUser(List<Long> id ,Long publicationDateId , List<?> voterIds,Long userId,String type,String male,String female,long ...ages);

	 
	 public List<Object[]> getAgeDataForBoothByHamlets(Long constituencyId,Long userId,Long publicationDateId,Long boothId ,String type,String male,String female,long ...ages);
	 
	 public List<Object[]> getAgeWiseInfoForUser(List<?> voterIds,Long userId,String male,String female,long ...ages);
	 
	 public List<Object[]> getLocalityIdsForUser(Long hamletId , Long userId,List<?> voterIds,String male,String female,long ...ages);
	 
	 public List<Object[]> getGenderWiseVoterDetailsForCustomWard(Long id , Long publicationDateId ,Long userId);
	 
	 public List<Object[]> getImpFamilesForCustomWard(List<Long> wardIds,Long publicationDateId,String queryString,Long userId );

	 public List<Object[]> getWardsBYLocalElectionBodyId(Long id , Long publicationId ,Long userId);
	 
	 
	 
	public void updateUserVoterDetails2WithWard(Long voterId,Long userId,Long partyId,Long localitityId,Long wardId);
	
	public void updateUserVoterDetailsWithWard(Long voterId,Long userId,Long partyId,Long castStateId,Long localitityId, Long wardId);
	
	public void updateUserVoterDetails3WithWard(Long voterId,Long userId,Long castStateId,Long localitityId,Long wardId);
	
	public void updateUserVoterDetailsForLocalityWithWard(Long voterId,Long userId,Long localitityId, Long wardId);
	
	public List<Long> getBoothIdsByCustomWardId(Long customWardId,Long constituencyId, Long publicationDateId);
	
	public List<Object[]> getAllLocalitiesForHamletOrWard(String type,Long userId, Long id,Long publicationDateId,String queryCond);
	
	public List<Object[]> getPanchayatWiseHamletsAssignedDetailsInAConstituency(Long constituencyId,Long publicationDateId);
	
	public List<?> getVoterIdsBYLocalElectionBodyId(Long id , Long publicationId ,Long userId ,String type);
	
	public List<Object[]> getBoothsForCustomWard(Long wardId,Long constituencyId,Long publicationDateId,Long userId);
	
	public List<Object[]> getPanchayatWiseHamletsAssignedDetails(Long constituencyId,Long publicationDateId,Long userId);
	
	public List<Long> getCountForSelectedTypeInHamlet(Long hamletId,Long userId,String type,String selLevel);
	
	public List<Cadre> getCadreDetailsForSelectedHamlet(Long hamletId,Long userId,Integer startIndex,Integer maxIndex,String order,String columnName,String selLevel);
	
	public List<InfluencingPeople> getInfluencingPeopleDetailsForSelectedHamlet(Long hamletId,Long userId,Integer startIndex,Integer maxIndex,String order,String columnName,String selLevel);
	
	public List<Candidate> getCandidateDetailsForSelectedHamlet(Long hamletId,Long userId,Integer startIndex,Integer maxIndex,String order,String columnName,String selLevel);
	
	public List<Object[]> getVotersCountBasedOnGenderForSelectedWard(Long userId,Long customWardId,Long publicationDateId);
	
	public List<Object> getDistinctWardsOfLocalElectionBodyId(Long id,Long publicationDateId,Long userId);
	
	public List<Object[]> getAgeWiseDetailsInSelectdCustomWard(Long wardId,Long userId,Long publicationDateId,Long minAge,Long maxAge,Long constituencyId);
	
	public List<Object[]> getAbove60AgeWiseDetailsInSelectdCustomWard(Long wardId,Long userId,Long publicationDateId,Long age,Long constituencyId);
	
	public List<Long> getBoothsInACustomWard(Long wardId,Long userId,Long publicationDateId,Long constituencyId);

	public List<Object[]> getCountDetailsInSelectdCustomWard(List<Long> boothIds,Long userId,Long publicationDateId);
	
	public List<Object[]> getVoterDetailsForCustomWardByBooth(Long boothId,Long publicationDateId,Long userId,Long casteStateId);
	
	public List<Object[]> getcasteForVoter(List<Long> voterIds,Long userId);
	
	public List<Long> getWardsCountByLocalEleBodyId(Long localEleBodyId,Long constituencyId,Long userId,Long publicationDateId);
	
	public List<Long> getBoothsCountForCustomWard(Long wardId,Long constituencyId,Long publicationDateId,Long userId);
			
	public List<Object[]> getHamletOrWardList(Long userId,Long Id,String type);
	
	public List<Object[]> getLocalAreaWiseAgeDetailsForCustomWard(Long constituencyId,Long publicationDateId,Long customWardId,Long userId,String ageRange);
	
	public List<Object[]> getLocalAreaWiseAgeDetails(Long constituencyId,Long publicationDateId,Long customWardId,Long userId,Long minAge,Long maxAge,String type);
	
	public List<String> getLocalityByCustomWardId(Long constituencyId,Long publicationDateId,Long id,Long userId,String type);
		
	public List<Object[]> getVoterDetailsForSurveyForm(Long voterId,Long userId);
	
	public List<Object[]> getWardsForMuncByConsIdAndUserId(Long constituencyId,Long localEleBodyId,Long publicationDateId,Long userId);
	
	public List<Long> getVotersCountBySearchCriteria(Long publicationDateId,Long id,String queryString);
	
	public List<Object[]> getVotersDetailsBySearchCriteria(Long publicationDateId,Long id,Integer startRecord,Integer maxRecords,String queryString);
	
	public List<Object[]> getCasteAssignedVotersList(Long constituencyId,Long publicationId,String type,Long userId);
	
	public List<Object[]> getWardWiseTotalVotersCount(Long constituencyId,Long publicationDateId,Long localEleBodyId,String type,Long userId);
	
	public List<Object[]> getBoothsForCustomWardIdsList(List<Long> wardIdsList,Long constituencyId,Long publicationDateId,Long userId);
	
	 public List<Object[]> getWardsBYLocalElectionBodyId(List<Long> ids , Long publicationId ,Long userId);
		
	public List<Object[]> getVoterIdAndMobileNoByLocalityForUser(Long localityId,Long hamletId,Long userId,Long casteStateId,String queryStr);
	
	public List<Object[]> getVoterIdAndMobileNoForuserByHamletIdsByCaste(Long userId ,Long hamletId,Long casteStateId);
	
	public List<Object[]> getVoterIdAndMobileNoByVoterIdsList(List<Long> voterIdsList,Long userId);
	
	public List<Long> getHamletIdsListByUserIdAndConstituencyId(Long constituencyId,Long publicationDateId,Long userId);
	
	public List<Long> getWardIdsByLocalEleBodyId(Long constituencyId,Long userId,Long publicationDateId,Long localEleBodyId);
	
	public List<Object[]> getHamletIdsListByMandalIdsList(Long constituencyId,Long publicationDateId,Long userId,List<Long> locationValuesList,String type);
	
	public List<Object[]> getBoothsCountByWardIdsList(List<Long> locationIdsList,Long constituencyId,Long publicationId,Long userId,String type);
	
	public List<Object[]> getWardsCountByBoothIdsList(List<Long> boothIdsList,Long constituencyId,Long publicationId,Long userId);
	
	public List<Object[]> getWardsCountByMuncipalityIdsList(List<Long> muncipalityIdsList,Long constituencyId,Long publicationId,Long userId);
	
	public List<Object[]> getCasteDetailsOfVoterByBoothId(Long boothId,Long publicationId,Long userId);
	
	public List<Object[]> getCasteDetailsOfVoterByBooths(List<Long> boothIds,Long publicationId,Long userId);
	
	public List<Object[]> getUserAssignedVotersCastesByCosntiId(Long constituencyId,Long userId);
	
	public List<Object[]> getUserAssignedCasteCountByBoothIdAndPublication(List<Long> boothIds,Long publicationId,Long userId);
	
	public List<Object[]> getCasteInHamlet(List<Long> hamletIds,Long userId,Long publicationId);

	public List<Object[]> getVoterCountByHamlet(List<Long> hamletIds,Long userId,Long publicationId);
	
	public Integer updateVoterMobileNo(String mobileNo, Long voterId);
	
	public List<Object[]> getCasteDetailsOfVoterByLocationId(Long panchayatId,Long publicationId,Long userid,String locationType);
	
	public void updateUserVoterDetails(Long voterId,Long userId,Long partyId,Long castStateId,Long localitityId, Long hamletId,String mobileNo);
	
	public void updateUserVoterDetailsWithWard(Long voterId,Long userId,Long partyId,Long castStateId,Long localitityId, Long wardId,String mobileNo);
	
	public List<Object[]> getCaste3();
	
	public List<Object[]> getPanchayatWiseCasteAssigned();
	
	public List<UserVoterDetails> getUserVoterDetailsOfAConstituencyForAPublication(Long constituencyId, Long publicationDateId, Long userId);
	
	public List<Object[]> getVoterAgeDetailsForHamlet(Long constituencyId,Long publicationDateId,List<Long> locationIdsList,Long userId);
	
    public List<Long> getVoterDetailsByCaste();
	
	public List<Object[]> getAllTheCastesOfConstituency(Long constituencyId , Long userId,Long publicationDateId);
	public List<Object[]> getVotersDetailsBySearchToSendSMS(String queryString , SMSSearchCriteriaVO searchVO );
	public List<Long> getVotersDetailsCountBySearchToSendSMS(String queryString,SMSSearchCriteriaVO searchVO);
	
	public List<Object[]> getCasteDetailsOfVoterByBoothIds(List<Long> boothId,Long publicationId,Long userId);
	
	//public List<Object[]> getCasteDetailsOfVoterByConstituency(Long constituencyId,Long publicationId,Long userId,List<Long> casteIds);

	public List<Object[]> getYoungVoterAgeDetailsForHamlet(Long constituencyId,Long publicationDateId,List<Long> locationIdsList,Long userId,Long ageFrom,Long ageTo);
	
	public List<Object[]> getYoungerVoterDetailsForHamlet(Long publicationDateId ,Long id,Long userId,String type,Long ageFrom,Long ageTo);
	
	public List<Object[]> getYoungerVoterDetailsForHamletBylocationIdsList(List<Long> locationIds, Long publicationDateId,Long userId,String type,Long ageFrom,Long ageTo);
	
	public List<Object[]> getTotalVoterForHamletBylocationIdsList(List<Long> locationIds, Long publicationDateId,Long userId,String type);
	
	public List<Object[]> getYoungVotersForHamlet(Long constituencyId,Long userId,Long publicationDateId,Long boothId,Long ageFrom,Long ageTo,String type);
	
	public List<Object[]> getTotalYoungVotersForHamlet(Long constituencyId,Long userId,Long publicationDateId,Long boothId,String type);
	
	public List<Object[]> getYoungerVotersForLocality(
			 Long publicationDateId ,Long id,Long userId,String type,Long ageFrom,Long ageTo);
	
	public List<Object[]> getGenderWiseVotersCountForLocality(Long constituencyId,Long publicationDateId,Long userId);
	
	public List<Object[]> getLocalityIdsForHamletAndBooth(Long constituencyId,Long publicationDateId,Long userId,String tempVar);
	
	public List<Object[]> getFamiliesCountForLocality(Long constituencyId,Long publicationDateId,Long userId);
	
	public List<Object[]> getLocalityWiseVoterAgeInfo(Long constituencyId,Long publicationDateId,Long userId);
	
	public List<Object[]> getLocalityWiseYoungVotersAgeInfo(Long constituencyId,Long publicationDateId,Long userId,Long ageFrom,Long ageTo);
	
	public List<Object[]> getLocalityWiseFamilyDetails(Long constituencyId,Long publicationDateId,Long userId);
	
	public List<Object[]> getLocalityWiseCasteDetails(Long constituencyId,Long publicationDateId,Long userId);
	
	public List<Object[]> getLocalityTotalCastes(Long constituencyId,Long publicationDateId,Long userId);
	
	public List<Object[]> getCadreCaste(List<Long> cadreIdsList);
	
	public List<Object[]> getHamletBoothInfo(Long constituencyId,Long publicationDateId,Long userId);
	
	public List<Object[]> getWardIdsByLocalEleBodyIdPublicationId(Long constituencyId,Long userId,Long publicationDateId,Long localEleBodyId);
	
	public List<Long> getAllWardIdsByLocalEleBodyIdPublicationId(Long constituencyId,Long userId,Long publicationDateId,Long localEleBodyId);
	
	public List<VoterFlag> getFlagDetailsForSelectedHamlet(Long hamletId,
			Long userId,Integer startIndex,Integer maxIndex,String order,String columnName,String selLevel);
	public List getCasteCategory(Long userId,Long voterId);
	
	public List<Locality> getAllLocatiesInAConstituency(Long constituencyId,Long publicationDateId,Long userId);
	
	public List<Object[]> getCountForCasteType(Long userId);
	
	public List<Long> getMatchtedRecordsForACaste(Long userId,Long casteStateId,List<Long> voterIdsList);
	
	public List<Long> getUnmatchtedRecordsForACaste(Long userId,Long casteStateId,List<Long> voterIdsList);
	
	public Integer updateCasteInsertType(List<Long> userVoterDetailsIdsList,Long casteInsertTypeId);
	
	public List<Long> getAvailableVoterIdsList(Long userId,Long casteStateId,List<Long> voterIdsList);
	
	public List<Object[]> getCasteForVoter(List<Long> voterId);
	
	public List<UserVoterDetails> getVoterDetailsByVoterId(Long voterId,Long userId);
	
	public Integer updateCasteStateId(Long casteStateId, Long voterId);
	
	public List<UserVoterDetails> getUserVoterDetailsByVoterCardNo(String voterCardNo);
	
	public List<Object[]> getCasteReport(Long constituencyId,Long publicationId,String type,Long userId);
	
	public List<Object[]> getVotersDetailsBySearchCriteriaForHouseHolds(Long publicationDateId,String qry);
	
	public List<Object[]> getVoterHnoAndBooths(Long constituencyId,Long publicationId);
	
	public List<Object[]> getElderVoterDetails(Long boothId,String hno);
	
	public List<Object[]> getLocationForVoter(List<Long> boothIds,String type);
	
	public List<Object[]> getHamletForVoter(List<Long> voterIds,Long userId);
	
	public List<Object[]> getCasteForVoter(List<Long> voterIds,Long userId);
	
	public List<UserVoterDetails> getVoterDetailsByUserIdAndVoterId(Long voterId,Long userId);
	public List<Object[]> getLocalbodyCasteReport(Long constituencyId,Long publicationId,Long userId);
	public List<Long> getTehsils(Long constituencyId,Long publicationId);
	public List<Object[]> getPartialPanchayats(Long tehsilId,Long constituencyId,Long publicationId);
	public List<Object[]> getCasteReportForPartial(Long constituencyId,Long publicationId,String type,Long userId,String partialIds,Long tehsilId);
	public List<Object[]> getCasteReportForNotPartial(Long constituencyId,Long publicationId,String type,Long userId,String partialIds,Long tehsilId);
	
	public List<Object[]> getVoterCountInLocation1(Long constituencyId,Long publicationId,Long userId,Set<Long> partialPanchayatIds);
	
	public List<Object[]> getPartialPanchayatsForConstituency(Long constituencyId,Long publicationId);
	
	public List<Object[]> getVoterCountInLocation(Long constituencyId,Long publicationId,Long userId,Set<Long> partialPanchayatIds);
	public Long getCasteVoterNamesOfAConstituency(Long constituencyId,Long publicationId,Long userId);
	
	public List<Object[]> getWardBYLocalElectionBodyId(Long id , Long publicationId ,Long userId);
	 
	public List<Object[]> getVoterHnoAndBoothsForPanchayatList(List<Long> panchayatIdsList,Long publicationId);
	
	public List<Object[]> getCasteForVoterList(List<Long> voterIds,Long userId);
	
	public Integer updateVoterCasteByPrediction(Long userId,Long casteStateId,Long casteInsertTypeId,List<Long> voterIdsList);
	
	public UserVoterDetails getUserVoterDetailsByUserIdAndVoterId(Long userId,Long voterId);
	
	public List<Object[]> getVoterNamesByVoterIdsList(List<Long> voterIds);
	public List<Hamlet> getHamletByVoterId(Long voterId);
	public List<Constituency> getWardByVoterId(Long voterId);
	 public List<Object[]> getCasteCountByConstituencyIds(Long publicationDateId,Long userId);
	 public List<Object[]> getCasteCountBylocationType(Long publicationDateId,Long userId,Long constituencyId,String locationType);;
	 public List<Object[]> getCasteVotersCountBylocationTypeInConstituency(Long publicationDateId,Long userId,Long constituencyId,String locationType);
	 public List<Object[]> getCasteVotersDetailsBylocationTypeInConstituency(Long publicationDateId,Long userId,Long constituencyId,String locationType,Long casteId,String gender,Long locationId,String queryStr);
	 public List<Object[]> getVoterCadreCasteDetailsBySearchCriteria(Long stateId,String locationType,List<Long> locationIdsList,Long casteStateId);
	 public List<Object[]> getVotersCasteNAgeGroupWiseCount(Long casteGroupId,Long casteId,List<Long> constituencyIds,Long publicationDateId);

	public List<Object[]> getVotersCasteNAgeGroupWiseCounts(Long casteGroupId, Long casteId, Long reportLevelId,Long locationTypeId, List<Long> constituencyIds,Long publicationDateId);
	public List<Object[]> getLocationWiseVoterCounts(Long locationTypeId,List<Long> locationValues,Long casteId,Long publicationDateId,String type);
}
