package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.service.IAcPcWiseElectionResultService;

public class AcPcWiseElectionResultService implements IAcPcWiseElectionResultService{

	
	private final static Logger LOG = Logger.getLogger(AcPcWiseElectionResultService.class);
	
	@Autowired
	
	ICandidateResultDAO candidateResultDAO;
	
	@Autowired
	
	IDelimitationConstituencyDAO delimitationConstituencyDAO;
	
	@Autowired
	
	IPartyDAO partyDAO;
	
	public List<BasicVO> getPartyWiseComperassionResult(Long stateId,Long electionId,List<Long> partyIds,Long electionScopeId,String scope)
	{
		List<BasicVO> returnList = null;
		try 
		{
			List<Object[]> result = candidateResultDAO.getElectionResultsForSelection(electionId,stateId,partyIds,electionScopeId);
			if(result != null && result.size() > 0)
			{
				
				Map<Long,List<BasicVO>> constituencyWiseMap = new HashMap<Long, List<BasicVO>>();
				Map<Long,Long> constituencyNosMap = new HashMap<Long, Long>();
				Map<Long,String> constituencyNameMap = new HashMap<Long, String>();
				for (Object[] objects : result)
				{
					List<BasicVO> constituencyWiseList = constituencyWiseMap.get((Long)objects[0]);
					if(constituencyWiseList == null)
					{
						constituencyWiseList = new ArrayList<BasicVO>();
						constituencyWiseMap.put((Long)objects[0], constituencyWiseList);
					}
					BasicVO basicVO = new BasicVO();
					basicVO.setId((Long)objects[0]);//constituency Id
					basicVO.setName(objects[1] != null ? objects[1].toString() : "");//constituency Name
					basicVO.setMandalName(objects[2] != null ? objects[2].toString() : "");//District Name
					basicVO.setCount(objects[3] != null ?Double.valueOf(objects[3].toString()).longValue() : 0l);//gained Votes
					basicVO.setPersent(objects[4] != null ? objects[4].toString() : "0");//votes percentage
					basicVO.setLevelId((Long)objects[5]);//party Id
					basicVO.setDescription(objects[6] != null ? objects[6].toString() : "");//party Name
					basicVO.setCasteName(objects[7] != null ? objects[7].toString() : "");//candidate
					basicVO.setLevelValue((Long)objects[8]);//rank
					constituencyWiseList.add(basicVO);
				}
				List<Object[]> constituencyDetails = delimitationConstituencyDAO.getConstituencyNoByState(stateId,2009l,electionScopeId,scope);
				if(constituencyDetails != null && constituencyDetails.size() > 0)
				{
					for (Object[] objects : constituencyDetails)
					{
						constituencyNosMap.put((Long)objects[0], (Long)objects[1]);
						constituencyNameMap.put((Long)objects[0], objects[2].toString());
					}
				}
				List<Object[]> parties = partyDAO.getPartyShortNameByIds(partyIds);
				
				List<Long> constituenctyIds = new ArrayList<Long>(constituencyNosMap.keySet());
				if(constituenctyIds != null && constituenctyIds.size() > 0)
				{
					returnList = new ArrayList<BasicVO>();
					for (Long constituencyId : constituenctyIds)
					{
						BasicVO VO = new BasicVO();
						VO.setId(constituencyId);
						VO.setHamletId(constituencyNosMap.get(constituencyId));
						VO.setName(constituencyNameMap.get(constituencyId));
						List<BasicVO> constiwiseList = constituencyWiseMap.get(constituencyId);
						if(constiwiseList != null && constiwiseList.size() > 0)
						{
							List<BasicVO> partiesList = new ArrayList<BasicVO>();
							if(parties != null && parties.size() > 0)
							{
								for (Object[] objects : parties)
								{
									BasicVO partyVO = new BasicVO();
									partyVO.setId((Long)objects[0]);
									partyVO.setName(objects[1].toString());
									partyVO.setCount(0l);
									partyVO.setPerc(0.0);
									partiesList.add(partyVO);
								}
							}
							for (BasicVO subVO : constiwiseList)
							{
								VO.setMandalName(subVO.getMandalName());
								for(BasicVO partyVO : partiesList)
								{
									if(subVO.getLevelValue().longValue() == 1l)
									{
										partyVO.setLevelValue(1l);
									}
									if(partyVO.getId().longValue() == subVO.getLevelId().longValue())
									{
										partyVO.setCount(subVO.getCount());
										partyVO.setPersent(subVO.getPersent());
										partyVO.setCasteName(subVO.getCasteName());
									}
								}
							}
							Collections.sort(partiesList, new Comparator<BasicVO>() {

								public int compare(BasicVO o1, BasicVO o2) {									
									return o2.getCount().compareTo(o1.getCount());
								}
							});
							
							VO.setSelectedCasteDetails(partiesList);
						}
						returnList.add(VO);
					}
				}
						
				
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}
}

