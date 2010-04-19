/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 15,2010
 */
package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICommentCategoryCandidateDAO;
import com.itgrids.partyanalyst.dao.ICommentCategoryConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICommentCategoryPartyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.ConstituencyCommentsVO;
import com.itgrids.partyanalyst.dto.ElectionCommentsVO;
import com.itgrids.partyanalyst.dto.PartyCommentsVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.CommentCategoryCandidate;
import com.itgrids.partyanalyst.model.CommentCategoryConstituency;
import com.itgrids.partyanalyst.model.CommentCategoryParty;
import com.itgrids.partyanalyst.model.CommentData;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.service.ICommentsDataService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CommentsDataService implements ICommentsDataService {

	private IPartyDAO partyDAO;
	private IElectionDAO electionDAO;
	private INominationDAO nominationDAO;
	private IConstituencyDAO constituencyDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private TransactionTemplate transactionTemplate;
	private ICommentCategoryCandidateDAO commentCategoryCandidateDAO;
	private ICommentCategoryPartyDAO commentCategoryPartyDAO;
	private ICommentCategoryConstituencyDAO commentCategoryConstituencyDAO;
		
	private static final Logger log = Logger.getLogger(CommentsDataService.class);
	
	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public ICommentCategoryCandidateDAO getCommentCategoryCandidateDAO() {
		return commentCategoryCandidateDAO;
	}

	public void setCommentCategoryCandidateDAO(
			ICommentCategoryCandidateDAO commentCategoryCandidateDAO) {
		this.commentCategoryCandidateDAO = commentCategoryCandidateDAO;
	}

	public ICommentCategoryPartyDAO getCommentCategoryPartyDAO() {
		return commentCategoryPartyDAO;
	}

	public void setCommentCategoryPartyDAO(
			ICommentCategoryPartyDAO commentCategoryPartyDAO) {
		this.commentCategoryPartyDAO = commentCategoryPartyDAO;
	}

	public ICommentCategoryConstituencyDAO getCommentCategoryConstituencyDAO() {
		return commentCategoryConstituencyDAO;
	}

	public void setCommentCategoryConstituencyDAO(
			ICommentCategoryConstituencyDAO commentCategoryConstituencyDAO) {
		this.commentCategoryConstituencyDAO = commentCategoryConstituencyDAO;
	}

	/*
	 * This Method gets all comments on a candidate based on the input selected parameters and sets the data to VO,ElectionCommentsVO.
	 * comments may be related to a particular election result of a candidate or all elections.
	 */
	public List<ElectionCommentsVO> getCandidateCommentsData(String electionType,
			String electionYear, Long electionId, Long constituencyId,
			Long candidateId, String categoryType) {
		
		if(log.isDebugEnabled())
		log.debug("Inside getCandidateCommentsData Method.....");
		List<ElectionCommentsVO> electionCommentsVO =  new ArrayList<ElectionCommentsVO>();
		ResultStatus resultStatus = new ResultStatus();
		List<CommentCategoryCandidate> candidateComments = null;
		try{
			candidateComments = new ArrayList<CommentCategoryCandidate>();
			if(categoryType != null && categoryType.equals(IConstants.CANDIDATE_COMMENTS_ALL)){
				if(candidateId != null && !candidateId.equals(new Long(0))){
				candidateComments = commentCategoryCandidateDAO.getAllCommentsOnACandidateInAllElections(candidateId);
				}
			}
			else if(categoryType != null && categoryType.equals(IConstants.CANDIDATE_COMMENTS_CONSTITUENCY)){
				if(electionType != null && electionYear != null && constituencyId != null && candidateId != null){
				candidateComments = commentCategoryCandidateDAO.getCommentsOnACandidateInAConstituency(electionType, electionYear, candidateId, constituencyId);
				}
			}
			else if(categoryType != null && categoryType.equals(IConstants.CANDIDATE_COMMENTS_ALL_CONSTITUENCY)){
				if(electionId != null && !electionId.equals(new Long(0)) && candidateId != null){
				candidateComments = commentCategoryCandidateDAO.getAllCommentsOnACandidateInAnElection(electionId, candidateId);
				}
				else if(electionType != null && electionYear != null && candidateId != null){
				candidateComments = commentCategoryCandidateDAO.getAllCommentsOnACandidate(electionType, electionYear, candidateId);
				}
			}
			
			//processed results
			if(candidateComments != null){
				electionCommentsVO = getProcessedCandidateCommentResults(candidateComments);
				resultStatus.setResultPartial(false);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				electionCommentsVO.get(0).setResultStatus(resultStatus);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			ElectionCommentsVO electionComments = new ElectionCommentsVO();
			electionComments.setResultStatus(resultStatus);
		}
	  return electionCommentsVO;
	}
	
	/*
	 * Method processes the comment results and sets the main VO
	 */
	@SuppressWarnings("unchecked")
	public List<ElectionCommentsVO> getProcessedCandidateCommentResults(List<CommentCategoryCandidate> candidateComments){
		List<ElectionCommentsVO> electionCommentsVO = null;
		if(candidateComments != null){
			electionCommentsVO = new ArrayList<ElectionCommentsVO>();
			Map<Long,List<CommentCategoryCandidate>> commentsMap = new HashMap<Long,List<CommentCategoryCandidate>>();
			for(CommentCategoryCandidate candComment:candidateComments){
				Long electionId = candComment.getNomination().getConstituencyElection().getElection().getElectionId();
				if(commentsMap.isEmpty() || !commentsMap.containsKey(electionId)){
					List<CommentCategoryCandidate> commentsList = new ArrayList<CommentCategoryCandidate>();
					commentsList.add(candComment);
					commentsMap.put(electionId, commentsList);
				}
				else if(commentsMap.containsKey(electionId)){
					List<CommentCategoryCandidate> commentsList = commentsMap.get(electionId);
					commentsList.add(candComment);
					commentsMap.put(electionId, commentsList);
				}
			}
			if(!commentsMap.isEmpty()){
				Set entries = commentsMap.entrySet();
				Iterator iterator = entries.iterator();
				while(iterator.hasNext()){
				Map.Entry entry = (Map.Entry)iterator.next();
				List<CommentCategoryCandidate> commentsList = (List<CommentCategoryCandidate>)entry.getValue();
				Long electionId = (Long)entry.getKey();
				ElectionCommentsVO electionComments = getCandidateCommentResultsFromMap(electionId,commentsList);
				if(electionComments != null)
				electionCommentsVO.add(electionComments);
				}
			}
		}
		return electionCommentsVO;
	}
	
	/*
	 * Process the Map and Set Results to VO
	 */
	public ElectionCommentsVO getCandidateCommentResultsFromMap(Long electionId,List<CommentCategoryCandidate> commentsList){
		ElectionCommentsVO elecComments = null;
		if(commentsList != null){
			String electionType = "";
			String electionYear = "";
			elecComments = new ElectionCommentsVO();
			List<CandidateCommentsVO> candidateCommentsVO = new ArrayList<CandidateCommentsVO>();
			int i=0;
			for(CommentCategoryCandidate comments:commentsList){
				if(i==0){
				electionType = comments.getNomination().getConstituencyElection().getElection().getElectionScope().getElectionType().getElectionType();
				electionYear = comments.getNomination().getConstituencyElection().getElection().getElectionYear();
				}i++;
				CandidateCommentsVO candComments = new CandidateCommentsVO();
				candComments.setCandidateId(comments.getNomination().getCandidate().getCandidateId());
				candComments.setCandidate(comments.getNomination().getCandidate().getLastname());
				candComments.setCommentDesc(comments.getCommentData().getCommentDesc());
				candComments.setCommentedBy(comments.getCommentData().getCommentBy());
				candComments.setCommentedOn(comments.getCommentData().getCommentDate().toString());
				
				candidateCommentsVO.add(candComments);
			}
			elecComments.setElectionId(electionId);
			elecComments.setElectionType(electionType);
			elecComments.setElectionYear(electionYear);
			elecComments.setCandidateCommentsVO(candidateCommentsVO);
		}
	 return elecComments;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICommentsDataService#getPartyCommentsData(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String)
	 * This Method gets all comments on a party based on the input selected parameters and sets the data to VO,ElectionCommentsVO.
	 * comments may be related to a particular election result of a party or all elections.
	 */
	public List<ElectionCommentsVO> getPartyCommentsData(String electionType,
			String electionYear, Long electionId, Long partyId,
			String categoryType) {
		
		if(log.isDebugEnabled())
		log.debug("Inside getPartyCommentsData Method.....");
		
		List<ElectionCommentsVO> electionCommentsVO =  new ArrayList<ElectionCommentsVO>();
		ResultStatus resultStatus = new ResultStatus();
		List<CommentCategoryParty> partyCommentsList = null;
		try{
			if(categoryType != null && categoryType.equals(IConstants.PARTY_COMMENTS_ELECTION)){
				if(electionId != null && !electionId.equals(new Long(0)) && partyId != null)
				partyCommentsList = commentCategoryPartyDAO.getCommentsOnAPartyInAnElection(electionId, partyId);
				else
				partyCommentsList = commentCategoryPartyDAO.getCommentsOnAPartyInAnElection(electionType, electionYear, partyId);
			}
			else if(categoryType != null && categoryType.equals(IConstants.PARTY_COMMENTS_ALL)){
				partyCommentsList = commentCategoryPartyDAO.getCommentsOnAPartyInAllElections(partyId);
			}
			
			if(partyCommentsList != null){
				electionCommentsVO = getProcessedPartyCommentResults(partyCommentsList);
				resultStatus.setResultPartial(false);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				electionCommentsVO.get(0).setResultStatus(resultStatus);
			}
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			ElectionCommentsVO electionComments = new ElectionCommentsVO();
			electionComments.setResultStatus(resultStatus);
		}
		
	 return electionCommentsVO;
	}
	
	/*
	 * Method processes the comment results and sets the main VO
	 */
	@SuppressWarnings("unchecked")
	public List<ElectionCommentsVO> getProcessedPartyCommentResults(List<CommentCategoryParty> partyComments){
		List<ElectionCommentsVO> commentsOnParty = null;
		if(partyComments != null){
			commentsOnParty = new ArrayList<ElectionCommentsVO>();
			Map<Long,List<CommentCategoryParty>> partyCommentsMap = new HashMap<Long,List<CommentCategoryParty>>();
			for(CommentCategoryParty partyComment:partyComments){
				Long electionId = partyComment.getElection().getElectionId();
				if(partyCommentsMap.isEmpty() || !partyCommentsMap.containsKey(electionId)){
					List<CommentCategoryParty> partyComentsList = new ArrayList<CommentCategoryParty>();
					partyComentsList.add(partyComment);
					partyCommentsMap.put(electionId, partyComentsList);
				}
				else if(partyCommentsMap.containsKey(electionId)){
					List<CommentCategoryParty> partyComentsList = partyCommentsMap.get(electionId);
					partyComentsList.add(partyComment);
					partyCommentsMap.put(electionId, partyComentsList);
				}
			}
			if(!partyCommentsMap.isEmpty()){
				Set entries = partyCommentsMap.entrySet();
				Iterator iterator = entries.iterator();
				while(iterator.hasNext()){
				Map.Entry entry = (Map.Entry)iterator.next();
				List<CommentCategoryParty> partyComentsList = (List<CommentCategoryParty>)entry.getValue();
				Long electionId = (Long)entry.getKey();
				ElectionCommentsVO electionComments = getPartyCommentResultsFromMap(electionId,partyComentsList);
				if(electionComments != null)
				commentsOnParty.add(electionComments);
				}
			}
		}
	  return commentsOnParty;
	}
	
	/*
	 * Process the party comments Map and Set Results to VO
	 */
	public ElectionCommentsVO getPartyCommentResultsFromMap(Long electionId,List<CommentCategoryParty> partyComentsList){
		ElectionCommentsVO elecComments = null;
		if(partyComentsList != null){
			String electionType = "";
			String electionYear = "";
			elecComments = new ElectionCommentsVO();
			List<PartyCommentsVO> partyCommentsVO = new ArrayList<PartyCommentsVO>();
			int i=0;
			for(CommentCategoryParty comments:partyComentsList){
				if(i==0){
				electionType = comments.getElection().getElectionScope().getElectionType().getElectionType();
				electionYear = comments.getElection().getElectionYear();
				}i++;
				PartyCommentsVO partyComments = new PartyCommentsVO();
				partyComments.setPartyId(comments.getParty().getPartyId());
				partyComments.setPartyName(comments.getParty().getLongName());
				partyComments.setCommentDesc(comments.getCommentData().getCommentDesc());
				partyComments.setCommentedBy(comments.getCommentData().getCommentBy());
				partyComments.setCommentedOn(comments.getCommentData().getCommentDate().toString());
				
				partyCommentsVO.add(partyComments);
			}
			elecComments.setElectionId(electionId);
			elecComments.setElectionType(electionType);
			elecComments.setElectionYear(electionYear);
			elecComments.setPartyCommentsVO(partyCommentsVO);
		}
	 return elecComments;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICommentsDataService#getConstituencyCommentsData(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String)
	 * This Method gets all comments on a constituency based on the input selected parameters and sets the data to VO,ElectionCommentsVO.
	 * comments may be related to a particular election result of a constituency or all elections.
	 */
	public List<ElectionCommentsVO> getConstituencyCommentsData(
			String electionType, String electionYear, Long electionId,
			Long constituencyId, String categoryType) {
		if(log.isDebugEnabled())
			log.debug("Inside getConstituencyCommentsData Method.....");
			
			List<ElectionCommentsVO> electionCommentsVO =  new ArrayList<ElectionCommentsVO>();
			ResultStatus resultStatus = new ResultStatus();
			List<CommentCategoryConstituency> constituencyCommentsList = null;
			try{
				if(categoryType != null && categoryType.equals(IConstants.CONSTITUENCY_COMMENTS_ELECTION)){
					if(electionId != null && !electionId.equals(new Long(0)) && constituencyId != null)
					constituencyCommentsList = commentCategoryConstituencyDAO.getCommentsOnAConstituencyInAElection(electionId, constituencyId);
					else
					constituencyCommentsList = commentCategoryConstituencyDAO.getCommentsOnAConstituencyInAElection(electionYear, constituencyId);
				}
				else if(categoryType != null && categoryType.equals(IConstants.CONSTITUENCY_COMMENTS_ALL)){
					constituencyCommentsList = commentCategoryConstituencyDAO.getCommentsOnAConstituencyInAllElections(constituencyId);
				}
				
				if(constituencyCommentsList != null){
					electionCommentsVO = getProcessedConstituencyCommentResults(constituencyCommentsList);
					resultStatus.setResultPartial(false);
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					electionCommentsVO.get(0).setResultStatus(resultStatus);
				}
				
			}
			catch(Exception ex){
				ex.printStackTrace();
				resultStatus.setExceptionEncountered(ex);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setResultPartial(true);
				ElectionCommentsVO electionComments = new ElectionCommentsVO();
				electionComments.setResultStatus(resultStatus);
			}
	 return electionCommentsVO;
	}
	
	/*
	 * Method processes the comment results and sets the main VO
	 */
	@SuppressWarnings("unchecked")
	public List<ElectionCommentsVO> getProcessedConstituencyCommentResults(List<CommentCategoryConstituency> constituencyCommentsList){
		List<ElectionCommentsVO> commentsOnConstituency = null;
		if(constituencyCommentsList != null){
			commentsOnConstituency = new ArrayList<ElectionCommentsVO>();
			Map<Long,List<CommentCategoryConstituency>> constiCommentsMap = new HashMap<Long,List<CommentCategoryConstituency>>();
			for(CommentCategoryConstituency constiComment:constituencyCommentsList){
				Long electionId = constiComment.getElection().getElectionId();
				if(constiCommentsMap.isEmpty() || !constiCommentsMap.containsKey(electionId)){
					List<CommentCategoryConstituency> constiComentsList = new ArrayList<CommentCategoryConstituency>();
					constiComentsList.add(constiComment);
					constiCommentsMap.put(electionId, constiComentsList);
				}
				else if(constiCommentsMap.containsKey(electionId)){
					List<CommentCategoryConstituency> constiComentsList = constiCommentsMap.get(electionId);
					constiComentsList.add(constiComment);
					constiCommentsMap.put(electionId, constiComentsList);
				}
			}
			if(!constiCommentsMap.isEmpty()){
				Set entries = constiCommentsMap.entrySet();
				Iterator iterator = entries.iterator();
				while(iterator.hasNext()){
				Map.Entry entry = (Map.Entry)iterator.next();
				List<CommentCategoryConstituency> constiComentsList = (List<CommentCategoryConstituency>)entry.getValue();
				Long electionId = (Long)entry.getKey();
				ElectionCommentsVO electionComments = getConstituencyCommentResultsFromMap(electionId,constiComentsList);
				if(electionComments != null)
				commentsOnConstituency.add(electionComments);
				}
			}
		}
	  return commentsOnConstituency;
	}
	
	/*
	 * Process the constituency comments Map and Set Results to VO
	 */
	public ElectionCommentsVO getConstituencyCommentResultsFromMap(Long electionId,List<CommentCategoryConstituency> constiComentsList){
		ElectionCommentsVO elecComments = null;
		if(constiComentsList != null){
			String electionType = "";
			String electionYear = "";
			elecComments = new ElectionCommentsVO();
			List<ConstituencyCommentsVO> constiCommentsVO = new ArrayList<ConstituencyCommentsVO>();
			int i=0;
			for(CommentCategoryConstituency comments:constiComentsList){
				if(i==0){
				electionType = comments.getElection().getElectionScope().getElectionType().getElectionType();
				electionYear = comments.getElection().getElectionYear();
				}i++;
				ConstituencyCommentsVO consComments = new ConstituencyCommentsVO();
				consComments.setConstituencyId(comments.getConstituency().getConstituencyId());
				consComments.setConstituency(comments.getConstituency().getName());
				consComments.setCommentDesc(comments.getCommentData().getCommentDesc());
				consComments.setCommentedBy(comments.getCommentData().getCommentBy());
				consComments.setCommentedOn(comments.getCommentData().getCommentDate().toString());
				
				constiCommentsVO.add(consComments);
			}
			elecComments.setElectionId(electionId);
			elecComments.setElectionType(electionType);
			elecComments.setElectionYear(electionYear);
			elecComments.setConstituencyCommentsVO(constiCommentsVO);
		}
	 return elecComments;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICommentsDataService#saveCandidateCommentForAnElection(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long)
	 * Method to save a comment for a candidate
	 */
	public CommentCategoryCandidate saveCandidateCommentForAnElection(
			String electionType, String electionYear, Long electionId,
			Long constituencyId, Long candidateId,final String commentDesc,final String commentedBy) {
		
		log.debug("Inside saveCandidateCommentForAnElection Method ......");
		
		Nomination nomination = null;
		List<Nomination> nominations = null;
				
		if(electionId != null && !electionId.equals(new Long(0)) && constituencyId != null && candidateId != null)
		nominations = nominationDAO.getNominationOfACandidateInAnElection(electionId, constituencyId, candidateId);
		else if(electionType != null && electionYear != null && constituencyId != null && candidateId != null)
		nominations = nominationDAO.getNominationOfACandidateInAnElection(electionType, electionYear, constituencyId, candidateId);
		
		if(nominations != null && nominations.size() > 0)
		nomination = nominations.get(0);
		
		final Nomination finalNominatn = nomination;
		
		CommentCategoryCandidate commentCategoryCandidate = (CommentCategoryCandidate)transactionTemplate.execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus status) {
				CommentCategoryCandidate commentCategoryCandidateSaved = null;
				try{
					java.util.Date today = new java.util.Date();
					String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String formatDate = sdf.format(today) ;
					today = sdf.parse(formatDate);
					
					CommentData commentData = new CommentData();
					commentData.setCommentDesc(commentDesc);
					commentData.setCommentBy(commentedBy);
					commentData.setCommentDate(today);
					
					commentCategoryCandidateSaved = new CommentCategoryCandidate();
					commentCategoryCandidateSaved.setNomination(finalNominatn);
					commentCategoryCandidateSaved.setCommentData(commentData);
					
					commentCategoryCandidateSaved = commentCategoryCandidateDAO.save(commentCategoryCandidateSaved);
					
					log.debug("Saved Successfully ......");
					
				}catch(Exception ex){
					ex.printStackTrace();
					status.setRollbackOnly();
					log.debug("Exception Raised :" + ex);
				}
			  return commentCategoryCandidateSaved;
			}
			
		});
		
	 return commentCategoryCandidate;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICommentsDataService#saveConstituencyCommentForAnElection(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 * Method to save a comment for a party
	 */
	@SuppressWarnings("unchecked")
	public CommentCategoryConstituency saveConstituencyCommentForAnElection(
			String electionType, String electionYear, Long electionId,
			final Long constituencyId,final String commentDesc,final String commentedBy) {
		
		log.debug("Inside saveConstituencyCommentForAnElection Method ......");
		
		Election electionObj = null;
		
		if(electionId != null && !electionId.equals(new Long(0)))
		electionObj = electionDAO.get(electionId);
		else{
			//code to get election from election type and year
			List elecObj = constituencyElectionDAO.findElectionIdForAParticularElectionTypeAndYearAndConstituency(electionType, electionYear, constituencyId);
			if(elecObj != null){
			Object params = (Object)elecObj.get(0);
			electionObj = (Election)params;
			}
		}
		final Election election = electionObj;
		
		CommentCategoryConstituency commentCategoryConstituency = (CommentCategoryConstituency)transactionTemplate.execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus status) {
				
				final Constituency constituency = constituencyDAO.get(constituencyId);
				CommentCategoryConstituency commentCategoryConstituencySaved = null;
				try{
					java.util.Date today = new java.util.Date();
					String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String formatDate = sdf.format(today) ;
					today = sdf.parse(formatDate);
					
					CommentData commentData = new CommentData();
					commentData.setCommentDesc(commentDesc);
					commentData.setCommentBy(commentedBy);
					commentData.setCommentDate(today);
					
					commentCategoryConstituencySaved = new CommentCategoryConstituency();
					commentCategoryConstituencySaved.setCommentData(commentData);
					commentCategoryConstituencySaved.setElection(election);
					commentCategoryConstituencySaved.setConstituency(constituency);
					
					commentCategoryConstituencySaved = commentCategoryConstituencyDAO.save(commentCategoryConstituencySaved);
					
					log.debug("Saved Successfully ......");
					
					}catch(Exception ex){
						ex.printStackTrace();
						log.debug("Exception Raised :" + ex);
					}
			 return commentCategoryConstituencySaved;
			}
		});
	  return commentCategoryConstituency;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICommentsDataService#savePartyCommentForAnElection(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 * Method to save a comment for a constituency
	 */
	public CommentCategoryParty savePartyCommentForAnElection(
			String electionType, String electionYear, Long electionId,
			final Long partyId,final String commentDesc,final String commentedBy) {
		
		log.debug("Inside savePartyCommentForAnElection Method ......");
		
		Election electionObj = null;
		
		if(electionId != null && !electionId.equals(new Long(0)))
		electionObj = electionDAO.get(electionId);
		else{
		//code to get election from election type and year
		}
		
		final Election election = electionObj;
				
		CommentCategoryParty commentCategoryParty = (CommentCategoryParty)transactionTemplate.execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus status) {
				
				final Party party = partyDAO.get(partyId);
				CommentCategoryParty commentCategoryPartySaved = null;
				try{
					java.util.Date today = new java.util.Date();
					String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String formatDate = sdf.format(today) ;
					today = sdf.parse(formatDate);
					
					CommentData commentData = new CommentData();
					commentData.setCommentDesc(commentDesc);
					commentData.setCommentBy(commentedBy);
					commentData.setCommentDate(today);
					
					commentCategoryPartySaved = new CommentCategoryParty();
					commentCategoryPartySaved.setCommentData(commentData);
					commentCategoryPartySaved.setParty(party);
					commentCategoryPartySaved.setElection(election);
					
					commentCategoryPartySaved = commentCategoryPartyDAO.save(commentCategoryPartySaved);
					
					log.debug("Saved Successfully ......");
					
					}catch(Exception ex){
						ex.printStackTrace();
						log.debug("Exception Raised :" + ex);
					}
			 return commentCategoryPartySaved;
			}
		});
	  return commentCategoryParty;
	}

}
