/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 24,2010
 */
package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICommentCategoryCandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionStateResultDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.AnalysisCategoryBasicVO;
import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionBasicCommentsVO;
import com.itgrids.partyanalyst.dto.ElectionCommentsVO;
import com.itgrids.partyanalyst.dto.PartyAnalysisBasicVO;
import com.itgrids.partyanalyst.dto.PartyAnalysisReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionAnalysisResultVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotesMarginAnalysisVO;
import com.itgrids.partyanalyst.model.CommentCategoryCandidate;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IAnalysisReportService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;

public class AnalysisReportService implements IAnalysisReportService {
	
	private IPartyDAO partyDAO;
	private IStateDAO stateDAO;
	private IElectionDAO electionDAO;
	private INominationDAO nominationDAO;
	private IStaticDataService staticDataService;
	private IPartyElectionResultDAO partyElectionResultDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private ICommentCategoryCandidateDAO commentCategoryCandidateDAO; 
	
	
	private static final Logger log = Logger.getLogger(AnalysisReportService.class);
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);

	
	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
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

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IPartyElectionResultDAO getPartyElectionResultDAO() {
		return partyElectionResultDAO;
	}

	public void setPartyElectionResultDAO(
			IPartyElectionResultDAO partyElectionResultDAO) {
		this.partyElectionResultDAO = partyElectionResultDAO;
	}

	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public ICommentCategoryCandidateDAO getCommentCategoryCandidateDAO() {
		return commentCategoryCandidateDAO;
	}

	public void setCommentCategoryCandidateDAO(
			ICommentCategoryCandidateDAO commentCategoryCandidateDAO) {
		this.commentCategoryCandidateDAO = commentCategoryCandidateDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAnalysisReportService#getAnalysisReportForAPartyInAnElection(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public PartyAnalysisReportVO getAnalysisReportForAPartyInAnElection(
			String electionType, String electionYear, Long stateId, Long partyId) {
		
		log.debug("Inside getAnalysisReportForAPartyInAnElection Method....... ");
		
		PartyAnalysisReportVO partyAnalysisReportVO = null;
		ResultStatus resultStatus = new ResultStatus();
		List<SelectOptionVO> allianceParties = null;
		Election electionMain = null;
		Long electionId = null;
		
		try{
			
			Party party = partyDAO.get(partyId);
			State state = stateDAO.get(stateId);
			partyAnalysisReportVO = new PartyAnalysisReportVO();
			List election = electionDAO.findElectionIdByElectionTypeAndYear(electionType, electionYear, stateId);
			if(election != null && election.size() > 0){
				Object params = (Object)election.get(0);
				electionId = (Long)params;
			}
			if(electionId != null)
				electionMain = electionDAO.get(electionId);
			
			if(electionMain != null)
				allianceParties = staticDataService.getAlliancePartiesAsVO(electionYear,electionMain.getElectionScope().getElectionType().getElectionTypeId(),partyId);
				
			//analysis basic info for main party
			if(partyId != null && electionId != null && electionType != null && electionYear != null){
			PartyAnalysisBasicVO basicAnalysisForMainParty = getPartyAnalysisBasicInformation(partyId,electionId,electionType,electionYear);
			partyAnalysisReportVO.setPartyId(partyId);
			
			//check for party short name
			if(party.getShortName() != null)
			partyAnalysisReportVO.setPartyName(party.getShortName());
			else
			partyAnalysisReportVO.setPartyName(party.getLongName());
			
			partyAnalysisReportVO.setElectionId(electionId);
			partyAnalysisReportVO.setElectionType(electionType);
			partyAnalysisReportVO.setElectionYear(electionYear);
			partyAnalysisReportVO.setStateName(state.getStateName());
			partyAnalysisReportVO.setElectionTypeId(electionMain.getElectionScope().getElectionType().getElectionTypeId());
			partyAnalysisReportVO.setPartyBasicAnalysisVO(basicAnalysisForMainParty);
			}
			
			//analysis basic info for alliance parties
			if(allianceParties != null && allianceParties.size() > 0){
				List<PartyAnalysisBasicVO> basicAnalysisForAlliancPartys = new ArrayList<PartyAnalysisBasicVO>();
				for(SelectOptionVO partyOption:allianceParties){
					if(!partyOption.getId().equals(partyId)){
						PartyAnalysisBasicVO basicAnalysisForAlliancParty = getPartyAnalysisBasicInformation(partyOption.getId(),electionId,electionType,electionYear);
						basicAnalysisForAlliancPartys.add(basicAnalysisForAlliancParty);
					}
				}
				partyAnalysisReportVO.setAlliancPartys(allianceParties);
				partyAnalysisReportVO.setAlliancPartiesBasicAnalysisVO(basicAnalysisForAlliancPartys);
			}
			
			
		}
		catch(Exception ex){
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			partyAnalysisReportVO = new PartyAnalysisReportVO();
			partyAnalysisReportVO.setResultStatus(resultStatus);
		}
	  return partyAnalysisReportVO;
	}

	/*
	 * This Method Returns Basic Analysis Details For A Party
	 */
	@SuppressWarnings("unchecked")
	public PartyAnalysisBasicVO getPartyAnalysisBasicInformation(Long partyId,Long electionId,String electionType,String electionYear){
		
		log.debug("Inside getPartyAnalysisBasicInformation Method..... ");
		
		PartyAnalysisBasicVO partyAnalysisBasicVO = null;
		if(partyId != null && electionId != null){
			List basicResults = null;
			partyAnalysisBasicVO = new PartyAnalysisBasicVO();
			Long constiParticipated = new Long(0);
			
			basicResults = partyElectionResultDAO.getBasicPartyElectionResultForAPartyInAnElection(electionId, partyId);
			if(basicResults == null || basicResults.size() == 0){
				staticDataService.savePartyElectionResultForAPartyForAElection(electionId, partyId);
				basicResults = partyElectionResultDAO.getBasicPartyElectionResultForAPartyInAnElection(electionId, partyId);
			}
			
			//processing basic results list and set to VO
			if(basicResults != null && basicResults.size() > 0){
				Object[] basicResultParams = (Object[])basicResults.get(0);
				partyAnalysisBasicVO.setPartyId((Long)basicResultParams[0]);
				partyAnalysisBasicVO.setPartyName((String)basicResultParams[1]);
				partyAnalysisBasicVO.setSeatsWon(new Long((String)basicResultParams[2]));
				constiParticipated = new Long((String)basicResultParams[3]);
				partyAnalysisBasicVO.setPartiConstituencies(new Long((String)basicResultParams[3]));
				partyAnalysisBasicVO.setSeatsLost(new Long((String)basicResultParams[3]) - new Long((String)basicResultParams[2]));
			}
			
			//get comments details
			List commentsCountForParty = commentCategoryCandidateDAO.getCommentsCountForAPartyInAnElection(electionId, partyId);
			if(commentsCountForParty != null && commentsCountForParty.size() > 0){
				Object params = (Object)commentsCountForParty.get(0);
				Long countVal = (Long)params;
				partyAnalysisBasicVO.setAnalyzedConsti(countVal);
				partyAnalysisBasicVO.setNotAnalyzedConsti(constiParticipated - countVal);
			}
		}
	 return partyAnalysisBasicVO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAnalysisReportService#getCandidateCommentDetailsInAnElection(java.lang.Long, java.lang.Long)
	 * Method To Find All Comments For A Party In An Election
	 */
	@SuppressWarnings("unchecked")
	public List<ElectionBasicCommentsVO> getCandidateCommentDetailsInAnElection(
			Long electionId, Long partyId) {
		
		log.debug("Inside getCandidateCommentDetailsInAnElection Method..... ");
		
		Map<Long,List<CandidateCommentsVO>> commentsDataMap = null;
		List<ElectionBasicCommentsVO> electionBasicCommentsVO = null;
		ResultStatus resultStatus = new ResultStatus();
		
		try{
			if(electionId != null && partyId != null){
				electionBasicCommentsVO = new ArrayList<ElectionBasicCommentsVO>();
				commentsDataMap = new HashMap<Long,List<CandidateCommentsVO>>();
				
				List commentsDetails = commentCategoryCandidateDAO.getCommentsResultsForAPartyInAnElection(electionId, partyId);
				Party party = partyDAO.get(partyId);
				
				if(commentsDetails != null && commentsDetails.size() > 0){
					for(int i=0;i<commentsDetails.size();i++){
					 	Object[] results = (Object[])commentsDetails.get(i);
						Long constituencyId = (Long)results[0];
						if(commentsDataMap.isEmpty() || !commentsDataMap.containsKey(constituencyId)){
							List<CandidateCommentsVO> candidateCommentsList = new ArrayList<CandidateCommentsVO>();
							CandidateCommentsVO candidComments = getCandidateCommentsProcessedToMap(results);
							candidateCommentsList.add(candidComments);
							commentsDataMap.put(constituencyId, candidateCommentsList);
						}
						else if(commentsDataMap.containsKey(constituencyId)){
							List<CandidateCommentsVO> candidateCommentsList = commentsDataMap.get(constituencyId);
							CandidateCommentsVO candidComments = getCandidateCommentsProcessedToMap(results);
							candidateCommentsList.add(candidComments);
							commentsDataMap.put(constituencyId, candidateCommentsList);
						}
					}
				}
				
				//Processing the Map and set the Data to VO
				if(!commentsDataMap.isEmpty()){
					Set entries = commentsDataMap.entrySet();
					Iterator iterator = entries.iterator();
					while(iterator.hasNext()){
					Map.Entry entry = (Map.Entry)iterator.next();
					List<CandidateCommentsVO> commentsList = (List<CandidateCommentsVO>)entry.getValue();
					Long constituencyId = (Long)entry.getKey();
					ElectionBasicCommentsVO elecBasicComments = new ElectionBasicCommentsVO();
					elecBasicComments.setConstituencyId(constituencyId);
					elecBasicComments.setConstituencyName(commentsList.get(0).getConstituencyName());
					elecBasicComments.setPartyId(partyId);
					elecBasicComments.setPartyName(party.getShortName());
					elecBasicComments.setCandidateComments(commentsList);
					
					electionBasicCommentsVO.add(elecBasicComments);
					}
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			ElectionBasicCommentsVO electionBasicCommentsErrVO = new ElectionBasicCommentsVO();
			electionBasicCommentsErrVO.setResultStatus(resultStatus);
			electionBasicCommentsVO.add(electionBasicCommentsErrVO);
		}
	 return electionBasicCommentsVO;
	}
	
	public CandidateCommentsVO getCandidateCommentsProcessedToMap(Object[] results){
		
		log.debug("Inside getCandidateCommentsProcessedToMap Method..... ");
		
		CandidateCommentsVO candidateCommentsVO = null;
		if(results != null){
			candidateCommentsVO = new CandidateCommentsVO();
			candidateCommentsVO.setConstituencyName((String)results[1]);
			candidateCommentsVO.setCandidateId((Long)results[2]);
			candidateCommentsVO.setCandidate((String)results[3]);
			candidateCommentsVO.setCommentDesc((String)results[4]);
			candidateCommentsVO.setCommentedBy((String)results[5]);
			
			candidateCommentsVO.setCommentedOn(sdf.format((Date)results[6]).toString());
			candidateCommentsVO.setCommentCategory((String)results[7]);
		}
		
	 return candidateCommentsVO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAnalysisReportService#getAnalysisCategoryResultForAPartyInAnElection(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Boolean)
	 * Method to Process and Provide The Analysis Report In Detail For a Particular party(with/without alliance) election Result Category(won/lost)
	 */
	@SuppressWarnings("unchecked")
	public PartyPositionAnalysisResultVO getAnalysisCategoryResultForAPartyInAnElection(
			String electionType, String electionYear, Long electionId,
			Long stateId, Long partyId, String analysisCategory,
			Boolean includeAllianc) {
		log.debug("Inside getAnalysisCategoryResultForAPartyInAnElection Method..... ");
		
		PartyPositionAnalysisResultVO partyPositionAnalysisResultVO = null;
		ResultStatus resultStatus = new ResultStatus();
	
	    try{
	    
	    List basicResults = null;
	    List analysisResults = null;
	    List<AnalysisCategoryBasicVO> analysisBasicResults = null;
	    List<SelectOptionVO> multipleCategories = null;
	    Long totalConstituenciesCount = new Long(0);
	    partyPositionAnalysisResultVO = new PartyPositionAnalysisResultVO();
	    
        if(electionId == null){
	    	List election = electionDAO.findElectionIdByElectionTypeAndYear(electionType, electionYear, stateId);
			if(election != null && election.size() > 0){
				Object params = (Object)election.get(0);
				electionId = (Long)params;
			}
	    }
	    
	    if(electionId != null){
	    List totConstiCount = constituencyElectionDAO.findConstituenciesCountInAnElection(electionId);
	    if(totConstiCount != null && totConstiCount.size() > 0){
	    	Object params = (Object)totConstiCount.get(0);
	    	totalConstituenciesCount = (Long)params;
	    }
	    }    
	    
	    //block for alliance parties
		if(includeAllianc.equals(true)){
			
		}
		
		//block without alliance parties
		else if(includeAllianc.equals(false)){
			if(partyId != null && electionId != null){
				basicResults = partyElectionResultDAO.getBasicPartyElectionResultForAPartyInAnElection(electionId, partyId);
				if(basicResults == null || basicResults.size() == 0){
					staticDataService.savePartyElectionResultForAPartyForAElection(electionId, partyId);
					basicResults = partyElectionResultDAO.getBasicPartyElectionResultForAPartyInAnElection(electionId, partyId);
				}
				
				//analysis results
				if(analysisCategory.equals(IConstants.CANDIDATE_COMMENTS_WON)){
				analysisResults = commentCategoryCandidateDAO.getCommentsCountInAnElectionForAPartyForCommentCategory(electionId,partyId,IConstants.CANDIDATE_COMMENTS_WON);
				analysisBasicResults = getAnalysisCategoryBasicDetails(electionId,partyId,includeAllianc,IConstants.CANDIDATE_COMMENTS_WON);
				multipleCategories = getMultipleCategoriesBasicResults(electionId,partyId,includeAllianc,IConstants.CANDIDATE_COMMENTS_WON);
				}
				else if(analysisCategory.equals(IConstants.CANDIDATE_COMMENTS_LOST)){
				analysisResults = commentCategoryCandidateDAO.getCommentsCountInAnElectionForAPartyForCommentCategory(electionId,partyId,IConstants.CANDIDATE_COMMENTS_LOST);
				analysisBasicResults = getAnalysisCategoryBasicDetails(electionId,partyId,includeAllianc,IConstants.CANDIDATE_COMMENTS_LOST);
				multipleCategories = getMultipleCategoriesBasicResults(electionId,partyId,includeAllianc,IConstants.CANDIDATE_COMMENTS_LOST);
				}
			}
		}
		
		//process basicResults
		if(basicResults != null && basicResults.size() > 0){
			Object[] basicResultParams = (Object[])basicResults.get(0);
			Long seatsWon = new Long((String)basicResultParams[2]);
			Long totConstiParticipated = new Long((String)basicResultParams[3]);
			Long seatsLost = totConstiParticipated - seatsWon;
			
			if(analysisCategory.equals(IConstants.CANDIDATE_COMMENTS_WON)){
				partyPositionAnalysisResultVO.setResultType(IConstants.CANDIDATE_COMMENTS_WON);
				partyPositionAnalysisResultVO.setResultTypeValue(seatsWon);
			}
			else if(analysisCategory.equals(IConstants.CANDIDATE_COMMENTS_LOST)){
				partyPositionAnalysisResultVO.setResultType(IConstants.CANDIDATE_COMMENTS_LOST);
				partyPositionAnalysisResultVO.setResultTypeValue(seatsLost);
			}
		}
		
		//Analysis results
		if(analysisResults != null && analysisResults.size() > 0){
			Object analysisCount = (Object)analysisResults.get(0);
			Long value = (Long)analysisCount;
			partyPositionAnalysisResultVO.setAnalyzedConsti(value);
			partyPositionAnalysisResultVO.setNotAnalyzedConsti(partyPositionAnalysisResultVO.getResultTypeValue() - value);
		}
		
		if(analysisBasicResults != null && analysisBasicResults.size() > 0)
			partyPositionAnalysisResultVO.setAnalysisCategoryBasicResultVO(analysisBasicResults);
		
		if(multipleCategories == null || multipleCategories.size() == 0){
			for(int i=1;i<=4;i++){
				SelectOptionVO resultVO = new SelectOptionVO();
				resultVO.setId(new Long(i));
				resultVO.setName("0");
				
				multipleCategories.add(resultVO);
			}
			SelectOptionVO resultVO = new SelectOptionVO();
			resultVO.setId(new Long(0));
			resultVO.setName("0");
			multipleCategories.add(resultVO);
		}
		
		if(multipleCategories != null && multipleCategories.size() > 0){
			partyPositionAnalysisResultVO.setMultipleCategories(multipleCategories);
		}
		
	    }
	    catch(Exception ex){
	    	ex.printStackTrace();
	    	resultStatus.setExceptionEncountered(ex);
	    	resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	    	partyPositionAnalysisResultVO = new PartyPositionAnalysisResultVO();
	    	partyPositionAnalysisResultVO.setResultStatus(resultStatus);
	    }
		
	 return partyPositionAnalysisResultVO;
	}
	
	/*
	 * Method To Get AnalysisCategoryBasicDetails
	 */
	@SuppressWarnings("unchecked")
	public List<AnalysisCategoryBasicVO> getAnalysisCategoryBasicDetails(Long electionId,Long partyId,Boolean includeAllianc,String analysisCategory){
		
		log.debug("Inside getAnalysisCategoryBasicDetails Method..... ");
		
		List<AnalysisCategoryBasicVO> analysisCategoryBasicVO = null;
		if(electionId != null && partyId != null && includeAllianc == false){
			analysisCategoryBasicVO = new ArrayList<AnalysisCategoryBasicVO>();
			
			List analysisResults = commentCategoryCandidateDAO.getCommentsCountGroupedByCommentCategory(electionId, partyId, analysisCategory);
			if(analysisResults != null && analysisResults.size() > 0){
				for(int i=0;i<analysisResults.size();i++){
					Object[] params = (Object[])analysisResults.get(i);
					
					AnalysisCategoryBasicVO analysisBasics = new AnalysisCategoryBasicVO();
					analysisBasics.setCategoryId((Long)params[1]);
					analysisBasics.setCategoryType((String)params[2]);
					analysisBasics.setCategoryResultCount((Long)params[0]);
					
					analysisCategoryBasicVO.add(analysisBasics);
				}
			}
		}
	 return analysisCategoryBasicVO;
	}
	
	/*
	 * Method to get basic results of analysis on multiple categories
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getMultipleCategoriesBasicResults(Long electionId,Long partyId,Boolean includeAllianc,String analysisCategory){
		
		log.debug("Inside getMultipleCategoriesBasicResults Method..... ");
		
		List<SelectOptionVO> multipleCategories = null;
		Map<Long,Long> multipleCategoryMap = null;
		if(electionId != null && partyId != null && includeAllianc == false){
			multipleCategories = new ArrayList<SelectOptionVO>();
			multipleCategoryMap = new HashMap<Long,Long>();
			
			List multipleCategoryComments = commentCategoryCandidateDAO.getCommentsCommentCategoryCountGroupedByConstituencyForAParty(electionId,partyId,analysisCategory);
			
			log.debug("MultipleCategoryComments Size :" + multipleCategoryComments.size());
			if(multipleCategoryComments != null && multipleCategoryComments.size() > 0){
				for(int i=0;i<multipleCategoryComments.size();i++){
					Object[] params = (Object[])multipleCategoryComments.get(i);
					Long commentsCount = (Long)params[0];
					if(multipleCategoryMap.isEmpty() || !multipleCategoryMap.containsKey(commentsCount)){
						multipleCategoryMap.put(commentsCount, new Long(1));
					}
					else if(multipleCategoryMap.containsKey(commentsCount)){
						Long countVal = multipleCategoryMap.get(commentsCount);
						multipleCategoryMap.put(commentsCount, ++countVal);
					}
				}
				
				if(!multipleCategoryMap.isEmpty()){
					Long nthCount = new Long(0);
					Set entries = multipleCategoryMap.entrySet();
					Iterator iterator = entries.iterator();
					while(iterator.hasNext()){
					Map.Entry entry = (Map.Entry)iterator.next();
					Long constiCount = (Long)entry.getValue();
					Long commentsCat = (Long)entry.getKey();
					
					log.debug("commentsCat :"  + commentsCat);
					log.debug("constiCount :"  + constiCount);
					
					if(commentsCat > new Long(4)){
						nthCount+=constiCount;
					}
					else{
						SelectOptionVO mulCategory = new SelectOptionVO();
						mulCategory.setId(commentsCat);
						mulCategory.setName(constiCount.toString());
						
						multipleCategories.add(mulCategory);
					}
					}
					//for nth comments
					if(nthCount > new Long(0)){
					SelectOptionVO mulCategory = new SelectOptionVO();
					mulCategory.setId(new Long(0));
					mulCategory.setName(nthCount.toString());
					
					multipleCategories.add(mulCategory);
					}
				}
			}
		}
	 return multipleCategories;
	}

	@SuppressWarnings("unchecked")
	public List<CandidateElectionResultVO> getElectionResultsForNotAnalyzedConstituencies(
			Long electionId, Long partyId,Long stateId) {
		
		log.debug("Inside getElectionResultsForNotAnalyzedConstituencies Method..... ");
		
		List<CandidateElectionResultVO> notAnalyzedResultsList = null;
		Map<Long,Nomination> partyNominationsMap = null;
		
		if(electionId != null && partyId != null && stateId != null){
			//get party participated nominations
			notAnalyzedResultsList = new ArrayList<CandidateElectionResultVO>();
			partyNominationsMap = new HashMap<Long,Nomination>();
			List<Nomination> partyNominations = nominationDAO.findByElectionIdAndPartyIdStateId(electionId,partyId,stateId);
			if(partyNominations != null && partyNominations.size() > 0){
				for(Nomination nominations:partyNominations){
					partyNominationsMap.put(nominations.getNominationId(), nominations);
				}
			}
			
			//getPartyCommentDetails
			List commentNominations = commentCategoryCandidateDAO.getNominationsForCandidateHavingComments(electionId,partyId);
			if(commentNominations != null && commentNominations.size() > 0){
				for(int i=0;i<commentNominations.size();i++){
					Object params = commentNominations.get(i);
					Long nominationId = (Long)params;
					if(!partyNominationsMap.isEmpty() && partyNominationsMap.containsKey(nominationId))
						partyNominationsMap.remove(nominationId);
				}
			}
			
			if(!partyNominationsMap.isEmpty()){
				Set entries = partyNominationsMap.entrySet();
				Iterator iterator = entries.iterator();
				while(iterator.hasNext()){
				Map.Entry entry = (Map.Entry)iterator.next();
				Nomination nomination = (Nomination)entry.getValue();
				CandidateElectionResultVO candidateElectionResultVO = getProcessedResultsForNotanalyzed(nomination);
				notAnalyzedResultsList.add(candidateElectionResultVO);
				}
			}
		}
		return notAnalyzedResultsList;
	}
	
	public CandidateElectionResultVO getProcessedResultsForNotanalyzed(Nomination nomination){
		
		log.debug("Inside getProcessedResultsForNotanalyzed Method..... ");
		
		CandidateElectionResultVO candidateElectionResultVO = null;
		if(nomination != null){
			candidateElectionResultVO = new CandidateElectionResultVO();
			candidateElectionResultVO.setCandidateId(nomination.getCandidate().getCandidateId());
			candidateElectionResultVO.setCandidateName(nomination.getCandidate().getLastname());
			candidateElectionResultVO.setConstituencyId(nomination.getConstituencyElection().getConstituency().getConstituencyId());
			candidateElectionResultVO.setPartyId(nomination.getParty().getPartyId());
			if(nomination.getParty().getShortName() != null)
			candidateElectionResultVO.setPartyName(nomination.getParty().getShortName());
			else
			candidateElectionResultVO.setPartyName(nomination.getParty().getLongName());
			candidateElectionResultVO.setConstituencyName(nomination.getConstituencyElection().getConstituency().getName());
			candidateElectionResultVO.setTotalVotesEarned(nomination.getCandidateResult().getVotesEarned().longValue());
			candidateElectionResultVO.setTotalValidVotes(nomination.getConstituencyElection().getConstituencyElectionResult().getValidVotes().longValue());
			candidateElectionResultVO.setRank(nomination.getCandidateResult().getRank());
			candidateElectionResultVO.setVotesPercentage(nomination.getCandidateResult().getVotesPercengate());
			
		}
	 return candidateElectionResultVO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAnalysisReportService#getCandidateCommentDetailsInAnElection(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ElectionBasicCommentsVO> getCandidateCommentDetailsInAnElection(
			Long electionId, Long partyId, String category,Long categoryTypeId) {
		
        log.debug("Inside getCandidateCommentDetailsInAnElection Method..... ");
		
		Map<Long,List<CandidateCommentsVO>> commentsDataMap = null;
		List<ElectionBasicCommentsVO> electionBasicCommentsVO = null;
		ResultStatus resultStatus = new ResultStatus();
		
		try{
			if(electionId != null && partyId != null){
				electionBasicCommentsVO = new ArrayList<ElectionBasicCommentsVO>();
				commentsDataMap = new HashMap<Long,List<CandidateCommentsVO>>();
				List commentsDetails = null;
				
				//List commentsDetails = commentCategoryCandidateDAO.getCommentsResultsForAPartyInAnElection(electionId, partyId);
				if(categoryTypeId == null || categoryTypeId.equals(new Long(0)))
				    commentsDetails = commentCategoryCandidateDAO.getCommentsResultsForAPartyInAnElection(electionId, partyId,category);
				else 
					commentsDetails = commentCategoryCandidateDAO.getCommentsResultsForAPartyInAnElection(electionId, partyId,category,categoryTypeId);	
				
				Party party = partyDAO.get(partyId);
				
				if(commentsDetails != null && commentsDetails.size() > 0){
					for(int i=0;i<commentsDetails.size();i++){
					 	Object[] results = (Object[])commentsDetails.get(i);
						Long constituencyId = (Long)results[0];
						if(commentsDataMap.isEmpty() || !commentsDataMap.containsKey(constituencyId)){
							List<CandidateCommentsVO> candidateCommentsList = new ArrayList<CandidateCommentsVO>();
							CandidateCommentsVO candidComments = getCandidateCommentsProcessedToMap(results);
							candidateCommentsList.add(candidComments);
							commentsDataMap.put(constituencyId, candidateCommentsList);
						}
						else if(commentsDataMap.containsKey(constituencyId)){
							List<CandidateCommentsVO> candidateCommentsList = commentsDataMap.get(constituencyId);
							CandidateCommentsVO candidComments = getCandidateCommentsProcessedToMap(results);
							candidateCommentsList.add(candidComments);
							commentsDataMap.put(constituencyId, candidateCommentsList);
						}
					}
				}
				
				//Processing the Map and set the Data to VO
				if(!commentsDataMap.isEmpty()){
					Set entries = commentsDataMap.entrySet();
					Iterator iterator = entries.iterator();
					while(iterator.hasNext()){
					Map.Entry entry = (Map.Entry)iterator.next();
					List<CandidateCommentsVO> commentsList = (List<CandidateCommentsVO>)entry.getValue();
					Long constituencyId = (Long)entry.getKey();
					ElectionBasicCommentsVO elecBasicComments = new ElectionBasicCommentsVO();
					elecBasicComments.setConstituencyId(constituencyId);
					elecBasicComments.setConstituencyName(commentsList.get(0).getConstituencyName());
					elecBasicComments.setPartyId(partyId);
					elecBasicComments.setPartyName(party.getShortName());
					elecBasicComments.setCandidateComments(commentsList);
					
					electionBasicCommentsVO.add(elecBasicComments);
					}
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			ElectionBasicCommentsVO electionBasicCommentsErrVO = new ElectionBasicCommentsVO();
			electionBasicCommentsErrVO.setResultStatus(resultStatus);
			electionBasicCommentsVO.add(electionBasicCommentsErrVO);
		}
	 return electionBasicCommentsVO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAnalysisReportService#getElectionResultsForNotAnalyzedConstituencies(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<CandidateElectionResultVO> getElectionResultsForNotAnalyzedConstituencies(
			Long electionId, Long partyId, Long stateId, String category) {
        log.debug("Inside getElectionResultsForNotAnalyzedConstituencies Method..... ");
		
		List<CandidateElectionResultVO> notAnalyzedResultsList = null;
		Map<Long,Nomination> partyNominationsMap = null;
		
		if(electionId != null && partyId != null && stateId != null && category != null){
			//get party participated nominations
			notAnalyzedResultsList = new ArrayList<CandidateElectionResultVO>();
			partyNominationsMap = new HashMap<Long,Nomination>();
			List<Nomination> partyNominations = null;
			
			if(category.equals(IConstants.CANDIDATE_COMMENTS_WON))
			    partyNominations = nominationDAO.findByElectionIdAndPartyIdStateIdForWon(electionId,partyId,new Long(1));
			else if(category.equals(IConstants.CANDIDATE_COMMENTS_LOST))
				partyNominations = nominationDAO.findByElectionIdAndPartyIdStateIdForLost(electionId,partyId,new Long(1));
				
			if(partyNominations != null && partyNominations.size() > 0){
				for(Nomination nominations:partyNominations){
					partyNominationsMap.put(nominations.getNominationId(), nominations);
				}
			}
			
			//getPartyCommentDetails
			//List commentNominations = commentCategoryCandidateDAO.getNominationsForCandidateHavingComments(electionId,partyId);
			List commentNominations = commentCategoryCandidateDAO.getNominationsForCandidateHavingComments(electionId,partyId,category);
			if(commentNominations != null && commentNominations.size() > 0){
				for(int i=0;i<commentNominations.size();i++){
					Object params = commentNominations.get(i);
					Long nominationId = (Long)params;
					if(!partyNominationsMap.isEmpty() && partyNominationsMap.containsKey(nominationId))
						partyNominationsMap.remove(nominationId);
				}
			}
			
			if(!partyNominationsMap.isEmpty()){
				Set entries = partyNominationsMap.entrySet();
				Iterator iterator = entries.iterator();
				while(iterator.hasNext()){
				Map.Entry entry = (Map.Entry)iterator.next();
				Nomination nomination = (Nomination)entry.getValue();
				CandidateElectionResultVO candidateElectionResultVO = getProcessedResultsForNotanalyzed(nomination);
				notAnalyzedResultsList.add(candidateElectionResultVO);
				}
			}
		}
		return notAnalyzedResultsList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAnalysisReportService#getVotesMarginAnalysisResults(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<VotesMarginAnalysisVO> getVotesMarginAnalysisResults(
			Long electionId, Long partyId, String category) {
		
		 log.debug("Inside getVotesMarginAnalysisResults Method..... ");
		 
		 List<VotesMarginAnalysisVO> votesMarginAnalysisVO = null;
		 ResultStatus resultStatus = new ResultStatus();
		 
		 try{
			 
			 List<Nomination> partyNominations = null;
			 List<Nomination> oppPartyNominations = null;
			 //Map<constituencyId,Nomination> oppositionPartyNominations ..
			 Map<Long,Nomination> oppPartyNominationsMap = null;
			 
			 if(electionId != null && partyId != null && category != null){
				 
				 votesMarginAnalysisVO = new ArrayList<VotesMarginAnalysisVO>();
				 
				  if(category.equals(IConstants.CANDIDATE_COMMENTS_WON)){
					  partyNominations = nominationDAO.findByElectionIdAndPartyIdStateIdForWon(electionId,partyId,new Long(1));
					  List<Long> constituencyIds = getConstituencyIdsFromNominations(partyNominations);
					  oppPartyNominations = nominationDAO.findByElectionIdAndRank(electionId,new Long(2),constituencyIds);
				  }
				  else if(category.equals(IConstants.CANDIDATE_COMMENTS_LOST)){
					  partyNominations = nominationDAO.findByElectionIdAndPartyIdStateIdForLost(electionId, partyId, new Long(1));
					  List<Long> constituencyIds = getConstituencyIdsFromNominations(partyNominations);
					  oppPartyNominations = nominationDAO.findByElectionIdAndRank(electionId,new Long(1),constituencyIds);
				  }
				  
				  if(oppPartyNominations != null && oppPartyNominations.size() > 0)
					  oppPartyNominationsMap = getOppPartyNominationsMap(oppPartyNominations);
				  
				  //creating dummy map with dummy margin values
				  Map<Long,List<Long>> marginNominationIds = new HashMap<Long,List<Long>>();
				  for(int i=1;i<5;i++){
					  List<Long> arrayList = new ArrayList<Long>();
					  marginNominationIds.put(new Long(i), arrayList);
				  }
				  
				  //process the party result nominations
				  if(partyNominations != null && partyNominations.size() > 0 && !oppPartyNominationsMap.isEmpty()){
					  for(Nomination partyNomintn:partyNominations){
						  Long constituencyId = partyNomintn.getConstituencyElection().getConstituency().getConstituencyId();
						  Nomination oppPartyNomitn = null;
						  if(oppPartyNominationsMap.containsKey(constituencyId))
						  oppPartyNomitn = oppPartyNominationsMap.get(constituencyId);
						  
						  if(oppPartyNomitn != null){
							  Long maginValue = getMarginValueFromPartyAndOppPartyNominations(partyNomintn,oppPartyNomitn);
							  
							  if(maginValue != null){
								  Long marginVal = new Long(0);
								  if(maginValue > new Long(0) && maginValue <= new Long(10))
									  marginVal = new Long(1);
								  else if(maginValue > new Long(10) && maginValue <= new Long(20))
									  marginVal = new Long(2);
								  else if(maginValue > new Long(20) && maginValue <= new Long(30))
									  marginVal = new Long(3);
								  else if(maginValue > new Long(30))
									  marginVal = new Long(4);
								  
								  List<Long> partyNomin = marginNominationIds.get(marginVal);
								  partyNomin.add(partyNomintn.getNominationId());
								  marginNominationIds.put(marginVal, partyNomin);
							  }
						  }
							  
					  }
				  }
				  
				  //get analyzed constituencies
				  if(!marginNominationIds.isEmpty()){
					Set entries = marginNominationIds.entrySet();
					Iterator iterator = entries.iterator();
					while(iterator.hasNext()){
					Map.Entry entry = (Map.Entry)iterator.next();
					List<Long> nominationIds = (List<Long>)entry.getValue();
					if(nominationIds != null && nominationIds.size() > 0){
					Long id = (Long)entry.getKey();
					VotesMarginAnalysisVO votesMarginAnalysis = getDetailsOfMarginVotesAnalysis(id,nominationIds);
					
					if(votesMarginAnalysis != null)
						votesMarginAnalysisVO.add(votesMarginAnalysis);
					}
					}
				  }
				  
			 }
			 
			 
		 }
		 catch(Exception ex){
			 ex.printStackTrace();
			 VotesMarginAnalysisVO votesMarginAnalysis = new VotesMarginAnalysisVO();
			 resultStatus.setExceptionEncountered(ex);
			 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			 votesMarginAnalysis.setResultStatus(resultStatus);
			 votesMarginAnalysisVO = new ArrayList<VotesMarginAnalysisVO>();
			 votesMarginAnalysisVO.add(votesMarginAnalysis);
		 }
		
		return votesMarginAnalysisVO;
	}
	
	/*
	 * 
	 */
	public List<Long> getConstituencyIdsFromNominations(List<Nomination> nominations){
		
		 log.debug("Inside getConstituencyIdsFromNominations Method..... ");
		 
		 List<Long> constituencyIds = null;
		 
		 if(nominations != null && nominations.size() > 0){
			 constituencyIds = new ArrayList<Long>();
			 for(Nomination nomintn:nominations){
				 Long constituencyId = nomintn.getConstituencyElection().getConstituency().getConstituencyId();
				 constituencyIds.add(constituencyId);
			 }
		 }
	 return constituencyIds;
	}
	
	/*
	 * 
	 */
	@SuppressWarnings("unchecked")
	public VotesMarginAnalysisVO getDetailsOfMarginVotesAnalysis(Long id,List<Long> nominationIds){
		
		 log.debug("Inside getDetailsOfMarginVotesAnalysis Method..... ");
		 
		 VotesMarginAnalysisVO votesMarginAnalysisVO = null;
		 
		 if(nominationIds != null && nominationIds.size() > 0){
			 votesMarginAnalysisVO = new VotesMarginAnalysisVO();
			 List<AnalysisCategoryBasicVO> analysisCategoryVosList = new ArrayList<AnalysisCategoryBasicVO>();
			 List analysisResults = commentCategoryCandidateDAO.getCommentResultsForCandidateNominations(nominationIds);
			 if(analysisResults != null && analysisResults.size() > 0){
				 Long reasonsCount = new Long(analysisResults.size());
				 for(int i=0;i<analysisResults.size();i++){
					 Object[] params = (Object[])analysisResults.get(i);
					 Long countVal = (Long)params[2];
					 //reasonsCount+=countVal;
					 
					 AnalysisCategoryBasicVO analysisCategory = new AnalysisCategoryBasicVO();
					 analysisCategory.setCategoryId((Long)params[0]);
					 analysisCategory.setCategoryType((String)params[1]);
					 analysisCategory.setCategoryResultCount(countVal);
					 analysisCategoryVosList.add(analysisCategory);
				 }
				 votesMarginAnalysisVO.setNominationIds(nominationIds);
				 votesMarginAnalysisVO.setCandidatesCount(new Long(nominationIds.size()));
				 votesMarginAnalysisVO.setAnalyzedCount(reasonsCount);
				 votesMarginAnalysisVO.setAnalysisCategoryBasicVO(analysisCategoryVosList);
				 
				 if(id.equals(new Long(1))){
					 votesMarginAnalysisVO.setMarginValueOne(new Long(0));
					 votesMarginAnalysisVO.setMarginValueTwo(new Long(10));
					 votesMarginAnalysisVO.setMarginRange("0 - 10 %");
				 }
				 else if(id.equals(new Long(2))){
					 votesMarginAnalysisVO.setMarginValueOne(new Long(10));
					 votesMarginAnalysisVO.setMarginValueTwo(new Long(20));
					 votesMarginAnalysisVO.setMarginRange("10 - 20 %");
				 }
				 else if(id.equals(new Long(3))){
					 votesMarginAnalysisVO.setMarginValueOne(new Long(20));
					 votesMarginAnalysisVO.setMarginValueTwo(new Long(30));
					 votesMarginAnalysisVO.setMarginRange("20 - 30 %");
				 }
				 else{
					 votesMarginAnalysisVO.setMarginValueOne(new Long(30));
					 votesMarginAnalysisVO.setMarginValueTwo(new Long(100));
					 votesMarginAnalysisVO.setMarginRange("30 % and above %");
				 }
				 
			 }
		 }
		 
	  return votesMarginAnalysisVO;
	}
	
	/*
	 * 
	 */
	public Map<Long,Nomination> getOppPartyNominationsMap(List<Nomination> oppPartyNominations) throws Exception{
		
		 log.debug("Inside getOppPartyNominationsMap Method..... ");
		 
		 Map<Long,Nomination> oppPartyNominationsMap = null;
		if(oppPartyNominations != null && oppPartyNominations.size() > 0){
			oppPartyNominationsMap = new HashMap<Long,Nomination>();
			for(Nomination nomination:oppPartyNominations){
				Long constituencyId = nomination.getConstituencyElection().getConstituency().getConstituencyId();
				oppPartyNominationsMap.put(constituencyId, nomination);
			}
		}
	 return oppPartyNominationsMap;
	}
	
	/*
	 * 
	 */
	public Long getMarginValueFromPartyAndOppPartyNominations(Nomination partyNomination,Nomination oppPartyNomination) throws Exception{
		
		log.debug("Inside getMarginValueFromPartyAndOppPartyNominations Method..... ");
		Long marginValueLong = null;
		
		try{
		String partyVotesPercent = partyNomination.getCandidateResult().getVotesPercengate();
		Long partyRank = partyNomination.getCandidateResult().getRank();
		String oppPartyVotesPercent = oppPartyNomination.getCandidateResult().getVotesPercengate();
		Double marginValue = null;
		if(partyRank.equals(new Long(1))){
			marginValue = new BigDecimal(new Double(partyVotesPercent)-new Double(oppPartyVotesPercent)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		else{
			marginValue = new BigDecimal(new Double(oppPartyVotesPercent)-new Double(partyVotesPercent)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		marginValueLong = marginValue.longValue();
		}
		catch(Exception ex){
			ex.printStackTrace();
			log.debug("Exception Raised :" + ex);
		}
		return marginValueLong;
	}
	
}
