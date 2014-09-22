package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDebateParticipantCharcsDAO;
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
	
	public void testgetPartyCandidateDetailsTopicWise()
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
		
	}
}
