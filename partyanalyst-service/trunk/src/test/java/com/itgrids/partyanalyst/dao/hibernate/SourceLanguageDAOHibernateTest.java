package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;

public class SourceLanguageDAOHibernateTest extends BaseDaoTestCase {

	private ISourceLanguageDAO sourceLanguageDAO;

	public void setSourceLanguageDAO(ISourceLanguageDAO sourceLanguageDAO) {
		this.sourceLanguageDAO = sourceLanguageDAO;
	}
	public void test()
	{
		sourceLanguageDAO.getAll();
		
	}
}
