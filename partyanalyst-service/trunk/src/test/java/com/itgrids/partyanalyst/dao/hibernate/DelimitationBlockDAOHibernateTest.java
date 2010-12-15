package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationBlockDAO;

public class DelimitationBlockDAOHibernateTest extends BaseDaoTestCase{

	private IDelimitationBlockDAO delimitationBlockDAO;

	public void setDelimitationBlockDAO(IDelimitationBlockDAO delimitationBlockDAO) {
		this.delimitationBlockDAO = delimitationBlockDAO;
	}
	public void test(){
		delimitationBlockDAO.getAll();		
	}
}
