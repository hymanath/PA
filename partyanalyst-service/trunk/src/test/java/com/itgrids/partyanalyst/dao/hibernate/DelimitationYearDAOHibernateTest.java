package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationYearDAO;

public class DelimitationYearDAOHibernateTest extends BaseDaoTestCase{
	
	private IDelimitationYearDAO delimitationYearDAO;

	public void setDelimitationYearDAO(IDelimitationYearDAO delimitationYearDAO) {
		this.delimitationYearDAO = delimitationYearDAO;
	}
	public void test(){
		delimitationYearDAO.getAll();		
	}


}
