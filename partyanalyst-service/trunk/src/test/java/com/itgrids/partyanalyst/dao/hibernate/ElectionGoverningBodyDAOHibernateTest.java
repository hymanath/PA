package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IElectionGoverningBodyDAO;
import com.itgrids.partyanalyst.model.ElectionGoverningBody;

public class ElectionGoverningBodyDAOHibernateTest extends BaseDaoTestCase{

	private IElectionGoverningBodyDAO electionGoverningBodyDAO;

	
	public IElectionGoverningBodyDAO getElectionGoverningBodyDAO() {
		return electionGoverningBodyDAO;
	}


	public void setElectionGoverningBodyDAO(
			IElectionGoverningBodyDAO electionGoverningBodyDAO) {
		this.electionGoverningBodyDAO = electionGoverningBodyDAO;
	}


	public void testGetAll(){
		List<ElectionGoverningBody> list = electionGoverningBodyDAO.getAll();
		assertEquals(list.size() >= 0, true);
	}
	
	
}
