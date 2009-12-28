package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.model.ProblemHistory;

public class ProblemHistroryDAOHibernateTest extends BaseDaoTestCase {

	private IProblemHistoryDAO problemHistoryDAO;

	public IProblemHistoryDAO getProblemHistoryDAO() {
		return problemHistoryDAO;
	}

	public void setProblemHistoryDAO(IProblemHistoryDAO problemHistoryDAO) {
		this.problemHistoryDAO = problemHistoryDAO;
	}
	
	public void testSave(){
		ProblemHistory obj = new ProblemHistory();
		problemHistoryDAO.save(obj);
		setComplete();
	}
	
	public void testGetAll(){
		List<ProblemHistory> list = problemHistoryDAO.getAll();
		assertEquals(1, list.size());
	}
	
	
}
