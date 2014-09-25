/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.model.Candidate;


/**
*@author <a href="mailto:sai.basetti@gmail.com">Sai Krishna</a>
*@author <a href="mailto:sriharigopalnalam@gmail.com">Srihari</a>
*/
public class CandidateDAO extends GenericDaoHibernate<Candidate, Long> implements 
   ICandidateDAO {

	
	public CandidateDAO() {
		super(Candidate.class);
	}
	
	
 @SuppressWarnings("unchecked")
 public List<Object[]> getCandidateListByPartyId(String accessQuery,Long partyId)
 {
	 Query query = getSession().createQuery(" select distinct model.candidateId,model.lastname,model.designation.designation,model.isMinister from Candidate model where model.party.partyId =:partyId "+accessQuery+" order by model.lastname ");
	 query.setParameter("partyId", partyId);
	 return query.list();
			 
 }
 @SuppressWarnings("unchecked")
 public List<Object[]> getDesignationsAndLocation(Long candidateId)
 {
	 Query query = getSession().createQuery(" select distinct model.designation.designationId,model.district.districtId,model.assembly.constituencyId,model.parliament.constituencyId from Candidate model where model.candidate.candidateId =:candidateId  order by model.designationId ");
	 query.setParameter("candidateId", candidateId);
	 return query.list();
			 
 }
 
 @SuppressWarnings("unchecked")
 public List<Long> getCandidateIdByPartyIdAndCandidateName(Long partyId,String candidateName,Long designationId)
 {
	 Query query = getSession().createQuery(" select distinct model.candidateId from Candidate model where model.party.partyId =:partyId " +
	 		" and model.lastname =:candidateName and model.designation.designationId =:designationId ");
	 
	 query.setParameter("partyId", partyId);
	 query.setParameter("candidateName", candidateName);
	 query.setParameter("designationId", designationId);
	 return query.list();
 }
 
 public String getCandidateName(Long candidateId)
 {
	 Query query = getSession().createQuery(" select model.lastname from Candidate model where  model.candidateId =:candidateId ");
		 
		 query.setParameter("candidateId", candidateId);
		
		 return (String) query.uniqueResult();
 }

	public List<Object[]> getFilteredCandidateListByName(Long partyId,Long designationId,Long locationId,String locationType,String searchString){
		
		StringBuffer query = new StringBuffer();
		query.append(" select model.candidateId,model.lastname from Candidate model where ");
		query.append(" model.party.partyId = :partyId and model.designation.designationId = :designationId and ");
		if(locationId != 0){
		if(locationType.equalsIgnoreCase("district"))
			query.append(" model.district.districtId =:locationId and ");		
		else if(locationType.equalsIgnoreCase("Assembly Constituency"))
			query.append(" model.assembly.constituencyId =:locationId and ");
		else if(locationType.equalsIgnoreCase("Parliament Constituency"))
			query.append(" model.parliament.constituencyId =:locationId and ");
		}
		query.append(" model.lastname like '%"+searchString+"%' ");
		
		Query queryObj = getSession().createQuery(query.toString());

		queryObj.setParameter("partyId", partyId);
		queryObj.setParameter("designationId", designationId);
		//queryObj.setParameter("searchString", searchString);
		if((locationType.equalsIgnoreCase("district") || locationType.equalsIgnoreCase("Assembly Constituency") || locationType.equalsIgnoreCase("Parliament Constituency")) && locationId != 0)
			queryObj.setParameter("locationId", locationId);
		return queryObj.list();

	}

	 @SuppressWarnings("unchecked")
	public List<Object[]> getCandidateNames(Set<Long> candidateIds)
	 {
		 Query query = getSession().createQuery(" select distinct model.candidateId,model.lastname from Candidate model where" +
		 		"  model.candidateId in (:candidateIds) ");
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
	
	public List<Long> getCandidateExistesOrNot(Long partyId,String name)
	{
		Query query = getSession().createQuery("select  distinct model.candidateId from Candidate model " +
				" where model.party.partyId = :partyId and model.lastname = :name and model.isDebateCandidate = 'Y' ");
		query .setParameter("partyId", partyId);
		query .setParameter("name", name);
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
	
}
