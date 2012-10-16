package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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

	/*public void testSave(){
		UserCategory userCategory = new UserCategory(1L);
		ProblemSourceScope problemSourceScope = new ProblemSourceScope("Country", userCategory, null);
		problemSourceScopeDAO.save(problemSourceScope);
		setComplete();
	}*/
	
	/*public void testGetAll(){
		List<ProblemSourceScope> list = problemSourceScopeDAO.getAll();
		assertEquals(1, list.size());
	}*/
	
	/*public void testFindBySourceScope(){
		List<ProblemSourceScope> list = problemSourceScopeDAO.findBySourceScope("Constituency");
		assertEquals(1, list.size());
	}*/
	public void testGetStatesForDepartment(){
		List<Object[]> list = problemSourceScopeDAO.getStatesForDepartment();
		System.out.println(list.size());
		for(Object[] state:list){
			System.out.println(state[0].toString()+","+state[1].toString());
		}
	}
	
}
