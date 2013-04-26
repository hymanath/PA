package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserVoterCategoryDAO;

public class UserVoterCategoryDAOHibernateTest  extends BaseDaoTestCase{

	private IUserVoterCategoryDAO userVoterCategoryDAO;

	public void setUserVoterCategoryDAO(IUserVoterCategoryDAO userVoterCategoryDAO) {
		this.userVoterCategoryDAO = userVoterCategoryDAO;
	}
	
	/*
	public void testgetUserCategoryValues(){
		
		List<UserCategoryValues> categoryValues=userVoterCategoryDAO.getUserCategoryValues();
		System.out.println(categoryValues.size());
		
	}*/
/*public void testgetUserValues(){
		
		List<UserCategoryValues> categoryValues=userVoterCategoryDAO.checkCategoryExist(1l,"party");
		System.out.println(categoryValues.size());
		
	}*/


 /*public void testgetCategoryValuesList(){
		
		List<Object[]> categoryValues=userVoterCategoryDAO.getCategoryValuesList(1l);
		System.out.println(categoryValues.size());
		
	}*/

    /* public void test(){
    	 userVoterCategoryDAO.getAll();
     }*/
	
	public void testGetCategoryNameByCategoryId()
	{
		System.out.println(userVoterCategoryDAO.getCategoryNameByCategoryId(2l));
	}
}
