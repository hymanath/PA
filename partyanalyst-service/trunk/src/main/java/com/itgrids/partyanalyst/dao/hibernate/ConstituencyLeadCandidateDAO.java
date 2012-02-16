package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IConstituencyLeadCandidateDAO;
import com.itgrids.partyanalyst.model.ConstituencyLeadCandidate;

public class ConstituencyLeadCandidateDAO  extends GenericDaoHibernate<ConstituencyLeadCandidate, Long> implements IConstituencyLeadCandidateDAO{
	public ConstituencyLeadCandidateDAO() {
		super(ConstituencyLeadCandidate.class);
	}
	public List<Object> getCandidateResultStatus(Long candidateId,Long constiElecId)
	{
		Query query = getSession().createQuery("Select model.status from ConstituencyLeadCandidate model where model.constituencyElection.constiElecId =:constiElecId " +
				" and model.candidate.candidateId =:candidateId ");
		query.setLong("constiElecId", constiElecId);
		query.setLong("candidateId", candidateId);
        
       return query.list();
	}
	public List<Object> checkCandidateResultExist(Long constiElecId)
	{
		Query query = getSession().createQuery("Select model.constituencyLeadCandidateId from ConstituencyLeadCandidate model where model.constituencyElection.constiElecId =:constiElecId ");
		query.setLong("constiElecId", constiElecId);
        
       return query.list();
	}
}
