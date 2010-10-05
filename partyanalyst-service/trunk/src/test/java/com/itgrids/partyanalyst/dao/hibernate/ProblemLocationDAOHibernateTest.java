package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.model.ProblemLocation;

public class ProblemLocationDAOHibernateTest extends BaseDaoTestCase{

	private IProblemLocationDAO problemLocationDAO;

	public IProblemLocationDAO getProblemLocationDAO() {
		return problemLocationDAO;
	}

	public void setProblemLocationDAO(IProblemLocationDAO problemLocationDAO) {
		this.problemLocationDAO = problemLocationDAO;
	}
	
	/*public void testSave(){
		Problem problem = new Problem(new Long(1));
		Township township = new Township(new Long(1));
		ProblemLocation problemLocation = new ProblemLocation();
		problemLocationDAO.save(problemLocation);
		setComplete();
	}*/
	
	public void testGetAll(){
		List<ProblemLocation> list = problemLocationDAO.getAll();
		assertEquals(1, list.size());
	}
	
	/*public void testFindByHamletId(){
		List<ProblemLocation> list = problemLocationDAO.findByHamletId(new Long(20));
		assertEquals(1, list.size());
	}*/
	
	public void testFindProblemsByUserId(){
		List<ProblemLocation> list = problemLocationDAO.findProblemsByUserId(new Long(1));
		assertEquals(1, list.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetProblemsList(){
		
		List<Long> list = new ArrayList<Long>();
		list.add(232L);
	
		List list1 = problemLocationDAO.findByLevel(3L, list);
		System.out.println(" Size :" + list1.size());
	}
}
