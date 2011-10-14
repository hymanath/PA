package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileGallaryDAO;

public class FileGallaryDAOHibernateTest extends BaseDaoTestCase{

	private IFileGallaryDAO fileGallaryDAO;

	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
		this.fileGallaryDAO = fileGallaryDAO;
	}
	
	/*public void test()
	{
		fileGallaryDAO.getAll();
	}*/
	public void testGetAllRecordInGallary()
	{
		List<Object[]> results = fileGallaryDAO.getAllRecordInGallary(300L);
		System.out.println(results.size());
	}
	
}
