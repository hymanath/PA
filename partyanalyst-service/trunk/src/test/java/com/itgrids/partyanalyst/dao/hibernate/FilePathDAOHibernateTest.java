package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFilePathDAO;

public class FilePathDAOHibernateTest extends BaseDaoTestCase {
   
	private IFilePathDAO filePathDAO;

	public void setFilePathDAO(IFilePathDAO filePathDAO) {
		this.filePathDAO = filePathDAO;
	}
	
	public void test(){
		filePathDAO.getAll();
	 }
}
