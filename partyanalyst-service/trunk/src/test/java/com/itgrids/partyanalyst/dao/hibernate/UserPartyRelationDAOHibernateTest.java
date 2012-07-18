package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserPartyRelationDAO;

public class UserPartyRelationDAOHibernateTest extends BaseDaoTestCase{

	private IUserPartyRelationDAO userPartyRelationDAO;

	
	public void setUserPartyRelationDAO(IUserPartyRelationDAO userPartyRelationDAO) {
		this.userPartyRelationDAO = userPartyRelationDAO;
	}


	/*public void test()
	{
		userPartyRelationDAO.getAll();
	}*/
	/*public void  testGetUserPartyRelationDetails()
	{
		List<Object[]> result = userPartyRelationDAO.getUserPartyRelationDetails(1l);
		System.out.println(result.size());
		if(result != null && result.size() > 0)
		{
			for(Object[] params : result)
			{
				System.out.println(params[0]+" --- "+"" +params[1]+" "+params[2].toString());
			}
		}
	}*/
	
	/*public void testDeleteUserPartyRelation()
	{
		 userPartyRelationDAO.deleteUserPartyRelation(1l);
	}*/
	public void testcheckPartyForUser()
	{
		Long count = userPartyRelationDAO.checkPartyForUser(1l,2l);
		System.out.println(count);
	}
	
}
