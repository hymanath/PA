package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyTownDAO;

public class DelimitationConstituencyTownDAOHibernateTest extends BaseDaoTestCase{
	
	private IDelimitationConstituencyTownDAO delimitationConstituencyTownDAO;

	public void setDelimitationConstituencyTownDAO(
			IDelimitationConstituencyTownDAO delimitationConstituencyTownDAO) {
		this.delimitationConstituencyTownDAO = delimitationConstituencyTownDAO;
	}
	
	/*public void test()
	{
		delimitationConstituencyTownDAO.getAll();
	}*/
	
	/*public void testGetLatestTownsForAConstituency()
	{
		List<Object[]> list = delimitationConstituencyTownDAO.getLatestTownsForAConstituency(237l);
		System.out.println(list.size());
		
		for(int i=0; i<list.size();i++)
		{
			Object[] obj = (Object[]) list.get(i);
			System.out.println("delim const Id "+ obj[0].toString());
			System.out.println("Township Id "+ obj[1].toString());
			System.out.println("Township Name Id "+ obj[2].toString());
			System.out.println("is partial Id "+ obj[3].toString());
		}
	}*/
	
	public void testGetLatestTownsForATehsil()
	{
		List<Object[]> list = delimitationConstituencyTownDAO.getLatestTownsForATehsil(237l, 854l);
		System.out.println(list.size());
		
		for(int i=0; i<list.size();i++)
		{
			Object[] obj = (Object[]) list.get(i);
			System.out.println("delim const Town Id "+ obj[0].toString());
			System.out.println("Township Id "+ obj[1].toString());
			System.out.println("is partial Id "+ obj[2].toString());
		}
	}

}
