package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICasteInsertTypeDAO;

public class CasteInsertTypeDAOHibernateTest extends BaseDaoTestCase{
	private ICasteInsertTypeDAO casteInsertTypeDAO;

	public void setCasteInsertTypeDAO(ICasteInsertTypeDAO casteInsertTypeDAO) {
		this.casteInsertTypeDAO = casteInsertTypeDAO;
	}
	

	public void test()
	{
		casteInsertTypeDAO.getAll();
	}
}
