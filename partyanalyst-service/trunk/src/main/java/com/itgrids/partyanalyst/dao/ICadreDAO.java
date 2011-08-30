package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Cadre;
/**
 * 
 * @author Narender Akula
 *
 */
public interface ICadreDAO extends GenericDao<Cadre, Long>{
	public List<Cadre> findByProperty(String propertyName, Object value);
	public List<Cadre> findByUserID(Long userID);
	public Long findTotalCadresByUserID(Long userID, String cadrerType);
	public Long findTotalCadresByUserIDInALocation(Long userID, String cadreType, String model, String idToCompare, Long locationId);
	public List findTotalCadresByUserIdBasedOnCadreLevel(Long userID, String cadreType, String model, String idToCompare, Long locationId);
	public List findCadresByLevels(Long userID, String cadrerType);
	public List findStatesByCountryID(String countryID);
	public List findDistrictsByStateID(String stateID);
	public List findMandalsByDistrictID(String districtID);
	public List findVillagesByTehsilID(String tehsilID);
	public List findHamletsByTehsilIds(String tehsilIds);
	public List findCadreSizeStateWise(Long userID); //findTotalStateZeroSizeCadres
	public List findCadreSizeDistrictWise(Long userID);//findTotalDistrictZeroSizeCadres
	public List findCadreSizeMandalWise(Long userID);//findTotalMandalZeroSizeCadres
	public List findCadreSizeVillageWise(Long userID);//findTotalVillageZeroSizeCadres
	public List findCadreSizeLocalElectionBodywise(Long userId);
	public List findCadreSizeConstituencywise(Long userId);
	public List findCadreSizeWardswise(Long userId);
	public List findCadreSizeBoothwise(Long userId);
	public List findCadreSizeBoothwiseInMandal(Long userId);
	//Ajax call methods
	public List findStateCadresByCountry(Long countryID, Long userID, String cardreType);
	public List findDistCadresByState(Long stateID, Long userID, String cardreType);

	public List findConstituencyCadresByDist(Long districtID, Long userID, String cadreType);
	public List findConstituencyCadresByParliamentConst(Long parliamentId, Long userID, String cadreType);
	public List findMandalCadresByConstituency(Long constituencyID, Long userID, String cadreType);
	
	public List findMandalCadresByDist(Long districtID, Long userID, String cadreType);
	public List findVillageCadresByMandal(Long mandalID, Long userID, String cadreType);
	public List<Cadre> findCadresByVillage(Long villageID, Long userID, String cadreType);
	public List findMandalCadresByMandals(String mandalIDs, Long userID, String cadreType);
	//public List findMandalsByConstituencyID(String constituencyId);
	
	public List<Cadre> findCadresByCadreLevel(String cadreLevel, Long userID, String cadreType);
	public List<Cadre> findCadresByCadreLevelByUserIDInALocation(String cadreLevel, Long userID, String cadreType, String model, String idToCompare, Long locationId);	
	
	public List getUserAccessStates(Long userID);
	public List getUserAccessDistricts(Long userID);
	public List getUserAccessMLAConstituencies(Long userID);
	public List getUserAccessMandals(Long userID);
	

	public List getMobileNosByState(Long userID, Long stateID);
	public List getMobileNosByDistrict(Long userID, Long districtID);
	public List getMobileNosByConstituency(Long userID, Long constituencyId);
	public List getMobileNosByMandal(Long userID, Long mandalID);
	public List getMobileNosByVillage(Long userID, Long villageID); 
	public List getMobileNosByHamlet(Long userID, Long hamletID);
	
	public List getMobileNosByCadreLevel(Long userID, Long level);
	
	public List<Cadre> findByCadreIDs(String cadreIDs);

	public List<Cadre> findCadresByState(Long stateID, Long userID);
	public List<Cadre> findCadresByDistrict(Long districtID, Long userID);
	public List<Cadre> findCadresByConstituency(Long constituencyID, Long userID);
	public List<Cadre> findCadresByMandal(Long mandalID, Long userID);

	public List getCadreSizeByHamlet(String revenueVillageIDs, Long userID, String cadreType);
	public List<Cadre> findCadresByHamlet(Long hamletID, Long userID, String cadreType);
	
	public List findHamletsByRVs(String townships);
	@SuppressWarnings("unchecked")
	public List findCadreSizeHamletWise(Long userID);
	
	@SuppressWarnings("unchecked")
	public List findCadreDetailsByLevelAndProperty(Long userId,String propertyColumnOne,String propertyColumnTwo,String propertyColumnId,Long propertyColumnValue, String query);
	
	@SuppressWarnings("unchecked")
	public List findCadreIdsByMemberTypeAndCadreList(String memberType,List<Long> cadreIds, String query);
	
	@SuppressWarnings("unchecked")
	public List findCadreIdsByUserAndMemberType(Long userId,String memberType);
	
	@SuppressWarnings("unchecked")
	public List findCadreByPropertyValueAndCadreIds(String propertyObject,String propertyField,Long propertyValue,List<Long> cadreIds);
	
	@SuppressWarnings("unchecked")
	public List findCadreByPropertyValueListAndCadreIds(String propertyObject,String propertyField,List<Long> propertyValue,List<Long> cadreIds, String query);
	
	@SuppressWarnings("unchecked")
	public List findCadreByPropertyValueAndUser(Long userId,String propertyObject,String propertyField,Long propertyValue);
	
	@SuppressWarnings("unchecked")
	public List findCadreByPropertyValueListAndUser(Long userId,String propertyObject,String propertyField,List<Long> propertyValue, String query);
	
	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeAndUser(Long userId,Long partyCommiteeId);
	
	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeListAndUser(Long userId,List<Long> partyCommiteeId);
	
	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeAndCadreIds(Long partyCommiteeId,List<Long> cadreIds);
	
	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeListAndCadreIds(List<Long> partyCommiteeId,List<Long> cadreIds);
	
	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeDesignationAndUser(Long userId,Long partyCommiteeDesigId);
	
	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeDesignationListAndUser(Long userId,List<Long> partyCommiteeDesigId);
	
	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeDesignationAndCadreIds(Long partyCommiteeDesigId,List<Long> cadreIds);
	
	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeDesignationListAndCadreIds(List<Long> partyCommiteeDesigId,List<Long> cadreIds);
	
	@SuppressWarnings("unchecked")
	public List findCadreByUserAndCadreLevel(Long userId,Long levelId, String query);
	
	public Integer deleteByCadreId(Long cadreId);
	
	public List findLocalElectionBodiesCadresByConst(Long constituencyId, Long userID, String cadreType);
	
	public List findHamletCadresByMandal(Long mandalID, Long userID, String cadreType);
	public List findBoothCadresByMandal(Long mandalID, Long userID, String cadreType);
	public List findBoothCadresByLocalElectionBody(Long localElectionBodyId, Long userID, String cadreType);
	
	public List findCadresByWard(Long localElectionBodyId, Long userID, String cadreType);
	
	public List<Cadre> getCadresByWard(Long wardId, Long userID, String cadreType);
	public List<Cadre> findCadresByBooth(Long boothId, Long userID, String cadreType);
	public List findCadresNotAssignedToBooth(Long userId, String cadreType);
	public List findCadresNotAssignedToBoothByTehsil(Long tehsilId, Long userId, String cadreType);
	public List findCadresNotAssignedToBoothLocalElectionBody(Long localBodyId,Long userId, String cadreType);
	public List findCadresNotAssignedToBoothInWard(Long wardId,Long userId, String cadreType);
	public List findVillageLevelCadresContByMandal(Long mandalId, Long userId, String cadreType);
	public List findLocalBodyLevelCadresContByMandal(Long localBodyId, Long userId, String cadreType);
	public List<Cadre> findCadreDetailsNotAssignedToBooth(Long tehsilId,Long userId, String cadreType);
	public List<Cadre> findCadreDetailsNotAssignedToBoothInLocalBody(Long localBodyId,Long userId, String cadreType);
	public List<Cadre> findCadreDetailsNotAssignedToBoothInWard(Long wardId,Long userId, String cadreType);
	public List<Cadre> findCadreDetailsAssignedToBoothInWard(Long wardId, Long userId, String cadreType);
	public List findCadresByBoothInWard(Long wardId, Long userId, String cadreType);
	public List<Object[]> findActiveCadreForSMS(Long registrationId,String cadreType,String searchCriteria,String sortOption,String order,Integer startIndex,Integer maxResult);
	public List<Object[]> findNormalCadreForSMS(Long registrationId,String cadreType,String searchCriteria,String sortOption,String order,Integer startIndex,Integer maxResult);
	public List<Object[]> findNormalCadre(Long registrationId);
	
	public List<Long> findCadreForSMS(Long registrationId,String cadreType,String searchCriteria,String SocailStatus,String genderStr,String mobileStr,String cadreNameStr,String roleStr, String sortOption,String order,Integer startIndex,Integer maxResult);
	
	public List<Long> findTotalCadreCountForSms(Long registrationId,String cadreType,String searchCriteria,String SocailStatus,String genderStr,String mobileStr,String cadreNameStr,String roleStr);
	
	@SuppressWarnings("unchecked")
	public List findTownshipCadresByMandal(Long mandalID, Long userID,String cadreType);
	
	public List<Cadre> findCadresByTownship(Long townshipId, Long userID, String cadreType);
	
	public Integer updateCadreImage(Long cadreId,String imageName);
	
	public List<Object> getMobileNosOfCadre(List<Long> cadreIdsList);
}
