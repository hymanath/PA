package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationWardDAO;

public class DelimitationWardDAOHibernateTest extends BaseDaoTestCase{
	
	private IDelimitationWardDAO delimitationWardDAO;

	public void setDelimitationWardDAO(IDelimitationWardDAO delimitationWardDAO) {
		this.delimitationWardDAO = delimitationWardDAO;
	}
	/*public void test(){
		delimitationWardDAO.getAll();		
	}*/

	public void testGetWardsFromPartialTownship(){
		List list = delimitationWardDAO.getWardsFromPartialTownship(4l);
		System.out.println(list.size());
		
		/*for(int i=0;i<list.size();i++)
		{
			Object obj =  list.get(i);
			System.out.println(i+"   "+obj);
		}*/
		
		StringBuilder wardIds = new StringBuilder();
		for(int i=0;i<list.size();i++)
		{
			Object obj = list.get(i);
			wardIds.append(obj.toString());
			wardIds.append(",");
		}
		System.out.println(wardIds);
		String obj2 = wardIds.substring(0, wardIds.length()-1);
		System.out.println(obj2);
		
	}
}
