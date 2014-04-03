package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserSmsSentDAO;
import com.itgrids.partyanalyst.model.UserSmsSent;

public class UserSmsSentDAO extends GenericDaoHibernate<UserSmsSent, Long> implements IUserSmsSentDAO{

	public UserSmsSentDAO() {
		super(UserSmsSent.class);
	}
	
	
}
