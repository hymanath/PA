package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemDAO;
import com.itgrids.partyanalyst.model.Problem;

public class ProblemDAOHibernateTest extends BaseDaoTestCase{

	private IProblemDAO problemDAO;
	private Long problemId;
	
	public IProblemDAO getProblemDAO() {
		return problemDAO;
	}

	public void setProblemDAO(IProblemDAO problemDAO) {
		this.problemDAO = problemDAO;
	}
	
	/*public void testSave(){
		Date date = Calendar.getInstance().getTime();
		Problem problem = new Problem("Living Problem", "Heivy", new java.util.Date(), null);
		problemId = problemDAO.save(problem).getProblemId();
		setComplete();
	}*/
	
	public void testGetAll(){
		List<Problem> list = problemDAO.getAll();
		assertEquals(1, list.size());
	}
}
