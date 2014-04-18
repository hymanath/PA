package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterNamesDAO;
import com.itgrids.partyanalyst.model.VoterNames;

public class VoterNamesDAO extends GenericDaoHibernate<VoterNames, Long> implements IVoterNamesDAO{

	public VoterNamesDAO() {
		super(VoterNames.class);
	}
	
	public VoterNames gerVoterNamesObjByVoterId(Long voterId)
	{
		Query query = getSession().createQuery("Select model from VoterNames model where model.voter.voterId = :voterId");
		query.setParameter("voterId",voterId);
		return (VoterNames) query.uniqueResult();
	}
	
	
}
