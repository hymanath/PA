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
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.columns.enums.CandidateColumnNames;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.utils.IConstants;


/**
*@author <a href="mailto:sai.basetti@gmail.com">Sai Krishna</a>
*@author <a href="mailto:sriharigopalnalam@gmail.com">Srihari</a>
*/
public class CandidateDAO extends GenericDaoHibernate<Candidate, Long> implements 
   ICandidateDAO {

	
	public CandidateDAO() {
		super(Candidate.class);
	}
	
	public List<Candidate> findByAddress(Object address) {
		
		return findByProperty(CandidateColumnNames.ADDRESS, address);
	}

	public List<Candidate> findByEducation(Object education) {
		
		return findByProperty(CandidateColumnNames.EDUCATION, education);
	}

	public List<Candidate> findByEmailAddress(Object emailAddress) {
		
		return findByProperty(CandidateColumnNames.EMAIL_ADDRESS, emailAddress);
	}

	public List<Candidate> findByFirstname(Object firstname) {
		
		return findByProperty(CandidateColumnNames.FIRST_NAME, firstname);
	}

	public List<Candidate> findByGender(Object gender) {
		
		return findByProperty(CandidateColumnNames.GENDER, gender);
	}

	public List<Candidate> findByLastname(Object lastname) {
		
		return findByProperty(CandidateColumnNames.LAST_NAME, lastname);
	}

	public List<Candidate> findByMiddlename(Object middlename) {
		
		return findByProperty(CandidateColumnNames.MIDDLE_NAME, middlename);
	}

	public List<Candidate> findByMobile(Object mobile) {
		
		return findByProperty(CandidateColumnNames.MOBILE, mobile);
	}

	public List<Candidate> findByPhone(Object phone) {
		
		return findByProperty(CandidateColumnNames.PHONE, phone);
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Candidate> findByProperty(CandidateColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from Candidate where " + propertyName.getValue() + "=?", value);		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Candidate> findByFirstMiddleAndLastNames(String[] names){
		
		StringBuffer queryBuffer = new StringBuffer("select model from Candidate model where ");
		
		for(int i = 0; i < names.length; i++){
			queryBuffer.append("model.firstname like '%"+names[i]+"%' or " );
			queryBuffer.append("model.lastname like '%"+names[i]+"%' or " );
		}
		
		String query = queryBuffer.toString().substring(0, queryBuffer.toString().length()-3);
		
		Query queryObject = getSession().createQuery(query);		
		queryObject.setMaxResults(IConstants.MAX_SEARCH_RESULTS_DISPLAY.intValue());
		return queryObject.list();		
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Candidate> findByFirstMiddleAndLastNames(String searchText,String sortOption,String order,Integer startIndex,Integer maxResult){
		
		StringBuffer queryBuffer = new StringBuffer("select model.candidate from Nomination model where ");
		
		queryBuffer.append("model.candidate.firstname like '%"+searchText+"%' or " );
		queryBuffer.append("model.candidate.lastname like '%"+searchText+"%' or " );
		queryBuffer.append("model.candidate.middlename like '%"+searchText+"%' and "); 
		queryBuffer.append("model.constituencyElection.election.electionId in (:ids) order by "+sortOption+" " +order);
		
		Query queryObject = getSession().createQuery(queryBuffer.toString());		
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(maxResult);
		return queryObject.list();		
	}*/
	
	
	/*@SuppressWarnings("unchecked")
	public List<Object[]> findByFirstMiddleAndLastNames(String searchText,String sortOption,String order,Integer startIndex,Integer maxResult,String ids){
		
		StringBuffer queryBuffer = new StringBuffer("select model.candidate.lastname,model.party.shortName,"); 
		queryBuffer.append("model.constituencyElection.election.electionYear,model.constituencyElection.constituency.name, ");
		queryBuffer.append("model.constituencyElection.election.electionScope.electionType.electionType, ");
		queryBuffer.append("model.candidateResult.rank from Nomination model where ");
		queryBuffer.append("model.candidate.firstname like '%"+searchText+"%' or " );
		queryBuffer.append("model.candidate.lastname like '%"+searchText+"%' or " );
		queryBuffer.append("model.candidate.middlename like '%"+searchText+"%' and "); 
		queryBuffer.append("model.constituencyElection.election.electionId in ("+ids+") order by "+sortOption+" " +order);
		
		Query queryObject = getSession().createQuery(queryBuffer.toString());
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(maxResult);
		return queryObject.list();		
	}
	
	@SuppressWarnings("unchecked")
	public List totalSearchCount(String searchText, String ids){
		
		StringBuffer queryBuffer = new StringBuffer("select count(model.candidate.candidateId) from Nomination model where ");
		queryBuffer.append("model.candidate.firstname like '%"+searchText+"%' or " );
		queryBuffer.append("model.candidate.lastname like '%"+searchText+"%' or " );
		queryBuffer.append("model.candidate.middlename like '%"+searchText+"%' and "); 
		queryBuffer.append("model.constituencyElection.election.electionId in ("+ids+")");
		
		Query queryObject = getSession().createQuery(queryBuffer.toString());
		
		return queryObject.list();		
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Long> findCandidatesSize(){
		Query queryObject = getSession().createQuery("select count(model.candidateId) from Candidate model");
		  
		 		 
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidateDetails(Long candidateId){
		
		return getHibernateTemplate().find("from Candidate model where model.candidateId = ?", candidateId);
		
	}

	@SuppressWarnings("unchecked")
	public Candidate findCandidateByLastName(String lastName){
		Candidate candidate = null;
		List<Candidate> list = getHibernateTemplate().find("from Candidate as model where model.lastname=?", lastName);
		if(list!=null && list.size()>0){
			candidate = list.get(0);
		}
		return candidate;
	}
	@SuppressWarnings("unchecked")
	public Long getCandidateByLastName(String lastName)
	{
		Query query = getSession().createQuery("select model.candidateId from Candidate model where model.lastname=?");
		query.setParameter(0, lastName);
		return (Long)query.uniqueResult();
	}
	
	
	
	public Object getCandidateNameByCandidateId(Long candidateId)
	{
		Query query = getSession().createQuery("select model.lastname from Candidate model where model.candidateId = ?");
		query.setParameter(0,candidateId);
		return query.uniqueResult();
	}
	
	public Integer findEmailInsertionInCandidate(String emailId,Long candidateId){
		
		StringBuffer query1 = new StringBuffer();
		query1.append("update Candidate model set model.emailAddress=? where model.candidateId=?");
		Query queryObject = getSession().createQuery(query1.toString());
		queryObject.setParameter(0,emailId);
		queryObject.setParameter(1,candidateId); 
		
		return queryObject.executeUpdate();	
	
	}
	
	public Integer findNameInsertionInCandidate(String candidateName,Long candidateId){
		
		StringBuffer query1 = new StringBuffer();
		query1.append("update Candidate model set model.lastname=? where model.candidateId=?");
		Query queryObject = getSession().createQuery(query1.toString());
		queryObject.setParameter(0,candidateName);
		queryObject.setParameter(1,candidateId); 
		
		return queryObject.executeUpdate();	
	
	}
	
	public List<Candidate> getEmailInfo(Long candidateId){
		
		return getHibernateTemplate().find("from Candidate model where model.candidateId = ?", candidateId);
	}
	/**
	 * This Method Is Used To Get The Candidate Details Based On Candidate Name Search Criteria ,
	 * gender , ConstituencyId and StateId
	 * @author Prasad Thiragabathina
	 * @param String gender
	 * @param 	String name
	 * @param Long constituencyId
	 * @param Long stateId
	 * @return List<Object[]>
	 */
	public List<Object[]> getCandidateDetailsBySearch(String gender,
			String name, Long constituencyId, Long stateId,String selectedType) {
		
		StringBuilder query = new StringBuilder();
		query.append("select distinct model.candidate.candidateId,model.candidate.lastname ,model.constituencyElection.constituency.name from Nomination model " +
		" where model.candidate.candidateId is not null");
		
		if(name!= null && name.trim().length()>0)
			query.append("  and model.candidate.lastname like '%"+name+"%' ");
		
		if(gender!= null && gender.trim().length()>0)
			query.append("  and model.candidate.gender=:gender ");	
		
		if(constituencyId!= null && constituencyId>0L)
			query.append("  and model.constituencyElection.constituency.constituencyId=:constituencyId ");	
		
		if(stateId!= null && stateId>0L)
			query.append("  and model.constituencyElection.constituency.state.stateId=:stateId ");
		
		if(selectedType.equalsIgnoreCase("mptc") || selectedType.equalsIgnoreCase("zptc"))
			query.append("  and model.constituencyElection.election.electionScope.electionType.electionTypeId = :elctionTypeId");
	
		
		
		query.append("  order by model.candidate.lastname asc ");	
		
		 Query queryObject = getSession().createQuery(query.toString());
		 
		 if(gender!= null && gender.trim().length()>0)
		 queryObject.setString("gender",gender);
		 
		 if(constituencyId!= null && constituencyId>0L)
			 queryObject.setLong("constituencyId", constituencyId);	 
		 
		 if(stateId!= null && stateId>0L)
			 queryObject.setLong("stateId", stateId);	 
		 
		 if(selectedType.equalsIgnoreCase("mptc"))
			 queryObject.setParameter("elctionTypeId", IConstants.MPTC_ELCTION_TYPE_ID);
		 else if(selectedType.equalsIgnoreCase("zptc"))
			 queryObject.setParameter("elctionTypeId", IConstants.ZPTC_ELCTION_TYPE_ID);
		
		 
		 return	queryObject.list();
	}

	/**
	 * This Method is used To get The Count Of Voters based on voterId
	 * @author Prasad Thiragabathina
	 * @param Long voterId
	 * @return List<Long>
	 */
	public List<Long> getinfluencingPeopleVoterId(Long voterId) {
		Query query = getSession().createQuery("select count(model.voter.voterId) from Candidate model where model.voter.voterId = ? ");
		query.setParameter(0, voterId);
		return query.list();
	}
	/**
	 * This Methos is Used To get the details of the candidate weather he is a voter r not
	 * @author Prasad Thiragabathina
	 * @param List<Long> voterIds
	 * @return List<Long>
	 */
	@SuppressWarnings("unchecked")
	public List<Long> findCandidatePeopleDetails(List<Long> voterIds) {
	String queryString = "select model.voter.voterId from Candidate model where model.voter.voterId in(:voterIds)";
	Query query = getSession().createQuery(queryString);
	query.setParameterList("voterIds", voterIds);
	return query.list();
	}
	
	public List<Object[]> getCandidateNameByCandidateIds(List<Long> candidateIds)
	{
		Query query = getSession().createQuery("select model.candidateId,model.lastname from Candidate model where model.candidateId in (:candidateIds)");
		query.setParameterList("candidateIds", candidateIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidatesForDebate(Long partyId)
	{
		Query query = getSession().createQuery("select distinct model.candidateId,model.lastname from Candidate model where " +
				" model.party.partyId = :partyId and model.isDebateCandidate = 'Y'");
		query.setParameter("partyId", partyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidatesForDebateParties(List<Long> partyIds)
	{
		StringBuffer query = new StringBuffer();
		
		query.append(" select distinct model.candidateId,model.lastname from Candidate model where model.isDebateCandidate = 'Y' ");
		
		if(partyIds != null && partyIds.size()>0)
			query.append(" and model.party.partyId in (:partyIds) ");
		
		Query queryObj = getSession().createQuery(query.toString());
		
		if(partyIds != null && partyIds.size()>0)
			queryObj.setParameterList("partyIds", partyIds);
		
		return queryObj.list();
	}
	public List<Long> getCandidateExistesOrNot(Long partyId,String name)
	{
		Query query = getSession().createQuery("select  distinct model.candidateId from Candidate model " +
				" where model.party.partyId = :partyId and model.lastname = :name and model.isDebateCandidate = 'Y' ");
		query .setParameter("partyId", partyId);
		query .setParameter("name", name);
		return query.list();
	}
	public List<Object[]> getCandidatesByName(){
		Query query = getSession().createQuery("select model.candidateId,model.lastname from Candidate model");
		return query.list();
	}
	public List<Object[]> getCandidatesByName(String candidateName)
	{
		Query query = getSession().createQuery("select model.candidateId,model.lastname from Candidate model where (model.lastname like '%"+candidateName+"%' )");
		return query.list();
	}
	
	
	public List<Object[]> getElectionInformationLocationWise(List<Long> yearsList, Long locationTypeId,
			Long locationValue,List<Long> electionScopeIds, List<Long> electionBodyIds,List<Long> tehsilIds,Long partyId) {

		StringBuilder sb = new StringBuilder();
		sb.append("select p.party_id as partyId,p.short_name as partyName ,e.election_id as electionId ,e.election_year as electionYear,"
				+ " et.election_type_id as electionTypeId,et.election_type as electionType,c1.constituency_id as locationId ,"
				+ " c1.name as locationName,cer.total_votes as totalVoters,cer.valid_votes as validVoters,"
				+ " cer.missing_votes as missedVotes,cer.rejected_votes as rejectedVotes,cr.votes_earned as earnedVotes,"
				+ " cr.votes_percentage as earnedVotesPerc,cr.margin_votes as marginVotes,count(n.party_id) as wonSeatsCount");

		sb.append(" from candidate c, party p,nomination n,constituency_election ce,constituency_election_result cer, " 
				+ " election e ,election_scope es,election_type et,constituency c1 ,candidate_result cr ");
		
		sb.append(" where e.election_scope_id = es.election_scope_id and "
				+ " es.election_type_id = et.election_type_id and "
				+ " ce.election_id = e.election_id and "
				+ " ce.consti_elec_id = cer.consti_elec_id AND "
				+ " n.consti_elec_id = ce.consti_elec_id AND "
				+ " n.party_id = p.party_id and"
				+ " n.candidate_id = c.candidate_id and "
				+ " ce.constituency_id = c1.constituency_id and"
				+ " cr.nomination_id = n.nomination_id and "
				+ " cr.rank = 1 and ");
		if(partyId != null && partyId.longValue()>0L)
			sb.append(" p.party_id = :partyId and ");
		
		if (yearsList != null && yearsList.size() > 0) {
			sb.append(" e.election_year in(:years) and");
		}
		if(electionScopeIds != null && electionScopeIds.size()>0){
			sb.append(" es.election_scope_id in (:electionScopeIds ) and ");
		}
		if (locationTypeId != null && locationTypeId.longValue() > 0l) {

			if (locationTypeId == 2l) {
				sb.append("  c1.state_id =:locationValue ");
			} else if (locationTypeId == 3l) {
				sb.append(" c1.district_id =:locationValue ");
			} else if (locationTypeId == 4l || locationTypeId==10l) {
				sb.append("(c1.constituency_id =:locationValue OR ( c1.tehsil_id in (:tehsilIds)) or "
						+ "(c1.local_election_body_id in(:electionBodys))) ");
			} else if (locationTypeId == 5l) {
				sb.append(" c1.tehsil_id  =:locationValue ");
			} else if (locationTypeId == 7l) {
				sb.append(" c1.local_election_body_id  =:locationValue ");
			}

		}
		sb.append(" group by et.election_type,e.election_year,p.party_id ORDER BY e.election_year,et.election_type");

		SQLQuery query = getSession().createSQLQuery(sb.toString());
				query.addScalar("partyId",Hibernate.LONG);
				query.addScalar("partyName",Hibernate.STRING);
				query.addScalar("electionId",Hibernate.LONG);
				query.addScalar("electionYear",Hibernate.STRING);
				query.addScalar("electionTypeId",Hibernate.LONG);
				query.addScalar("electionType",Hibernate.STRING);
				query.addScalar("locationId",Hibernate.LONG);
				query.addScalar("locationName",Hibernate.STRING);
				query.addScalar("totalVoters",Hibernate.LONG);
				query.addScalar("validVoters",Hibernate.LONG);
				query.addScalar("missedVotes",Hibernate.LONG);
				query.addScalar("rejectedVotes",Hibernate.LONG);
				query.addScalar("earnedVotes",Hibernate.LONG);
				query.addScalar("earnedVotesPerc",Hibernate.LONG);
				query.addScalar("marginVotes",Hibernate.LONG);
				query.addScalar("wonSeatsCount",Hibernate.LONG);
				
				
		if(yearsList!=null && yearsList.size()>0){
	         query.setParameterList("years", yearsList);
	       }
	      
	      if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValue !=null && locationValue.longValue()>0l){
	    	  query.setParameter("locationValue", locationValue);
	    	  if (locationTypeId == 4l || locationTypeId == 10l) {
	    		  query.setParameterList("tehsilIds",tehsilIds);
	    		  query.setParameterList("electionBodys",electionBodyIds);
				} 
	      }
	      if(electionScopeIds != null && electionScopeIds.size()>0){
	    	  query.setParameterList("electionScopeIds",electionScopeIds);
	      }
	      if(partyId != null && partyId.longValue()>0L)
	    	  query.setParameter("partyId", partyId);
		return query.list();

	}
}
