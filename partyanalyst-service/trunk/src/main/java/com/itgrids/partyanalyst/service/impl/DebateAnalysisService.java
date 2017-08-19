package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IDebateParticipantCharcsDAO;
import com.itgrids.partyanalyst.dao.IDebateParticipantDAO;
import com.itgrids.partyanalyst.dto.DebateAnalysisVO;
import com.itgrids.partyanalyst.dto.DebateCandidateCharcVO;
import com.itgrids.partyanalyst.dto.DebatePartyWiseCountVO;
import com.itgrids.partyanalyst.dto.DebateRankingVO;
import com.itgrids.partyanalyst.dto.DebateTopicVO;
import com.itgrids.partyanalyst.service.IDebateAnalysisService;

public class DebateAnalysisService implements IDebateAnalysisService
{
	private static final Logger LOG = Logger.getLogger(DebateAnalysisService.class);
	

	private IDebateParticipantDAO debateParticipantDAO ;
	

	private IDebateParticipantCharcsDAO debateParticipantCharcsDAO;
	
	
	
	
	public IDebateParticipantDAO getDebateParticipantDAO() {
		return debateParticipantDAO;
	}


	public void setDebateParticipantDAO(IDebateParticipantDAO debateParticipantDAO) {
		this.debateParticipantDAO = debateParticipantDAO;
	}


	public IDebateParticipantCharcsDAO getDebateParticipantCharcsDAO() {
		return debateParticipantCharcsDAO;
	}


	public void setDebateParticipantCharcsDAO(
			IDebateParticipantCharcsDAO debateParticipantCharcsDAO) {
		this.debateParticipantCharcsDAO = debateParticipantCharcsDAO;
	}


	/**
	 * This Serice is used for party wise over all performance
	 * @author Prasad Thiragabathina
	 * Date 23-09-2014
	 * @return returnList
	 */
	public List<DebatePartyWiseCountVO> getPartyWiseOverAllPerformance(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIdsList,  List<Long> candidatesIds,Long stateId)
	{
		List<DebatePartyWiseCountVO> returnList = null;
		try {
			Map<Long,DebatePartyWiseCountVO> partyWiseTotalDebatesMap = getPartyWiseTotalDebatesAndScalesService(fromDate,toDate,channelIds,partyIdsList,candidatesIds,stateId);
			Map<Long,DebatePartyWiseCountVO> partyWiseTotalDebatesCharsMap = getPartyWiseTotalDebatePartiCharsCountService(partyWiseTotalDebatesMap,fromDate,toDate,channelIds,partyIdsList,candidatesIds,stateId);
			Map<Long,DebateRankingVO> rankingMap = calucateEachDebateWiseRanking(fromDate,toDate,channelIds,partyIdsList,candidatesIds,stateId);
			if(partyWiseTotalDebatesMap != null && partyWiseTotalDebatesMap.size() > 0 && partyWiseTotalDebatesCharsMap != null && partyWiseTotalDebatesCharsMap.size() > 0)
			{
				returnList = new ArrayList<DebatePartyWiseCountVO>();
				for(Long partyId : partyWiseTotalDebatesMap.keySet())
				{
					DebatePartyWiseCountVO partyWiseTotalDebatesCharsVO = partyWiseTotalDebatesCharsMap.get(partyId);
					DebatePartyWiseCountVO partyWiseTotalDebatesVO = partyWiseTotalDebatesMap.get(partyId);
					partyWiseTotalDebatesCharsVO.setTotalDebates(partyWiseTotalDebatesVO.getTotalDebates());
					partyWiseTotalDebatesCharsVO.setDebateScale(Double.parseDouble(new BigDecimal((partyWiseTotalDebatesVO.getDebateScale())/partyWiseTotalDebatesCharsVO.getTotalDebates()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
					partyWiseTotalDebatesCharsVO.setRankingVO(rankingMap.get(partyId));
					returnList.add(partyWiseTotalDebatesCharsVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyWiseOverAllPerformance in DebateAnalysisService class", e);
		}
		return returnList;
	}
	
	
	/**
	 * This Service is used for getting all partys participated in how many debates and having how much scaling count
	 * @author Prasad Thiragabathina
	 * Date 22-09-2014
	 * @return returnMap
	 */
	public Map<Long,DebatePartyWiseCountVO> getPartyWiseTotalDebatesAndScalesService(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIdsList,  List<Long> candidatesIds,Long stateId)
	{
		Map<Long,DebatePartyWiseCountVO> returnMap = null;
		try {
			
			List<Object[]> partyDebateCount = debateParticipantCharcsDAO.getPartyWiseTotalDebatesAndScalForSelection(fromDate,toDate,channelIds, partyIdsList, candidatesIds,stateId);
			if(partyDebateCount != null && partyDebateCount.size() > 0)
			{
				returnMap = new TreeMap<Long, DebatePartyWiseCountVO>();
				for (Object[] parms : partyDebateCount) {
					if(parms[0] != null){
					
						DebatePartyWiseCountVO debatePartyWiseCountVO = returnMap.get((Long)parms[0]);
						if(debatePartyWiseCountVO == null)
						{
							debatePartyWiseCountVO = new DebatePartyWiseCountVO();
							returnMap.put((Long)parms[0], debatePartyWiseCountVO);
						}
						debatePartyWiseCountVO.setPartyId((Long)parms[0]);
						debatePartyWiseCountVO.setPartyName(parms[1] != null ? parms[1].toString() : "");	
						debatePartyWiseCountVO.setTotalDebates(parms[2] != null ? (Long)parms[2]:0l);
						debatePartyWiseCountVO.setDebateScale(parms[3] != null ? (Double)parms[3]:0.0);
						
						
					}
				}
			}
 		} catch (Exception e) {
			LOG.error("Exception raised in getPartyWiseTotalDebatesAndScales in DebateAnalysisService class", e);
		}
		return returnMap;
	}
	
	
	/**
	 * This Service is used for getting party wise each charastricts count for total debates
	 * @author Prasad Thiragabathina
	 * Date 22-09-2014
	 * @return returnMap
	 */
	public Map<Long,DebatePartyWiseCountVO> getPartyWiseTotalDebatePartiCharsCountService(Map<Long,DebatePartyWiseCountVO> partyWiseTotalDebatesMap,Date fromDate,Date toDate,List<Long> channelIds,List<Long>  partyIdsList,List<Long>  candidatesIds,Long stateId)
	{
		Map<Long,DebatePartyWiseCountVO> returnMap = null;
		try {
			
			List<Object[]> debatePariCharsList = debateParticipantCharcsDAO.getPartyWiseDebatePartiCharsCountsForSelection(fromDate,toDate, channelIds,  partyIdsList,candidatesIds,stateId);
			if(debatePariCharsList != null && debatePariCharsList.size() > 0)
			{
				returnMap = new HashMap<Long, DebatePartyWiseCountVO>();
				DebatePartyWiseCountVO totalDebatsVO = null;
				List<DebatePartyWiseCountVO> subList = null;
				for (Object[] objects : debatePariCharsList)
				{
					if(objects[0] != null)
					{
						DebatePartyWiseCountVO debatePartyWiseCountVO = returnMap.get((Long)objects[0]);
						if(debatePartyWiseCountVO == null)
						{
							debatePartyWiseCountVO = new DebatePartyWiseCountVO();
							returnMap.put((Long)objects[0], debatePartyWiseCountVO);
							debatePartyWiseCountVO.setPartyId((Long)objects[0]);
							debatePartyWiseCountVO.setPartyName(objects[1] != null ? objects[1].toString() : "");	
							subList = new ArrayList<DebatePartyWiseCountVO>();
							totalDebatsVO = partyWiseTotalDebatesMap.get((Long)objects[0]);
						}
						if(totalDebatsVO != null)
						{
							DebatePartyWiseCountVO subVO = new DebatePartyWiseCountVO();
							subVO.setCharId(objects[2] != null ? (Long)objects[2]:0l);
							subVO.setCharsName(objects[4] != null ? objects[4].toString() : "");
							subVO.setDebateScale(objects[3] != null ? Double.parseDouble(new BigDecimal(((Double)objects[3])/totalDebatsVO.getTotalDebates()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()):0.0);
							subList.add(subVO);
							debatePartyWiseCountVO.setSubList(subList);
						}
						
					}
					
				}
				
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyWiseDebatePartiCharsCount in DebateAnalysisService class", e);
		}
		return returnMap;
	}

	
	/**
	 * This Service is used for Ranking count for each party
	 * @author Prasad Thiragabathina
	 * Date 23-09-2014
	 * @return returnMap
	 */
	
	public Map<Long,DebateRankingVO> calucateEachDebateWiseRanking(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIdsList,  List<Long> candidatesIds,Long stateId)
	{
		Map<Long,DebateRankingVO> returnMap = null;
		try {
			
			List<Object[]> result = debateParticipantCharcsDAO.getPartyWiseEachDebateCharsCounttsForSelection(fromDate,toDate,channelIds, partyIdsList, candidatesIds,stateId);
			if(result != null && result.size() > 0)
			{
				Map<Long,DebatePartyWiseCountVO> resultMap = new HashMap<Long, DebatePartyWiseCountVO>();
				returnMap = new HashMap<Long, DebateRankingVO>();
				Long count = 0l;
				for (Object[] objects : result)
				{
					if(objects[2] != null)
					{
						DebatePartyWiseCountVO dabateVO = resultMap.get((Long)objects[2]);
						if(dabateVO == null)
						{
							count = 0l;
							dabateVO = new DebatePartyWiseCountVO();
							resultMap.put((Long)objects[2], dabateVO);		
						}
						
						count++;
						DebateRankingVO rankVO = returnMap.get((Long)objects[0]);
						if(rankVO == null)
						{
							rankVO = new DebateRankingVO();
							returnMap.put((Long)objects[0], rankVO);
							
						}
						if(count.longValue() == 1l)
						{
							rankVO.setFirstRank(returnMap.get((Long)objects[0]).getFirstRank() + 1);
						}
						else if(count.longValue() == 2l)
						{
							rankVO.setSecondRank(returnMap.get((Long)objects[0]).getSecondRank() + 1);
						}
						else if(count.longValue() == 3l)
						{
							rankVO.setThirdRank(returnMap.get((Long)objects[0]).getThirdRank() + 1);
						}
						else if(count.longValue() == 4l)
						{
							rankVO.setFourthRank(returnMap.get((Long)objects[0]).getFourthRank() + 1);
						}
							
						
					}
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in calucateEachDebateWiseRanking in DebateAnalysisService class", e);
		}
		return returnMap;
	}
	
	
	/**
	 * This Service is used for building each topic wise candidate and party performance
	 * @author Prasad Thiragabathina
	 * Date 22-09-2014
	 * @return returnList
	 */
	public List<DebateTopicVO> getPartyCandidatePerfortmanceTopicWise(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIdsList,  List<Long> candidatesIds, Long stateId )
	{
		List<DebateTopicVO> returnList = null;
		try {
			Map<Long,List<DebateTopicVO>> subjectWiseMap = new HashMap<Long, List<DebateTopicVO>>();
			
			List<Object[]> result =   debateParticipantCharcsDAO.getPartyCandidateDetailsTopicWiseForSelection(fromDate, toDate, channelIds, partyIdsList, candidatesIds,stateId);
			
			if(result != null && result.size() > 0)
			{
				for (Object[] parms : result) {
					
					List<DebateTopicVO> resultList = subjectWiseMap.get((Long)parms[3]);
					if(resultList == null)
					{
						resultList = new ArrayList<DebateTopicVO>();
						subjectWiseMap.put((Long)parms[3], resultList);
					}
					
					DebateTopicVO debateTopicVO = new DebateTopicVO();
					
					debateTopicVO.setPartyId(parms[0] != null ? (Long)parms[0] : 0l);
					debateTopicVO.setParty(parms[1] != null ? StringEscapeUtils.unescapeJava(parms[1] .toString()) : "");
					
					debateTopicVO.setCandidateId(parms[5] != null ? (Long)parms[5] : 0l);
					debateTopicVO.setCandidate(parms[6] != null ? StringEscapeUtils.unescapeJava(parms[6] .toString()) : "");
					
					debateTopicVO.setSubject(parms[4] != null ? StringEscapeUtils.unescapeJava(parms[4] .toString()) : "");
					debateTopicVO.setSubjectId(parms[3] != null ? (Long)parms[3] : 0l);
					
					debateTopicVO.setCountScale(parms[7] != null ? (Double)parms[7] : 0l);
					
					resultList.add(debateTopicVO);
				}
				
				List<Object[]> partiesList = null;
				
				//if(partyIdsList != null && partyIdsList.size()>0)
					partiesList = debateParticipantDAO.getDistinctDebatePartiesForSelection(fromDate, toDate,partyIdsList,stateId);					
				//else
				//	partiesList = debateParticipantDAO.getDistinctDebateParties();
				
				if(subjectWiseMap != null && subjectWiseMap.size() > 0)
				{
					returnList = new ArrayList<DebateTopicVO>();
					fillVoForTopicWiseEachCandidateAndPartyResult( subjectWiseMap,partiesList, returnList);
				}
			}

			
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyCandidatePerfortmanceTopicWise in DebateAnalysisService class", e);
		}
		return returnList;
	}
	
	/**
	 * This Service is used for fill vo for each topic wise candidate and party performance
	 * @author Prasad Thiragabathina
	 * Date 22-09-2014
	 * @param subjectWiseMap
	 * @param partyIds
	 * @param returnList
	 */
	public void fillVoForTopicWiseEachCandidateAndPartyResult(Map<Long,List<DebateTopicVO>> subjectWiseMap,List<Object[]> partiesList,List<DebateTopicVO> returnList)
	{
		try {
			
			if(subjectWiseMap != null && subjectWiseMap.size()>0)
			{
				for(Long subjectId : subjectWiseMap.keySet())
				{
					DebateTopicVO mainVO = new DebateTopicVO();
					mainVO.setSubjectId(subjectId);
					
					List<DebateTopicVO> subjectWiseList = subjectWiseMap.get(subjectId);
					if(subjectWiseList != null && subjectWiseList.size() > 0)
					{
						mainVO.setSubject(subjectWiseList.get(0).getSubject());
						List<DebateTopicVO> subList = new ArrayList<DebateTopicVO>();
							for (Object[] partyObj : partiesList) 
							{
								if(partyObj[0] != null)
								{
									DebateTopicVO subVO = new DebateTopicVO();
									subVO.setPartyId((Long)partyObj[0]);
									subVO.setParty(partyObj[1] != null ? partyObj[1].toString() :"");
									for (DebateTopicVO debateTopicVO : subjectWiseList) 
									{
										if(subVO.getPartyId().longValue() == debateTopicVO.getPartyId())
										{
											subVO.setCandidateId(debateTopicVO.getCandidateId());
											subVO.setCandidate(debateTopicVO.getCandidate());
											subVO.setCountScale(debateTopicVO.getCountScale());
										}
									}
									subList.add(subVO);
								}
								
								
							}
							mainVO.setSubList(subList);
						
					}
					returnList.add(mainVO);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in fillVoForTopicWiseEachCandidateAndPartyResult in DebateAnalysisService class", e);
		}
	}
	
	
	public DebateCandidateCharcVO getPartyWiseCandidateCharacteristicsDetails(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIdsList,  List<Long> candidatesIds,Long stateId)
	{
		DebateCandidateCharcVO resultVO = new DebateCandidateCharcVO();
		try 
		{
		LOG.info("Enterd into getPartyWiseCandidateCharacteristicsDetails method");
		 
	 	List<Object[]> list = debateParticipantDAO.getDebateCandidateCharacteristicsDetailForSelection(fromDate,toDate,channelIds,partyIdsList,candidatesIds,stateId);
	 	
	 	List<DebateCandidateCharcVO> debateSubjectList = new ArrayList<DebateCandidateCharcVO>();
	 	//List<DebateCandidateCharcVO> debatePartiesList = null;
	 	List<Long> debateSubjectIds = new ArrayList<Long>();
		
		
	 	
			for (Object[] debateSubject : list) {
				if(!debateSubjectIds.contains((Long)debateSubject[2])){
					DebateCandidateCharcVO ds= new DebateCandidateCharcVO();
					ds.setDebateSubjectId((Long)debateSubject[2]);
					ds.setDebateSubject(StringEscapeUtils.unescapeJava(debateSubject[3].toString()));
					debateSubjectIds.add((Long)debateSubject[2]);
					ds.setPartyList(setParties(list));
					debateSubjectList.add(ds);
				}			
			}
	
	 	resultVO.setDebateSubjectList(debateSubjectList);
	 	
	 	for (Object[] params : list) {
	 			 		
	 		DebateCandidateCharcVO debateSubject = getMatchedDebateSubjectVO(debateSubjectList,(Long)params[2]);
	 		
	 		if(debateSubject != null){
	 			DebateCandidateCharcVO party = getMatchedPartyVO(debateSubject.getPartyList(),(Long)params[0]);
	 			
	 			if(party != null){
	 				if(!party.getCandidateIds().contains((Long)params[4])){ 					
		 				DebateCandidateCharcVO vo =  new DebateCandidateCharcVO();
		 				vo.setCandidateId((Long) params[4]);
		 				vo.setCandidateName(params[5].toString());
		 				party.getCandidateIds().add((Long) params[4]);
		 				party.getCandidatesList().add(vo);
	 				}
	 			} 		
	 		}
	 	}
	 	for (Object[] param : list) {	
		 		DebateCandidateCharcVO debateSubject = getMatchedDebateSubjectVO(debateSubjectList,(Long)param[2]);
		 		
		 		if(debateSubject != null){
		 			DebateCandidateCharcVO party = getMatchedPartyVO(debateSubject.getPartyList(),(Long)param[0]);
		 			
		 			if(party != null){ 	
		 				for(DebateCandidateCharcVO VO :debateSubject.getPartyList()){
		 			
			 				DebateCandidateCharcVO candidate = getMatchedCandidateVO(VO.getCandidatesList(),(Long)param[4]);
			 				//List<DebateCandidateCharcVO> characteristicsList = new ArrayList<DebateCandidateCharcVO>();
			 				if(candidate != null){ 	
				 				DebateCandidateCharcVO vo1 =  new DebateCandidateCharcVO();
				 				vo1.setCharacteristics(param[7].toString());			
				 				vo1.setScale(param[8].toString());
				 				//characteristicsList.add(vo1);
				 				party.getCharacList().add(vo1);
			 				}
			 				
		 				}
		 			}
	 			
	 			
	 		}
		}	
	 	
	 	}catch (Exception e){
			LOG.error("Error occured in getPartyWiseCandidateCharacteristicsDetails()",e);
		}
		
	 		
		return resultVO;
	 }
	 
	
	public DebateCandidateCharcVO getMatchedDebateSubjectVO(List<DebateCandidateCharcVO> debateSubjectList,Long debateSubjectId){
	
		try{
			if(debateSubjectList == null)
				return null;
			for(DebateCandidateCharcVO vo : debateSubjectList){
				if(debateSubjectId.longValue() == vo.getDebateSubjectId().longValue())
					return vo;
			}
		}
		catch (Exception e) {
			LOG.error("Error occured in getMatchedDebateSubjectVO()",e);
			
		}
		return null;
	}
	
	
	public DebateCandidateCharcVO getMatchedPartyVO(List<DebateCandidateCharcVO> partiesList,Long partyId){
	
		try{
			if(partiesList == null)
				return null;
			for(DebateCandidateCharcVO vo : partiesList){
				if(partyId.longValue() == vo.getPartyId().longValue())
					return vo;
			}
		}
		catch (Exception e) {
			LOG.error("Error occured in getMatchedPartyVO()",e);		
		}
		return null;
	}
	
	public DebateCandidateCharcVO getMatchedCandidateVO(List<DebateCandidateCharcVO> candidatesList,Long candidateId){
		
		try{
			if(candidatesList == null)
				return null;
			for(DebateCandidateCharcVO vo : candidatesList){
				if(candidateId.longValue() == vo.getCandidateId().longValue())
					return vo;
			}
		}
		catch (Exception e) {
			LOG.error("Error occured in getMatchedCandidateVO()",e);				
		}
		return null;
	}
	
	public List<DebateCandidateCharcVO> setParties(List<Object[]> list){
		
		List<Long> partyIds = new ArrayList<Long>();
		List<DebateCandidateCharcVO> debatePartiesList= new ArrayList<DebateCandidateCharcVO>();
		try {
			
			if(list != null && list.size()>0){
				for (Object[] parties : list) {
					if(!partyIds.contains((Long)parties[0])){
						DebateCandidateCharcVO party= new DebateCandidateCharcVO();
						party.setPartyId((Long)parties[0]);
						party.setPartyName(parties[1].toString());
						partyIds.add((Long)parties[0]);
						debatePartiesList.add(party);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured in setParties()",e);
		}
		
		return debatePartiesList;
	}
	
	public List<DebateAnalysisVO> partyWiseCandidatePerformance(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIdsList,  List<Long> candidatesIds,Long stateId)
	{
		Map<Long, DebateAnalysisVO> partiesAndCandidatesMap=null;
		Map<Long, Double> performanceCountMap=null;
		Map<Long, DebateAnalysisVO> charsCountMap=null;
		
		List<DebateAnalysisVO> finalResult=null;
		
		try {
			LOG.info("Entered into partyWiseCandidatePerformance method in DebateAnalysisService Class");
			
			List<Object[]> result1 = debateParticipantDAO.getPartiesAndCanidatesIdForSelection(fromDate, toDate, channelIds, partyIdsList, candidatesIds,stateId);
			if(result1 != null && result1.size() > 0)
			{
				partiesAndCandidatesMap = new HashMap<Long, DebateAnalysisVO>();
				setPartiesAndCandidatesInfo(result1,partiesAndCandidatesMap);
			}
			
			List<Object[]> result2 = debateParticipantCharcsDAO.getDebatePerformanceCountsForSelection(fromDate, toDate, channelIds, partyIdsList, candidatesIds,stateId);
			if(result2 != null && result2.size() > 0)
			{
				performanceCountMap = new HashMap<Long, Double>();
				setPerformanceCountInfo(result2,performanceCountMap);
			}
			
			List<Object[]> result3 = debateParticipantCharcsDAO.getDebatePerformanceCountCharForSelection(fromDate, toDate, channelIds, partyIdsList, candidatesIds,stateId);
			if(result3 != null && result3.size() > 0)
			{
				charsCountMap = new HashMap<Long, DebateAnalysisVO>();
				setCharsCountInfo(result3,charsCountMap);
			}	
			
			finalResult = new ArrayList<DebateAnalysisVO>();
			setPartyWiseCandidatePerformanceInfo(partiesAndCandidatesMap,performanceCountMap,charsCountMap,finalResult);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getPartiesAndCandidateIds method in DebateAnalysisService Class", e);
		}				
		
		return finalResult;
	}
	
	public void setPartiesAndCandidatesInfo(List<Object[]> result,Map<Long, DebateAnalysisVO> resultMap)
	{
		try {
			for (Object[] objects : result)
			{
				if(objects[2] != null)
				{
					DebateAnalysisVO debateAnalysisVO = resultMap.get(objects[2] );
					if(debateAnalysisVO == null)
					{
						debateAnalysisVO = new DebateAnalysisVO();
						resultMap.put((Long)objects[2], debateAnalysisVO);
					}
					debateAnalysisVO.setPartyId(objects[0] != null ? (Long)objects[0] : 0l);
					debateAnalysisVO.setPartyName(objects[1] != null ? objects[1].toString() : "");
					debateAnalysisVO.setCandidateId(objects[2] != null ? (Long)objects[2] : 0l);
					debateAnalysisVO.setCandidate(objects[3] != null ? objects[3].toString() : "");
					debateAnalysisVO.setTotalDebates(objects[4] != null ? (Long)objects[4] : 0l);
				}
				
			}
		} catch (Exception e) {
			LOG.error("Exception raised in setPartiesAndCandidatesInfo method in DebateAnalysisService Class", e);
		}
		
	}
	
	public void setPerformanceCountInfo(List<Object[]> result,Map<Long, Double> resultMap)
	{
		try {
			for (Object[] objects : result)
			{
				if(objects[0] != null && objects[1] != null)
				{
					resultMap.put((Long)objects[0], (Double)objects[1]);
					
				}			
			}
		} catch (Exception e) {
			LOG.error("Exception raised in setPerformanceCountInfo method in DebateAnalysisService Class", e);
		}
		
	}
	
	public void setCharsCountInfo(List<Object[]> result,Map<Long, DebateAnalysisVO> resultMap)
	{
		try {
			
			if(result != null && result.size()>0)
			{
				for (Object[] objects : result)
				{
					if(objects[0] != null)
					{
						DebateAnalysisVO debateAnalysisVO = resultMap.get(objects[0] );
						if(debateAnalysisVO == null)
						{
							debateAnalysisVO = new DebateAnalysisVO();
							resultMap.put((Long)objects[0], debateAnalysisVO);
						}
						if((Long)objects[1] == 1 ){
							debateAnalysisVO.setSubject(objects[3] != null ? (Double)objects[3] : 0l);
						}else if((Long)objects[1] == 2 ){
							debateAnalysisVO.setPresentation(objects[3] != null ? (Double)objects[3] : 0l);
						}else if((Long)objects[1] == 3 ){
							debateAnalysisVO.setCounterAttack(objects[3] != null ? (Double)objects[3] : 0l);
						}else if((Long)objects[1] == 4 ){
							debateAnalysisVO.setBodyLanguage(objects[3] != null ? (Double)objects[3] : 0l);
						}
						debateAnalysisVO.setCount(objects[3] != null ? (Double)objects[3] : 0.0D);
					}
					
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in setCharsCountInfo method in DebateAnalysisService Class", e);
		}
	
	}	
	
	
	public void setPartyWiseCandidatePerformanceInfo(Map<Long, DebateAnalysisVO> partiesAndCandidatesMap,Map<Long, Double> performanceCountMap,Map<Long, DebateAnalysisVO> charsCountMap,List<DebateAnalysisVO> finalResult){
		try {
			if(partiesAndCandidatesMap != null && partiesAndCandidatesMap.size()>0)
			{
				for(Long candidateId:partiesAndCandidatesMap.keySet())
				{
				    DebateAnalysisVO partiesCandidates=partiesAndCandidatesMap.get(candidateId);
			        Long totalDebates=partiesCandidates.getTotalDebates();	        
			        DebateAnalysisVO result=new DebateAnalysisVO();
			        result.setCandidateId(partiesCandidates.getCandidateId());
			        result.setPartyId(partiesCandidates.getPartyId());
			        result.setCandidate(partiesCandidates.getCandidate());
			        result.setPartyName(partiesCandidates.getPartyName());
			        result.setTotalDebates(totalDebates);
			        
			        result.setPerformanceCount(Double.parseDouble(new BigDecimal((performanceCountMap.get(candidateId))/totalDebates).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
			        
			        
			        DebateAnalysisVO charsCount=charsCountMap.get(candidateId);
			        result.setPresentation(Double.parseDouble(new BigDecimal((charsCount.getPresentation())/totalDebates).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
			        result.setBodyLanguage(Double.parseDouble(new BigDecimal((charsCount.getBodyLanguage())/totalDebates).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
			        result.setCounterAttack(Double.parseDouble(new BigDecimal((charsCount.getCounterAttack())/totalDebates).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
			        result.setSubject(Double.parseDouble(new BigDecimal((charsCount.getSubject())/totalDebates).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
			        finalResult.add(result);
			    }	
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in setPartyWiseCandidatePerformanceInfo method in DebateAnalysisService Class", e);
		}
			
	
	}	
	
	/**
	 * This Service is used for topic wise each party strong and weak details
	 * @author Prasad Thiragabathina
	 * Date 24-09-2014 
	 * @return returnList
	 */
	public List<DebateTopicVO> getPartyWiseStrongAndWeakTopicAndCandidates(Date fromDate, Date toDate, List<Long> channelIds, List<Long> partyIdsList,  List<Long> candidatesIds,Long stateId)
	{  
		List<DebateTopicVO> returnList = null;
		try {
			Map<Long,DebateTopicVO> topTopicesMap = null;
			Map<Long,List<DebateTopicVO>> topTopicesPartyWiseMap = null;
			List<Object[]> topTopicesList = debateParticipantCharcsDAO.getTopicWiseStrongOrWeakCandidatsForSelection(fromDate,toDate,channelIds,partyIdsList,candidatesIds,"desc",stateId);
			if(topTopicesList != null)
			{
				topTopicesMap = new LinkedHashMap<Long, DebateTopicVO>();
				topTopicesPartyWiseMap = new LinkedHashMap<Long, List<DebateTopicVO>>();
				getTopicWiseStrongOrWeakCandidate(topTopicesList,topTopicesMap,topTopicesPartyWiseMap);
			}
			Map<Long,DebateTopicVO> weakTopicesMap = null;
			Map<Long,List<DebateTopicVO>> weakTopicesPartyWiseMap = null;
			List<Object[]> weakTopicesList = debateParticipantCharcsDAO.getTopicWiseStrongOrWeakCandidatsForSelection(fromDate,toDate,channelIds,partyIdsList,candidatesIds,"asc",stateId);
			if(weakTopicesList != null)
			{
				weakTopicesMap = new LinkedHashMap<Long, DebateTopicVO>();
				weakTopicesPartyWiseMap = new LinkedHashMap<Long, List<DebateTopicVO>>();
				getTopicWiseStrongOrWeakCandidate(weakTopicesList,weakTopicesMap,weakTopicesPartyWiseMap);
			}
			
			List<Object[]> partiesList = null;
			
			//if(partyIdsList != null && partyIdsList.size()>0)
				partiesList = debateParticipantDAO.getDistinctDebatePartiesForSelection(fromDate, toDate,partyIdsList,stateId);					
			//else
			//	partiesList = debateParticipantDAO.getDistinctDebateParties();
			
			if(partiesList != null && partiesList.size() > 0)
			{
				returnList = new ArrayList<DebateTopicVO>();
				for (Object[] objects : partiesList)
				{
					if(objects[0] != null)
					{	
						DebateTopicVO debateTopicVO =null;
						
						if(topTopicesPartyWiseMap.get((Long)objects[0]) != null)
						{
							debateTopicVO = new DebateTopicVO();
							debateTopicVO.setPartyId((Long)objects[0]);
							debateTopicVO.setParty(objects[1].toString());
							
							List<DebateTopicVO> listForTop = new ArrayList<DebateTopicVO>();
							List<DebateTopicVO> list = topTopicesPartyWiseMap.get((Long)objects[0]);
							if(list != null && list.size() > 0)
							{
								for (DebateTopicVO debateTopicVO2 : list) 
								{
									if(topTopicesMap.get(debateTopicVO2.getSubjectId()) != null)
									debateTopicVO2.setCount(topTopicesMap.get(debateTopicVO2.getSubjectId()).getCount());
									listForTop.add(debateTopicVO2);
								}
							}
							debateTopicVO.setTop(listForTop);
							
						}
						if(weakTopicesPartyWiseMap.get((Long)objects[0]) != null)
						{
							if(debateTopicVO == null)								
							{
								debateTopicVO = new DebateTopicVO();
							}
							
							List<DebateTopicVO> listForWeak  = new ArrayList<DebateTopicVO>();
							List<DebateTopicVO> list = weakTopicesPartyWiseMap.get((Long)objects[0]);
							if(list != null && list.size() > 0)
							{
								for (DebateTopicVO debateTopicVO2 : list) 
								{
									if(weakTopicesMap.get(debateTopicVO2.getSubjectId()) != null)
									debateTopicVO2.setCount(weakTopicesMap.get(debateTopicVO2.getSubjectId()).getCount());
									listForWeak.add(debateTopicVO2);
								}
							}
							debateTopicVO.setWeak(listForWeak);
						}
						returnList.add(debateTopicVO);
					}
					
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyWiseStrongAndWeakTopicAndCandidates method in DebateAnalysisService Class", e);
		}
		return returnList;
	}
	
	/**
	 * This Service is used for calucate each tpoic wise strong and week topics list
	 * @author Prasad Thiragabathina
	 * Date 24-09-2014 
	 * @return returnList
	 */
	public void getTopicWiseStrongOrWeakCandidate(List<Object[]> topTopicesList,Map<Long,DebateTopicVO> topTopicesMap,Map<Long,List<DebateTopicVO>> topTopicesPartyWiseMap)
	{
		
		try {
			Long count = 0l;
			Double scaleCount = 0.0;
			for (Object[] objects : topTopicesList)
			{
				if(objects[2] != null)
				{
					DebateTopicVO debateTopicVO = topTopicesMap.get((Long)objects[2]);
					if(debateTopicVO == null)
					{
						debateTopicVO = new DebateTopicVO();
						topTopicesMap.put((Long)objects[2], debateTopicVO);
						debateTopicVO.setSubjectId(objects[3] != null ? (Long)objects[3]:0l);
						debateTopicVO.setSubject(objects[4] != null ? objects[4] .toString() : "");
						count = 0l;
					}
					count ++;
					List<DebateTopicVO> partyWiseVOList = topTopicesPartyWiseMap.get((Long)objects[0]);
					if(partyWiseVOList == null)
					{
						partyWiseVOList = new ArrayList<DebateTopicVO>();
						topTopicesPartyWiseMap.put((Long)objects[0], partyWiseVOList);
						scaleCount = 0.0;
					}
					DebateTopicVO partyWiseVO = new DebateTopicVO();
					if(count == 1)
					{
						partyWiseVO.setPartyId(objects[0] != null ? (Long)objects[0]:0l);
						partyWiseVO.setParty(objects[1] != null ? StringEscapeUtils.unescapeJava(objects[1] .toString()) : "");
						partyWiseVO.setSubjectId(objects[3] != null ? (Long)objects[3]:0l);
						partyWiseVO.setSubject(objects[4] != null ? StringEscapeUtils.unescapeJava(objects[4] .toString()) : "");
						partyWiseVO.setCandidateId(objects[5] != null ? (Long)objects[5]:0l);
						partyWiseVO.setCandidate(objects[6] != null ? StringEscapeUtils.unescapeJava(objects[6] .toString()) : "");
						partyWiseVO.setCountScale(objects[7] != null ? (Double)objects[7]:0l);
						partyWiseVO.setChannel(objects[8] != null ? StringEscapeUtils.unescapeJava(objects[8] .toString()) : "");
						partyWiseVO.setDebateId(objects[2] != null ? (Long)objects[2]:0l);
						scaleCount = (Double)objects[7];
						partyWiseVOList.add(partyWiseVO);
					}
					else
					{
						if((Double)objects[7] == scaleCount.doubleValue())
						{
							partyWiseVO.setPartyId(objects[0] != null ? (Long)objects[0]:0l);
							partyWiseVO.setParty(objects[1] != null ? StringEscapeUtils.unescapeJava(objects[1] .toString()) : "");
							partyWiseVO.setSubjectId(objects[3] != null ? (Long)objects[3]:0l);
							partyWiseVO.setSubject(objects[4] != null ? StringEscapeUtils.unescapeJava(objects[4] .toString()) : "");
							partyWiseVO.setCandidateId(objects[5] != null ? (Long)objects[5]:0l);
							partyWiseVO.setCandidate(objects[6] != null ? StringEscapeUtils.unescapeJava(objects[6] .toString()) : "");
							partyWiseVO.setCountScale(objects[7] != null ? (Double)objects[7]:0l);
							partyWiseVO.setChannel(objects[8] != null ? StringEscapeUtils.unescapeJava(objects[8] .toString()) : "");
							partyWiseVO.setDebateId(objects[2] != null ? (Long)objects[2]:0l);
							scaleCount = (Double)objects[7];
							partyWiseVOList.add(partyWiseVO);
							debateTopicVO.setCount(count);
							partyWiseVO.setCount(count);
						}
					}
				}
				
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getTopicWiseStringCandidate method in DebateAnalysisService Class", e);
		}
		
	}
}
