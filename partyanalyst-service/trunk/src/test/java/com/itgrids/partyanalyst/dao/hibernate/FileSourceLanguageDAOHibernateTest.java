package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileSourceLanguageDAO;

public class FileSourceLanguageDAOHibernateTest extends BaseDaoTestCase {
  
	 private IFileSourceLanguageDAO fileSourceLanguageDAO;

	public void setFileSourceLanguageDAO(
			IFileSourceLanguageDAO fileSourceLanguageDAO) {
		this.fileSourceLanguageDAO = fileSourceLanguageDAO;
	}
	 
	 public void test(){
		 
		 fileSourceLanguageDAO.getAll();
	 }
}
