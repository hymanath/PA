package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemSourceScopeDAO;
import com.itgrids.partyanalyst.model.ProblemSourceScope;

public class ProblemSourceScopeDAO extends GenericDaoHibernate<ProblemSourceScope, Long> implements IProblemSourceScopeDAO{

	public ProblemSourceScopeDAO(){
		super(ProblemSourceScope.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemSourceScope> findByUserCategory(Long userCategoryId){
		return getHibernateTemplate().find("from ProblemSourceScope model where model.userCategory.userCatagoryId = ?",userCategoryId);
	}

	@SuppressWarnings("unchecked")
	public List<ProblemSourceScope> findBySourceScope(String problemSourceScope) {
		return getHibernateTemplate().find("from ProblemSourceScope model where model.scope = ?",problemSourceScope);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemSourceScope> findByStateId(Long stateId){
		return getHibernateTemplate().find("from ProblemSourceScope model where model.state.stateId = ?",stateId);
	}
}
