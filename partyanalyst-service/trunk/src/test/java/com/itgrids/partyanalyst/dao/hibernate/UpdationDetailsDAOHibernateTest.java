package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUpdationDetailsDAO;

public class UpdationDetailsDAOHibernateTest extends BaseDaoTestCase {

	private IUpdationDetailsDAO updationDetailsDAO;

	public void setUpdationDetailsDAO(IUpdationDetailsDAO updationDetailsDAO) {
		this.updationDetailsDAO = updationDetailsDAO;
	}
	public void testGetAll(){
		updationDetailsDAO.getAll();
	}
}
