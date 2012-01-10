package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISpecialPageCustomPagesDAO;

public class SpecialPageCustomPagesDAOHibernateTest extends BaseDaoTestCase{
	
	private ISpecialPageCustomPagesDAO specialPageCustomPagesDAO;

	public void setSpecialPageCustomPagesDAO(
			ISpecialPageCustomPagesDAO specialPageCustomPagesDAO) {
		this.specialPageCustomPagesDAO = specialPageCustomPagesDAO;
	}

	/*public void test()
	{
		specialPageCustomPagesDAO.getAll();
	}*/
	
	public void testGetCustomPagesOfASpecialPage()
	{
		List<Object[]> list = specialPageCustomPagesDAO.getCustomPagesOfASpecialPage(1l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0].toString() +"---"+params[1].toString());
		}
	}
}
