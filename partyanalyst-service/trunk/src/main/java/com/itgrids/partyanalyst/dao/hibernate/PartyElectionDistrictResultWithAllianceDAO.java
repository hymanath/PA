package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultWithAllianceDAO;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResultWithAlliance;
import com.itgrids.partyanalyst.model.PartyElectionStateResultWithAlliance;

public class PartyElectionDistrictResultWithAllianceDAO extends
		GenericDaoHibernate<PartyElectionDistrictResultWithAlliance, Long>
		implements IPartyElectionDistrictResultWithAllianceDAO {

	public PartyElectionDistrictResultWithAllianceDAO() {
		super(PartyElectionDistrictResultWithAlliance.class);
	}

	@SuppressWarnings("unchecked")
	public List<PartyElectionStateResultWithAlliance> getPartyResultsForADistrictByPartyIdAndElectionId(Long electionId,Long partyId,Long districtId){
		Object[] parms = {electionId,partyId,districtId};
		return getHibernateTemplate().find("select model.district.districtId,model.totalConstiParticipated,model.totalValidVotes," +
				" model.votesPercentage,model.completeVotesPercent,model.totalSeatsWon from PartyElectionDistrictResultWithAlliance model where model.election.electionId = ? and" +
				" model.party.partyId = ? and model.district.districtId = ? order by model.district.districtId",parms);
		
	}
	
	public List findDistrictWiseElectionResultsForStatePartyAndElection(Long partyId, Long stateId, Long electionId){
		Object[] params = {partyId, electionId, stateId};
		return getHibernateTemplate().find("select model.district.districtId, model.district.districtName, model.totalConstiParticipated, " +
				"model.totalSeatsWon, model.votesPercentage, model.completeVotesPercent from PartyElectionDistrictResultWithAlliance model " +
				"where model.party.partyId = ? and model.election.electionId = ? and model.district.state.stateId = ?", params);
	}
}
