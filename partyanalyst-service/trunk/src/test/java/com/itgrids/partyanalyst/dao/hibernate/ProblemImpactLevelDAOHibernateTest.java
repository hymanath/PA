package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IProblemImpactLevelDAO;
import com.itgrids.partyanalyst.model.ProblemImpactLevel;

public class ProblemImpactLevelDAOHibernateTest extends BaseDaoTestCase {

	private IProblemImpactLevelDAO problemImpactLevelDAO;

	public IProblemImpactLevelDAO getProblemImpactLevelDAO() {
		return problemImpactLevelDAO;
	}

	public void setProblemImpactLevelDAO(
			IProblemImpactLevelDAO problemImpactLevelDAO) {
		this.problemImpactLevelDAO = problemImpactLevelDAO;
	}
	
	@Test
	public void testGetImpactProblems(){
		List<ProblemImpactLevel> values = problemImpactLevelDAO.getAll();
		
	}
}
