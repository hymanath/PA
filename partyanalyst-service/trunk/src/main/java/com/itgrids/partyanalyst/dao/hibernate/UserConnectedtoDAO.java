package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserConnectedtoDAO;
import com.itgrids.partyanalyst.model.UserConnectedto;

public class UserConnectedtoDAO extends GenericDaoHibernate<UserConnectedto,Long> implements
		IUserConnectedtoDAO {

	public UserConnectedtoDAO() {
		super(UserConnectedto.class);
	}

}
