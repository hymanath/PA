/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StaticUserDesignation;

/**
 * @author Sai Krishna
 *
 */
public interface IStaticUserDesignationDAO extends GenericDao<StaticUserDesignation, Long> {

	@SuppressWarnings("unchecked")
	public List getDesignationsByStaticLocalGroupId(Long staticLocalGroupId);
	
	public List<Object[]> getDesignations();
}
