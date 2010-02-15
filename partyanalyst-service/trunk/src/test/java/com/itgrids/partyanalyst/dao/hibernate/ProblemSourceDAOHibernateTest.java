package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemSourceDAO;
import com.itgrids.partyanalyst.model.ProblemSource;

public class ProblemSourceDAOHibernateTest extends BaseDaoTestCase{

	private IProblemSourceDAO problemSourceDAO;

	public IProblemSourceDAO getProblemSourceDAO() {
		return problemSourceDAO;
	}

	public void setProblemSourceDAO(IProblemSourceDAO problemSourceDAO) {
		this.problemSourceDAO = problemSourceDAO;
	}

	public void testGetAll(){
		List<ProblemSource> list = problemSourceDAO.getAll();
		assertEquals(1, list.size());
	}
}