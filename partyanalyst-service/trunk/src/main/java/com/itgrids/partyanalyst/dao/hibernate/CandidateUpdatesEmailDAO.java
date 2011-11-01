package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICandidateUpdatesEmailDAO;
import com.itgrids.partyanalyst.model.CandidateUpdatesEmail;

public class CandidateUpdatesEmailDAO extends GenericDaoHibernate<CandidateUpdatesEmail,Long> implements ICandidateUpdatesEmailDAO{

	public CandidateUpdatesEmailDAO()
	{
		super(CandidateUpdatesEmail.class);
	}
}
