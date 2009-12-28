package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAssignedProblemProgressDAO;

public class AssignedProblemProgressDAOHibernateTest extends BaseDaoTestCase{

	private IAssignedProblemProgressDAO assignedProblemProgressDAO;

	public IAssignedProblemProgressDAO getAssignedProblemProgressDAO() {
		return assignedProblemProgressDAO;
	}

	public void setAssignedProblemProgressDAO(
			IAssignedProblemProgressDAO assignedProblemProgressDAO) {
		this.assignedProblemProgressDAO = assignedProblemProgressDAO;
	}
	
	
}
