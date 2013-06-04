/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 25, 2010
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
