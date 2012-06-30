package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IProblemAssignedDepartmentDAO;
import com.itgrids.partyanalyst.model.ProblemAssignedDepartment;

public class ProblemAssignedDepartmentDAO extends GenericDaoHibernate<ProblemAssignedDepartment,Long>
															implements IProblemAssignedDepartmentDAO
{
	public ProblemAssignedDepartmentDAO()
	{
		super(ProblemAssignedDepartment.class);
	}
}
