package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemAndProblemSourceDAO;
import com.itgrids.partyanalyst.model.ProblemAndProblemSource;

public class ProblemAndProblemSourceDAO extends GenericDaoHibernate<ProblemAndProblemSource, Long>
implements IProblemAndProblemSourceDAO{
	
	public ProblemAndProblemSourceDAO(){
		super(ProblemAndProblemSource.class);
	}

}
