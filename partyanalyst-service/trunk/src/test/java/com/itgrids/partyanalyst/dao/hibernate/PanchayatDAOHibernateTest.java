package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPanchayatDAO;

public class PanchayatDAOHibernateTest extends BaseDaoTestCase{
	
	private IPanchayatDAO panchayatDAO;

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	
	/*public void test()
	{
		panchayatDAO.getAll();
	}
	*/
	
	public void testGetPanchayatsByTehsilId()
	{
		List<Object[]> list = panchayatDAO.getPanchayatsByTehsilId(844l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0].toString()+"------"+params[1].toString());
		}
	}

}
