package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEntitlementDAO;
import com.itgrids.partyanalyst.model.Entitlement;

public class EntitlementDAO  extends GenericDaoHibernate<Entitlement,Long> implements IEntitlementDAO {

	public EntitlementDAO(){
		super(Entitlement.class);
	}

}
