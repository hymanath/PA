package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.ISpecialPageDescriptionDAO;

public class SpecialPageDescriptionDAOHibernateTest extends BaseDaoTestCase{
	private ISpecialPageDescriptionDAO specialPageDescriptionDAO;
	
	
	public void setSpecialPageDescriptionDAO(
			ISpecialPageDescriptionDAO specialPageDescriptionDAO) {
		this.specialPageDescriptionDAO = specialPageDescriptionDAO;
	}


	/*public void test()
	{
		specialPageDescriptionDAO.getAll();
	}*/
	/*public void testGetEventDescription()
	{
		List<Object[]> list=specialPageDescriptionDAO.getEventDescription(1l);
		for(Object[] params:list)
		{
			System.out.println(params[0]);
			System.out.println(params[1].toString());
			System.out.println(params[2]);
		}
	}*/
	
	/*public void testdeleteEventProfileDescriptionById(){
		Integer eventId=specialPageDescriptionDAO.deleteEventProfileDescriptionById(1l);
		System.out.println(eventId);
	}*/
	public void testgetMaxOrderNo()
	{
		Object orderNo=specialPageDescriptionDAO.getMaxOrderNo(1l);
		System.out.println(orderNo);
	}

}
