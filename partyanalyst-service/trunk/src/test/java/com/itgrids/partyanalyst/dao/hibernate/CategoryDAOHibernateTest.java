package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICategoryDAO;

public class CategoryDAOHibernateTest extends BaseDaoTestCase {
	private ICategoryDAO categoryDAO;

	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	public void test()
	{
		categoryDAO.getAll();
		
	}

}
