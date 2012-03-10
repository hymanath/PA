package com.itgrids.partyanalyst.dao.hibernate;
import java.util.*;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyProfileDescriptionDAO;
import com.itgrids.partyanalyst.model.*;

public class PartyProfileDescriptionDAOHibernateTest extends BaseDaoTestCase {
	private IPartyProfileDescriptionDAO partyProfileDescriptionDAO;

	public void setPartyProfileDescriptionDAO(
			IPartyProfileDescriptionDAO partyProfileDescriptionDAO) {
		this.partyProfileDescriptionDAO = partyProfileDescriptionDAO;
	}

	/*public void test() {
		partyProfileDescriptionDAO.getAll();
	}*/
	/*public void  testGetPartyProfileDescription()
	{
		List<Object> list= partyProfileDescriptionDAO.getPartyProfileDescription(1l);
		for(Object params:list)
		{
			System.out.println(list.size());
			System.out.println(params);
		}
	}*/
	/*public void testGetPartyProfileDescription1()
	{
		
   List<Object> result = partyProfileDescriptionDAO.getMaxOrderNo(1l);
   for (Object object : result) {
	System.out.println(object.toString());

	
}
	}*/
	
	/*public void testGetPartyProfileDescription()
	{
		
   List<Object[]> result = partyProfileDescriptionDAO.getPartyProfileInfo(1l);
   for (Object[] object : result) {
	  System.out.println("patyid"+object[0]);
	   System.out.println("description"+object[1].toString());
	   System.out.println("orderno"+object[2]);
   }
	}*/
	/*public void testDeletePartyProfileDescriptionById()
	{
		Integer integer=partyProfileDescriptionDAO.deletePartyProfileDescriptionById(1l);
		System.out.println(integer);
	}*/
	
	 
	}

	

