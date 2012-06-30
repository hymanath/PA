package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IProblemCommentsDAO;
import com.itgrids.partyanalyst.model.ProblemComments;

public class ProblemCommentsDAO extends GenericDaoHibernate<ProblemComments,Long> implements IProblemCommentsDAO{

	public ProblemCommentsDAO()
	{
		super(ProblemComments.class);
	}
}
