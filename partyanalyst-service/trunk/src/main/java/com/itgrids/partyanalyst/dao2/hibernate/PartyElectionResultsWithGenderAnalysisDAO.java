package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyElectionResultsWithGenderAnalysisDAO;
import com.itgrids.partyanalyst.model.PartyElectionResultsWithGenderAnalysis;

public class PartyElectionResultsWithGenderAnalysisDAO extends GenericDaoHibernate<PartyElectionResultsWithGenderAnalysis,Long>
			implements IPartyElectionResultsWithGenderAnalysisDAO{

	public PartyElectionResultsWithGenderAnalysisDAO()
	{
		super(PartyElectionResultsWithGenderAnalysis.class);
	}
	
	public Object getCountOfPartiesInAElection(Long electionId,List<Long>partiesList)
	{
		Query query = getSession().createQuery("select count(model.party.partyId) from PartyElectionResultsWithGenderAnalysis model where " +
				" model.election.electionId = ? and model.party.partyId in(:partiesList)");
		query.setParameter(0, electionId);
		query.setParameterList("partiesList", partiesList);
		return query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyWiseGenderDetails(Long electionId,List<Long>partiesList)
	{
		Query query = getSession().createQuery("select model.party.partyId,model.maleParticipated,model.maleWon,model.maleCandidateVotesGainedPercetage,model.femaleParticipated," +
				"model.femaleWon,model.femaleCandidateVotesGainedPercetage from PartyElectionResultsWithGenderAnalysis model where model.election.electionId = ? " +
				" and model.party.partyId in(:partiesList)");
		query.setParameter(0, electionId);
		query.setParameterList("partiesList", partiesList);
		return query.list();
	}
}
