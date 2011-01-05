package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IConstituencyCensusDetailsDAO;

public class ConstituencyCensusDetailsDAOHibernateTest extends BaseDaoTestCase{
	
	private IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO;

	public void setConstituencyCensusDetailsDAO(
			IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO) {
		this.constituencyCensusDetailsDAO = constituencyCensusDetailsDAO;
	}
	/*public void test()
	{
		constituencyCensusDetailsDAO.getAll();
	}*/
	
	public void testFindConstituencyWiseCensusDetails()
	{
		List<Object[]> list =  constituencyCensusDetailsDAO.findConstituencyWiseCensusDetails(1l, 232l, 2001l);
		System.out.println(list.size());
		for(Object obj:list.get(0))
		{
			System.out.println(obj.toString());
		}
	}
	
	/*public void testCheckForConstituencyExistance()
	{
		List<Long> list =  constituencyCensusDetailsDAO.checkForConstituencyExistance(232l);
		System.out.println(list.size());
		
		for(Long id:list){
			System.out.println(id);
			if(232l==id)
			{
				System.out.println("y");
			}
		}
	}*/
}
