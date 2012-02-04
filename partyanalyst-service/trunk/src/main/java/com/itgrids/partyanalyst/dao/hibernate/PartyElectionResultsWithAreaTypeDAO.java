package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyElectionResultsWithAreaTypeDAO;
import com.itgrids.partyanalyst.model.PartyElectionResultsWithAreaType;

public class PartyElectionResultsWithAreaTypeDAO extends GenericDaoHibernate<PartyElectionResultsWithAreaType, Long> implements IPartyElectionResultsWithAreaTypeDAO{

	public PartyElectionResultsWithAreaTypeDAO()
	{
		super(PartyElectionResultsWithAreaType.class);
	}
	
	public Object getCountOfPartiesInAElection(Long electionId,List<Long>partiesList)
	{
		Query query = getSession().createQuery("select count(model.party.partyId) from PartyElectionResultsWithAreaType model where " +
				" model.election.electionId = ? and model.party.partyId in(:partiesList)");
		query.setParameter(0, electionId);
		query.setParameterList("partiesList", partiesList);
		return query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartiesElectionResultInConstituencyAreaTypes(Long electionId,List<Long>partiesList)
	{
		Query query = getSession().createQuery("select model.party.partyId,model.ruralParticipated,model.ruralWon,model.ruralVotesGainedPercentage,model.urbanParticipated," +
				" model.urbanWon,model.urbanVotesGainedPercentage,model.ruralUrbanParticipated,model.ruralUrbanWon,model.ruralUrbanVotesGainedPercentage from PartyElectionResultsWithAreaType model " +
				" where model.election.electionId = ? and model.party.partyId in(:partiesList)");
		query.setParameter(0, electionId);
		query.setParameterList("partiesList", partiesList);
		return query.list();
	}
}
