package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationWardDAO;

public class DelimitationWardDAOHibernateTest extends BaseDaoTestCase{
	
	private IDelimitationWardDAO delimitationWardDAO;

	public void setDelimitationWardDAO(IDelimitationWardDAO delimitationWardDAO) {
		this.delimitationWardDAO = delimitationWardDAO;
	}
	public void test(){
		delimitationWardDAO.getAll();		
	}

}
