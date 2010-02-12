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
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.columns.enums.NominationColumnNames;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;

/**
*@author <a href="mailto:sai.basetti@gmail.com">Sai Krishna</a>
*@author <a href="mailto:sriharigopalnalam@gmail.com">Srihari</a>
*/

public class NominationDAO extends GenericDaoHibernate<Nomination, Long> implements
		INominationDAO {
    
	
	public NominationDAO() {
		super(Nomination.class);
	}	 
	public List<Nomination> findByAssets(Object assets) {
		
		return findByProperty(NominationColumnNames.ASSETS, assets);
	}

	public List<Nomination> findByCriminalCharges(Object criminalCharges) {
		
		return findByProperty(NominationColumnNames.CRIMINAL_CHARGES, criminalCharges);
	}

	public List<Nomination> findByLiabilities(Object liabilities) {
		
		return findByProperty(NominationColumnNames.LIABILITIES, liabilities);
	}

	public List<Nomination> findByNominationStatus(Object nominationStatus) {
		
		return findByProperty(NominationColumnNames.NOMINATION_STATUS, nominationStatus);
	}

	@SuppressWarnings("unchecked")
	public List<Nomination> findByProperty(NominationColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from Nomination where " + propertyName.getValue() + "=?", value);		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByConstituencyElection(Long constituencyElectionID){
		return getHibernateTemplate().find( "from Nomination model where model.constituencyElection.constiElecId = ?",constituencyElectionID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByConstituencyElectionAndCandidate(String candidateName, Long constituencyElectionID){
		Object[] params = {candidateName, constituencyElectionID};
		return getHibernateTemplate().find( "from Nomination model where model.candidate.lastname = ? and model.constituencyElection.constiElecId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findConstitueniesByPartyAndElectionType(Long partyId, Long electionTypeId, String electionYear){
		Object[] params = {partyId, electionTypeId, electionYear};
		return getHibernateTemplate().find( "select model.constituencyElection.constituency from Nomination model where model.party.partyId = ? and model.constituencyElection.election.electionScope.electionType.electionTypeId = ? and model.constituencyElection.election.electionYear = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByConstituencyPartyAndElectionYear(Long partyId, Long constituencyId, String electionYear){
		Object[] params = {partyId, electionYear, constituencyId};
		return getHibernateTemplate().find( "from Nomination model where model.party.partyId = ? and model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.constituencyId = ?)", params);
	}
	
	@SuppressWarnings("unchecked")
    public List<Nomination> findByStatePartyAndElectionId(final Long stateId, final Long electionId, final Long partyId){
           
            return ( List<Nomination> ) getHibernateTemplate().execute( new HibernateCallback() {
                public Object doInHibernate( Session session ) throws HibernateException, SQLException {
                		List<Nomination> constElectionResults = session.createCriteria(Nomination.class)
                							.createAlias("constituencyElection", "constElec")
                							.createAlias("party", "p")
                							.createAlias("constElec.election", "elec")
                							.createAlias("constElec.constituency", "const")
                							.createAlias("const.state", "state")
                							.createAlias("candidate", "cand")
                							.add(Expression.eq("state.stateId", stateId))
                							.add(Expression.eq("elec.electionId", electionId))
                							.add(Expression.eq("p.partyId", partyId))
                							.addOrder(Order.asc("cand.lastname"))
                							.list();
                		 return constElectionResults;
                }
            });
    }
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByElectionIdAndPartys(Long electionId,List<Long> partys){
		
		StringBuffer queryBuffer = new StringBuffer("from Nomination model where model.nominationId in(select model.nominationId from model where model.constituencyElection.election.electionId = " + electionId + " and model.party.partyId in(");
		for(int i=0;i<partys.size();i++){
			queryBuffer.append(partys.get(i) + ",");
		}
		String query = queryBuffer.toString().substring(0, queryBuffer.toString().length()-1);
		query = query + "))";
				
		return getHibernateTemplate().find(query);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByCandidate(Long candidateId) {
		return getHibernateTemplate().find( "from Nomination model where model.candidate.candidateId = ?", candidateId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Party> findPartiesByConstituencyAndElection(Long constituencyId, String electionYear) {
		Object[] params = {constituencyId, electionYear};
		return getHibernateTemplate().find( "select model.party from Nomination model where model.constituencyElection.constituency.constituencyId = ? and model.constituencyElection.election.electionYear = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByConstituencyPartyAndElectionYearIncludingAliance(List<Long> aliancePartyIds, Long pcId, String electionYear) {
		StringBuilder queryBuffer = new StringBuilder();
		queryBuffer.append("from Nomination model where model.party.partyId in( ");
		for(int i=0; i<aliancePartyIds.size(); i++)
			queryBuffer.append(aliancePartyIds.get(i)+",");
		String query = queryBuffer.toString().substring(0, queryBuffer.toString().length()-1)+" ) and model.constituencyElection.election.electionYear = "+electionYear
						+"and model.constituencyElection.constituency.constituencyId =" +pcId;
		return getHibernateTemplate().find(query.toString());
	}
	
	@SuppressWarnings("unchecked")
	public List findCandidateNamePartyByConstituencyAndElection(String constituencyIds, String electionYear) {
		return getHibernateTemplate().find( "select upper(model.constituencyElection.constituency.name), upper(model.candidate.lastname), upper(model.party.shortName), " +
				" model.constituencyElection.constituency.constituencyId , model.candidate.candidateId from Nomination model where model.constituencyElection.constituency.constituencyId in (  " + constituencyIds +
				") and model.constituencyElection.election.electionYear = ? " +
				" and model.candidateResult.rank = 1", electionYear);
	}
	
	public List findMPTCInfoByElectionTypeTehsilAndParty(String mptcElectionType, Long tehsilID, Long partyId) {
		Object[] params = {tehsilID, mptcElectionType};
		return getHibernateTemplate().find("select model.party.shortName, model.constituencyElection.election.electionYear, sum(model.constituencyElection.constituencyElectionResult.totalVotesPolled), sum(model.constituencyElection.constituencyElectionResult.validVotes), "+
				"sum(model.candidateResult.votesEarned) from Nomination model where model.constituencyElection.constituency.tehsil.tehsilId = ? " +
				"and model.constituencyElection.constituency.electionScope.electionType.electionType = ? and model.party.partyId in ("+partyId+") group by model.constituencyElection.election.electionYear",params);
	}

	public List findCandidatesInfoByConstituencyAndElectionYear(Long constituencyId, String electionYear){
		Object[] params = {electionYear, constituencyId};
		return getHibernateTemplate().find( "select model.nomination.candidate.candidateId, model.nomination.candidate.lastname, model.nomination.candidateResult.rank " +
				"from Nomination model where model.constituencyElection.election.electionYear = ? " +
				"and model.constituencyElection.constituency.constituencyId = ?)", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndStateAndRank(Long electionID,Long stateId,Long rank,Long partyId){
		Object[] params = {electionID,stateId,rank,partyId};
		return getHibernateTemplate().find("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.constituencyElection.constituency.state.stateId =? and model.candidateResult.rank = ? and model.party.partyId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndStateAndDistrictAndRank(Long electionID,Long stateId,Long districtID,Long rank,Long partyId){
		Object[] params = {electionID,stateId,districtID,rank,partyId};
		return getHibernateTemplate().find("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.constituencyElection.constituency.state.stateId =? and model.constituencyElection.constituency.district.districtId =? and model.candidateResult.rank =? and model.party.partyId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndStateAndRank(Long electionID,Long stateId,Long rank,List<Long> partyIds){
		Query queryObject = getSession().createQuery("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.constituencyElection.constituency.state.stateId =? and model.candidateResult.rank = ? and model.party.partyId in (:partyIds)");
		queryObject.setParameter(0,electionID);
		queryObject.setParameter(1,stateId);
		queryObject.setParameter(2,rank);
		queryObject.setParameterList("partyIds", partyIds);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndStateAndDistrictAndRank(Long electionID,Long stateId,Long districtID,Long rank,List<Long> partyIds){
		Query queryObject = getSession().createQuery("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.constituencyElection.constituency.state.stateId =? and model.constituencyElection.constituency.district.districtId =? and model.candidateResult.rank = ? and model.party.partyId in (:partyIds)");
		queryObject.setParameter(0,electionID);
		queryObject.setParameter(1,stateId);
		queryObject.setParameter(2,districtID);
		queryObject.setParameter(3,rank);
		queryObject.setParameterList("partyIds", partyIds);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndStateAndNthRank(Long electionID,Long stateId,Long rank,List<Long> partyIds){
		Query queryObject = getSession().createQuery("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.constituencyElection.constituency.state.stateId =? and model.candidateResult.rank > ? and model.party.partyId in (:partyIds)");
		queryObject.setParameter(0,electionID);
		queryObject.setParameter(1,stateId);
		queryObject.setParameter(2,rank);
		queryObject.setParameterList("partyIds", partyIds);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndStateAndDistrictAndNthRank(Long electionID,Long stateId,Long districtID,Long rank,List<Long> partyIds){
		Query queryObject = getSession().createQuery("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.constituencyElection.constituency.state.stateId =? and model.constituencyElection.constituency.district.districtId =? and model.candidateResult.rank > ? and model.party.partyId in (:partyIds)");
		queryObject.setParameter(0,electionID);
		queryObject.setParameter(1,stateId);
		queryObject.setParameter(2,districtID);
		queryObject.setParameter(3,rank);
		queryObject.setParameterList("partyIds", partyIds);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndStateAndNthRank(Long electionID,Long stateId,Long rank,Long partyId){
		Object[] params = {electionID,stateId,rank,partyId};
		return getHibernateTemplate().find("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.constituencyElection.constituency.state.stateId =? and model.candidateResult.rank > ? and model.party.partyId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndStateAndDistrictAndNthRank(Long electionID,Long stateId,Long districtID,Long rank,Long partyId){
		Object[] params = {electionID,stateId,districtID,rank,partyId};
		return getHibernateTemplate().find("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.constituencyElection.constituency.state.stateId =? and model.constituencyElection.constituency.district.districtId =? and model.candidateResult.rank >? and model.party.partyId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionIdAndStateIdAndCountryIdAndRank(Long electionID,Long stateId,Long countryId,Long rank,Long partyId){
		Object[] params = {electionID,countryId,rank,partyId};
		return getHibernateTemplate().find("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.constituencyElection.constituency.countryId =? and model.candidateResult.rank = ? and model.party.partyId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionIdAndStateIdAndCountryIdAndNthRank(Long electionID,Long stateId,Long countryId,Long rank,Long partyId){
		Object[] params = {electionID,countryId,rank,partyId};
		return getHibernateTemplate().find("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.constituencyElection.constituency.countryId =? and model.candidateResult.rank > ? and model.party.partyId = ?", params);
	} 
	
	@SuppressWarnings("unchecked")
	public List findValidVotesOfAllCandiatesOfAMandalByElectionTypeMandalAndYear(String electionType, String electionYear, Long tehsilId){
		Object [] params = {electionType, electionYear, tehsilId};
		return getHibernateTemplate().find("select sum(model.candidateResult.votesEarned) from Nomination model where model.constituencyElection.constituency.electionScope.electionType.electionType = ? and " +
				"model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.tehsil.tehsilId = ? ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByElectionIdAndPartyId(final Long electionId,final Long partyId){
		 return ( List<Nomination> ) getHibernateTemplate().execute( new HibernateCallback() {
             public Object doInHibernate( Session session ) throws HibernateException, SQLException {
             		List<Nomination> constElectionResults = session.createCriteria(Nomination.class)
             							.createAlias("constituencyElection", "constElec")
             							.createAlias("party", "p")
             							.createAlias("constElec.election", "elec")
             							.createAlias("constElec.constituency", "const")
             							.createAlias("candidate", "cand")
             							.add(Expression.eq("elec.electionId", electionId))
             							.add(Expression.eq("p.partyId", partyId))
             							.addOrder(Order.asc("cand.lastname"))
             							.list();
             		 return constElectionResults;
             }
         });
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByElectionIdAndPartyIdStateIdAndDistrictId(final Long electionId,final Long partyId,final Long stateId,final Long districtId){
		 return ( List<Nomination> ) getHibernateTemplate().execute( new HibernateCallback() {
             public Object doInHibernate( Session session ) throws HibernateException, SQLException {
             		List<Nomination> constElectionResults = session.createCriteria(Nomination.class)
             							.createAlias("constituencyElection", "constElec")
             							.createAlias("party", "p")
             							.createAlias("constElec.election", "elec")
             							.createAlias("constElec.constituency", "const")
             							.createAlias("const.state", "state")
             							.createAlias("const.district", "district")
             							.createAlias("candidate", "cand")
             							.add(Expression.eq("state.stateId", stateId))
             							.add(Expression.eq("district.districtId", districtId))
             							.add(Expression.eq("elec.electionId", electionId))
             							.add(Expression.eq("p.partyId", partyId))
             							.addOrder(Order.asc("cand.lastname"))
             							.list();
             		 return constElectionResults;
             }
         });
	}
}
