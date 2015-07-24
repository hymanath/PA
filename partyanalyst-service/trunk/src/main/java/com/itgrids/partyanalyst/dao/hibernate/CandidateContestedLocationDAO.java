package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICandidateContestedLocationDAO;
import com.itgrids.partyanalyst.model.CandidateContestedLocation;

public class CandidateContestedLocationDAO extends GenericDaoHibernate<CandidateContestedLocation, Long> implements ICandidateContestedLocationDAO{

	public CandidateContestedLocationDAO() {
		super(CandidateContestedLocation.class);
		
	}

}
