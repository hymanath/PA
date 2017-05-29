package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtProposalStatusDAO;
import com.itgrids.partyanalyst.model.GovtProposalStatus;

public class GovtProposalStatusDAO extends GenericDaoHibernate<GovtProposalStatus, Long> implements IGovtProposalStatusDAO{

	public GovtProposalStatusDAO() {
		super(GovtProposalStatus.class);
	}

}
