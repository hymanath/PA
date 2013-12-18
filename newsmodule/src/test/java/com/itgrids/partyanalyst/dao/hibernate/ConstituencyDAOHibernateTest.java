package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.util.IConstants;

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
	
	/*public void testgetStateAndDistrictDetails()
	{
		List<Object[]> values = constituencyDAO.getStateAndDistrictDetails(232l);
		for(Object[] params:values)
			 System.out.println(params[0]+" "+params[1]);
	}*/
	
	
	/*public void testfindConstituencyByDistrictElectionType()
	{
		List<Object[]> values = constituencyDAO.findConstituencyByDistrictElectionTypeAndYear(19L,IConstants.ASSEMBLY_ELECTION_TYPE,"2009");
		System.out.println(values.size());
		for(Object[] params:values)
			 System.out.println(params[0]+" "+params[1]);
	}*/
	
	public void testgetConstituencyes()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(1l);
		ids.add(2l);
		List<Object[]> values= constituencyDAO.getConstituencyes(ids);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+  parms[1]);
		}
	}
	
}
