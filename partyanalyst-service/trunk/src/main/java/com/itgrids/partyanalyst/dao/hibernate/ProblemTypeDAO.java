package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemTypeDAO;
import com.itgrids.partyanalyst.model.ProblemType;

public class ProblemTypeDAO extends GenericDaoHibernate<ProblemType, Long> implements IProblemTypeDAO{

	public ProblemTypeDAO() {
		super(ProblemType.class);
	}

	
}
