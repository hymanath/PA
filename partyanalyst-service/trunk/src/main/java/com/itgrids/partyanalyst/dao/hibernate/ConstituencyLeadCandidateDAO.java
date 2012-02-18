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
	
	@SuppressWarnings("unchecked")
	public List getCountOfOldConstituenciesInAElection(Long electionId){
		
		return getHibernateTemplate().find("select count(model.constituencyElection.constituency.constituencyId) from ConstituencyLeadCandidate model " +
				" where model.constituencyElection.constiElecId in (select model1.constiElecId from ConstituencyElection model1" +
				" where model1.election.electionId =? and model1.constituency.startDate is null and " +
				" model1.constituency.deformDate is null)",electionId );
	}
	
	
	@SuppressWarnings("unchecked")
	public List getCountOfDelimitedConstituenciesInAElection(Long electionId){
		
		return getHibernateTemplate().find("select count(model.constituencyElection.constituency.constituencyId) from ConstituencyLeadCandidate model " +
				" where model.constituencyElection.constiElecId in (select model1.constiElecId from ConstituencyElection model1" +
				" where model1.election.electionId =? and model1.constituency.startDate is not null and " +
				" model1.constituency.deformDate is null)",electionId );
	}
	
	@SuppressWarnings("unchecked")
	public List getLeadingConstituenciesCount(Long electionId){
		
		 return getHibernateTemplate().find("select count(model.constituencyElection.constituency.constituencyId) from ConstituencyLeadCandidate model where " +
		 		" model.constituencyElection.constiElecId in (select model1.constiElecId from ConstituencyElection model1 " +
		 		" where model1.election.electionId = ?)",electionId);
	}

/*	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyLeadingOrWinningConstituencies(Long electionId) {
		Object[] params = {electionId};
		return getHibernateTemplate().find("select " +
				" count(model.constituencyElection.constituency.constituencyId)," +
				" model.party.shortName ,model1.status from" +
				" Nomination model ,ConstituencyLeadCandidate model1 where" +
				" model.constituencyElection.constiElecId = model1.constituencyElection.constiElecId and " +
				" model.constituencyElection.constiElecId in " +
				"(select model1.constituencyElection.constiElecId from ConstituencyLeadCandidate model1 where " +
				" model1.constituencyElection.election.electionId = ? ) group by model.party.shortName,model1.status " ,params);
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyLeadingOrWinningConstituencies(Long electionId)
	{
		return getHibernateTemplate().find("select model.party.shortName,model2.constituencyElection.constituency.startDate,model2.status,count(model2.status) from " +
				" Nomination model, ConstituencyLeadCandidate model2 where model.constituencyElection.constiElecId = model2.constituencyElection.constiElecId and " +
				" model2.constituencyElection.election.electionId = ? and model.candidate.candidateId = model2.candidate.candidateId group by model.party.partyId,model2.constituencyElection.constituency.startDate,model2.status",electionId);
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
	
	public List getElectionIds(Long electionId){
		return getHibernateTemplate().find("Select model.election.electionId from ConstituencyElection model " +
				"where model.constituency.deformDate ='2009-01-03' and model.election.electionId =?",electionId);
	}
}
