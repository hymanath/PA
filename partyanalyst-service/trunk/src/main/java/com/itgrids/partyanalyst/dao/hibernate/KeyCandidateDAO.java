package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IKeyCandidateDAO;
import com.itgrids.partyanalyst.model.KeyCandidate;
import org.hibernate.Query;

public class KeyCandidateDAO extends GenericDaoHibernate<KeyCandidate, Long> implements IKeyCandidateDAO{

	public KeyCandidateDAO(){
		super(KeyCandidate.class);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidatesBasedOnPartyId(String candidateName,Long stateId , Long partyId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct model.candidate.candidateId,model.candidate.lastname from Nomination model"
				+" where model.constituencyElection.constituency.state.stateId =:stateId and model.party.partyId=:partyId" +
				" and model.candidate.lastname like :candidateName");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyId", partyId);
		query.setParameter("stateId",stateId);
		query.setParameter("candidateName","%"+ candidateName+"%");
		return query.list();
		
	}
	
	public Object getCountCandidate(Long candidateId)
	{
		Query query = getSession().createQuery("select count(model.candidate.candidateId) from KeyCandidate model " +
				" where model.candidate.candidateId = ?");
		query.setParameter(0,candidateId);
		return query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<KeyCandidate> getCandidateById(Long candidateId)
	{
		Query query = getSession().createQuery("select model from KeyCandidate model where model.candidate.candidateId = ?");
		query.setParameter(0,candidateId);
		return query.list();
	}
	
	
	public List<Object[]> getImportantCandidatesInfoByElectionId(Long electionId){
		
		
		Query query = getSession().createQuery("select model1.candidate.candidateId ,model1.candidate.lastname ," +
				" model1.description,model2.constituencyElection.constituency.name ,model2.status " +
				"from KeyCandidate model1 , ConstituencyLeadCandidate model2 where " +
				"model1.candidate.candidateId = model2.candidate.candidateId  " +
				"and model2.constituencyElection.election.electionId = ?");
		
		
		query.setParameter(0, electionId);
		
		return query.list();
		
	}
}
