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
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.utils.IConstants;

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
	public List findByConstituencyElection(Long constituencyElectionID){
		return getHibernateTemplate().find( "select model.party.shortName, model.party.longName, model.candidateResult.votesEarned, model.candidateResult.rank  from Nomination model where model.constituencyElection.constiElecId = ?",constituencyElectionID);
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
		return getHibernateTemplate().find( "select model.party.partyId, model.party.shortName, model.candidateResult.votesEarned, " +
				"model.candidateResult.rank from Nomination model where model.constituencyElection.election.electionYear = ? " +
				"and model.constituencyElection.constituency.constituencyId = ? order by model.nominationId)", params);
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
	public List<Nomination> findByElectionIdAndPartyIdStateIdAndDistrictId(final Long electionId,final Long partyId,final Long districtId){
		 return ( List<Nomination> ) getHibernateTemplate().execute( new HibernateCallback() {
             public Object doInHibernate( Session session ) throws HibernateException, SQLException {
             		List<Nomination> constElectionResults = session.createCriteria(Nomination.class)
             							.createAlias("constituencyElection", "constElec")
             							.createAlias("party", "p")
             							.createAlias("constElec.election", "elec")
             							.createAlias("constElec.constituency", "const")
             							.createAlias("const.district", "district")
             							.createAlias("candidate", "cand")
             							.add(Expression.eq("district.districtId", districtId))
             							.add(Expression.eq("elec.electionId", electionId))
             							.add(Expression.eq("p.partyId", partyId))
             							.addOrder(Order.asc("cand.lastname"))
             							.list();
             		 return constElectionResults;
             }
         });
	}
	
	@SuppressWarnings("unchecked")
	public List getCandidateNPartyInfoForParliament(String constituencyIds,String electionType,Long rank, String electionSubtype)
	{
		Object[] params = {rank, electionType, electionSubtype};
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName," +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId in ("+constituencyIds+") and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.electionYear) from Election nModel where " +
				"nModel.electionScope.electionType.electionType = ? and nModel.elecSubtype = ?)",params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List getCandidateNPartyInfo(String constituencyIds,String electionType,Long rank, String electionSubtype,Long stateId)
	{
		Object[] params = {rank, electionType, electionSubtype,stateId};
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName," +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId in ("+constituencyIds+") and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.electionYear) from Election nModel where " +
				"nModel.electionScope.electionType.electionType = ? and nModel.elecSubtype = ? and nModel.electionScope.state.stateId = ?)",params);
		
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
	public List getParliamentCandidateNPartyInfo(Long constituencyId,String electionType,Long rank,String elecSubType)
	{
		Object[] params = {constituencyId, rank, electionType,elecSubType};
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName, " +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId = ? and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.electionYear) from Election nModel where nModel.electionScope.electionType.electionType = ? and nModel.elecSubtype = ?)",params);
		
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
	public List<ConstituencyElection> findConstituencyElectionByElectionIdAndStateAndPartys(Long electionId, Long stateId, List<Long> partyIds){
		Query queryObject = getSession().createQuery("select distinct constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.constituencyElection.constituency.state.stateId =? and model.party.partyId in (:partyIds)");
		queryObject.setParameter(0,electionId);
		queryObject.setParameter(1,stateId);
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
	
	public List findPCElectionResultsForACandidateForAnElectionInAConstituency(
			Long constituencyId, Long electionId, Long partyId) {
		Object[] params = {constituencyId,electionId,partyId};
		return getHibernateTemplate().find("select model.candidate.lastname,model.candidate.candidateId," + 
				" model.party.shortName,model.constituencyElection.constituency.name,model.constituencyElection.constituency.state.stateName," + 
				" model.constituencyElection.constituency.state.stateName,model.constituencyElection.election.electionYear," + 
				" model.constituencyElection.election.electionScope.electionType.electionType,model.candidateResult.votesEarned," +
				" model.candidateResult.votesPercengate,model.candidateResult.rank from Nomination model where " +
				" model.constituencyElection.constituency.constituencyId = ? and model.constituencyElection.election.electionId = ? and model.party.partyId = ?",params);
	}
	
	public List findPCElectionResultsForAnElectionInAConstituencyWithoutSelectedParty(
			Long constituencyId, Long electionId, Long partyId) {
		Object[] params = {constituencyId,electionId,partyId};
		return getHibernateTemplate().find("select model.candidate.lastname,model.candidate.candidateId," + 
				" model.party.shortName,model.constituencyElection.constituency.name,model.constituencyElection.constituency.state.stateName," + 
				" model.constituencyElection.constituency.state.stateName,model.constituencyElection.election.electionYear," + 
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
		return getHibernateTemplate().find("select model.constituencyElection.election.electionId," +//0
				" model.candidateResult.rank, model.party.partyId, model.party.shortName, " +//1,2,3
				" sum(model.candidateResult.votesEarned), model.constituencyElection.election.electionYear,"+//4,5
				" model.constituencyElection.election.electionScope.electionType.electionType," +//6
				" model.constituencyElection.constituencyElectionResult.validVotes, " +//7
				" model.constituencyElection.constituencyElectionResult.totalVotes, " +//8
				" model.constituencyElection.constituency.name, model.candidate.lastname, model.candidate.candidateId, " +//9,10,11
				"model.candidateResult.votesPercengate, model.constituencyElection.constituency.constituencyId " +//12,13
				"from Nomination model where model.constituencyElection.constituency.tehsil.tehsilId =? " +
				"group by model.nominationId, model.party.partyId, model.constituencyElection.election.electionId order by " +
				"model.constituencyElection.election.electionYear desc, " +
				"model.constituencyElection.election.electionScope.electionType.electionType, " +
				"model.party.partyId, model.candidateResult.rank", tehsilId);
	}
	
	public List findSeatsWonByAPartyInMandalForAnElectionYear(String tehsilIds,String electionYear,String electionType,Long rank) {
		Object[] params = {electionYear, electionType,rank};
		return getHibernateTemplate().find("select model.party.shortName,count(model.candidateResult.rank)" +
				" from Nomination model where model.constituencyElection.constituency.tehsil.tehsilId in (  " + tehsilIds +
				" ) and model.constituencyElection.election.electionYear = ? and " +
				" model.constituencyElection.election.electionScope.electionType.electionType = ? and model.candidateResult.rank = ? " +
				" group by model.party.shortName ", params);
	}
	
	public List getPartysWinningCandidateInfoForAParticularElectionYear(String electionType,String electionYear,Long rank,Long districtId){
		Object[] params = {electionType,electionYear,rank,districtId};
		return getHibernateTemplate().find("select model.party.shortName,count(model.candidateResult.rank)" +
				" from Nomination model where model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituencyElection.election.electionYear = ? and model.candidateResult.rank = ? " +
				" and model.constituencyElection.constituency.district.districtId = ? group by model.party.shortName ",params);
	}
	
	public List findAllMptcAndZptcElectionsInfoInMandalForAnElectionYear(Long tehsilId,Long electionYear,Long electionType) {
		Object[] params = {tehsilId, electionYear, electionType};
		return getHibernateTemplate().find("select model.party.partyId, model.party.shortName,model.party.partyFlag, " +
				" sum(model.candidateResult.votesEarned),count(model.candidateResult.rank)"+
				" model.constituencyElection.constituencyElectionResult.validVotes, " +
				" model.constituencyElection.constituencyElectionResult.totalVotes, " +
				" model.constituencyElection.constituency.name, model.candidateResult.votesPercengate, " +
				" model.constituencyElection.constituency.constituencyId " +
				" from Nomination model where model.constituencyElection.constituency.tehsil.tehsilId =?," +
				" model.constituencyElection.election.electionYear = ?," +
				" model.constituencyElection.election.electionScope.electionType.electionType = ?" +
				" group by model.nominationId, model.party.partyId, model.constituencyElection.election.electionId order by " +
				" model.constituencyElection.election.electionYear desc, " +
				" model.constituencyElection.election.electionScope.electionType.electionType, " +
				" model.party.partyId, model.candidateResult.rank", params);
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
	
	public List getPartysInfoForATehsilForAParticularElectionYear(String electionType,String tehsilIds,String electionYear){
		Object[] params = {electionType,electionYear};
		return getHibernateTemplate().find("select model.party.shortName,count(model.party.partyId),sum(model.candidateResult.votesEarned)," +
				" sum(model.constituencyElection.constituencyElectionResult.validVotes),model.party.partyId" +
				" from Nomination model where model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituencyElection.constituency.tehsil.tehsilId  in (  " + tehsilIds +
				" ) and model.constituencyElection.election.electionYear = ? group by model.party.partyId",params);
	}
	
	public List getPartysInfoForAParticularElectionYear(String electionType,String electionYear,Long districtId){
		Object[] params = {electionType,electionYear,districtId};
		return getHibernateTemplate().find("select model.party.shortName,count(model.party.partyId),sum(model.candidateResult.votesEarned)," +
				" sum(model.constituencyElection.constituencyElectionResult.validVotes)" +
				" from Nomination model where model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.district.districtId = ? group by model.party.partyId",params);
	}
	
	public List getAllCandidatesByElectionTypes(String electionTypes){
		return getHibernateTemplate().find("select distinct model.candidate.candidateId, model.candidate.firstname, model.candidate.middlename, model.candidate.lastname from " +
				"Nomination model where model.constituencyElection.election.electionScope.electionType.electionType in ( "+electionTypes+" )");
	}

	//MPTC and ZPTC for district DAO's Starts from here...
	
	@SuppressWarnings("unchecked")
	public List findAllZPTCsInaDistrict(Long districtId,String electionTypes,Long rank,String electionYear) {
		Object[] params = {districtId,electionTypes,rank,electionYear};
		return getHibernateTemplate().find("select model.party.partyFlag, model.constituencyElection.election.electionYear," +
				" upper(model.candidate.lastname),upper(model.constituencyElection.constituency.tehsil.tehsilName)," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.candidateResult.votesEarned," +
				" model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.rank,model.constituencyElection.constituency.constituencyId, " +
				" model.candidateResult.votesPercengate,model.party.longName,model.party.partyId,model.constituencyElection.reservationZone,model.constituencyElection.constituency.name" +
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
					" model.candidateResult.votesEarned," +
					" model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.rank,model.constituencyElection.constituency.constituencyId," +
					" model.candidateResult.votesPercengate,model.party.longName,model.party.partyId,model.constituencyElection.reservationZone,model.constituencyElection.constituency.name" +
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
				" model.candidateResult.votesPercengate,model.party.longName,model.party.partyId,model.constituencyElection.reservationZone,model.constituencyElection.constituency.name" +
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
					" model.candidateResult.votesPercengate,model.party.longName,model.party.partyId,model.constituencyElection.reservationZone,model.constituencyElection.constituency.name" +
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
				" model.candidateResult.votesPercengate,model.party.longName,model.party.partyId,model.constituencyElection.reservationZone,model.constituencyElection.constituency.name" +
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
				" model.candidateResult.votesPercengate,model.party.longName,model.party.partyId,model.constituencyElection.reservationZone,model.constituencyElection.constituency.name" +
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
				" model.candidateResult.votesPercengate,model.party.longName,model.party.partyId,model.constituencyElection.reservationZone,model.constituencyElection.constituency.name" +
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
				" model.candidateResult.votesPercengate,model.party.longName,model.party.partyId,model.constituencyElection.reservationZone,model.constituencyElection.constituency.name" +
				" from Nomination model where model.constituencyElection.constituency.tehsil.district.districtId = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ? " +
				" and model.constituencyElection.election.electionYear = ? and model.party.partyId = ?" +
				" and model.candidateResult.rank  = ? order by model.constituencyElection.constituency.constituencyId",params);
	}
	
	//Ends here......
	
	
	
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
	return getHibernateTemplate().find("select model.candidate.candidateId," +//0
			" model.candidate.lastname," +//1
			" model.candidateResult.votesEarned," +//2
			" model.candidateResult.votesPercengate," +//3
			" model.candidateResult.rank," +//4
			" model.party.partyId," +  //5
			" model.party.partyFlag," +//6
			" model.party.longName," +//7
			" model.party.shortName," +//8
			" model.constituencyElection.constituency.constituencyId," +//9
			" model.constituencyElection.constituency.name," +	//10
			" model.constituencyElection.election.electionYear," +//11
			" model.constituencyElection.constituency.electionScope.electionType.electionType," +//12
			" model.constituencyElection.constituencyElectionResult.totalVotes," +//13
			" model.constituencyElection.constituencyElectionResult.totalVotesPolled," +//14
			" model.constituencyElection.constituencyElectionResult.votingPercentage" +//15
			" from Nomination model where model.constituencyElection.constituency.constituencyId in (  " + constituencyId +
			" ) and model.constituencyElection.election.electionYear = ? " +
			" and model.constituencyElection.constituency.electionScope.electionType.electionType = ? order by model.candidateResult.rank",params);		
	}
	
	@SuppressWarnings("unchecked")
	public List getCandidatesInfoForTheGivenMuncipalityOrCorporationConstituency(String constituencyId,String electionYear,String electionType)	{
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
			" model.constituencyElection.constituency.localElectionBody.electionType.electionType," +
			" model.constituencyElection.constituencyElectionResult.totalVotes," +
			" model.constituencyElection.constituencyElectionResult.totalVotesPolled," +//14
			" model.constituencyElection.constituencyElectionResult.votingPercentage" +//15
			" from Nomination model where model.constituencyElection.constituency.constituencyId in (  " + constituencyId +
			" ) and model.constituencyElection.election.electionYear = ? " +
			" and model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? order by model.candidateResult.rank",params);		
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
				" model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.tehsil.tehsilName," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.constituencyElection.constituency.name" +
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
				" model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.tehsil.tehsilName," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.constituencyElection.constituency.name" +
				" from Nomination model where model.constituencyElection.constituency.state.stateId = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituencyElection.election.electionYear = ? and model.party.partyId = ?  and model.candidateResult.rank =?",params);
	}
	
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
				" model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.tehsil.tehsilName," +		
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.constituencyElection.constituency.name" +
				" from Nomination model where model.constituencyElection.constituency.state.stateId = ? and " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituencyElection.election.electionYear = ? ",params);
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
				" model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.tehsil.tehsilName," +	
				" model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.constituency.tehsil.tehsilId," +
				" model.constituencyElection.constituency.name" +
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
	
	public List getAllElectionsInDistrict(Long districtId) {
		return getHibernateTemplate().find("select distinct model.constituencyElection.election.electionId," +
				"model.constituencyElection.election.electionScope.electionType.electionType, model.constituencyElection.election.electionYear from Nomination model " +
				"where model.constituencyElection.constituency.district.districtId = ?", districtId);
	}
	@SuppressWarnings("unchecked")
	public List<Nomination> getNominationOfACandidateInAnElection(
			String electionType, String electionYear, Long constituencyId,
			Long candidateId) {
	    Object[] params = {electionType,electionYear,constituencyId,candidateId};
		return getHibernateTemplate().find("from Nomination model where model.constituencyElection.election.electionScope.electionType.electionType = ?"+
				" and model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.constituencyId = ?"+
				" and model.candidate.candidateId = ?",params);
	}
	@SuppressWarnings("unchecked")
	public List<Nomination> getNominationOfACandidateInAnElection(
			Long electionId, Long constituencyId, Long candidateId) {
		Object[] params = {electionId,constituencyId,candidateId};
		return getHibernateTemplate().find("from Nomination model where model.constituencyElection.election.electionId = ?"+
				" and model.constituencyElection.constituency.constituencyId = ?"+
				" and model.candidate.candidateId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List<Nomination> findByElectionIdAndPartyIdStateId(final Long electionId,final Long partyId,final Long stateId){
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
	public List getTehsilLevelElectionDetailsForAGivenConstituency(String query,Object[] parms){
		return getHibernateTemplate().find(query,parms);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findNominationsByConstituencyElectionPartyAndCandidate(Long constiElecId, Long partyId, Long candidateId){
		Object[] params = {constiElecId, partyId, candidateId};
		return getHibernateTemplate().find("from Nomination model where model.constituencyElection.constiElecId = ? and " +
				"model.party.partyId = ? and model.candidate.candidateId = ?", params);
	}
	

	@SuppressWarnings("unchecked")
	public List findElectionResultsByElectionIdAndPartyIdAndRank(
			Long electionId, Long partyId, Long rank) {
	    Object[] params = {electionId,partyId,rank};
	    return getHibernateTemplate().find("select model.candidate.candidateId,model.candidate.lastname,model.constituencyElection.constituency.constituencyId,"+
	    		"model.constituencyElection.constituency.name,model.constituencyElection.constituencyElectionResult.validVotes,"+
	    		"model.candidateResult.votesEarned,model.candidateResult.votesPercengate,model.nominationId from Nomination model where "+
	    		"model.constituencyElection.election.electionId = ? and model.party.partyId = ? "+
	    		"and model.candidateResult.rank = ? order by model.constituencyElection.constituency.constituencyId",params);
	}
	@SuppressWarnings("unchecked")
	public List findElectionResultsByElectionIdAndPartyIdAndLostRank(
			Long electionId, Long partyId, Long rank) {
	    Object[] params = {electionId,partyId,rank};
	    return getHibernateTemplate().find("select model.candidate.candidateId,model.candidate.lastname,model.constituencyElection.constituency.constituencyId,"+
	    		"model.constituencyElection.constituency.name,model.constituencyElection.constituencyElectionResult.validVotes,"+
	    		"model.candidateResult.votesEarned,model.candidateResult.votesPercengate,model.candidateResult.rank from Nomination model where "+
	    		"model.constituencyElection.election.electionId = ? and model.party.partyId = ? "+
	    		"and model.candidateResult.rank != ?",params);
	}

	@SuppressWarnings("unchecked")
	public List<Nomination> findByElectionIdAndPartyIdStateIdForLost(
			Long electionId, Long partyId, Long rank) {
		Object[] params = {electionId,partyId,rank};
		return getHibernateTemplate().find("from Nomination model where model.constituencyElection.election.electionId = ?"+
				" and model.party.partyId = ? and model.candidateResult.rank != ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByElectionIdAndPartyIdStateIdForLost(
			Long electionId, Long partyId, Long rank,Long stateId) {
		Object[] params = {electionId,stateId,partyId,rank};
		return getHibernateTemplate().find("from Nomination model where model.constituencyElection.election.electionId = ?"+
				" and model.constituencyElection.constituency.state.stateId = ? and model.party.partyId = ? and model.candidateResult.rank != ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByElectionIdAndPartyIdDistrictIdForLost(
			Long electionId, Long partyId, Long rank,Long districtId) {
		Object[] params = {electionId,districtId,partyId,rank};
		return getHibernateTemplate().find("from Nomination model where model.constituencyElection.election.electionId = ?"+
				" and model.constituencyElection.constituency.district.districtId = ? and model.party.partyId = ? and model.candidateResult.rank != ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByElectionIdAndPartyIdStateIdForWon(
			Long electionId, Long partyId, Long rank) {
		Object[] params = {electionId,partyId,rank};
		return getHibernateTemplate().find("from Nomination model where model.constituencyElection.election.electionId = ?"+
				" and model.party.partyId = ? and model.candidateResult.rank = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByElectionIdAndPartyIdStateIdForWon(
			Long electionId, Long partyId, Long rank,Long stateId) {
		Object[] params = {electionId,stateId,partyId,rank};
		return getHibernateTemplate().find("from Nomination model where model.constituencyElection.election.electionId = ?"+
				" and model.constituencyElection.constituency.state.stateId = ? and model.party.partyId = ? and model.candidateResult.rank = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByElectionIdAndPartyIdDistrictIdForWon(
			Long electionId, Long partyId, Long rank,Long districtId) {
		Object[] params = {electionId,districtId,partyId,rank};
		return getHibernateTemplate().find("from Nomination model where model.constituencyElection.election.electionId = ?"+
				" and model.constituencyElection.constituency.district.districtId = ? and model.party.partyId = ? and model.candidateResult.rank = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByElectionIdAndRank(Long electionId, Long rank,List<Long> constituencyIds) {
		Query queryObject = getSession().createQuery("from Nomination model where model.constituencyElection.election.electionId = ? "+
				"and model.candidateResult.rank = ? and model.constituencyElection.constituency.constituencyId in (:constituencyIds) order by model.constituencyElection.constituency.constituencyId");
		queryObject.setParameter(0,electionId);
		queryObject.setParameter(1,rank);
		queryObject.setParameterList("constituencyIds", constituencyIds);
		return queryObject.list();
	}


	@SuppressWarnings("unchecked")
	public List getMuncipalityCandidateDetails(String electionType,Long districtId){
		  Object[] params = {electionType,districtId};
		 return getHibernateTemplate().find("select model.constituencyElection.constituency.localElectionBody.name," +
		 		" model.constituencyElection.constituency.localElectionBody.localElectionBodyId," +
		 		" model.constituencyElection.constituency.localElectionBody.noOfWards," +
		 		" sum(model.constituencyElection.constituencyElectionResult.totalVotes)" +
		 		" from Nomination model where model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? and " +
		    	" model.constituencyElection.constituency.localElectionBody.tehsil.district.districtId = ? " +
		    	" group by model.constituencyElection.constituency.localElectionBody.localElectionBodyId ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getLocalBodiesElecConstituenciesDetailsForAnElection(Long electionId, String lebIds){
		 return getHibernateTemplate().find("select model.constituencyElection.constituency.localElectionBody.name," +
		 		" model.constituencyElection.constituency.localElectionBody.localElectionBodyId," +
		 		" model.constituencyElection.constituency.localElectionBody.noOfWards," +
		 		" sum(model.constituencyElection.constituencyElectionResult.totalVotes)," +
		 		" model.constituencyElection.constituency.localElectionBody.district.state.stateId," +
				" model.constituencyElection.election.electionScope.electionType.electionTypeId " +
		 		" from Nomination model where model.constituencyElection.constituency.localElectionBody.localElectionBodyId in("+lebIds+") and " +
		    	" model.constituencyElection.election.electionId = ? group by " +
		    	" model.constituencyElection.constituency.localElectionBody.localElectionBodyId ",electionId);
	}
	
	@SuppressWarnings("unchecked")
	public List getLocalElectionsCandidateDetailsInAConstituency(String electionType,String tehsilIds){		  
		 return getHibernateTemplate().find("select model.constituencyElection.constituency.localElectionBody.name," +
		 		" model.constituencyElection.constituency.localElectionBody.localElectionBodyId," +
		 		" model.constituencyElection.constituency.localElectionBody.noOfWards," +
		 		" sum(model.constituencyElection.constituencyElectionResult.totalVotes)" +
		 		" from Nomination model where model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? and " +
		    	" model.constituencyElection.constituency.localElectionBody.tehsil.tehsilId in ( " +tehsilIds+")"+
		    	" group by model.constituencyElection.constituency.localElectionBody.localElectionBodyId ",electionType);
	}
	
	public List getSeatsWonByAPartyInLocalElectionsForAConstituency(String muncipalityIds,String electionYear,String electionType,Long rank) {
		Object[] params = {electionYear, electionType,rank};
		return getHibernateTemplate().find("select model.party.shortName,count(model.candidateResult.rank)" +
				" from Nomination model where model.constituencyElection.constituency.localElectionBody.localElectionBodyId in ( "+ muncipalityIds+
				" ) and model.constituencyElection.election.electionYear = ? and " +
				" model.constituencyElection.election.electionScope.electionType.electionType = ? and model.candidateResult.rank = ? " +
				" group by model.party.shortName ", params);
	}
	
	@SuppressWarnings("unchecked")
	public List findSeatsWonByAPartyInMuncipalityForAnElectionYear(String muncipalityIds,String electionYear,String electionType,Long rank) {
		Object[] params = {electionYear, electionType,rank};
		return getHibernateTemplate().find("select model.party.shortName,count(model.candidateResult.rank)" +
				" from Nomination model where model.constituencyElection.constituency.localElectionBody.localElectionBodyId in ( "+ muncipalityIds+
				" ) and model.constituencyElection.election.electionYear = ? and " +
				" model.constituencyElection.election.electionScope.electionType.electionType = ? and model.candidateResult.rank = ? " +
				" group by model.party.shortName ", params);
	}
	
	@SuppressWarnings("unchecked")
	public List getPartysInfoForAMuncipalityForAnElectionYear(String electionType,String muncipalityIds,String electionYear){
		Object[] params = {electionType,electionYear};
		return getHibernateTemplate().find("select model.party.shortName,count(model.party.partyId),sum(model.candidateResult.votesEarned)" +
				" from Nomination model where model.constituencyElection.constituency.localElectionBody.electionType.electionType = ?" +
				" and model.constituencyElection.constituency.localElectionBody.localElectionBodyId in ( "+ muncipalityIds+
				" ) and model.constituencyElection.election.electionYear = ? group by model.party.partyId",params);
	}
	
	/*@SuppressWarnings("unchecked")
	public List getAllParticipatedPartyResultsInALocalBodyElection(Long localBodyID,String electionType,String electionYear){
		Object[] params = {localBodyID,electionType,electionYear};
		return getHibernateTemplate().find("select model.party.partyId as partyID,model.party.shortName as partyShortName,count(model.party.partyId)"+
				" as participatedConsti,sum(model.candidateResult.votesEarned) as votesGained from Nomination model where model.constituencyElection.constituency."+
				"localElectionBody.localElectionBodyId = ? and model.constituencyElection.constituency.localElectionBody.electionType." +
				"electionType = ? and model.constituencyElection.election.electionYear = ? group by model.party.partyId",params);
	}*/
	
	@SuppressWarnings("unchecked")
	public List getResultsOfAllPartiesInLocalBodyELectionsBasedOnRank(Long localBodyID,String electionYear,Long rank){
		Object[] params = {localBodyID,electionYear,rank};
		return getHibernateTemplate().find("select model.party.partyId as partyId,model.party.shortName as partyShortName,count(model.candidateResult.rank) as seats"+
				" from Nomination model where model.constituencyElection.constituency.localElectionBody.localElectionBodyId = ? and"+
				" model.constituencyElection.election.electionYear = ? and model.candidateResult.rank = ? group by model.party.partyId" ,params);
	}
	
	@SuppressWarnings("unchecked")
	public List getResultsOfAllPartiesInLocalBodyELectionsBasedOnNthRank(Long localBodyID,String electionYear,Long rank){
		Object[] params = {localBodyID,electionYear,rank};
		return getHibernateTemplate().find("select model.party.partyId as partyId,model.party.shortName as partyShortName,count(model.candidateResult.rank) as seats"+
				" from Nomination model where model.constituencyElection.constituency.localElectionBody.localElectionBodyId = ? and"+
				" model.constituencyElection.election.electionYear = ? and model.candidateResult.rank > ? group by model.party.partyId" ,params);
	}
	
	@SuppressWarnings("unchecked")
	public List getAllParticipatedPartyResultsInALocalBodyElection(Long localBodyID,String electionYear){
		Object[] params = {localBodyID,electionYear};
		return getHibernateTemplate().find("select model.party.partyId as partyId,model.party.shortName as partyShortName,count(model.party.partyId)"+
				" as participatedConsti,sum(model.candidateResult.votesEarned) as votesGained from Nomination model where model.constituencyElection.constituency."+
				"localElectionBody.localElectionBodyId = ? and model.constituencyElection.election.electionYear = ? " +
				"group by model.party.partyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getPartyParticipatedValidVotesForLocalBodyElection(Long localBodyId,Long partyId,String electionYear){
		Object[] params = {localBodyId,electionYear,partyId};
		return getHibernateTemplate().find("select sum(model.constituencyElection.constituencyElectionResult.validVotes) "+
				"from Nomination model where model.constituencyElection.constituency.localElectionBody.localElectionBodyId = ? and "+
				"model.constituencyElection.election.electionYear = ? and model.party.partyId = ? group by "+
				"model.constituencyElection.constituency.localElectionBody.localElectionBodyId",params);
	}
	

	@SuppressWarnings("unchecked")
	public List getConstituencyLevelPartyParticipatedLocalBodyElectionVotesInfo(Long localBodyId,Long partyId,Long electionId){
		Object[] params = {localBodyId,electionId,partyId};
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId,"+
				"model.constituencyElection.constituency.name,"+
				"sum(model.constituencyElection.constituencyElectionResult.validVotes),"+
				"sum(model.constituencyElection.constituencyElectionResult.totalVotesPolled),"+
				"sum(model.constituencyElection.constituencyElectionResult.totalVotes) "+
				"from Nomination model where model.constituencyElection.constituency.localElectionBody.localElectionBodyId = ? and "+
				"model.constituencyElection.election.electionId = ? and model.party.partyId = ? group by "+
				"model.constituencyElection.constituency.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getAllPartiesForAMuncipality(String electionType,Long muncipalityId,String electionYear){
		Object[] params = {electionType,muncipalityId,electionYear};
		return getHibernateTemplate().find("select distinct model.party.shortName,model.party.partyId" +
				" from Nomination model where model.constituencyElection.constituency.localElectionBody.electionType.electionType = ?" +
				" and model.constituencyElection.constituency.localElectionBody.localElectionBodyId = ?"+
				" and model.constituencyElection.election.electionYear = ?",params);
	}
	
	public List getAllElectionYearsForAConstituency(Long constituencyId,String electionType){
		Object[] params = {constituencyId,electionType};
		return getHibernateTemplate().find("select distinct model.constituencyElection.election.electionYear" +
				" from Nomination model where model.constituencyElection.constituency.constituencyId = ?" +
				" and model.constituencyElection.election.electionScope.electionType.electionType = ?",params);
	}
	@SuppressWarnings("unchecked")
	public List<Nomination> getNominationsForANominationIdsSet(
			List<Long> nominationIds) {
		Query queryObject = getSession().createQuery("from Nomination model where model.nominationId in (:nominationIds)");
		queryObject.setParameterList("nominationIds", nominationIds);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List findPartiesInfoByElectionAndPartyGroupByDistrict(Long electionId, String partyIds) {
		Object[] params = {electionId};
		return getHibernateTemplate().find("select model.constituencyElection.constituency.district.districtId, " +
				"model.constituencyElection.constituency.district.districtName, " +
				"count(distinct model.constituencyElection.constituency.constituencyId), model.party.partyId, model.party.shortName," +
				"sum(model.constituencyElection.constituencyElectionResult.validVotes)," +
				"sum(model.candidateResult.votesEarned) from Nomination model where " +
				"model.constituencyElection.election.electionId = ? and model.party.partyId in ("+partyIds+") "+
				"group by model.constituencyElection.constituency.district.districtId " +
				"order by model.constituencyElection.constituency.district.districtId",params);
	}

	public List findPartiesInfoByElectionAndPartyGroupByState(Long electionId, String partyIds) {
		Object[] params = {electionId};
		return getHibernateTemplate().find("select model.constituencyElection.constituency.state.stateId, " +
				"model.constituencyElection.constituency.state.stateName, " +
				"count(model.constituencyElection.constituency.constituencyId), model.party.partyId, model.party.shortName," +
				"sum(model.constituencyElection.constituencyElectionResult.validVotes)," +
				"sum(model.candidateResult.votesEarned) from Nomination model where " +
				"model.constituencyElection.election.electionId = ? and model.party.partyId in ("+partyIds+") "+
				"group by model.constituencyElection.constituency.state.stateId " +
				"order by model.constituencyElection.constituency.state.stateId",params);
	}	
	
	public List findPartyWonConstituenciesInfoByElectionAndPartyGroupByState(Long electionId, String partyIds, Long rank) {
		Object[] params = {electionId, rank};
		return getHibernateTemplate().find("select model.constituencyElection.constituency.state.stateId, " +
				"model.constituencyElection.constituency.state.stateName, " +
				"count(model.constituencyElection.constituency.constituencyId), " +
				"model.party.partyId, model.party.shortName from Nomination model " +
				"where model.constituencyElection.election.electionId = ? " +
				"and model.party.partyId in ("+partyIds+")"+
				"and model.candidateResult.rank = ? group by model.constituencyElection.constituency.state.stateId " +
				"order by model.constituencyElection.constituency.state.stateId",params);
	}
	
	public List findPartyWonConstituenciesInfoByElectionAndPartyGroupByDistrict(Long electionId, String partyIds, Long rank) {
		Object[] params = {electionId, rank};
		return getHibernateTemplate().find("select model.constituencyElection.constituency.district.districtId, " +
				"model.constituencyElection.constituency.district.districtName, " +
				"count(model.constituencyElection.constituency.constituencyId), " +
				"model.party.partyId, model.party.shortName from Nomination model " +
				"where model.constituencyElection.election.electionId = ? " +
				"and model.party.partyId in ("+partyIds+")"+
				"and model.candidateResult.rank = ? group by model.constituencyElection.constituency.district.districtId " +
				"order by model.constituencyElection.constituency.district.districtId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findConstituencyElectionsByElectionPartyAndPosition(
			Long electionId, Long partyId, Long rank) {
		Object[] params = {electionId, rank, partyId};	
		return getHibernateTemplate().find("select constituencyElection from Nomination model where " +
				"model.constituencyElection.election.electionId =? and model.candidateResult.rank = ? and " +
				"model.party.partyId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List findElectionResultsForAllPartiesInAssemblyConstituencies(
			String electionYear, List<Long> constituencyIds) {
		Query queryObject = getSession().createQuery("select model.candidateResult.votesPercengate, model.candidateResult.votesEarned, " + 
				"model.party.shortName, model.party.partyId, model.constituencyElection.constituency.name, model.constituencyElection.constituency.constituencyId, model.constituencyElection.constituencyElectionResult.validVotes " + 
				"from Nomination model where model.constituencyElection.election.electionYear =? and model.constituencyElection.constituency.constituencyId in (:constituencyIds)");
		queryObject.setParameter(0,electionYear);
		queryObject.setParameterList("constituencyIds",constituencyIds);
		return queryObject.list();

	}
	
	@SuppressWarnings("unchecked")
	public List findWinningCandidatesDetailsInContituencies(
			String electionYear, List<Long> constituencyIds) {
		Query queryObject = getSession().createQuery("select model.constituencyElection.constituency.district.districtId, model.constituencyElection.constituency.district.districtName, " +
				"model.constituencyElection.constituency.constituencyId, model.constituencyElection.constituency.name, " +
				"model.candidate.lastname, " +
				"model.party.shortName, model.party.partyId, model.candidateResult.votesPercengate,model.candidateResult.votesEarned " +  
				"from Nomination model where model.constituencyElection.election.electionYear =? and model.candidateResult.rank = 1 and model.constituencyElection.constituency.constituencyId in (:constituencyIds)" +
				"order by model.constituencyElection.constituency.district.districtName")  ;
		queryObject.setParameter(0,electionYear);
		queryObject.setParameterList("constituencyIds",constituencyIds);
		return queryObject.list();
	
	}
	
	@SuppressWarnings("unchecked")
	public List findOppositionCandidateVotesPercentageInConstituencies(String electionYear,
			List<Long> constituencyIds) {
		Query queryObject = getSession().createQuery("select model.candidateResult.votesPercengate, model.constituencyElection.constituency.constituencyId,model.candidateResult.votesEarned " +  
				"from Nomination model where model.constituencyElection.election.electionYear =? and model.candidateResult.rank = 2 and model.constituencyElection.constituency.constituencyId in (:constituencyIds)");
		queryObject.setParameter(0,electionYear);
		queryObject.setParameterList("constituencyIds",constituencyIds);
		return queryObject.list();
	}
	@SuppressWarnings("unchecked")
	public List getCandidateRankInAConstituencyElection(Long constituencyId,
			String electionYear, String electionType, Long partyId) {
		Object[] params = {constituencyId,partyId,electionYear,electionType};
		return getHibernateTemplate().find("select model.candidateResult.rank from Nomination model "+
				"where model.constituencyElection.constituency.constituencyId = ? "+
				"and model.party.partyId = ? and model.constituencyElection.election.electionYear = ? "+
				"and model.constituencyElection.election.electionScope.electionType.electionType = ?",params);
	}
		
	public List getPartyVotesShareInAConstituency(Long constituencyId, String electionIds, String partyIds) {
		Object[] params = {constituencyId};
		return getHibernateTemplate().find("select model.constituencyElection.election.electionYear," + 
				" model.constituencyElection.election.electionScope.electionType.electionType," +
				" model.candidateResult.votesPercengate,model.candidateResult.votesEarned" +
				" from Nomination model where " +
				" model.constituencyElection.constituency.constituencyId = ? and model.constituencyElection.election.electionId in (" +electionIds+")"+
				" and model.party.partyId in (" +partyIds+")",params);
	}
	@SuppressWarnings("unchecked")
	public List getWonAndOppCandidateInAnElection(Long constiId, String elecYear) {
		Object[] params = {constiId,elecYear};
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName,"+
				"model.candidate.candidateId,model.candidate.lastname,model.candidateResult.votesEarned,"+
				"model.candidateResult.votesPercengate,model.candidateResult.rank "+
				"from Nomination model where model.constituencyElection.constituency.constituencyId = ? "+
				"and model.constituencyElection.election.electionYear = ? and model.candidateResult.rank in(1,2)",params);
	}
	
	public List getMptcZptcPartiesResultsInMandals(String mandalIds){
		return getHibernateTemplate().find("select model.constituencyElection.election.electionId," +
				"model.constituencyElection.election.electionScope.electionType.electionType, " +
				"model.constituencyElection.election.electionYear," +
				"model.party.shortName, model.party.partyId, sum(model.candidateResult.votesEarned), " +
				"sum(model.constituencyElection.constituencyElectionResult.validVotes) from " +
				"Nomination model where model.constituencyElection.constituency.tehsil.tehsilId in("+mandalIds+") " +
				"group by model.constituencyElection.election.electionId, model.party.partyId order by " +
				"model.constituencyElection.election.electionId");
	}
	
	public List getMunicipalitiesAndCorporationsResultsInMandals(String mandalIds){
		return getHibernateTemplate().find("select model.constituencyElection.election.electionId," +
				"model.constituencyElection.election.electionScope.electionType.electionType, " +
				"model.constituencyElection.election.electionYear," +
				"model.party.shortName, model.party.partyId, sum(model.candidateResult.votesEarned), " +
				"sum(model.constituencyElection.constituencyElectionResult.validVotes) from " +
				"Nomination model where model.constituencyElection.constituency.localElectionBody.tehsil.tehsilId in("+mandalIds+") " +
				"group by model.constituencyElection.election.electionId, model.party.partyId order by " +
				"model.constituencyElection.election.electionId");
	}
	@SuppressWarnings("unchecked")
	public List getResultsForElectionInConstituency(Long constituencyId,String electionYear) {
		Object[] params = {constituencyId,electionYear};
		return getHibernateTemplate().find("select model.party.shortName,model.candidateResult.votesEarned,"+
				"model.party.partyId,model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.votesPercengate "+
				"from Nomination model where model.constituencyElection.constituency.constituencyId = ? "+
				"and model.constituencyElection.election.electionYear = ? group by model.nominationId "+
				"order by model.candidateResult.votesEarned desc",params);
	}
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndPartyAndNthRank(
			Long electionID, Long rank, Long partyId) {
		Object[] params = {electionID,rank,partyId};
		return getHibernateTemplate().find("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.candidateResult.rank > ? and model.party.partyId = ?", params);
	}
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndPartyAndRank(
			Long electionID, Long rank, Long partyId) {
		Object[] params = {electionID,rank,partyId};
		return getHibernateTemplate().find("select constituencyElection from Nomination model where model.constituencyElection.election.electionId =? and model.candidateResult.rank = ? and model.party.partyId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Election> getElectionsInState(Long stateId) {
		return getHibernateTemplate().find("select distinct model.constituencyElection.election from Nomination model where " +
				"model.constituencyElection.election.electionScope.state.stateId = ? or model.constituencyElection.election.electionScope.state.stateId is null order by model.constituencyElection.election.electionYear desc", stateId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Election> findByElectionScopeIdAndPartyId(Long electionScopeId,
			String electionSubtypes,Long partyId){
		Object params[] = {electionScopeId, electionSubtypes,partyId};
		return getHibernateTemplate().find("select distinct model.constituencyElection.election.electionId,model.constituencyElection.election.electionYear from Nomination model where model.constituencyElection.election.electionScope.electionScopeId = ? " +
				"and model.constituencyElection.election.elecSubtype = ? and model.party.partyId = ? order by model.constituencyElection.election.electionYear desc", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> getAllAllianceConstituenciesForAPartyInAStateForAnElection(Long electionId,String alliancePartyIds,Long partyId,Long stateId){
		Object[] parameters = {electionId, electionId, partyId, stateId, stateId};
		return getHibernateTemplate().find("select distinct model.constituencyElection from Nomination model where " +
				"  model.constituencyElection.election.electionId = ?  and model.party.partyId in (" + alliancePartyIds + ")" +
				"  and model.constituencyElection.constiElecId not in (" +				
				"  select model2.constituencyElection.constiElecId from Nomination model2 where " +
				"  model2.constituencyElection.election.electionId = ?  and model2.party.partyId = ?" +
				"  and model2.constituencyElection.constituency.state.stateId = ?"+
				" )and model.constituencyElection.constituency.state.stateId = ?",parameters);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> getAllAllianceConstituenciesForAPartyInADistrict(Long electionId,String alliancePartyIds,Long partyId,Long districtId){
		Object[] parameters = {electionId, electionId, partyId, districtId, districtId};
		return getHibernateTemplate().find("select distinct model.constituencyElection from Nomination model where " +
				" model.constituencyElection.election.electionId = ?  and model.party.partyId in (" + alliancePartyIds + ")" +
				" and model.constituencyElection.constiElecId not in ( select model2.constituencyElection.constiElecId " +
				" from Nomination model2 where model2.constituencyElection.election.electionId = ?  and model2.party.partyId = ?" +				
				" and model2.constituencyElection.constituency.district.districtId = ? )" +
				" and model.constituencyElection.constituency.district.districtId = ?",parameters);
	}
	
	public List getConstituenciesCountByDistrictForElectionStateAndParties(Long electionId, Long stateId, String alliancePartyIds){
		Object[] parameters = {electionId, stateId};
		return getHibernateTemplate().find("select count(distinct model.constituencyElection.constiElecId) from Nomination model where " +
				"model.constituencyElection.election.electionId = ? and model.party.partyId in (" + alliancePartyIds + ") and " +
				"model.constituencyElection.constituency.district.state.stateId = ? " +
				"group by model.constituencyElection.constituency.district.districtId", parameters);
	}
	
	public List getConstituenciesCountByStateForElectionCountryAndParties(Long electionId, Long countryId, String alliancePartyIds){
		Object[] parameters = {electionId, countryId};
		return getHibernateTemplate().find("select count(distinct model.constituencyElection.constiElecId) from Nomination model where " +
				"model.constituencyElection.election.electionId = ? and model.party.partyId in (" + alliancePartyIds + ") and " +
				"model.constituencyElection.constituency.state.country.countryId = ? " +
				"group by model.constituencyElection.constituency.state.stateId", parameters);
	}
	@SuppressWarnings("unchecked")
	public List getResultsForAllPartiesInALocalBodyElectionInAWard(
			Long localBodyId, Long electionId, Long wardId) {
		Object[] params = {localBodyId,electionId,wardId};
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName,model.candidate.candidateId,"+
				"model.candidate.lastname,model.constituencyElection.constituency.constituencyId,model.constituencyElection."+
				"constituency.name,model.candidateResult.votesEarned,model.candidateResult.votesPercengate,model.candidateResult.rank,"+
				"model.party.partyFlag from Nomination model where model.constituencyElection.constituency.localElectionBody.localElectionBodyId = ? "+
				"and model.constituencyElection.election.electionId = ? and model.constituencyElection.constituency.constituencyId = ? ",params);
	}
	@SuppressWarnings("unchecked")
	public List getWardWiseResultsForAPartyInALocalBodyElection(
			Long localBodyId, Long electionId, Long partyId) {
		Object[] params = {localBodyId,electionId,partyId};
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName,model.candidate.candidateId,"+
				"model.candidate.lastname,model.constituencyElection.constituency.constituencyId,model.constituencyElection."+
				"constituency.name,model.candidateResult.votesEarned,model.candidateResult.votesPercengate,model.candidateResult.rank,"+
				"model.party.partyFlag from Nomination model where model.constituencyElection.constituency.localElectionBody.localElectionBodyId = ? "+
				"and model.constituencyElection.election.electionId = ? and model.party.partyId = ? ",params);
				
	}
	
	@SuppressWarnings("unchecked")
	public List getAllPartyResultsInAWardInALocalBodyElection(
			Long localBodyId, Long electionId, Long wardId) {
		Object[] params = {localBodyId,electionId,wardId};
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName,model.candidate.candidateId,"+
				"model.candidate.lastname,model.constituencyElection.constituency.constituencyId,model.constituencyElection."+
				"constituency.name,model.candidateResult.votesEarned,model.candidateResult.votesPercengate,model.candidateResult.rank,"+
				"model.party.partyFlag from Nomination model where model.constituencyElection.constituency.localElectionBody.localElectionBodyId = ? "+
				"and model.constituencyElection.election.electionId = ? and model.constituencyElection.constituency.constituencyId = ? ",params);
				
	}
	
	@SuppressWarnings("unchecked")
	public List getWardWiseResultsOfAllPartiesInLocalElectionBodies(
			Long localBodyId, Long electionId) {
		Object[] params = {localBodyId,electionId};
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName,model.candidate.candidateId,"+
				"model.candidate.lastname,model.constituencyElection.constituency.constituencyId,model.constituencyElection."+
				"constituency.name,model.candidateResult.votesEarned,model.candidateResult.votesPercengate,model.candidateResult.rank,"+
				"model.party.partyFlag from Nomination model where model.constituencyElection.constituency.localElectionBody.localElectionBodyId = ? "+
				"and model.constituencyElection.election.electionId = ? order by model.constituencyElection.constituency.constituencyId",params);
	}
	public List getAllCandidatesByElectionTypeInState(String electionType,
			Long stateId, String searchString) {
		String cName = ""+searchString+"%";
		Query queryObject = getSession().createQuery("select distinct model.candidate.candidateId, model.candidate.firstname, model.candidate.middlename, model.candidate.lastname from " +
				"Nomination model where model.constituencyElection.election.electionScope.electionType.electionType = ? and model.constituencyElection.constituency.state.stateId = ? and model.candidate.lastname like ?");
		queryObject.setString(0,electionType);
		queryObject.setLong(1,stateId);
		queryObject.setString(2, cName);
		queryObject.setMaxResults(IConstants.MAX_PROBLEMS_DISPLAY.intValue());
		return queryObject.list();		
	}
	
	public List findAllElectionResultsForConstituencies(Long electionId, String constituencyIds,Long partyId) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select model.constituencyElection.election.electionId, ");
		sb.append("model.constituencyElection.election.electionYear, " );
		sb.append("model.constituencyElection.election.electionScope.electionType.electionType, "); 
		sb.append("model.constituencyElection.constituency.localElectionBody.localElectionBodyId, " );
		sb.append("model.constituencyElection.constituency.localElectionBody.name, model.constituencyElection.constituency.constituencyId, " );
		sb.append("model.constituencyElection.constituency.name, model.party.partyId, model.party.shortName, model.candidateResult.votesEarned, ");
		sb.append("model.candidateResult.votesPercengate, model.candidateResult.rank, model.candidate.lastname, " );
		sb.append("model.constituencyElection.constituencyElectionResult.totalVotes," );
		sb.append("model.constituencyElection.constituency.localElectionBody.district.state.stateId," );
		sb.append("model.constituencyElection.election.electionScope.electionType.electionTypeId from Nomination model where " );
		sb.append("model.constituencyElection.constituency.constituencyId in ("+constituencyIds+") and model.constituencyElection.election.electionId = ? "); 
		if(partyId!=0l){
			sb.append(" and model.party.partyId = ? ");	
		}		
		sb.append("order by model.constituencyElection.constituency.name, model.candidateResult.rank");
		
		Query queryObject = getSession().createQuery(sb.toString());
		queryObject.setLong(0,electionId);
		if(partyId!=0l){
			queryObject.setLong(1,partyId);
		}
		return queryObject.list();
	}
	
	public List getALLPartiesByElectionId(Long electionId, String constituencyIds) {		
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.party.partyId, model.party.shortName ");
		sb.append(" from Nomination model where " );
		sb.append(" model.constituencyElection.constituency.constituencyId in ("+constituencyIds+")");
		return getHibernateTemplate().find(sb.toString());
		
	}
	@SuppressWarnings("unchecked")
	public List checkForResultsAvailabilityForAnElection(Long electionId) {
		return getHibernateTemplate().find("select count(model.nominationId) from Nomination model where model.constituencyElection.election.electionId = ?",electionId);
	}
	
}
