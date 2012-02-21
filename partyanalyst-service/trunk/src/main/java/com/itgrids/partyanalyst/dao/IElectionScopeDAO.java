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

import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.State;

/**
 * Interface for ElectionScopeDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface IElectionScopeDAO extends GenericDao<ElectionScope, Long>{

	/**
	 * Find all ElectionScope entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ElectionScope property to query
	 * @param value
	 *            the property value to match
	 * @return List<ElectionScope> found by query
	 */
	public List<ElectionScope> findByPropertyElectionTypeId(Object value);

	public List<ElectionScope> findByCountryStateId(Object countryStateId);
	
	public List<ElectionScope> findByTypeIdCountryIdStateId(Long typeId, Long countryID, Long stateID);
	
	public ElectionScope findByElectionTypeCountry(ElectionType electionType, Country country);
	public ElectionScope findByElectionTypeCountryState(ElectionType electionType, Country country,State state);
	public List<ElectionScope> findByPropertyElectionTypeIdandStateId(Long electionTypeId,Long stateId);

	public List getElectionScopes();
	
	public List<ElectionScope> getElectionScopes(Long stateId);
	
	public List<ElectionScope> getElectionScopeForAElectionType(Long stateId,String electionType,Long countryId);
	
	public List<ElectionScope> getElectionScopeForAElectionType(String electionType,Long countryId);
	
	public List getAllStatesAndTheirIds(String electionType);
	
	public List<ElectionScope> findByTypeIdStateId(Long typeId,Long stateID);

}