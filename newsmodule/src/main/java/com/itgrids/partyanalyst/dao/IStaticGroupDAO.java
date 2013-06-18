/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */

package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.StaticGroup;

public interface IStaticGroupDAO extends GenericDao<StaticGroup, Long> {

	public List<StaticGroup> findByGroupDescription(String groupDescription);
	
	@SuppressWarnings("unchecked")
	public List findAllStaticGroupDetails();
	
	public List<StaticGroup> findAllStaticGroups();
	
}
