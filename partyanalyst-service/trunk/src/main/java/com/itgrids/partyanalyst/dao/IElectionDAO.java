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
	public List<Election> findByProperty(String propertyName, Object value);

	public List<Election> findByEndDate(Object endDate);

	public List<Election> findByElectionYear(Object electionYear);

}