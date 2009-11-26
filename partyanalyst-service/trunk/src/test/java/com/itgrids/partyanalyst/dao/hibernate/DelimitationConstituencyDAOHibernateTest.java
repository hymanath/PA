package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.model.DelimitationConstituency;

public class DelimitationConstituencyDAOHibernateTest extends BaseDaoTestCase {

	IDelimitationConstituencyDAO delimitationConstituencyDAO;

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	
	public void testDelimitationConstituencies(){
		List<DelimitationConstituency> list = delimitationConstituencyDAO.findByElectionScopeIdStateIdAndElectionYear(new Long(1), new Long(1), new Long(2009));
		assertEquals(1,list.size());
	}
}
