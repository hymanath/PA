package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;

public class ConstituencyDAOHibernateTest extends BaseDaoTestCase{

	IConstituencyDAO constituencyDAO;

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	/*public void testfindByStateIdForUser(){
		
	List<Object[]> constituencies = constituencyDAO.findConstituencyByDistrictElectionType(7l,"Assembly");
	
		for (Object[] object : constituencies) {
			System.out.println(object[0]);
			System.out.println(object[1]);
			}
	}*/
	
	/*public void testgetStateDistrictConstituency()
	{
		List list = constituencyDAO.getStateDistrictConstituency(232L);
		for(Object[] params:(List<Object[]>)list)
		 System.out.println(params[0]+" "+params[1]+" "+params[2]+" "+params[3]+" "+params[4]);
 	}*/
	
	
	/*public void testgetStateByConstituencyId()
	{
	  System.out.println(constituencyDAO.getStateByConstituencyId(232l).size());	
	}*/
	
	public void testgetStateAndDistrictDetails()
	{
		List<Object[]> values = constituencyDAO.getStateAndDistrictDetails(232l);
		for(Object[] params:values)
			 System.out.println(params[0]+" "+params[1]);
	}
	
}
