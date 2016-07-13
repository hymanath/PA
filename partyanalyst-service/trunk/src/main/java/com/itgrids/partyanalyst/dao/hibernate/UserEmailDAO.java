package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserEmailDAO;
import com.itgrids.partyanalyst.model.UserEmail;

public class UserEmailDAO extends GenericDaoHibernate<UserEmail, Long> implements IUserEmailDAO {
	public UserEmailDAO(){
		super(UserEmail.class);
	}
}
