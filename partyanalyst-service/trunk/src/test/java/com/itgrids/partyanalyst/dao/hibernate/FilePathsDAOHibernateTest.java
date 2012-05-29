package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFilePathsDAO;

public class FilePathsDAOHibernateTest extends BaseDaoTestCase {
   
	private IFilePathsDAO filePathsDAO;

	public void setFilePathsDAO(IFilePathsDAO filePathsDAO) {
		this.filePathsDAO = filePathsDAO;
	}
	
	/*public void test(){
		
		filePathsDAO.getAll();
	 }*/
	public void testGetMaxOrderNo()
	{
		List<Object> list = filePathsDAO.getMaxOrderNo();
		System.out.println(list.size());
	}
}
