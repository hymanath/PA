package com.itgrids.partyanalyst.dao.hibernate;


import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISourceDAO;

public class SourceDAOHibernateTest extends BaseDaoTestCase {

	private ISourceDAO sourceDAO;

	public void setSourceDAO(ISourceDAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}
	
	/*public void test()
	{
		sourceDAO.getAll();
		
	}*/
	public void testGetSourceIdBySource()
	{
		List<Object> list = sourceDAO.getSourceIdBySource("No Source");
		System.out.println(list.get(0));
	}
}
