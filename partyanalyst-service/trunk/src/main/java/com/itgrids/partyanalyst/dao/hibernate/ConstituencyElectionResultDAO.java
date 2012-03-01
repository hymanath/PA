/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 11, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.dao.columns.enums.ConstituencyElectionResultColumnNames;

import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;


/**
*@author <a href="mailto:sai.basetti@gmail.com">Sai Krishna</a>
*@author <a href="mailto:sriharigopalnalam@gmail.com">Srihari</a>
*/


public class ConstituencyElectionResultDAO extends GenericDaoHibernate<ConstituencyElectionResult, Long>
		implements IConstituencyElectionResultDAO {
    
	
	public ConstituencyElectionResultDAO() {
		super(ConstituencyElectionResult.class);
	}
	public List<ConstituencyElectionResult> findByMissingVotes(
			Object missingVotes) {
		
		return findByProperty(ConstituencyElectionResultColumnNames.MISSING_VOTES, missingVotes);
	}

	
	public List<ConstituencyElectionResult> findByRejectedVotes(
			Object rejectedVotes) {
		
		return findByProperty(ConstituencyElectionResultColumnNames.REJECTED_VOTES, rejectedVotes);
	}

	public List<ConstituencyElectionResult> findByTenderedVotes(
			Object tenderedVotes) {
		
		return findByProperty(ConstituencyElectionResultColumnNames.TENDERED_VOTES, tenderedVotes);
	}

	public List<ConstituencyElectionResult> findByTotalVotes(Object totalVotes) {
		
		return findByProperty(ConstituencyElectionResultColumnNames.TOTAL_VOTES, totalVotes);
	}

	public List<ConstituencyElectionResult> findByTotalVotesPolled(
			Object totalVotesPolled) {
		
		return findByProperty(ConstituencyElectionResultColumnNames.TOTAL_VOTES_POLLED, totalVotesPolled);
	}

	public List<ConstituencyElectionResult> findByValidVotes(Object validVotes) {
		
		return findByProperty(ConstituencyElectionResultColumnNames.VALID_VOTES, validVotes);
	}

	@SuppressWarnings("unchecked")
	public List<ConstituencyElectionResult> findByProperty(ConstituencyElectionResultColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from ConstituencyElectionResult where " + propertyName.getValue() + "=?", value);		
		
	}
	
/**
 * @param constituencyElectionIDs should be list of constituencyElectionIDs with comma seperated string
 */
	@SuppressWarnings("unchecked")
	public List<ConstituencyElectionResult> findByConstituencyElections(String constituencyElectionIDs){
		return getHibernateTemplate().find( "from ConstituencyElectionResult model where model.constituencyElection.constiElecId in ("+constituencyElectionIDs+")");
	}

	@SuppressWarnings("unchecked")
	public List<ConstituencyElectionResult> findByElectionTypeId_Year_StateId(
		final Long electionTypeId, final String electionYear, final Long stateId) {
		return ( List<ConstituencyElectionResult> ) getHibernateTemplate().execute( new HibernateCallback() {
            public Object doInHibernate( Session session ) throws HibernateException, SQLException {
            		List<ConstituencyElectionResult> constElectionResults = session.createCriteria(ConstituencyElectionResult.class)
            							.createAlias("constituencyElection", "constElec")
            							.createAlias("constElec.constituency", "consti")
            							.createAlias("consti.state", "state")
            							.createAlias("constElec.election", "elec")
            							.createAlias("elec.electionScope", "scope")
            							.createAlias("scope.electionType", "type")
            							.add(Expression.eq("type.electionTypeId", electionTypeId))
            							.add(Expression.eq("elec.electionYear", electionYear))
            							.add(Expression.eq("state.stateId", stateId))
            							.list();
            		 return constElectionResults;
            }
        });
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElectionResult> findByElectionTypeId_Year_StateId_DistrictId(
		final Long electionTypeId, final String electionYear, final Long stateId, final Long districtId) {
		return ( List<ConstituencyElectionResult> ) getHibernateTemplate().execute( new HibernateCallback() {
            public Object doInHibernate( Session session ) throws HibernateException, SQLException {
            		List<ConstituencyElectionResult> constElectionResults = session.createCriteria(ConstituencyElectionResult.class)
            							.createAlias("constituencyElection", "constElec")
            							.createAlias("constElec.constituency", "consti")
            							.createAlias("consti.state", "state")
            							.createAlias("consti.district", "dist")
            							.createAlias("constElec.election", "elec")
            							.createAlias("elec.electionScope", "scope")
            							.createAlias("scope.electionType", "type")
            							.add(Expression.eq("type.electionTypeId", electionTypeId))
            							.add(Expression.eq("elec.electionYear", electionYear))
            							.add(Expression.eq("state.stateId", stateId))
            							.add(Expression.eq("dist.districtId", districtId))
            							.list();
            		 return constElectionResults;
            }
        });
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElectionResult> findByElectionTypeAndYearAndCountry(Long electionTypeId,String year,Long countryId){
		Object params[] = {electionTypeId,year,countryId};
		return getHibernateTemplate().find("from ConstituencyElectionResult model where model.constituencyElection.election.electionScope.electionType.electionTypeId = ? and model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.countryId = ?",params);
	}
	
	public List getTotalVotesAndValidVotesForMPTCZPTC(Long tehsilId,
			String electionType, String electionYear) {
		Object[] params = {tehsilId, electionType, electionYear};
		return getHibernateTemplate().find("select sum(model.totalVotes), sum(model.validVotes), " +
				"count(model.constituencyElection.constituency.constituencyId) " +
				"from ConstituencyElectionResult model where " +
				"model.constituencyElection.constituency.tehsil.tehsilId = ? and " +
				"model.constituencyElection.election.electionScope.electionType.electionType = ? and " +
				"model.constituencyElection.election.electionYear = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElectionResult> findByConstituency(Long constituencyId){
		return getHibernateTemplate().find("from ConstituencyElectionResult model where model.constituencyElection.constituency.constituencyId = ?", constituencyId);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findTotalVotersAndValidVotesByYearAndConstituencyIds(
			List<Long> constituencyIds, String year) {
		Query queryObject = getSession().createQuery("select sum(model.totalVotes), sum(model.validVotes) from ConstituencyElectionResult model " +
				"where model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.constituencyId in " +
				"(:constituencyIds)");
		queryObject.setParameter(0,year);
		queryObject.setParameterList("constituencyIds", constituencyIds);
		return queryObject.list(); 
	}
	@SuppressWarnings("unchecked")
	public List<ConstituencyElectionResult> getConstituencyElectionResultByElection(
			Long electionId) {
		
		return getHibernateTemplate().find("from ConstituencyElectionResult model where model.constituencyElection.election.electionId = ?", electionId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findTotalVotesAndValidVotesAndPolledVotesAndVotesPercentage(Long electionId ){
		
		return getHibernateTemplate().find(" select sum(model.totalVotes),sum(model.validVotes),sum(model.totalVotesPolled),count(model.totalVotes)"+
				" from ConstituencyElectionResult model where model.constituencyElection.election.electionId = ? " 
				, electionId); 
	}
	
	public Object getResultKnownConstituenciesCountInAElection(Long electionId)
	{
		Query query = getSession().createQuery("select count(model.constituencyElection.constiElecId) from ConstituencyElectionResult model where model.constituencyElection.election.electionId = ?");
		query.setParameter(0,electionId);
		
		return query.uniqueResult();
	}
}
