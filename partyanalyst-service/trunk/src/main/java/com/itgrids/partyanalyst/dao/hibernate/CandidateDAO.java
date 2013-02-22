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
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.columns.enums.CandidateColumnNames;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Nomination;
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
			String name, Long constituencyId, Long stateId) {
		
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
		
		query.append("  order by model.candidate.lastname asc ");	
		
		 Query queryObject = getSession().createQuery(query.toString());
		 
		 if(gender!= null && gender.trim().length()>0)
		 queryObject.setString("gender",gender);
		 
		 if(constituencyId!= null && constituencyId>0L)
			 queryObject.setLong("constituencyId", constituencyId);	 
		 
		 if(stateId!= null && stateId>0L)
			 queryObject.setLong("stateId", stateId);	 
		 
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
	
}
