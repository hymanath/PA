/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 11, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.columns.enums.CandidateResultColumnNames;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.utils.IConstants;



public class CandidateResultDAO extends GenericDaoHibernate<CandidateResult, Long> implements
		ICandidateResultDAO {
  
	public CandidateResultDAO() {
		super(CandidateResult.class);
	}	
	

	public List<CandidateResult> findByRank(Object rank) {
		
		return findByProperty(CandidateResultColumnNames.RANK, rank);
	}

	public List<CandidateResult> findByVotesEarned(Object votesEarned) {
		
		return findByProperty(CandidateResultColumnNames.VOTES_EARNED, votesEarned);
	}
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findByProperty(CandidateResultColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from CandidateResult where " + propertyName.getValue() + "=?", value);		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> findCandidateResultsCount(Long electionScopeId,Long partyId,String year){
		
		Query queryObject = getSession().createQuery("select count(model.candidateResultId) from CandidateResult as model where model.nomination.nominationId in ( select model.nomination.nominationId from model where model.nomination.constituencyElection.election.electionScope.electionScopeId = ? and model.nomination.constituencyElection.election.electionYear = ?) and model.nomination.party.partyId = ?" );
		  queryObject.setParameter(0, electionScopeId);
		  queryObject.setParameter(1, year);
		  queryObject.setParameter(2, partyId);
		return queryObject.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResults(Long electionScopeId,Long partyId,String year){
		
		Query queryObject = getSession().createQuery("from CandidateResult as model where model.nomination.party.partyId = ? and model.nomination.nominationId in ( select model.nomination.nominationId from model where model.nomination.constituencyElection.election.electionScope.electionScopeId = ? and model.nomination.constituencyElection.election.electionYear = ?)" );
		  queryObject.setParameter(0, partyId);
		  queryObject.setParameter(1, electionScopeId);
		  queryObject.setParameter(2, year);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResults(Long electionScopeId,List<Long> partyIds,String year){
		
		StringBuffer queryBuffer = new StringBuffer("from CandidateResult as model where model.nomination.nominationId in ( select model.nomination.nominationId from model where model.nomination.constituencyElection.election.electionScope.electionScopeId = " + electionScopeId + " and model.nomination.constituencyElection.election.electionYear = " + year + " ) and model.nomination.party.partyId in(");
		for(int i=0;i<partyIds.size();i++){
			queryBuffer.append(partyIds.get(i) + ",");
		}
		String query = queryBuffer.toString().substring(0, queryBuffer.toString().length()-1);
		query = query + "))";
		
		return getHibernateTemplate().find(query);
		
	}

	public List getMPTCElectionResultForMandal(Long mandalID){
		Object[] params = {mandalID,IConstants.MPTC};
		return getHibernateTemplate().find("select cr.nomination.constituencyElection.election.electionYear, " +
				"cr.rank, " +
				"cr.nomination.constituencyElection.constituency.name, " +
				"cr.nomination.party.shortName, " +
				"cr.nomination.candidate.lastname, cr.votesEarned, cr.nomination.constituencyElection.constituencyElectionResult.validVotes " +
				"from CandidateResult cr where cr.nomination.constituencyElection.constituency.tehsil.tehsilId=? and " +
				"cr.nomination.constituencyElection.constituency.electionScope.electionType.electionType=? " +
				"order by cr.nomination.constituencyElection.election.electionYear, " +
				"cr.nomination.constituencyElection.constituency.name," +
				"cr.rank", params);
	}


	@SuppressWarnings("unchecked")
	public List getVotesPercentOfACandidateInAnElection(Long electionId,Long constituencyId,
			Long rank) {
		Object[] params = {electionId,constituencyId,rank};
		return getHibernateTemplate().find("select model.votesPercengate from CandidateResult model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.constituencyElection.constituency.constituencyId = ?"+
				" and model.rank = ?",params);
	}


	@SuppressWarnings("unchecked")
	public List getElectionResultsForAllPartiesInAMandal(Long mandalId,
			String electionType, String electionYear) {
		Object[] params = {mandalId,electionType,electionYear};
		return getHibernateTemplate().find("select model.nomination.party.partyId,model.nomination.party.shortName,"+
				"sum(model.votesEarned),sum(model.nomination.constituencyElection.constituencyElectionResult.validVotes) "+
				"from CandidateResult model where model.nomination.constituencyElection.constituency.tehsil.tehsilId = ? and "+
				"model.nomination.constituencyElection.election.electionScope.electionType.electionType = ? and "+
				"model.nomination.constituencyElection.election.electionYear = ?"+
				"group by model.nomination.party.partyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResultObjects(Long electionId) {
		
		Query queryObject = getSession().createQuery("from CandidateResult as model where model.nomination.constituencyElection.election.electionId = ?");
		 queryObject.setParameter(0, electionId);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResultObjects(Long candidateId,Long rank){
		
		Query queryObject = getSession().createQuery("from CandidateResult as model where model.rank = ? and model.nomination.candidate.candidateId = ?");
		 queryObject.setParameter(0, rank);
		 queryObject.setParameter(1, candidateId);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResults(Long candidateId){
		Query queryObject = getSession().createQuery("from CandidateResult as model where model.nomination.candidate.candidateId = ? order by model.nomination.constituencyElection.election.electionYear desc");
		  queryObject.setParameter(0, candidateId);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResults(Long candidateId,Long electionId,Long constituencyId){
		Query queryObject = getSession().createQuery("from CandidateResult as model where model.nomination.candidate.candidateId != ? and model.nomination.constituencyElection.election.electionId = ? and model.nomination.constituencyElection.constituency.constituencyId = ?");
		  queryObject.setParameter(0, candidateId);
		  queryObject.setParameter(1, electionId);
		  queryObject.setParameter(2, constituencyId);
		return queryObject.list();
	}
	
	public int updateMarginVotesAndPercentage(String marginPercentage,Double marginVotes,String electionYear,String electionType,Long constituencyId,Long candidateId){
		StringBuilder query = new StringBuilder();				
		query.append(" update CandidateResult model set model.marginVotesPercentage = ? ,model.marginVotes  = ? where");
		query.append(" model.nomination.nominationId = (select model1.nominationId from Nomination model1 where ");
		query.append(" model1.constituencyElection.election.electionYear = ?");
		
		 if(electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.GREATER_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE))
			query.append(" and model1.constituencyElection.constituency.localElectionBody.electionType.electionType = ?");	
		 else
			query.append(" and model1.constituencyElection.constituency.electionScope.electionType.electionType = ?");
		 
		query.append(" and model1.constituencyElection.constituency.constituencyId = ?");
		query.append(" and model1.candidate.candidateId = ?)");		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setString(0, marginPercentage);
		queryObject.setDouble(1, marginVotes);
		queryObject.setString(2, electionYear);
		queryObject.setString(3, electionType);
		queryObject.setLong(4, constituencyId);
		queryObject.setLong(5, candidateId);
		
		return queryObject.executeUpdate();
	}


	@SuppressWarnings("unchecked")
	public List findCandidateResultByElectionId(Long electionId) {
		
		return getHibernateTemplate().find("select model.nomination.candidate.candidateId,model from CandidateResult model where model.nomination.constituencyElection.election.electionId = ?",electionId);
	}


	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResultsByElectionId(
			Long electionId) {
		
		return getHibernateTemplate().find("from CandidateResult model where model.nomination.constituencyElection.election.electionId = ?",electionId);
	}


	public Long getCandidateResultsCountInaConstituencyInAnElection(Long constiElecId) {
		
		Long resultsCount = ( (Long) getSession().createQuery("select count(model.candidateResultId) from CandidateResult model "+
				"where model.nomination.constituencyElection.constiElecId = "+constiElecId).iterate().next() );
	 return resultsCount;
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResultsByCandidateIds(List<Long> candidateIds , Long electionId){
		
		Query queryObject = getSession().createQuery("from CandidateResult as model where model.nomination.constituencyElection.election.electionId = :electionId and model.nomination.candidate.candidateId in(:candidateIds) order by model.nomination.constituencyElection.election.electionYear desc");
		  queryObject.setParameter("electionId",electionId);
		  queryObject.setParameterList("candidateIds", candidateIds);
		return queryObject.list();
	}
	
	public List<String> getTdpPartyPercentage(Long constituencyId){
		Query queryObject = getSession().createQuery("select model.votesPercengate from CandidateResult model where model.nomination.constituencyElection.election.electionId in (3,38) and model.nomination.constituencyElection.constituency.constituencyId =:constituencyId and model.nomination.party.partyId = 872 order by model.nomination.constituencyElection.election.electionYear desc");
		  queryObject.setParameter("constituencyId",constituencyId);
		return queryObject.list();
	}
	
	public List<String> getPartyPercentage(Long constituencyId,Long electionId,List<Long> partyIds){
		Query queryObject = getSession().createQuery("select model.votesPercengate from CandidateResult model where model.nomination.constituencyElection.election.electionId =:electionId and model.nomination.constituencyElection.constituency.constituencyId =:constituencyId and model.nomination.party.partyId in (:partyIds) order by model.votesPercengate desc ");
		  queryObject.setParameter("constituencyId",constituencyId);
		  queryObject.setParameter("electionId",electionId);
		  queryObject.setParameterList("partyIds",partyIds);
		return queryObject.list();
	}
	
	public List<Object[]> getPreviousElectionWinningPartyByConstituency(Long constituencyId){
		Query query = getSession().createQuery(" select model.nomination.party.partyId,model.nomination.party.shortName," +
				" model.nomination.constituencyElection.constituency.name," +
				" model.nomination.constituencyElection.election.electionYear from CandidateResult model where model.rank = 1 " +
				" and model.nomination.constituencyElection.constituency.constituencyId =:constituencyId " +
				" and nomination.constituencyElection.constituency.electionScope.electionType.electionTypeId=2 order by " +
				" model.nomination.constituencyElection.election.electionYear desc ");
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	public List<Object[]> getElectionResultsByMargin(Long electionId,List<Long> locationIds,Long type){
		
		StringBuffer sb = new StringBuffer();
		if(type.equals(2l)){
		sb.append("select model.nomination.party.partyId," +//0
				" model.nomination.party.shortName," +//1
				" model.nomination.constituencyElection.constituency.constituencyId," +//2
				" model.nomination.constituencyElection.constituency.name," +//3
				" model.votesEarned," +//4
				" model.marginVotes," +//5
				" model.marginVotesPercentage" +//6 
				" from CandidateResult model where  ");
		
		sb.append(" model.rank = 1 and model.nomination.constituencyElection.constituency.district.districtId in(:locationIds)" +
				" and model.nomination.constituencyElection.election.electionId =:electionId" +
				" order by " +
				" model.nomination.constituencyElection.constituency.constituencyId,model.votesEarned desc ");
		}
		
		if(type.equals(4l)){
		sb.append("select model.nomination.party.partyId," +//0
				" model.nomination.party.shortName," +//1
				" model.nomination.constituencyElection.constituency.constituencyId," +//2
				" model.nomination.constituencyElection.constituency.name," +//3
				" model.votesEarned," +//4
				" model.marginVotes," +//5
				" model.marginVotesPercentage" +//6 
				" from CandidateResult model,DelimitationConstituencyAssemblyDetails model2 where  ");
		
		sb.append(" model.nomination.constituencyElection.constituency.constituencyId = model2.constituency.constituencyId and" +
				" model.rank = 1 and model2.delimitationConstituency.constituency.constituencyId in(:locationIds)" +
				" and model.nomination.constituencyElection.election.electionId =:electionId" +
				" and model2.delimitationConstituency.year = 2009 " +
				" order by " +
				" model.nomination.constituencyElection.constituency.constituencyId,model.votesEarned desc ");
		}
		
		if(type.equals(3l)){
		sb.append("select model.nomination.party.partyId," +//0
				" model.nomination.party.shortName," +//1
				" model.nomination.constituencyElection.constituency.constituencyId," +//2
				" model.nomination.constituencyElection.constituency.name," +//3
				" model.votesEarned," +//4
				" model.marginVotes," +//5
				" model.marginVotesPercentage" +//6 
				" from CandidateResult model,StateRegionDistrict model2 where  ");
		
		sb.append(" model.nomination.constituencyElection.constituency.district.districtId = model2.district.districtId and " +
				" model.rank = 1 and model2.stateRegion.stateRegionId in(:locationIds)" +
				" and model.nomination.constituencyElection.election.electionId =:electionId" +
				" order by " +
				" model.nomination.constituencyElection.constituency.constituencyId,model.votesEarned desc ");
		}
			
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("electionId", electionId);
		query.setParameterList("locationIds", locationIds);
		
		return query.list();
	}
	
	public List<Object[]> getElectionResultsByConstituency(Long electionId,String region){
		StringBuffer sb = new StringBuffer();
		sb.append("select model.nomination.party.partyId," +//0
				" model.nomination.party.shortName," +//1
				" model.nomination.constituencyElection.constituency.constituencyId," +//2
				" model.nomination.constituencyElection.constituency.name," +//3
				" model.nomination.constituencyElection.constituency.district.districtId," +//4
				" model.nomination.constituencyElection.constituency.district.districtName," +//5
				" model.votesEarned" +//6 
				" from CandidateResult model where  ");
		
		if(region.equalsIgnoreCase("Seemandhra")){
			sb.append(" model.nomination.constituencyElection.constituency.district.districtId > 10 and " );
		}else{
			sb.append(" model.nomination.constituencyElection.constituency.district.districtId <= 10 and " );
		}
		
			sb.append(" model.nomination.constituencyElection.constituency.electionScope.electionType.electionTypeId=2 " +
					" and model.nomination.constituencyElection.election.electionId =:electionId order by " +
					" model.nomination.constituencyElection.constituency.constituencyId,model.votesEarned desc ");
		
			
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("electionId", electionId);
		
		return query.list();
	}
	
	public List<Object[]> getElectionResultsByParliament(Long electionId){
		StringBuffer sb = new StringBuffer();
		sb.append("select model.nomination.party.partyId," +//0
				" model.nomination.party.shortName," +//1
				" model.nomination.constituencyElection.constituency.constituencyId," +//2
				" model.nomination.constituencyElection.constituency.name," +//3
				" model.nomination.constituencyElection.constituency.state.stateId," +//4
				" model.nomination.constituencyElection.constituency.state.stateName," +//5
				" model.votesEarned," +//6
				" model.nomination.constituencyElection.countStatus," +//7
				" model.nomination.party.partyLogo," +//8
				" model.nomination.party.partyFlag," +//9
				" model.nomination.party.partyFlagLarge" +//10
				" from CandidateResult model where  ");
		
		sb.append("model.rank = 1 and model.nomination.constituencyElection.constituency.electionScope.electionType.electionTypeId=1 " +
				" and model.nomination.constituencyElection.election.electionId =:electionId order by " +
				" model.nomination.constituencyElection.constituency.state.stateId," +
				" model.nomination.constituencyElection.constituency.constituencyId," +
				" model.votesEarned desc ");
		
			
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("electionId", electionId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getElectionResultsForSelection(Long electionId,Long stateid,List<Long> partyIds,Long electionScopeId)
	{
		StringBuffer sb = new StringBuffer();
		if(electionScopeId.longValue() == 2l)
		{
			sb.append("select model.nomination.constituencyElection.constituency.constituencyId ," +
					" model.nomination.constituencyElection.constituency.name," +
					" model.nomination.constituencyElection.constituency.district.districtName ," +
					" model.votesEarned , model.votesPercengate ," +
					" model.nomination.party.partyId , model.nomination.party.shortName," +
					" model.nomination.candidate.lastname , model.rank from CandidateResult model where " +
					" model.nomination.constituencyElection.election.electionId = :electionId " +
					" and model.nomination.constituencyElection.constituency.state.stateId = :stateid " +
					" order by model.nomination.constituencyElection.constituency.constituencyId");
		}
		else
		{
			sb.append("select model.nomination.constituencyElection.constituency.constituencyId ," +
					" model.nomination.constituencyElection.constituency.name," +
					" model.nomination.constituencyElection.constituency.name," +
					" model.votesEarned , model.votesPercengate ," +
					" model.nomination.party.partyId , model.nomination.party.shortName," +
					" model.nomination.candidate.lastname,model.rank from CandidateResult model where " +
					" model.nomination.constituencyElection.election.electionId = :electionId " +
					" and model.nomination.constituencyElection.constituency.countryId = :stateid " +
					" order by model.nomination.constituencyElection.constituency.constituencyId");
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("electionId", electionId);
		query.setParameter("stateid", stateid);
		//query.setParameterList("partyIds", partyIds);
		return query.list();
	}
	
	public List<Object[]> getElectionResultsForCBNORMODIEffect(Long electionId,Long stateid,List<Long> partyId,Long electionScopeId)
	{
		StringBuffer sb = new StringBuffer();
		if(electionScopeId.longValue() == 2l)
		{
			sb.append("select model.nomination.constituencyElection.constituency.constituencyId ," +
					" model.nomination.constituencyElection.constituency.name,model.nomination.constituencyElection.constituency.district.districtId from CandidateResult model where " +
					" model.nomination.party.partyId in(:partyId) and model.nomination.constituencyElection.election.electionId = :electionId " +
					"  " +
					" and model.rank = 1 " +
					" order by model.nomination.constituencyElection.constituency.constituencyId");
		}
		else
		{
			sb.append("select model.nomination.constituencyElection.constituency.constituencyId ," +
					" model.nomination.constituencyElection.constituency.name" +
					" from CandidateResult model where " +
					" model.nomination.party.partyId in(:partyId) and model.nomination.constituencyElection.election.electionId = :electionId " +
					" " +
					" and model.rank = 1" +
					" order by model.nomination.constituencyElection.constituency.constituencyId");
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("electionId", electionId);
		//query.setParameter("stateid", stateid);
		query.setParameterList("partyId", partyId);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> filterToGetElectionResultsForSelection(Long electionId,List<Long> partyIds,Long electionScopeId,List<Long> constiIds)
	{
		StringBuffer sb = new StringBuffer();
		if(electionScopeId.longValue() == 2l)
		{
			sb.append("select distinct model.nomination.constituencyElection.constituency.constituencyId ," +
					" model.nomination.constituencyElection.constituency.name," +
					" model.nomination.constituencyElection.constituency.district.districtName ," +
					" model.votesEarned , model.votesPercengate ," +
					" model.nomination.party.partyId , model.nomination.party.shortName," +
					" model.nomination.candidate.lastname , model.rank from CandidateResult model where " +
					" model.nomination.constituencyElection.election.electionId = :electionId " +
					" and model.nomination.constituencyElection.constituency.constituencyId in (:constiIds ) " +
					" order by model.nomination.constituencyElection.constituency.name asc");
		}
		else
		{
			sb.append("select model.nomination.constituencyElection.constituency.constituencyId ," +
					" model.nomination.constituencyElection.constituency.name," +
					" model.nomination.constituencyElection.constituency.name," +
					" model.votesEarned , model.votesPercengate ," +
					" model.nomination.party.partyId , model.nomination.party.shortName," +
					" model.nomination.candidate.lastname,model.rank from CandidateResult model where " +
					" model.nomination.constituencyElection.election.electionId = :electionId " +
					" and model.nomination.constituencyElection.constituency.countryId = 1 " +
					" and model.nomination.constituencyElection.constituency.constituencyId in (:constiIds ) " +
					" order by model.nomination.constituencyElection.constituency.name");
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("electionId", electionId);
		query.setParameterList("constiIds", constiIds);
		//query.setParameterList("partyIds", partyIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> searchElectionResultsByConstituencyName(Long electionId,List<Long> partyIds,Long electionScopeId,List<Long> constiIds,String searchName)
	{
		StringBuffer sb = new StringBuffer();
		if(electionScopeId.longValue() == 2l)
		{
			sb.append("select model.nomination.constituencyElection.constituency.constituencyId ," +
					" model.nomination.constituencyElection.constituency.name," +
					" model.nomination.constituencyElection.constituency.district.districtName ," +
					" model.votesEarned , model.votesPercengate ," +
					" model.nomination.party.partyId , model.nomination.party.shortName," +
					" model.nomination.candidate.lastname , model.rank from CandidateResult model where " +
					" model.nomination.constituencyElection.election.electionId = :electionId " +
					" and model.nomination.constituencyElection.constituency.constituencyId in (:constiIds ) " +
					" and model.nomination.constituencyElection.constituency.name like '"+searchName+"' "+
					" order by model.nomination.constituencyElection.constituency.name asc");
		}
		else
		{
			sb.append("select model.nomination.constituencyElection.constituency.constituencyId ," +
					" model.nomination.constituencyElection.constituency.name," +
					" model.nomination.constituencyElection.constituency.name," +
					" model.votesEarned , model.votesPercengate ," +
					" model.nomination.party.partyId , model.nomination.party.shortName," +
					" model.nomination.candidate.lastname,model.rank from CandidateResult model where " +
					" model.nomination.constituencyElection.election.electionId = :electionId " +
					//" and model.nomination.constituencyElection.constituency.countryId = :stateid " +
					" and model.nomination.constituencyElection.constituency.constituencyId in (:constiIds ) " +
					" and model.nomination.constituencyElection.constituency.name like '"+searchName+"' "+
					" order by model.nomination.constituencyElection.constituency.name");
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("electionId", electionId);
		query.setParameterList("constiIds", constiIds);
		//query.setParameterList("partyIds", partyIds);
		return query.list();
	}
	
}
