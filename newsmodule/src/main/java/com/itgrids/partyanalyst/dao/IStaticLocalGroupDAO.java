/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StaticLocalGroup;

/**
 * @author Sai Krishna
 *
 */
public interface IStaticLocalGroupDAO extends GenericDao<StaticLocalGroup, Long> {

	@SuppressWarnings("unchecked")
	public List getAllStaticLocalGroups();
	
	@SuppressWarnings("unchecked")
	public List getStaticLocalGroupsForAUser(Long userId);
	
	
}
