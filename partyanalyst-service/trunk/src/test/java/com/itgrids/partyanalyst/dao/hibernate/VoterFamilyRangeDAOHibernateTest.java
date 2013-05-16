package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterFamilyRangeDAO;

public class VoterFamilyRangeDAOHibernateTest extends BaseDaoTestCase{

private IVoterFamilyRangeDAO voterFamilyRangeDAO;
	
	public void setVoterFamilyRangeDAO(IVoterFamilyRangeDAO voterFamilyRangeDAO) {
		this.voterFamilyRangeDAO = voterFamilyRangeDAO;
	}
	
	/*public void test()
	{
		voterFamilyRangeDAO.getAll();
	}*/
	
	/*public void testGetVoterFamilyRangeIdByFamilyRange()
	{
		System.out.println(voterFamilyRangeDAO.getVoterFamilyRangeIdByFamilyRange("7-10"));
	}*/
	
	public void testgetVoterFamilyRangeDetails()
	{
		List<Long> list = voterFamilyRangeDAO.getVoterFamilyRangeDetails();
		System.out.println(list.size());
	}
}
