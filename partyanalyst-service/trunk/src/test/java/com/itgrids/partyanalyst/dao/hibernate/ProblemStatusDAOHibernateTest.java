package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemStatusDAO;
import com.itgrids.partyanalyst.model.ProblemStatus;

public class ProblemStatusDAOHibernateTest extends BaseDaoTestCase{

	private IProblemStatusDAO problemStatusDAO;

	public IProblemStatusDAO getProblemStatusDAO() {
		return problemStatusDAO;
	}

	public void setProblemStatusDAO(IProblemStatusDAO problemStatusDAO) {
		this.problemStatusDAO = problemStatusDAO;
	}
	
	/*public void testSave(){
		ProblemStatus problemStatus = new ProblemStatus("FIXED", null);
		problemStatusDAO.save(problemStatus);
		setComplete();
	}*/
	
	public void testGetAll(){
		List<ProblemStatus> list = problemStatusDAO.getAll();
		assertEquals(1, list.size());
	}
}
