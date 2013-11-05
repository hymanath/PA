package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileKeywordDAO;

public class FileKeywordDAOHibernateTest extends BaseDaoTestCase{
	
	
	
	private IFileKeywordDAO fileKeywordDAO;

	public void setFileKeywordDAO(IFileKeywordDAO fileKeywordDAO) {
		this.fileKeywordDAO = fileKeywordDAO;
	}

     public void test()
     {
	    fileKeywordDAO.getAll();
     }
	}