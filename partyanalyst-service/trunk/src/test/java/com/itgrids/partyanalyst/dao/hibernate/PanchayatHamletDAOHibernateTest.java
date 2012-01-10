package com.itgrids.partyanalyst.dao.hibernate;

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
	
	public void testGetHamletsOfAPanchayat()
	{
		List<Object[]> list = panchayatHamletDAO.getHamletsOfAPanchayat(1l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0].toString() +"---"+params[1].toString());
		}
	}
}
