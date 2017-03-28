package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemSourceScopeConcernedDepartmentDAO;
import com.itgrids.partyanalyst.model.ProblemSourceScopeConcernedDepartment;

public class ProblemSourceScopeConcernedDepartmentDAO extends GenericDaoHibernate<ProblemSourceScopeConcernedDepartment, Long> implements IProblemSourceScopeConcernedDepartmentDAO{

	public ProblemSourceScopeConcernedDepartmentDAO(){
		super(ProblemSourceScopeConcernedDepartment.class);
	}

	@SuppressWarnings("unchecked")
	public List<ProblemSourceScopeConcernedDepartment> findByProblemScopeId(Long problemSourceScopeId) {
		return getHibernateTemplate().find("from ProblemSourceScopeConcernedDepartment model where model.problemSourceScope.problemSourceScopeId = ?", problemSourceScopeId);
	}

	@SuppressWarnings("unchecked")
	public List<ProblemSourceScopeConcernedDepartment> findByDepartmentAndScope(
			String department, String problemScope) {
		Object[]params = {department, problemScope};
		return getHibernateTemplate().find("from ProblemSourceScopeConcernedDepartment model where model.department = ? " +
				"and model.problemSourceScope.scope = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List<ProblemSourceScopeConcernedDepartment> findDepartmentsByScope(String problemScope) {
		
		return getHibernateTemplate().find("from ProblemSourceScopeConcernedDepartment model where model.problemSourceScope.scope = ? order by model.department", problemScope);
	}
	
}
