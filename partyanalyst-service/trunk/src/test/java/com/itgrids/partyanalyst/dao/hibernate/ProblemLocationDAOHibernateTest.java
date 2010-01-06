package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemLocation;
import com.itgrids.partyanalyst.model.Township;

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
	
	public void testFindByHamletId(){
		List<ProblemLocation> list = problemLocationDAO.findByHamletId(new Long(20));
		assertEquals(1, list.size());
	}
}
