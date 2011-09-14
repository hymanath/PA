package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;

public class UserDistrictAccessInfoDAOHibernateTest extends BaseDaoTestCase{

	private IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO;

	public IUserDistrictAccessInfoDAO getUserDistrictAccessInfoDAO() {
		return userDistrictAccessInfoDAO;
	}

	public void setUserDistrictAccessInfoDAO(
			IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO) {
		this.userDistrictAccessInfoDAO = userDistrictAccessInfoDAO;
	}
	
	/*public void testGetAll(){
		System.out.println(userDistrictAccessInfoDAO.getAll().size());
	}
	
	public void testFindByUser(){
		List list = userDistrictAccessInfoDAO.findByUser(1l);
		assertEquals(list.size() >= 0, true);
	}
	public void testfindByUserAndState(){
		List list = userDistrictAccessInfoDAO.findByUserAndState(1L,9L);
		
		 System.out.println(list.size());
	}*/
	
}
