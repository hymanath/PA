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

import com.itgrids.partyanalyst.dao.columns.enums.ElectionColumnNames;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;

/**
 * Interface for ElectionDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface IElectionDAO extends GenericDao<Election, Long>{

	/**
	 * Find all Election entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Election property to query
	 * @param value
	 *            the property value to match
	 * @return List<Election> found by query
	 */
	public List<Election> findByProperty(ElectionColumnNames propertyName, Object value);

	public List<Election> findByEndDate(Object endDate);

	public List<Election> findByElectionYear(Object electionYear);
	
	public List<Election> findByPropertyTypeId(Long typeID);
	
	public List<Election> findByPropertyTypeId_CountryId_StateId(Long typeID, Long countryId, Long stateId);

	public List<Election> findByElectionScope(Long electionScopeID);
	
	public List<String> listOfYears();
	
	public List<Election> findByElectionScope_OrderByAsc(Long electionScopeID);
	
	public String findPreviousElectionYear(String year, Long typeId, Long stateId, Long countryId);
	
	public List<Election> findByElectionScopeId(Long electionScopeId);
	
	public Election findByElectionScopeIdElectionYear(Long electionScope,String electionYear, String elecSubtype);
	
	public Election getElectionByCountryStateTypeIDElectionYear(Long typeID, Long countryID, Long stateID,String electionYear);
	
	public List<Election> findByElectionTypeCountry(Long typeId, Long countryID);
		
	public List<Election> findByElectionTypeYearAndState(Long typeId,String year,Long stateId,Long countryId);
	
	public List findLatestElectionYear(String electionType);
	
	@SuppressWarnings("unchecked")
	public List findElectionIdAndYear(Long electionType,Long stateId);
	
	@SuppressWarnings("unchecked")
	public List findElectionAndYearForElectionTypeAndState(Long electionType,Long stateId);

	
	@SuppressWarnings("unchecked")
	public List findLatestElectionIdAndYear(Long electionType);

	
	@SuppressWarnings("unchecked")
	public List findLatestElectionIdAndYear(Long electionType,Long stateId);
	
	@SuppressWarnings("unchecked")
	public List findParliamentElectionIdByElectionTypeAndYear(String electionType,String year);
	
	@SuppressWarnings("unchecked")
	public List findElectionIdByElectionTypeAndYear(String electionType,String year,Long stateId);
	
	public List findElectionYearsForElectionTypeAndState(Long electionType,Long stateId);
	
	public List getElectionTypeAndElectionYearByElectionId(Long electionId);
	
	public List findElectionYearsBySubType(Long typeID, String type);
	
	
}