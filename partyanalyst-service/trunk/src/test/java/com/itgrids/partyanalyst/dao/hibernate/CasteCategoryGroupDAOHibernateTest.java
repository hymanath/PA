package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICasteCategoryGroupDAO;

public class CasteCategoryGroupDAOHibernateTest extends BaseDaoTestCase {
	
	private ICasteCategoryGroupDAO casteCategoryGroupDAO;

	

	public void setCasteCategoryGroupDAO(
			ICasteCategoryGroupDAO casteCategoryGroupDAO) {
		this.casteCategoryGroupDAO = casteCategoryGroupDAO;
	}
	
/*public void testGetCasteCategoryGroupDAO() {
	
List<Object[]> obj=	casteCategoryGroupDAO.getCasteCategoryGroupNames((long)1);
		
	System.out.println(obj.size());
	for(Object[] params : obj)
		System.out.println(params[0]+"-----"+params[1].toString());
		
		}
*/
	
	/*public void testGetAllCasteCategoryGroupInfoDetails()
	{
		List<Object[]> list = casteCategoryGroupDAO.getAllCasteCategoryGroupInfoDetails();
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
		}
	}*/
	
	public void testgetCasteCategoryGroupList()
	{
		casteCategoryGroupDAO.getCasteCategoryGroupList();
	}
}
