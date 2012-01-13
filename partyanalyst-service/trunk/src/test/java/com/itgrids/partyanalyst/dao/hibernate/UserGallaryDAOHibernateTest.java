package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserGallaryDAO;

public class UserGallaryDAOHibernateTest extends BaseDaoTestCase{

	private IUserGallaryDAO userGallaryDAO;

	public void setUserGallaryDAO(IUserGallaryDAO userGallaryDAO) {
		this.userGallaryDAO = userGallaryDAO;
	}
	
	/*public void test()
	{
		userGallaryDAO.getAll();
	}*/
	
	public void testGetAllNewsGallaryBasedOnUserId(){
		List<Object[]> list = userGallaryDAO.getAllNewsGallaryBasedOnUserId(1l);
		System.out.println(list.size());
		for(Object[] params :list){
		System.out.println(params[0]);
		System.out.println(params[1]);
		}
	}
}
