package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IUserProblemDAO;
import com.itgrids.partyanalyst.model.UserProblem;

public class UserProblemDAO extends GenericDaoHibernate<UserProblem,Long> implements IUserProblemDAO{

	public UserProblemDAO()
	{
		super(UserProblem.class);
	}
}
