package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.ICommentCategoryCandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.service.IElectionAnalyzeService;
import com.itgrids.partyanalyst.utils.CandidateDetailsMarginVotesComoparator;
import com.itgrids.partyanalyst.utils.IConstants;

public class ElectionAnalyzeService implements IElectionAnalyzeService {

	private final static Logger log = Logger.getLogger(ElectionAnalyzeService.class);
	
	
	
	private ICommentCategoryCandidateDAO commentCategoryCandidateDAO;
	private INominationDAO nominationDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private TransactionTemplate transactionTemplate;
	private ICandidateResultDAO candidateResultDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	
	public ICommentCategoryCandidateDAO getCommentCategoryCandidateDAO() {
		return commentCategoryCandidateDAO;
	}

	public void setCommentCategoryCandidateDAO(
			ICommentCategoryCandidateDAO commentCategoryCandidateDAO) {
		this.commentCategoryCandidateDAO = commentCategoryCandidateDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
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
	
	

	public ICandidateResultDAO getCandidateResultDAO() {
		return candidateResultDAO;
	}

	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}

	
	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	/**
	 * This generic method gives details like all the candidates and winning candidates and candidates that belong to a party
	 * and the winning candidates for a particular election year in the country or state or district or constituency or 
	 * zptc or mptc based on the user selection choice in the state page.
	 * 
	 * @param electionType
	 * @param electionYear
	 * @param resultsCategory
	 * @param electionLevel
	 * @param locationId
	 * @param partyId
	 * @param stateId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public CandidateDetailsVO getCandidatesPartyInfoForAnElectionType(String electionType,String electionYear,String resultsCategory,
			String electionLevel,Long locationId,Long partyId,Long stateId,int startIndex, int maxResult, String order, String columnName){
		CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();
		
		try{
			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE)  || electionType.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE) ) {
				
				if(electionType.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE))
					if(electionLevel.equalsIgnoreCase("stateWiseZptc"))
						electionLevel = IConstants.STATE_LEVEL;
					 if(electionLevel.equalsIgnoreCase("districtWiseZptc"))
						electionLevel = IConstants.DISTRICT_LEVEL;
				
				if(electionType.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE))
					if(electionLevel.equalsIgnoreCase("stateWiseMptc"))
						electionLevel = IConstants.STATE_LEVEL;
				    if(electionLevel.equalsIgnoreCase("districtWiseMptc"))
					electionLevel = IConstants.DISTRICT_LEVEL;
				    
				if(partyId==0l){					
					candidateDetailsVO = getCandidatesInfoForAnElectionType(electionType,electionYear,resultsCategory,electionLevel,locationId,stateId,startIndex,maxResult,order,columnName);					
				}else{
					candidateDetailsVO = getCandidatesWinnerInfoForAnElectionTypes(electionType,electionYear,resultsCategory,electionLevel,locationId,partyId,stateId,startIndex,maxResult,order,columnName);
				}
			}else if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
				if(partyId==0l){
					candidateDetailsVO = getCandidatesInfoForAnElectionType(IConstants.PARLIAMENT_ELECTION_TYPE,electionYear,resultsCategory,electionLevel,locationId,stateId,startIndex,maxResult,order,columnName);		
				}else{
					candidateDetailsVO = getCandidatesWinnerInfoForAnElectionTypes(IConstants.PARLIAMENT_ELECTION_TYPE,electionYear,resultsCategory,electionLevel,locationId,partyId,stateId,startIndex,maxResult,order,columnName);
				}
			}
			//Local Election Body
			else {
				if(partyId==0l){
					candidateDetailsVO = getCandidatesInfoForLocalElectionBodies(electionType,electionYear,resultsCategory,electionLevel,locationId,0l,stateId,startIndex,maxResult,order,columnName);						
				}else{
					candidateDetailsVO = getCandidatesInfoForLocalElectionBodies(electionType,electionYear,resultsCategory,electionLevel,locationId,partyId,stateId,startIndex,maxResult,order,columnName);
				}		
			}

			//modified by sai
			//check and place candidate comments size..
			if(candidateDetailsVO.getCandidateDetails() != null){
			for(CandidateDetailsVO results:candidateDetailsVO.getCandidateDetails()){
			List count = commentCategoryCandidateDAO.getCommentsCountForACandidate(results.getCandidateId(), results.getConstituencyId(), results.getElectionType(), results.getElectionYear());			
			if(count != null && count.size() > 0){
				Object params = (Object)count.get(0);
				Long commentsCount = (Long)params;
				results.setCommentsCount(commentsCount);
				log.debug("Comments Count:" + commentsCount);
			}
			}
			}
		 return candidateDetailsVO;		
		}catch(Exception e){
			e.printStackTrace();
			if(log.isDebugEnabled()){
				log.debug("Exception raised in getCandidatesPartyInfoForAnElectionType() method of static data servicce");
			}
			return null;
		}
	}
	
	public CandidateDetailsVO getCandidatesInfoForLocalElectionBodies(String electionType,String electionYear,
			String resultsCategory,String electionLevel,Long locationId,Long partId,Long stateId,int startIndex, int maxResult, String order, String columnName){
		Long winnerCandidateRank = 1l,successorCandidateRank=2l;
		List allCandidateResult= null;
		List winnerCandidateResult = null;
		List successorCandidateResult = null;
		CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();
		CandidateDetailsVO details = new CandidateDetailsVO();
		List<CandidateDetailsVO> allCandidates = new ArrayList<CandidateDetailsVO>(0);		
		List<CandidateDetailsVO> successorCandidate = new ArrayList<CandidateDetailsVO>(0);
		int lostFlag=1;
		try{
				if(resultsCategory.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
					List nominationList = nominationDAO.getCountOfAllLocalBodyCandidates(electionYear,locationId,stateId,electionType,winnerCandidateRank,electionLevel,IConstants.WINNER_CANDIDATES,null,0l);
					List candidateList = nominationDAO.getCountOfAllLocalBodyCandidates(electionYear,locationId,stateId,electionType,winnerCandidateRank,electionLevel,IConstants.WINNER_CANDIDATES,"Candidates",0l);
					Long count = new Long(nominationList.get(0).toString())- new Long(candidateList.get(0).toString());
					//System.out.println("Count--->"+count);					
					if(count!=0l){
						if(electionLevel.equalsIgnoreCase(IConstants.STATE_LEVEL)){														
							 winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRankForLocalBody(electionYear,stateId,electionType,winnerCandidateRank,partId);
							 successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRankForLocalBody(electionYear,stateId,electionType,successorCandidateRank,partId);							
						}else if(electionLevel.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
							List<Long> listOfConstituencies  = new ArrayList<Long>();	
							listOfConstituencies = getConstituenciesForDistrictByTypeAsList(locationId,stateId,electionYear,electionType);
							if(listOfConstituencies != null && listOfConstituencies.size() > 0){
								winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRankForLocalBodys(listOfConstituencies,electionYear,electionType,winnerCandidateRank,partId);
								successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRankForLocalBodys(listOfConstituencies,electionYear,electionType,successorCandidateRank,partId);									
								}
						}
						//winnerCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,0l,0,electionType);
						//candidateDetailsVO.setCandidateDetails(winnerCandidate);	
						saveMarginVotesAndPercentagesForWinners(winnerCandidateResult,successorCandidateResult);
						//String electionYear,Long locationId,Long stateId,String electionType,Long rank,String locationType,String candidateType,Long partyId
						details = getAllCandidateDetails(electionYear,locationId,stateId,electionType,1L,electionLevel,resultsCategory,0L,startIndex,maxResult,order,columnName);
						candidateDetailsVO.setCandidateDetails(details.getCandidateDetails());
						candidateDetailsVO.setTotalSearchCount(details.getTotalSearchCount());
					}else{
						//String electionYear,Long locationId,Long stateId,String electionType,Long rank,String locationType,String candidateType,Long partyId
						details = getAllCandidateDetails(electionYear,locationId,stateId,electionType,1L,electionLevel,resultsCategory,partId,startIndex,maxResult,order,columnName);						
						candidateDetailsVO.setCandidateDetails(details.getCandidateDetails());
						candidateDetailsVO.setTotalSearchCount(details.getTotalSearchCount());
					}
				}else if(resultsCategory.equalsIgnoreCase(IConstants.ALL_CANDIDATES) || resultsCategory.equalsIgnoreCase(IConstants.LOST_CANDIDATES)){
					List nominationList = nominationDAO.getCountOfAllLocalBodyCandidates(electionYear,locationId,stateId,electionType,winnerCandidateRank,electionLevel,IConstants.LOST_CANDIDATES,null,0l);
					List candidateList = nominationDAO.getCountOfAllLocalBodyCandidates(electionYear,locationId,stateId,electionType,winnerCandidateRank,electionLevel,IConstants.LOST_CANDIDATES,"Candidates",0l);
					Long count = new Long(nominationList.get(0).toString())- new Long(candidateList.get(0).toString());
					//System.out.println("Count--->"+count);
				//	System.out.println("nominationList--->"+new Long(nominationList.get(0).toString()));
				//	System.out.println("candidateList--->"+new Long(candidateList.get(0).toString()));
					//System.out.println("==========");
					if(count!=0l){
						 if(electionLevel.equalsIgnoreCase(IConstants.STATE_LEVEL)){							
							winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRankForLocalBody(electionYear,stateId,electionType,winnerCandidateRank,partId);
							successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRankForLocalBody(electionYear,stateId,electionType,successorCandidateRank,partId);
							allCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearForLocalBody(electionYear,stateId,electionType);					
						}else if(electionLevel.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){							
							//Local Body ELections
								List<Long> listOfConstituencies  = new ArrayList<Long>();	
								listOfConstituencies = getConstituenciesForDistrictByTypeAsList(locationId,stateId,electionYear,electionType);
								StringBuilder listOfParliamentConstituencies  = new StringBuilder();	
								Iterator it = listOfConstituencies.iterator();
								while(it.hasNext()){							
									listOfParliamentConstituencies.append(",").append(new Long(it.next().toString()));
								}
								if(listOfConstituencies != null && listOfConstituencies.size() > 0){
									winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRankForLocalBodys(listOfConstituencies,electionYear,electionType,winnerCandidateRank,partId);
									successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRankForLocalBodys(listOfConstituencies,electionYear,electionType,successorCandidateRank,partId);
									allCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyForLocalBody(listOfParliamentConstituencies.substring(1),electionYear,electionType);
								}
						}					
						successorCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,partId,lostFlag,electionType);
						allCandidates = setAllCandidateDetailsIntoVo(winnerCandidateResult,allCandidateResult,lostFlag,electionType);
						if(successorCandidate!=null){
							allCandidates.addAll(successorCandidate);
						}	
						//candidateDetailsVO.setCandidateDetails(allCandidates);
						//saveMarginVotesAndPercentagesForWinners(winnerCandidateResult,successorCandidateResult);
						saveMarginVotesAndPercentagesForAllCandidates(winnerCandidateResult,allCandidateResult);
						//String electionYear,Long locationId,Long stateId,String electionType,Long rank,String locationType,String candidateType,Long partyId
						details = getAllCandidateDetails(electionYear,locationId,stateId,electionType,1L,electionLevel,resultsCategory,0L,startIndex,maxResult,order,columnName);
						candidateDetailsVO.setCandidateDetails(details.getCandidateDetails());
						candidateDetailsVO.setTotalSearchCount(details.getTotalSearchCount());
					}else{
						//String electionYear,Long locationId,Long stateId,String electionType,Long rank,String locationType,String candidateType,Long partyId
						details = getAllCandidateDetails(electionYear,locationId,stateId,electionType,1L,electionLevel,resultsCategory,0L,startIndex,maxResult,order,columnName);
						candidateDetailsVO.setCandidateDetails(details.getCandidateDetails());
						candidateDetailsVO.setTotalSearchCount(details.getTotalSearchCount());
					}
				}	
				return candidateDetailsVO;
		}catch(Exception e){
			e.printStackTrace();
			if(log.isDebugEnabled()){
				log.debug("Exception raised in getCandidatesInfoForAnElectionType() method of static data servicce");
			}
			return null;
		}
	}
	/**
	 * This method gets all the data for a zptc/mptc based on user selection criteria.
	 * 
	 * @param electionType
	 * @param electionYear
	 * @param resultsCategory
	 * @param electionLevel
	 * @param stateId
	 * @param partyId
	 * @return
	 */
	public CandidateDetailsVO getZptcOrMptcCandidatesInfoForAnElectionType(String electionType,String electionYear,String resultsCategory,String electionLevel,Long stateId,final Long partyId){
		try{ 
			CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();
			Long winnerCandidateRank = 1l,successorCandidateRank=2l;
			List allCandidateResult= null;
			List winnerCandidateResult = null;
			List successorCandidateResult = null;
			List<CandidateDetailsVO> allCandidates = new ArrayList<CandidateDetailsVO>(0);
			List<CandidateDetailsVO> winnerCandidate = new ArrayList<CandidateDetailsVO>(0);
			List<CandidateDetailsVO> successorCandidate = new ArrayList<CandidateDetailsVO>(0);
			int lostFlag=0;	
			if(partyId==0l && (resultsCategory.equalsIgnoreCase(IConstants.ALL_CANDIDATES) || resultsCategory.equalsIgnoreCase(IConstants.LOST_CANDIDATES))){
				if(resultsCategory.equalsIgnoreCase(IConstants.LOST_CANDIDATES)){
					lostFlag = 1;
				}else{
					lostFlag = 0;
				}
				allCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaState(stateId,electionType,electionYear);	
				winnerCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,winnerCandidateRank);		
				successorCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,successorCandidateRank);			
				successorCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,0l,lostFlag,electionType);
				allCandidates = setAllCandidateDetailsIntoVo(winnerCandidateResult,allCandidateResult,0,electionType);
				if(successorCandidate!=null){
					allCandidates.addAll(successorCandidate);
				}	
				candidateDetailsVO.setCandidateDetails(allCandidates);
			}else if(partyId==0l && resultsCategory.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
				 winnerCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,winnerCandidateRank);
				 successorCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,successorCandidateRank);
				 winnerCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,0l,0,electionType);
				 candidateDetailsVO.setCandidateDetails(winnerCandidate);
			}else if(partyId!=0l && (resultsCategory.equalsIgnoreCase(IConstants.ALL_CANDIDATES)  || resultsCategory.equalsIgnoreCase(IConstants.LOST_CANDIDATES))){
				if(resultsCategory.equalsIgnoreCase(IConstants.LOST_CANDIDATES)){
					lostFlag = 1;
				}else{
					lostFlag = 0;
				}	
				allCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateForAParty( stateId, electionType, electionYear, partyId);	
				winnerCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,winnerCandidateRank);
				successorCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,successorCandidateRank);
				successorCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,partyId,lostFlag,electionType);
				allCandidates = setAllCandidateDetailsIntoVo(winnerCandidateResult,allCandidateResult,0,electionType);
				if(successorCandidate!=null){
					allCandidates.addAll(successorCandidate);
				}	
				candidateDetailsVO.setCandidateDetails(allCandidates);
			}else if(partyId!=0l && resultsCategory.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
				 winnerCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateForAPartyByRank( stateId, electionType, electionYear, partyId, winnerCandidateRank);
				 successorCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,successorCandidateRank);
				 winnerCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,partyId,0,electionType);
				 candidateDetailsVO.setCandidateDetails(winnerCandidate);
			}
			return candidateDetailsVO;
		}catch(Exception e){
			e.printStackTrace();
			if(log.isDebugEnabled()){
				log.debug("Exception raised in getCandidatesPartyInfoForAnElectionType() method of static data service");
			}
			return null;
		}
	}
	/**
	 * This method retrives data based on the user selection choice.
	 * 
	 * @param electionType
	 * @param electionYear
	 * @param resultsCategory
	 * @param electionLevel
	 * @param locationId
	 * @param stateId
	 * @return
	 */
	
	public CandidateDetailsVO getCandidatesInfoForAnElectionType(String electionType,String electionYear,
			String resultsCategory,String electionLevel,Long locationId,Long stateId,int startIndex, int maxResult, String order, String columnName){
		
		Long winnerCandidateRank = 1l,successorCandidateRank=2l;
		List allCandidateResult= null;
		List winnerCandidateResult = null;
		List successorCandidateResult = null;
		CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();
		CandidateDetailsVO details = new CandidateDetailsVO();
		List<CandidateDetailsVO> allCandidates = new ArrayList<CandidateDetailsVO>(0);		
		List<CandidateDetailsVO> successorCandidate = new ArrayList<CandidateDetailsVO>(0);
		int lostFlag=1;
		try{
			//String electionYear,Long locationId,String electionType,Long rank,String locationType,String candidateType,String candidateDetails);
		
				if(resultsCategory.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
					List nominationList = nominationDAO.getCountOfAllCandidates(electionYear,locationId,stateId,electionType,winnerCandidateRank,electionLevel,IConstants.WINNER_CANDIDATES,null,0l);
					List candidateList = nominationDAO.getCountOfAllCandidates(electionYear,locationId,stateId,electionType,winnerCandidateRank,electionLevel,IConstants.WINNER_CANDIDATES,"Candidates",0l);
					Long count = new Long(nominationList.get(0).toString())- new Long(candidateList.get(0).toString());
					//System.out.println("Count--->"+count);					
					if(count!=0l){
						if(electionLevel.equalsIgnoreCase(IConstants.COUNTRY_LEVEL)){
							winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,winnerCandidateRank);
							successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,successorCandidateRank);
						}else if(electionLevel.equalsIgnoreCase(IConstants.STATE_LEVEL)){						
							if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE) ||
									electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE) ||
									electionType.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE) ||
									electionType.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE)){
							  winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,winnerCandidateRank);
							  successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,successorCandidateRank);
							}							
						}else if(electionLevel.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){	
							
								if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE)){
									StringBuilder listOfConstituencies  = new StringBuilder();	
									listOfConstituencies = getConstituenciesForDistrictByType(locationId,stateId,electionYear,electionType);
									if(listOfConstituencies != null && listOfConstituencies.length() > 0){
										winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,winnerCandidateRank);
										successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
									}
								}else if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
									StringBuilder listOfParliamentConstituencies  = new StringBuilder();	
									listOfParliamentConstituencies = getParliamentConstituenciesForDistrict(locationId,stateId,electionYear);	
									if(listOfParliamentConstituencies != null && listOfParliamentConstituencies.length() > 0){
										winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,winnerCandidateRank);
										successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
									}
								}
						}else if(electionLevel.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){					
								winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,winnerCandidateRank);
								successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,successorCandidateRank);
						}
						//winnerCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,0l,0,electionType);
						//candidateDetailsVO.setCandidateDetails(winnerCandidate);	
						saveMarginVotesAndPercentagesForWinners(winnerCandidateResult,successorCandidateResult);
						//String electionYear,Long locationId,Long stateId,String electionType,Long rank,String locationType,String candidateType,Long partyId
						details = getAllCandidateDetails(electionYear,locationId,stateId,electionType,1L,electionLevel,resultsCategory,0L,startIndex,maxResult,order,columnName);
						candidateDetailsVO.setCandidateDetails(details.getCandidateDetails());
						candidateDetailsVO.setTotalSearchCount(details.getTotalSearchCount());
					}else{
						//String electionYear,Long locationId,Long stateId,String electionType,Long rank,String locationType,String candidateType,Long partyId
						details = getAllCandidateDetails(electionYear,locationId,stateId,electionType,1L,electionLevel,resultsCategory,0L,startIndex,maxResult,order,columnName);						
						candidateDetailsVO.setCandidateDetails(details.getCandidateDetails());
						candidateDetailsVO.setTotalSearchCount(details.getTotalSearchCount());
					}
				}else if(resultsCategory.equalsIgnoreCase(IConstants.ALL_CANDIDATES) || resultsCategory.equalsIgnoreCase(IConstants.LOST_CANDIDATES)){
					List nominationList = nominationDAO.getCountOfAllCandidates(electionYear,locationId,stateId,electionType,winnerCandidateRank,electionLevel,IConstants.LOST_CANDIDATES,null,0l);
					List candidateList = nominationDAO.getCountOfAllCandidates(electionYear,locationId,stateId,electionType,winnerCandidateRank,electionLevel,IConstants.LOST_CANDIDATES,"Candidates",0l);
					Long count = new Long(nominationList.get(0).toString())- new Long(candidateList.get(0).toString());
					//System.out.println("Count--->"+count);
				//	System.out.println("nominationList--->"+new Long(nominationList.get(0).toString()));
				//	System.out.println("candidateList--->"+new Long(candidateList.get(0).toString()));
					//System.out.println("==========");
					if(count!=0l){
						if(resultsCategory.equalsIgnoreCase(IConstants.ALL_CANDIDATES)){
							lostFlag = 0;
						}else if(resultsCategory.equalsIgnoreCase(IConstants.LOST_CANDIDATES)){
							lostFlag = 1;
						}
						if(electionLevel.equalsIgnoreCase(IConstants.COUNTRY_LEVEL)){
							winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,winnerCandidateRank);
							successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,successorCandidateRank);
							allCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryId(electionYear,locationId,electionType);
						}else if(electionLevel.equalsIgnoreCase(IConstants.STATE_LEVEL)){
							
							if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE) ||
									electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE) ||
									electionType.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE) ||
									electionType.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE)){
							winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,winnerCandidateRank);
							successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,successorCandidateRank);
							allCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYear(electionYear,stateId,electionType);	
							}
						}else if(electionLevel.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
							
							 if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE) || 
									 electionType.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE)){
									StringBuilder listOfConstituencies  = new StringBuilder();	
									listOfConstituencies = getConstituenciesForDistrictByType(locationId,stateId,electionYear,electionType);
									if(listOfConstituencies != null && listOfConstituencies.length() > 0){
										winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,winnerCandidateRank);
										successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
										allCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituency(listOfConstituencies.substring(1),electionYear,electionType);	
									}
							 }else if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
									StringBuilder listOfParliamentConstituencies  = new StringBuilder();	
									listOfParliamentConstituencies = getParliamentConstituenciesForDistrict(locationId,stateId,electionYear);	
									if(listOfParliamentConstituencies != null && listOfParliamentConstituencies.length() > 0){
										winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,winnerCandidateRank);
										successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
										allCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituency(listOfParliamentConstituencies.substring(1),electionYear,electionType);	
									}
							}
						}else if(electionLevel.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
							winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,winnerCandidateRank);
							successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,successorCandidateRank);
							allCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituency(locationId.toString(),electionYear,electionType);
						}
					
						successorCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,0l,lostFlag,electionType);
						allCandidates = setAllCandidateDetailsIntoVo(winnerCandidateResult,allCandidateResult,lostFlag,electionType);
						if(successorCandidate!=null){
							allCandidates.addAll(successorCandidate);
						}	
						//candidateDetailsVO.setCandidateDetails(allCandidates);
						//saveMarginVotesAndPercentagesForWinners(winnerCandidateResult,successorCandidateResult);
						saveMarginVotesAndPercentagesForAllCandidates(winnerCandidateResult,allCandidateResult);
						//String electionYear,Long locationId,Long stateId,String electionType,Long rank,String locationType,String candidateType,Long partyId
						details = getAllCandidateDetails(electionYear,locationId,stateId,electionType,1L,electionLevel,resultsCategory,0L,startIndex,maxResult,order,columnName);
						candidateDetailsVO.setCandidateDetails(details.getCandidateDetails());
						candidateDetailsVO.setTotalSearchCount(details.getTotalSearchCount());
					}else{
						//String electionYear,Long locationId,Long stateId,String electionType,Long rank,String locationType,String candidateType,Long partyId
						details = getAllCandidateDetails(electionYear,locationId,stateId,electionType,1L,electionLevel,resultsCategory,0L,startIndex,maxResult,order,columnName);
						candidateDetailsVO.setCandidateDetails(details.getCandidateDetails());
						candidateDetailsVO.setTotalSearchCount(details.getTotalSearchCount());
					}
				}	
				return candidateDetailsVO;
		}catch(Exception e){
			e.printStackTrace();
			if(log.isDebugEnabled()){
				log.debug("Exception raised in getCandidatesInfoForAnElectionType() method of static data servicce");
			}
			return null;
		}
	}
	
	/**
	 * This method can be used to get all the details of all the candidates.
	 * 
	 * @param electionYear
	 * @param locationId
	 * @param stateId
	 * @param electionType
	 * @param rank
	 * @param locationType
	 * @param candidateType
	 * @param partyId
	 * @param startIndex
	 * @param maxResult
	 * @param order
	 * @param columnName
	 * @return
	 */
	public CandidateDetailsVO getAllCandidateDetails(String electionYear,Long locationId,Long stateId,String electionType,Long rank,String locationType,String candidateType,Long partyId,int startIndex, int maxResult, String order, String columnName){
		CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();
		List<CandidateDetailsVO> data = new ArrayList<CandidateDetailsVO>(0); 
		String sortColumnName="";
		
		try{
			if(columnName.equalsIgnoreCase("candidateName")){
				sortColumnName = "model.candidate.lastname";
			}else if(columnName.equalsIgnoreCase("votesEarned")){
				sortColumnName = "model.candidateResult.votesEarned";
			}else if(columnName.equalsIgnoreCase("votesPercentage")){
				sortColumnName = "model.candidateResult.votesPercengate";
			}else if(columnName.equalsIgnoreCase("rank")){
				sortColumnName = "model.candidateResult.rank";
			}else if(columnName.equalsIgnoreCase("partyFlag")){
				sortColumnName = "model.party.partyFlag";
			}else if(columnName.equalsIgnoreCase("partyName")){
				sortColumnName = "model.party.longName";
			}else if(columnName.equalsIgnoreCase("constituencyName")){
				sortColumnName = "model.constituencyElection.constituency.name";
			}else if(columnName.equalsIgnoreCase("marginVotesPercentage")){
				sortColumnName = "model.candidateResult.marginVotesPercentage";
			}else if(columnName.equalsIgnoreCase("votesDifference")){
				sortColumnName = "model.candidateResult.marginVotes";
			}	
			
			List result = nominationDAO.getAllCandidateDetails(electionYear,locationId,stateId,electionType,rank,locationType,candidateType,partyId,startIndex,maxResult,order,sortColumnName);
			
			for(int i=0;i<result.size();i++){
				Object[] parms = (Object[])result.get(i);
				CandidateDetailsVO candidateDetailsVo = new CandidateDetailsVO();				
				String candidateName = parms[0].toString();
				if(candidateName.contains("\n")){
					candidateName = candidateName.replace("\n"," ");
					candidateDetailsVo.setCandidateName(candidateName.toUpperCase());
				}else{
					candidateDetailsVo.setCandidateName(candidateName.toUpperCase());
				}
				if(parms[2]!= null){
					candidateDetailsVo.setVotesEarned(new Float(parms[7].toString()).longValue()+"");
				}else{
					candidateDetailsVo.setVotesEarned("--");
				}
				if(parms[3]!= null){
					candidateDetailsVo.setVotesPercentage(parms[8].toString());
				}else{
					candidateDetailsVo.setVotesPercentage("--");
				}			
				candidateDetailsVo.setRank(new Long(parms[9].toString()));
				if(parms[5]!= null){
					candidateDetailsVo.setPartyFlag(parms[5].toString());
				}else{
					candidateDetailsVo.setPartyFlag("no_Image.png");
				}			
				candidateDetailsVo.setPartyName(parms[4].toString());
				Long constituencyID = new Long(parms[1].toString());
				candidateDetailsVo.setConstituencyId(constituencyID);
				candidateDetailsVo.setConstituencyName(parms[2].toString().toUpperCase());
				candidateDetailsVo.setElectionYear(parms[12].toString());
				candidateDetailsVo.setElectionType(parms[13].toString());
				candidateDetailsVo.setMoreDetails("view more details");
				if(parms[11]==null)
					candidateDetailsVo.setMarginVotesPercentage(new BigDecimal("0.0").setScale(2, BigDecimal.ROUND_HALF_UP));
				else
					candidateDetailsVo.setMarginVotesPercentage(new BigDecimal(parms[11].toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
				
				if(parms[10]==null)
					candidateDetailsVo.setVotesDifference(0.0f);
				else
					candidateDetailsVo.setVotesDifference(new Float(parms[10].toString()));
				candidateDetailsVo.setCandidateId(new Long(parms[14].toString()));
				data.add(candidateDetailsVo);
			}
			
			if(columnName.equalsIgnoreCase("marginVotesPercentage")){
				Collections.sort(data,new CandidateDetailsMarginVotesComoparator());
			}
			
			candidateDetailsVO.setCandidateDetails(data);
			
			List count = nominationDAO.getCountOfAllCandidateDetails(electionYear,locationId,stateId,electionType,rank,locationType,candidateType,partyId);
			candidateDetailsVO.setTotalSearchCount(new Long(count.get(0).toString()));
			//System.out.println(new Long(count.get(0).toString()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return candidateDetailsVO;
	}
	
	/**
	 * This method is used to caluculate and store marging votes and margin votes percentage.
	 * 
	 * @param winningCandidate
	 * @param allCandidates
	 */
	public void saveMarginVotesAndPercentagesForAllCandidates(final List winningCandidate,final List allCandidates){		
		try{
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						Map<Long,Float> winner = new HashMap<Long,Float>(0);
						Long constituencyId,candidateId;
						Float differenceVotes,votesPercentage;
						String marginVotesPercentage,electionYear,electionType;
						Double marginVotes;
						
							for(int i=0;i<winningCandidate.size();i++){
								Object[] parms = (Object[])winningCandidate.get(i);
								winner.put(Long.parseLong(parms[9].toString()), Float.parseFloat(parms[2].toString()));
							}		
							for(int i=0;i<allCandidates.size();i++){
								Object[] parms = (Object[])allCandidates.get(i);
								constituencyId = Long.parseLong(parms[9].toString());
								candidateId = Long.parseLong(parms[0].toString());
								electionYear = parms[11].toString();
								electionType = parms[12].toString();
								if(winner.containsKey(constituencyId)){
									differenceVotes = winner.get(constituencyId)-Float.parseFloat(parms[2].toString());
									if(winner.get(constituencyId)!=0){
										votesPercentage =  differenceVotes/winner.get(constituencyId)*100;
									}else{
										votesPercentage = 0f;
									}	
									marginVotes = Float.parseFloat(differenceVotes.toString())+0.0d;
									marginVotesPercentage = new BigDecimal(votesPercentage.floatValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
								}else{
									marginVotes =  0.0d;
									votesPercentage = 0f;
									marginVotesPercentage = votesPercentage.toString();
								}
								Long rank = new Long(parms[4].toString());
								
								if(rank!=1){									
									int count = candidateResultDAO.updateMarginVotesAndPercentage(marginVotesPercentage,marginVotes,electionYear,electionType,constituencyId,candidateId);
								}				
								//System.out.println("marginVotesPercentage--->"+marginVotesPercentage+"constituencyId-->"+constituencyId+"  candidateId--->"+candidateId);
						}
					}
				});
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	/**
	 * This method can be used to caluculate margin votes and margin votes percentage.
	 * 
	 * @param winningCandidate
	 * @param successorCandidate
	 */
	public void saveMarginVotesAndPercentagesForWinners(final List winningCandidate,final List successorCandidate){		
	try{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					Map<Long,Float> successor = new HashMap<Long,Float>(0);
					Long constituencyId,candidateId;
					Float differenceVotes,votesPercentage;
					String marginVotesPercentage,electionYear,electionType;
					Double marginVotes;
				for(int i=0;i<successorCandidate.size();i++){
					Object[] parms = (Object[])successorCandidate.get(i);
					successor.put(Long.parseLong(parms[9].toString()), Float.parseFloat(parms[2].toString()));
				}
				for(int i=0;i<winningCandidate.size();i++){
				//	CandidateDetailsVO candidateDetailsVo = new CandidateDetailsVO();
					Object[] parms = (Object[])winningCandidate.get(i);
					constituencyId = Long.parseLong(parms[9].toString());
					candidateId = Long.parseLong(parms[0].toString());
					electionYear = parms[11].toString();
					electionType = parms[12].toString();
					if(successor.containsKey(constituencyId)){
						differenceVotes = (Float.parseFloat(parms[2].toString())-successor.get(constituencyId));
						if(Float.parseFloat(parms[2].toString())!=0f){
							List list = nominationDAO.getVotesInfoForAConstituency(parms[9].toString(),parms[11].toString(),parms[12].toString());
							Double allCandidateVotes=0d;
							Double constituencyValidVotes=0d;
							for(int k=0; k<list.size(); k++){
								Object[] parm = (Object[])list.get(k);
								if(parm[0]!=null){
									allCandidateVotes = new Double(parm[0].toString());
								}
								if(parm[1]!=null){
									constituencyValidVotes = new Double(parm[1].toString());
								}
							}
							if(constituencyValidVotes!=0){							
								marginVotesPercentage = new BigDecimal((differenceVotes/constituencyValidVotes)*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							}else{
								marginVotesPercentage = new BigDecimal((differenceVotes/allCandidateVotes)*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();							
							}
						}else{
							marginVotesPercentage = "0.00";						 
						}		
						marginVotes = Float.parseFloat(differenceVotes.toString())+0.0d;							
					}else{
						differenceVotes = 0f;
						votesPercentage = 0f;
						marginVotes = differenceVotes + 0.0d;
						marginVotesPercentage = votesPercentage.toString();
					}
					
					int count = candidateResultDAO.updateMarginVotesAndPercentage(marginVotesPercentage,marginVotes,electionYear,electionType,constituencyId,candidateId);	
					
					//System.out.println("marginVotesPercentage--->"+marginVotesPercentage+"constituencyId-->"+constituencyId+"  candidateId--->"+candidateId);
					}
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * This method retrives candidates participated and won for a party.
	 * 
	 * @param electionType
	 * @param electionYear
	 * @param resultsCategory
	 * @param electionLevel
	 * @param locationId
	 * @param partyId
	 * @param stateId
	 * @return
	 */
								
	public CandidateDetailsVO getCandidatesWinnerInfoForAnElectionTypes(String electionType,String electionYear,
			String resultsCategory,String electionLevel,Long locationId,Long partyId,Long stateId,int startIndex, int maxResult, String order, String columnName){
		Long winnerCandidateRank = 1l,successorCandidateRank=2l;
		List allCandidateResult= null;
		List winnerCandidateResult = null;
		List successorCandidateResult = null;
		CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();
		CandidateDetailsVO details = new CandidateDetailsVO();
	
		int lostFlag=1;
		try{
				if(resultsCategory.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
					List nominationList = nominationDAO.getCountOfAllCandidates(electionYear,locationId,stateId,electionType,winnerCandidateRank,electionLevel,IConstants.WINNER_CANDIDATES,null,partyId);
					List candidateList = nominationDAO.getCountOfAllCandidates(electionYear,locationId,stateId,electionType,winnerCandidateRank,electionLevel,IConstants.WINNER_CANDIDATES,"Candidates",partyId);
					Long count = new Long(nominationList.get(0).toString())- new Long(candidateList.get(0).toString());
					//System.out.println("Count--->"+count);					
					if(count!=0l){
							if(electionLevel.equalsIgnoreCase(IConstants.COUNTRY_LEVEL)){
								winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRankForAParty(electionYear,locationId,electionType,partyId,winnerCandidateRank);
								successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,successorCandidateRank);
							}else if(electionLevel.equalsIgnoreCase(IConstants.STATE_LEVEL)){
								winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRankForAParty(electionYear,stateId,electionType,winnerCandidateRank,partyId);
								successorCandidateResult =  nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,successorCandidateRank);
								}else if(electionLevel.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
								if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){							
									StringBuilder listOfConstituencies  = new StringBuilder();	
									listOfConstituencies = getConstituenciesForDistrictByType(locationId,stateId,electionYear,electionType);
									winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRankAndPartyId(listOfConstituencies.substring(1),electionYear,electionType,winnerCandidateRank,partyId);
									successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
								}else if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
									StringBuilder listOfParliamentConstituencies  = new StringBuilder();	
									listOfParliamentConstituencies = getParliamentConstituenciesForDistrict(locationId,stateId,electionYear);						
									winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRankAndPartyId(listOfParliamentConstituencies.substring(1),electionYear,electionType,winnerCandidateRank,partyId);
									successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
								}		 
								}else if(electionLevel.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){					
									winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRankAndPartyId(locationId.toString(),electionYear,electionType,winnerCandidateRank,partyId);
									successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,successorCandidateRank);
								}
							//winnerCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,partyId,0,electionType);
							//candidateDetailsVO.setCandidateDetails(winnerCandidate);
							saveMarginVotesAndPercentagesForWinners(winnerCandidateResult,successorCandidateResult);
							//String electionYear,Long locationId,Long stateId,String electionType,Long rank,String locationType,String candidateType,Long partyId
							details = getAllCandidateDetails(electionYear,locationId,stateId,electionType,1L,electionLevel,resultsCategory,partyId,startIndex,maxResult,order,columnName);							
							candidateDetailsVO.setCandidateDetails(details.getCandidateDetails());
							candidateDetailsVO.setTotalSearchCount(details.getTotalSearchCount());
						}else{
							//String electionYear,Long locationId,Long stateId,String electionType,Long rank,String locationType,String candidateType,Long partyId
							details = getAllCandidateDetails(electionYear,locationId,stateId,electionType,1L,electionLevel,resultsCategory,partyId,startIndex,maxResult,order,columnName);							
							candidateDetailsVO.setCandidateDetails(details.getCandidateDetails());
							candidateDetailsVO.setTotalSearchCount(details.getTotalSearchCount());
						}
				}else if(resultsCategory.equalsIgnoreCase(IConstants.ALL_CANDIDATES) || resultsCategory.equalsIgnoreCase(IConstants.LOST_CANDIDATES)){
					List nominationList = nominationDAO.getCountOfAllCandidates(electionYear,locationId,stateId,electionType,winnerCandidateRank,electionLevel,IConstants.LOST_CANDIDATES,null,partyId);
					List candidateList = nominationDAO.getCountOfAllCandidates(electionYear,locationId,stateId,electionType,winnerCandidateRank,electionLevel,IConstants.LOST_CANDIDATES,"Candidates",partyId);
					Long count = new Long(nominationList.get(0).toString())- new Long(candidateList.get(0).toString());
					//System.out.println("Count--->"+count);
					if(count!=0l){
						if(resultsCategory.equalsIgnoreCase(IConstants.ALL_CANDIDATES)){
							lostFlag = 0;
						}else if(resultsCategory.equalsIgnoreCase(IConstants.LOST_CANDIDATES)){
							lostFlag = 1;						
						}
						if(electionLevel.equalsIgnoreCase(IConstants.COUNTRY_LEVEL)){
							winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,winnerCandidateRank);
							successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,successorCandidateRank);
							allCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdForAParty(electionYear,locationId,electionType,partyId);
						}else if(electionLevel.equalsIgnoreCase(IConstants.STATE_LEVEL)){
							winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,winnerCandidateRank);
							successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,successorCandidateRank);
							allCandidateResult = nominationDAO.findAllAssemblyCandidatesForAnElectionBytheElectionYear(electionYear,stateId,electionType,partyId);	
						}else if(electionLevel.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
							if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){						
								StringBuilder listOfConstituencies  = new StringBuilder();	
								listOfConstituencies = getConstituenciesForDistrictByType(locationId,stateId,electionYear,electionType);
								winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,winnerCandidateRank);
								successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
								allCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyByPartyId(listOfConstituencies.substring(1),electionYear,electionType,partyId);
							}else if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
								StringBuilder listOfParliamentConstituencies  = new StringBuilder();	
								listOfParliamentConstituencies = getParliamentConstituenciesForDistrict(locationId,stateId,electionYear);
								winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,winnerCandidateRank);
								successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
								allCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyByPartyId(listOfParliamentConstituencies.substring(1),electionYear,electionType,partyId);						
							}
						}else if(electionLevel.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
								winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,winnerCandidateRank);
								successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,successorCandidateRank);	
								allCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyByPartyId(locationId.toString(),electionYear,electionType,partyId);
						}						
						saveMarginVotesAndPercentagesForAllCandidates(winnerCandidateResult,allCandidateResult);						
						details = getAllCandidateDetails(electionYear,locationId,stateId,electionType,1L,electionLevel,resultsCategory,partyId,startIndex,maxResult,order,columnName);							
						candidateDetailsVO.setCandidateDetails(details.getCandidateDetails());
						candidateDetailsVO.setTotalSearchCount(details.getTotalSearchCount());
					}else{										
						details = getAllCandidateDetails(electionYear,locationId,stateId,electionType,1L,electionLevel,resultsCategory,partyId,startIndex,maxResult,order,columnName);							
						candidateDetailsVO.setCandidateDetails(details.getCandidateDetails());
						candidateDetailsVO.setTotalSearchCount(details.getTotalSearchCount());
					}
				}	
				return candidateDetailsVO;
		}catch(Exception e){
			e.printStackTrace();
			if(log.isDebugEnabled()){
				log.debug("Exception raised in getCandidatesInfoForAnElectionType() method of static data servicce");
			}
			return null;
		}
	}
	
	
	
	
	/**
	 * This method caluculates the votes marging between the winning candidate and succesor candidate and sets them in to a VO
	 * and returns the same data to the calling method.
	 * @param winningCandidate
	 * @param successorCandidate
	 * @param partyId
	 * @return
	 */

	public List<CandidateDetailsVO> setWinnerCandidateDetailsIntoVO(List winningCandidate,List successorCandidate,Long partyId,int flag,String electionType){	
		List<CandidateDetailsVO> candidateDetails = new ArrayList<CandidateDetailsVO>(0);		
		Long constituencyId,candidatepartyID=0l;
		Long selectedPartyId = partyId;
		Float differenceVotes,votesPercentage;
		Map<Long,Float> successor = new HashMap<Long,Float>(0);
		try{
			for(int i=0;i<successorCandidate.size();i++){
				Object[] parms = (Object[])successorCandidate.get(i);
				successor.put(Long.parseLong(parms[9].toString()), Float.parseFloat(parms[2].toString()));
			}
			log.info("Inside populateElectionsData() method..");
			for(int i=0;i<winningCandidate.size();i++){
				CandidateDetailsVO candidateDetailsVo = new CandidateDetailsVO();
				Object[] parms = (Object[])winningCandidate.get(i);
				constituencyId = Long.parseLong(parms[9].toString());
				candidateDetailsVo.setCandidateId(new Long(parms[0].toString()));
				String candidateName = parms[1].toString();
				if(candidateName.contains("\n")){
					candidateName = candidateName.replace("\n"," ");
					candidateDetailsVo.setCandidateName(candidateName.toUpperCase());
				}else{
					candidateDetailsVo.setCandidateName(candidateName.toUpperCase());
				}
				if(parms[2]!= null){
					candidateDetailsVo.setVotesEarned(parms[2].toString());
				}else{
					candidateDetailsVo.setVotesEarned("--");
				}
				if(parms[3]!= null){
					candidateDetailsVo.setVotesPercentage(parms[3].toString());
				}else{
					candidateDetailsVo.setVotesPercentage("--");
				}			
				candidateDetailsVo.setRank(new Long(parms[4].toString()));
				if(parms[6]!= null){
					candidateDetailsVo.setPartyFlag(parms[6].toString());
				}else{
					candidateDetailsVo.setPartyFlag("no_Image.png");
				}			
				candidateDetailsVo.setPartyName(parms[7].toString());
				Long constituencyID = new Long(parms[9].toString());
				candidateDetailsVo.setConstituencyId(constituencyID);
				candidateDetailsVo.setConstituencyName(parms[10].toString());
				candidateDetailsVo.setElectionYear(parms[11].toString());
				candidateDetailsVo.setElectionType(parms[12].toString());	
				if((electionType.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE)) || (electionType.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE))){
					candidateDetailsVo.setLocalBodyElectionsConstituencyName(parms[10].toString());
				}
				candidateDetailsVo.setMoreDetails("view more details");
				if(successor.containsKey(constituencyId)){
					differenceVotes = (Float.parseFloat(parms[2].toString())-successor.get(constituencyId));
					if(Float.parseFloat(parms[2].toString())!=0f){
						List list = nominationDAO.getVotesInfoForAConstituency(parms[9].toString(),parms[11].toString(),parms[12].toString());
						Double allCandidateVotes=0d;
						Double constituencyValidVotes=0d;
						for(int k=0; k<list.size(); k++){
							Object[] parm = (Object[])list.get(k);
							if(parm[0]!=null){
								allCandidateVotes = new Double(parm[0].toString());
							}
							if(parm[1]!=null){
								constituencyValidVotes = new Double(parm[1].toString());
							}
						}
						if(constituencyValidVotes!=0){							
							candidateDetailsVo.setMarginVotesPercentage(new BigDecimal((differenceVotes/constituencyValidVotes)*100).setScale(2, BigDecimal.ROUND_HALF_UP));
						}else{
							candidateDetailsVo.setMarginVotesPercentage(new BigDecimal((differenceVotes/allCandidateVotes)*100).setScale(2, BigDecimal.ROUND_HALF_UP));							
						}
					}else{
						candidateDetailsVo.setMarginVotesPercentage(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_HALF_UP));						 
					}		
					candidateDetailsVo.setVotesDifference(Float.parseFloat(differenceVotes.toString()));
					
							
				}else{
					differenceVotes = 0f;
					votesPercentage = 0f;
					candidateDetailsVo.setVotesDifference(differenceVotes);
					candidateDetailsVo.setMarginVotesPercentage(new BigDecimal(votesPercentage).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				
				candidatepartyID = new Long(parms[5].toString());
				if(flag!=1){
					if(selectedPartyId==0L){
						candidateDetails.add(candidateDetailsVo);
					//	System.out.println(candidateDetailsVo.getCandidateName()+"\t\t"+parms[4]+"\t\t"+candidateDetailsVo.getPartyName()+"\t"+candidateDetailsVo.getConstituencyName()+"\t"+candidateDetailsVo.getVotesEarned()+"\t"+Float.parseFloat(parms[2].toString())+"\t"+candidateDetailsVo.getVotesDifference()+"\t"+candidateDetailsVo.getVotesPercentage());
					}
					else if(candidatepartyID.equals(selectedPartyId)){
						candidateDetails.add(candidateDetailsVo);	
					//	System.out.println(candidateDetailsVo.getCandidateName()+"\t\t"+parms[4]+"\t\t"+candidateDetailsVo.getPartyName()+"\t"+candidateDetailsVo.getConstituencyName()+"\t"+candidateDetailsVo.getVotesEarned()+"\t"+Float.parseFloat(parms[2].toString())+"\t"+candidateDetailsVo.getVotesDifference()+"\t"+candidateDetailsVo.getVotesPercentage());
					}
				}else if(flag==1){
					if(selectedPartyId==0L){
						if(new Long(parms[4].toString())!=1l){
							candidateDetails.add(candidateDetailsVo);
						}
					//	System.out.println(candidateDetailsVo.getCandidateName()+"\t\t"+parms[4]+"\t\t"+candidateDetailsVo.getPartyName()+"\t"+candidateDetailsVo.getConstituencyName()+"\t"+candidateDetailsVo.getVotesEarned()+"\t"+Float.parseFloat(parms[2].toString())+"\t"+candidateDetailsVo.getVotesDifference()+"\t"+candidateDetailsVo.getVotesPercentage());
					}
					else if(candidatepartyID.equals(selectedPartyId)){
						if(new Long(parms[4].toString())!=1l){
							candidateDetails.add(candidateDetailsVo);
						}
					//	System.out.println(candidateDetailsVo.getCandidateName()+"\t\t"+parms[4]+"\t\t"+candidateDetailsVo.getPartyName()+"\t"+candidateDetailsVo.getConstituencyName()+"\t"+candidateDetailsVo.getVotesEarned()+"\t"+Float.parseFloat(parms[2].toString())+"\t"+candidateDetailsVo.getVotesDifference()+"\t"+candidateDetailsVo.getVotesPercentage());
					}					
				}								
			}
		return candidateDetails;		
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * This method caluculates the votes marging between the winning candidate and all the candidates and sets them in to a VO
	 * and returns the same data to the calling method.
	 * @param winningCandidate
	 * @param allCandidates
	 * @return
	 */
	public List<CandidateDetailsVO> setAllCandidateDetailsIntoVo(List winningCandidate,List allCandidates,int flag,String electionType){
		List<CandidateDetailsVO> candidateDetails = new ArrayList<CandidateDetailsVO>(0);	
		Map<Long,Float> winner = new HashMap<Long,Float>(0);
		Float differenceVotes,votesPercentage;
		Long constituencyId;
		Long rank=0l;
		try{
			for(int i=0;i<winningCandidate.size();i++){
				Object[] parms = (Object[])winningCandidate.get(i);
				winner.put(Long.parseLong(parms[9].toString()), Float.parseFloat(parms[2].toString()));
			}		
			for(int i=0;i<allCandidates.size();i++){
				Object[] parms = (Object[])allCandidates.get(i);
				CandidateDetailsVO candidateDetailsVo = new CandidateDetailsVO();
				constituencyId=Long.parseLong(parms[9].toString());
				candidateDetailsVo.setCandidateId(new Long(parms[0].toString()));
				String candidateName = parms[1].toString();
				if(candidateName.contains("\n")){
					candidateName = candidateName.replace("\n"," ");
					candidateDetailsVo.setCandidateName(candidateName.toUpperCase());
				}else{
					candidateDetailsVo.setCandidateName(candidateName.toUpperCase());
				}
				if(parms[2]!= null){
					candidateDetailsVo.setVotesEarned(parms[2].toString());
				}else{
					candidateDetailsVo.setVotesEarned("--");
				}
				if(parms[3]!= null){
					candidateDetailsVo.setVotesPercentage(parms[3].toString());
				}else{
					candidateDetailsVo.setVotesPercentage("--");
				}			
				candidateDetailsVo.setRank(new Long(parms[4].toString()));
				if(parms[6]!= null){
					candidateDetailsVo.setPartyFlag(parms[6].toString());
				}else{
					candidateDetailsVo.setPartyFlag("no_Image.png");
				}			
				candidateDetailsVo.setPartyName(parms[8].toString());
				candidateDetailsVo.setConstituencyId(new Long(parms[9].toString()));
				candidateDetailsVo.setConstituencyName(parms[10].toString());
				candidateDetailsVo.setElectionYear(parms[11].toString());
				candidateDetailsVo.setElectionType(parms[12].toString());	
				candidateDetailsVo.setMoreDetails("view more details");
				if(winner.containsKey(constituencyId)){
					differenceVotes = winner.get(constituencyId)-Float.parseFloat(parms[2].toString());
					if(winner.get(constituencyId)!=0){
						votesPercentage =  differenceVotes/winner.get(constituencyId)*100;
					}else{
						votesPercentage = 0f;
					}	
					candidateDetailsVo.setVotesDifference(Float.parseFloat(differenceVotes.toString()));
					candidateDetailsVo.setMarginVotesPercentage(new BigDecimal(votesPercentage.floatValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
				}else{
					differenceVotes = 0f;
					votesPercentage = 0f;
					candidateDetailsVo.setVotesDifference(differenceVotes);
					candidateDetailsVo.setMarginVotesPercentage(new BigDecimal(votesPercentage).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if((electionType.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE)) || (electionType.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE))){
					candidateDetailsVo.setLocalBodyElectionsConstituencyName(parms[10].toString());
				}
				rank = Long.parseLong(parms[4].toString());
				if(rank!=1l){
					candidateDetails.add(candidateDetailsVo);
				//	System.out.println(candidateDetailsVo.getCandidateName()+"\t\t"+parms[4]+"\t\t"+candidateDetailsVo.getPartyName()+"\t"+candidateDetailsVo.getConstituencyName()+"\t"+candidateDetailsVo.getVotesEarned()+"\t"+Float.parseFloat(parms[2].toString())+"\t"+candidateDetailsVo.getVotesDifference()+"\t"+candidateDetailsVo.getVotesPercentage());
				}else if(flag==1){
					if(new Long(parms[4].toString())!=1l){
						candidateDetails.add(candidateDetailsVo);
					}
				}					
			}
			return candidateDetails;	
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}		
	}
	
	
	/**
	 * This method returns a StringBuilder object that constits of all parliament assemblies that are present in a district for a particular election year.
	 * 
	 * @param districtId
	 * @param stateId
	 * @param electionYear
	 * @return
	 */
	public StringBuilder getParliamentConstituenciesForDistrict(Long districtId,Long stateId,String electionYear){
		try{
			List<Long> parliamentIds = getParliamentConstituenciesAsList(districtId,stateId,electionYear);
			
			StringBuilder listOfParliamentConstituencies  = new StringBuilder();	
			Iterator it = parliamentIds.iterator();
			while(it.hasNext()){							
				listOfParliamentConstituencies.append(",").append(new Long(it.next().toString()));
			}				
			return listOfParliamentConstituencies;	
		}catch(Exception e){
			e.printStackTrace();
			if(log.isDebugEnabled()){
				log.debug("Exception raised in setCandidateDetailsIntoVO() method of static data servicce");
			}
			return null;
		}
	}
	

	/**
	 * This method can be used to get all the parliament constituencies in a district.
	 * 
	 * @param districtId
	 * @param stateId
	 * @param electionYear
	 * @return
	 */
	
	public List<Long> getParliamentConstituenciesAsList(Long districtId,Long stateId,String electionYear){
		try{
			Set<Long> parliamentIds = new HashSet<Long>(0);
			List list = constituencyElectionDAO.findConstituencyByDistrictAndStateIds(districtId,stateId,electionYear);						
			StringBuilder listOfConstituencies  = new StringBuilder();					
			for(int i=0;i<list.size();i++){
				Object[] parms = (Object[])list.get(i);
				listOfConstituencies.append(",").append(new Long(parms[0].toString()));
			}						
			List parliamentList = delimitationConstituencyAssemblyDetailsDAO.findParliamentConstituencyForListOfAssemblyConstituency(listOfConstituencies.substring(1),new Long(electionYear));
			for(int i=0;i<parliamentList.size();i++){
				Object[] parms = (Object[])parliamentList.get(i);
				parliamentIds.add(new Long(parms[0].toString()));
			}						
			
			return new ArrayList(parliamentIds);	
		}catch(Exception e){
			e.printStackTrace();
			if(log.isDebugEnabled()){
				log.debug("Exception raised in setCandidateDetailsIntoVO() method of static data servicce");
			}
			return null;
		}		
	}
	
	
	
	/**
	 * This method retrives all the assemblies Constituencies for a particular District.
	 * 
	 * @param districtId
	 * @param stateId
	 * @param electionYear
	 * @return
	 */
	public StringBuilder getConstituenciesForDistrictByType(Long districtId,Long stateId,String electionYear,String electionType){
		try{
			StringBuilder listOfConstituencies  = new StringBuilder();	
			List list = getConstituenciesForDistrictByTypeAsList(districtId,stateId,electionYear,electionType);
			
			StringBuilder listOfParliamentConstituencies  = new StringBuilder();	
			Iterator it = list.iterator();
			while(it.hasNext()){							
				listOfConstituencies.append(",").append(new Long(it.next().toString()));
			}
			
			return listOfConstituencies;
		}catch(Exception e){
			e.printStackTrace();
			if(log.isDebugEnabled()){
				log.debug("Exception raised in setCandidateDetailsIntoVO() method of static data servicce");
			}
			return null;
		}
	}
	
	/**
	 * This method retrives all the assemblies Constituencies for a particular District.
	 * 
	 * @param districtId
	 * @param stateId
	 * @param electionYear
	 * @return
	 */
	public List<Long> getConstituenciesForDistrictByTypeAsList(Long districtId,Long stateId,String electionYear,String electionType){
		try{			
			List list = null;
			List<Long> assembliesList = new ArrayList<Long>(0);
			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE) || 
					electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE) ||
					electionType.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE) || 
					electionType.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE)){
				list = constituencyElectionDAO.findConstituencyByDistrictAndStateIds(districtId,stateId,electionYear,electionType);
			}else{
				list = constituencyElectionDAO.findConstituencyByDistrictAndStateIdsForLocalBodys(districtId,stateId,electionYear,electionType);
			}									
			for(int i=0;i<list.size();i++){
				Object[] parms = (Object[])list.get(i);
				assembliesList.add(new Long(parms[0].toString()));
			}	
			return assembliesList;
		}catch(Exception e){
			e.printStackTrace();
			if(log.isDebugEnabled()){
				log.debug("Exception raised in setCandidateDetailsIntoVO() method of static data servicce");
			}
			return null;
		}
	}
}

	
