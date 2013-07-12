package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAccessRestrictedSessionDAO;

public class AccessRestrictedSessionDAOHibernateTest extends BaseDaoTestCase{
	
	private IAccessRestrictedSessionDAO accessRestrictedSessionDAO;

	public void setAccessRestrictedSessionDAO(
			IAccessRestrictedSessionDAO accessRestrictedSessionDAO) {
		this.accessRestrictedSessionDAO = accessRestrictedSessionDAO;
	}
	
	public void test()
	{
		accessRestrictedSessionDAO.getAll();
	}

}
