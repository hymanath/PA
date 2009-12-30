package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemFundSourceDAO;
import com.itgrids.partyanalyst.model.ProblemFundSource;

public class ProblemFundSourceDAO extends GenericDaoHibernate<ProblemFundSource, Long> implements IProblemFundSourceDAO{

	public ProblemFundSourceDAO(){
		super(ProblemFundSource.class);
	}
}
