package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.ISpecialPageMetaInfoDAO;

public class SpecialPageMetaInfoDAOHibernateTest extends BaseDaoTestCase{

	private ISpecialPageMetaInfoDAO specialPageMetaInfoDAO;

	public void setSpecialPageMetaInfoDAO(
			ISpecialPageMetaInfoDAO specialPageMetaInfoDAO) {
		this.specialPageMetaInfoDAO = specialPageMetaInfoDAO;
	}
	
	/*public void test()
	{
		specialPageMetaInfoDAO.getAll();
	}*/
	
	public void testGetMetaInfoForASpecialPage()
	{
		List<Object[]> list = specialPageMetaInfoDAO.getMetaInfoForASpecialPage(1l);
		System.out.println(list.size());
		
		System.out.println(list.get(0)[0].toString());
		System.out.println(list.get(0)[1].toString());
		
	}
}
