/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 25, 2010
 */
package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.LocalGroupRegion;

/**
 * @author Sai Krishna
 *
 */
public interface ILocalGroupRegionDAO extends GenericDao<LocalGroupRegion, Long> {
	public Integer deleteLocalUserGroupRegionById(Long groupId);

}
