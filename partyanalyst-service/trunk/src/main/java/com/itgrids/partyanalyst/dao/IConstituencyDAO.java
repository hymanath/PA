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

import com.itgrids.partyanalyst.dao.columns.enums.CandidateColumnNames;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ElectionScope;

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

}