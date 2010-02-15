package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemClassificationDAO;
import com.itgrids.partyanalyst.model.ProblemClassification;

public class ProblemClassificationDAOHibernateTest extends BaseDaoTestCase{

	private IProblemClassificationDAO problemClassificationDAO;

	public IProblemClassificationDAO getProblemClassificationDAO() {
		return problemClassificationDAO;
	}

	public void setProblemClassificationDAO(
			IProblemClassificationDAO problemClassificationDAO) {
		this.problemClassificationDAO = problemClassificationDAO;
	}
	
	/*public void testGetAll(){
		List<ProblemClassification> list = problemClassificationDAO.getAll();
		assertEquals(1, list.size());
	}*/

	public void testFindByClassification(){
		List<ProblemClassification> list = problemClassificationDAO.findByClassification("SOCIAL");
		assertEquals(1, list.size());
	}
}
