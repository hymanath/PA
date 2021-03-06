/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Candidate;

/**
 * Interface for CandidateDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface ICandidateDAO extends GenericDao<Candidate, Long>{/*

	*//**
	 * Find all Candidate entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Candidate property to query
	 * @param value
	 *            the property value to match
	 * @return List<Candidate> found by query
	 *//*
	public List<Candidate> findByProperty(CandidateColumnNames propertyName, Object value);

	public List<Candidate> findByFirstname(Object firstname);

	public List<Candidate> findByMiddlename(Object middlename);

	public List<Candidate> findByLastname(Object lastname);

	public List<Candidate> findByEmailAddress(Object emailAddress);

	public List<Candidate> findByPhone(Object phone);

	public List<Candidate> findByMobile(Object mobile);

	public List<Candidate> findByAddress(Object address);

	public List<Candidate> findByEducation(Object education);

	public List<Candidate> findByGender(Object gender);
	
	public List<Candidate> findByFirstMiddleAndLastNames(String[] firstName);
	

	public List findCandidatesSize();

	public List<Candidate> findCandidateDetails(Long candidateId);

	public Candidate findCandidateByLastName(String lastName);
	
	public Object getCandidateNameByCandidateId(Long candidateId);
	
	public List<Object[]> findByFirstMiddleAndLastNames(String searchText,String sortOption,String order,Integer startIndex,Integer maxResult,String ids);

	public List<Long> totalSearchCount(String searchText, String ids);
	
	public Long getCandidateByLastName(String lastName);
	
	public Integer findEmailInsertionInCandidate(String emailId,Long candidateId);

	public List<Candidate> getEmailInfo(Long candidateId);
	
	public Integer findNameInsertionInCandidate(String candidateName,Long candidateId);
	
	
	public List<Long> getinfluencingPeopleVoterId(Long voterId);
	
	public List<Long> findCandidatePeopleDetails(List<Long> voterIds);
*/

	public List<Object[]> getCandidateListByPartyId(String accessQuery,Long partyId);
	
	public List<Long> getCandidateIdByPartyIdAndCandidateName(Long partyId,String candidateName,Long designationId);
	
	public String getCandidateName(Long candidateId);

	public List<Object[]> getFilteredCandidateListByName(Long partyId,Long designationId,Long locationId,String locationType,String searchString);	

	public List<Object[]> getCandidateNames(Set<Long> candidateIds);
	
	public List<Object[]> getDesignationsAndLocation(Long candidateId);
	
	public List<Object[]> getCandidatesForDebate(Long partyId);
	
	public List<Long> getCandidateExistesOrNot(Long partyId,String name);
	
	public List<Object[]> getCandidatesForDebateParties(List<Long> partyIds);
	
	
}