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
	
	public List<Constituency> findByConstituencyNamePattern(String constituencyName);
	
	public List<Constituency> findByStateId(Long stateId);
	
	public List<Constituency> findByConstituencyId(Long constituencyId);
	
	public List<Constituency> findByElectionScope(Long scopeID);

	public List<Constituency> getConstituenciesByDistrictID (Long districtID);
	
	public List getStateDistrictConstituency(Long constituencyID);
	
	public List getStateForParliamentConstituency(Long constituencyID);
	
	public List<Constituency> findByConstituencyNameAndDistrictId(String constituencyName,Long districtId);
	
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
}