package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IProblemProgressDAO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.ProblemProgress;

public class ProblemProgressDAO extends GenericDaoHibernate<ProblemProgress,Long> implements IProblemProgressDAO{

	public ProblemProgressDAO()
	{
		super(ProblemProgress.class);
	}
	
}
