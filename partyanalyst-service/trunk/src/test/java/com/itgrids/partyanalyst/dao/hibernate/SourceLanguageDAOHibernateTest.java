package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;

public class SourceLanguageDAOHibernateTest extends BaseDaoTestCase {

	private ISourceLanguageDAO sourceLanguageDAO;

	public void setSourceLanguageDAO(ISourceLanguageDAO sourceLanguageDAO) {
		this.sourceLanguageDAO = sourceLanguageDAO;
	}
	/*public void test()
	{
		sourceLanguageDAO.getAll();
		
	}*/
	public void testGetLanguageIdByLanguage()
	{
		List<Object> list = sourceLanguageDAO.getLanguageIdByLanguage("No Language");
		System.out.println(list.get(0));
	}
}
