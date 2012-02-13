package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMinistryScopeDAO;

public class MinistryScopeDAOHibernateTest extends BaseDaoTestCase{
	
	private IMinistryScopeDAO ministryScopeDAO;

	public void setMinistryScopeDAO(IMinistryScopeDAO ministryScopeDAO) {
		this.ministryScopeDAO = ministryScopeDAO;
	}
	
	public void test()
	{
		ministryScopeDAO.getAll();
	}

}
