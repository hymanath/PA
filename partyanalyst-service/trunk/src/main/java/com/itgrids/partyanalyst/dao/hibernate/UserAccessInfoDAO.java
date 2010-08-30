package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserAccessInfoDAO;
import com.itgrids.partyanalyst.model.UserAccessInfo;

public class UserAccessInfoDAO extends GenericDaoHibernate<UserAccessInfo, Long> implements IUserAccessInfoDAO{

	public UserAccessInfoDAO(){
		super(UserAccessInfo.class);
	}
	
}
