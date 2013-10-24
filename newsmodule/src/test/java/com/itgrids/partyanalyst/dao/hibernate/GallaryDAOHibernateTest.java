package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IGallaryDAO;

public class GallaryDAOHibernateTest extends BaseDaoTestCase{
	
	private IGallaryDAO gallaryDAO;

	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}
	
	/*public void test()
	{
		gallaryDAO.getAll();
	}*/
	
	public void testGetGallariesForSelectedCategory()
	{
		List<Object[]> list = gallaryDAO.getGallariesForSelectedCategory(12L);
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]);
	}

}
