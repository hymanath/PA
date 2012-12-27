package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserCategoryValuesDAO;
import com.itgrids.partyanalyst.model.UserCategoryValues;

public class UserCategoryValuesDAOHibernateTest  extends BaseDaoTestCase{

	private IUserCategoryValuesDAO userCategoryValuesDAO;

	public void setUserCategoryValuesDAO(
			IUserCategoryValuesDAO userCategoryValuesDAO) {
		this.userCategoryValuesDAO = userCategoryValuesDAO;
	}
	
	public void testgetUserCategoryValues(){
		
		List<UserCategoryValues> categoryValues=userCategoryValuesDAO.getUserCategoryValues();
		System.out.println(categoryValues.size());
		
	}
	
}
