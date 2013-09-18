package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserStateAccessInfoDAO;

public class UserStateAccessInfoDAOHibernateTest extends BaseDaoTestCase{

	private IUserStateAccessInfoDAO userStateAccessInfoDAO;

	public IUserStateAccessInfoDAO getUserStateAccessInfoDAO() {
		return userStateAccessInfoDAO;
	}

	public void setUserStateAccessInfoDAO(
			IUserStateAccessInfoDAO userStateAccessInfoDAO) {
		this.userStateAccessInfoDAO = userStateAccessInfoDAO;
	}
	
	public void testGetAll(){
		System.out.println(userStateAccessInfoDAO.getAll().size());
	}
	
	public void testFindByUser(){
		List list = userStateAccessInfoDAO.findByUser(1l);
		assertEquals(list.size() >= 0, true);
		if(list != null && list.size() > 0)
		for(Object[] params:(List<Object[]>)list)
		 System.out.println(params[0]+" "+params[1]);
	}
}
