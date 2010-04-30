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

	@SuppressWarnings("unchecked")
	public List getCommentsCountForAPartyInAnElection(Long electionId,Long partyId) {
		Object[] params = {electionId,partyId};
		return getHibernateTemplate().find("select count(distinct model.nomination.constituencyElection.constituency.constituencyId) from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentsResultsForAPartyInAnElection(Long electionId,
			Long partyId) {
		Object[] params = {electionId,partyId};
		return getHibernateTemplate().find("select model.nomination.constituencyElection.constituency.constituencyId,model.nomination.constituencyElection.constituency.name,"+
				"model.nomination.candidate.candidateId,model.nomination.candidate.lastname,"+
				"model.commentData.commentDesc,model.commentData.commentBy,"+
				"model.commentData.commentDate,model.commentData.commentDataCategory.commentDataCategoryType "+
				"from CommentCategoryCandidate model where model.nomination.constituencyElection.election.electionId = ? "+
				"and model.nomination.party.partyId = ? order by model.nomination.constituencyElection.constituency.constituencyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentsCountForACandidateInAConstituencyInAnELection(
			Long electionId, Long candidateId, Long constituencyId) {
		Object[] params = {electionId,candidateId,constituencyId};
		return getHibernateTemplate().find("select count(model.commentData.commentDataId) from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.candidate.candidateId = ?"+
				" and model.nomination.constituencyElection.constituency.constituencyId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentsCountInAnElectionForAPartyForCommentCategory(Long electionId,Long partyId,String category) {
		Object[] params = {electionId,partyId,category};
		return getHibernateTemplate().find("select count(distinct model.nomination.constituencyElection.constituency.constituencyId) from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ?"+
				" and model.commentData.commentDataCategory.commentClassification = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentsCountGroupedByCommentCategory(Long electionId,
			Long partyId, String category) {
		Object[] params = {electionId,partyId,category};
		return getHibernateTemplate().find("select count(model.commentData.commentDataId),model.commentData.commentDataCategory.commentDataCategoryId,model.commentData.commentDataCategory.commentDataCategoryType from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ?"+
				" and model.commentData.commentDataCategory.commentClassification = ?"+
				" group by model.commentData.commentDataCategory.commentDataCategoryType",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentsCommentCategoryCountGroupedByConstituencyForAParty(
			Long electionId, Long partyId, String category) {
		Object[] params = {electionId,partyId,category};
		return getHibernateTemplate().find("select count(distinct model.commentData.commentDataCategory.commentDataCategoryType),model.nomination.constituencyElection.constituency.constituencyId,"+
				"model.nomination.constituencyElection.constituency.name from CommentCategoryCandidate model where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ?"+
				" and model.commentData.commentDataCategory.commentClassification = ?"+
				" group by model.nomination.constituencyElection.constituency.constituencyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getNominationsForCandidateHavingComments(Long electionId,
			Long partyId) {
		Object[] params = {electionId,partyId};
		return getHibernateTemplate().find("select distinct model.nomination.nominationId from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ?",params);
	}
	
	

}
