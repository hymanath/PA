package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserLevelDAO;
import com.itgrids.partyanalyst.model.UserLevel;

public class UserLevelDAO extends GenericDaoHibernate<UserLevel, Long> implements IUserLevelDAO {
	
	public UserLevelDAO() {
		super(UserLevel.class);
	}
}

