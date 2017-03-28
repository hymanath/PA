package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemCompleteLocationDAO;
import com.itgrids.partyanalyst.model.ProblemCompleteLocation;

public class ProblemCompleteLocationDAO extends	GenericDaoHibernate<ProblemCompleteLocation, Long> implements
	IProblemCompleteLocationDAO {

	public ProblemCompleteLocationDAO() {
		super(ProblemCompleteLocation.class);		
	}

	
	
}
