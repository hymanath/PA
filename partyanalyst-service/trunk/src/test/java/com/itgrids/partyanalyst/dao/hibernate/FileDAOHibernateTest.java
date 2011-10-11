package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileDAO;

public class FileDAOHibernateTest extends BaseDaoTestCase{
	
	private IFileDAO fileDAO;

	

	public void setFileDAO(IFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	public void test()
	{
		fileDAO.getAll();
		
	}

	
}
