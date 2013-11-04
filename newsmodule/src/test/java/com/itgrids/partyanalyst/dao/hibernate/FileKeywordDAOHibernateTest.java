package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileKeywordDAO;

public class FileKeywordDAOHibernateTest extends BaseDaoTestCase{
	
	
	IFileKeywordDAO FileKeywordDAO;

	public void setFileKeywordDAO(
			IFileKeywordDAO FileKeywordDAO) {
		this.FileKeywordDAO = FileKeywordDAO;
	}

}
