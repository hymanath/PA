package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDebateParticipantCharcsDAO;
import com.itgrids.partyanalyst.dto.DebatePartyWiseCountVO;
import com.itgrids.partyanalyst.dto.DebateRankingVO;
import com.itgrids.partyanalyst.dto.DebateTopicVO;

public class DebateParticipantCharcsDAOHibernateTest extends BaseDaoTestCase{

	private IDebateParticipantCharcsDAO debateParticipantCharcsDAO;

	public void setDebateParticipantCharcsDAO(
			IDebateParticipantCharcsDAO debateParticipantCharcsDAO) {
		this.debateParticipantCharcsDAO = debateParticipantCharcsDAO;
	}
	
	/*public void testAll()
	{
		
	}*/
	
	/*public void testgetPartyWiseTotalDebatesAndScales()
	{
		System.out.println(debateParticipantCharcsDAO.getPartyWiseTotalDebatesAndScales().size());
		
	}*/
	
	/*public void testgetPartyWiseDebatePartiCharsCount()
	{
		System.out.println(debateParticipantCharcsDAO.getPartyWiseDebatePartiCharsCount().size());
		
	}*/
	
	/*public void testgetPartyWiseEachDebateCharsCount()
	{
		System.out.println(debateParticipantCharcsDAO.getPartyWiseEachDebateCharsCount().size());
		
	}*/
	
	/*public void testgetPartyCandidateDetailsTopicWise()
	{
		List<DebateTopicVO> returnList = new ArrayList<DebateTopicVO>();
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
			}
			
			if(returnList != null && returnList.size() > 0)
			{
				StringBuffer str = new StringBuffer();
				str.append("<table>");
				str.append("<tr>");
				str.append("<th>Topic</th>");
				List<DebateTopicVO> partiesList = returnList.get(0).getSubList();
				for (DebateTopicVO debateTopicVO : partiesList)
				{
					str.append("<th colspan=2>"+debateTopicVO.getParty()+"</th>");
				}
				str.append("</tr>");
				for (DebateTopicVO debateTopicVO : returnList)
				{
					str.append("<tr>");
					str.append("<td>"+debateTopicVO.getSubject()+"</td>");
					
					for(DebateTopicVO debateTopicVO1 : debateTopicVO.getSubList())
					{
						str.append("<td>"+debateTopicVO1.getCandidate()+"</td>");
						if(debateTopicVO1.getCountScale() != null)
						{
							str.append("<td>"+debateTopicVO1.getCountScale()+"</td>");
						}
						
						else
						{
							str.append("<td>-</td>");
						}
					}
					str.append("</tr>");
				}
				
				str.append("<table>");
				
				System.out.println(str.toString());
			}
			
		}
		
	}*/
	
	/*public void testgetDebatePerformanceCount(){
		try {
			List<Object[]> values = debateParticipantCharcsDAO.getDebatePerformanceCountCharcs();
			for (Object[] objects : values) {
				System.out.println(objects[0]+"..."+objects[1] +"..."+ objects[2] + "..." + objects[3]);
			}
		} catch (Exception e) {
		}
}*/
	
	/*public void testgetPartyWiseTotalDebatesAndScales()
	{
		List<Object[]> partyDebateCount =  debateParticipantCharcsDAO.getPartyWiseTotalDebatesAndScales();
		if(partyDebateCount != null && partyDebateCount.size() > 0)
		{
			Map<Long,DebatePartyWiseCountVO> returnMap = new TreeMap<Long, DebatePartyWiseCountVO>();
			fillDebatePartyWiseCountVO(partyDebateCount, returnMap, null);
			for (Long  partyId : returnMap.keySet())
			{
				DebatePartyWiseCountVO debatePartyWiseCountVO = returnMap.get(partyId);
				System.out.println(debatePartyWiseCountVO.getPartyName() +":"+ debatePartyWiseCountVO.getTotalDebates() +":"+ debatePartyWiseCountVO.getDebateScale());
			}
			
			List<Object[]> debatePariCharsList = debateParticipantCharcsDAO.getPartyWiseDebatePartiCharsCount();
			if(debatePariCharsList != null && debatePariCharsList.size() > 0)
			{
				Map<Long,DebatePartyWiseCountVO> returnMap1 = new HashMap<Long, DebatePartyWiseCountVO>();
				if(debatePariCharsList != null && debatePariCharsList.size() > 0)
				{
					returnMap = new HashMap<Long, DebatePartyWiseCountVO>();
					List<DebatePartyWiseCountVO> subList = null;
					for (Object[] objects : debatePariCharsList)
					{
						if(objects[0] != null)
						{
							DebatePartyWiseCountVO debatePartyWiseCountVO = returnMap1.get((Long)objects[0]);
							if(debatePartyWiseCountVO == null)
							{
								debatePartyWiseCountVO = new DebatePartyWiseCountVO();
								returnMap1.put((Long)objects[0], debatePartyWiseCountVO);
								debatePartyWiseCountVO.setPartyId((Long)objects[0]);
								debatePartyWiseCountVO.setPartyName(objects[1] != null ? objects[1].toString() : "");	
								subList = new ArrayList<DebatePartyWiseCountVO>();
							}
							DebatePartyWiseCountVO subVO = new DebatePartyWiseCountVO();
							subVO.setCharId(objects[2] != null ? (Long)objects[2]:0l);
							subVO.setCharsName(objects[4] != null ? objects[4].toString() : "");
							subVO.setDebateScale(objects[3] != null ? (Double)objects[3]:0.0);
							subList.add(subVO);
							debatePartyWiseCountVO.setSubList(subList);
						}
						
					}
					
				}
				for(Long party : returnMap1.keySet())
				{
					DebatePartyWiseCountVO debatePartyWiseCountVO = returnMap1.get(party);
					System.out.println(debatePartyWiseCountVO.getPartyName() );
					List<DebatePartyWiseCountVO> list = debatePartyWiseCountVO.getSubList();
					for (DebatePartyWiseCountVO debatePartyWiseCountVO2 : list) 
					{
						System.out.println(debatePartyWiseCountVO2.getCharsName() +":"+ debatePartyWiseCountVO2.getDebateScale());
					}
				}
			}
		}
		
		
	}*/
	
	/*public void fillDebatePartyWiseCountVO(List<Object[]> resultList,Map<Long,DebatePartyWiseCountVO> returnMap,String type)
	{
		try {
			for (Object[] parms : resultList) {
				if(parms[0] != null){
				
					DebatePartyWiseCountVO debatePartyWiseCountVO = returnMap.get((Long)parms[0]);
					if(debatePartyWiseCountVO == null)
					{
						debatePartyWiseCountVO = new DebatePartyWiseCountVO();
						returnMap.put((Long)parms[0], debatePartyWiseCountVO);
					}
					 
					debatePartyWiseCountVO.setPartyId((Long)parms[0]);
					debatePartyWiseCountVO.setPartyName(parms[1] != null ? parms[1].toString() : "");
					
					if(type != null){
					
						debatePartyWiseCountVO.setCharId(parms[2] != null ? (Long)parms[2]:0l);
						debatePartyWiseCountVO.setCharsName(parms[4] != null ? parms[4].toString() : "");
					}
					else{
					
						debatePartyWiseCountVO.setTotalDebates(parms[2] != null ? (Long)parms[2]:0l);
					}
					debatePartyWiseCountVO.setDebateScale(parms[3] != null ? (Double)parms[3]:0.0);
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/*public void testGetPartyWiseEachDebateCharsCount()
	{
		List<Object[]> result = debateParticipantCharcsDAO.getPartyWiseEachDebateCharsCount();
		if(result != null && result.size() > 0)
		{
			Map<Long,DebatePartyWiseCountVO> resultMap = new HashMap<Long, DebatePartyWiseCountVO>();
			Map<Long,DebateRankingVO> partyRankCountMap = new HashMap<Long, DebateRankingVO>();
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
					DebateRankingVO rankVO = partyRankCountMap.get((Long)objects[0]);
					if(rankVO == null)
					{
						rankVO = new DebateRankingVO();
						partyRankCountMap.put((Long)objects[0], rankVO);
						
					}
					if(count.longValue() == 1l)
					{
						rankVO.setFirstRank(partyRankCountMap.get((Long)objects[0]).getFirstRank() + 1);
					}
					else if(count.longValue() == 2l)
					{
						rankVO.setSecondRank(partyRankCountMap.get((Long)objects[0]).getSecondRank() + 1);
					}
					else if(count.longValue() == 3l)
					{
						rankVO.setThirdRank(partyRankCountMap.get((Long)objects[0]).getThirdRank() + 1);
					}
					else if(count.longValue() == 4l)
					{
						rankVO.setFourthRank(partyRankCountMap.get((Long)objects[0]).getFourthRank() + 1);
					}
						
					
				}
				
			}
			
			for(Long partyId : partyRankCountMap.keySet())
			{
				DebateRankingVO rankVO = partyRankCountMap.get(partyId);
				System.out.println(partyId);
				System.out.println(rankVO.getFirstRank() +":"+ rankVO.getSecondRank() +":"+ rankVO.getThirdRank() +":"+ rankVO.getFourthRank());
			}
		}
	}*/
	
	public void testGetTopicWiseStrongOrWeakCandidates()
	{
		try {
			List<Object[]> topTopicesList = debateParticipantCharcsDAO.getTopicWiseStrongOrWeakCandidates("asc");
			if(topTopicesList != null && topTopicesList.size() > 0)
			{
				Map<Long,DebateTopicVO> topTopicesMap = new HashMap<Long, DebateTopicVO>();
				Map<Long,List<DebateTopicVO>> topTopicesPartyWiseMap = new HashMap<Long, List<DebateTopicVO>>();
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
							partyWiseVO.setParty(objects[1] != null ? objects[1] .toString() : "");
							partyWiseVO.setSubjectId(objects[3] != null ? (Long)objects[3]:0l);
							partyWiseVO.setSubject(objects[4] != null ? objects[4] .toString() : "");
							partyWiseVO.setCandidateId(objects[5] != null ? (Long)objects[5]:0l);
							partyWiseVO.setCandidate(objects[6] != null ? objects[6] .toString() : "");
							partyWiseVO.setCountScale(objects[7] != null ? (Double)objects[7]:0l);
							partyWiseVO.setChannel(objects[8] != null ? objects[8] .toString() : "");
							scaleCount = (Double)objects[7];
							partyWiseVOList.add(partyWiseVO);
						}
						else
						{
							if((Double)objects[7] == scaleCount.doubleValue())
							{
								partyWiseVO.setPartyId(objects[0] != null ? (Long)objects[0]:0l);
								partyWiseVO.setParty(objects[1] != null ? objects[1] .toString() : "");
								partyWiseVO.setSubjectId(objects[3] != null ? (Long)objects[3]:0l);
								partyWiseVO.setSubject(objects[4] != null ? objects[4] .toString() : "");
								partyWiseVO.setCandidateId(objects[5] != null ? (Long)objects[5]:0l);
								partyWiseVO.setCandidate(objects[6] != null ? objects[6] .toString() : "");
								partyWiseVO.setCountScale(objects[7] != null ? (Double)objects[7]:0l);
								partyWiseVO.setChannel(objects[8] != null ? objects[8] .toString() : "");
								scaleCount = (Double)objects[7];
								partyWiseVOList.add(partyWiseVO);
								debateTopicVO.setCount(count);
							}
						}
					}
					
				}
				
				for(Long debateId : topTopicesMap.keySet())
				{
					DebateTopicVO debateTopicVO = topTopicesMap.get(debateId);
					if(debateTopicVO.getCount() > 1)
					{
						System.out.println(debateTopicVO.getSubjectId());
					}
				}
				System.out.println("*************************************");
				
				for(Long partyId : topTopicesPartyWiseMap.keySet())
				{
					List<DebateTopicVO> partyWiseVOList = topTopicesPartyWiseMap.get(partyId);
					System.out.println(partyId);
					for (DebateTopicVO debateTopicVO : partyWiseVOList) 
					{
						System.out.println(debateTopicVO.getSubjectId() +":"+ debateTopicVO.getCandidate() +":"+ debateTopicVO.getChannel() +":"+ debateTopicVO.getCountScale());
					}
					System.out.println("______________________________________");
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
