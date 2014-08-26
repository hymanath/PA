package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserEntitlementDAO;
import com.itgrids.partyanalyst.model.UserEntitlement;

public class UserEntitlementDAO  extends GenericDaoHibernate<UserEntitlement,Long> implements IUserEntitlementDAO {

	public UserEntitlementDAO(){
		super(UserEntitlement.class);
	}
	

}
