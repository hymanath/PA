/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.columns.enums.CandidateColumnNames;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Voter;

/**
 * Interface for CandidateDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface ICandidateDAO extends GenericDao<Candidate, Long>{

	/**
	 * Find all Candidate entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Candidate property to query
	 * @param value
	 *            the property value to match
	 * @return List<Candidate> found by query
	 */
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
	
	/*public List<Object[]> findByFirstMiddleAndLastNames(String searchText,String sortOption,String order,Integer startIndex,Integer maxResult,String ids);

	public List<Long> totalSearchCount(String searchText, String ids);*/
	
	public Long getCandidateByLastName(String lastName);
	
	public Integer findEmailInsertionInCandidate(String emailId,Long candidateId);

	public List<Candidate> getEmailInfo(Long candidateId);
	
	public Integer findNameInsertionInCandidate(String candidateName,Long candidateId);
	
	public List<Object[]> getCandidateDetailsBySearch(String gender,String name,Long constituencyId,Long stateId,String selectedType);
	
	public List<Long> getinfluencingPeopleVoterId(Long voterId);
	
	public List<Long> findCandidatePeopleDetails(List<Long> voterIds);
	
	public List<Object[]> getCandidateNameByCandidateIds(List<Long> candidateIds);
	public List<Object[]> getCandidatesForDebate(Long partyId);
	public List<Object[]> getCandidatesForDebateParties(List<Long> partyIds);
	public List<Long> getCandidateExistesOrNot(Long partyId,String name);
	public List<Object[]> getCandidatesByName(String candidateName);
	public List<Object[]> getCandidatesByName();
	public List<Object[]> getElectionInformationLocationWise(List<Long> yearsList, Long locationTypeId,
			Long locationValue,List<Long> electionScopeIds, List<Long> electionBodyIds,List<Long> tehsilIds,List<Long> partyIds,List<String> subTypes);

	public List<Object[]> getElectionInformationLocationWiseVoterShare(List<Long> electionYrs, Long locationTypeId, Long locationValue,
			List<Long> electionScopeIds, Object object, List<String> subTypes, List<Long> parlimentIds, boolean ismandalData);

	List<Object[]> getElectionInformationLocationWiseEarnedVoterShare(List<Long> electionYrs, Long locationTypeId, Long locationValue,
			List<Long> electionScopeIds, Object object, List<String> subTypes, List<Long> parlimentIds, boolean ismandalData);

	public List<Object[]> getElectionInformationLocationWiseWonedCount(List<Long> electionYrs, Long locationTypeId, Long locationValue,
			List<Long> electionScopeIds, List<String> subTypes, List<Long> parlimentIds,boolean isMandal);
	
	public List<Object[]> getAvailableSeatsforElection(List<Long> yearsList ,Long locationTypeId,List<Long> locationValue,List<Long> electionScopeIds,List<String> subTypeList,String type);
	public List<Object[]> getParticipatedPartyListforElection(List<Long> yearsList ,Long locationTypeId,List<Long> locationValue,List<Long> electionScopeIds,List<String> subTypeList,String type);
	public List<Object[]> getParticipatedPartyListforElectionDetails(List<Long> yearsList ,Long locationTypeId,List<Long> locationValue,List<Long> electionScopeIds,List<String> subTypeList,String type,String resultFor);

	public List<Object[]> getElectionInformationLocationWisedetailsForValidVotes(List<Long> electionYrs, Long locationTypeId, Long locationValue,
			List<Long> electionScopeIds, Object object, List<String> subTypes,List<Long> parliamentIdsList,String searchType, boolean localelecbody, boolean isMandalData);

	List<Object[]> getElectionInformationLocationWiseDetailEarnedVoterShare(List<Long> electionYrs, Long locationTypeId, Long locationValue,
			List<Long> electionScopeIds, Object object, List<String> subTypes,List<Long> parliamentIdsList, List<Long> partIds,String searchType, boolean localelecbody,boolean isMandalData);
	public List<Object[]> getAssemblyPartyListforElection(List<Long> electionScopeIds,List<String> subTypeList,List<Long> yearList,Long constituencyId,Long lelevlId,List<Long> locationValue);
}