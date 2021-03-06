package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.model.VoterAgeRange;

public class VoterAgeRangeDAOHibernateTest extends BaseDaoTestCase{

private IVoterAgeRangeDAO voterAgeRangeDAO;
	
	public void setVoterAgeRangeDAO(IVoterAgeRangeDAO voterAgeRangeDAO) {
		this.voterAgeRangeDAO = voterAgeRangeDAO;
	}
	
	public IVoterAgeRangeDAO getVoterAgeRangeDAO() {
		return voterAgeRangeDAO;
	}

	/*public void test()
	{
		voterAgeRangeDAO.getAll();
	}*/
	
	/*public void testGetVoterAgeRangeIdByType()
	{
		System.out.println(voterAgeRangeDAO.getVoterAgeRangeIdByType("18-22"));
	}*/
	
	/*public void testGetAllVoterAgeRanges()
	{
		List<String> list = voterAgeRangeDAO.getAllVoterAgeRanges();
		for(String str : list)
			System.out.println(str);
	}*/
	
	/*public void testgetVoterAgeRangeDetails()
	{
		List<Long> list = voterAgeRangeDAO.getVoterAgeRangeDetails();
		
			System.out.println(list.size());
	}*/
	
	public void testGetVoterAgeRangeList()
	{
		List<VoterAgeRange> list = voterAgeRangeDAO.getVoterAgeRangeList();
		System.out.println(list.size());
	}
	
}
