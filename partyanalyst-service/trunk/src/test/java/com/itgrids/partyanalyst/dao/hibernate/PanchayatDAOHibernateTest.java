package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
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
	
	/*public void testGetPanchayatsByTehsilId()
	{
		List<Object[]> list = panchayatDAO.getPanchayatsByTehsilId(844l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0].toString()+"------"+params[1].toString());
		}
	}*/
	
	/*public void testgetPanchayatsByConstituencyId()
	{
	List<Object[]>  list =panchayatDAO.getPanchayatsByConstituencyId(232l);
	for(Object[] params : list)
	{
		System.out.println(params[1].toString());
	}
	}*/
	
	/*public void testgetPanchayatsBymandalId()
	{
		List<Object[]> list = panchayatDAO.getPanchayatsBymandalId(295l);
		System.out.println(list.size());
	}
*/
	/*public void testgetPanchayatiesCount()
	{
	 //Long value = panchayatDAO.getPanchayatiesCount(844l,"constituency");
	 //System.out.println(value);
	}*/
	
	/*public void testGetPanchayatIdsByTehsilId()
	{
		List<Long> list = panchayatDAO.getPanchayatIdsByTehsilId(844l);
		System.out.println(list.size());
		
		for(Long id : list)
			System.out.println(id);
	}*/
	
	/*public void testGetPanchayatIdsByMandalIdsList()
	{
		List<Long> mandalIdsList = new ArrayList<Long>(0);
		mandalIdsList.add(844l);
		List<Object[]> list = panchayatDAO.getPanchayatIdsByMandalIdsList(mandalIdsList);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]);
		}
	}*/
	
	public void testGetPanchayatIdsBytehsilIdsList()
	{
		List<Long> mandalIdsList = new ArrayList<Long>(0);
		mandalIdsList.add(844l);
		List<Long> panchayatIdsList = panchayatDAO.getPanchayatIdsBytehsilIdsList(mandalIdsList);
		System.out.println(panchayatIdsList);
	}
	
}
