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
	
	public void testGetVotersCountForPartyByCustomGroup()
	{
		List<Object[]> list = customVoterDAO.getVotersCountForPartyByCustomGroup(1l,1l);
		for(Object[] params : list){
			System.out.println(params[0]);
			System.out.println(params[1]);
			System.out.println(params[2]);
			System.out.println(params[3]);
		}
	}
	
	/*public void testGetTotalVotersByCustomGroupId(){
		
		Long count = boothPublicationVoterDAO.getTotalVotersByCustomGroupId(2l);
		System.out.println(count);
		
		
	}*/
	
	/*public void testGetCasteWiseCustomVoterDetails()
	{
		List<Voter> list = customVoterDAO.getCasteWiseCustomVoterDetails(285l, 285l, 1l, 1l);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		 for(Voter voter:list)
		  System.out.println(voter.getName()+" "+voter.getVoterId());
	}*/
}
