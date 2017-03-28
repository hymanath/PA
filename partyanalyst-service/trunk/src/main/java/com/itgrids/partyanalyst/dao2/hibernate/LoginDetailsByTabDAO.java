package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ILoginDetailsByTabDAO;
import com.itgrids.partyanalyst.model.LoginDetailsByTab;

public class LoginDetailsByTabDAO extends GenericDaoHibernate<LoginDetailsByTab, Long> implements ILoginDetailsByTabDAO{

	public LoginDetailsByTabDAO() {
		super(LoginDetailsByTab.class);
	}

}
