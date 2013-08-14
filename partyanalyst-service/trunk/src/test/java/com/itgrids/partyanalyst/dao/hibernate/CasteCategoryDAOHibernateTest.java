package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;

public class CasteCategoryDAOHibernateTest  extends BaseDaoTestCase{
	
	private ICasteCategoryDAO casteCategoryDAO;

	public void setCasteCategoryDAO(ICasteCategoryDAO casteCategoryDAO) {
		this.casteCategoryDAO = casteCategoryDAO;
	}
	
/*public void testgetCasteCategoryDetails()
{
 List<Object[]> result = casteCategoryDAO.getCasteCategoryDetails();
 System.out.println(result.size());
 for(Object[] params:result)
 {
	 System.out.println(params[1].toString());
 }
}*/
	
	public void testgetCasteCategoryList()
	{
		casteCategoryDAO.getCasteCategoryList();
	}
}
