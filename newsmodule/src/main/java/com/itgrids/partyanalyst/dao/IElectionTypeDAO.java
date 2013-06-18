/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
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

	public List<ElectionType> findByProperty(ElectionTypeColumnNames propertyName, Object value);

	public List<ElectionType> findByElectionType(Object electionType);

	public List<ElectionType> findByScope(Object scope);
	
	//public String getElectionTypeByTypeId(Long electionTypeId);

}