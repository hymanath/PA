package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserTypeDAO;
import com.itgrids.partyanalyst.model.UserType;

public class UserTypeDAO extends GenericDaoHibernate<UserType, Long> implements IUserTypeDAO {
	
	public UserTypeDAO() {
		super(UserType.class);
	}
}
