package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUnionTabUserDAO;
import com.itgrids.partyanalyst.model.UnionTabUser;

public class UnionTabUserDAO extends GenericDaoHibernate<UnionTabUser, Long> implements IUnionTabUserDAO{

	public UnionTabUserDAO() {
		super(UnionTabUser.class);
		// TODO Auto-generated constructor stub
	}

}
