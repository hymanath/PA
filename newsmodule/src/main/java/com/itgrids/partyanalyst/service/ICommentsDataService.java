/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 15,2010
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.ConstituencyCommentsVO;
import com.itgrids.partyanalyst.dto.ElectionCommentsVO;
import com.itgrids.partyanalyst.dto.PartyCommentsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.CommentCategoryCandidate;
import com.itgrids.partyanalyst.model.CommentCategoryConstituency;
import com.itgrids.partyanalyst.model.CommentCategoryParty;

public interface ICommentsDataService {/*

	public List<ElectionCommentsVO> getCandidateCommentsData(String electionType,String electionYear,Long electionId,
			Long constituencyId,Long candidateId,String categoryType, Long userId, String userType);
	
	public List<ElectionCommentsVO> getPartyCommentsData(String electionType,String electionYear,Long electionId,Long partyId,String categoryType);
	
	public List<ElectionCommentsVO> getConstituencyCommentsData(String electionType,String electionYear,Long electionId,Long constituencyId,
			String categoryType);
	
	public CommentCategoryCandidate saveCandidateCommentForAnElection(String electionType,String electionYear,Long electionId, Long constituencyId,
			Long candidateId,String commentDesc,String commentedBy,Long commentCategoryId, Long userId, String userType, Float severity);
	
	public CommentCategoryCandidate saveCandidateCommentForAnElection(String electionType, String electionYear, Long electionId,
			Long constituencyId, Long candidateId,final String commentDesc,	final String commentedBy,Long commentCategoryId, 
			final Long userId, final String userType, final Float severity,final Boolean hasFreeUserRole, final Boolean hasPartyAnalystUserRole);
	
	public CommentCategoryParty savePartyCommentForAnElection(String electionType,String electionYear,Long electionId,Long partyId,
			String commentDesc,String commentedBy,Long commentCategoryId);
	
	public CommentCategoryParty savePartyCommentForAnElection(
			String electionType, String electionYear, Long electionId,
			final Long partyId,final String commentDesc,final String commentedBy,Long commentCategoryId,Boolean hasFreeUserRole,Boolean hasPartyAnalystUserRole) ;
	
	public CommentCategoryConstituency saveConstituencyCommentForAnElection(String electionType,String electionYear,
			Long electionId,Long constituencyId,String commentDesc,String commentedBy,Long commentCategoryId);
	
	public CandidateCommentsVO saveCandidateCommentsToDB(String electionType, String electionYear, Long electionId,	Long constituencyId, 
			Long candidateId,String commentDesc,String commentedBy,Long commentCategoryId, Long userId, String userType, Long severityPercent);
	public CandidateCommentsVO saveCandidateCommentsToDB(String electionType, String electionYear, Long electionId,
			Long constituencyId, Long candidateId,String commentDesc,String commentedBy,Long commentCategoryId, 
			Long userId, String userType, Long severityPercent,Boolean hasFreeUserRole, Boolean hasPartyAnalystUserRole);
	
	public ConstituencyCommentsVO saveConstituencyCommentsToDB(String electionType, String electionYear, Long electionId,
			Long constituencyId,String commentDesc,String commentedBy,Long commentCategoryId);
	
	public PartyCommentsVO savePartyCommentsToDB(String electionType, String electionYear, Long electionId,
			Long partyId,String commentDesc,String commentedBy,Long commentCategoryId);
	public PartyCommentsVO savePartyCommentsToDB(String electionType, String electionYear, Long electionId,
			Long partyId,String commentDesc,String commentedBy,Long commentCategoryId,Boolean hasFreeUserRole,Boolean hasPartyAnalystUserRole);
	
	public List<SelectOptionVO> getCandidateCommentsCategoryStatics(Long candidateRank);
	
	public List<CandidateCommentsVO> getAnalyzedResonsWithRatingsForConstituencyInAnElection(Boolean isNomination, 
			Long constiElecOrNominationId);
	
	public List<SelectOptionVO> getElectionYearsForConstituency(Long constituencyId, Boolean onlyAssets);
	
	public List<CandidateVO> getCandidateResultsForConstiElectionId(Long constElectionId);
	
	public List<CandidateCommentsVO> getAllComments(String fromDate,String toDate,String status);
	
	public List<CandidateCommentsVO> scrutinizePostedComments(List<Long> reasonIds, String actionType, String fromDate, String toDate);
	

*/}
