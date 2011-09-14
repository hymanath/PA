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

import com.itgrids.partyanalyst.dao.columns.enums.StateColumnNames;
import com.itgrids.partyanalyst.model.State;

/**
 * Interface for StateDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface IStateDAO extends GenericDao<State, Long>{

	/**
	 * Find all State entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the State property to query
	 * @param value
	 *            the property value to match
	 * @return List<State> found by query
	 */
	public List<State> findByProperty(StateColumnNames propertyName, Object value);

	public List<State> findByStateName(Object stateName);

	public List<State> findByAdminCapital(Object adminCapital);

	public List<State> findByLegisCapital(Object legisCapital);

	public List<State> findByJudiciaryCapital(Object judiciaryCapital);

	public List<State> findByStateLanguage(Object stateLanguage);

	public List<State> findByStateSymbol(Object stateSymbol);

	public List<State> findByStateSong(Object stateSong);

	public List<State> findByStateAnimal(Object stateAnimal);

	public List<State> findByStateBird(Object stateBird);

	public List<State> findByStateTree(Object stateTree);

	public List<State> findByStateSport(Object stateSport);

	public List<State> findByStateDance(Object stateDance);

	public List<State> findByStateFlower(Object stateFlower);

	public List<State> findByIsoCode(Object isoCode);

	public List<State> findByStateCode(Object stateCode);
	
	public List<State> findByStateId(Long stateId);
	
	public List<State> findByCountryId(Long countryId);
	
	@SuppressWarnings("unchecked")
	public List findStatesByCountryId(Long countryId);
	
	@SuppressWarnings("unchecked")
	public List findStateIdByNameAndCountryId(String stateName,Long countryId);
	
	public List<State> findByCountryIdForSearch(Long countryId);
	
	public List getAllStatesByCountryIdOrderByStateId(Long countryId);
	
	public List findStatesByCountryIdAndCountryAccess(Long countryId,Long userId);
	
	public List findStatesByCountryIdAndCountryAccessAndStateAccess(Long countryId,Long userId);

}