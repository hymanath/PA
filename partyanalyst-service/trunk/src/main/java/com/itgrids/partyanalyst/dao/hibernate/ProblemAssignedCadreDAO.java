package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IProblemAssignedCadreDAO;
import com.itgrids.partyanalyst.model.ProblemAssignedCadre;

public class ProblemAssignedCadreDAO extends GenericDaoHibernate<ProblemAssignedCadre,Long> implements IProblemAssignedCadreDAO
{
	public ProblemAssignedCadreDAO()
	{
		super(ProblemAssignedCadre.class);
	}
}
