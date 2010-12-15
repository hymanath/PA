package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICensusYearDAO;

public class CensusYearDAOHibernateTest extends BaseDaoTestCase{
	
	private ICensusYearDAO censusYearDAO;

	public void setCensusYearDAO(ICensusYearDAO censusYearDAO) {
		this.censusYearDAO = censusYearDAO;
	}
	public void test(){
		censusYearDAO.getAll();		
	}

}
