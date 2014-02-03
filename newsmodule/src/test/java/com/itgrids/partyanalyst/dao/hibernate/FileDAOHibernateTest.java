package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
	
	/*public void testgetTotalFilesList()
	{
		System.out.println(fileDAO.getTotalFilesList(1L, null, null, null, null).size());
	}*/
	public void testgetTotalFilesList()
	{
		
		List<Object[]> val = fileDAO.getSelectedNewsBySearchCriteria("select 1l,'' from File", null, null, null, null);
		for(Object[] v:val){
			System.out.println((Long)v[0]);
		}
	}
	
}
