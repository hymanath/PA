/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Constituency;

/**
 * Interface for ConstituencyDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface IConstituencyDAO extends GenericDao<Constituency, Long>{


	/**
	 * Find all Constituency entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Constituency property to query
	 * @param value
	 *            the property value to match
	 * @return List<Constituency> found by query
	 */
	public List<Constituency> findByProperty(String propertyName, Object value);

	public List<Constituency> findByName(Object name);

	public List<Constituency> findByCountryId(Object countryId);
	
	public List<Constituency> findByConstituencyNamePattern(String electionType, String constituencyName,Long stateId);
	
	public List<Constituency> findByStateId(Long stateId);
	
	public List<Constituency> findByElectionScope(Long scopeID);

	public List<Constituency> getConstituenciesByDistrictID (Long districtID);
	
	public List getStateDistrictConstituency(Long constituencyID);
	
	public List getStateForParliamentConstituency(Long constituencyID);
	
	public List<Constituency> findByConstituencyNameAndDistrictId(String constituencyName,Long districtId);
	
	@SuppressWarnings("unchecked")
	public List findByConstituencyIdConstituencyNameAndDistrictId(String constituencyName,Long districtId,String electionType);
	
	public List<Constituency> findByConstituencyNameAndDistrictId(String constituencyName,Long districtId,String electionType);
	
	public List findConstituencyByDistrictElectionType(Long districtId, String electionType);
	
	public List<Constituency> findByConstituencyNameDistrictIdTehsilName(String constituencyName, Long districtID, String tehsilName, Long electionScopeID);
	
	public List<Long> getDistrictIdByConstituencyId(Long constituencyId);
		
	public List<Long> getStateIdByConstituencyId(Long constituencyId);

	public List<Constituency> findByElectionScopeState(Long scopeID, Long stateID);

	public List getAllConstituencyNamesAndIds();

	
	@SuppressWarnings("unchecked")
	public List basicElecDetailsForAConstituency(Long constituencyId);

	
	public List getConstituencyTypeAndDelimitationInfoByConstituencyId(Long constituencyId);

	public List getConstituenciesByElectionTypeAndStateId(Long electionTypeId , Long StateId);
	
	@SuppressWarnings("unchecked")
	public List getConstituencyInfoByConstituencyIdElectionYearAndElectionType(Long constituencyId);
	
	@SuppressWarnings("unchecked")
	public List getParliamentConstituencyInfoByConstituencyIdElectionYearAndElectionType(Long constituencyId);
	
	public List findConstituencyByDistrictAndStateIds(Long districtId,Long stateId,String electionType);

	public List getConstituencyInfoByConstituencyIdElectionYearAndElectionTypeForMuncipalAndCorporationElection(Long constituencyId);

	public List<Constituency> findByLocalElectionBodyAndElectionScope(
			Long localElectionBodyId, String wardNo);

	public List<Constituency> findWardsAndIdsInMuncipality(Long localElectionBodyId);
	
	public List findWardsInLocalElectionBodies(String localElectionBodyIds);
	
	@SuppressWarnings("unchecked")
	public List findConstituenciesForBiElectionInADistrict(String query);
	
	@SuppressWarnings("unchecked")
	public List findConstituencyIdByTehsil(Long tehsilId);
	
	public List<Constituency> getAllParliamentConstituenciesInCountry(Long electionScopeId, Long countryId);

	public List<Constituency> findByConstituencyNameElectionScopeAndDistrictId(String constituencyName, Long districtId, Long electionScopeId);
	
	public List<Long> getAllWardsByLocalElectionBodyIds(List<Long> localElectionBodyIds);
	
	public List<Long> getAllConstituencysByDistrictIds(List<Long> districtIds, String electionType);
	
	public List getConstituenciesBySearchString(Long electionTypeId , Long stateId, String searchString);
	
	public List<Constituency> getAllWardsObjsByLocalElectionBodyWardIds(List<Long> localElectionBodyWardIds);
	
	public List getStateToConstituencyByConstituency(String constituencyIds);
	
	public List getStateToWardByWard(String constituencyIds);
	
	public List<Constituency> findConstituenciesByNameScopeAndCountryId(String constituencyName, Long electionScopeId, Long countryId);
	
	public List<Constituency> findConstituenciesByNameScopeAndStateId(String constituencyName, Long electionScopeId, Long stateId);
	public List<Object[]> findByConstituencyNamesForAssembly(String searchText,String stateStr,String sortoption,String order,Integer startIndex,Integer maxResult);
	public List<Object[]> findByConstituencyNamesForPalriament(String searchText,String stateStr,String sortoption,String order,Integer startIndex,Integer maxResult);
	public Object totalSearchCount(String searchText,Long electionType,String state);
	
	@SuppressWarnings("unchecked")
	public List getWardIdByWardNameAndLocalBodyId(String wardName,Long localBodyId);
	
	public void flushAndclearSession();
	
	public List getLatestConstituenciesByStateId(String electionType,Long stateID);
	
	public List getAssConstituenciesByElectionTypeAndStateIdAndDistrictAccess(Long electionTypeId , Long stateId,Long userId);
	
	public List<Constituency> getAllParliamentConstituenciesInCountryByStateAccessAndCountryAccess(Long electionScopeId, Long countryId,Long userId);
	
	public List<Object[]> getConstituencyInfoByConstituencyIdList(List<Long> constituenciesList);
	
	public List<Object[]> getConstituencyInfoWithStartDateByConstituencyIdList(List<Long> constituenciesList);
	
	public List<Object[]> getConstituenciesByDistrictId(Long districtId);
	
	public List<Constituency> getAllParliamentConstituenciesInAState(Long electionScopeId, Long StateId);
	
	public List<Constituency> getPresentAssemblyConstituencyDetailsByDistrictId(Long stateId,List<Long> districtIds);
	
	public List<Constituency> findByStateIdForUser(Long stateId) ;
	
	public List<Constituency> findByDistrictIdForUser(Long districtId);
	
	public List<Constituency> findByConstituencyNameAndDistrictIdElectionType(String constituencyName,Long districtId,Long electionType);
	
	public List<Constituency> findConstituenciesByDistrictId(Long districtId);
	
	public List<Object[]> getConstituencyName(Long constituencyId);
	public List<Object[]> getWardsInMuncipality(Long assemblyElectionBodyId);
	public List getNameByInfluenceScopeValue(Long locationValue,String type);
	
	public String getLocalBodyElectionTypeByConstituencyId(Long constituencyId);
	
	public List<Object[]> getStateToConstituencyByParlConstituency(String constituencyIds);
	
	public List<Object[]> getWardsInMuncipalityFomConstituency(Long localBodyid);
	
	public List<Object[]> getConstituencyType(Long constituencyId);
	
	public List<Object[]> getConstituencyNameByConstituencyIdsList(List<Long> constituencyIdsList);
	
	public String getConstituencyNameByConstituencyId(Long constituencyId);
	
	public List<Object[]> getWardsInALocalBody(Long localBodyId);
	
	public List<Object[]> getStateAndDistricts(Long constituencyId);
	
	public List<Object[]> getConstituencyTypeByConstituencyList(List<Long> constituencyIds);
	
	public List<Object[]> getStateForSelectedConstituency(List<Long> constituencyId);
	
	public List<Object[]> getDistrictForSelectedConstituency(List<Long> constituencyId,Long stateId);
	
	public List<Object[]> getConstituencysForSelDistrict(List<Long> constituencyIds,Long districtId);
	
	public String getConstituencyNameByConstituencyIdInWards(Long constituencyId);
	
	public List<Object[]> getConstityencyByConstituencyids(List<Long> constituencyIds);
	
	public List<Constituency> getConstituencyDetails();
	
	public List<Object[]> getConstituencyByStateId(Long stateId);
	
	public List<Object[]> constituencyName(Long constituencyId);
		
	public List<Object[]> getRuralAndRuralUrbanConstiencies(List<Long> constituencyIds);
	
	public String getConstituencyAreaType(Long constituencyId);
	
	public List<Object[]> getParliamentConstituencies();
	
	public List<Object[]> getDistrictConstituencies(Long districtId) ;
	
	public List<Object[]> getAreaTypesOfAConstituencyByElectionScope(Long electionScopeId);	
	List<Object[]> getConstis();
	public List<Object[]> getAllMucipalAssembyConstiListInState(Long stateId);
	
	public List<Object[]> getDistrictConstituenciesList(List<Long> districtIds);
	
	public List<Object[]> getRuralAndRurlaUrbanConstis(Set<Long> assemblyIds);
	
	public List<Object[]> getConstituencyByState(Long stateId);
	public List getLatestConstituenciesByStateIdForregion(String electionType , Long stateID,String region,Long districtId,Long tehsilId);
	
	public List<Object[]> getConstituencyDetaisByRegionid(List<Long> regionIds);
	
	public List getConstituenciesByElectionTypeAndStateIdForMPTC(Long electionTypeId , Long stateID);
	public List<Long> getConstituencyIdsByDistrictId(Long districtId,Long electionTypeId);
	
	public List<Object[]> getAllAssemblyConstituenciesByStateId(Long stateId);
	
	public List<Object[]> getDistictWiseConstituencyListByConstiIds(List<Long> constituencyIds);

	public List<Object[]> findWardsAndIdsInlocalElectionBody(Long localElectionBodyId);
	public List<Object[]> getConstituencies(List<Long> districtIds);
	
	public List<Object[]> getAllAssemblyConstituenciesByStateTypeId(Long stateTypeId,Long stateId,Long electionYear);
	public List getConstituenciesForRegion(String region);
	
	public List<Object[]> getAssemblyConstituenciesInAP(String type);
	
	public List<Long> getConstituenciesInADistrict(Long districtId);
	
	 public List<Long> getConstituenciesInAState(Long stateId);
	 
	 public List<Long> getAllAssemblyConstituencyIdsByStateId(Long stateId);
	 
	 public List<Object[]> findConstituenciesByStateId(Long stateId);
	public List<Long> getDistrictIdsByConstituency(List<Long> constituencyIds);
	
	public List<Long> getDistrictIdByConstituencyIds(List<Long> constituencyIds);
	
	public List<Object[]> getConstituencyInfoByNotConstituencyIdList(List<Long> constituenciesList,String searchType);
	
	public List<Object[]> getConstituencysByDistrictId(List<Long> districtIdList);
	
	public List<Long> getConstituencyIdsByStateIdForAElectionType(Long stateId,Long electionTypeId);
	
	public List<Object[]> getConstituencysByLsitDistrictIds(List<Long> districtIdList);
	
	public List<Object[]> getParliamentConstituencyInfoByConstituencyIds(List<Long> constituencyIds);
	
	public List<Object[]> getWardsInLocalElectionBody(List<Long> localBodyIds);
	
	public List<Object[]> getWardIdAndName(Long localElectionBodyId);
	
	public List<Object[]> getConstituencyByStateAndAreaType(Long stateId,Long level);
	
	public List<Object[]> getWardsNameInLocalElectionBodyByWardIds(List<Long> wardIds);
	
	public List<Object[]> getConstituenciesByStateId(Long stateId,Long stateTypeId);
	public List<Object[]> getAllConstituenciesInADistrict(Long districtId);
	
	public List<Object[]> getWardsAndLEBIdsInLocalElectionBody(List<Long> localBodyIds);
	
	public List<Object[]> getAssemblyConstituencyDetlsByDistrictIds(List<Long> districtIds);
	
	public List<Object[]> getParliamentConstituencyByParliamentId(Long constituencyId);
	
	public List<Object[]> getTheConstituenciesInADistrict(Long districtId);
	public List getConstituenciesByElectionTypeAndStateIdForMPTC1(Long electionTypeId , Long stateId);
	public List getConstituenciesByElectionTypeAndStateId1(Long electionTypeId , Long stateId);
	public List<Object[]> getConstituencyListByDistrictIdsList(List<Long> districtIdsList);
	public List<Object[]> getDistrictConstituenciesByState(Long districtId,Long stateId);
	public List<Long> getDistrictIdByConstituencyIdandState(Long constituencyId,Long stateId);
	public List<Long> getConstituenciesByState(Long stateId);
	public List<Long> getDistrictByConstituencyId(Long constituencyId);
	
	 public List<Long> getStateByConstituencyId(Long constituencyId);
	 
	 public List<Object[]> getConstituenciesDetaildByDistrictId(Long districtId);
	 public String getConstituencyNameById(Long constituencyId);
	 public Object[] getlocalbodyName(Long constituencyId);
	 
	 public List<Object[]> getAllWardsForState(Long stateId);
	 public List<Object[]> getMPTCZPTCLocationAreaDetails(List<Long> constituencyIds,List<Long> tehsilsList);
	 public List<Object[]> getConstituenciesByStateAndDistrict(Long stateId, List<Long> districtIds);
	 public List<Object[]> getStateAndDistricsOfConstituency(List<Long> constituencyIds);
	 public List<Object[]> getWardDetailsById(List<Long> locationIds);
	 public Long getElectionScopeByConstituency(Long constituencyId);
	 public List<Long> getConstituncyIdsByDistrictId(List<Long> districtsIds,Long electionTypeId);
	 public Long getConstituencyIdByDistrictIdAndConstituencyName(Long districtId,String constituencyName);
	 public Long getWardIdByTownIdAndWardName(Long localElectionBodyId,String wardName);
	 public List<Object[]> getConstituencyDetailsByCintiId(Long constiId);
	 public List<Object[]> getConstituenciesByDistId(Long districtId);
	 public List<Object[]> getConstituenciesForADistrict(Long distId,Long stateId);
	 public List<Long> getDivisionIdsOfGreater(List<Long> greaterIds);
	 public List<Long> getAllTehsilsByConstituency(List<Long> constituencyIds);
	 public List<Long> getAllLocalBodiesForConstituency(List<Long> constituencyIds);
	 public List<Long> getAllDivisions(List<Long> locationIds);
	 public List<Long> getAllDivisionsOfConstituency(List<Long> constituencyIds);
	 public List<Long> getAllPanchayatsForDistrict(List<Long> districtIds);
	 public List<Long> getAllPanchayatsForConstituency(List<Long> constituencyIds);
	 public List<Long> getAllPanchayatsByTehsilId(List<Long> tehsilId);
	 public List<Long> getAllWardsForDistrict(List<Long> districtIds);
	 public List<Long> getAllWardsForConstituency(List<Long> constituencyIds,Long publicationDateId);
	 public List<Long> getAllWardIdsForLocalBody(List<Long> localElectionBodyId);
	 public List<Object[]> getTehsilsByConstituency(Long constituencyId);
	 public List<Object[]> getLocalElectionBodiesByconstituency(Long constituencyId);
	 public List<Object[]> getPanchayatsByTehsilId(Long tehsilId);
	 public List<Object[]> getTemaporarilyMobileNoByDate(Date date);
	 public List<Constituency> getConstituenciesByStteIdStatTypeId(Long stateId,Long stateTypeId);
	 public List<Object[]> getConstituenciesByStateId(Long stateId);
	 public List<Object[]> getConstituenciesByDistrictIds(List<Long> districtIds);
	 public List<Object[]> getDefaultConstituencyAndDistrictForAState(Long stateId);
	 public List<Object[]> getConstituencyIdAndNameByStateForRegion(Long stateId,String type);
	 public List<Object[]> getDistrictByDistrictId(Long districtId);
	 public List<Object[]> getMPTCConstituenciesForMandal(Long tehsilId);
	 public List<Object[]> getZPTCConstituenciesForMandal(List<Long> tehsilIds);
	 public List<Object[]> getMandalsForDistrictWiseDetails(List<Long> districtId);
	 public List<Object[]> getVillagesForDistrictWiseDetails(List<Long> districtId);
	 
	 public List<Object[]> getConstituenctNamesByIds(List<Long> constituencyIds);
	 public List<Object[]> getAllConstituencyList(Long stateId);
	 public List<Object[]> getStateWiseConstituency();
	 public List<Object[]> getDistrictWiseConstituency(Long districtId);
	 public List<Object[]> getStateWiseAssemblyConstituency(Long stateId);
	 
	 public List<Object[]> getAllASsemblyContsAndItsDistricts();
	 public List<Long> getStateConstituencyIds(Long stateId);
	 public List<Object[]> getDistrictBasedOnConstituenciesId(Set<Long> constitueciesIds);
	 public List<Object[]> getDistAndConDtslByConstituenciesIds(List<Long> constitueciesIds);
	 public List<Object[]> getConstituenciesList(List<Long> distIds);
	 public Long getDistId(Long locationId);
	 public List<Object[]> getConstituenciesByStateForStateTypeId(Long stateId,Long stateTypeId,Long districtId);
	 public List<Object[]> getConstituenciesByStatTypeId(Long stateTypeId);
	 public List<Object[]> getConstituenciesByConstituenciesIds(List<Long> constituencyIds,Long stateId);
	 public List<Object[]> getConstituenciesNamesByIds(List<Long> constituencyIds);
	 public List<Long> getDistrictsByConstituenciesIds(Set<Long> constituenciesIds);
	 public List<Object[]> getWardDetailsIdsForLocalBody(Long localElectionBodyId);
	 public List<Object[]> getConstituencyByStateDetails();
	 public List<Long> getConstituenciesIds(Long distId);
	 public List<Object[]> getAssemblyConstituencyTeluguNames();
	 public List<Object[]> getConstituencyByConstituencyIds(List<Long> constituencyIds);
	 public List<Object[]> getConstLebDetailsByConstIds(List<Long> constIds);
	 public List<Object[]> getConstituencyListByDistrictId(Long districtId);
	 public List<Object[]> getConstituenciesByDistrict(List<Long> districtIds);
	 public List<Object[]> getTehsilsByDistrict(Long districtId);
	 public List<Object[]> getLocalElectionBodiesByDistrict(Long districtId);
	 public List<Object[]> getHamletByPanchayat(Long panchayatId);
	 public List<Long> getConstistuencyWiseParliamentIds(Set<Long> userLocationLevelValues);
	 public List<Object[]> getConstituencyListByDistrictId(List<Long> districtId);
	 public List<Object[]> getLocationsDetailsBySearchType(List<Long> locationIds,String searchType);
	 public List<Object[]> getConstituenciesIdsListByDistrictIds(Long districtId);
	 public List<Constituency> getConstituencyInfo(Long districtId,String constituencyName);
	 public List<Constituency> getWardInfoOfLocalBody(Long localBodyId,String wardName);
	 public List<Long> getConstituenciesIdsList(List<Long> districtIds);
}
