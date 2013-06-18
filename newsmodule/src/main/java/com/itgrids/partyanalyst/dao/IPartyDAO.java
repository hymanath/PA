/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.columns.enums.PartyColumnNames;
import com.itgrids.partyanalyst.model.Party;

/**
 * Interface for PartyDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface IPartyDAO extends GenericDao<Party, Long>{/*

	*//**
	 * Find all Party entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Party property to query
	 * @param value
	 *            the property value to match
	 * @return List<Party> found by query
	 *//*
	public List<Party> findByProperty(PartyColumnNames propertyName, Object value);

	public List<Party> findByLongName(Object longName);

	public List<Party> findByShortName(Object shortName);

	public List<Party> findBySymbol(Object symbol);

	public List<Party> findByAddress(Object address);

	public List<Party> findByComments(Object comments);

	public List<Party> findByPartyRecognization(Object partyRecognization);
	
	public List<Party> findByPartyId(Long partyId);
	
	public List<Party> findByShortNames(String shortNames);
	
	@SuppressWarnings("unchecked")
	public List findPartyIdByShortName(String shortName);
	
	public List<Party> findStaticPartiesByRecognization(String partyType,String shortNames);
	
	public List<Party> findStaticParties(String partyType);
	
	public List<Party> findStatePartyByStateId(Long stateId);
	
	public List<Object[]> findAllPartyNames();

	public List<Party> getPartyDetails(Long partyId);
	
	public List<Object[]> getParticipatedPartiesInMandal(Long tehsilId);
	public List<Object[]> getParticipatedPartiesInMandalByElectionId(Long tehsilId,Long electionId);
	
	public List<Long> getStaticParties(String shortNames);
*/}