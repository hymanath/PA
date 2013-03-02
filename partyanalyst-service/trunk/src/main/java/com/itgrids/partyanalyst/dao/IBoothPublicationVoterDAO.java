package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Voter;

public interface IBoothPublicationVoterDAO extends
		GenericDao<BoothPublicationVoter, Long> {
	
	
	public List<BoothPublicationVoter> checkForVoterExistenceInBoothPublicationVoter(
			Long boothId, Long voterId);
	
	public List<Voter> getVotersDetailsByBoothId(Long boothId, Integer startIndex,
			Integer maxRecords, String order, String columnName);
	
	public List<Voter> getVotersDetailsForPanchayatByPublicationId(
			Long panchayatId, Long publicationDateId, Integer startIndex,
			Integer maxRecords, String order, String columnName);
	
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
	
	public List<Voter> getVoterDetailsByCasteStateForBooth(Long boothid,Long publicationDateId,Long casteStateId);
	
	public List<Voter> getVoterDetailsByCasteStateForPanchayat(Long panchayatId,Long publicationDateId,Long casteStateId);
	
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
	 public List<Object[]> getVotersCountDetailsInSpecifiedRangeForHamletByPublicationId(
				Long hamletId, Long publicationDateId , Long startAge, Long endAge); 
	 public List<Object[]> getVotersCountInSpecifiedRangeForHamletByPublicationId(
				Long hamletId, Long publicationDateId , Long startAge, Long endAge);
	 
	 
	 public List<Voter> getVotersByBoothId(Long boothId);
	 
	 public List<Object[]> getPartNoAndVoterIdByConstituencyInAPublication(Long constituencyId,Long publicationDateId);
	 
	 public List<Object[]> getSerialNoByVoterIdsList(List<Long> voterIdsList);
	 
	 public Integer updateSerialNoByVoterId(Long serialNo, Long voterId);
	 
	 public List<Voter> getVotersInABoothsList(List<String> partNosList, Long constituencyId, Long publicationDateId);
	 
	 public List getLocationNameByLocationValue(String type, Long locationValue);
	 

	 public List<Long> getCandidateCount(List<Long> locationValue,Long publicationDateId,String type);
	 
	 public List<Object[]> getPoliticianDetails(List<Long> locationValue,Long publicationDateId,String type,Integer startIndex,
				Integer maxRecords);
}
