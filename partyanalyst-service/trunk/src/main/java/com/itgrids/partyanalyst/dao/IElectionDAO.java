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
	
	public List<Election> findByElectionType(Long typeID);
	
	public List<Election> findByPropertyTypeId_CountryId_StateId(Long typeID, Long countryId, Long stateId);

	public List<String> listOfYears();
	
	public String findPreviousElectionYear(String year, Long typeId, Long stateId, Long countryId);
	
	public String findPreviousParliamentElectionYear(final String year, final Long typeId, final Long countryId);
	
	public Election findByElectionScopeIdElectionYear(Long electionScope,String electionYear, String elecSubtype);
	
	public List<Election> findByElectionTypeCountry(Long typeId, Long countryID);
		
	public List<Election> findByElectionTypeYearAndState(Long typeId,String year,Long stateId,Long countryId);
	
	public List<Election> findByElectionTypeYearAndCountryForParliament(Long typeId,String year,Long countryId);
	
	public List findLatestElectionYear(String electionType);
	
	@SuppressWarnings("unchecked")
	public List findElectionIdAndYear(Long electionType,Long stateId);
	
	@SuppressWarnings("unchecked")
	public List findElectionAndYearForElectionTypeAndState(Long electionType,Long stateId);

	
	@SuppressWarnings("unchecked")
	public List findLatestElectionIdAndYear(Long electionType);
	
	@SuppressWarnings("unchecked")
	public List findRecentElectionIdAndYearForParliament(Long electionType);
	
	@SuppressWarnings("unchecked")
	public List findElectionAndYearForParliamentElectionType(Long electionType);

	
	@SuppressWarnings("unchecked")
	public List findLatestElectionIdAndYear(Long electionType,Long stateId);
	
	@SuppressWarnings("unchecked")
	public List findParliamentElectionIdByElectionTypeAndYear(String electionType,String year);
	
	@SuppressWarnings("unchecked")
	public List findElectionIdByElectionTypeAndYear(String electionType,String year,Long stateId);
	
	public List findElectionYearsForElectionTypeAndState(Long electionType,Long stateId);
	
	public List getElectionTypeAndElectionYearByElectionId(Long electionId);
	
	public List findByElectionType(Long typeID, String type);

	public List<Election> findByElectionScopeId(Long electionScopeId, String electionSubtype);
	
	public List findElectionIdByParliamentElectionTypeAndYear(String electionType,String year);
	
	public List findStatesByElectionType(Long electionTypeId);
	
	@SuppressWarnings("unchecked")
	public List findLatestElectionAssemblyElectionYearForState(String electionType, Long stateId);
	
	@SuppressWarnings("unchecked")
	public List getLocalBodyElectionsInAState(String electionType,Long stateId);
	
	@SuppressWarnings("unchecked")
	public List findElectionIdForGivenElectionYearAndElectionYears(Long electionType,String year,Long stateId,String electionSubType);
	
	public List findLatestElectionYearForGHMC(String electionType);
	
	public List<Election> getRecentElectionHappendForAnElectionType(String electionType,Long stateId);
	
	@SuppressWarnings("unchecked")
	public List findLatestElectionAssemblyElectionYearForState(String electionType, Long stateId,String electionSubType);
	
	@SuppressWarnings("unchecked")
	public List findLatestElectionAssemblyElectionIdForState(String electionType, Long stateId,String electionSubType);
	
	public List findElectionsByState(Long stateId);
	
	public List<Election> findElections(Long electionType_id,Long country_id,Long state_id);
		
	public List<Election> findElections(Long electionType_id,Long country_id);
	
	public List<Election> findElections(Long state_id);
	
	public List findLatestParliamentaryElectionYear(Long state_id);
	
	@SuppressWarnings("unchecked")
	public List findParliamentElectionIdForGivenElectionYearAndElectionYears(Long electionType,String year,String electionSubType);
	
	public List findLatestElectionIdForElectionType(String electionType, String subType);
	
	@SuppressWarnings("unchecked")
	public List findLatestElectionIdForElectionType(String electionType, String subType,Long stateId);
	
	public List<Long> getAllElectionYearsBasedOnElectionType(String electionType,String type,Long stateId);
	
	public List getCountOfElectionYears(Long stateId,String electionType,String electionSubType);
	
	public List getCountOfElectionYearsForParliament(Long stateId,String electionType,String electionSubType);
	
	public List<Long> getElectionYears(Long stateId,String electionType,String elecSubType);
	
	public List<Long> getElectionIds(Long stateId,String electionType,String elecSubType);
	
	public List getElectionDetailsForAnElection(Long electionId);
	
	public List findLatestParliamentElectionYear(String electionType,String electionSubType);
	
	public List findLatestElectionIdAndYearForAnElection(String electionType,Long stateId,String elecSubType);
	
	//DAO method to get latest electionId happened in a state election by electiontype and stateID
	@SuppressWarnings("unchecked")
	public List findRecentElectionIdByElectionTypeAndState(String electionType,Long stateId);
	
	@SuppressWarnings("unchecked")
	public List findLatestElectionYearHappenedInState(Long stateId,String electionType);
	
	public List getLatestElectionYearForAStateBasedOnElectionType(Long stateId, String electionType, String subType);
	
	public List findLatestElectionYearHappenedInState(Long stateId,String electionType,String electionSubType);
	
	public List<Election> findByElectionScopeId(Long electionScopeId);
	
	public List<Object[]> getPreviousElectionIdAndYear(Long electionId);
	
	public List<Object[]> getElectionYearsBasedOnElectionTypeAndState(Long stateId,String electionType);
	
	public List<Object[]> getElectionYearsBasedOnElectionTypeIdAndStateId(Long stateId,Long electionTypeId);
}