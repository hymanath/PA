package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemSourceScopeConcernedDepartment;

public interface IProblemSourceScopeConcernedDepartmentDAO extends GenericDao<ProblemSourceScopeConcernedDepartment, Long>{

	public List<ProblemSourceScopeConcernedDepartment> findByProblemScopeId(Long problemSourceScopeId);

	public List<ProblemSourceScopeConcernedDepartment> findByDepartmentAndScope(String department, String problemScope);
	
	public List<ProblemSourceScopeConcernedDepartment> findDepartmentsByScope(String problemScope);

}
