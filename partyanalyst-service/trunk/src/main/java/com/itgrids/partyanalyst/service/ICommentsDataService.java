/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 15,2010
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import org.hibernate.sql.Select;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.ConstituencyCommentsVO;
import com.itgrids.partyanalyst.dto.ElectionCommentsVO;
import com.itgrids.partyanalyst.dto.PartyCommentsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.CommentCategoryCandidate;
import com.itgrids.partyanalyst.model.CommentCategoryConstituency;
import com.itgrids.partyanalyst.model.CommentCategoryParty;

public interface ICommentsDataService {

	public List<ElectionCommentsVO> getCandidateCommentsData(String electionType,String electionYear,Long electionId,Long constituencyId,Long candidateId,String categoryType);
	
	public List<ElectionCommentsVO> getPartyCommentsData(String electionType,String electionYear,Long electionId,Long partyId,String categoryType);
	
	public List<ElectionCommentsVO> getConstituencyCommentsData(String electionType,String electionYear,Long electionId,Long constituencyId,String categoryType);
	
	public CommentCategoryCandidate saveCandidateCommentForAnElection(String electionType,String electionYear,Long electionId,Long constituencyId,Long candidateId,String commentDesc,String commentedBy,Long commentCategoryId);
	
	public CommentCategoryParty savePartyCommentForAnElection(String electionType,String electionYear,Long electionId,Long partyId,String commentDesc,String commentedBy,Long commentCategoryId);
	
	public CommentCategoryConstituency saveConstituencyCommentForAnElection(String electionType,String electionYear,Long electionId,Long constituencyId,String commentDesc,String commentedBy,Long commentCategoryId);
	
	public CandidateCommentsVO saveCandidateCommentsToDB(String electionType, String electionYear, Long electionId,
			Long constituencyId, Long candidateId,String commentDesc,String commentedBy,Long commentCategoryId);
	
	public ConstituencyCommentsVO saveConstituencyCommentsToDB(String electionType, String electionYear, Long electionId,
			Long constituencyId,String commentDesc,String commentedBy,Long commentCategoryId);
	
	public PartyCommentsVO savePartyCommentsToDB(String electionType, String electionYear, Long electionId,
			Long partyId,String commentDesc,String commentedBy,Long commentCategoryId);
	
	public List<SelectOptionVO> getCandidateCommentsCategoryStatics(Long candidateRank);
	
	public List<CandidateCommentsVO> getAnalyzedResonsWithRatingsForConstituencyInAnElection(Long constiElecId);
	
	public List<SelectOptionVO> getElectionYearsForConstituency(Long constituencyId);
	
	public List<CandidateVO> getCandidateResultsForConstiElectionId(Long constElectionId);
}
