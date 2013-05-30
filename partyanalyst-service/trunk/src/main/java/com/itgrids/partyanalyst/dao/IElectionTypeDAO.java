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

import com.itgrids.partyanalyst.dao.columns.enums.ElectionTypeColumnNames;
import com.itgrids.partyanalyst.model.ElectionType;

/**
 * Interface for ElectionTypeDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface IElectionTypeDAO extends GenericDao<ElectionType, Long>{

	/**
	 * Find all ElectionType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ElectionType property to query
	 * @param value
	 *            the property value to match
	 * @return List<ElectionType> found by query
	 */
	public List<ElectionType> findByProperty(ElectionTypeColumnNames propertyName, Object value);

	public List<ElectionType> findByElectionType(Object electionType);

	public List<ElectionType> findByScope(Object scope);
	
	public String getElectionTypeByTypeId(Long electionTypeId);

}