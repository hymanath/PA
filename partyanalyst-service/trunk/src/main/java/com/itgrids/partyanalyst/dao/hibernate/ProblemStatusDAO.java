package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemStatusDAO;
import com.itgrids.partyanalyst.model.ProblemStatus;

public class ProblemStatusDAO extends GenericDaoHibernate<ProblemStatus, Long> implements IProblemStatusDAO{

	public ProblemStatusDAO(){
		super(ProblemStatus.class);
	}
	
}
