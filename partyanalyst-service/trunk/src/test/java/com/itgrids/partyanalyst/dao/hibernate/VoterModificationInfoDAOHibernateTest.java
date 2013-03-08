package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;

public class VoterModificationInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterModificationInfoDAO voterModificationInfoDAO;

	public void setVoterModificationInfoDAO(
			IVoterModificationInfoDAO voterModificationInfoDAO) {
		this.voterModificationInfoDAO = voterModificationInfoDAO;
	}
	
	/*public void test()
	{
		voterModificationInfoDAO.getAll();
	}*/
	public void test1()
	{
		List<Long> list1 = new ArrayList<Long>();
		list1.add(1l);
		voterModificationInfoDAO.getGenderWiseVoterModificationsBetweenPublications(1l,2l,3l,list1);
	}
	public void test2()
	{
		List<Long> list1 = new ArrayList<Long>();
		list1.add(1l);
		voterModificationInfoDAO.getGenderWiseVoterModificationsForEachPublication(1l,2l,3l,list1);
	}
	public void testgetVoterModificationInfoIdByReportLevelValue()
	{
		Long id = voterModificationInfoDAO.getVoterModificationInfoIdByReportLevelValue(1l, 232l, 7l, 1l, 232l);
		System.out.println(id); 
		
	}
}
