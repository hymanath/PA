package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IGallaryKeywordDAO;


public class GallaryKeywordDAOHibernateTest extends BaseDaoTestCase{
	
	
	private IGallaryKeywordDAO gallaryKeywordDAO;


	public void setGallaryKeywordDAO(IGallaryKeywordDAO gallaryKeywordDAO) {
		this.gallaryKeywordDAO = gallaryKeywordDAO;
	}

    public void test()
    {
	  gallaryKeywordDAO.getAll();
    }

}


