package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IConstituencyLeadCandidateDAO;
import com.itgrids.partyanalyst.model.ConstituencyLeadCandidate;
import com.itgrids.partyanalyst.utils.IConstants;

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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartiesLeadingInfo(Long electionId)
	{
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName,model2.constituencyElection.constituency.constituencyId," +
				" model2.constituencyElection.constituency.name, model2.constituencyElection.constituency.startDate,model2.status from " +
				" Nomination model, ConstituencyLeadCandidate model2 where model.constituencyElection.constiElecId = model2.constituencyElection.constiElecId and " +
				" model2.constituencyElection.election.electionId = ? and model.candidate.candidateId = model2.candidate.candidateId order by model.party.shortName,model2.constituencyElection.constituency.name",electionId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartiesinfoInSpecifiedConstituencies(Long electionId,List<Long> constituenciesList)
	{
		Query query = getSession().createQuery("select model.party.partyId,model.party.shortName,model2.constituencyElection.constituency.constituencyId," +
				" model2.constituencyElection.constituency.name, model2.constituencyElection.constituency.startDate,model2.status from " +
				" Nomination model, ConstituencyLeadCandidate model2 where model.constituencyElection.constiElecId = model2.constituencyElection.constiElecId and " +
				" model2.constituencyElection.election.electionId = ? and model.candidate.candidateId = model2.candidate.candidateId and " +
				" model2.constituencyElection.constituency.constituencyId in (:constituenciesList) order by model.party.shortName,model2.constituencyElection.constituency.name");
		
		query.setParameter(0,electionId);
		query.setParameterList("constituenciesList",constituenciesList);
		
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartiesWonCountInSpecifiedConstituencies(Long electionId,List<Long> constituenciesList)
	{
		Query query = getSession().createQuery("select model.party.shortName,count(model.party.partyId) from Nomination model, ConstituencyLeadCandidate model2 " +
				" where model.constituencyElection.constiElecId = model2.constituencyElection.constiElecId and model2.constituencyElection.election.electionId = ? and " +
				" model.candidate.candidateId = model2.candidate.candidateId and model2.constituencyElection.constituency.constituencyId in (:constituenciesList) " +
				" group by model.party.partyId order by model.party.shortName");
		
		query.setParameter(0,electionId);
		query.setParameterList("constituenciesList",constituenciesList);
		
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyWinConstituencies(Long partyId,Long electionId,List<Long> constituenciesList)
	{
		Query query = getSession().createQuery("select model.constituencyElection.constituency.constituencyId,model.constituencyElection.constituency.name from Nomination model, ConstituencyLeadCandidate model2 " +
				" where model.constituencyElection.constiElecId = model2.constituencyElection.constiElecId and model2.constituencyElection.election.electionId = ? and " +
				" model.candidate.candidateId = model2.candidate.candidateId and model.party.partyId = ? and model2.constituencyElection.constituency.constituencyId in (:constituenciesList)");
		
		query.setParameter(0,electionId);
		query.setParameter(1,partyId);
		query.setParameterList("constituenciesList",constituenciesList);
		
		return query.list();
	}
		
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidateResultsForPartialElec(Long constituencyId,Long electionId)
	{
		Object[] data = {constituencyId,electionId};
		return getHibernateTemplate().find("select model.candidate.candidateId,model.status from ConstituencyLeadCandidate model where  model.constituencyElection.constiElecId in " +
				" (select model1.constituencyElection.constiElecId from Nomination model1  where model1.constituencyElection.constituency.constituencyId = ? and model1.constituencyElection.election.electionId = ?)",data);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyWiseCandidatesStates(Long electionId)
	{
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId,model.constituencyElection.constituency.name,model.constituencyElection.constituency.district.districtId," +
				" model.constituencyElection.constituency.district.districtName,model.candidate.candidateId, model.candidate.lastname,model.party.partyId,model.party.shortName,model2.status " +
				" from Nomination model,ConstituencyLeadCandidate model2 where model.constituencyElection.election.electionId = ? and model.constituencyElection.constiElecId = model2.constituencyElection.constiElecId and " +
				" model.candidate.candidateId = model2.candidate.candidateId order by model.constituencyElection.constituency.name",electionId);
	}
	
	public Object getResultKnownConstituenciesCountInAElection(Long electionId)
	{
		Query query = getSession().createQuery("select count(model.constituencyElection.constituency.constituencyId) from ConstituencyLeadCandidate model " +
				" where model.constituencyElection.election.electionId = ?");
		query.setParameter(0,electionId);
		return query.uniqueResult();
	}
		
	public List<Long> getTotalResultsKnown(Long electionId,List<Long> constituenciesList)
	{
		Object[] data = {constituenciesList,electionId};
		Query query = getSession().createQuery("select count(*) from ConstituencyLeadCandidate model where  model.constituencyElection.constituency.constituencyId in (:constituenciesList) and model.constituencyElection.election.electionId =:electionId and model.status =:status ");
		
		query.setParameterList("constituenciesList",constituenciesList);
		query.setLong("electionId",electionId);
		query.setString("status", IConstants.WON);
		
		return query.list();
	}
	public List<Object[]> getPartyWinConst(Long partyId,Long electionId,List<Long> constituenciesList)
	{
		Query query = getSession().createQuery("select model.constituencyElection.constituency.constituencyId,model.constituencyElection.constituency.name from Nomination model, ConstituencyLeadCandidate model2 " +
				" where model.constituencyElection.constiElecId = model2.constituencyElection.constiElecId and model2.constituencyElection.election.electionId = ? and " +
				" model.candidate.candidateId = model2.candidate.candidateId and model.party.partyId = ? and model2.constituencyElection.constituency.constituencyId in (:constituenciesList) and model2.status =:status ");
		
		query.setParameter(0,electionId);
		query.setParameter(1,partyId);
		query.setParameterList("constituenciesList",constituenciesList);
		query.setString("status", IConstants.WON);
		
		return query.list();
	}
}
