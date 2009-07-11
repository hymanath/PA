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

import com.itgrids.partyanalyst.model.ElectionScope;

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
	public List<ElectionScope> findByProperty(String propertyName, Object value);

	public List<ElectionScope> findByCountryStateId(Object countryStateId);

}