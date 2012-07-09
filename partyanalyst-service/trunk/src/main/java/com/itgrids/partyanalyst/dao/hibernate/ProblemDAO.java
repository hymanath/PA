package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IProblemDAO;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemStatus;

public class ProblemDAO extends GenericDaoHibernate<Problem,Long> implements IProblemDAO{

	public ProblemDAO()
	{
		super(Problem.class);
	}	
}
