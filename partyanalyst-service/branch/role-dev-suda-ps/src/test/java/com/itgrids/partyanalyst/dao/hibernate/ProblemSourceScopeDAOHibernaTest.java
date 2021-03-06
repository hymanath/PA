package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemSourceScopeDAO;
import com.itgrids.partyanalyst.model.ProblemSourceScope;
import com.itgrids.partyanalyst.model.UserCategory;

public class ProblemSourceScopeDAOHibernaTest extends BaseDaoTestCase {

	private IProblemSourceScopeDAO problemSourceScopeDAO;
	
	public IProblemSourceScopeDAO getProblemSourceScopeDAO() {
		return problemSourceScopeDAO;
	}

	public void setProblemSourceScopeDAO(
			IProblemSourceScopeDAO problemSourceScopeDAO) {
		this.problemSourceScopeDAO = problemSourceScopeDAO;
	}

	public void testSave(){
		UserCategory userCategory = new UserCategory(1L);
		ProblemSourceScope problemSourceScope = new ProblemSourceScope("Country", userCategory, null);
		problemSourceScopeDAO.save(problemSourceScope);
		setComplete();
	}
	
	
}
