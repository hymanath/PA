package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;

public class DelimitationConstituencyMandalDAOHibernateTest extends BaseDaoTestCase{
	
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	
	/*public void test()
	{
		System.out.println(delimitationConstituencyMandalDAO.getAll());
	}
	*/
	
	/*public void testgetMandalsOfConstituency()
	{
		List list = delimitationConstituencyMandalDAO.getMandalsOfConstituency(27772L);
		System.out.println(list.size());
	}
*/
  public void testgetStateDistrictConstituencyIdByMandalId()
  {
	  List<Object[]> list = delimitationConstituencyMandalDAO.getStateDistrictConstituencyIdByMandalId(832L);
	  System.out.println(list.size());
	  for(Object[] params:list)
	   System.out.println(params[0]+" "+params[1]+" "+params[2]+" "+params[3]);
  }
	
}
