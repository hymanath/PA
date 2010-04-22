/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 14,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommentCategoryCandidateDAO;
import com.itgrids.partyanalyst.model.CommentCategoryCandidate;

public class CommentCategoryCandidateDAO extends GenericDaoHibernate<CommentCategoryCandidate, Long>
		implements ICommentCategoryCandidateDAO {

	public CommentCategoryCandidateDAO() {
		super(CommentCategoryCandidate.class);
	}

	@SuppressWarnings("unchecked")
	public List<CommentCategoryCandidate> getCommentsOnACandidateInAConstituency(
			String electionType, String electionYear, Long candidateId,
			Long constituencyId) {
		Object[] params = {electionType,electionYear,candidateId,constituencyId};
		return getHibernateTemplate().find("from CommentCategoryCandidate model where model.nomination.constituencyElection.election.electionScope.electionType.electionType = ?"+
				" and model.nomination.constituencyElection.election.electionYear = ?" +
				" and model.nomination.candidate.candidateId = ? and model.nomination.constituencyElection.constituency.constituencyId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<CommentCategoryCandidate> getAllCommentsOnACandidate(
			String electionType, String electionYear, Long candidateId) {
		Object[] params = {electionType,electionYear,candidateId};
		return getHibernateTemplate().find("from CommentCategoryCandidate model where model.nomination.constituencyElection.election.electionScope.electionType.electionType = ?"+
				" and model.nomination.constituencyElection.election.electionYear = ?" +
				" and model.nomination.candidate.candidateId = ?", params);

	}

	@SuppressWarnings("unchecked")
	public List<CommentCategoryCandidate> getAllCommentsOnACandidateInAllElections(
			Long candidateId) {
		return getHibernateTemplate().find("from CommentCategoryCandidate model where model.nomination.candidate.candidateId = ?", candidateId);
	}
	
	@SuppressWarnings("unchecked")
	public List<CommentCategoryCandidate> getAllCommentsOnACandidateInAnElection(Long electionId,Long candidateId){
		Object[] params = {electionId,candidateId};
		return getHibernateTemplate().find("from CommentCategoryCandidate model where model.nomination.constituencyElection.election.electionId = ? and model.nomination.candidate.candidateId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentsCountForACandidate(Long candidateId,
			Long constituencyId, String electionType, String electionYear) {
		Object[] params = {candidateId,constituencyId,electionType,electionYear};
		return getHibernateTemplate().find("select count(model.commentCategoryCandidateId) from CommentCategoryCandidate model"+
				" where model.nomination.candidate.candidateId = ? and model.nomination.constituencyElection.constituency.constituencyId = ? and"+
				" model.nomination.constituencyElection.election.electionScope.electionType.electionType = ? and model.nomination.constituencyElection.election.electionYear = ?",params);
	}

}
