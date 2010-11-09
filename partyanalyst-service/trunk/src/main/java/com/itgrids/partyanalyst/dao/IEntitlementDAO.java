/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 10,2010
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Entitlement;
import com.itgrids.partyanalyst.model.GroupEntitlement;

public interface IEntitlementDAO extends GenericDao<Entitlement, Long> {
	
	public List<Entitlement> findEntitlementByName(String name);

	public List<Entitlement> getAllEntitlements();
	
	public List<Entitlement> checkForEntitlementAvailability(String name);
}
