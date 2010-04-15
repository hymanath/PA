/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 09,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultDAO;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;

public class PartyElectionDistrictResultDAO extends GenericDaoHibernate<PartyElectionDistrictResult, Long>
		implements IPartyElectionDistrictResultDAO {

	public PartyElectionDistrictResultDAO() {
		super(PartyElectionDistrictResult.class);
	}

	@SuppressWarnings("unchecked")
	public List<PartyElectionDistrictResult> getByPartyIdElectionIdAndDistrict(Long electionId, Long partyId, Long stateId, Long districtId) {
		Object[] params = {electionId,partyId,stateId,districtId};
		return getHibernateTemplate().find("from PartyElectionDistrictResult model where model.election.electionId = ? and model.party.partyId = ? and model.state.stateId = ? and model.district.districtId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List getParticipatedPartysCountForAnElection(Long electionId){
		return getHibernateTemplate().find("select count(distinct model.party.partyId) from PartyElectionDistrictResult model where model.election.electionId = ?", electionId);
	}

	@SuppressWarnings("unchecked")
	public List getAllParyDetailsForAllElectionYearsForADistrict(String electionIds, String partyIds, Long stateId, Long districtId){
		Object[] params = {stateId,districtId};
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName,model.election.electionYear," +
				" model.election.electionScope.electionType.electionType,model.totalVotesGained,model.completeVotesPercent," +
				" model.totalSeatsWon from PartyElectionDistrictResult model where model.election.electionId in ("+electionIds+
				" ) and model.party.partyId in ("+partyIds+
				" ) and model.state.stateId = ? " +
				" and model.district.districtId = ? order by model.party.partyId", params);
	}

	@SuppressWarnings("unchecked")
	public List<PartyElectionDistrictResult> getDistrictWiseAllPartiesResults(Long electionId,String votesPercentMargin){
		Object[] params = {votesPercentMargin,electionId};
		return getHibernateTemplate().find("from PartyElectionDistrictResult model where model.completeVotesPercent > ? and model.election.electionId = ? order by model.party.partyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<PartyElectionDistrictResult> getPartyElecResultsInAllDistsForAParty(Long electionId,Long partyId){
		Object[] params = {electionId,partyId};
		return getHibernateTemplate().find("from PartyElectionDistrictResult model where model.election.electionId = ? and model.party.partyId = ?",params);
	}
	
	public List getPartiesPositionsInDistrictInElection(Long electionId, Long districtId, String votesPercentMargin){
		Object[] params = {votesPercentMargin, electionId, districtId};
		return getHibernateTemplate().find("select model.party.partyId, model.party.shortName, model.totalSeatsWon, model.secondPosWon, " +
				"model.thirdPosWon, model.fourthPosWon from PartyElectionDistrictResult model where model.completeVotesPercent > ? and model.election.electionId = ? and " +
				"model.district.districtId = ? order by model.totalSeatsWon",params);
	}


}
