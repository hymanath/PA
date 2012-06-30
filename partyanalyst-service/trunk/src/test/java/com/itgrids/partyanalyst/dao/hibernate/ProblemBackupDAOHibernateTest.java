package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemBackupDAO;
import com.itgrids.partyanalyst.model.ProblemBackup;

public class ProblemBackupDAOHibernateTest extends BaseDaoTestCase{

	private IProblemBackupDAO problemDAO;
	private Long problemId;
	
	public IProblemBackupDAO getProblemDAO() {
		return problemDAO;
	}

	public void setProblemDAO(IProblemBackupDAO problemDAO) {
		this.problemDAO = problemDAO;
	}
	
	/*public void testSave(){
		Date date = Calendar.getInstance().getTime();
		Problem problem = new Problem("Living Problem", "Heivy", new java.util.Date(), null);
		problemId = problemDAO.save(problem).getProblemId();
		setComplete();
	}*/
	
	public void testGetAll(){
		List<ProblemBackup> list = problemDAO.getAll();
		assertEquals(1, list.size());
	}
}
