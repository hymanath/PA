package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemLikesDAO;
import com.itgrids.partyanalyst.model.ProblemLikes;

public class ProblemLikesDAO extends GenericDaoHibernate<ProblemLikes,Long> implements IProblemLikesDAO{

	public ProblemLikesDAO()
	{
		super(ProblemLikes.class);
	}
}
