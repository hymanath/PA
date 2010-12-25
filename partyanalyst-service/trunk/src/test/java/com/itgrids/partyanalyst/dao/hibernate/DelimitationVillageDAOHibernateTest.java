package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationVillageDAO;

public class DelimitationVillageDAOHibernateTest extends BaseDaoTestCase{
	
	private IDelimitationVillageDAO delimitationVillageDAO;

	public void setDelimitationVillageDAO(
			IDelimitationVillageDAO delimitationVillageDAO) {
		this.delimitationVillageDAO = delimitationVillageDAO;
	}
	
	/*public void test()
	{
		delimitationVillageDAO.getAll();
	}*/
	
	public void testGetVillagesFromPartialMandal()
	{
		List list = delimitationVillageDAO.getVillagesFromPartialMandal(843l);
		System.out.println(list.size());
		
		for(int i=0;i<list.size();i++)
		{
			System.out.println(i);
			Object obj = list.get(i);
			System.out.println((Long)obj);
			
		}
	}

}
