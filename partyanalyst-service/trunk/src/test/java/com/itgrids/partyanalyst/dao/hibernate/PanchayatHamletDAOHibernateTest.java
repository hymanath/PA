package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;

public class PanchayatHamletDAOHibernateTest extends BaseDaoTestCase{

	private IPanchayatHamletDAO panchayatHamletDAO;

	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}
	
	/*public void test()
	{
		panchayatHamletDAO.getAll();
	}*/
	
	/*public void testGetHamletsOfAPanchayat()
	{
		List<Object[]> list = panchayatHamletDAO.getHamletsOfAPanchayat(4l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0].toString() +"---"+params[1].toString());
		}
	}*/
	
	/*public void testGetHamletsByPanchayatsLis()
	{
		List<Long> panchayatsList = new ArrayList<Long>(0);
		panchayatsList.add(1l);
		List<Object[]> list = panchayatHamletDAO.getHamletsByPanchayatsList(panchayatsList);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.print(params[0].toString() +"---"+params[1].toString());
			System.out.println("---"+params[2].toString() +"---"+params[3].toString());
		}
		
	}*/
	
	public void testgetHamletsListByConstituencyId()
	{
		List<Object[]> list = panchayatHamletDAO.getHamletsListByConstituencyId(232l, 8l);
		System.out.println(list.size());
	}
	
}
