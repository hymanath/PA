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
			Long locationValue,List<Long> electionScopeIds, List<Long> electionBodyIds,List<Long> tehsilIds,List<Long> partyIds,List<String> subTypes) {

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
				+ " cr.rank = 1   and ");
		if(partyIds != null && partyIds.size()>0)
			sb.append(" p.party_id in (:partyIds) and ");
		
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
				sb.append("(c1.constituency_id =:locationValue OR ( c1.tehsil_id in (:tehsilIds)) ");
			if(electionBodyIds.size() > 0 && electionBodyIds !=null){
				sb.append(" or (c1.local_election_body_id in(:electionBodys))) ");
			}else{
				sb.append(" )");
			}
					
			} else if (locationTypeId == 5l) {
				sb.append(" c1.tehsil_id  =:locationValue ");
			} else if (locationTypeId == 7l) {
				sb.append(" c1.local_election_body_id  =:locationValue ");
			}

		}
		if(subTypes != null && subTypes.size() >0){
			sb.append(" and e.sub_type in  (:subTypes) ");
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
	    		  if(electionBodyIds.size() > 0 && electionBodyIds !=null){
	    			  query.setParameterList("electionBodys",electionBodyIds);
	    		  }
				} 
	      }
	      if(electionScopeIds != null && electionScopeIds.size()>0){
	    	  query.setParameterList("electionScopeIds",electionScopeIds);
	      }
	      if(partyIds != null && partyIds.size()>0L)
	    	  query.setParameterList("partyIds", partyIds);
	      if(subTypes != null && subTypes.size()>0){
	    	  query.setParameterList("subTypes", subTypes);
	      }
		return query.list();

	}

	@Override
	public List<Object[]> getElectionInformationLocationWiseVoterShare(List<Long> electionYrs, Long locationTypeId, Long locationValue,
			List<Long> electionScopeIds, Object object, List<String> subTypes, List<Long> parlimentIds,boolean isMandal) {
		SQLQuery query = null;
		StringBuilder sb = new StringBuilder();
		if(object !=null && object.toString().equalsIgnoreCase("lowLevels")){
			sb.append( " SELECT ");
			sb.append( " sum(br.valid_votes) as totalVotes, ");
			sb.append( " e.election_year as year,  ");
			sb.append( " et.election_type_id as electionTypeid, ");
			sb.append( " et.election_type as electionType,  ");
			sb.append( " e.election_id as electionId ");
			sb.append( " FROM ");
			sb.append( " election e ,  ");
			sb.append( " election_scope es , ");
			sb.append( " election_type et, ");
			sb.append( " constituency_election ce, ");
			sb.append( " booth_result br,  ");
			sb.append( " booth_constituency_election bce, ");
			sb.append( " booth b  ");
			sb.append( " where  ");
			sb.append( " e.election_scope_id=es.election_scope_id and  ");
			sb.append( " es.election_type_id = et.election_type_id and  ");
			sb.append( " ce.consti_elec_id = bce.consti_elec_id and  ");
			sb.append( " e.election_id = ce.election_id and  ");
			sb.append( " br.booth_constituency_election_id = bce.booth_constituency_election_id and  ");
			sb.append( " bce.booth_id = b.booth_id   ");
			if(electionYrs != null && electionYrs.size() > 0l){
				 sb.append(" and e.election_year in(:electionYrs)");
			}
			if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l){
				if(locationTypeId.longValue() ==5l){
					sb.append(" and b.tehsil_id =:locationValue");
				}else if(locationTypeId.longValue() ==6l){
					sb.append(" and b.panchayat_id =:locationValue");
				}else if(locationTypeId.longValue() ==7l){
					sb.append(" and b.local_election_body_id =:locationValue");
				}
			}
			if(electionScopeIds !=null && electionScopeIds.size()>0){
				sb.append(" AND e.election_scope_id IN (:electionScopeIds)");
			}
			if(subTypes.size()>0l && subTypes !=null){
				sb.append(" AND e.sub_type IN (:subTypes)");
			}

			sb.append( " GROUP BY e.election_year,e.election_id , et.election_type_id  ");
			sb.append( " ORDER BY e.election_year,e.election_id , et.election_type_id ");
		}else{
			sb.append("SELECT SUM(cer.votes_earned) as totalVotes, e.election_year as year, et.election_type_id as electionTypeid,et.election_type as electionType, e.election_id as electionId " +
					" FROM candidate_result cer " +
					" JOIN nomination n ON  cer.nomination_id = n.nomination_id  " +
					" JOIN constituency_election ce ON  ce.consti_elec_id = n.consti_elec_id  " +
					" JOIN constituency c ON c.constituency_id = ce.constituency_id " +
					" JOIN election_scope es ON es.election_scope_id = c.election_scope_id " +
					" JOIN election e ON e.election_id = ce.election_id " +
					" JOIN election_type et ON es.election_type_id = et.election_type_id WHERE e.sub_type in(:subTypes)  " );
			if(parlimentIds == null || parlimentIds.size() == 0){
				sb.append("and (c.district_id between 11 and 23) ");
				if(electionScopeIds !=null && electionScopeIds.size()>0){
					sb.append(" AND c.election_scope_id IN (:electionScopeIds)");
				}
			}
			else
				sb.append("and c.constituency_id in (:parlimentIds)  AND c.election_scope_id =1 ");
			if(electionYrs != null && electionYrs.size() > 0l){
				 sb.append("and e.election_year in(:electionYrs)");
			}
			
			if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l){
				if(locationTypeId ==2l){
					sb.append(" and c.state_id =:locationValue");
				}else if(locationTypeId ==3l){
					sb.append(" and c.district_id =:locationValue");
				}else if(locationTypeId ==4l){
					if(isMandal){
						sb.append(" and c.tehsil_id in( select distinct tehsil_id from tehsil_constituency where constituency_id=:locationValue)");
					}else{
						sb.append(" and c.constituency_id =:locationValue");
					}
						
				}else if(locationTypeId ==5l){
					sb.append(" and c.tehsil_id =:locationValue");
				}
			}
			sb.append(" GROUP BY e.election_year,e.election_id , et.election_type_id ORDER BY e.election_year,e.election_id , et.election_type_id");
		}
		
		query = getSession().createSQLQuery(sb.toString()).addScalar("totalVotes",Hibernate.LONG)
		.addScalar("year",Hibernate.LONG)
		.addScalar("electionTypeId", Hibernate.LONG).addScalar("electionType",Hibernate.STRING)
		.addScalar("electionId",Hibernate.LONG);
		
		if(electionYrs != null && electionYrs.size() > 0l){
			query.setParameterList("electionYrs",electionYrs);
		}
		if(subTypes.size()>0l && subTypes !=null){
			query.setParameterList("subTypes",subTypes);
		}
		if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l && locationTypeId.longValue() != 10L){
			query.setParameter("locationValue",locationValue);
		}
		
		if(parlimentIds != null && parlimentIds.size() > 0){
			query.setParameterList("parlimentIds",parlimentIds);
		}else if(electionScopeIds !=null && electionScopeIds.size()>0){
			query.setParameterList("electionScopeIds",electionScopeIds);
		}
		return query.list();
	}
	
	
	@Override
	public List<Object[]> getElectionInformationLocationWiseEarnedVoterShare(List<Long> electionYrs, Long locationTypeId, Long locationValue,
			List<Long> electionScopeIds, Object object, List<String> subTypes, List<Long> parlimentIds,boolean isMandal) {
		
		SQLQuery query = null;
		StringBuilder sb = new StringBuilder();
		if(object !=null && object.toString().equalsIgnoreCase("lowLevels")){
			sb.append(" SELECT ");
			sb.append(" p.party_id as partyId, ");
			sb.append(" p.short_name as partyName, ");
			sb.append(" SUM(cbr.votes_earned) as earnedVotes, ");
			sb.append(" et.election_type_id AS electionTypeId, ");
			sb.append(" et.election_type AS electionType,  ");
			sb.append(" e.election_year as electionyear, ");
			sb.append(" e.election_id as electionId ");
			sb.append(" FROM ");
			sb.append(" election e ,  ");
			sb.append(" election_scope es , ");
			sb.append(" election_type et, ");
			sb.append(" constituency_election ce, ");
			sb.append(" booth_result br,  ");
			sb.append(" booth_constituency_election bce, ");
			sb.append(" booth b , ");
			sb.append(" nomination n , party p , ");
			sb.append(" candidate_booth_result cbr  ");
			sb.append(" where  ");
			sb.append(" n.nomination_id = cbr.nomination_id and  ");
			sb.append(" n.party_id = p.party_id and  ");
			sb.append(" cbr.booth_constituency_election_id = bce.booth_constituency_election_id and  ");
			sb.append(" e.election_scope_id=es.election_scope_id and  ");
			sb.append(" es.election_type_id = et.election_type_id and  ");
			sb.append(" ce.consti_elec_id = bce.consti_elec_id and  ");
			sb.append(" e.election_id = ce.election_id and  ");
			sb.append(" br.booth_constituency_election_id = bce.booth_constituency_election_id and  ");
			sb.append(" bce.booth_id = b.booth_id   ");
			
			if(electionYrs != null && electionYrs.size() > 0l){
				 sb.append(" and e.election_year in(:electionYrs)");
			}
			if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l){
				if(locationTypeId.longValue() ==5l){
					sb.append(" and b.tehsil_id =:locationValue");
				}else if(locationTypeId.longValue() ==6l){
					sb.append(" and b.panchayat_id =:locationValue");
				}else if(locationTypeId.longValue() ==7l){
					sb.append(" and b.local_election_body_id =:locationValue");
				}
			}
			if(electionScopeIds !=null && electionScopeIds.size()>0){
				sb.append(" AND e.election_scope_id IN (:electionScopeIds)");
			}
			if(subTypes.size()>0l && subTypes !=null){
				sb.append(" AND e.sub_type IN (:subTypes)");
			}
			sb.append(" GROUP BY e.election_year,e.election_id, et.election_type_id ,p.party_id ORDER BY e.election_year,e.election_id , et.election_type_id ");

		}else{
			sb.append("SELECT p.party_id as partyId,p.short_name as partyName,SUM(cr.votes_earned) as earnedVotes,et.election_type_id AS electionTypeId," +
					" et.election_type AS electionType, e.election_year as electionyear,e.election_id as electionId" +
					" FROM party p, candidate c, nomination n, constituency_election ce, constituency_election_result cer, election e," +
					" election_scope es, election_type et, constituency c1, candidate_result cr " +
					" where e.election_scope_id = es.election_scope_id and " +
					" es.election_type_id = et.election_type_id and " +
					" ce.election_id = e.election_id and " +
					" ce.consti_elec_id = cer.consti_elec_id AND " +
					" n.consti_elec_id = ce.consti_elec_id AND " +
					" n.party_id = p.party_id and " +
					" n.candidate_id = c.candidate_id and " +
					" ce.constituency_id = c1.constituency_id and " +
					" cr.nomination_id = n.nomination_id  " );
			if(parlimentIds == null || parlimentIds.size() == 0){
				sb.append("and (c1.district_id between 11 and 23) ");
				if(electionScopeIds !=null && electionScopeIds.size()>0){
					sb.append(" AND c1.election_scope_id IN (:electionScopeIds)");
				}
			}
			else
				sb.append("and c1.constituency_id in (:parlimentIds)  AND c1.election_scope_id = 1 ");
			if(electionYrs != null && electionYrs.size() > 0l){
				 sb.append(" and e.election_year in(:electionYrs)");
			}
			
			if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l){
				if(locationTypeId ==2l){
					sb.append(" and c1.state_id =:locationValue");
				}else if(locationTypeId ==3l){
					sb.append(" and c1.district_id =:locationValue");
				}else if(locationTypeId ==4l){
					if(isMandal){
						sb.append(" and c1.tehsil_id in( select distinct tehsil_id from tehsil_constituency where constituency_id=:locationValue)");
					}else{
						sb.append("  and c1.constituency_id =:locationValue");
					}
				}else if(locationTypeId ==5l){
					sb.append(" and c1.tehsil_id =:locationValue");
				}
			}
			if(subTypes.size()>0l && subTypes !=null){
				sb.append(" AND e.sub_type IN (:subTypes)");
			}
			sb.append("GROUP BY e.election_year,e.election_id, et.election_type_id ,p.party_id ORDER BY e.election_year,e.election_id , et.election_type_id");
		}
		
		query = getSession().createSQLQuery(sb.toString())
				.addScalar("partyId",Hibernate.LONG)
				.addScalar("partyName",Hibernate.STRING)
				.addScalar("earnedVotes", Hibernate.LONG)
				.addScalar("electionTypeId",Hibernate.LONG)
				.addScalar("electionType",Hibernate.STRING)
				.addScalar("electionyear",Hibernate.LONG)
				.addScalar("electionId",Hibernate.LONG);
		
		if(electionYrs != null && electionYrs.size() > 0l){
			query.setParameterList("electionYrs",electionYrs);
		}
		
		if(subTypes.size()>0l && subTypes !=null){
			query.setParameterList("subTypes",subTypes);
		}
		if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l  && locationTypeId.longValue() != 10L){
			query.setParameter("locationValue",locationValue);
		}
		if(parlimentIds != null && parlimentIds.size() > 0){
			query.setParameterList("parlimentIds",parlimentIds);
		}else if(electionScopeIds !=null && electionScopeIds.size()>0){
			query.setParameterList("electionScopeIds",electionScopeIds);
		}
		return query.list();
	}

	@Override
	public List<Object[]> getElectionInformationLocationWiseWonedCount(List<Long> electionYrs, Long locationTypeId, Long locationValue,
			List<Long> electionScopeIds, List<String> subTypes, List<Long> parlimentIds,boolean isMandal) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT p.party_id as partyId,p.short_name as partyName, count(n.party_id) as woncount,et.election_type_id AS electionTypeId," +
				" et.election_type AS electionType, e.election_year as electionyear,e.election_id as electionId" +
				" FROM party p, candidate c, nomination n, constituency_election ce, constituency_election_result cer, election e," +
				" election_scope es, election_type et, constituency c1, candidate_result cr " +
				" where e.election_scope_id = es.election_scope_id and " +
				" es.election_type_id = et.election_type_id and " +
				" ce.election_id = e.election_id and " +
				" ce.consti_elec_id = cer.consti_elec_id AND " +
				" n.consti_elec_id = ce.consti_elec_id AND " +
				" n.party_id = p.party_id and " +
				" n.candidate_id = c.candidate_id and " +
				" ce.constituency_id = c1.constituency_id and " +
				" cr.nomination_id = n.nomination_id AND cr.rank = 1 " );
		if(parlimentIds == null || parlimentIds.size() == 0){
			sb.append("and (c1.district_id between 11 and 23) ");
			if(electionScopeIds !=null && electionScopeIds.size()>0){
				sb.append(" AND c1.election_scope_id IN (:electionScopeIds)");
			}
		}
		else
			sb.append("and c1.constituency_id in (:parlimentIds) AND c1.election_scope_id  = 1  ");
		
		if(electionYrs != null && electionYrs.size() > 0l){
			 sb.append(" and e.election_year in(:electionYrs)");
		}
		
		if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l && (parlimentIds == null || parlimentIds.size() == 0)){
			if(locationTypeId ==2l){
				sb.append(" and c1.state_id =:locationValue");
			}else if(locationTypeId ==3l){
				sb.append(" and c1.district_id =:locationValue");
			}else if(locationTypeId ==4l){
				if(isMandal){
					sb.append(" and c1.tehsil_id in( select distinct tehsil_id from tehsil_constituency where constituency_id=:locationValue)");
				}else{
					sb.append("  and c1.constituency_id =:locationValue");
				}
			}else if(locationTypeId ==5l){
				sb.append(" and c1.tehsil_id =:locationValue");
			}else if(locationTypeId == 10l ){
				if(isMandal){
					sb.append(" and c1.tehsil_id in (select distinct tehsil_id from tehsil_constituency where constituency_id in" +
							" (select distinct assembly_id from parliament_assembly where parliament_id =:locationValue)) ");
				}else{
					
					sb.append(" and  c1.constituency_id in (select distinct assembly_id from parliament_assembly where parliament_id =:locationValue)");
				}
			}
		}
		if(subTypes.size()>0l && subTypes !=null){
			sb.append(" AND e.sub_type IN (:subTypes)");
		}
		sb.append("GROUP BY e.election_year,e.election_id, et.election_type_id ,p.party_id ORDER BY e.election_year,e.election_id , et.election_type_id");
		SQLQuery query = getSession().createSQLQuery(sb.toString())
				.addScalar("partyId",Hibernate.LONG)
				.addScalar("partyName",Hibernate.STRING)
				.addScalar("woncount", Hibernate.LONG)
				.addScalar("electionTypeId",Hibernate.LONG)
				.addScalar("electionType",Hibernate.STRING)
				.addScalar("electionyear",Hibernate.LONG)
				.addScalar("electionId",Hibernate.LONG);
		
		if(electionYrs != null && electionYrs.size() > 0l){
			query.setParameterList("electionYrs",electionYrs);
		}
		
		if(subTypes.size()>0l && subTypes !=null){
			query.setParameterList("subTypes",subTypes);
		}
		if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l && (parlimentIds == null || parlimentIds.size() == 0) ){
			query.setParameter("locationValue",locationValue);
		}
		if(parlimentIds != null && parlimentIds.size() > 0){
			query.setParameterList("parlimentIds",parlimentIds);
		}else if(electionScopeIds !=null && electionScopeIds.size()>0){
			query.setParameterList("electionScopeIds",electionScopeIds);
		}
		
		return query.list();
	}
	
	public List<Object[]> getAvailableSeatsforElection(List<Long> yearsList ,Long locationTypeId,List<Long> locationValue,List<Long> electionScopeIds,List<String> subTypeList,String type){
		StringBuilder sb = new StringBuilder();
		
		sb.append("select e.election_scope_id,e.election_id,et.election_type,e.election_scope_id," +
				" e.election_year,count(ce.consti_elec_id) from constituency c," +
				" constituency_election ce,election e ,election_scope es ," +
				" election_type et where  ce.election_id is not null " );
		if(yearsList != null && yearsList.size()>0){
			sb.append(" and e.election_year in(:yearsList) ");
		}
		if(electionScopeIds != null && electionScopeIds.size()>0){
			sb.append(" and e.election_scope_id  in (:electionScopeIds)  " );
		  }
		 sb.append(" and e.election_scope_id = es.election_scope_id and et.election_type_id = es.election_type_id " +
				" and c.constituency_id = ce.constituency_id and ce.election_id = e.election_id " );
		if(subTypeList != null && subTypeList.size()>0){
			 sb.append(" and e.sub_type in(:subTypeList)" );
			}
		if(type != null && type.equalsIgnoreCase("parliament")){
			sb.append("and c.constituency_id in ("+IConstants.AP_PARLIAMENT_IDS_LIST_STR+")");
		}else {
			sb.append(" and (c.district_id BETWEEN 11 and 23)");
		}
		if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValue !=null && locationValue.size()>0l){
			if (locationTypeId.longValue() == 3l) {
				sb.append("  and c.district_id in(:locationValue) ");
			} else if (locationTypeId.longValue() == 4l || locationTypeId.longValue() == 10L) {
				sb.append(" and c.constituency_id in(:locationValue) ");
			}
			}
			
		sb.append(" group by ce.election_id " +
				" order by e.election_year,e.election_id,e.election_scope_id ");
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		if(electionScopeIds != null && electionScopeIds.size()>0){
			query.setParameterList("electionScopeIds", electionScopeIds);
		}
		if(subTypeList != null && subTypeList.size()>0){
			query.setParameterList("subTypeList", subTypeList);
		}
		if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValue !=null && locationValue.size()>0l && (locationTypeId.longValue() ==3l || locationTypeId.longValue()== 4l || locationTypeId.longValue() == 10L)){
	    	  query.setParameterList("locationValue", locationValue);
	      }	
		if(yearsList != null && yearsList.size()>0){
			query.setParameterList("yearsList", yearsList);
		}
		return query.list();
	}
	
	
	
	public List<Object[]> getParticipatedPartyListforElection(List<Long> yearsList ,Long locationTypeId,List<Long> locationValue,List<Long> electionScopeIds,List<String> subTypeList,String type){
		StringBuilder sb = new StringBuilder(); 
		
		sb.append(" SELECT " +
		          " e.election_scope_id as temp," +
		          " e.election_id as electionId," +
		          "  et.election_type as electionType," +
		          "  e.election_scope_id as electionScopeId," +
		          " e.election_year as electionYear," +
		          " n.party_id as partyId,p.short_name as partyName,count(n.party_id) as count," +
		          " p.party_flag as flag from constituency c , constituency_election ce,election e ,election_scope es ,election_type et ,nomination n ,party p " );
		if(locationTypeId != 2L && locationTypeId != 3L && locationTypeId != 10L && locationTypeId != 4L)
			sb.append(" ,booth b, booth_constituency_election bce, candidate_booth_result cbr ");
		
		sb.append(" where " );
		sb.append(" n.consti_elec_id = ce.consti_elec_id and " +
		          " n.party_id = p.party_id and " +
		          " e.election_scope_id = es.election_scope_id and " +
		          " et.election_type_id = es.election_type_id and " +
		          " ce.election_id = e.election_id  " );
		if(locationTypeId == 2L || locationTypeId ==  3L || locationTypeId ==  10L || locationTypeId ==  4L)
			sb.append(" and c.constituency_id = ce.constituency_id  " );
		else 
			sb.append(" and c.constituency_id = b.constituency_id and cbr.nomination_id = n.nomination_id and ce.consti_elec_id = bce.consti_elec_id and " +
					" bce.booth_id = b.booth_id  " );
		
		if (yearsList != null && yearsList.size() > 0) {
			sb.append(" and e.election_year in(:years) ");
		}
		if(electionScopeIds != null && electionScopeIds.size()>0){
			sb.append("and es.election_scope_id in (:electionScopeIds ) ");
		}
		if(subTypeList != null && subTypeList.size()>0){
			sb.append(" and e.sub_type in (:subTypeList) ");
		}
		if(type != null && type.equalsIgnoreCase("parliament")){
			sb.append("and c.constituency_id in ("+IConstants.AP_PARLIAMENT_IDS_LIST_STR+")");
		}else{
			sb.append(" and (c.district_id BETWEEN 11 and 23)");
		}
		if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValue !=null && locationValue.size()>0l){
			if (locationTypeId.longValue() == 3l) {
				sb.append("  and c.district_id in(:locationValue) ");
			} else if (locationTypeId.longValue() == 4l || locationTypeId.longValue() == 10l ) {
				sb.append(" and c.constituency_id in(:locationValue) ");
			
			}else if (locationTypeId.longValue() == 5l ) {
				sb.append(" and b.tehsil_id in(:locationValue) ");
			}
			else if (locationTypeId.longValue() == 6l ) {
				sb.append(" and b.panchayat_id in(:locationValue) ");
			
			}
			else if (locationTypeId.longValue() == 7l ) {
				sb.append(" and b.local_election_body_id in(:locationValue) ");
			}
		}
		
		sb.append(" group by e.election_scope_id,e.election_id,et.election_type,e.election_scope_id,e.election_year,n.party_id");
		sb.append(" order by e.election_scope_id,e.election_id,et.election_type,e.election_scope_id,e.election_year,n.party_id");
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		query.addScalar("temp",Hibernate.LONG);
		query.addScalar("electionId",Hibernate.LONG);
		query.addScalar("electionType",Hibernate.STRING);
		query.addScalar("electionScopeId",Hibernate.LONG);
		query.addScalar("electionYear",Hibernate.STRING);
		query.addScalar("partyId",Hibernate.LONG);
		query.addScalar("partyName",Hibernate.STRING);
		query.addScalar("count",Hibernate.LONG);
		query.addScalar("flag",Hibernate.STRING);
		 if(yearsList!=null && yearsList.size()>0){
			 query.setParameterList("years", yearsList);
	     }
		 if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValue !=null && locationValue.size()>0l && (locationTypeId.longValue() !=2L && locationTypeId != 10l)){
	    	  query.setParameterList("locationValue", locationValue);
	      }
		 
		 	if (locationTypeId.longValue() == 3l) {
			  query.setParameterList("locationValue", locationValue);
			} else if (locationTypeId.longValue() == 4l || locationTypeId.longValue() == 10l ) {
				  query.setParameterList("locationValue", locationValue);
			}else if (locationTypeId.longValue() == 5l ) {
				  query.setParameterList("locationValue", locationValue);
			}
			else if (locationTypeId.longValue() == 6l ) {
				  query.setParameterList("locationValue", locationValue);
			}
			else if (locationTypeId.longValue() == 7l ) {
				  query.setParameterList("locationValue", locationValue);
			}
		 
	      if(electionScopeIds != null && electionScopeIds.size()>0){
	    	  query.setParameterList("electionScopeIds",electionScopeIds);
	      }
	      if(subTypeList != null && subTypeList.size()>0){
	    	   query.setParameterList("subTypeList", subTypeList);
	      }
		return query.list();
	}
	
	
	public List<Object[]> getParticipatedPartyListforElectionDetails(List<Long> yearsList ,Long locationTypeId,List<Long> locationValue,List<Long> electionScopeIds,List<String> subTypeList,String type,String resultFor){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT "+ 
		          " e.election_scope_id as temp,e.election_id as electionId,et.election_type as electionType,e.election_scope_id as electionScopeId," +
		          " e.election_year as electionYear,n.party_id as partyId,p.short_name as partyName,count(n.party_id) as count,sum(cr.votes_earned) as sum, " +
		          " p.party_flag as flag " +
		          " from constituency c,constituency_election ce,election e ,election_scope es ," +
		          " election_type et ,nomination n ,party p ,candidate_result cr " );
		sb.append(" where cr.nomination_id = n.nomination_id  " );
		if(resultFor != null && resultFor.trim().equalsIgnoreCase("WONRESULT"))
			sb.append(" and cr.rank = 1  " );
		if(electionScopeIds != null && electionScopeIds.size()>0){
			sb.append("	and e.election_scope_id  in (:electionScopeIds) ");
		}
		sb.append(" and n.consti_elec_id = ce.consti_elec_id and " +
				  " n.party_id = p.party_id and " +
		          " e.election_scope_id = es.election_scope_id and  " +
	              " et.election_type_id = es.election_type_id and " +
		          " c.constituency_id = ce.constituency_id and " +
		          " ce.election_id = e.election_id  " );
		if(subTypeList != null && subTypeList.size()>0){
			sb.append(" and e.sub_type in(:subTypeList)");
		}
		if(type != null && type.equalsIgnoreCase("parliament")){
			sb.append(" and c.constituency_id in ("+IConstants.AP_PARLIAMENT_IDS_LIST_STR+")");
		}else{
			sb.append(" and (c.district_id BETWEEN 11 and 23)");
		}
		if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValue !=null && locationValue.size()>0l){
			if (locationTypeId.longValue() == 3l) {
				sb.append("  and c.district_id in(:locationValue) ");
			}else if (locationTypeId.longValue() == 4l  || locationTypeId.longValue() == 10l) {
				sb.append(" and c.constituency_id in(:locationValue)");
			}
		}
		if (yearsList != null && yearsList.size() > 0) {
			sb.append(" and e.election_year in(:years)");
		}
		
		sb.append(" group by e.election_scope_id,e.election_id,et.election_type,e.election_scope_id,e.election_year,n.party_id");
		sb.append("	order by e.election_scope_id,e.election_id,et.election_type,e.election_scope_id,e.election_year,n.party_id");
        SQLQuery query = getSession().createSQLQuery(sb.toString());
		query.addScalar("temp",Hibernate.LONG);
		query.addScalar("electionId",Hibernate.LONG);
		query.addScalar("electionType",Hibernate.STRING);
		query.addScalar("electionScopeId",Hibernate.LONG);
		query.addScalar("electionYear",Hibernate.STRING);
		query.addScalar("partyId",Hibernate.LONG);
		query.addScalar("partyName",Hibernate.STRING);
		query.addScalar("count",Hibernate.LONG);
		query.addScalar("sum",Hibernate.LONG);
		query.addScalar("flag",Hibernate.STRING);
		  if(yearsList!=null && yearsList.size()>0){
	         query.setParameterList("years", yearsList);
	      }
	      if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValue !=null && locationValue.size()>0l && 
	    		  (locationTypeId.longValue() ==3l || locationTypeId.longValue() ==4l)){
	    	  query.setParameterList("locationValue", locationValue);
	      }
	      if(electionScopeIds != null && electionScopeIds.size()>0){
	    	  query.setParameterList("electionScopeIds",electionScopeIds);
	      }
	      if(subTypeList != null && subTypeList.size()>0){
	    	  query.setParameterList("subTypeList", subTypeList);
	      }
		return query.list();
	}

	@Override
	public List<Object[]> getElectionInformationLocationWisedetailsForValidVotes(List<Long> electionYrs, Long locationTypeId, Long locationValue,
			List<Long> electionScopeIds, Object object, List<String> subTypes,List<Long> parlimentIds, String searchType,boolean localEleclBody, boolean isMandataData) {

		SQLQuery query = null;
		StringBuilder sbs = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		String queryStr= null;
		
		sbs.append( " SELECT e.election_year as year, et.election_type_id as electionTypeid,et.election_type as electionType, e.election_id as electionId ");
		if(object !=null && object.toString().equalsIgnoreCase("lowLevels")){
			sbs.append(" ,sum(br.valid_votes) as totalVotes");
			sbm.append("  FROM ");
			sbm.append( " election e ,  ");
			sbm.append( " election_scope es , ");
			sbm.append( " election_type et, ");
			sbm.append( " constituency_election ce, ");
			sbm.append( " booth_result br,  ");
			sbm.append( " booth_constituency_election bce, ");
			sbm.append( " booth b  ");
			sbe.append( " where  ");
			sbe.append( " e.election_scope_id=es.election_scope_id and  ");
			sbe.append( " es.election_type_id = et.election_type_id and  ");
			sbe.append( " ce.consti_elec_id = bce.consti_elec_id and  ");
			sbe.append( " e.election_id = ce.election_id and  ");
			sbe.append( " br.booth_constituency_election_id = bce.booth_constituency_election_id and  ");
			sbe.append( " bce.booth_id = b.booth_id ");
			
			if(electionYrs != null && electionYrs.size() > 0l){
				 sbe.append(" and e.election_year in(:electionYrs) ");
			}
			if(electionScopeIds !=null && electionScopeIds.size()>0){
				sbe.append(" AND e.election_scope_id IN (:electionScopeIds)");
			}
			if(subTypes.size()>0l && subTypes !=null){
				sbe.append(" AND e.sub_type IN (:subTypes)");
			}
			if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l || localEleclBody){
				if(locationTypeId ==4l && searchType.equalsIgnoreCase("panchayat")){
					sbs.append(" ,b.panchayat_id as locationId,p.panchayat_name as locationName ");
					sbm.append(" ,panchayat p");
					sbe.append(" and b.panchayat_id = p.panchayat_id and b.constituency_id =:locationValue");
					sbe.append( " GROUP BY e.election_id , et.election_type_id,b.panchayat_id  ");
					sbe.append( " ORDER BY e.election_id , et.election_type_id ");
				}else if(locationTypeId ==4l && !localEleclBody){
					sbs.append(" ,t.tehsil_id as locationId,t.tehsil_name as locationName ");
					sbm.append(" ,tehsil t");
					sbe.append(" and b.tehsil_id = t.tehsil_id and b.constituency_id =:locationValue and b.local_election_body_id is null");
					sbe.append( " GROUP BY e.election_id , et.election_type_id,b.tehsil_id  ");
					sbe.append( " ORDER BY e.election_id , et.election_type_id ");
				}else if(locationTypeId ==4l && localEleclBody){
					sbs.append(" ,b.local_election_body_id  as locationid, concat(l.name,'  ',et1.election_type) as locationName ");
					sbm.append(" ,local_election_body l, election_type et1");
					sbe.append(" and b.local_election_body_id= l.local_election_body_id and l.election_type_id=et1.election_type_id and " +
							" b.constituency_id =:locationValue and b.local_election_body_id is not null ");
					sbe.append( " GROUP BY e.election_id , et.election_type_id,b.local_election_body_id  ");
					sbe.append( " ORDER BY e.election_id , et.election_type_id ");
					
				}
				if(locationTypeId.longValue() ==5l){
					sbs.append(" ,b.panchayat_id as locationId,p.panchayat_name as locationName ");
					sbm.append(" ,panchayat p");
					sbe.append(" and b.panchayat_id = p.panchayat_id and b.tehsil_id =:locationValue");
					sbe.append( " GROUP BY e.election_id , et.election_type_id,b.panchayat_id  ");
					sbe.append( " ORDER BY e.election_id , et.election_type_id ");
				}else if(locationTypeId.longValue() ==6l){
					sbs.append(" ,b.panchayat_id as locationId,p.panchayat_name as locationName ");
					sbm.append(" ,panchayat p");
					sbe.append(" and b.panchayat_id = p.panchayat_id and b.panchayat_id =:locationValue");
					sbe.append( " GROUP BY e.election_id , et.election_type_id,b.panchayat_id  ");
					sbe.append( " ORDER BY e.election_id , et.election_type_id ");
				}else if(locationTypeId.longValue() ==7l){
					sbs.append(" ,b.local_election_body_id  as locationid, concat(l.name,'  ',et1.election_type) as locationName ");
					sbm.append(" ,local_election_body l, election_type et1");
					sbe.append(" and b.local_election_body_id= l.local_election_body_id and l.election_type_id=et1.election_type_id and b.local_election_body_id =:locationValue");
					sbe.append( " GROUP BY e.election_id , et.election_type_id,b.local_election_body_id ");
					sbe.append( " ORDER BY e.election_id , et.election_type_id ");
				}else if(locationTypeId.longValue() ==10l){
					sbs.append(" ,c2.constituency_id as locationid, c2.name as locationName ");
					sbm.append(" ,constituency c2");
					sbe.append(" and c2.constituency_id=b.constituency_id and c2.constituency_id in (select distinct p.assembly_id from parliament_assembly p where parliament_id =:locationValue)");
					sbe.append( " GROUP BY e.election_id , et.election_type_id,b.constituency_id ");
					sbe.append( " ORDER BY e.election_id , et.election_type_id ");
				}
			}
			
			queryStr =sbs.append(sbm).append(sbe).toString();
			
		}else{
			sbs.append(" ,sum(cer.valid_votes) as totalVotes");
			sbm.append(" FROM constituency_election_result cer " +
					" JOIN constituency_election ce ON  ce.consti_elec_id = cer.consti_elec_id " +
					" JOIN constituency c ON c.constituency_id = ce.constituency_id " +
					" JOIN election_scope es ON es.election_scope_id = c.election_scope_id " +
					" JOIN election e ON e.election_id = ce.election_id " +
					" JOIN election_type et ON es.election_type_id = et.election_type_id ");
			sbe.append (" WHERE e.sub_type in(:subTypes) " );
			
			if(electionYrs != null && electionYrs.size() > 0l){
				 sbe.append("and e.election_year in(:electionYrs)");
			}
			
			if(parlimentIds == null || parlimentIds.size() == 0 && !electionScopeIds.contains(1l)){
				sbe.append("and (c.district_id between 11 and 23) ");
				if(electionScopeIds !=null && electionScopeIds.size()>0 ){
					sbe.append(" AND c.election_scope_id IN (:electionScopeIds)");
				}
			}
			else{
				sbs.append(" ,c.constituency_id as locationId,c.name as locationName ");
				sbe.append("and c.constituency_id in (:parlimentIds)  AND c.election_scope_id =1 and c.state_id =:locationValue  GROUP BY c.constituency_id, ");
			}
			
			
			if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l){
				if(locationTypeId ==2l && parlimentIds == null && searchType.equalsIgnoreCase("district")){
					sbs.append(" ,c.district_id  as locationId,d.district_name as locationName ");
					sbm.append(" join district d on d.district_id=c.district_id ");
					sbe.append(" and c.state_id =:locationValue GROUP BY c.district_id,");
				}else if(locationTypeId ==2l && parlimentIds == null && searchType.equalsIgnoreCase("constituency")){
					sbs.append(" ,c.constituency_id as locationId,c.name as locationName ");
					sbe.append(" and c.state_id =:locationValue GROUP BY c.constituency_id,");
				}else if(locationTypeId ==3l){
					if(searchType.equalsIgnoreCase("mandal") && localEleclBody== false){
						sbs.append(" ,tc.tehsil_id as locationId,t.tehsil_name as locationName ");
						sbm.append(" join tehsil_constituency tc on tc.constituency_id=c.constituency_id " +
								" join tehsil t on t.tehsil_id =tc.tehsil_id ");
						sbe.append(" and c.district_id =:locationValue  GROUP BY tc.tehsil_id, ");
					}else {
						if(searchType.equalsIgnoreCase("mandal") && localEleclBody== true){
							sbs.append(" ,p1.local_election_body_id AS locationId, concat(p1.name,'-',et1.election_type) AS locationName ");
							sbm.append(" join tehsil_constituency tc ON tc.constituency_id = c.constituency_id JOIN local_election_body p1 ON p1.tehsil_id = tc.tehsil_id " +
									"Join election_type et1 on et1.election_type_id = p1.election_type_id ");
							sbe.append(" and c.district_id =:locationValue  GROUP BY tc.tehsil_id, ");
						}else{
							sbs.append(" ,c.constituency_id as locationId,c.name as locationName ");
							sbe.append(" and c.district_id =:locationValue  GROUP BY c.constituency_id,");
						}
					}
					
				}else if(locationTypeId ==4l && isMandataData){
						sbs.append(" ,c.constituency_id as locationId,c.name as locationName ");
						sbe.append(" and c.tehsil_id in(select distinct tehsil_id from tehsil_constituency where constituency_id=:locationValue)  GROUP BY c.constituency_id, ");
			
				}else if(locationTypeId ==5l && isMandataData){
					sbs.append(" ,c.constituency_id as locationId,c.name as locationName ");
					sbe.append(" and c.tehsil_id =:locationValue  GROUP BY c.constituency_id, ");
		
				}else if(locationTypeId ==10l){
					sbs.append(" ,c.constituency_id as locationId,c.name as locationName ");
					sbe.append(" and c.constituency_id in (select distinct p.assembly_id from parliament_assembly p where parliament_id =:locationValue) GROUP BY c.constituency_id,");
				}
			}
			sbe.append(" e.election_id , et.election_type_id ORDER BY e.election_id , et.election_type_id");
			queryStr =sbs.append(sbm).append(sbe).toString();
		}
		
		query = getSession().createSQLQuery(queryStr)
		.addScalar("year",Hibernate.LONG)
		.addScalar("electionTypeId", Hibernate.LONG).addScalar("electionType",Hibernate.STRING)
		.addScalar("electionId",Hibernate.LONG)
		.addScalar("totalVotes",Hibernate.LONG)
		.addScalar("locationId", Hibernate.LONG)
		.addScalar("locationName",Hibernate.STRING);
		
		if(electionYrs != null && electionYrs.size() > 0l){
			query.setParameterList("electionYrs",electionYrs);
		}
		if(subTypes.size()>0l && subTypes !=null){
			query.setParameterList("subTypes",subTypes);
		}
		if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l){
			query.setParameter("locationValue",locationValue);
		}
		
		if(parlimentIds != null && parlimentIds.size() > 0){
			query.setParameterList("parlimentIds",parlimentIds);
		}else if(electionScopeIds !=null && electionScopeIds.size()>0){
			query.setParameterList("electionScopeIds",electionScopeIds);
		}
		return query.list();
	}
	
	@Override
	public List<Object[]> getElectionInformationLocationWiseDetailEarnedVoterShare(List<Long> electionYrs, Long locationTypeId, Long locationValue,
			List<Long> electionScopeIds, Object object, List<String> subTypes, List<Long> parlimentIds, List<Long> partIds,String searchType,boolean localBody,boolean isMandataData) {
		
		SQLQuery query = null;
		StringBuilder sb = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		String queryStr=null;
		sb.append("SELECT p.party_id as partyId,p.short_name as partyName,et.election_type_id AS electionTypeId," +
				" et.election_type AS electionType, e.election_year as electionyear,e.election_id as electionId" );
				
		if(object !=null && object.toString().equalsIgnoreCase("lowLevels") || localBody){
			sb.append(",SUM(cbr.votes_earned) as earnedVotes");
			sbm.append(" FROM election e , election_scope es , election_type et, constituency_election ce, ");
			sbm.append(" booth_result br, booth_constituency_election bce,  booth b , nomination n , party p , candidate_booth_result cbr  ");
			
			sbe.append(" where  n.nomination_id = cbr.nomination_id and n.party_id = p.party_id and  ");
			sbe.append(" cbr.booth_constituency_election_id = bce.booth_constituency_election_id and  ");
			sbe.append(" e.election_scope_id=es.election_scope_id and es.election_type_id = et.election_type_id and  ");
			sbe.append(" ce.consti_elec_id = bce.consti_elec_id and  e.election_id = ce.election_id and  ");
			sbe.append(" br.booth_constituency_election_id = bce.booth_constituency_election_id and  ");
			sbe.append(" bce.booth_id = b.booth_id ");
			if(partIds !=null && partIds.size() != 0){
			sbe.append(" and n.party_id in (:partIds) ");
			}
			if(electionYrs != null && electionYrs.size() > 0l){
				sbe.append(" and e.election_year in(:electionYrs)");
			}
			if(electionScopeIds !=null && electionScopeIds.size()>0){
				sbe.append(" AND e.election_scope_id IN (:electionScopeIds)");
			}
			if(subTypes.size()>0l && subTypes !=null){
				sbe.append(" AND e.sub_type IN (:subTypes)");
			}
			if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l){
				
				if(locationTypeId.longValue() ==4l && searchType.equalsIgnoreCase("panchayat")){
					sb.append(" ,b.panchayat_id as locationId,pn.panchayat_name as locationName");
					sbe.append(" and b.panchayat_id=pn.panchayat_id and b.constituency_id =:locationValue ");
					sbm.append(" ,panchayat pn");
					sbe.append(" GROUP BY e.election_year,e.election_id, et.election_type_id ,p.party_id,b.panchayat_id ");
				}else if( locationTypeId.longValue() ==4l && localBody){
					sb.append(" ,b.local_election_body_id  as locationid, concat(l.name,'  ',et1.election_type) as locationName ");
					sbm.append(" ,local_election_body l, election_type et1");
					sbe.append(" and b.local_election_body_id= l.local_election_body_id and l.election_type_id=et1.election_type_id and " +
							" b.constituency_id =:locationValue and b.local_election_body_id is not null ");
					sbe.append(" GROUP BY e.election_year,e.election_id, et.election_type_id ,p.party_id,b.local_election_body_id ");
				}else if(locationTypeId.longValue() ==4l && !localBody){
					sb.append(" ,t.tehsil_id as locationId,t.tehsil_name as locationName");
					sbe.append(" and b.tehsil_id=t.tehsil_id and b.constituency_id =:locationValue and b.local_election_body_id is null ");
					sbm.append(" ,tehsil t");
					sbe.append(" GROUP BY e.election_year,e.election_id, et.election_type_id ,p.party_id,b.tehsil_id ");
				}else if(locationTypeId.longValue() ==5l){
					sb.append(" ,b.panchayat_id as locationId,pn.panchayat_name as locationName");
					sbe.append(" and b.panchayat_id=pn.panchayat_id and b.tehsil_id =:locationValue ");
					sbm.append(" ,panchayat pn");
					sbe.append(" GROUP BY e.election_year,e.election_id, et.election_type_id ,p.party_id,b.panchayat_id ");
				}else if(locationTypeId.longValue() ==6l){
					sb.append(" ,b.panchayat_id as locationId,pn.panchayat_name as locationName ");
					sbm.append(" ,panchayat pn");
					sbe.append(" and b.panchayat_id=pn.panchayat_id and b.panchayat_id =:locationValue");
					sbe.append(" GROUP BY e.election_year,e.election_id, et.election_type_id ,p.party_id,b.panchayat_id ");
				}else if(locationTypeId.longValue() ==7l){
					sb.append(" ,b.local_election_body_id  as locationid, concat(l.name,'  ',et1.election_type) as locationName ");
					sbm.append(" ,local_election_body l, election_type et1");
					sbe.append(" and b.local_election_body_id= l.local_election_body_id and l.election_type_id=et1.election_type_id and b.local_election_body_id =:locationValue");
					sbe.append(" GROUP BY e.election_year,e.election_id, et.election_type_id ,p.party_id,b.local_election_body_id ");
				}else if(locationTypeId.longValue() ==10l){
					sb.append(" ,c2.constituency_id as locationid, c2.name as locationName ");
					sbm.append(" ,constituency c2");
					sbe.append(" and c2.constituency_id=b.constituency_id and c2.constituency_id in (select distinct p.assembly_id from parliament_assembly p where parliament_id =:locationValue)");
					sbe.append( " GROUP BY e.election_id , et.election_type_id,b.constituency_id,n.party_id ");
				}
			}
			
			sbe.append(" ORDER BY e.election_year,e.election_id , et.election_type_id ");
			queryStr =sb.append(sbm).append(sbe).toString();

		}else{
			sb.append(",SUM(cr.votes_earned) as earnedVotes");
			sbm.append(" FROM party p, candidate c, nomination n, constituency_election ce, constituency_election_result cer, election e," +
					" election_scope es, election_type et, constituency c1, candidate_result cr ");
			sbe.append(" where e.election_scope_id = es.election_scope_id and  es.election_type_id = et.election_type_id and  ce.election_id = e.election_id and " +
					" ce.consti_elec_id = cer.consti_elec_id AND  n.consti_elec_id = ce.consti_elec_id AND " +
					" n.party_id = p.party_id and n.candidate_id = c.candidate_id and  ce.constituency_id = c1.constituency_id and  cr.nomination_id = n.nomination_id ");
			
			if(partIds !=null && partIds.size() != 0 ){
				sbe.append(" and n.party_id in (:partIds) ");
			}
			if(electionYrs != null && electionYrs.size() > 0l){
				 sbe.append(" and e.election_year in(:electionYrs)");
			}
			
			if(subTypes.size()>0l && subTypes !=null){
				sbe.append(" AND e.sub_type IN (:subTypes)");
			}
			if(parlimentIds == null || parlimentIds.size() == 0){
				sbe.append("and (c1.district_id between 11 and 23) ");
				if(electionScopeIds !=null && electionScopeIds.size()>0){
					sbe.append(" AND c1.election_scope_id IN (:electionScopeIds)");
				}
			}
			else{
				sb.append(" ,c1.constituency_id as locationId,c1.name as locationName ");
				sbe.append("and c1.constituency_id in (:parlimentIds) and c1.state_id =:locationValue AND c1.election_scope_id = 1 ");
				sbe.append(" GROUP BY c1.constituency_id,e.election_id, et.election_type_id ,p.party_id  ");
			
			}
			if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l){
				if(locationTypeId.longValue() ==2l && parlimentIds == null  && searchType.equalsIgnoreCase("district")){
					sb.append(" ,c1.district_id  as locationId,d.district_name as locationName ");
					sbm.append(" ,district d");
					sbe.append(" and d.district_id=c1.district_id and c1.state_id =:locationValue");
					sbe.append(" GROUP BY e.election_id, e.election_year, et.election_type_id ,p.party_id,c1.district_id  ");
				}else if(locationTypeId ==2l && parlimentIds == null && searchType.equalsIgnoreCase("constituency")){
					sb.append(" ,c1.constituency_id as locationId,c1.name as locationName ");
					//sbe.append(" and c.state_id =:locationValue");
					sbe.append(" GROUP BY e.election_id, et.election_type_id ,p.party_id,c1.constituency_id ");
				}else if(locationTypeId.longValue() ==3l){
					if(searchType.equalsIgnoreCase("mandal")){
						if(localBody == false){
							sb.append(" ,tc.tehsil_id as locationid, t.tehsil_name as locationName ");
							sbm.append(" ,tehsil_constituency tc,tehsil t ");
							sbe.append(" and tc.constituency_id=c1.constituency_id and t.tehsil_id =tc.tehsil_id and c1.district_id =:locationValue");
							sbe.append(" GROUP BY e.election_id, e.election_year, et.election_type_id ,p.party_id,t.tehsil_id ");
						}else{
							sb.append(" , p1.local_election_body_id AS locationId, concat(p1.name,'-',et1.election_type) AS locationName  ");
							sbm.append(" ,tehsil_constituency tc,local_election_body p1, election_type et1 ");
							sbe.append(" and tc.constituency_id=c1.constituency_id and p1.tehsil_id = tc.tehsil_id and et1.election_type_id = p1.election_type_id " +
									" and c1.district_id =:locationValue");
							sbe.append(" GROUP BY e.election_id, e.election_year, et.election_type_id ,p.party_id,p1.local_election_body_id ");
						}
						
					}else{
						sb.append(" ,c1.constituency_id as locationId,c1.name as locationName ");
						sbe.append(" and c1.district_id =:locationValue");
						sbe.append(" GROUP BY e.election_id, e.election_year, et.election_type_id ,p.party_id,c1.constituency_id ");
					}
				}else if(locationTypeId ==4l && isMandataData){
					sb.append(" ,c1.constituency_id as locationId,c1.name as locationName ");
					sbe.append(" and c1.tehsil_id in(select distinct tehsil_id from tehsil_constituency where constituency_id=:locationValue) ");
					sbe.append(" GROUP BY e.election_id, e.election_year, et.election_type_id ,p.party_id,c1.constituency_id ");
				}else if(locationTypeId ==5l && isMandataData){
					sb.append(" ,c1.constituency_id as locationId,c1.name as locationName ");
					sbe.append(" and c1.tehsil_id =:locationValue ");
					sbe.append(" GROUP BY e.election_id, e.election_year, et.election_type_id ,p.party_id,c1.constituency_id ");
				}else if(locationTypeId ==10l){
					sb.append(" ,c1.constituency_id as locationId,c1.name as locationName ");
					sbe.append(" and c1.constituency_id in (select distinct p.assembly_id from parliament_assembly p where parliament_id =:locationValue)"); 
					sbe.append("  GROUP BY c1.constituency_id, e.election_year, et.election_type_id ,p.party_id");
				}
			}
			sbe.append(" ORDER BY e.election_year,e.election_id , et.election_type_id");
			queryStr =sb.append(sbm).append(sbe).toString();
		}
		
		query = getSession().createSQLQuery(queryStr)
				.addScalar("partyId",Hibernate.LONG)
				.addScalar("partyName",Hibernate.STRING)
				.addScalar("electionTypeId",Hibernate.LONG)
				.addScalar("electionType",Hibernate.STRING)
				.addScalar("electionyear",Hibernate.LONG)
				.addScalar("electionId",Hibernate.LONG)
				.addScalar("earnedVotes", Hibernate.LONG)
				.addScalar("locationId",Hibernate.LONG)
				.addScalar("locationName",Hibernate.STRING);
		
		if(electionYrs != null && electionYrs.size() > 0l){
			query.setParameterList("electionYrs",electionYrs);
		}
		
		if(subTypes.size()>0l && subTypes !=null){
			query.setParameterList("subTypes",subTypes);
		}
		if(locationTypeId != null && locationValue != null && locationValue.longValue()>0l && locationTypeId.longValue()>0l){
			if( locationTypeId==2l && searchType == null || (searchType!=null && !searchType.equalsIgnoreCase("constituency"))){
				query.setParameter("locationValue",locationValue);
			}else{
				if(locationTypeId ==2l && parlimentIds == null && searchType.equalsIgnoreCase("constituency"))
					;//query.setParameter("locationValue",locationValue);
				else
					query.setParameter("locationValue",locationValue);
			}
			
		}
			
		if(parlimentIds != null && parlimentIds.size() > 0){
			query.setParameterList("parlimentIds",parlimentIds);
		}else if(electionScopeIds !=null && electionScopeIds.size()>0){
			query.setParameterList("electionScopeIds",electionScopeIds);
		}
		if(partIds !=null && partIds.size() != 0){
			query.setParameterList("partIds",partIds);
		}
		return query.list();
	}
	public List<Object[]> getAssemblyPartyListforElection(List<Long> electionScopeIds,List<String> subTypeList,List<Long> yearList,Long constituencyId,Long lelevlId,List<Long> locationValue){
		StringBuilder sb=new StringBuilder();
		/*if(lelevlId != null && lelevlId.longValue() ==4l){
		sb.append("select ");
				//" b.tehsil_id," );
		}else if(lelevlId != null && lelevlId.longValue() ==5l || lelevlId != null && lelevlId.longValue() ==7l){
		sb.append("select " );
				//" b.local_election_body_id," );
		}else if(lelevlId != null && lelevlId.longValue() ==6l){
		sb.append("select " );
				//"b.panchayat_id," );	
		}*/
		sb.append(" select e.election_scope_id,e.election_id,et.election_type,e.election_scope_id," +
				" e.election_year,n.party_id,p.short_name,count(n.party_id)," +
				" sum(cbr.votes_earned) from constituency c," +
				" constituency_election ce,election e ," +
				" election_scope es ,election_type et ,nomination n ,party p ," +
				" booth_constituency_election bce,candidate_booth_result cbr,booth b " +
				" where bce.booth_id = b.booth_id and bce.consti_elec_id = ce.consti_elec_id " +
				" and cbr.booth_constituency_election_id = bce.booth_constituency_election_id " +
				" and cbr.nomination_id = n.nomination_id " );
		if(electionScopeIds != null && electionScopeIds.size()>0){
			sb.append(" and e.election_scope_id  in (:electionScopeIds) " );
		  }
		sb.append(" and n.consti_elec_id = ce.consti_elec_id and n.party_id = p.party_id " +
				" and e.election_scope_id = es.election_scope_id and et.election_type_id = es.election_type_id " +
				" and c.constituency_id = ce.constituency_id and ce.election_id = e.election_id " );
		if(subTypeList != null && subTypeList.size()>0){
			sb.append(" and e.sub_type in(:subTypeList)" );
		 }
		 sb.append(" and (c.district_id BETWEEN 11 and 23)" );
		if(yearList != null && yearList.size()>0){
			sb.append(" and e.election_year in(:yearList) ");
		}	
		if(lelevlId != null && lelevlId.longValue() ==5l){
		if(constituencyId != null && constituencyId.longValue()>0l){
			sb.append(" and b.constituency_id =:constituencyId  " );
		  }
		}else if(lelevlId != null && lelevlId.longValue() ==6l){
			if(constituencyId != null && constituencyId.longValue()>0l){
			 sb.append(" and b.constituency_id =:constituencyId ");
			}
		}else if(lelevlId != null &&  lelevlId.longValue() ==7l){
			if(constituencyId != null && constituencyId.longValue()>0l){
				sb.append(" and b.constituency_id =:constituencyId and b.local_election_body_id is not null " );
			  }
		}
		/*if(lelevlId != null && lelevlId.longValue() ==4l){
		//sb.append(" group by b.tehsil_id,e.election_scope_id,e.election_id,et.election_type," +
				//" e.election_scope_id,e.election_year,n.party_id " +
				////" order by b.tehsil_id,e.election_scope_id,e.election_id," +
				//" et.election_type,e.election_scope_id,e.election_year,n.party_id");
		}else if(lelevlId != null && lelevlId.longValue() ==5l || lelevlId != null && lelevlId.longValue() ==7l){
			sb.append(" group by b.local_election_body_id,e.election_scope_id,e.election_id,et.election_type,e.election_scope_id," +
					" e.election_year,n.party_id" +
					"  order by b.local_election_body_id,e.election_scope_id,e.election_id,et.election_type,e.election_scope_id,e.election_year,n.party_id ");
		}else if(lelevlId != null && lelevlId.longValue() ==6l){
			sb.append(" group by b.panchayat_id,e.election_scope_id,e.election_id," +
					" et.election_type,e.election_scope_id,e.election_year,n.party_id " +
					" order by b.panchayat_id,e.election_scope_id,e.election_id,et.election_type,e.election_scope_id,e.election_year,n.party_id ");	
		}*/
		sb.append(" group by e.election_scope_id,e.election_id," +
				" et.election_type,e.election_scope_id,e.election_year,n.party_id " +
				" order by b.panchayat_id,e.election_scope_id,e.election_id,et.election_type,e.election_scope_id,e.election_year,n.party_id ");
		Query query = getSession().createSQLQuery(sb.toString());
		if(electionScopeIds != null && electionScopeIds.size()>0){
			query.setParameterList("electionScopeIds", electionScopeIds);
		}
		if(yearList != null && yearList.size()>0){
			query.setParameterList("yearList", yearList);
		}
		if(subTypeList != null && subTypeList.size()>0){
			query.setParameterList("subTypeList", subTypeList);
		}
		
		if(lelevlId != null && lelevlId.longValue() ==5l){
			if(constituencyId != null && constituencyId.longValue()>0l){
				query.setParameter("constituencyId", constituencyId);
			  }
		}else if(lelevlId != null && lelevlId.longValue() ==6l){
			if(constituencyId != null && constituencyId.longValue()>0l){
				query.setParameter("constituencyId", constituencyId);
			}
		}else if(lelevlId != null &&  lelevlId.longValue() ==7l){
			if(constituencyId != null && constituencyId.longValue()>0l){
				query.setParameter("constituencyId", constituencyId);
			  }
		}
		/*if(lelevlId != null && locationValue != null && locationValue.size()>0l && lelevlId.longValue()>0l){
			query.setParameterList("locationValue", locationValue);
		}*/
		return query.list();
		
	}
}
