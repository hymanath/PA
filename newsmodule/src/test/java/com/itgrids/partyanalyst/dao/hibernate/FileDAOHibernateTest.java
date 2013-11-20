package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileDAO;

public class FileDAOHibernateTest extends BaseDaoTestCase{
	
	private IFileDAO fileDAO;

	public void setFileDAO(IFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	/*public void test()
	{
		fileDAO.getAll();
	}*/
	
	public void testgetTotalFilesList()
	{
		System.out.println(fileDAO.getTotalFilesList(1L, null, null, null, null).size());
	}

}
