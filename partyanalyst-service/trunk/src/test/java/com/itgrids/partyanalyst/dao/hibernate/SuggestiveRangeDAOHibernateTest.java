package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISuggestiveRangeDAO;

public class SuggestiveRangeDAOHibernateTest extends BaseDaoTestCase{
	
	private ISuggestiveRangeDAO suggestiveRangeDAO;

	public void setSuggestiveRangeDAO(ISuggestiveRangeDAO suggestiveRangeDAO) {
		this.suggestiveRangeDAO = suggestiveRangeDAO;
	}
	
	public void test()
	{
		suggestiveRangeDAO.getAll();
	}

}
