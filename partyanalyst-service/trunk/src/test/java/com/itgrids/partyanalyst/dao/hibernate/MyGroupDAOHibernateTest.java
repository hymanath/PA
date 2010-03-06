package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMyGroupDAO;
import com.itgrids.partyanalyst.model.MyGroup;


public class MyGroupDAOHibernateTest extends BaseDaoTestCase {

	private IMyGroupDAO myGroupDAO;

	public IMyGroupDAO getMyGroupDAO() {
		return myGroupDAO;
	}

	public void setMyGroupDAO(IMyGroupDAO myGroupDAO) {
		this.myGroupDAO = myGroupDAO;
	}
	
	public void testGetAll(){
		List<MyGroup> list = myGroupDAO.getAll();
		System.out.println(list.size());
	}
	
}
