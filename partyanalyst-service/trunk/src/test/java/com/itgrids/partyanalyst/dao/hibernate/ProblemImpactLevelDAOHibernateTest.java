package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
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
		if(values != null)
		Assert.assertEquals(7, values.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetProblemImpactLevelByName(){
		List details = problemImpactLevelDAO.getProblemImpactLevelByName("CONSTITUENCY");
		if(details != null){
			Object[] values = (Object[])details.get(0);
			System.out.println("  Impact Level :" + (String)values[1]);
			Assert.assertEquals("CONSTITUENCY", (String)values[1]);
		}
	}
}
