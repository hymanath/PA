package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dto.SurveyCompletionDetailsVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.model.SurveyCompletedLocationsDetails;
import com.itgrids.partyanalyst.service.ISurveyDashBoardService;
import com.itgrids.partyanalyst.utils.IConstants;

public class SurveyDashBoardService implements ISurveyDashBoardService {
	
	private static final Logger LOG = Logger.getLogger(SurveyDashBoardService.class);
	
	@Autowired
	private ISurveyCompletedLocationsDetailsDAO surveyCompletedLocationsDetailsDAO;
	
	@Autowired
	private IConstituencyDAO constituencyDAO;
	
	@Autowired
	private ISurveyDetailsInfoDAO surveyDetailsInfoDAO;
	
	@Autowired
	private IDistrictDAO districtDAO;
	
	@Autowired
	private IBoothDAO boothDAO;
	
	@Autowired
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	

	public SurveyDashBoardVO getCompletdConstituenciesDetails()
	{
		LOG.info("Entered into the getCompletdConstituenciesDetails service method");
		//List<SurveyDashBoardVO> resultList = new ArrayList<SurveyDashBoardVO>();
		
	    SurveyDashBoardVO resultVO = new SurveyDashBoardVO();
		List<Long> completedConstituenciesIds = new ArrayList<Long>();

		
		try
		{
		/*	List<SurveyDashBoardVO> tempList = new ArrayList<SurveyDashBoardVO>();
			
			 List<Object[]> districtDetails =   districtDAO.getDistrictIdAndNameByState(1L);
			 
			 
			 */

			
			
			
			List<Object[]> completedConstituenciesDtls = surveyCompletedLocationsDetailsDAO
					.getSurveyCompletedLocationDetails(IConstants.CONSTITUENCY_SCOPE_ID);
			
			if(completedConstituenciesDtls != null && completedConstituenciesDtls.size() >0)
			{
			
					List<SurveyDashBoardVO> list = new ArrayList<SurveyDashBoardVO>();
					for(Object[] obj:completedConstituenciesDtls)
					{
						SurveyDashBoardVO constituencyVO = getMatchedLocationVO(list, (Long)obj[0]);
						
						if(constituencyVO != null)
						{
							if(((Long)obj[1]).equals(IConstants.DATA_COLLECTOR_ROLE_ID))
								constituencyVO.setDataCollectorCompleted(true);
							else if(((Long)obj[1]).equals(IConstants.VERIFIER_ROLE_ID))
								constituencyVO.setVerifierCompleted(true);
							
						}else
						{
							 constituencyVO  = new SurveyDashBoardVO();
							 constituencyVO.setLocationId((Long)obj[0]);
							 
							 if((obj[1] != null ? obj[1].toString():"0").equals(IConstants.DATA_COLLECTOR_ROLE_ID))
									constituencyVO.setDataCollectorCompleted(true);
								else if((obj[1] != null ? obj[1].toString():"0").equals(IConstants.VERIFIER_ROLE_ID))
									constituencyVO.setVerifierCompleted(true);
							 list.add(constituencyVO);
						}
						
					}
					
					
					for(SurveyDashBoardVO completedConstituency:list)
					{
						if(completedConstituency.isDataCollectorCompleted() && completedConstituency.isVerifierCompleted())
						{
							completedConstituenciesIds.add(completedConstituency.getLocationId());
						}
					}
			
			}
		    List<Object[]> constituencyDetails = constituencyDAO.getAllAssemblyConstituenciesByStateId(1L);
		    
		    
		    Map<Long,List<Long>> constnCountMap = new HashMap<Long, List<Long>>();
		    
		    for(Object[] obj:constituencyDetails)
		    {
		    	if(constnCountMap.get((Long)obj[2]) != null)
		    	{
		    		constnCountMap.get((Long)obj[2]).add((Long)obj[0]);
		    	}else
		    	{
		    		List<Long> constituencyIds = new ArrayList<Long>();
		    		constituencyIds.add((Long)obj[0]);
		    		
		    		constnCountMap.put((Long)obj[2], constituencyIds);
		    	}
		    }
		    
		    Map<Long,List<Long>> completedConstnCountMap = new HashMap<Long, List<Long>>();

		    if(completedConstituenciesIds != null && completedConstituenciesIds.size() >0)
		    {
		    
			    List<Object[]> completdConstituencyDetails = constituencyDAO.getConstituencyInfoByConstituencyIdList(completedConstituenciesIds);
			    
			    
			    for(Object[] obj:completdConstituencyDetails)
			    {
			    	if(completedConstnCountMap.get((Long)obj[2]) != null)
			    	{
			    		completedConstnCountMap.get((Long)obj[2]).add((Long)obj[0]);
			    	}else
			    	{
			    		List<Long> constituencyIds = new ArrayList<Long>();
			    		constituencyIds.add((Long)obj[0]);
			    		
			    		completedConstnCountMap.put((Long)obj[2], constituencyIds);
			    	}
			    }
		    }
		    
		 List<Object[]> districtDetails =   districtDAO.getDistrictIdAndNameByState(1L);
		 
		 Map<Long,String> districtMap = new HashMap<Long, String>();
		 
		 for(Object[] obj:districtDetails)
			 districtMap.put((Long)obj[0], obj[1].toString());
		    
		    for(Entry<Long, List<Long>> entry:constnCountMap.entrySet())
		    {
		    	SurveyDashBoardVO dashBoardVO =  new SurveyDashBoardVO(); 
		    	dashBoardVO.setLocationId(entry.getKey());
		    	dashBoardVO.setLocationName(districtMap.get(entry.getKey()));
		    	
		    	if(completedConstnCountMap.get(entry.getKey()) == null)
		    	{
		    		//resultVO.getNotStarted().add(dashBoardVO);
		    	}
		    	else if(completedConstnCountMap.get(entry.getKey()).size() == entry.getValue().size())
		    	{
		    		//dashBoardVO.setLocationId(entry.getKey());
		    		resultVO.getCompleted().add(dashBoardVO);
		    		resultVO.getStarted().add(dashBoardVO);
		    	}
		    	else if(completedConstnCountMap.get(entry.getKey()).size() < entry.getValue().size())
		    	{
		    		//dashBoardVO.setLocationId(entry.getKey());
		    		resultVO.getProcess().add(dashBoardVO);
		    		resultVO.getStarted().add(dashBoardVO);
		    	}
		    }
		    

		    
			List<Long> startedConstituenciesCount = surveyDetailsInfoDAO
					.getSurveyStartedConstituenciesDetails();	
			
			
			
		  //  List<Object[]> startedConstituencyDetails = constituencyDAO.getConstituencyInfoByConstituencyIdList(startedConstituenciesCount);
		    
		    
		    Map<Long,List<Long>> startedConstnCountMap = new HashMap<Long, List<Long>>();

		    if(startedConstituenciesCount != null && startedConstituenciesCount.size() >0)
		    {
		    
			    List<Object[]> startedConstituencyDetails = constituencyDAO.getConstituencyInfoByConstituencyIdList(startedConstituenciesCount);
			    
			    
			    for(Object[] obj:startedConstituencyDetails)
			    {
			    	if(startedConstnCountMap.get((Long)obj[2]) != null)
			    	{
			    		startedConstnCountMap.get((Long)obj[2]).add((Long)obj[0]);
			    	}else
			    	{
			    		List<Long> constituencyIds = new ArrayList<Long>();
			    		constituencyIds.add((Long)obj[0]);
			    		
			    		startedConstnCountMap.put((Long)obj[2], constituencyIds);
			    	}
			    }
		    }
		    
		    
		    for(Entry<Long, List<Long>> entry:constnCountMap.entrySet())
		    {
		    	SurveyDashBoardVO dashBoardVO =  new SurveyDashBoardVO(); 
		    	dashBoardVO.setLocationId(entry.getKey());
		    	dashBoardVO.setLocationName(districtMap.get(entry.getKey()));
		    	
		    	if(startedConstnCountMap.get(entry.getKey()) == null)
		    	{
		    		resultVO.getNotStarted().add(dashBoardVO);
		    	}
		    	/*else if(startedConstnCountMap.get(entry.getKey()).size() == entry.getValue().size())
		    	{
		    		//dashBoardVO.setLocationId(entry.getKey());
		    		resultVO.getCompleted().add(dashBoardVO);
		    		resultVO.getStarted().add(dashBoardVO);
		    	}*/
		    	else//if(startedConstnCountMap.get(entry.getKey()).size() < entry.getValue().size())
		    	{
		    		//dashBoardVO.setLocationId(entry.getKey());
		    		SurveyDashBoardVO processdVO = matchedStartedVO(resultVO.getProcess(),entry.getKey());
		    		SurveyDashBoardVO completedVO = matchedStartedVO(resultVO.getCompleted(),entry.getKey());
		    		if(processdVO == null && completedVO == null)
		    		{
			    		resultVO.getProcess().add(dashBoardVO);
			    		resultVO.getStarted().add(dashBoardVO);
		    		}
		    	}
		    }
		    
			
			int cStartedCount = startedConstituenciesCount.size();
			int cProcessCount = startedConstituenciesCount.size() - completedConstituenciesIds.size();
			int cCompletdCount = completedConstituenciesIds.size();
			int cTotalCount = constituencyDetails.size();
			int cNotStartedCount = cTotalCount - cStartedCount;
			
			
			resultVO.setCompletedCount(cCompletdCount);
			resultVO.setProcessingCount(cProcessCount);
			resultVO.setStartedCount(cStartedCount);
            resultVO.setTotalCount(cTotalCount);
            resultVO.setNotStartedCount(cNotStartedCount);
            
            
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getCompletdConstituenciesDetails service method");
		}
		
		return resultVO;
	}
	
	
	public SurveyDashBoardVO matchedStartedVO(List<SurveyDashBoardVO> resultList , Long locationId)
	{

		for(SurveyDashBoardVO boothVO:resultList)
			if(boothVO.getLocationId().equals(locationId))
				return boothVO;
		return null;
	
		
	}
	public String saveSurveyCompletionDetails(SurveyCompletionDetailsVO completionDetailsVO)
	{
		try
		{
			List<Object[]> locationDtls = null;
			List<Long> locationValues = new ArrayList<Long>();

			Long locationScopeId = completionDetailsVO.getLocationScopeId();

			
			if(locationScopeId.equals(IConstants.BOOTH_SCOPE_ID))
			{
				locationDtls = boothDAO
					.getAllTheBoothsDetailsByConstituencyId(
							completionDetailsVO.getLocationValue(),
							IConstants.VOTER_DATA_PUBLICATION_ID);
			
			
				for(Object[] obj:locationDtls)
					locationValues.add((Long)obj[0]);
				
				surveyCompletedLocationsDetailsDAO.deleteBoothCompletionDataOfContituency(locationValues);

			}else
			{
				
				surveyCompletedLocationsDetailsDAO.deleteConstituencyCompletionData();

			}
			
			
			
			
			for(Long collectorBoothId:completionDetailsVO.getCollectorCompletedList())
			{
			   SurveyCompletedLocationsDetails surveyCompletedLocationsDetails = new SurveyCompletedLocationsDetails();
				
				surveyCompletedLocationsDetails.setLocationScopeId(locationScopeId);
				surveyCompletedLocationsDetails.setLocationValue(collectorBoothId);
				surveyCompletedLocationsDetails.setSurveyUserTypeId(IConstants.DATA_COLLECTOR_ROLE_ID);
				surveyCompletedLocationsDetailsDAO.save(surveyCompletedLocationsDetails);

			}
			
			for(Long verifierBoothId:completionDetailsVO.getVerifierCompletedList())
			{
			   SurveyCompletedLocationsDetails surveyCompletedLocationsDetails = new SurveyCompletedLocationsDetails();
				
				surveyCompletedLocationsDetails.setLocationScopeId(locationScopeId);
				surveyCompletedLocationsDetails.setLocationValue(verifierBoothId);
				surveyCompletedLocationsDetails.setSurveyUserTypeId(IConstants.VERIFIER_ROLE_ID);
				
				surveyCompletedLocationsDetailsDAO.save(surveyCompletedLocationsDetails);

			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "success";
	}
	
	public List<SurveyCompletionDetailsVO> getSurveyCompletionDetailsForAllConstituencies()
	{
		LOG.info("Entered into the getSurveyCompletionDetails service mthod");

		List<SurveyCompletionDetailsVO> resultList = new ArrayList<SurveyCompletionDetailsVO>();
		try
		{
			List<Object[]> constnList = constituencyDAO.getAllAssemblyConstituenciesByStateId(1L);
			
			for(Object[] obj:constnList)
			{
				SurveyCompletionDetailsVO completionDtls = new SurveyCompletionDetailsVO();
				
				completionDtls.setLocationValue((Long)obj[0]);
				completionDtls.setLocationName(obj[1].toString());
				
				resultList.add(completionDtls);
			}
			
			List<Object[]> surveyCompletionDtls = surveyCompletedLocationsDetailsDAO
					.getSurveyCompletedConstituencyDetails();
			
			for(Object[] obj:surveyCompletionDtls)
			{
				SurveyCompletionDetailsVO constituencyVO = getMatchedLocationSurveyCompletionVO(resultList,(Long)obj[0]);
				
				if(((Long)obj[1]).equals(IConstants.DATA_COLLECTOR_ROLE_ID))
					constituencyVO.setDataCollectorCompleted(true);
				else if(((Long)obj[1]).equals(IConstants.VERIFIER_ROLE_ID))
					constituencyVO.setVerifierCompleted(true);
					
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getSurveyCompletionDetails service method");
		}
		
		return resultList;
	}
	
	public List<SurveyCompletionDetailsVO> getSurveyCompletionDetailsOfConstituency(Long constituencyId)
	{
		LOG.info("Entered into the getSurveyCompletionDetailsOfConstituency service mthod");
		List<SurveyCompletionDetailsVO> resultList = new ArrayList<SurveyCompletionDetailsVO>();

		try
		{
			List<Object[]> boothDtls = boothPublicationVoterDAO.getLatestBoothDetailsOfConstituency(constituencyId);
			
			List<Long> boothIds = new ArrayList<Long>();
			
			for(Object[] obj:boothDtls)
			{
				SurveyCompletionDetailsVO boothDetails = new SurveyCompletionDetailsVO();
				
				boothDetails.setLocationValue((Long)obj[0]);
				boothDetails.setLocationName(obj[1].toString());
				boothIds.add((Long)obj[0]);
				
				resultList.add(boothDetails);
			}
			
			List<Object[]> completedBoothsDtls = surveyCompletedLocationsDetailsDAO.getSurveyCompletedBoothsDetails(boothIds);
			
			for(Object[] obj:completedBoothsDtls)
			{
				SurveyCompletionDetailsVO boothVO = getMatchedLocationSurveyCompletionVO(resultList, (Long)obj[0]);
				
				if(((Long)obj[1]).equals(IConstants.DATA_COLLECTOR_ROLE_ID))
					boothVO.setDataCollectorCompleted(true);
				else if(((Long)obj[1]).equals(IConstants.VERIFIER_ROLE_ID))
					boothVO.setVerifierCompleted(true);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getSurveyCompletionDetailsOfConstituency service method");
		}
		
		return resultList;
	}
	
	public List<SurveyDashBoardVO> getConstituencyWiseCompletionReport()
	{
		LOG.info("Entered into the getConstituencyWiseCompletionReport service mthod");
		List<SurveyDashBoardVO> resultList = new ArrayList<SurveyDashBoardVO>();
		try
		{
			
			//setting all constituency details start
			List<Object[]> constnDtls = constituencyDAO.getAllAssemblyConstituenciesByStateId(1L);
			
			for(Object[] obj:constnDtls)
			{
				SurveyDashBoardVO constituency = new SurveyDashBoardVO();
				
				constituency.setLocationId((Long)obj[0]);
				constituency.setLocationName(obj[1].toString());
				
				resultList.add(constituency);
			}
			//setting all constituency details end
			
			
			// complete booths details start
			List<Object[]> completeBoothsDetails = surveyCompletedLocationsDetailsDAO
					.getSurveyCompletedBoothsDetails(null);
			Set<Long> boothIds = new HashSet<Long>();
			
			if(completeBoothsDetails != null && completeBoothsDetails.size() >0)
			{
			
					List<SurveyDashBoardVO> boothsList = new ArrayList<SurveyDashBoardVO>();
					
					
					for(Object[] obj:completeBoothsDetails)
						boothIds.add((Long)obj[0]);
					
					List<Object[]> boothsDtls = boothDAO.getBoothDetailsByBoothIds(boothIds);
					
					Map<Long,Long> boothConstituencyMap = new HashMap<Long, Long>();
					
					for(Object[] obj:boothsDtls)
						boothConstituencyMap.put((Long)obj[0], (Long)obj[1]);
					
					for(Long boothId:boothIds)
					{
						SurveyDashBoardVO booth = new SurveyDashBoardVO();
						booth.setBoothId(boothId);
						booth.setConstituencyId(boothConstituencyMap.get(boothId));
						boothsList.add(booth);
					}
					
					for(Object[] obj:completeBoothsDetails)
					{
						SurveyDashBoardVO boothVO = getMatchedBoothVO(boothsList,(Long)obj[0]);
						
						if(((Long)obj[1]).equals(IConstants.DATA_COLLECTOR_ROLE_ID))
							boothVO.setDataCollectorCompleted(true);
						else if(((Long)obj[1]).equals(IConstants.VERIFIER_ROLE_ID))
							boothVO.setVerifierCompleted(true);
					}
					
					for(SurveyDashBoardVO completedBooth:boothsList)
					{
						if(completedBooth.isDataCollectorCompleted() && completedBooth.isVerifierCompleted())
						{
							SurveyDashBoardVO constituencyVO = getMatchedLocationVO(resultList,completedBooth.getConstituencyId());
							constituencyVO.setCompletedCount(constituencyVO.getCompletedCount() +1);
						}
					}
			}
			//  complete booths details end

			
			// total booths details start
			List<Object[]> totalBoothCountDetails = boothDAO.getConstituencyWiseBoothCount();
			Map<Long,Long> totalBoothCountMap = new HashMap<Long, Long>();
			
			for(Object[] obj:totalBoothCountDetails)
				totalBoothCountMap.put((Long)obj[1], (Long)obj[0]);
			
			//total booths details end
			
			List<Object[]> surveyBooths = 	surveyDetailsInfoDAO.getSurveyBooths();
			
			Set<Long> surveyBoothIds = new HashSet<Long>();
			
			for(Object[] obj:surveyBooths)
				surveyBoothIds.add((Long)obj[0]);
		
			surveyBoothIds.removeAll(boothIds);
			
			// processing booths start
			
			if(surveyBoothIds  != null && surveyBoothIds.size() >0)
			{
			
				List<Object[]> processedBoothDtls = boothDAO.getBoothDetailsByBoothIds(surveyBoothIds);
				
				Map<Long,Long> processedBoothsCount = new HashMap<Long, Long>();
				
				for(Object[] obj:processedBoothDtls)
				{
					if(processedBoothsCount.get((Long)obj[1]) != null)
						processedBoothsCount.put((Long)obj[1], processedBoothsCount.get((Long)obj[1])+1);
					else
						processedBoothsCount.put((Long)obj[1], 1L);
				}
				
				//processing booths end
				
				
				
				for(SurveyDashBoardVO constituency:resultList)
				{
					constituency.setTotalCount(totalBoothCountMap.get(constituency.getLocationId()) != null ?totalBoothCountMap.get(constituency.getLocationId()).intValue():0);
					constituency.setProcessingCount(processedBoothsCount.get(constituency.getLocationId()) !=null ?processedBoothsCount.get(constituency.getLocationId()).intValue():0);
					constituency.setStartedCount(constituency.getCompletedCount() + constituency.getProcessingCount());
					constituency.setNotStartedCount(constituency.getTotalCount() - constituency.getStartedCount());
				}

			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getConstituencyWiseCompletionReport service method");
		}
		
		return resultList;
	}
	
	
	private SurveyDashBoardVO getMatchedLocationVO(List<SurveyDashBoardVO> resultList,Long locationId)
	{

		for(SurveyDashBoardVO boothVO:resultList)
			if(boothVO.getLocationId().equals(locationId))
				return boothVO;
		return null;
	
		
	}
	
	private SurveyDashBoardVO getMatchedBoothVO(List<SurveyDashBoardVO> resultList,Long boothId)
	{

		for(SurveyDashBoardVO boothVO:resultList)
			if(boothVO.getBoothId().equals(boothId))
				return boothVO;
		return null;
	
		
	}
	
	private SurveyCompletionDetailsVO getMatchedLocationSurveyCompletionVO(List<SurveyCompletionDetailsVO> resultList,Long locationValue)
	{

		for(SurveyCompletionDetailsVO location:resultList)
			if(location.getLocationValue().equals(locationValue))
				return location;
		return null;
	
		
	}
	

	

}
