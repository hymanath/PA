package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemFundSourceDAO;
import com.itgrids.partyanalyst.model.ProblemFundSource;

public class ProblemFundSourceDAOHibernateTest extends BaseDaoTestCase{

	private IProblemFundSourceDAO problemFundSourceDAO;

	public IProblemFundSourceDAO getProblemFundSourceDAO() {
		return problemFundSourceDAO;
	}

	public void setProblemFundSourceDAO(IProblemFundSourceDAO problemFundSourceDAO) {
		this.problemFundSourceDAO = problemFundSourceDAO;
	}
	
	/*public void testSave(){
		ProblemFundSource problemFundSource = new ProblemFundSource();
		problemFundSourceDAO.save(problemFundSource);
	}*/
	
	public void testGetAll(){
		List<ProblemFundSource> list = problemFundSourceDAO.getAll();
		assertEquals(1, list.size());
	}
}
