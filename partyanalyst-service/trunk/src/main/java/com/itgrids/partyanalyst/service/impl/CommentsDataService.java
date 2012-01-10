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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.ICommentCategoryCandidateDAO;
import com.itgrids.partyanalyst.dao.ICommentCategoryConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICommentCategoryPartyDAO;
import com.itgrids.partyanalyst.dao.ICommentDataCategoryDAO;
import com.itgrids.partyanalyst.dao.ICommentDataDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.ConstituencyCommentsVO;
import com.itgrids.partyanalyst.dto.ElectionCommentsVO;
import com.itgrids.partyanalyst.dto.PartyCommentsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserCommentsInfoVO;
import com.itgrids.partyanalyst.model.CommentCategoryCandidate;
import com.itgrids.partyanalyst.model.CommentCategoryConstituency;
import com.itgrids.partyanalyst.model.CommentCategoryParty;
import com.itgrids.partyanalyst.model.CommentData;
import com.itgrids.partyanalyst.model.CommentDataCategory;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
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
	private ICommentDataCategoryDAO commentDataCategoryDAO;
	private IAnanymousUserDAO ananymousUserDAO;
	private IRegistrationDAO registrationDAO;
	private ICommentDataDAO commentDataDAO; 	
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;

	private static final Logger log = Logger.getLogger(CommentsDataService.class);
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	
	
	
	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
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

	public ICommentDataCategoryDAO getCommentDataCategoryDAO() {
		return commentDataCategoryDAO;
	}

	public void setCommentDataCategoryDAO(
			ICommentDataCategoryDAO commentDataCategoryDAO) {
		this.commentDataCategoryDAO = commentDataCategoryDAO;
	}

	public IAnanymousUserDAO getAnanymousUserDAO() {
		return ananymousUserDAO;
	}

	public void setAnanymousUserDAO(IAnanymousUserDAO ananymousUserDAO) {
		this.ananymousUserDAO = ananymousUserDAO;
	}

	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}
	
	public ICommentDataDAO getCommentDataDAO() {
		return commentDataDAO;
	}

	public void setCommentDataDAO(ICommentDataDAO commentDataDAO) {
		this.commentDataDAO = commentDataDAO;
	}

	/*
	 * This Method gets all comments on a candidate based on the input selected parameters and sets the data to VO,ElectionCommentsVO.
	 * comments may be related to a particular election result of a candidate or all elections.
	 */
	public List<ElectionCommentsVO> getCandidateCommentsData(String electionType,
			String electionYear, Long electionId, Long constituencyId,
			Long candidateId, String categoryType, Long userId, String userType) {
		
		if(log.isDebugEnabled())
		log.debug("Inside getCandidateCommentsData Method.....");
		List<ElectionCommentsVO> electionCommentsVO =  new ArrayList<ElectionCommentsVO>();
		ResultStatus resultStatus = new ResultStatus();
		List<CommentCategoryCandidate> candidateComments = null;
		
		String hqlQuery = "";
		if(IConstants.PARTY_ANALYST_USER.equalsIgnoreCase(userType))
			hqlQuery = " and model.paidUser.registrationId = ?";
		else
			hqlQuery = " and model.freeUser.userId = ?";
		
		try{
			candidateComments = new ArrayList<CommentCategoryCandidate>();
			if(categoryType != null && categoryType.equals(IConstants.CANDIDATE_COMMENTS_ALL)){
				if(candidateId != null && !candidateId.equals(new Long(0))){
					candidateComments = commentCategoryCandidateDAO.getAllCommentsOnACandidateInAllElections(candidateId);
				}
			}
			else if(categoryType != null && categoryType.equals(IConstants.CANDIDATE_COMMENTS_CONSTITUENCY)){
				if(electionType != null && electionYear != null && constituencyId != null && candidateId != null){
					candidateComments = commentCategoryCandidateDAO.getCommentsOnACandidateInAConstituency(electionType, electionYear, candidateId, 
						constituencyId, userId, hqlQuery);
				}
			}
			else if(categoryType != null && categoryType.equals(IConstants.CANDIDATE_COMMENTS_ALL_CONSTITUENCY)){
				if(electionId != null && !electionId.equals(new Long(0)) && candidateId != null){
					candidateComments = commentCategoryCandidateDAO.getAllCommentsOnACandidateInAnElection(electionId, 
							candidateId, userId, hqlQuery);
				}
				else if(electionType != null && electionYear != null && candidateId != null){
					candidateComments = commentCategoryCandidateDAO.getAllCommentsOnACandidate(electionType, electionYear, 
							candidateId, userId, hqlQuery);
				}
			}
			
			//processed results
			if(candidateComments != null){
				electionCommentsVO = getProcessedCandidateCommentResults(candidateComments);
				resultStatus.setResultPartial(false);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				if(electionCommentsVO != null && electionCommentsVO.size() > 0)
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
		Float severity = 0f;
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
				candComments.setCommentedOn(sdf.format(comments.getCommentData().getCommentDate()).toString());
				candComments.setCommentCategory(comments.getCommentData().getCommentDataCategory().getCommentDataCategoryType());
				severity = comments.getSeverity() != null? comments.getSeverity(): 0f;
				candComments.setReasonScore(Math.round(severity*100)/100.0f);
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
				partyComments.setCommentedOn(sdf.format(comments.getCommentData().getCommentDate()).toString());
				partyComments.setCommentCategory(comments.getCommentData().getCommentDataCategory().getCommentDataCategoryType());
				
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
				consComments.setCommentedOn(sdf.format(comments.getCommentData().getCommentDate()).toString());
				consComments.setCommentCategory(comments.getCommentData().getCommentDataCategory().getCommentDataCategoryType());
				
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
	 * Method to save the comments placed for a candidate to DB
	 */
	public CandidateCommentsVO saveCandidateCommentsToDB(String electionType, String electionYear, Long electionId,
			Long constituencyId, Long candidateId,String commentDesc,String commentedBy,Long commentCategoryId, 
			Long userId, String userType, Long severityPercent){
		
		log.debug("Inside saveCandidateCommentsToDB Method ......");
		
		CommentCategoryCandidate commentCategoryCandidate = null;
		Float severity = severityPercent/100f;
		Float remainingValue = 1 - severity;
		Float tempSeverity = 0f;
		String hqlQuery = "";
		if(IConstants.PARTY_ANALYST_USER.equalsIgnoreCase(userType))
			hqlQuery = " and model.paidUser.registrationId = ?";
		else
			hqlQuery = " and model.freeUser.userId = ?";
		
		List<Object[]> candidatesIdAndReasonSeverity = commentCategoryCandidateDAO.getAllCommentsOfUserForANomination(electionId, constituencyId, 
				candidateId, userId, hqlQuery);
		
		if(candidatesIdAndReasonSeverity.size() == 0)
			severity = 1f;
		
		for(Object[] values:candidatesIdAndReasonSeverity){
			commentCategoryCandidate = commentCategoryCandidateDAO.get((Long)values[0]);
			tempSeverity = commentCategoryCandidate.getSeverity();
			commentCategoryCandidate.setSeverity(Math.round(tempSeverity*remainingValue*100)/100.0f);
			commentCategoryCandidateDAO.save(commentCategoryCandidate);
		}
		
		CandidateCommentsVO candidateComments = null;
		commentCategoryCandidate = saveCandidateCommentForAnElection(electionType, electionYear, electionId, 
				constituencyId, candidateId, commentDesc, commentedBy, commentCategoryId, userId, userType, severity);
		
		if(commentCategoryCandidate != null){
			candidateComments = new CandidateCommentsVO();
			candidateComments.setCandidateId(candidateId);
			candidateComments.setCommentDesc(commentDesc);
			candidateComments.setCommentedBy(commentedBy);
			candidateComments.setCandidate(commentCategoryCandidate.getNomination().getCandidate().getLastname());
			candidateComments.setCommentedOn(commentCategoryCandidate.getCommentData().getCommentDate().toString());
			candidateComments.setCommentCategory(commentCategoryCandidate.getCommentData().getCommentDataCategory().getCommentDataCategoryType());
		}
		return candidateComments;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICommentsDataService#saveCandidateCommentForAnElection(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long)
	 * Method to save a comment for a candidate
	 */
	public CommentCategoryCandidate saveCandidateCommentForAnElection(String electionType, String electionYear, Long electionId,
			Long constituencyId, Long candidateId,final String commentDesc,	final String commentedBy,Long commentCategoryId, 
			final Long userId, final String userType, final Float severity) {
		
		log.debug("Inside saveCandidateCommentForAnElection Method ......");
		
		Nomination nomination = null;
		List<Nomination> nominations = null;
				
		if(electionId != null && !electionId.equals(new Long(0)) && constituencyId != null && candidateId != null){
			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
				nominations = nominationDAO.getNominationOfACandidateInAnElection(electionId, constituencyId, candidateId);
			}else{
				List parliamentConstId = delimitationConstituencyAssemblyDetailsDAO.findParliamentForAssemblyForTheGivenYear(constituencyId,Long.parseLong(electionYear));
				if(parliamentConstId.size()==0)
					parliamentConstId = delimitationConstituencyAssemblyDetailsDAO.findParliamentForAssemblyForTheMaxOfGivenYear(constituencyId);
				
				Long conId = 0l;
				for(int i=0;i<parliamentConstId.size();i++){
					Object[] parms = (Object[])parliamentConstId.get(i);
					conId = (Long)parms[0];
				}
				nominations = nominationDAO.getNominationOfACandidateInAnElection(electionId, conId, candidateId);
			}
				
		}
		
		else if(electionType != null && electionYear != null && constituencyId != null && candidateId != null){
			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
				nominations = nominationDAO.getNominationOfACandidateInAnElection(electionType, electionYear, constituencyId, candidateId);	
			}else{
				List parliamentConstId = delimitationConstituencyAssemblyDetailsDAO.findParliamentForAssemblyForTheGivenYear(constituencyId,Long.parseLong(electionYear));
				Long conId = 0l;
				for(int i=0;i<parliamentConstId.size();i++){
					Object[] parms = (Object[])parliamentConstId.get(i);
					conId = (Long)parms[0];
				}
				nominations = nominationDAO.getNominationOfACandidateInAnElection(electionType, electionYear, conId, candidateId);
			}
			
		}
		
		if(nominations != null && nominations.size() > 0)
		nomination = nominations.get(0);
		
		final Nomination finalNominatn = nomination;
		
		final CommentDataCategory commentDataCategory = commentDataCategoryDAO.get(commentCategoryId);
		
		CommentCategoryCandidate commentCategoryCandidate = (CommentCategoryCandidate)transactionTemplate.execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus status) {
				CommentCategoryCandidate commentCategoryCandidateSaved = null;
				try{
					java.util.Date today = new java.util.Date();
					String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String formatDate = sdf.format(today) ;
					today = sdf.parse(formatDate);
					
					CommentData commentData = new CommentData();
					commentData.setCommentDesc(commentDesc);
					commentData.setCommentBy(commentedBy);
					commentData.setCommentDate(today);
					commentData.setCommentDataCategory(commentDataCategory);
					
					commentCategoryCandidateSaved = new CommentCategoryCandidate();
					commentCategoryCandidateSaved.setNomination(finalNominatn);
					commentCategoryCandidateSaved.setCommentData(commentData);
					
					if(IConstants.FREE_USER.equalsIgnoreCase(userType))
						commentCategoryCandidateSaved.setFreeUser(ananymousUserDAO.get(userId));
					else
						commentCategoryCandidateSaved.setPaidUser(registrationDAO.get(userId));
					
					commentCategoryCandidateSaved.setSeverity(severity);
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
	 *  Method to save the comments placed for a constituency to DB
	 */
	public ConstituencyCommentsVO saveConstituencyCommentsToDB(String electionType, String electionYear, Long electionId,
			Long constituencyId,String commentDesc,String commentedBy,Long commentCategoryId){
		
		log.debug("Inside saveConstituencyCommentsToDB Method ......");
		
		ConstituencyCommentsVO constituencyComments = null;
		CommentCategoryConstituency commentCategoryConstituency = saveConstituencyCommentForAnElection(electionType,electionYear,electionId,constituencyId,commentDesc,commentedBy,commentCategoryId);
		
		if(commentCategoryConstituency != null){
			constituencyComments = new ConstituencyCommentsVO();
			constituencyComments.setConstituencyId(constituencyId);
			constituencyComments.setCommentDesc(commentDesc);
			constituencyComments.setCommentedBy(commentedBy);
			constituencyComments.setConstituency(commentCategoryConstituency.getConstituency().getName());
			constituencyComments.setCommentedOn(commentCategoryConstituency.getCommentData().getCommentDate().toString());
			constituencyComments.setCommentCategory(commentCategoryConstituency.getCommentData().getCommentDataCategory().getCommentDataCategoryType());
		}
		
	 return constituencyComments;	
	}
	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICommentsDataService#saveConstituencyCommentForAnElection(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 * Method to save a comment for a party
	 */
	@SuppressWarnings("unchecked")
	public CommentCategoryConstituency saveConstituencyCommentForAnElection(
			String electionType, String electionYear, Long electionId,
			final Long constituencyId,final String commentDesc,final String commentedBy,Long commentCategoryId) {
		
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
		
		//final CommentDataCategory commentDataCategory = commentDataCategoryDAO.get(commentCategoryId);
		final CommentDataCategory commentDataCategory = null;
		
		CommentCategoryConstituency commentCategoryConstituency = (CommentCategoryConstituency)transactionTemplate.execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus status) {
				
				final Constituency constituency = constituencyDAO.get(constituencyId);
				CommentCategoryConstituency commentCategoryConstituencySaved = null;
				try{
					java.util.Date today = new java.util.Date();
					String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String formatDate = sdf.format(today) ;
					today = sdf.parse(formatDate);
					
					CommentData commentData = new CommentData();
					commentData.setCommentDesc(commentDesc);
					commentData.setCommentBy(commentedBy);
					commentData.setCommentDate(today);
					commentData.setCommentDataCategory(commentDataCategory);
					
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
	 * Method to save the comments placed for a party to DB
	 */
	public PartyCommentsVO savePartyCommentsToDB(String electionType, String electionYear, Long electionId,
			Long partyId,String commentDesc,String commentedBy,Long commentCategoryId){
		
		log.debug("Inside savePartyCommentsToDB Method ......");
		
		PartyCommentsVO partyComments = null;
		CommentCategoryParty commentCategoryParty = savePartyCommentForAnElection(electionType,electionYear,electionId,partyId,commentDesc,commentedBy,commentCategoryId);
		
		if(commentCategoryParty != null){
			partyComments = new PartyCommentsVO();
			partyComments.setPartyId(partyId);
			partyComments.setPartyName(commentCategoryParty.getParty().getShortName());
			partyComments.setCommentDesc(commentDesc);
			partyComments.setCommentedBy(commentedBy);
			partyComments.setCommentedOn(commentCategoryParty.getCommentData().getCommentDate().toString());
			partyComments.setCommentCategory(commentCategoryParty.getCommentData().getCommentDataCategory().getCommentDataCategoryType());
		}
	 return partyComments;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ICommentsDataService#savePartyCommentForAnElection(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 * Method to save a comment for a constituency
	 */
	public CommentCategoryParty savePartyCommentForAnElection(
			String electionType, String electionYear, Long electionId,
			final Long partyId,final String commentDesc,final String commentedBy,Long commentCategoryId) {
		
		log.debug("Inside savePartyCommentForAnElection Method ......");
		
		Election electionObj = null;
		
		if(electionId != null && !electionId.equals(new Long(0)))
		electionObj = electionDAO.get(electionId);
		else{
		//code to get election from election type and year
		}
		
		final Election election = electionObj;
		
		//final CommentDataCategory commentDataCategory = commentDataCategoryDAO.get(commentCategoryId);
		final CommentDataCategory commentDataCategory = null;
				
		CommentCategoryParty commentCategoryParty = (CommentCategoryParty)transactionTemplate.execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus status) {
				
				final Party party = partyDAO.get(partyId);
				CommentCategoryParty commentCategoryPartySaved = null;
				try{
					java.util.Date today = new java.util.Date();
					String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String formatDate = sdf.format(today) ;
					today = sdf.parse(formatDate);
					
					CommentData commentData = new CommentData();
					commentData.setCommentDesc(commentDesc);
					commentData.setCommentBy(commentedBy);
					commentData.setCommentDate(today);
					commentData.setCommentDataCategory(commentDataCategory);
					
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

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getCandidateCommentsCategoryStatics(Long candidateRank) {
		
		log.debug("Inside getCandidateCommentsCategoryStatics Method ....");
		
		List<SelectOptionVO> candStatics = null;
		List staticsList = null;
		if(candidateRank != null && !candidateRank.equals(new Long(0))){
			if(candidateRank.equals(new Long(1))){
				staticsList = commentDataCategoryDAO.findCommentDataCategoryByType(IConstants.CANDIDATE_COMMENTS_WON);
			}
			else if(!candidateRank.equals(new Long(1))){
				staticsList = commentDataCategoryDAO.findCommentDataCategoryByType(IConstants.CANDIDATE_COMMENTS_LOST);
			}
			
			if(staticsList != null && staticsList.size() > 0){
				candStatics = new ArrayList<SelectOptionVO>();
				for(int i=0;i<staticsList.size();i++){
					Object[] params = (Object[])staticsList.get(i);
					Long id = (Long)params[0];
					String type = (String)params[1];
					String basicType = (String)params[2];
					
					SelectOptionVO selectOption = new SelectOptionVO();
					selectOption.setId(id);
					selectOption.setName(type);
					
					candStatics.add(selectOption);
				}
			}
		}
		return candStatics;
	}
	
	public List<CandidateCommentsVO> getAnalyzedResonsWithRatingsForConstituencyInAnElection(Boolean isNomination, 
			Long constiElecOrNominationId){
		List constiElecInfo = null;
		if(isNomination)
			constiElecInfo = nominationDAO.findByNominationId(constiElecOrNominationId);
		else
			constiElecInfo = nominationDAO.findByConstituencyElection(constiElecOrNominationId);
		List commentsByUser = null;
		Map<String, List<Object[]>> userwiseComments = null;
		Map<Long, UserCommentsInfoVO> categoryAndScores = null;
		List<Object[]> comments = null;
		CandidateCommentsVO candidateCommentsVO = null;
		List<CandidateCommentsVO> allUsersCommentsForNomination = null;
		List<CandidateCommentsVO> allNominationsComments = new ArrayList<CandidateCommentsVO>();
		CandidateCommentsVO nominationComments = null;
		List<UserCommentsInfoVO> commetsAndScores = null;
		UserCommentsInfoVO userCommentInfoVO = null;
		Double commentScore = 0.0d;
		for(Object[] values:(List<Object[]>)constiElecInfo){
			nominationComments = new CandidateCommentsVO();
			nominationComments.setCandidateId((Long)values[6]);
			nominationComments.setCandidate(values[5].toString());
			nominationComments.setPartyName(values[0].toString());
			nominationComments.setRank((Long)values[3]);
			
			userwiseComments = new HashMap<String, List<Object[]>>();
			
			commentsByUser = commentCategoryCandidateDAO.getAllCommentsByFreeUserAndCategoryForANomination((Long)values[4]);
			
			for(Object[] commentInfo:(List<Object[]>)commentsByUser){
				comments = userwiseComments.get(commentInfo[0]+IConstants.FREE_USER);
				if(comments == null)
					comments = new ArrayList<Object[]>();
				comments.add(commentInfo);
				userwiseComments.put(commentInfo[0]+IConstants.FREE_USER, comments);
			}
			
			commentsByUser = commentCategoryCandidateDAO.getAllCommentsByPaidUserAndCategoryForANomination((Long)values[4]);
			
			for(Object[] commentInfo:(List<Object[]>)commentsByUser){
				comments = userwiseComments.get(commentInfo[0]+IConstants.PARTY_ANALYST_USER);
				if(comments == null)
					comments = new ArrayList<Object[]>();
				comments.add(commentInfo);
				userwiseComments.put(commentInfo[0]+IConstants.PARTY_ANALYST_USER, comments);
			}
			
			allUsersCommentsForNomination = new ArrayList<CandidateCommentsVO>();
			for(Entry<String, List<Object[]>> entry:userwiseComments.entrySet()){
				comments = entry.getValue();
				candidateCommentsVO = new CandidateCommentsVO();
				candidateCommentsVO.setUserId((Long)comments.get(0)[0]);
				candidateCommentsVO.setUserName((String)comments.get(0)[1]);
				commetsAndScores = new ArrayList<UserCommentsInfoVO>(0);
				for(Object[] userCatComments:entry.getValue()){
					userCommentInfoVO = new UserCommentsInfoVO();
					userCommentInfoVO.setComment(userCatComments[2].toString());
					userCommentInfoVO.setCommentCategory(userCatComments[3].toString());
					userCommentInfoVO.setCommentCategoryId((Long)userCatComments[4]);
					commentScore = userCatComments[6] != null ? Math.round(Double.parseDouble(userCatComments[6]+"")*100)/100.0 : 0.0d;
					userCommentInfoVO.setCommentScore(commentScore);
					commetsAndScores.add(userCommentInfoVO);
				}
				candidateCommentsVO.setCommetsAndScores(commetsAndScores);
				allUsersCommentsForNomination.add(candidateCommentsVO);
			}
			
			categoryAndScores = new HashMap<Long, UserCommentsInfoVO>();
			
			for(CandidateCommentsVO commentByUser:allUsersCommentsForNomination){
				for(UserCommentsInfoVO commentCategory:commentByUser.getCommetsAndScores()){
					userCommentInfoVO = categoryAndScores.get(commentCategory.getCommentCategoryId());
					
					if(userCommentInfoVO == null)
						userCommentInfoVO = new UserCommentsInfoVO();
					else
						categoryAndScores.remove(commentCategory.getCommentCategoryId());
					
					userCommentInfoVO.setCommentCategoryId(commentCategory.getCommentCategoryId());
					userCommentInfoVO.setCommentCategory(commentCategory.getCommentCategory());
					userCommentInfoVO.setCommentScore(Math.round((commentCategory.getCommentScore() + userCommentInfoVO.getCommentScore())*100)/100.0);
					categoryAndScores.put(commentCategory.getCommentCategoryId(), userCommentInfoVO);
				}
			}
			
			commetsAndScores = new ArrayList<UserCommentsInfoVO>();
			
			for(Map.Entry<Long, UserCommentsInfoVO> categoryEntry:categoryAndScores.entrySet())
				commetsAndScores.add(categoryEntry.getValue());
			
			nominationComments.setPostedUsersCount(Long.parseLong(userwiseComments.size()+""));
			nominationComments.setCommetsAndScores(commetsAndScores);
			allNominationsComments.add(nominationComments);
		}
		
		return allNominationsComments;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getElectionYearsForConstituency(Long constituencyId, Boolean onlyAssets)
	{
		List<SelectOptionVO> electionYears = new ArrayList<SelectOptionVO>();
		List years = null;
		
		if(onlyAssets)
			years = constituencyElectionDAO.findAllAssetsAndLiabilitiesElectionsByConstituencyId(constituencyId);
		else
			years = constituencyElectionDAO.findAllElectionsByConstituencyId(constituencyId);
		
		if(years != null && years.size() > 0)
		{
			for(int i=0; i<years.size(); i++)
			{
				SelectOptionVO option = new SelectOptionVO();				
				Object[] params= (Object[])years.get(i);
				option.setId((Long)params[0]);
				option.setName(params[1].toString());
				
				electionYears.add(option);				
			}
		}
		
		return electionYears;
	}
	
	public List<CandidateVO> getCandidateResultsForConstiElectionId(Long constiElectionId)
	{
		List<CandidateVO> candidates = new ArrayList<CandidateVO>();
		
		List info = nominationDAO.findByConstituencyElection(constiElectionId);
		
		if(info != null || info.size() > 0)
		{
			for(int i=0; i<info.size(); i++)
			{
				CandidateVO candidate = new CandidateVO();				
				Object[] params = (Object []) info.get(i);
				candidate.setCandidateName(params[5].toString());
				candidate.setParty(params[0].toString());
				candidate.setStatus(params[3].toString());
				candidate.setNominationId((Long) params[4]);
				candidate.setId((Long) params[6]);
				candidate.setElectionId((Long) params[7]);
				candidate.setElectionType(params[8].toString());
				candidate.setYear(params[9].toString());
				
				candidates.add(candidate);
			}
		}
		return candidates;
	}
	
	public String getUserSelectedChoice(String choice){
		if(choice.equalsIgnoreCase("approved")){
			return IConstants.TRUE;
		}else if(choice.equalsIgnoreCase("rejected")){
			return IConstants.FALSE;
		}else{
			return IConstants.REJECTED;
		}			
	}
	
	public List<CandidateCommentsVO> extractCommentsDetailsFromList(List comments)
	{
		List<CandidateCommentsVO> commentsList = null;
		
		if(comments != null || comments.size() > 0)
		{
			commentsList = new ArrayList<CandidateCommentsVO>();
			for (int i = 0; i < comments.size(); i++)
			{
				CandidateCommentsVO comment = new CandidateCommentsVO();
				Object[] params = (Object[])comments.get(i);
				comment.setCommentId((Long)params[0]);
				comment.setCommentDesc(params[1].toString());
				comment.setCommentedOn(params[2].toString());
				comment.setCommentedBy(params[3].toString());
				comment.setCandidateId((Long)params[4]);
				comment.setCandidate(params[5].toString());
				comment.setPartyName(params[6].toString());
				comment.setConstituencyName(params[7].toString());
				comment.setRank((Long)params[8]);
				comment.setElectionType(params[9].toString());
				comment.setElectionYear(params[10].toString());
				
				commentsList.add(comment);
				
			}
		}			
		
		return commentsList;
	}
	
	
		
	public List<CandidateCommentsVO> getAllComments(String fromDate,String toDate)
	{
		List<CandidateCommentsVO> candidateComments = null;			
		try{
			candidateComments = new ArrayList<CandidateCommentsVO>();
			
			Date firstDate = DateService.convertStringToDate(fromDate, IConstants.DATE_PATTERN_YYYY_MM_DD);
			Date secondDate = DateService.convertStringToDate(toDate, IConstants.DATE_PATTERN_YYYY_MM_DD);
			List comments = commentCategoryCandidateDAO.getAllOpenedComments(firstDate, secondDate);			
			
			return extractCommentsDetailsFromList(comments);
			
		}catch(Exception e){				
			return candidateComments;
		}	
	}
	
	public List<CandidateCommentsVO> scrutinizePostedComments(List<Long> reasonIds, String actionType, String fromDate, String toDate) 
	{
		List<CandidateCommentsVO> candidateComments = null;
		String isApproved = IConstants.FALSE;
		
		if(actionType.equalsIgnoreCase(IConstants.APPROVED))
			isApproved = IConstants.TRUE;
		else if(actionType.equalsIgnoreCase(IConstants.REJECTED))
			isApproved = IConstants.FALSE;
		
		try {
			
			int count = commentDataDAO.updateSetIsApprovedStatusToPostedComments(reasonIds, isApproved);
			
			Date firstDate = DateService.convertStringToDate(fromDate, IConstants.DATE_PATTERN_YYYY_MM_DD);
			Date secondDate = DateService.convertStringToDate(toDate, IConstants.DATE_PATTERN_YYYY_MM_DD);
			List comments = commentCategoryCandidateDAO.getAllOpenedComments(firstDate, secondDate);
			
			return extractCommentsDetailsFromList(comments);
			
		} catch (Exception e) {
			return candidateComments;
		}
	}
}
