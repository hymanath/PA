package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemExternalSourceDAO;
import com.itgrids.partyanalyst.model.ProblemExternalSource;

public class ProblemExternalSourceDAOHibernateTest extends BaseDaoTestCase{

	private IProblemExternalSourceDAO problemExternalSourceDAO;

	public IProblemExternalSourceDAO getProblemExternalSourceDAO() {
		return problemExternalSourceDAO;
	}

	public void setProblemExternalSourceDAO(
			IProblemExternalSourceDAO problemExternalSourceDAO) {
		this.problemExternalSourceDAO = problemExternalSourceDAO;
	}
	
	public void testGetAll(){
		List<ProblemExternalSource> list = problemExternalSourceDAO.getAll();
		assertEquals(1, list.size());
	}
	
}
