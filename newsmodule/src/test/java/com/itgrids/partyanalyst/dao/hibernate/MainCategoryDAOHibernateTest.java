package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMainCategoryDAO;


public class MainCategoryDAOHibernateTest extends BaseDaoTestCase{
		
	private IMainCategoryDAO mainCategoryDAO;

	public void setMainCategoryDAO(IMainCategoryDAO mainCategoryDAO) {
		this.mainCategoryDAO = mainCategoryDAO;
	}


public void test()
{
	mainCategoryDAO.getAll();
}
	}


