package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemSourceScopeConcernedDepartmentDAO;
import com.itgrids.partyanalyst.model.ProblemSourceScopeConcernedDepartment;

public class ProblemSourceScopeConcernedDepartmentDAO extends GenericDaoHibernate<ProblemSourceScopeConcernedDepartment, Long> implements IProblemSourceScopeConcernedDepartmentDAO{

	public ProblemSourceScopeConcernedDepartmentDAO(){
		super(ProblemSourceScopeConcernedDepartment.class);
	}
}
