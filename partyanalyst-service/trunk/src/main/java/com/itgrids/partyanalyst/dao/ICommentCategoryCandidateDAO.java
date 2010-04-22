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

}
