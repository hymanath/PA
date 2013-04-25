package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserVoterCategoryValueDAO;

public class UserVoterCategoryValueDAOHibernateTest  extends BaseDaoTestCase{
	
	private IUserVoterCategoryValueDAO userVoterCategoryValueDAO;

	public void setUserVoterCategoryValueDAO(
			IUserVoterCategoryValueDAO userVoterCategoryValueDAO) {
		this.userVoterCategoryValueDAO = userVoterCategoryValueDAO;
	}

	/*public void test(){
		userVoterCategoryValueDAO.getAll();
	}*/
	
	/*public void testgetVoterCategoryValues(){
		
		List list=userVoterCategoryValueDAO.checkCategoryExist(1l,"inc",1l);
		System.out.println(list);
	}*/
	
	/*public void testGetCategoeryValuesDAO()
	{
		List<Object[]> values = userVoterCategoryValueDAO.getCategoeryValuesDAO(1l, 5l);
		
		for (Object[] parms : values) {
			System.out.println(parms[0]+ ":"   + parms[1] + ":" + parms[2] );
		}
		
	}*/
	
	public void testDeleteCategoeryValues()
	{
		List<Long> categoeryIds = new ArrayList<Long>();
		categoeryIds.add(25l);
		categoeryIds.add(26l);
		userVoterCategoryValueDAO.deleteCategoeryValues(categoeryIds);
	}

}
