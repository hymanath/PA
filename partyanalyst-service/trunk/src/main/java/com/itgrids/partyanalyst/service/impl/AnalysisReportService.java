/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 24,2010
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

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICommentCategoryCandidateDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionStateResultDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.ElectionBasicCommentsVO;
import com.itgrids.partyanalyst.dto.ElectionCommentsVO;
import com.itgrids.partyanalyst.dto.PartyAnalysisBasicVO;
import com.itgrids.partyanalyst.dto.PartyAnalysisReportVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.CommentCategoryCandidate;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IAnalysisReportService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;

public class AnalysisReportService implements IAnalysisReportService {
	
	private IPartyDAO partyDAO;
	private IStateDAO stateDAO;
	private IElectionDAO electionDAO;
	private IStaticDataService staticDataService;
	private IPartyElectionResultDAO partyElectionResultDAO;
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
	
}
