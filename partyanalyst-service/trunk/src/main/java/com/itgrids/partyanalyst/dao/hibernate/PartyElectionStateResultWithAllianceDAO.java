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
	public List<PartyElectionStateResultWithAlliance> getPartyResultsByStateIdAndElectionId(Long electionId,Long partyId){
		Object[] parms = {electionId,partyId};
		return getHibernateTemplate().find("select model.state.stateId,model.totalConstiParticipated,model.totalValidVotes," +
				" model.votesPercentage,model.completeVotesPercent from PartyElectionStateResultWithAlliance model where model.election.electionId = ? and" +
				" model.party.partyId = ? order by model.state.stateId",parms);
		
	}
}
