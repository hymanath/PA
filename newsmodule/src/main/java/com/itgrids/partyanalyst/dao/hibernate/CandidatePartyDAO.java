package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidatePartyDAO;
import com.itgrids.partyanalyst.model.CandidateParty;

public class CandidatePartyDAO extends GenericDaoHibernate<CandidateParty, Long> implements ICandidatePartyDAO {
	
	

	public CandidatePartyDAO() {
		super(CandidateParty.class);
	}
	
	
	public List<Object[]> getCandidatesOfAParty(Long partyId)
	{
		Query query = getSession().createQuery("select distinct( model.candidate.candidateId ), model.candidate.lastname " +
				"from CandidateParty model where model.party.partyId = :partyId");
		
		query.setParameter("partyId", partyId);
		
		return query.list();
		
	}
}
