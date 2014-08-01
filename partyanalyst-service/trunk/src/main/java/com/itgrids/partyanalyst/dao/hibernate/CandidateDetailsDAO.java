package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateDetailsDAO;
import com.itgrids.partyanalyst.model.CandidateDetails;

public class CandidateDetailsDAO extends GenericDaoHibernate<CandidateDetails, Long> implements ICandidateDetailsDAO
{
	public CandidateDetailsDAO()
	{
		super(CandidateDetails.class);
	}
	
	
}
