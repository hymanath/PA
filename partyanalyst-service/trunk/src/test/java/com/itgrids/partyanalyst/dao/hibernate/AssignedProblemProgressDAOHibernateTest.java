package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAssignedProblemProgressDAO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;

public class AssignedProblemProgressDAOHibernateTest extends BaseDaoTestCase{

	private IAssignedProblemProgressDAO assignedProblemProgressDAO;

	public IAssignedProblemProgressDAO getAssignedProblemProgressDAO() {
		return assignedProblemProgressDAO;
	}

	public void setAssignedProblemProgressDAO(
			IAssignedProblemProgressDAO assignedProblemProgressDAO) {
		this.assignedProblemProgressDAO = assignedProblemProgressDAO;
	}
	
	public void testGetAll(){
		List<AssignedProblemProgress> list = assignedProblemProgressDAO.getAll();
		assertEquals(1, list.size());
	}	
		
	public void testByLocation(){
		List result = assignedProblemProgressDAO.findProblemsForAHamletByHistoryId(new Long(1));
		assertEquals(1, result.size());
	}
}
