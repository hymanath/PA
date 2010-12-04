/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 14,2010
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CommentCategoryCandidate;

public interface ICommentCategoryCandidateDAO extends GenericDao<CommentCategoryCandidate, Long> {
	
	public List<CommentCategoryCandidate> getCommentsOnACandidateInAConstituency(String electionType,String electionYear,Long candidateId,Long constituencyId);
	
	public List<CommentCategoryCandidate> getAllCommentsOnACandidate(String electionType,String electionYear,Long candidateId);
	
	public List<CommentCategoryCandidate> getAllCommentsOnACandidateInAnElection(Long electionId,Long candidateId);
	
	public List<CommentCategoryCandidate> getAllCommentsOnACandidateInAllElections(Long candidateId);
	
	@SuppressWarnings("unchecked")
	public List getCommentsCountForACandidate(Long candidateId,Long constituencyId,String electionType,String electionYear);
	
	@SuppressWarnings("unchecked")
	public List getCommentsCountForAPartyInAnElection(Long electionId,Long partyId);
	
	@SuppressWarnings("unchecked")
	public List getCommentsResultsForAPartyInAnElection(Long electionId,Long partyId);
	
	@SuppressWarnings("unchecked")
	public List getCommentsResultsForAPartyInAnElection(Long electionId,Long partyId,String category);
	
	@SuppressWarnings("unchecked")
	public List getCommentsCountForACandidateInAConstituencyInAnELection(Long electionId,Long candidateId,Long constituencyId);
	
	@SuppressWarnings("unchecked")
	public List getCommentsCountInAnElectionForAPartyForCommentCategory(Long electionId,Long partyId,String category);
	
	@SuppressWarnings("unchecked")
	public List getCommentsCountGroupedByCommentCategory(Long electionId,Long partyId,String category);
	
	@SuppressWarnings("unchecked")
	public List getCommentsCommentCategoryCountGroupedByConstituencyForAParty(Long electionId,Long partyId,String category);
	
	@SuppressWarnings("unchecked")
	public List getNominationsForCandidateHavingComments(Long electionId,Long partyId);
	
	@SuppressWarnings("unchecked")
	public List getNominationsForCandidateHavingComments(Long electionId,Long partyId,String category);
	
	@SuppressWarnings("unchecked")
	public List getCommentsResultsForAPartyInAnElection(Long electionId,Long partyId,String category,Long categoryTypeId);
	
	@SuppressWarnings("unchecked")
	public List getCommentResultsForCandidateNominations(List<Long> nominationIds);
	
	@SuppressWarnings("unchecked")
	public List getCommentDetailsForSetOfNominations(List<Long> nominationIds);
	
	@SuppressWarnings("unchecked")
	public List getCommentDetailsForSetOfNominations(List<Long> nominationIds,Long categoryTypeId);
	
	@SuppressWarnings("unchecked")
	public List getAnalyzedConstituenciesCountFromNominationIds(List<Long> nominationIds);
	
	@SuppressWarnings("unchecked")
	public List getCommentsCountForACandidateFromNominationId(Long nominationId);
	
	public List getAllCommentsByUserAndCategoryForANomination(Long nominationId);

}
