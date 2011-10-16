package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IContentTypeDAO;
import com.itgrids.partyanalyst.model.ContentType;

public class ContentTypeDAOHibernateTest extends BaseDaoTestCase{

	private IContentTypeDAO contentTypeDAO;

	public void setContentTypeDAO(IContentTypeDAO contentTypeDAO) {
		this.contentTypeDAO = contentTypeDAO;
	}
	
	/*public void test()
	{
		contentTypeDAO.getAll();
	}*/
	
	public void testGetContentTypeByType()
	{
		ContentType contentType = (ContentType) contentTypeDAO.getContentTypeByType("Photo Gallary");
		
		System.out.println(contentType.getContentType());
	}
}
