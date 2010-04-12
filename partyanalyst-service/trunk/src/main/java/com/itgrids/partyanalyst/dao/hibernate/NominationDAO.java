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
				" model.constituencyElection.constituency.constituencyId , model.candidate.candidateId , model.party.partyFlag from Nomination model where model.constituencyElection.constituency.constituencyId in (  " + constituencyIds +
				") and model.constituencyElection.election.electionYear = ? " +
				" and model.candidateResult.rank = 1", electionYear);
	}
	
	public List findMPTCInfoByElectionTypeTehsilAndParty( Long tehsilID, Long partyId) {
		Object[] params = {tehsilID};
		return getHibernateTemplate().find("select model.party.shortName, model.constituencyElection.election.electionYear, " +
				" sum(model.constituencyElection.constituencyElectionResult.totalVotesPolled), " +
				" sum(model.constituencyElection.constituencyElectionResult.validVotes), "+
				" sum(model.candidateResult.votesEarned), model.candidate.lastname," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +
				" from Nomination model where model.constituencyElection.constituency.tehsil.tehsilId = ? " +
				" and model.party.partyId in ("+partyId+") group by model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType order by model.constituencyElection.constituency.electionScope.electionType.electionType",params);
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
	
	public List findValidVotesOfAllCandiatesOfAMandalByElectionTypeMandalAndYear(String electionType, String electionYear, Long tehsilId){
		Object [] params = {electionType, electionYear, tehsilId};
		return getHibernateTemplate().find("select sum(model.candidateResult.votesEarned) from Nomination model where model.constituencyElection.constituency.electionScope.electionType.electionType = ? and " +
				"model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.tehsil.tehsilId = ? ",params);
	}
	
	public List findLocalLeadersOfMandal(Long tehsilId){
		return getHibernateTemplate().find("select model.candidate, model.constituencyElection.election.electionScope.electionType.electionType," +
				"model.party.shortName, model.constituencyElection.election.electionYear, model.constituencyElection.constituency.name " +
				"from Nomination model where model.constituencyElection.constituency.tehsil.tehsilId = ?",tehsilId);
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
	
	public List getCandidateNPartyInfo(String constituencyIds,String electionType,Long rank)
	{
		Object[] params = {rank, electionType};
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName," +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId in ("+constituencyIds+") and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.electionYear) from Election nModel where nModel.electionScope.electionType.electionType = ?)",params);
		
	}
	
	public List getParliamentCandidateNPartyInfo(Long constituencyId,String electionType,Long rank)
	{
		Object[] params = {constituencyId, rank, electionType};
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName, " +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId = ? and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.electionYear) from Election nModel where nModel.electionScope.electionType.electionType = ?)",params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findConstituencyElectionByElectionIdAndPartyId(Long electionId,Long partyId){
		   Object[] params={electionId,partyId};
		   return getHibernateTemplate().find("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.party.partyId = ?",params);
    }
		
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findConstituencyElectionByElectionIdAndPartys(Long electionId,List<Long> partyIds){
		    Query queryObject = getSession().createQuery("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.party.partyId in (:partyIds)");
			queryObject.setParameter(0,electionId);
			queryObject.setParameterList("partyIds", partyIds);
			return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findConstituencyElectionByElectionIdAndDistrictIdAndPartys(Long electionId,Long districtId,List<Long> partyIds){
		Query queryObject = getSession().createQuery("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.constituencyElection.constituency.district.districtId =? and model.party.partyId in (:partyIds)");
		queryObject.setParameter(0,electionId);
		queryObject.setParameter(1,districtId);
		queryObject.setParameterList("partyIds", partyIds);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List findElectionDataByElectionId(Long electionId){
		return getHibernateTemplate().find("select model.party.partyId,model.party.longName," + 
				"count(model.candidateResult), model.party.partyFlag from Nomination model where " +
				"model.constituencyElection.election.electionId = ? and model.candidateResult.rank = 1 group by model.party.partyId",electionId);
	}
	
	@SuppressWarnings("unchecked")
	public List findElectionResultsForACandidateForAnElectionInAConstituency(Long constituencyId,Long electionId,Long partyId){
		Object[] params = {constituencyId,electionId,partyId};
		return getHibernateTemplate().find("select model.candidate.lastname,model.candidate.candidateId," + 
				" model.party.shortName,model.constituencyElection.constituency.name,model.constituencyElection.constituency.state.stateName," + 
				" model.constituencyElection.constituency.district.districtName,model.constituencyElection.election.electionYear," + 
				" model.constituencyElection.election.electionScope.electionType.electionType,model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate,model.candidateResult.rank from Nomination model where " +
				" model.constituencyElection.constituency.constituencyId = ? and model.constituencyElection.election.electionId = ? and model.party.partyId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findElectionResultsForAnElectionInAConstituencyWithoutSelectedParty(Long constituencyId,Long electionId,Long partyId){
		Object[] params = {constituencyId,electionId,partyId};
		return getHibernateTemplate().find("select model.candidate.lastname,model.candidate.candidateId," + 
				" model.party.shortName,model.constituencyElection.constituency.name,model.constituencyElection.constituency.state.stateName," + 
				" model.constituencyElection.constituency.district.districtName,model.constituencyElection.election.electionYear," + 
				" model.constituencyElection.election.electionScope.electionType.electionType,model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate,model.candidateResult.rank from Nomination model where " +
				" model.constituencyElection.constituency.constituencyId = ? and model.constituencyElection.election.electionId = ? and model.party.partyId != ?",params);

	}
	
	public List getAllPartiesOfElectionTypeInMandal(Long tehsilId) {
		Object[] params = {tehsilId};
		return getHibernateTemplate().find("select distinct model.party.partyId, model.party.shortName from Nomination model " +
				"where model.constituencyElection.constituency.tehsil.tehsilId = ? ", params);
	}

	public List getAllConstiteunciesInfoForPartyInTehsil(Long tehsilId,	Long partyId, Long electionId) {
		Object[] params = {tehsilId, electionId, partyId};
		return getHibernateTemplate().find("select model.candidate.candidateId, model.candidate.firstname, model.candidate.middlename, model.candidate.lastname, " +
				"model.constituencyElection.constituency.constituencyId, model.constituencyElection.constituency.name, model.constituencyElection.constituencyElectionResult.totalVotes, " +
				"model.constituencyElection.constituencyElectionResult.validVotes, model.candidateResult.votesEarned, model.candidateResult.rank from Nomination model " +
				"where model.constituencyElection.constituency.tehsil.tehsilId = ? and model.constituencyElection.election.electionId = ? and model.party.partyId = ? " +
				"group by model.constituencyElection.constituency.constituencyId order by model.candidateResult.rank" ,params);
	}
	
	public List findAllMptcAndZptcElectionsInfoInMandal(Long tehsilId) {
		return getHibernateTemplate().find("select model.constituencyElection.election.electionId," +
				" model.candidateResult.rank, model.party.partyId, model.party.shortName, " +
				" sum(model.candidateResult.votesEarned), model.constituencyElection.election.electionYear,"+
				" model.constituencyElection.election.electionScope.electionType.electionType," +
				" model.constituencyElection.constituencyElectionResult.validVotes, " +
				" model.constituencyElection.constituencyElectionResult.totalVotes, " +
				" model.constituencyElection.constituency.name, model.candidate.lastname, model.candidate.candidateId, model.candidateResult.votesPercengate, model.constituencyElection.constituency.constituencyId " +
				"from Nomination model where model.constituencyElection.constituency.tehsil.tehsilId =? " +
				"group by model.nominationId, model.party.partyId, model.constituencyElection.election.electionId order by " +
				"model.constituencyElection.election.electionYear desc, " +
				"model.constituencyElection.election.electionScope.electionType.electionType, " +
				"model.party.partyId, model.candidateResult.rank", tehsilId);
	}
	@SuppressWarnings("unchecked")
	public List findAllCandidatesForAnElectionByElectionYear(Long constituencyId) {
		return getHibernateTemplate().find("select model.candidate.lastname,model.constituencyElection.constituency.name," +
				" model.candidateResult.votesEarned,model.candidateResult.rank,model.party.shortName," +
				" model.constituencyElection.election.electionYear from Nomination model where" +
				" model.constituencyElection.election.electionYear in (select Model.electionYear from Election Model) and model.constituencyElection.constituency.constituencyId = ? ",constituencyId); 
	}

	
	@SuppressWarnings("unchecked")
	public List findAllCandidatesForAnElectionByElectionYearByDistrictId(Long districtId,String electionType) {
		Object[] params = {districtId,electionType};
		return getHibernateTemplate().find("select model.candidate.lastname,model.constituencyElection.constituency.name," +
				" model.candidateResult.votesEarned,model.candidateResult.rank,model.party.shortName," +
				" model.constituencyElection.election.electionYear from Nomination model where" +
				" model.constituencyElection.election.electionYear in (select Model.electionYear from Election Model) and model.constituencyElection.constituency.district.districtId = ? " +
				" and model.constituencyElection.constituency.electionScope.electionType.electionType = ? order by model.constituencyElection.election.electionYear ",params); 
	}
		

	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List getConstituencyInfoByConstituencyIdElectionYearAndElectionType(
			Long constituencyId, String electionYear, String electionType) {
		Object[] params = {constituencyId, electionYear,electionType};	
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.name," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.constituency.district.districtId," +
				" model.constituencyElection.constituency.district.districtName," +
				" model.constituencyElection.constituency.district.state.stateId," +
				" model.constituencyElection.constituency.district.state.stateName" +			
				" from Nomination model where model.constituencyElection.constituency.constituencyId  = ? " +
				" and model.constituencyElection.election.electionYear = ? " +
				" and model.constituencyElection.constituency.electionScope.electionType.electionType = ? order by model.candidateResult.rank",params);			
	}
	
	public List getPartysInfoForAParticularElectionYear(String electionType,String electionYear,Long districtId){
		Object[] params = {electionType,electionYear,districtId};
		return getHibernateTemplate().find("select model.party.shortName,count(model.party.partyId),sum(model.candidateResult.votesEarned)," +
				" sum(model.constituencyElection.constituencyElectionResult.validVotes)" +
				" from Nomination model where model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.district.districtId = ? group by model.party.partyId",params);
	}
	
	public List getPartysWinningCandidateInfoForAParticularElectionYear(String electionType,String electionYear,Long rank,Long districtId){
		Object[] params = {electionType,electionYear,rank,districtId};
		return getHibernateTemplate().find("select model.party.shortName,count(model.candidateResult.rank)" +
				" from Nomination model where model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituencyElection.election.electionYear = ? and model.candidateResult.rank = ? " +
				" and model.constituencyElection.constituency.district.districtId = ? group by model.party.shortName ",params);
	}
	
	public List getAllCandidatesByElectionTypes(String electionTypes){
		return getHibernateTemplate().find("select distinct model.candidate.candidateId, model.candidate.firstname, model.candidate.middlename, model.candidate.lastname from " +
				"Nomination model where model.constituencyElection.election.electionScope.electionType.electionType in ( "+electionTypes+" )");
	}

	
	
	
	@SuppressWarnings("unchecked")
	public List findAllZPTCsInaDistrict(Long districtId,String electionTypes,Long rank,String electionYear) {
		Object[] params = {districtId,electionTypes,rank,electionYear};
		return getHibernateTemplate().find("select model.party.partyFlag, model.constituencyElection.election.electionYear," +
				" upper(model.candidate.lastname),upper(model.constituencyElection.constituency.tehsil.tehsilName)," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.candidateResult.votesEarned," +
				" model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.rank,model.constituencyElection.constituency.constituencyId, " +
				" model.candidateResult.votesPercengate,model.party.longName" +
				" from Nomination model where model.constituencyElection.constituency.district.districtId = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
				" and model.candidateResult.rank  = ? and model.constituencyElection.election.electionYear = ? ",params);
		}
	
	@SuppressWarnings("unchecked")
	public List findAllMPTCsInaDistrict(Long districtId,String electionType,Long rank,String electionYear) {
		Object[] params = {districtId,electionType,rank,electionYear};
			return getHibernateTemplate().find("select model.party.partyFlag, model.constituencyElection.election.electionYear," +
					" upper(model.candidate.lastname),upper(model.constituencyElection.constituency.tehsil.tehsilName)," +
					" model.constituencyElection.constituency.electionScope.electionType.electionType," +
					" model.constituencyElection.constituency.tehsil.tehsilId," +
					" model.candidateResult.votesPercengate,model.party.longName" +
					" model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.rank,model.constituencyElection.constituency.constituencyId, " +
					" model.candidateResult.votesPercengate,model.party.longName" +
					" from Nomination model where model.constituencyElection.constituency.tehsil.district.districtId = ? and " +
					" model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
					" and model.candidateResult.rank  = ? and model.constituencyElection.election.electionYear = ? ",params);
		}
	
	@SuppressWarnings("unchecked")
	public List findAllZptcCandidatesInaDistrict(Long districtId,String electionTypes,String electionYear) {
		Object[] params = {districtId,electionTypes,electionYear};
		return getHibernateTemplate().find("select model.party.partyFlag, model.constituencyElection.election.electionYear," +
				" upper(model.candidate.lastname),upper(model.constituencyElection.constituency.tehsil.tehsilName)," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.candidateResult.votesEarned," +
				" model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.rank,model.constituencyElection.constituency.constituencyId," +
				" model.candidateResult.votesPercengate,model.party.longName" +
				" from Nomination model where model.constituencyElection.constituency.district.districtId = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ? " +
				" and model.constituencyElection.election.electionYear = ? order by model.constituencyElection.constituency.constituencyId",params);
		}
	
	@SuppressWarnings("unchecked")
	public List findAllMptcCandidatesInaDistrict(Long districtId,String electionType,String electionYear) {
		Object[] params = {districtId,electionType,electionYear};
			return getHibernateTemplate().find("select model.party.partyFlag, model.constituencyElection.election.electionYear," +
					" upper(model.candidate.lastname),upper(model.constituencyElection.constituency.tehsil.tehsilName)," +
					" model.constituencyElection.constituency.electionScope.electionType.electionType," +
					" model.constituencyElection.constituency.tehsil.tehsilId," +
					" model.candidateResult.votesEarned," +
					" model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.rank,model.constituencyElection.constituency.constituencyId, " +
					" model.candidateResult.votesPercengate,model.party.longName" +
					" from Nomination model where model.constituencyElection.constituency.tehsil.district.districtId = ? and " +
					" model.constituencyElection.constituency.electionScope.electionType.electionType = ? " +
					"  and model.constituencyElection.election.electionYear = ? order by model.constituencyElection.constituency.constituencyId",params);
		}
	
	@SuppressWarnings("unchecked")
	public List findAllZptcPartysInaDistrict(Long districtId,String electionTypes, String electionYear,Long partyId,Long rank) {
		Object[] params = {districtId,electionTypes,electionYear,partyId,rank};
		return getHibernateTemplate().find("select model.party.partyFlag, model.constituencyElection.election.electionYear," +
				" upper(model.candidate.lastname),upper(model.constituencyElection.constituency.tehsil.tehsilName)," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.candidateResult.votesEarned," +
				" model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.rank,model.constituencyElection.constituency.constituencyId, " +
				" model.candidateResult.votesPercengate,model.party.longName" +
				" from Nomination model where model.constituencyElection.constituency.district.districtId = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ? " +
				" and model.constituencyElection.election.electionYear = ? and model.party.partyId = ? " +
				" and model.candidateResult.rank  != ? order by model.constituencyElection.constituency.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findAllMptcPartysInaDistrict(Long districtId,String electionType, String electionYear,Long partyId,Long rank) {
		Object[] params = {districtId,electionType,electionYear,partyId,rank};
		return getHibernateTemplate().find("select model.party.partyFlag, model.constituencyElection.election.electionYear," +
				" upper(model.candidate.lastname),upper(model.constituencyElection.constituency.tehsil.tehsilName)," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.candidateResult.votesEarned," +
				" model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.rank,model.constituencyElection.constituency.constituencyId, " +
				" model.candidateResult.votesPercengate,model.party.longName" +
				" from Nomination model where model.constituencyElection.constituency.tehsil.district.districtId = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ? " +
				" and model.constituencyElection.election.electionYear = ? and model.party.partyId = ? " +
				" and model.candidateResult.rank  != ? order by model.constituencyElection.constituency.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findAllZptcPartysWinnerInaDistrict(Long districtId,String electionTypes, String electionYear,Long partyId,Long rank) {
		Object[] params = {districtId,electionTypes,electionYear,partyId,rank};
		return getHibernateTemplate().find("select model.party.partyFlag, model.constituencyElection.election.electionYear," +
				" upper(model.candidate.lastname),upper(model.constituencyElection.constituency.tehsil.tehsilName)," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.candidateResult.votesEarned," +
				" model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.rank,model.constituencyElection.constituency.constituencyId, " +
				" model.candidateResult.votesPercengate,model.party.longName" +
				" from Nomination model where model.constituencyElection.constituency.district.districtId = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ? " +
				" and model.constituencyElection.election.electionYear = ? and model.party.partyId = ?" +
				" and model.candidateResult.rank  = ? order by model.constituencyElection.constituency.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findAllMptcPartysWinnerInaDistrict(Long districtId,String electionType, String electionYear,Long partyId,Long rank) {
		Object[] params = {districtId,electionType,electionYear,partyId,rank};
		return getHibernateTemplate().find("select model.party.partyFlag, model.constituencyElection.election.electionYear," +
				" upper(model.candidate.lastname),upper(model.constituencyElection.constituency.tehsil.tehsilName)," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.candidateResult.votesEarned," +
				" model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.rank,model.constituencyElection.constituency.constituencyId, " +
				" model.candidateResult.votesPercengate,model.party.longName" +
				" from Nomination model where model.constituencyElection.constituency.tehsil.district.districtId = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ? " +
				" and model.constituencyElection.election.electionYear = ? and model.party.partyId = ?" +
				" and model.candidateResult.rank  = ? order by model.constituencyElection.constituency.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getAllPartysForAParticularElectionYear(Long districtId,String electionType,String electionYear){
		Object[] params = {districtId,electionType,electionYear};
		return getHibernateTemplate().find("select distinct model.party.partyId,model.party.shortName"+
				" from Nomination model where model.constituencyElection.constituency.district.districtId = ? and" +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituencyElection.election.electionYear = ? order by model.party.shortName",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getPartyIdAndShortNameForThatParticipatedInAElection(Long electionId){
		return getHibernateTemplate().find("select distinct model.party.partyId,model.party.shortName from Nomination model"+
				" where model.constituencyElection.election.electionId = ?",electionId);
	}

	@SuppressWarnings("unchecked")
	public List getAllAssemblyOrParliamentCandidatesInAStateForAnElectionYear(Long stateId,Long electionType,Long electionYear){
		Object[] params = {stateId,electionType,electionYear};
		return getHibernateTemplate().find("select model.candidate.lastname,model.constituencyElection.constituency.name," +
				" model.candidateResult.votesEarned,model.candidateResult.rank,model.party.shortName," +
				" model.constituencyElection.election.electionYear from Nomination model where" +
				" model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.state.stateId = ? and" +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ?",params); 	
	}
	
	
	//District Level Access
	
		
	
	//Constituency Level Access
	
	
	@SuppressWarnings("unchecked")
	public List getCandidatesInfoForTheGivenConstituencyBasedOnRank(String constituencyId,String electionYear,String electionType,Long rank){
		Object[] params = {electionYear,electionType,rank};		
	return getHibernateTemplate().find("select model.candidate.candidateId," +
			" model.candidate.lastname," +
			" model.candidateResult.votesEarned," +
			" model.candidateResult.votesPercengate," +
			" model.candidateResult.rank," +
			" model.party.partyId," +  
			" model.party.partyFlag," +
			" model.party.longName," +
			" model.party.shortName," +
			" model.constituencyElection.constituency.constituencyId," +
			" model.constituencyElection.constituency.name," +	
			" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +		
			" from Nomination model where model.constituencyElection.constituency.constituencyId in (  " + constituencyId +
			" ) and model.constituencyElection.election.electionYear = ? " +
			" and model.constituencyElection.constituency.electionScope.electionType.electionType = ? and model.candidateResult.rank = ? order by model.candidateResult.rank",params);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List getCandidatesInfoForTheGivenConstituency(String constituencyId,String electionYear,String electionType)	{
		Object[] params = {electionYear,electionType};		
	return getHibernateTemplate().find("select model.candidate.candidateId," +
			" model.candidate.lastname," +
			" model.candidateResult.votesEarned," +
			" model.candidateResult.votesPercengate," +
			" model.candidateResult.rank," +
			" model.party.partyId," +  
			" model.party.partyFlag," +
			" model.party.longName," +
			" model.party.shortName," +
			" model.constituencyElection.constituency.constituencyId," +
			" model.constituencyElection.constituency.name," +	
			" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +	
			" from Nomination model where model.constituencyElection.constituency.constituencyId in (  " + constituencyId +
			" ) and model.constituencyElection.election.electionYear = ? " +
			" and model.constituencyElection.constituency.electionScope.electionType.electionType = ? order by model.candidateResult.rank",params);		
	}
	
	@SuppressWarnings("unchecked")
	public List getCandidatesInfoForTheGivenConstituencyBasedOnRankAndPartyId(String constituencyId,String electionYear,String electionType,Long rank,Long partyId){
		Object[] params = {electionYear,electionType,rank,partyId};		
	return getHibernateTemplate().find("select model.candidate.candidateId," +
			" model.candidate.lastname," +
			" model.candidateResult.votesEarned," +
			" model.candidateResult.votesPercengate," +
			" model.candidateResult.rank," +
			" model.party.partyId," +  
			" model.party.partyFlag," +
			" model.party.longName," +
			" model.party.shortName," +
			" model.constituencyElection.constituency.constituencyId," +
			" model.constituencyElection.constituency.name," +	
			" model.constituencyElection.election.electionYear," +
			" model.constituencyElection.constituency.electionScope.electionType.electionType" +	
			" from Nomination model where model.constituencyElection.constituency.constituencyId in (  " + constituencyId +
			" ) and model.constituencyElection.election.electionYear = ? " +
			" and model.constituencyElection.constituency.electionScope.electionType.electionType = ? and model.candidateResult.rank = ? and model.party.partyId = ? order by model.candidateResult.rank",params);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List getCandidatesInfoForTheGivenConstituencyByPartyId(String constituencyId,String electionYear,String electionType,Long partyId)	{
		Object[] params = {electionYear,electionType,partyId};		
	return getHibernateTemplate().find("select model.candidate.candidateId," +
			" model.candidate.lastname," +
			" model.candidateResult.votesEarned," +
			" model.candidateResult.votesPercengate," +
			" model.candidateResult.rank," +
			" model.party.partyId," +  
			" model.party.partyFlag," +
			" model.party.longName," +
			" model.party.shortName," +
			" model.constituencyElection.constituency.constituencyId," +
			" model.constituencyElection.constituency.name," +	
			" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +
			" from Nomination model where model.constituencyElection.constituency.constituencyId in (  " + constituencyId +
			" ) and model.constituencyElection.election.electionYear = ? " +
			" and model.constituencyElection.constituency.electionScope.electionType.electionType = ? and model.party.partyId = ? order by model.candidateResult.rank ",params);		
	}
	
	
	

	
	//State Level Access
	
	
	@SuppressWarnings("unchecked")
	public List findAllCandidatesForAnElectionBytheElectionYearByRank(String electionYear,Long stateId,String electionType,Long rank) {
		Object[] params = {electionYear, stateId,electionType,rank};
		return getHibernateTemplate().find("select model.candidate.candidateId," +
				" model.candidate.lastname," +
				" model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate," +
				" model.candidateResult.rank," +
				" model.party.partyId," +  
				" model.party.partyFlag," +
				" model.party.longName," +
				" model.party.shortName," +
				" model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.name," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +
				" from Nomination model where" +
				" model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.state.stateId = ? and" +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ? and model.candidateResult.rank = ?",params); 
	}
	
	@SuppressWarnings("unchecked")
	public List findAllCandidatesForAnElectionBytheElectionYearByRankForAParty(String electionYear,Long stateId,String electionType,Long rank,Long partyId) {
		Object[] params = {electionYear, stateId,electionType,rank,partyId};
		return getHibernateTemplate().find("select model.candidate.candidateId," +
				" model.candidate.lastname," +
				" model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate," +
				" model.candidateResult.rank," +
				" model.party.partyId," +  
				" model.party.partyFlag," +
				" model.party.longName," +
				" model.party.shortName," +
				" model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.name," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +
				" from Nomination model where" +
				" model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.state.stateId = ? and" +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ? and model.candidateResult.rank = ?" +
				"  and model.party.partyId = ?",params); 
	}
	
	
	@SuppressWarnings("unchecked")
	public List findAllAssemblyCandidatesForAnElectionBytheElectionYear(String electionYear,Long stateId,String electionType,Long partyId) {
		Object[] params = {electionYear, stateId,electionType,partyId};
		return getHibernateTemplate().find("select model.candidate.candidateId," +
				" model.candidate.lastname," +
				" model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate," +
				" model.candidateResult.rank," +
				" model.party.partyId," +  
				" model.party.partyFlag," +
				" model.party.longName," +
				" model.party.shortName," +
				" model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.name," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +	
				" from Nomination model where" +
				" model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.state.stateId = ? and" +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ? and model.party.partyId = ?",params); 
	}
	
	
	@SuppressWarnings("unchecked")
	public List findAllCandidatesForAnElectionBytheElectionYear(String electionYear,Long stateId,String electionType) {
		Object[] params = {electionYear, stateId,electionType};
		return getHibernateTemplate().find("select model.candidate.candidateId," +
				" model.candidate.lastname," +
				" model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate," +
				" model.candidateResult.rank," +
				" model.party.partyId," +  
				" model.party.partyFlag," +
				" model.party.longName," +
				" model.party.shortName," +
				" model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.name," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +	
				" from Nomination model where" +
				" model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.state.stateId = ? and" +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ?",params); 
	}
	
	
	
	
	
	//Country Level Access
	
	@SuppressWarnings("unchecked")
	public List findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(String electionYear,Long countryId,String electionType,Long rank) {
		Object[] params = {electionYear, countryId,electionType,rank};
		return getHibernateTemplate().find("select model.candidate.candidateId," +
				" model.candidate.lastname," +
				" model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate," +
				" model.candidateResult.rank," +
				" model.party.partyId," +  
				" model.party.partyFlag," +
				" model.party.longName," +
				" model.party.shortName," +
				" model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.name," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +
				" from Nomination model where" +
				" model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.state.country.countryId = ? and" +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ? and model.candidateResult.rank = ?",params); 
	}
	
	
	@SuppressWarnings("unchecked")
	public List findAllCandidatesForAnElectionBytheElectionYearByCountryId(String electionYear,Long countryId,String electionType) {
		Object[] params = {electionYear, countryId,electionType};
		return getHibernateTemplate().find("select model.candidate.candidateId," +
				" model.candidate.lastname," +
				" model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate," +
				" model.candidateResult.rank," +
				" model.party.partyId," +  
				" model.party.partyFlag," +
				" model.party.longName," +
				" model.party.shortName," +
				" model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.name," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +
				" from Nomination model where" +
				" model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.state.country.countryId = ? and" +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ?",params); 
	}
	
	@SuppressWarnings("unchecked")
	public List findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRankForAParty(String electionYear,Long countryId,String electionType,Long partyId,Long rank) {
		Object[] params = {electionYear, countryId,electionType,partyId,rank};
		return getHibernateTemplate().find("select model.candidate.candidateId," +
				" model.candidate.lastname," +
				" model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate," +
				" model.candidateResult.rank," +
				" model.party.partyId," +  
				" model.party.partyFlag," +
				" model.party.longName," +
				" model.party.shortName," +
				" model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.name," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +	
				" from Nomination model where" +
				" model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.state.country.countryId = ? and" +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ? and model.party.partyId = ?  and model.candidateResult.rank = ?",params);  
	}
	
	@SuppressWarnings("unchecked")
	public List findAllCandidatesForAnElectionBytheElectionYearByCountryIdForAParty(String electionYear,Long countryId,String electionType,Long partyId) {
		Object[] params = {electionYear, countryId,electionType,partyId};
		return getHibernateTemplate().find("select model.candidate.candidateId," +
				" model.candidate.lastname," +
				" model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate," +
				" model.candidateResult.rank," +
				" model.party.partyId," +  
				" model.party.partyFlag," +
				" model.party.longName," +
				" model.party.shortName," +
				" model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.name," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +	
				" from Nomination model where" +
				" model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.state.country.countryId = ? and" +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ? and model.party.partyId = ?",params); 
	}
	
	public List getAllPartiesForAnElectionYear(String electionYear,String electionType){
		Object[] params = {electionYear,electionType};
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName from Nomination model" +
				" where model.constituencyElection.election.electionYear = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ?",params);
	}
	
	
	//ZPTCOrMPTCs code starts here
	
	@SuppressWarnings("unchecked")
	public List findAllZPTCsOrMPTCsInaState(Long stateId,String electionType,String electionYear) {
		Object[] params = {stateId,electionType,electionYear};
		return getHibernateTemplate().find("select model.candidate.candidateId," +
				" model.candidate.lastname," +
				" model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate," +
				" model.candidateResult.rank," +
				" model.party.partyId," +  
				" model.party.partyFlag," +
				" model.party.longName," +
				" model.party.shortName," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.constituencyElection.constituency.tehsil.tehsilName," +		
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +	
				" from Nomination model where model.constituencyElection.constituency.state.stateId = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituencyElection.election.electionYear = ? ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findAllZPTCsOrMPTCsInaStateForAParty(Long stateId,String electionType,String electionYear,Long partyId) {
		Object[] params = {stateId,electionType,electionYear,partyId};
		return getHibernateTemplate().find("select model.candidate.candidateId," +
				" model.candidate.lastname," +
				" model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate," +
				" model.candidateResult.rank," +
				" model.party.partyId," +  
				" model.party.partyFlag," +
				" model.party.longName," +
				" model.party.shortName," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.constituencyElection.constituency.tehsil.tehsilName," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +	
				" from Nomination model where model.constituencyElection.constituency.state.stateId = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituencyElection.election.electionYear = ? and model.party.partyId = ? ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findAllZPTCsOrMPTCsInaStateForAPartyByRank(Long stateId,String electionType,String electionYear,Long partyId,Long rank) {
		Object[] params = {stateId,electionType,electionYear,partyId,rank};
		return getHibernateTemplate().find("select model.candidate.candidateId," +
				" model.candidate.lastname," +
				" model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate," +
				" model.candidateResult.rank," +
				" model.party.partyId," +  
				" model.party.partyFlag," +
				" model.party.longName," +
				" model.party.shortName," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.constituencyElection.constituency.tehsil.tehsilName," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +	
				" from Nomination model where model.constituencyElection.constituency.state.stateId = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituencyElection.election.electionYear = ? and model.party.partyId = ?  and model.candidateResult.rank =?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findAllZPTCsOrMPTCsInaStateByRank(Long stateId,String electionType,String electionYear,Long rank) {
		Object[] params = {stateId,electionType,electionYear,rank};
		return getHibernateTemplate().find("select model.candidate.candidateId," +
				" model.candidate.lastname," +
				" model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate," +
				" model.candidateResult.rank," +
				" model.party.partyId," +  
				" model.party.partyFlag," +
				" model.party.longName," +
				" model.party.shortName," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.constituencyElection.constituency.tehsil.tehsilName," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +	
				" from Nomination model where model.constituencyElection.constituency.state.stateId = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituencyElection.election.electionYear = ? and model.candidateResult.rank =?",params);
		}
	
	public List getAllPartyDetailsForAllElectionYearsInADistrict(Long districtId){
		return getHibernateTemplate().find("select model.constituencyElection.election.electionYear," +
				" model.party.shortName,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.election.electionId,model.party.partyId," +
				" model.constituencyElection.constituency.state.stateId from " +
				" Nomination model where model.constituencyElection.constituency.district.districtId = ? group by" +
				" model.party.partyId,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.election.electionYear order by " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType",districtId);
	}
	
	public List getTotalVotesPolledInADistrictForAllElectionYears(Long districtId){
			return getHibernateTemplate().find("select model.constituencyElection.election.electionYear," +				
					" model.constituencyElection.constituency.electionScope.electionType.electionType," +
					" sum(model.constituencyElection.constituencyElectionResult.validVotes) from" +
					" Nomination model where model.constituencyElection.constituency.district.districtId = ? group by" +
					" model.constituencyElection.constituency.electionScope.electionType.electionType," +
					" model.constituencyElection.election.electionYear order by " +
					" model.constituencyElection.constituency.electionScope.electionType.electionType",districtId);

	}

}
