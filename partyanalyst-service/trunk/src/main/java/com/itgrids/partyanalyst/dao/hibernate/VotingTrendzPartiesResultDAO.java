package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVotingTrendzPartiesResultDAO;
import com.itgrids.partyanalyst.model.VotingTrendzPartiesResult;

public class VotingTrendzPartiesResultDAO extends GenericDaoHibernate<VotingTrendzPartiesResult,Long> implements IVotingTrendzPartiesResultDAO{

	private VotingTrendzPartiesResultDAO(){
		super(VotingTrendzPartiesResult.class);
	}
}
