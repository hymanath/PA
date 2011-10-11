package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IContentTypeDAO;

public class ContentTypeDAOHibernateTest extends BaseDaoTestCase{

	private IContentTypeDAO contentTypeDAO;

	public void setContentTypeDAO(IContentTypeDAO contentTypeDAO) {
		this.contentTypeDAO = contentTypeDAO;
	}
	
	public void test()
	{
		contentTypeDAO.getAll();
	}
}
