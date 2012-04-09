package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;
import com.itgrids.partyanalyst.model.UserLoginDetails;

public class UserLoginDetailsDAO extends GenericDaoHibernate<UserLoginDetails, Long> implements IUserLoginDetailsDAO{
	
	public UserLoginDetailsDAO(){
		super(UserLoginDetails.class);
	}
}
