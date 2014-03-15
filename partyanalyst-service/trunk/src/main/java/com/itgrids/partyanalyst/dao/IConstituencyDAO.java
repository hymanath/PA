/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
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
	  
}
