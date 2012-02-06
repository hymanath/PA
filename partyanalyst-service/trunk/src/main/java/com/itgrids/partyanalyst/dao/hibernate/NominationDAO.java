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
		return getHibernateTemplate().find( "select model.party.shortName, model.party.longName, model.candidateResult.votesEarned, model.candidateResult.rank, " +
				"model.nominationId, model.candidate.lastname, model.candidate.candidateId, model.constituencyElection.election.electionId, model.constituencyElection.election.electionScope.electionType.electionType, "+
				"model.constituencyElection.election.electionYear,model.party.partyId  from Nomination model where model.constituencyElection.constiElecId = ?",constituencyElectionID);
	}
	
	@SuppressWarnings("unchecked")
	public List findByNominationId(Long nominationID){
		return getHibernateTemplate().find( "select model.party.shortName, model.party.longName, model.candidateResult.votesEarned, model.candidateResult.rank, " +
				"model.nominationId, model.candidate.lastname, model.candidate.candidateId, model.constituencyElection.election.electionId, model.constituencyElection.election.electionScope.electionType.electionType, "+
				"model.constituencyElection.election.electionYear  from Nomination model where model.nominationId = ?",nominationID);
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
		return getHibernateTemplate().find( "select upper(model.constituencyElection.constituency.name)," +
				" upper(model.candidate.lastname), upper(model.party.shortName), " +
				" model.constituencyElection.constituency.constituencyId , model.candidate.candidateId ," +
				" model.party.partyFlag,model.constituencyElection.reservationZone from Nomination model " +
				" where model.constituencyElection.constituency.constituencyId in (  " + constituencyIds +
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
	public List<Nomination> findByElectionIdAndPartyIdStateIdAndDistrictIdForLocalElectionBodys(final Long electionId, final Long partyId, final Long districtId) {
		
		return ( List<Nomination> ) getHibernateTemplate().execute( new HibernateCallback() {
            public Object doInHibernate( Session session ) throws HibernateException, SQLException {
            		List<Nomination> constElectionResults = session.createCriteria(Nomination.class)
            							.createAlias("constituencyElection", "constElec")
            							.createAlias("party", "p")
            							.createAlias("constElec.election", "elec")
            							.createAlias("constElec.constituency", "const")
            							.createAlias("const.localElectionBody", "localBody")
            							.createAlias("localBody.district", "district")
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
	
	/*@SuppressWarnings("unchecked")
	public List getCandidateNPartyInfoForParliament(String constituencyIds,String electionType,Long rank, String electionSubtype)
	{
		Object[] params = {rank, electionType, electionSubtype};
		String hasResults = "1";
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName," +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId in ("+constituencyIds+") and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.electionYear) from Election nModel where " +
				"nModel.electionScope.electionType.electionType = ? and nModel.elecSubtype = ?)",params);
		
	}*/
	
	@SuppressWarnings("unchecked")
	public List getCandidateNPartyInfoForParliament(String constituencyIds,String electionType,Long rank, String electionSubtype)
	{
		String hasResults = "1";
		Object[] params = {rank, electionType, electionSubtype,hasResults};
		
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName," +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId in ("+constituencyIds+") and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.election.electionYear) from ConstituencyElection nModel where nModel.election.electionScope.electionType.electionType = ? and nModel.election.elecSubtype = ? "+
				"and nModel.hasResults is null or nModel.hasResults = ? order by nModel.election.electionYear desc)",params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List getCandidateNPartyInfoForParliament(String constituencyIds,String electionType,Long rank)
	{
		String hasResults = "1";
		Object[] params = {rank, electionType,hasResults};
		
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName," +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId in ("+constituencyIds+") and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.election.electionYear) from ConstituencyElection nModel where nModel.election.electionScope.electionType.electionType = ? "+
				"and nModel.hasResults is null or nModel.hasResults = ? order by nModel.election.electionYear desc)",params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List getCandidateAndPartyInfoForParliament(Long constituencyId,String electionType,Long rank)
	{
		String hasResults = "1";
		Object[] params = {constituencyId, rank, electionType,constituencyId};
		
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName," +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId = ? and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.election.electionYear) from ConstituencyElection nModel where nModel.election.electionScope.electionType.electionType = ? "+
				"and nModel.constituency.constituencyId = ? and nModel.hasResults is null )",params);
		
	}
	
	/*@SuppressWarnings("unchecked")
	public List getCandidateNPartyInfo(String constituencyIds,String electionType,Long rank, String electionSubtype,Long stateId)
	{
		Object[] params = {rank, electionType, electionSubtype,stateId};
		String hasResults = "1";
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName," +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId in ("+constituencyIds+") and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.electionYear) from Election nModel where " +
				"nModel.electionScope.electionType.electionType = ? and nModel.elecSubtype = ? and nModel.electionScope.state.stateId = ?)",params);
		
	}*/
	
	@SuppressWarnings("unchecked")
	public List getCandidateNPartyInfo(String constituencyIds,String electionType,Long rank, String electionSubtype,Long stateId)
	{
		String hasResults = "1";
		Object[] params = {rank, electionType, electionSubtype,stateId,hasResults};
		
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName," +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId in ("+constituencyIds+") and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.election.electionYear) from ConstituencyElection nModel where nModel.election.electionScope.electionType.electionType = ? and nModel.election.elecSubtype = ? "+
				"and nModel.constituency.state.stateId = ? and nModel.hasResults is null or nModel.hasResults = ? order by nModel.election.electionYear desc)",params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidateAndPartyInfo(Long constituencyId,String electionType,Long rank)
	{
		Object[] params = {constituencyId,rank,electionType,constituencyId};
			
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName," +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId = ? and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.election.electionYear) from ConstituencyElection nModel where nModel.election.electionScope.electionType.electionType = ? "+
				"and nModel.constituency.constituencyId = ? )",params);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List getParliamentCandidateNPartyInfo(Long constituencyId,String electionType,Long rank)
	{
		Object[] params = {constituencyId, rank, electionType,constituencyId};
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName, " +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear,model.constituencyElection.reservationZone " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId = ? and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.election.electionYear) from ConstituencyElection nModel where " +
				" nModel.election.electionScope.electionType.electionType = ? and nModel.constituency.constituencyId = ?)",params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List getParliamentCandidateNPartyInfoInElection(Long constituencyId,String electionType,Long rank,String elecYear)
	{
		Object[] params = {constituencyId, rank,electionType,elecYear};
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName, " +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear,model.constituencyElection.reservationZone " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId = ? and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionScope.electionType.electionType = ? and model.constituencyElection.election.electionYear = ?",params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List getParliamentCandidateNPartyInfo(Long constituencyId,String electionType,Long rank,String elecSubType)
	{
		Object[] params = {constituencyId, rank, electionType,elecSubType};
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName, " +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear,model.constituencyElection.reservationZone " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId = ? and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.electionYear) from Election nModel where nModel.electionScope.electionType.electionType = ? and nModel.elecSubtype = ?)",params);
		
	}
	
	/*@SuppressWarnings("unchecked")
	public List getParliamentCandidateNPartyInfo(Long constituencyId,String electionType,Long rank,String elecSubType)
	{
		Object[] params = {constituencyId, rank, electionType,elecSubType};
		String hasResults = "1";
		
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId," +
				"model.constituencyElection.constituency.name,model.candidate.candidateId,model.candidate.firstname," +
				"model.candidate.middlename,model.candidate.lastname,model.party.partyId,model.party.shortName, " +
				"model.constituencyElection.constituency.deformDate,model.constituencyElection.constituency.electionScope.electionType.electionType," +
				"model.party.partyFlag, model.constituencyElection.election.electionYear,model.constituencyElection.reservationZone " +
				"from Nomination model where model.constituencyElection.constituency.constituencyId = ? and model.candidateResult.rank = ? and " +
				"model.constituencyElection.election.electionYear = (select max(nModel.election.electionYear) from constituencyElection nModel where nModel.election.electionScope.electionType.electionType = ? and nModel.election.elecSubtype = ? "+
				"and model.hasResults is null or model.hasResults = "+hasResults+")",params);
		
	}*/
	
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
		Query queryObject = getSession().createQuery("select distinct(constituencyElection) from Nomination model where model.constituencyElection.election.electionId =? and model.constituencyElection.constituency.district.districtId =? and model.party.partyId in (:partyIds)");
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
		return getHibernateTemplate().find("select model.party.shortName,count(model.party.partyId),sum(model.candidateResult.votesEarned),model.party.partyId," +
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
	public List findAllZPTCsOrMPTCsInaDistrict(Long districtId,String electionTypes,Long rank,String electionYear) {
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
	public List findAllZptcOrMptcCandidatesInaDistrict(Long districtId,String electionTypes,String electionYear) {
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
	public List getCandidatesInfoForTheGivenConstituencyBasedOnRankForLocalBodys(List<Long> constituencyId,String electionYear,String electionType,Long rank,Long partyId){
			
		StringBuilder sb = new StringBuilder();
		sb.append("select model.candidate.candidateId,");
		sb.append(" model.candidate.lastname,");
		sb.append(" model.candidateResult.votesEarned,");
		sb.append(" model.candidateResult.votesPercengate,");
		sb.append(" model.candidateResult.rank,");
		sb.append(" model.party.partyId,");  
		sb.append(" model.party.partyFlag,");
		sb.append(" model.party.longName,");
		sb.append(" model.party.shortName,");
		sb.append(" model.constituencyElection.constituency.constituencyId,");
		sb.append(" model.constituencyElection.constituency.name,");	
		sb.append(" model.constituencyElection.election.electionYear,");
		sb.append(" model.constituencyElection.constituency.localElectionBody.electionType.electionType");		
		sb.append(" from Nomination model where");
		
		sb.append(" model.constituencyElection.election.electionYear = ? ");
		sb.append(" and model.constituencyElection.constituency.localElectionBody.electionType.electionType = ?");
		sb.append(" and model.candidateResult.rank = ? ");
		
		
		if(partyId!=0l){
			sb.append(" and model.party.partyId = ?");
		}
		sb.append(" and model.constituencyElection.constituency.constituencyId in ( :constituencyId )");
		sb.append("order by model.candidateResult.rank");
		Query queryObject = getSession().createQuery(sb.toString());
		
		queryObject.setParameterList("constituencyId", constituencyId);
		queryObject.setParameter(0, electionYear);
		queryObject.setParameter(1, electionType);
		queryObject.setParameter(2, rank);		
		if(partyId!=0l){
			queryObject.setParameter(3,partyId);
		}
		
		return queryObject.list();
		
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
			" model.constituencyElection.constituencyElectionResult.votingPercentage," +//15
			" model.constituencyElection.reservationZone" +//16
			" from Nomination model where model.constituencyElection.constituency.constituencyId in (  " + constituencyId +
			" ) and model.constituencyElection.election.electionYear = ? " +
			" and model.constituencyElection.constituency.electionScope.electionType.electionType = ? order by model.candidateResult.rank",params);		
	}
	
	@SuppressWarnings("unchecked")
	public List getCandidatesInfoForTheGivenConstituencyForLocalBody(String constituencyId,String electionYear,String electionType)	{
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
			" model.constituencyElection.constituency.localElectionBody.electionType.electionType," +//12
			" model.constituencyElection.constituencyElectionResult.totalVotes," +//13
			" model.constituencyElection.constituencyElectionResult.totalVotesPolled," +//14
			" model.constituencyElection.constituencyElectionResult.votingPercentage," +//15
			" model.constituencyElection.reservationZone" +//16
			" from Nomination model where model.constituencyElection.constituency.constituencyId in (  " + constituencyId +
			" ) and model.constituencyElection.election.electionYear = ? " +
			" and model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? order by model.candidateResult.rank",params);		
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
			" model.constituencyElection.constituencyElectionResult.votingPercentage," +//15
			" model.constituencyElection.reservationZone" +//16
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
	public List findAllCandidatesForAnElectionBytheElectionYearByRankForLocalBody(String electionYear,Long stateId,String electionType,Long rank,Long partyId) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select model.candidate.candidateId,");
		sb.append(" model.candidate.lastname,");
		sb.append(" model.candidateResult.votesEarned,");
		sb.append(" model.candidateResult.votesPercengate,");
		sb.append(" model.candidateResult.rank,");
		sb.append(" model.party.partyId,");  
		sb.append(" model.party.partyFlag,");
		sb.append(" model.party.longName,");
		sb.append(" model.party.shortName,");
		sb.append(" model.constituencyElection.constituency.constituencyId,");//9
		sb.append(" model.constituencyElection.constituency.name,");	//10
		sb.append(" model.constituencyElection.election.electionYear,");//11
		sb.append(" model.constituencyElection.constituency.localElectionBody.electionType.electionType");//12
		sb.append(" from Nomination model where");
		sb.append(" model.constituencyElection.election.electionYear = ? and ");
		sb.append(" model.constituencyElection.constituency.localElectionBody.district.state.stateId = ? and");
		sb.append(" model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? and model.candidateResult.rank = ?");
		if(partyId!=0l){
			sb.append(" and model.party.partyId = ?");
		}
		Query queryObject = getSession().createQuery(sb.toString());
		
		queryObject.setParameter(0, electionYear);
		queryObject.setParameter(1, stateId);
		queryObject.setParameter(2, electionType);
		queryObject.setParameter(3, rank);
		if(partyId!=0l){
			queryObject.setParameter(4,partyId);
		}
		
		return queryObject.list();
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
	
	@SuppressWarnings("unchecked")
	public List findAllCandidatesForAnElectionBytheElectionYearForLocalBody(String electionYear,Long stateId,String electionType) {
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
				" model.constituencyElection.constituency.localElectionBody.electionType.electionType" +	
				" from Nomination model where" +
				" model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.localElectionBody.district.state.stateId = ? and" +
				" model.constituencyElection.constituency.localElectionBody.electionType.electionType = ?",params); 
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
	
	@SuppressWarnings("unchecked")
	public List getAllPartiesForAnElectionYearForLocalBody(String electionYear,String electionType){
		Object[] params = {electionYear,electionType};
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName from Nomination model" +
				" where model.constituencyElection.election.electionYear = ? and " +
				" model.constituencyElection.constituency.localElectionBody.electionType.electionType = ?",params);
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
	
	public List getAllAssemblyElectionsInDistrict(Long districtId,String type) {
		Object[] params = {districtId,type};
		return getHibernateTemplate().find("select distinct model.constituencyElection.election.electionId," +
				"model.constituencyElection.election.electionScope.electionType.electionType, model.constituencyElection.election.electionYear from Nomination model " +
				"where model.constituencyElection.constituency.district.districtId = ? and model.constituencyElection.election.electionScope.electionType.electionType = ?", params);
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
		return getHibernateTemplate().find("select model.party.shortName,count(model.party.partyId),sum(model.candidateResult.votesEarned) ,model.party.partyId" +
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
	public List getConstituencyLevelPartyParticipatedLocalBodyElectionVotesInfoForIND(Long localBodyId,Long partyId,Long electionId){
		Object[] params = {localBodyId,electionId,partyId};
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId,"+
				"model.constituencyElection.constituency.name,"+
				"model.constituencyElection.constituencyElectionResult.validVotes,"+
				"model.constituencyElection.constituencyElectionResult.totalVotesPolled,"+
				"model.constituencyElection.constituencyElectionResult.totalVotes "+
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
				" and model.constituencyElection.election.electionYear = ? order by model.party.shortName",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getAllElectionYearsForAConstituency(Long constituencyId,String electionType){
		
		String isPartial = "0";
		Object[] params = {constituencyId,electionType,isPartial};
		return getHibernateTemplate().find("select distinct model.constituencyElection.election.electionYear" +
				" from Nomination model where model.constituencyElection.constituency.constituencyId = ?" +
				" and model.constituencyElection.election.electionScope.electionType.electionType = ? and"+
				" model.constituencyElection.election.isPartial is null or model.constituencyElection.election.isPartial = ? order by model.constituencyElection.election.electionYear desc",params);
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
			String electionYear, List<Long> constituencyIds,String percentage) {
		Query queryObject = getSession().createQuery("select model.candidateResult.votesPercengate, model.candidateResult.votesEarned, " + 
				"model.party.shortName, model.party.partyId, model.constituencyElection.constituency.name, " +
				"model.constituencyElection.constituency.constituencyId, model.constituencyElection.constituencyElectionResult.validVotes, " + 
				"model.candidateResult.rank, model.constituencyElection.constituency.district.districtId, " +
				"model.constituencyElection.constituency.district.districtName from Nomination model where " +
				"model.constituencyElection.election.electionYear =? and model.candidateResult.votesPercengate > ? and " +
				"model.constituencyElection.constituency.constituencyId in (:constituencyIds) " +
				"order by model.constituencyElection.constituency.district.districtName");
		queryObject.setParameter(0,electionYear);
		queryObject.setParameter(1,percentage);
		queryObject.setParameterList("constituencyIds",constituencyIds);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List findElectionResultsForAllPartiesInAssemblyConstituenciesByCriteria(
			String electionYear, List<Long> constituencyIds, List<Long> partyIds, 
			List<Long> districtIds, String query ) {
		Query queryObject = getSession().createQuery("select model.candidateResult.votesPercengate, model.candidateResult.votesEarned, " + 
				"model.party.shortName, model.party.partyId, model.constituencyElection.constituency.name, " +
				"model.constituencyElection.constituency.constituencyId, model.constituencyElection.constituencyElectionResult.validVotes, " + 
				"model.candidateResult.rank, model.constituencyElection.constituency.district.districtId, " +
				"model.constituencyElection.constituency.district.districtName from Nomination model where " +
				"model.constituencyElection.election.electionYear =? " +
				"and model.constituencyElection.constituency.constituencyId in (:constituencyIds) "+query+
				" order by model.constituencyElection.constituency.district.districtName");
		queryObject.setParameter(0,electionYear);
		queryObject.setParameterList("constituencyIds",constituencyIds);
		if(partyIds.size() > 0 && districtIds.size() > 0){
			queryObject.setParameterList("partyIds",partyIds);
			queryObject.setParameterList("districtIds",districtIds);
		}else if(partyIds.size() > 0)
			queryObject.setParameterList("partyIds",partyIds);
		else if(districtIds.size() > 0)
			queryObject.setParameterList("districtIds",districtIds);
			
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
	
	/*@SuppressWarnings("unchecked")
	public List<Election> findByElectionScopeIdAndPartyId(Long electionScopeId,
			String electionSubtypes,Long partyId){
		String isPartial="0";
		Object params[] = {electionScopeId, electionSubtypes,partyId,isPartial};
		return getHibernateTemplate().find("select distinct model.constituencyElection.election.electionId,model.constituencyElection.election.electionYear from Nomination model where model.constituencyElection.election.electionScope.electionScopeId = ? " +
				"and model.constituencyElection.election.elecSubtype = ? and model.party.partyId = ? and model.constituencyElection.election.isPartial is null or model.constituencyElection.election.isPartial =? order by model.constituencyElection.election.electionYear desc", params);
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Election> findByElectionScopeIdAndPartyId(Long electionScopeId,
			String electionSubtypes,Long partyId){
		//String isPartial="0";
		Object params[] = {electionScopeId, partyId ,electionSubtypes};
		return getHibernateTemplate().find("select distinct model.constituencyElection.election.electionId,model.constituencyElection.election.electionYear from Nomination model where model.constituencyElection.election.electionScope.electionScopeId = ? " +
				"and model.party.partyId = ? and model.constituencyElection.election.elecSubtype = ? and model.constituencyElection.election.isPartial is null order by model.constituencyElection.election.electionYear desc", params);
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
	
	public List getNominations(String str){
		return getHibernateTemplate().find(str);
	}
	
	@SuppressWarnings("unchecked")
	public List getVotesInfoForAConstituency(String constituencyId,String electionYear,String electionType)	{
		Object[] params = {electionYear,electionType};	
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(model.candidateResult.votesEarned),");
		sb.append(" model.constituencyElection.constituencyElectionResult.totalVotesPolled");
		sb.append(" from Nomination model where model.constituencyElection.constituency.constituencyId in (  " + constituencyId +")");
		sb.append(" and model.constituencyElection.election.electionYear = ?");
		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE) ||
				electionType.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE)){			
			sb.append(" and model.constituencyElection.constituency.electionScope.electionType.electionType = ? group by model.constituencyElection.constituency.constituencyId");
		}else{
			sb.append("and model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? group by model.constituencyElection.constituency.constituencyId");
		}		
		return getHibernateTemplate().find(sb.toString(),params);		
	}
	
	
	@SuppressWarnings("unchecked")
	public List getAllElectionIdsAndYearsForADistrict(Long districtId){
		return getHibernateTemplate().find("select model.constituencyElection.election.electionYear," +
				" model.constituencyElection.constituency.electionScope.electionType.electionType" +				
				" from Nomination model where model.constituencyElection.constituency.district.districtId = ? group by" +
				" model.constituencyElection.constituency.electionScope.electionType.electionType," +
				" model.constituencyElection.election.electionYear order by " +
				" model.constituencyElection.constituency.electionScope.electionType.electionType",districtId);
	}
			
	@SuppressWarnings("unchecked")
	public List<Object[]> findByFirstMiddleAndLastNames(String searchText,String sortOption,String order,Integer startIndex,Integer maxResult,String ids){
		
		StringBuffer queryBuffer = new StringBuffer("select model.candidate.lastname,model.party.shortName,"); 
		queryBuffer.append("model.constituencyElection.election.electionYear,model.constituencyElection.constituency.name, ");
		queryBuffer.append("model.constituencyElection.election.electionScope.electionType.electionType, ");
		queryBuffer.append("model.candidateResult.rank,model.candidate.candidateId from Nomination model where ");
		queryBuffer.append(" "+searchText+" ");
		queryBuffer.append("model.constituencyElection.election.electionId in ("+ids+") order by "+sortOption+" " +order);
		
		Query queryObject = getSession().createQuery(queryBuffer.toString());
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(maxResult);
		return queryObject.list();		
	}
	
	
	@SuppressWarnings("unchecked")
	public List totalSearchCount(String searchText, String ids,Long stateId){
		
		StringBuffer queryBuffer = new StringBuffer("select count(model.candidate.candidateId) from Nomination model where ");
		queryBuffer.append(" "+searchText+" ");
		queryBuffer.append("model.constituencyElection.election.electionId in ("+ids+") and ");
		queryBuffer.append("model.constituencyElection.constituency.state.stateId in ("+stateId+")");
		
		Query queryObject = getSession().createQuery(queryBuffer.toString());
		
		return queryObject.list();		
	}
	
	@SuppressWarnings("unchecked")
	public List findElectionResultsForAllCostituenciesByElectionTypeYearAndCountryId(Long electionTypeId, String year, Long countryId){
		Object[] params = {electionTypeId, year, countryId};
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId, " +//0
				"model.party.partyId, model.candidate.candidateId, model.nominationId, " +//1,2,3
				"model.constituencyElection.constituency.name, model.party.shortName, model.party.longName, " +//4,5,6
				"model.candidate.lastname, model.candidateResult.votesEarned, model.candidateResult.rank, " +//7,8,9
				"model.constituencyElection.constituencyElectionResult.totalVotes, " +//10
				"model.constituencyElection.constituencyElectionResult.validVotes from Nomination model where " +//11
				"model.constituencyElection.election.electionScope.electionType.electionTypeId = ? and " +
				"model.constituencyElection.election.electionYear = ? and  " +
				"model.constituencyElection.constituency.state.country.countryId = ?", params);
	}
	
	public List findElectionResultsForAllCostituenciesByElectionTypeYearAndStateId(Long electionTypeId, String year, Long stateId){
		Object[] params = {electionTypeId, year, stateId};
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId, " +
				"model.party.partyId, model.candidate.candidateId, model.nominationId, " +
				"model.constituencyElection.constituency.name, model.party.shortName, model.party.longName, " +
				"model.candidate.lastname, model.candidateResult.votesEarned, model.candidateResult.rank, " +
				"model.constituencyElection.constituencyElectionResult.totalVotes, " +
				"model.constituencyElection.constituencyElectionResult.validVotes from Nomination model where " +
				"model.constituencyElection.election.electionScope.electionType.electionTypeId = ? and " +
				"model.constituencyElection.election.electionYear = ? and  " +
				"model.constituencyElection.constituency.state.stateId = ?", params);
	}
	
	public List findElectionResultsForAllCostituenciesByElectionTypeYearAndDistrictId(Long electionTypeId, String year, Long districtId){
		Object[] params = {electionTypeId, year, districtId};
		return getHibernateTemplate().find("select model.constituencyElection.constituency.constituencyId, " +
				"model.party.partyId, model.candidate.candidateId, model.nominationId, " +
				"model.constituencyElection.constituency.name, model.party.shortName, model.party.longName, " +
				"model.candidate.lastname, model.candidateResult.votesEarned, model.candidateResult.rank, " +
				"model.constituencyElection.constituencyElectionResult.totalVotes, " +
				"model.constituencyElection.constituencyElectionResult.validVotes from Nomination model where " +
				"model.constituencyElection.election.electionScope.electionType.electionTypeId = ? and " +
				"model.constituencyElection.election.electionYear = ? and  " +
				"model.constituencyElection.constituency.district.districtId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List getCountOfAllCandidates(String electionYear,Long locationId,Long stateId,String electionType,Long rank,String locationType,String candidateType,String candidateDetails,Long partyId) {		
		StringBuffer queryBuffer = new StringBuffer();
		if(candidateDetails==null)
			queryBuffer.append("select count(model)");
		else
			queryBuffer.append("select count(model.candidateResult.candidateResultId)");
		
			queryBuffer.append(" from Nomination model where");
			queryBuffer.append(" model.constituencyElection.election.electionYear = ?");
			queryBuffer.append(" and model.constituencyElection.constituency.electionScope.electionType.electionType = ? ");
			
		if(locationId!=0l){
			if(locationType.equalsIgnoreCase(IConstants.COUNTRY_LEVEL))
				queryBuffer.append(" and model.constituencyElection.constituency.state.country.countryId = ? ");			
			else if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL))
				queryBuffer.append(" and model.constituencyElection.constituency.state.stateId = ?");
			else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL))
				queryBuffer.append(" and model.constituencyElection.constituency.district.districtId = ?");		
			else 
				queryBuffer.append(" and model.constituencyElection.constituency.constituencyId = ?");
		}
		if(stateId!=0l){
				queryBuffer.append(" and model.constituencyElection.constituency.state.stateId = ?");
		}
		
		if(candidateType.equalsIgnoreCase(IConstants.WINNER_CANDIDATES) || candidateType.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES))			
			queryBuffer.append(" and model.candidateResult.rank = ?");
		else if(candidateType.equalsIgnoreCase(IConstants.LOST_CANDIDATES))
			queryBuffer.append(" and model.candidateResult.rank != ?");
		
		if(partyId!=0l)
			queryBuffer.append(" and model.party.partyId = ? ");
		if(candidateDetails!=null)
			queryBuffer.append(" and model.candidateResult.marginVotesPercentage is not null");
		
		Query queryObject = getSession().createQuery(queryBuffer.toString());
			queryObject.setString(0,electionYear);
			queryObject.setString(1,electionType);
			Long flag=0l;
			if(locationId!=0l){
				queryObject.setLong(2,locationId);
				flag = 1l;
			}
			if(flag==1 && stateId!=0l){
				queryObject.setLong(3,stateId);
				flag = 2l;
			}else if(flag==0 && stateId!=0l){
				queryObject.setLong(2,stateId);
			}		
			if(candidateType.equalsIgnoreCase(IConstants.WINNER_CANDIDATES) || candidateType.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES) || candidateType.equalsIgnoreCase(IConstants.LOST_CANDIDATES)){
				if(flag==2l){
					flag = 3l;
					queryObject.setLong(4,rank);
				}else{
					queryObject.setLong(3,rank);
				}
			}
			if(partyId!=0l && flag == 3l)
				queryObject.setLong(5,partyId);
			else if(partyId!=0l)
				queryObject.setLong(4,partyId);
			
		return queryObject.list();	
	}
	
	public int updateMarginVotesAndPercentage(String marginPercentage,Double marginVotes,String electionYear,String electionType,Long constituencyId,Long candidateId){
		StringBuilder query = new StringBuilder();				
		query.append(" update Nomination model set model.candidateResult.marginVotesPercentage = ? , model.candidateResult.marginVotes  = ? where");
		query.append(" model.constituencyElection.election.electionYear = ?");
		query.append(" and model.constituencyElection.constituency.electionScope.electionType.electionType = ?");
		query.append(" and model.constituencyElection.constituency.constituencyId = ?");
		query.append(" and model.candidate.candidateId = ?");		
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
	public List getList(String electionYear,String electionType,Long constituencyId,Long candidateId){
		StringBuilder query = new StringBuilder();				
		query.append(" select model.candidateResult.candidateResultId from  Nomination model where");
		query.append(" model.constituencyElection.election.electionYear = ?");
		query.append(" and model.constituencyElection.constituency.electionScope.electionType.electionType = ?");
		query.append(" and model.constituencyElection.constituency.constituencyId = ?");
		query.append(" and model.candidate.candidateId = ?");		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setParameter(0, electionYear);
		queryObject.setParameter(1, electionType);
		queryObject.setParameter(2, constituencyId);
		queryObject.setParameter(3, candidateId);
		
		return queryObject.list();
	}
	
	

	public List getAllCandidateDetails(String electionYear,Long locationId,Long stateId,String electionType,
			Long rank,String locationType,String candidateType,Long partyId,
			int startIndex, int maxResult, String order, String columnName) {
		StringBuilder query = new StringBuilder();
		query.append("select model.candidate.lastname,");//0
		query.append(" model.constituencyElection.constituency.constituencyId,");//1
		query.append(" model.constituencyElection.constituency.name,");//2
		query.append(" model.party.longName,");//3
		query.append(" model.party.shortName,");//4
		query.append(" model.party.partyFlag,");//5
		query.append(" model.party.partyId,"); //6
		
		query.append(" model.candidateResult.votesEarned,");//7
		query.append(" model.candidateResult.votesPercengate,");//8
		query.append(" model.candidateResult.rank,");//9
	     
		query.append(" model.candidateResult.marginVotes,");//10
		query.append(" model.candidateResult.marginVotesPercentage,");//11
		
	    query.append(" model.constituencyElection.election.electionYear,");//12
	    
	    if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.GREATER_ELECTION_TYPE)){
	    	query.append(" model.constituencyElection.constituency.localElectionBody.electionType.electionType,");	//13	
	    }else{
	    	 query.append(" model.constituencyElection.constituency.electionScope.electionType.electionType,");	//13
	    }
	    
	   // query.append(" model.constituencyElection.constituency.electionScope.electionType.electionType,");//13
	    query.append(" model.candidate.candidateId");//14
	    
	    query.append(" from Nomination model where");
	    
	    query.append(" model.constituencyElection.election.electionYear = ?");
	    if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.GREATER_ELECTION_TYPE)){
	    	query.append(" and model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? ");		
	    }else{
	    	 query.append(" and model.constituencyElection.constituency.electionScope.electionType.electionType = ? ");
	    }
	   
			
		if(locationId!=0l){
			if(locationType.equalsIgnoreCase(IConstants.COUNTRY_LEVEL))
				query.append(" and model.constituencyElection.constituency.state.country.countryId = ? ");			
			else if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
				if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.GREATER_ELECTION_TYPE)){
					 //query.append(" and model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? ");
					query.append(" and model.constituencyElection.constituency.localElectionBody.district.state.stateId  = ?");
				}else{
					query.append(" and model.constituencyElection.constituency.state.stateId = ?");
				}
			}
			else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
				if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.GREATER_ELECTION_TYPE)){
					query.append(" and model.constituencyElection.constituency.localElectionBody.district.districtId = ?");		
				}else{
					query.append(" and model.constituencyElection.constituency.district.districtId = ?");	
				}
			}
					
			else 
				query.append(" and model.constituencyElection.constituency.constituencyId = ?");
		}
		if(stateId!=0l){
			if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.GREATER_ELECTION_TYPE)){
				 //query.append(" and model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? ");
				query.append(" and model.constituencyElection.constituency.localElectionBody.district.state.stateId  = ?");
			}else{
				query.append(" and model.constituencyElection.constituency.state.stateId = ?");
			}			
		}
		
		if(candidateType.equalsIgnoreCase(IConstants.WINNER_CANDIDATES) || candidateType.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES))			
			query.append(" and model.candidateResult.rank = ?");
		else if(candidateType.equalsIgnoreCase(IConstants.LOST_CANDIDATES))
			query.append(" and model.candidateResult.rank != ?");
		
		if(partyId!=0l)
			query.append(" and model.party.partyId = ? ");
			
		query.append("order by "+columnName+" "+order);
				
		Query queryObject = getSession().createQuery(query.toString());
			queryObject.setString(0,electionYear);
			queryObject.setString(1,electionType);
			Long flag=0l;
			if(locationId!=0l){
				queryObject.setLong(2,locationId);
				flag = 1l;
			}
			if(flag==1 && stateId!=0l){
				queryObject.setLong(3,stateId);
				flag = 2l;
			}else if(flag==0 && stateId!=0l){
				queryObject.setLong(2,stateId);				
			}		
			
			if(candidateType.equalsIgnoreCase(IConstants.WINNER_CANDIDATES) || candidateType.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES) || candidateType.equalsIgnoreCase(IConstants.LOST_CANDIDATES)){
				if(flag==2l){
					flag = 3l;
					queryObject.setLong(4,rank);
				}else{
					queryObject.setLong(3,rank);
				}
			}else{
				if(partyId!=0l){
					flag = 4l;
					if(stateId!=0l && locationId!=0l){
						queryObject.setLong(4,partyId);
					}else{
						queryObject.setLong(3,partyId);
					}
				}
			}
			
			if(flag!=4l){
				if(partyId!=0l && flag == 3l)
					queryObject.setLong(5,partyId);
				else if(partyId!=0l)
					queryObject.setLong(4,partyId);
			}
			
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(maxResult);
			
		return queryObject.list();	
	}
	
	public List getCountOfAllCandidateDetails(String electionYear,Long locationId,Long stateId,String electionType,
			Long rank,String locationType,String candidateType,Long partyId) {
		StringBuilder query = new StringBuilder();
		query.append("select count(model.candidate.lastname)");		
	    query.append(" from Nomination model where");	    
	    query.append(" model.constituencyElection.election.electionYear = ?");
	    
	    if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.GREATER_ELECTION_TYPE)){
	    	query.append(" and model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? ");		
	    }else{
	    	 query.append(" and model.constituencyElection.constituency.electionScope.electionType.electionType = ? ");
	    }
	 
			
		if(locationId!=0l){
			if(locationType.equalsIgnoreCase(IConstants.COUNTRY_LEVEL))
				query.append(" and model.constituencyElection.constituency.state.country.countryId = ? ");			
			else if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
				if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.GREATER_ELECTION_TYPE)){
					 //query.append(" and model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? ");
					query.append(" and model.constituencyElection.constituency.localElectionBody.district.state.stateId  = ?");
				}else{
					query.append(" and model.constituencyElection.constituency.state.stateId = ?");
				}
			}				
			else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
				if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.GREATER_ELECTION_TYPE)){
					query.append(" and model.constituencyElection.constituency.localElectionBody.district.districtId = ?");		
				}else{
					query.append(" and model.constituencyElection.constituency.district.districtId = ?");	
				}
			}					
			else 
				query.append(" and model.constituencyElection.constituency.constituencyId = ?");
		}
		if(stateId!=0l){
			if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.GREATER_ELECTION_TYPE)){
				 //query.append(" and model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? ");
				query.append(" and model.constituencyElection.constituency.localElectionBody.district.state.stateId  = ?");
			}else{
				query.append(" and model.constituencyElection.constituency.state.stateId = ?");
			}
		}
		
		if(candidateType.equalsIgnoreCase(IConstants.WINNER_CANDIDATES) || candidateType.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES))			
			query.append(" and model.candidateResult.rank = ?");
		else if(candidateType.equalsIgnoreCase(IConstants.LOST_CANDIDATES))
			query.append(" and model.candidateResult.rank != ?");
		
		if(partyId!=0l)
			query.append(" and model.party.partyId = ? ");
				
		Query queryObject = getSession().createQuery(query.toString());
			queryObject.setString(0,electionYear);
			queryObject.setString(1,electionType);
			Long flag=0l;
			if(locationId!=0l){
				queryObject.setLong(2,locationId);
				flag = 1l;
			}
			if(flag==1 && stateId!=0l){
				queryObject.setLong(3,stateId);
				flag = 2l;
			}else if(flag==0 && stateId!=0l){
				queryObject.setLong(2,stateId);				
			}			
			if(candidateType.equalsIgnoreCase(IConstants.WINNER_CANDIDATES) || candidateType.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES) || candidateType.equalsIgnoreCase(IConstants.LOST_CANDIDATES)){
				if(flag==2l){
					flag = 3l;
					queryObject.setLong(4,rank);
				}else{
					queryObject.setLong(3,rank);
				}
			}else{
				if(partyId!=0l){
					flag = 4l;
					if(stateId!=0l && locationId!=0l){
						queryObject.setLong(4,partyId);
					}else{
						queryObject.setLong(3,partyId);
					}
				}
			}			
			if(flag!=4l){
				if(partyId!=0l && flag == 3l)
					queryObject.setLong(5,partyId);
				else if(partyId!=0l)
					queryObject.setLong(4,partyId);
			}			
		return queryObject.list();	
	}
	
	
	public List getCountOfAllLocalBodyCandidates(String electionYear,Long locationId,Long stateId,String electionType,
			Long rank,String locationType,String candidateType,String candidateDetails,Long partyId) {
		StringBuilder query = new StringBuilder();
		
		query.append("select count(model)");		
	    query.append(" from Nomination model where");	    
	    query.append(" model.constituencyElection.election.electionYear = ?");
	    query.append(" and model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? ");
	    query.append(" and model.constituencyElection.constituency.localElectionBody.district.state.stateId  = ?");
	    
		if(locationId!=0l){			
			query.append(" and model.constituencyElection.constituency.localElectionBody.district.districtId = ?");
		}

		if(candidateType.equalsIgnoreCase(IConstants.WINNER_CANDIDATES) || candidateType.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES))			
			query.append(" and model.candidateResult.rank = ?");
		else if(candidateType.equalsIgnoreCase(IConstants.LOST_CANDIDATES))
			query.append(" and model.candidateResult.rank != ?");
		
		if(partyId!=0l)
			query.append(" and model.party.partyId = ? ");
		
		if(candidateDetails!=null)
			query.append(" and model.candidateResult.marginVotesPercentage is not null");
		
		Query queryObject = getSession().createQuery(query.toString());
			queryObject.setString(0,electionYear);
			queryObject.setString(1,electionType);
			queryObject.setLong(2,stateId);

		int flag=0;
		if(locationId!=0l){
			flag = 1;
			queryObject.setLong(3,locationId);				
		}				
			
		if(candidateType.equalsIgnoreCase(IConstants.WINNER_CANDIDATES) || candidateType.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES) || candidateType.equalsIgnoreCase(IConstants.LOST_CANDIDATES)){			
			if(flag == 1){
				flag = 2;
				queryObject.setLong(4,rank);
			}else{
				flag = 3;
				queryObject.setLong(3,rank);
			}
		}
		
		if(partyId!=0l){
			if(flag == 2){
				queryObject.setLong(5,partyId);
			}else if(flag == 3){
				queryObject.setLong(4,partyId);
			}else {
				queryObject.setLong(3,partyId);
			}		
		}
		return queryObject.list();	
	}
	
	public List getAllElectionYearsForAConstituency(List<Long> constituencyIds){
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyElection.constituency.constituencyId,");
		query.append(" model.constituencyElection.election.electionYear,model.candidateResult.votesEarned,");
		query.append(" model.party.partyId,model.party.shortName,model.candidate.lastname,");
		query.append(" model.constituencyElection.constituency.name");
		query.append(" from Nomination model");			
		query.append(" where model.constituencyElection.constituency.constituencyId in (:constituencyIds) and");
		query.append(" model.candidateResult.rank = 1");	
		
		//query.append("");	
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("constituencyIds", constituencyIds);
		
		return queryObject.list();	
	}
	
	
	public List getAllPartyResultsBasedOnMatchingCriteria(Long stateId,List<Long> constituencyIds,List<Long> partyIds,String electionSubType,String electionType,String searchText,String searchType){
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyElection.constituency.constituencyId,count(model.party.partyId),model.party.shortName,");
		query.append(" upper(model.constituencyElection.constituency.name),model.party.partyId from Nomination model");			
		query.append(" where model.constituencyElection.election.elecSubtype = ? ");
		
		query.append(" and model.candidateResult.rank = 1 ");
		
		if(searchType.equalsIgnoreCase(IConstants.DISTRICT)){
			query.append(" and model.constituencyElection.constituency.district.districtName like ? ");
		}else if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			query.append(" and model.constituencyElection.constituency.name like ? ");
		}
		
		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
			query.append(" and model.constituencyElection.constituency.state.stateId = ?");
		
		query.append(" and model.constituencyElection.constituency.constituencyId in (:constituencyIds) ");
		query.append(" and model.party.partyId in (:partyIds)");
		query.append(" and model.constituencyElection.constituency.deformDate is null");
		query.append(" group by model.constituencyElection.constituency.constituencyId,model.party.partyId order by model.constituencyElection.constituency.constituencyId ");	
		
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setString(0,electionSubType);
		Long count=0l;
		if(searchType.equalsIgnoreCase(IConstants.DISTRICT) || searchType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			queryObject.setString(1,""+searchText+"%");
			count++;	
		}
		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			if(count>0 && electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
				queryObject.setLong(2,stateId);
			}else{
				queryObject.setLong(1,stateId);
			}
		}
			
		
		queryObject.setParameterList("constituencyIds", constituencyIds);
		queryObject.setParameterList("partyIds", partyIds);
		return queryObject.list();	
	}
	
	public List getAllPartyResults(List<Long> constituencyIds,List<Long> partyIds,String electionSubType,String type){
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyElection.constituency.constituencyId,count(model.party.partyId),model.party.shortName,");
		query.append(" upper(model.constituencyElection.constituency.name),model.party.partyId from Nomination model");			
		query.append(" where model.constituencyElection.election.elecSubtype = ? and");
		query.append(" model.constituencyElection.constituency.constituencyId in (:constituencyIds) and");
		
		if(type.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
			query.append(" model.candidateResult.rank = 1 ");
		}else if(type.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES)){
			query.append(" model.candidateResult.rank != 1 ");
		}
		query.append(" and model.party.partyId in (:partyIds)");
		query.append(" and model.constituencyElection.constituency.deformDate is null");
		query.append(" group by model.constituencyElection.constituency.constituencyId,model.party.partyId order by model.constituencyElection.constituency.constituencyId ");	
		
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setString(0,electionSubType);
		queryObject.setParameterList("constituencyIds", constituencyIds);
		queryObject.setParameterList("partyIds", partyIds);
		return queryObject.list();	
	}
	
	public List getCountOfAllPartyResultsForParliament(Long stateId,List<Long> partyIds,String electionSubType,String type){
		StringBuilder query = new StringBuilder();
		query.append(" select count(model.party.partyId),model.party.shortName,model.party.partyId");
		query.append(" from Nomination model");			
		query.append(" where model.constituencyElection.election.elecSubtype = ? and ");
		query.append(" model.constituencyElection.election.electionScope.electionType.electionType =?");
		if(type.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
			query.append(" and model.candidateResult.rank = 1 ");
		}else if(type.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES)){
			query.append(" and model.candidateResult.rank != 1 ");
		}
		query.append(" and model.constituencyElection.constituency.state.stateId = ?");
		query.append(" and model.constituencyElection.constituency.startDate is not null");
		query.append(" and model.constituencyElection.constituency.deformDate is null");
		
		
		query.append(" group by model.party.partyId order by count(model.party.partyId) desc ");	
		
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setString(0,electionSubType);
		queryObject.setString(1,IConstants.PARLIAMENT_ELECTION_TYPE);
		queryObject.setLong(2,stateId);
		return queryObject.list();	
	}


	public List getCountOfAllPartyResults(List<Long> constituencyIds,List<Long> partyIds,String electionSubType,String type){
			StringBuilder query = new StringBuilder();
			query.append(" select count(model.party.partyId),model.party.shortName,model.party.partyId");
			query.append(" from Nomination model");			
			query.append(" where model.constituencyElection.election.elecSubtype = ? and");
			query.append(" model.constituencyElection.constituency.constituencyId in (:constituencyIds) and");
			
			if(type.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
				query.append(" model.candidateResult.rank = 1 ");
			}else if(type.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES)){
				query.append(" model.candidateResult.rank != 1 ");
			}
			//query.append(" and model.party.partyId in (:partyIds)");
			query.append(" and model.constituencyElection.constituency.deformDate is null");
			query.append(" group by model.party.partyId order by count(model.party.partyId) desc ");	
			
					
			Query queryObject = getSession().createQuery(query.toString());
			queryObject.setString(0,electionSubType);
			queryObject.setParameterList("constituencyIds", constituencyIds);
			//queryObject.setParameterList("partyIds", partyIds);
			return queryObject.list();	
		}
	
	public List getAllLatestPartyResults(List<Long> constituencyIds,List<Long> partyIds,String electionSubType,String type){
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyElection.constituency.constituencyId,count(model.party.partyId),model.party.shortName,");
		query.append(" upper(model.constituencyElection.constituency.name),model.party.partyId from Nomination model");			
		query.append(" where model.constituencyElection.election.elecSubtype = ? and");
		query.append(" model.constituencyElection.constituency.constituencyId in (:constituencyIds) and");
		
		if(type.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
			query.append(" model.candidateResult.rank = 1 ");
		}else if(type.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES)){
			query.append(" model.candidateResult.rank != 1 ");
		}
		//query.append(" and model.party.partyId in (:partyIds)");
		query.append(" and model.constituencyElection.constituency.deformDate is null");
		query.append(" group by model.constituencyElection.constituency.constituencyId,model.party.partyId order by model.constituencyElection.constituency.constituencyId ");	
		
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setString(0,electionSubType);
		queryObject.setParameterList("constituencyIds", constituencyIds);
		//queryObject.setParameterList("partyIds", partyIds);
		return queryObject.list();	
	}
	
	public List getAllPartyStrengthsResults(List<Long> constituencyIds,List<Long> partyIds,String electionSubType,String type,String electionType,Long stateId){
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyElection.constituency.constituencyId,count(model.party.partyId),model.party.shortName,");
		query.append(" upper(model.constituencyElection.constituency.name),model.party.partyId from Nomination model");			
		query.append(" where model.constituencyElection.election.elecSubtype = ? and");
		
		if(type.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
			query.append(" model.candidateResult.rank = 1 ");
		}else if(type.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES)){
			query.append(" model.candidateResult.rank != 1 ");
		}
		
		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			query.append(" and model.constituencyElection.constituency.constituencyId in (:constituencyIds) and  model.party.partyId in (:partyIds) ");
		}else{
			query.append(" and model.constituencyElection.constituency.electionScope.electionType.electionType = ?");
			query.append(" and model.constituencyElection.constituency.state.stateId = ? ");
		}		
		query.append(" and model.constituencyElection.constituency.deformDate is null");
		query.append(" and model.constituencyElection.constituency.startDate is null");//219 consts
		query.append(" group by model.constituencyElection.constituency.constituencyId,model.party.partyId order by model.constituencyElection.constituency.constituencyId ");	
		
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setString(0,electionSubType);
		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			queryObject.setParameterList("constituencyIds", constituencyIds);
			queryObject.setParameterList("partyIds", partyIds);
		}else{
			queryObject.setString(1,electionType);
			queryObject.setLong(2,stateId);
		}
		return queryObject.list();	
	}
	
	public List getPartyResultsForAParty(List<Long> constituencyIds,Long partyId,String electionSubType,Long electionIds,String type){
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyElection.constituency.constituencyId,count(model.party.partyId),model.party.shortName,");
		query.append(" upper(model.constituencyElection.constituency.name),model.party.partyId from Nomination model where");			
		
		if(type.equalsIgnoreCase(IConstants.WINNER_CANDIDATES))
			query.append(" model.party.partyId = ? ");
		else
			query.append(" model.party.partyId != ? ");
		
		query.append(" and model.constituencyElection.election.elecSubtype = ? ");
		if(electionIds!=null){
			query.append(" and model.constituencyElection.election.electionId = ?");
		}
		query.append(" and model.constituencyElection.constituency.startDate is null");//219 consts
		query.append(" and model.constituencyElection.constituency.constituencyId in (:constituencyIds) and");
		query.append(" model.candidateResult.rank = 1 and model.constituencyElection.constituency.deformDate is null group by model.constituencyElection.constituency.constituencyId,");
		
		query.append(" model.party.partyId order by model.constituencyElection.constituency.constituencyId");	
					
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong(0,partyId);
		queryObject.setString(1,electionSubType);
		if(electionIds!=null){
			queryObject.setLong(2, electionIds);	
		}		
		queryObject.setParameterList("constituencyIds", constituencyIds);
		
		return queryObject.list();	
	}
	
	
	public List getRequiredPartyResultsForAParty(List<Long> partyIds,String electionSubType,String electionType,Long electionId){
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyElection.constituency.constituencyId,count(model.party.partyId),model.party.shortName");
		query.append(" from Nomination model where model.constituencyElection.constituency.electionScope.electionType.electionType = ?");
		query.append(" and model.constituencyElection.election.elecSubtype = ? and model.constituencyElection.election.electionId =? and model.party.partyId in (:partyIds)");
		query.append(" and model.candidateResult.rank = 1 and model.constituencyElection.constituency.deformDate is null ");
		query.append(" group by model.constituencyElection.constituency.constituencyId");
		query.append(" order by model.constituencyElection.constituency.constituencyId");	
					
		Query queryObject = getSession().createQuery(query.toString());	
		queryObject.setString(0,electionType);
		queryObject.setString(1,electionSubType);
		queryObject.setLong(2,electionId);
		queryObject.setParameterList("partyIds",partyIds);
		return queryObject.list();	
	}
	
	public List getAllPartiesStrengths(String electionType,Long stateId,List<String> electionYears,String electionSubType,String partyType,Long partyId,String type){
		StringBuilder query = new StringBuilder();
			query.append(" select count(model),model.party.partyId,model.party.shortName from Nomination model where");	
		
		if(! partyType.equalsIgnoreCase(IConstants.ALL_PARTIES)){
			query.append(" model.party.partyId = ? and");
		}
			
		if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType)){
			query.append(" model.constituencyElection.election.electionId in ( select model2.electionId from Election model2 where");	
			query.append(" model2.electionScope.state.stateId = ? and model2.electionScope.electionType.electionType = ? and model2.elecSubtype = ? ");
			
		}else{
			query.append(" model.constituencyElection.constituency.state.stateId = ? and model.constituencyElection.election.electionId in ");
			query.append(" ( select model2.electionId from Election model2 where");	
			query.append(" model2.electionScope.electionType.electionType = ? and model2.elecSubtype = ? ");
		}		
		
		query.append(" and model2.electionYear in (:electionYears)) ");
		
		if(type.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
			query.append(" and model.candidateResult.rank = 1 ");
		}else if(type.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES)){
			query.append(" and model.candidateResult.rank != 1 ");
		}
		query.append(" group by model.party.partyId  order by count(model) desc");
				
		Query queryObject = getSession().createQuery(query.toString());		
		
		if(! partyType.equalsIgnoreCase(IConstants.ALL_PARTIES)){
			queryObject.setLong(0,partyId);	
			queryObject.setLong(1,stateId);	
			queryObject.setString(2,electionType);
			queryObject.setString(3,electionSubType);
		}else{
			queryObject.setLong(0,stateId);	
			queryObject.setString(1,electionType);
			queryObject.setString(2,electionSubType);	
		}	
		
		queryObject.setParameterList("electionYears", electionYears);
		return queryObject.list();	
	}
	
	
	public List getAllCandidateDetailsForAConstituency(List<Long> constIds,Long partyId,String electionSubType,Long elecId,String type){
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyElection.constituency.constituencyId,upper(model.constituencyElection.constituency.name),");
		query.append(" model.party.partyId,model.party.partyFlag,model.constituencyElection.election.electionYear,");
		query.append(" model.candidateResult.votesEarned,model.candidate.lastname,model.party.shortName");
		query.append(" from Nomination model where");	
			
		query.append(" model.constituencyElection.election.elecSubtype =? and model.candidateResult.rank = 1 and");
				
		if(elecId!=null){
			query.append(" model.constituencyElection.election.electionId =? and ");
		}
		
		
		if(type.equalsIgnoreCase(IConstants.WINNER_CANDIDATES))
			query.append(" model.party.partyId = ? and ");
		else if(type.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES))
			query.append(" model.party.partyId != ? and ");	
		
		
		query.append(" model.constituencyElection.constituency.constituencyId in (:constituencyIds)");
		
		
		Query queryObject = getSession().createQuery(query.toString());	
		
		queryObject.setString(0,electionSubType);
		if(elecId!=null){
			queryObject.setLong(1,elecId);
			if(type.equalsIgnoreCase(IConstants.WINNER_CANDIDATES) || type.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES))
				queryObject.setLong(2,partyId);	
		}else{
			if(type.equalsIgnoreCase(IConstants.WINNER_CANDIDATES) || type.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES))
				queryObject.setLong(1,partyId);	
		}
		queryObject.setParameterList("constituencyIds", constIds);
		return queryObject.list();
	}
	
	
	public List getAllAllianceCandidateDetailsForAConstituency(List<Long> constIds,List<Long> partyId,Long electionId,String type){
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyElection.constituency.constituencyId,upper(model.constituencyElection.constituency.name),");
		query.append(" model.party.partyId,model.party.partyFlag,model.constituencyElection.election.electionYear,");
		query.append(" model.candidateResult.votesEarned,model.candidate.lastname");
		query.append(" from Nomination model where model.constituencyElection.election.electionId = ? ");	
		
		if(type.equalsIgnoreCase(IConstants.WINNER_CANDIDATES))
			query.append(" and model.party.partyId in (:partyId) ");
		else //if(type.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES))
			query.append(" and model.party.partyId not in (:partyId) ");
		
		query.append(" and model.candidateResult.rank = 1 and");
		query.append(" model.constituencyElection.constituency.constituencyId in (:constituencyIds)");
		
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong(0,electionId);
		
		queryObject.setParameterList("partyId",partyId);
		queryObject.setParameterList("constituencyIds", constIds);
				
		return queryObject.list();
	}
	
	
	public List<Long> getCountOfAllAllianceCandidateDetailsForAConstituency(List<Long> constIds,List<Long> partyId,Long electionId,String type){
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyElection.constituency.constituencyId");
		query.append(" from Nomination model where model.constituencyElection.election.electionId = ? ");	
		
		if(type.equalsIgnoreCase(IConstants.WINNER_CANDIDATES))
			query.append(" and model.party.partyId in (:partyId) ");
		else //if(type.equalsIgnoreCase(IConstants.SUCCESSOR_CANDIDATES))
			query.append(" and model.party.partyId not in (:partyId) ");
		
		query.append(" and model.candidateResult.rank = 1 and");
		query.append(" model.constituencyElection.constituency.constituencyId in (:constituencyIds)");
		
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong(0,electionId);
		
		queryObject.setParameterList("partyId",partyId);
		queryObject.setParameterList("constituencyIds", constIds);
				
		return queryObject.list();
	}
	
	public List getAllAllianceCandidateDetailsForAConstituency(List<Long> constIds,Long partyId,List<Long> electionIds){
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyElection.constituency.constituencyId,upper(model.constituencyElection.constituency.name),");
		query.append(" model.party.partyId,model.party.partyFlag,model.constituencyElection.election.electionYear,");
		query.append(" model.candidateResult.votesEarned,model.candidate.lastname");
		query.append(" from Nomination model where");	
		query.append(" model.party.partyId=?  and model.candidateResult.rank = 1 and");
		query.append(" model.constituencyElection.election.electionId in (:electionIds) and");
		query.append(" model.constituencyElection.constituency.constituencyId in (:constituencyIds)");
		
		
		Query queryObject = getSession().createQuery(query.toString());	
		queryObject.setLong(0,partyId);	
		queryObject.setParameterList("electionIds", electionIds);
		queryObject.setParameterList("constituencyIds", constIds);
		return queryObject.list();
	}
	
	public List getCountOfWonConstituency(List<Long> constIds,List<Long> partyIds,Long electionId){
		StringBuilder query = new StringBuilder();
		query.append(" select count(model)");		
		query.append(" from Nomination model where");	
		query.append(" model.constituencyElection.election.electionId = ? and");
		query.append(" model.party.partyId in (:partyIds) and model.candidateResult.rank = 1 and");
		query.append(" model.constituencyElection.constituency.constituencyId in (:constituencyIds)");
				
		Query queryObject = getSession().createQuery(query.toString());	
		queryObject.setLong(0,electionId);		
		queryObject.setParameterList("partyIds",partyIds);
		queryObject.setParameterList("constituencyIds", constIds);
		return queryObject.list();
	}
	

	public List getAllAllianceCandidateDetails(List<Long> constIds,Long electionId,String type,Long stateId,String electionType){
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyElection.constituency.constituencyId,upper(model.constituencyElection.constituency.name),");
		query.append(" model.party.partyId,model.party.partyFlag,model.constituencyElection.election.electionYear,");
		query.append(" model.candidateResult.votesEarned,model.candidate.lastname");
		query.append(" from Nomination model where model.constituencyElection.election.electionId = ? ");	
				
		if(type.equalsIgnoreCase(IConstants.ALL)){
			query.append(" and model.candidateResult.rank = 1 ");
			query.append(" and model.constituencyElection.constituency.constituencyId in (:constituencyIds)");
		}else{
			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
				query.append(" and model.constituencyElection.constituency.state.stateId = ?");
			
			query.append(" and model.candidateResult.rank != 1 ");
			query.append(" and model.constituencyElection.constituency.constituencyId not in (:constituencyIds)");
			query.append(" and model.constituencyElection.constituency.startDate is null and ");
			query.append(" and model.constituencyElection.constituency.deformDate is null");
		}
		
		query.append(" order by model.party.partyId");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong(0,electionId);
		if(!type.equalsIgnoreCase(IConstants.ALL) && electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
			queryObject.setLong(1,stateId);
		
		queryObject.setParameterList("constituencyIds", constIds);
				
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List getNominationsInAnElection(Long electionId) {
		
		return getHibernateTemplate().find("select model.candidate.candidateId,model from Nomination model where model.constituencyElection.election.electionId = ?",electionId);
	}
	
	/**
	 * DAO Method returns Candidate Nomination Details In An Election In A Constituency
	 */
	@SuppressWarnings("unchecked")
	public List getCandidateNominationDetailsInAnElection(Long constiElecId) {
		
		StringBuilder query = new StringBuilder();
		
		query.append("select model.nominationId,model.party.partyId,model.party.shortName,model.party.partyFlag,");
		query.append("model.assets,model.liabilities,model.criminalCharges,model.candidate.candidateId,");
		query.append("model.candidate.firstname,model.candidate.lastname,model.candidate.gender,");
		query.append("model.candidate.education, upper(model.constituencyElection.constituency.name)," +
				"model.constituencyElection.constituency.electionScope.electionType.electionType, " +
				"model.constituencyElection.election.electionYear from Nomination model " +
				"where model.constituencyElection.constiElecId = ? ");
		query.append("order by model.candidate.lastname asc");
		
		
		Query queryObject = getSession().createQuery(query.toString());	
		queryObject.setParameter(0,constiElecId);
		
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getElectionYearsBasedOnParty(Long stateId,Long partyId,Long electionTypeId)
	{
		Object[] param = {stateId,partyId,electionTypeId};
		return getHibernateTemplate().find("select distinct(model.constituencyElection.election.electionYear) from Nomination model where " +
				" model.constituencyElection.constituency.state.stateId = ? and model.party.partyId = ? and model.constituencyElection.election.electionScope.electionType.electionTypeId = ?" +
				" order by model.constituencyElection.election.electionYear desc ",param);
	}

	
	public List<Object[]> getCandidatesToMapWithUser(String gender,String name,Long constituencyId,Long userId,Long stateId)
	{
		StringBuilder query = new StringBuilder();
		query.append("select distinct model.candidate.candidateId,model.candidate.lastname  from Nomination model " +
		" where model.candidate.candidateId not in(select model1.candidate.candidateId from UserCandidateRelation model1" +
		" where model1.registration.registrationId=:userId )");
		
		if(name!= null && name.trim().length()>0)
			query.append("  and model.candidate.lastname like '%"+name+"%' ");
		
		if(gender!= null && gender.trim().length()>0)
			query.append("  and model.candidate.gender=:gender ");	
		
		if(constituencyId!= null && constituencyId>0L)
			query.append("  and model.constituencyElection.constituency.constituencyId=:constituencyId ");	
		
		if(stateId!= null && stateId>0L)
			query.append("  and model.constituencyElection.constituency.state.stateId=:stateId ");
		
		query.append("  order by model.candidate.lastname asc ");	
		
		 Query queryObject = getSession().createQuery(query.toString());
		 
		 queryObject.setLong("userId", userId);
		 
		 if(gender!= null && gender.trim().length()>0)
		 queryObject.setString("gender",gender);
		 
		 if(constituencyId!= null && constituencyId>0L)
			 queryObject.setLong("constituencyId", constituencyId);	 
		 
		 if(stateId!= null && stateId>0L)
			 queryObject.setLong("stateId", stateId);	 
		 
		 return	queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> selectData(Long electionId)
	{
		return getHibernateTemplate().find("select model.party.shortName,model.candidate.lastname,model.candidate.gender,model.candidateResult.rank from Nomination model where " +
				" model.constituencyElection.election.electionId = ? order by model.party.shortName ",electionId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGenderWiseElectionResultOfParties(Long electionId,List<Long> partiesList)
	{
		Query query = getSession().createQuery("select model.party.partyId,model.party.shortName,model.candidate.gender,model.candidateResult.rank,model.candidateResult.votesEarned," +
				" model.constituencyElection.constituencyElectionResult.validVotes from Nomination model where model.constituencyElection.election.electionId = ? " +
				" and model.party.partyId in (:partiesList) " +
				" order by model.party.shortName");
		query.setParameter(0,electionId);
		query.setParameterList("partiesList",partiesList);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGenderWiseElectionResultOfParties(Long electionId)
	{
		Query query = getSession().createQuery("select model.party.partyId,model.party.shortName,model.candidate.gender,model.candidateResult.rank,model.candidateResult.votesEarned," +
				" model.constituencyElection.constituencyElectionResult.validVotes from Nomination model where model.constituencyElection.election.electionId = ? order by model.party.shortName");
		query.setParameter(0,electionId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyAreaTypeWiseElectionResultOfParties(Long electionId)
	{
		Query query = getSession().createQuery("select model.party.partyId,model.party.shortName,model.constituencyElection.constituency.areaType,model.candidateResult.rank,model.candidateResult.votesEarned," +
				" model.constituencyElection.constituencyElectionResult.validVotes from Nomination model where model.constituencyElection.election.electionId = ? order by model.party.shortName");
		query.setParameter(0,electionId);
		return query.list();
	}
		
	public List<Object[]> getTopVotesGainedCandidates(Long electionId,int maxResult){
		StringBuilder query = new StringBuilder();
		query.append("select model.candidate.candidateId,model.candidate.lastname,model.party.partyId,model.party.shortName,model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.votesEarned,model.constituencyElection.constituency.constituencyId," +
		" model.constituencyElection.constituency.name ,model.candidateResult.votesPercengate from Nomination model where model.constituencyElection.election.electionId = :electionId ");
		
		query.append("  order by model.candidateResult.votesEarned desc ");	
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong("electionId",electionId);		
		
		queryObject.setMaxResults(maxResult);
         return queryObject.list();
	}	
	
	public List<Object[]> getHighestLowestMarginVotesInanElection(Long electionId,int maxResult,String type)
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.candidate.candidateId,model.candidate.lastname,model.party.partyId,model.party.shortName,model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.votesEarned," +
				" model.constituencyElection.constituency.constituencyId,model.constituencyElection.constituency.name ,(model.candidateResult.votesEarned - model2.candidateResult.votesEarned) from Nomination model,Nomination model2 " +
				" where model.constituencyElection.constiElecId = model2.constituencyElection.constiElecId and model.constituencyElection.election.electionId = ? and model.candidateResult.rank = 1 and model2.candidateResult.rank = 2 ");
		
		if(type.equalsIgnoreCase("HighestMarginGained"))
            query.append("  order by (model.candidateResult.votesEarned - model2.candidateResult.votesEarned) desc ");	
		else if(type.equalsIgnoreCase("LowestMarginGained"))
            query.append("  order by (model.candidateResult.votesEarned - model2.candidateResult.votesEarned) asc ");	
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0,electionId);		
		
		queryObject.setMaxResults(maxResult);
         return queryObject.list();
	}
	public List<Object[]> getTopVotesGainedPercentage(Long electionId,int maxResult){
		StringBuilder query = new StringBuilder();
		query.append("select model.candidate.candidateId,model.candidate.lastname,model.party.partyId,model.party.shortName,model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.votesEarned," +
				" model.constituencyElection.constituency.constituencyId,model.constituencyElection.constituency.name ,(model.candidateResult.votesEarned / model.constituencyElection.constituencyElectionResult.validVotes *100) from Nomination model where model.constituencyElection.election.electionId = ? ");
				
            query.append("  order by (model.candidateResult.votesEarned / model.constituencyElection.constituencyElectionResult.validVotes *100) desc ");	
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0,electionId);		
		
		queryObject.setMaxResults(maxResult);
         return queryObject.list();
	}
	
}
