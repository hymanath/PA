package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserEntitlementGroupRegionDAO;
import com.itgrids.partyanalyst.model.UserEntitlementGroupRegion;

public class UserEntitlementGroupRegionDAO extends GenericDaoHibernate<UserEntitlementGroupRegion, Long> implements IUserEntitlementGroupRegionDAO{

	public UserEntitlementGroupRegionDAO()
	{
		super(UserEntitlementGroupRegion.class);
	}
}
