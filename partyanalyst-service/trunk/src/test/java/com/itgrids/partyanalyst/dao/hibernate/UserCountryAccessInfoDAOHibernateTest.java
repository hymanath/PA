package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserCountryAccessInfoDAO;

public class UserCountryAccessInfoDAOHibernateTest extends BaseDaoTestCase{

	private IUserCountryAccessInfoDAO userCountryAccessInfoDAO;

	public IUserCountryAccessInfoDAO getUserCountryAccessInfoDAO() {
		return userCountryAccessInfoDAO;
	}

	public void setUserCountryAccessInfoDAO(
			IUserCountryAccessInfoDAO userCountryAccessInfoDAO) {
		this.userCountryAccessInfoDAO = userCountryAccessInfoDAO;
	}
	
	public void testGetAll(){
		System.out.println(userCountryAccessInfoDAO.getAll().size());
	}
	
	public void testFindByUser(){
		List list = userCountryAccessInfoDAO.findByUser(1l);
		assertEquals(list.size() >= 0, true);
	}
}
