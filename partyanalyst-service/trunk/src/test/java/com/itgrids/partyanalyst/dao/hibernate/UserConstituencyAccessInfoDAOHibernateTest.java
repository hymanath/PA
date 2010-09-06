package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;

public class UserConstituencyAccessInfoDAOHibernateTest extends BaseDaoTestCase{

	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;

	public IUserConstituencyAccessInfoDAO getUserConstituencyAccessInfoDAO() {
		return userConstituencyAccessInfoDAO;
	}

	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
	}
	
	public void testGetAll(){
		System.out.println(userConstituencyAccessInfoDAO.getAll().size());
	}
	
	public void testFindByUser(){
		List list = userConstituencyAccessInfoDAO.findByUser(1l);
		assertEquals(list.size() >= 0, true);
	}
}
