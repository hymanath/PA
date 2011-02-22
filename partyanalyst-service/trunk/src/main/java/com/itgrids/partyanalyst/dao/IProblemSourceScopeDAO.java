package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemSourceScope;

public interface IProblemSourceScopeDAO extends GenericDao<ProblemSourceScope, Long>{

	public List<ProblemSourceScope> findByUserCategory(Long userCategoryId);

	public List<ProblemSourceScope> findBySourceScope(String problemSourceScope);
	
	public List<ProblemSourceScope> findByStateId(Long stateId);
	
}
