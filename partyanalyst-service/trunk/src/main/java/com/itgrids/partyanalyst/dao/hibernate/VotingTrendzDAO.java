package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVotingTrendzDAO;
import com.itgrids.partyanalyst.model.VotingTrendz;

public class VotingTrendzDAO extends GenericDaoHibernate<VotingTrendz,Long> implements IVotingTrendzDAO{

	public VotingTrendzDAO(){
		super(VotingTrendz.class);
	}
}
