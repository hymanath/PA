package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.model.ProblemLocation;

public class ProblemLocationDAO extends GenericDaoHibernate<ProblemLocation, Long> implements IProblemLocationDAO{

	public ProblemLocationDAO(){
		super(ProblemLocation.class);
	}
}
