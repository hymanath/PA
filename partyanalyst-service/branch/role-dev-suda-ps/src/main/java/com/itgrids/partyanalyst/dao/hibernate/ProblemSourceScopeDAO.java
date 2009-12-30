package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemSourceScopeDAO;
import com.itgrids.partyanalyst.model.ProblemSourceScope;

public class ProblemSourceScopeDAO extends GenericDaoHibernate<ProblemSourceScope, Long> implements IProblemSourceScopeDAO{

	public ProblemSourceScopeDAO(){
		super(ProblemSourceScope.class);
	}
}
