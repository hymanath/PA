package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IKeywordDAO;

public class KeywordDAOHibernateTest extends BaseDaoTestCase{
	
	private IKeywordDAO keywordDAO;

	
public void setKeywordDAO(IKeywordDAO keywordDAO) {
		this.keywordDAO = keywordDAO;
	}


public void test()
{
	keywordDAO.getAll();
}
}
