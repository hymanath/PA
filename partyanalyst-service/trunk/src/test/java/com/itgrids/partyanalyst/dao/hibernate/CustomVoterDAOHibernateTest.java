package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICustomVoterDAO;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.utils.IConstants;

public class CustomVoterDAOHibernateTest extends BaseDaoTestCase{
	
	private ICustomVoterDAO customVoterDAO;

	public ICustomVoterDAO getCustomVoterDAO() {
		return customVoterDAO;
	}

	public void setCustomVoterDAO(ICustomVoterDAO customVoterDAO) {
		this.customVoterDAO = customVoterDAO;
	}
	/*
	public void test()
	{
		customVoterDAO.getAll();
	}*/

	/*public void testgetCasteWiseCustomVotersCount()
	{
		List<Object[]> list = customVoterDAO.getCasteWiseCustomVotersCount(1l, 1l);
		System.out.println(list.size());
	}*/
	
	/*public void testgetCustomGroupWiseVotersDetailsForCaste()
	{
		List<Object[]> list = customVoterDAO.getCustomGroupWiseVotersDetailsForCaste(1l,IConstants.RURAL,1l);
		System.out.println(list.size());
	}*/
	
	public void testGetCasteWiseCustomVoterDetails()
	{
		List<Voter> list = customVoterDAO.getCasteWiseCustomVoterDetails(285l, 285l, 1l, 1l);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		 for(Voter voter:list)
		  System.out.println(voter.getName()+" "+voter.getVoterId());
	}
}
