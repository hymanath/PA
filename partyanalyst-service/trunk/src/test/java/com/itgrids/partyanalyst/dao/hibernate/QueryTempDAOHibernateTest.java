package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IQueryTempDAO;

public class QueryTempDAOHibernateTest extends BaseDaoTestCase{
	
	private IQueryTempDAO queryTempDAO;

	public void setQueryTempDAO(IQueryTempDAO queryTempDAO) {
		this.queryTempDAO = queryTempDAO;
	}
	
	/*public void test()
	{
		queryTempDAO.getAll();
	}*/
	
	public void testDeleteAll()
	{
		System.out.println(queryTempDAO.deleteAll());
	}
}
