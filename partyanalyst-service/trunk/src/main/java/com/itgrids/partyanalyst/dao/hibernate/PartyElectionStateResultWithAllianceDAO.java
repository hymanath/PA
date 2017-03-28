package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyElectionStateResultWithAllianceDAO;
import com.itgrids.partyanalyst.model.PartyElectionStateResultWithAlliance;

public class PartyElectionStateResultWithAllianceDAO extends
		GenericDaoHibernate<PartyElectionStateResultWithAlliance, Long> implements
		IPartyElectionStateResultWithAllianceDAO {

	public PartyElectionStateResultWithAllianceDAO() {
		super(PartyElectionStateResultWithAlliance.class);
	}

	@SuppressWarnings("unchecked")
	public List<PartyElectionStateResultWithAlliance> getPartyResultsByStateIdAndElectionId(Long electionId,Long partyId,Long stateId){
		Object[] parms = {electionId,partyId,stateId};
		return getHibernateTemplate().find("select model.state.stateId,model.totalConstiParticipated,model.totalValidVotes," +
				" model.votesPercentage,model.completeVotesPercent,model.totalSeatsWon from PartyElectionStateResultWithAlliance model where model.election.electionId = ? and" +
				" model.party.partyId = ? and model.state.stateId =? order by model.state.stateId",parms);
		
	}

	public List findStatewiseResultsForPartyElectionAndCountry(Long partyId,
			Long countryId, Long electionId) {
		Object[] params = {electionId, partyId, countryId};
		return getHibernateTemplate().find("select model.state.stateId, model.state.stateName, model.totalConstiParticipated, " +
				"model.totalSeatsWon, model.votesPercentage, model.completeVotesPercent from PartyElectionStateResultWithAlliance model where " +
				"model.election.electionId = ? and model.party.partyId = ? and model.state.country.countryId = ?",params);
	}
}
