package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserVoterCategoryValueDAO;

public class UserVoterCategoryValueDAOHibernateTest  extends BaseDaoTestCase{
	
	private IUserVoterCategoryValueDAO userVoterCategoryValueDAO;

	public void setUserVoterCategoryValueDAO(
			IUserVoterCategoryValueDAO userVoterCategoryValueDAO) {
		this.userVoterCategoryValueDAO = userVoterCategoryValueDAO;
	}

	public void test(){
		userVoterCategoryValueDAO.getAll();
	}
	
	/*public void testgetVoterCategoryValues(){
		
		List list=userVoterCategoryValueDAO.checkCategoryExist(1l,"inc",1l);
		System.out.println(list);
	}*/

}
