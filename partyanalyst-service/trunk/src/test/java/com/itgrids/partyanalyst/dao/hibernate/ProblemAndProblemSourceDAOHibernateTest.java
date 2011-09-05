package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemAndProblemSourceDAO;
import com.itgrids.partyanalyst.model.ProblemAndProblemSource;

public class ProblemAndProblemSourceDAOHibernateTest extends BaseDaoTestCase{

	private IProblemAndProblemSourceDAO problemAndProblemSourceDAO;

	public IProblemAndProblemSourceDAO getProblemAndProblemSourceDAO() {
		return problemAndProblemSourceDAO;
	}

	public void setProblemAndProblemSourceDAO(
			IProblemAndProblemSourceDAO problemAndProblemSourceDAO) {
		this.problemAndProblemSourceDAO = problemAndProblemSourceDAO;
	}
	
	public void testGetAll(){
		problemAndProblemSourceDAO.getAll();
	}
	
}
