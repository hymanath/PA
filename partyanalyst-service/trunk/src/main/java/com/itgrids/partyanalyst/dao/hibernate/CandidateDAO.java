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
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.columns.enums.CandidateColumnNames;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Nomination;


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
	public List<Candidate> findCandidteIds(String[] names){
		
		StringBuffer queryBuffer = new StringBuffer("select model from Candidate model where ");
		
		for(int i = 0; i < names.length; i++){
			queryBuffer.append("model.firstname like '%"+names[i]+"%' or " );
			queryBuffer.append("model.lastname like '%"+names[i]+"%' or " );
		}
		
		String query = queryBuffer.toString().substring(0, queryBuffer.toString().length()-3);
		System.out.println("query: "+query);
		return getHibernateTemplate().find(query); 
		
	}
	

	@SuppressWarnings("unchecked")
	public List<Long> findCandidatesSize(){
		Query queryObject = getSession().createQuery("select count(model.candidateId) from Candidate model");
		  
		 		 
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidateDetails(Long candidateId){
		
		return getHibernateTemplate().find("from Candidate model where model.candidateId = ?", candidateId);
		
	}

	
	

}
