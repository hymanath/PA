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
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultDAO;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;

public class PartyElectionDistrictResultDAO extends GenericDaoHibernate<PartyElectionDistrictResult, Long>
		implements IPartyElectionDistrictResultDAO {

	public PartyElectionDistrictResultDAO() {
		super(PartyElectionDistrictResult.class);
	}
		
	@SuppressWarnings("unchecked")
	public List<PartyElectionDistrictResult> getByPartyIdElectionIdAndDistrict(Long electionId, Long partyId, Long districtId) {
		Object[] params = {electionId,partyId,districtId};
		return getHibernateTemplate().find("from PartyElectionDistrictResult model where model.election.electionId = ? and model.party.partyId = ? and model.district.districtId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List getParticipatedPartysCountForAnElection(Long electionId){
		return getHibernateTemplate().find("select count(distinct model.party.partyId) from PartyElectionDistrictResult model where model.election.electionId = ?", electionId);
	}

	@SuppressWarnings("unchecked")
	public List getAllParyDetailsForAllElectionYearsForADistrict(String electionIds, String partyIds, Long districtId){
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName,model.election.electionYear," +
				" model.election.electionScope.electionType.electionType,model.totalVotesGained,model.completeVotesPercent," +
				" model.totalSeatsWon from PartyElectionDistrictResult model where model.election.electionId in ("+electionIds+
				" ) and model.party.partyId in ("+partyIds+
				" ) and model.district.districtId = ? order by model.party.partyId", districtId);
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
	
	public List getAllElectionResultsInDistrictForElectionType(Long electionTypeId, Long districtId){
		Object[] params = {electionTypeId, districtId};
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName,model.election.electionYear," +
				" model.election.electionScope.electionType.electionType,model.totalVotesGained,model.completeVotesPercent," +
				" model.totalSeatsWon from PartyElectionDistrictResult model where model.election.electionScope.electionScopeId = ?" +
				" and model.district.districtId = ? order by model.party.partyId", params);
	}
	
	public List getAllElectionResultsInDistrict(Long electionTypeId, Long districtId){
		Object[] params = {electionTypeId, districtId};
		return getHibernateTemplate().find("select distinct model.election.electionYear," +
				" model.election.electionScope.electionType.electionType" +
				" from PartyElectionDistrictResult model where model.election.electionScope.electionScopeId = ?" +
				" and model.district.districtId = ? order by model.party.partyId", params);
	}
	
	public List findDistrictWiseElectionResultsForStatePartyAndElection(Long partyId, Long stateId, Long electionId){
		Object[] params = {partyId, electionId, stateId};
		return getHibernateTemplate().find("select model.district.districtId, model.district.districtName, model.totalConstiParticipated, " +
				"model.totalSeatsWon, model.votesPercentage, model.completeVotesPercent from PartyElectionDistrictResult model " +
				"where model.party.partyId = ? and model.election.electionId = ? and model.district.state.stateId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List findPartyResultsForARegionInState(List<Long> districtIds,Long electionId, List<Long> partyIds){
		
		Query queryObject = getSession().createQuery("select model.party.partyId,model.party.shortName,sum(model.totalSeatsWon)," +
				"sum(model.totalConstiParticipated),sum(model.totalVotesGained),sum(model.totalValidVotes) from PartyElectionDistrictResult " +
				" model where model.election.electionId = ? and " +
				"model.district.districtId in (:districtIds) and  model.party.partyId in (:partyIds) " +
				"group by model.party.partyId order by sum(model.totalSeatsWon) desc");
		
		queryObject.setParameter(0,electionId);
		queryObject.setParameterList("districtIds",districtIds);
		queryObject.setParameterList("partyIds",partyIds);
		return queryObject.list();
		
	}

   public List<Object[]> findDistrictWiseCompleteValidVotesInARegionInState(List<Long> districtIds,Long electionId){
		
		Query queryObject = getSession().createQuery("select distinct model.district.districtId,model.completeConstiValidVotes from PartyElectionDistrictResult " +
				" model where model.election.electionId = :electionId and model.district.districtId in (:districtIds) ");
		
		queryObject.setParameter("electionId",electionId);
		queryObject.setParameterList("districtIds",districtIds);

		return queryObject.list();
		
	}
   
	public List<PartyElectionDistrictResult> getParticipatedPartiesDetailsInDistricts(Long electionId,List districtIdList){
		
		Query query = getSession().createQuery("select model from PartyElectionDistrictResult model where model.election.electionId = :electionId and model.district.districtId in (:districtIdList) and model.totalSeatsWon > 0 ");
		
		query.setParameter("electionId", electionId);
		query.setParameterList("districtIdList", districtIdList);
		
		return query.list();
		
		
	}
	
public List<Long> getAllPartispatedPartiesInaAnElection(Long electionId, List<Long> partyIds){
		
		Query queryObject = getSession().createQuery("select distinct model.party.partyId from PartyElectionDistrictResult " +
				" model where model.election.electionId = ?  and  model.party.partyId in (:partyIds)");
		queryObject.setParameter(0,electionId);
		queryObject.setParameterList("partyIds",partyIds);
		return queryObject.list();
		
	}
}
