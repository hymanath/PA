package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IDebateParticipantCharcsDAO;
import com.itgrids.partyanalyst.dao.IDebateParticipantDAO;
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


	public void getPartyWiseOverAllPerformance()
	{
		try {
			
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyWiseOverAllPerformance in DebateAnalysisService class", e);
		}
	}
	
	
	/**
	 * This Service is used for getting all partys participated in how many debates and having how much scaling count
	 * @author Prasad Thiragabathina
	 * Date 22-09-2014
	 * @return returnMap
	 */
	public Map<Long,DebatePartyWiseCountVO> getPartyWiseTotalDebatesAndScalesService()
	{
		Map<Long,DebatePartyWiseCountVO> returnMap = null;
		try {
			
			List<Object[]> partyDebateCount = debateParticipantCharcsDAO.getPartyWiseTotalDebatesAndScales();
			if(partyDebateCount != null && partyDebateCount.size() > 0)
			{
				returnMap = new TreeMap<Long, DebatePartyWiseCountVO>();
				fillDebatePartyWiseCountVO(partyDebateCount, returnMap, null);
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
	public Map<Long,DebatePartyWiseCountVO> getPartyWiseTotalDebatePartiCharsCountService()
	{
		Map<Long,DebatePartyWiseCountVO> returnMap = null;
		try {
			
			List<Object[]> debatePariCharsList = debateParticipantCharcsDAO.getPartyWiseDebatePartiCharsCount();
			if(debatePariCharsList != null && debatePariCharsList.size() > 0)
			{
				returnMap = new HashMap<Long, DebatePartyWiseCountVO>();
				fillDebatePartyWiseCountVO(debatePariCharsList, returnMap, "forChars");
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyWiseDebatePartiCharsCount in DebateAnalysisService class", e);
		}
		return returnMap;
	}
	
	
	/**
	 * This Service is used for filling DebatePartyWiseCountVO for general purpose
	 * @author Prasad Thiragabathina
	 * Date 22-09-2014
	 * 
	 * @param resultList
	 * @param returnMap
	 * @param type
	 */
	
	
	public void fillDebatePartyWiseCountVO(List<Object[]> resultList,Map<Long,DebatePartyWiseCountVO> returnMap,String type)
	{
		try {
			for (Object[] parms : resultList) {
				if(parms[0] != null){
				
					DebatePartyWiseCountVO debatePartyWiseCountVO = new DebatePartyWiseCountVO();
					debatePartyWiseCountVO.setPartyId((Long)parms[0]);
					debatePartyWiseCountVO.setPartyName(parms[1] != null ? parms[1].toString() : "");
					
					if(type != null){
					
						debatePartyWiseCountVO.setCharId(parms[2] != null ? (Long)parms[2]:0l);
						debatePartyWiseCountVO.setCharsName(parms[4] != null ? parms[4].toString() : "");
					}
					else{
					
						debatePartyWiseCountVO.setTotalDebates(parms[2] != null ? (Long)parms[2]:0l);
					}
					debatePartyWiseCountVO.setDebateScale(parms[3] != null ? (Double)parms[2]:0.0);
					returnMap.put((Long)parms[0], debatePartyWiseCountVO);
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in fillDebatePartyWiseCountVO in DebateAnalysisService class", e);
		}
	}
	
	public Map<Long,DebatePartyWiseCountVO> calucateEachDebateWiseRanking()
	{
		Map<Long,DebatePartyWiseCountVO> returnMap = null;
		try {
			
			List<Object[]> eachDebateCharsCountList = debateParticipantCharcsDAO.getPartyWiseEachDebateCharsCount();
			if(eachDebateCharsCountList != null && eachDebateCharsCountList.size() > 0){
			
				returnMap = new HashMap<Long, DebatePartyWiseCountVO>();
				Map<Long,DebateRankingVO> debateRankingVO = new HashMap<Long, DebateRankingVO>();
				for (Object[] parms : eachDebateCharsCountList){
				
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in calucateEachDebateWiseRanking in DebateAnalysisService class", e);
		}
		return returnMap;
	}
	
	
	/**
	 * This Service is used for building each topic wise candidate and party performance
	 * @author Prasad Thuragabathina
	 * Date 22-09-2014
	 * @return returnList
	 */
	public List<DebateTopicVO> getPartyCandidatePerfortmanceTopicWise()
	{
		List<DebateTopicVO> returnList = null;
		try {
			Map<Long,List<DebateTopicVO>> subjectWiseMap = new HashMap<Long, List<DebateTopicVO>>();
			
			List<Object[]> result =   debateParticipantCharcsDAO.getPartyCandidateDetailsTopicWise();
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
				List<Long> partyIds = new ArrayList<Long>();
				
				partyIds.add(872l);
				partyIds.add(362l);
				partyIds.add(886l);
				partyIds.add(1117l);
				if(subjectWiseMap != null && subjectWiseMap.size() > 0)
				{
					returnList = new ArrayList<DebateTopicVO>();
					fillVoForTopicWiseEachCandidateAndPartyResult( subjectWiseMap,partyIds, returnList);
				}
			}

			
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyCandidatePerfortmanceTopicWise in DebateAnalysisService class", e);
		}
		return returnList;
	}
	
	/**
	 * This Service is used for fill vo for each topic wise candidate and party performance
	 * @author Prasad Thuragabathina
	 * Date 22-09-2014
	 * @param subjectWiseMap
	 * @param partyIds
	 * @param returnList
	 */
	public void fillVoForTopicWiseEachCandidateAndPartyResult(Map<Long,List<DebateTopicVO>> subjectWiseMap,List<Long> partyIds,List<DebateTopicVO> returnList)
	{
		try {
			for(Long subjectId : subjectWiseMap.keySet())
			{
				DebateTopicVO mainVO = new DebateTopicVO();
				mainVO.setSubjectId(subjectId);
				
				List<DebateTopicVO> subjectWiseList = subjectWiseMap.get(subjectId);
				if(subjectWiseList != null && subjectWiseList.size() > 0)
				{
					mainVO.setSubject(subjectWiseList.get(0).getSubject());
					List<DebateTopicVO> subList = new ArrayList<DebateTopicVO>();
						for (Long partyId : partyIds) 
						{
							DebateTopicVO subVO = new DebateTopicVO();
							for (DebateTopicVO debateTopicVO : subjectWiseList) 
							{
								if(partyId.longValue() == debateTopicVO.getPartyId())
								{
									subVO.setPartyId(partyId);
									subVO.setParty(debateTopicVO.getParty());
									subVO.setCandidateId(debateTopicVO.getCandidateId());
									subVO.setCandidate(debateTopicVO.getCandidate());
									subVO.setCountScale(debateTopicVO.getCountScale());
								}
							}
							subList.add(subVO);
						}
						mainVO.setSubList(subList);
					
				}
				returnList.add(mainVO);
			}
		} catch (Exception e) {
			LOG.error("Exception raised in fillVoForTopicWiseEachCandidateAndPartyResult in DebateAnalysisService class", e);
		}
	}
	
}
