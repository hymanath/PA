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
import com.itgrids.partyanalyst.model.GroupEntitlement;

public class EntitlementDAO extends GenericDaoHibernate<Entitlement, Long>
		implements IEntitlementDAO {

	public EntitlementDAO() {
		super(Entitlement.class);
	}

	public List<Entitlement> findEntitlementByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Entitlement> getAllEntitlements() {
		StringBuilder sb = new StringBuilder();
		sb.append("select model.entitlementId,model.entitlementType from Entitlement model order by model.entitlementType asc");
		return getHibernateTemplate().find(sb.toString());
	}
	
	@SuppressWarnings("unchecked")
	public List<Entitlement> checkForEntitlementAvailability(String name){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.entitlementId,model.entitlementType from Entitlement model");
		sb.append(" where model.entitlementType = ?");
		return getHibernateTemplate().find(sb.toString(),name);
		
	}
}
