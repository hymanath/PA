/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 10,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEntitlementDAO;
import com.itgrids.partyanalyst.model.Entitlement;

public class EntitlementDAO extends GenericDaoHibernate<Entitlement, Long>
		implements IEntitlementDAO {

	public EntitlementDAO() {
		super(Entitlement.class);
	}

	public List<Entitlement> findEntitlementByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
