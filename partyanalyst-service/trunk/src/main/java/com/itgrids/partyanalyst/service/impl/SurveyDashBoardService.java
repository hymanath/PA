package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.model.SurveyCompletedLocationsDetails;
import com.itgrids.partyanalyst.service.ISurveyDashBoardService;
import com.itgrids.partyanalyst.utils.IConstants;

public class SurveyDashBoardService implements ISurveyDashBoardService {
	
	private static final Logger LOG = Logger.getLogger(SurveyDashBoardService.class);
	
	@Autowired
	private ISurveyCompletedLocationsDetailsDAO surveyCompletedLocationDetailsDAO;
	
	@Autowired
	private IConstituencyDAO constituencyDAO;
	
	@Autowired
	private ISurveyDetailsInfoDAO surveyDetailsInfoDAO;
	
	@Autowired
	private IDistrictDAO districtDAO;

	public SurveyDashBoardVO getCompletdConstituenciesDetails()
	{
		LOG.info("Entered into the getCompletdConstituenciesDetails service method");
		//List<SurveyDashBoardVO> resultList = new ArrayList<SurveyDashBoardVO>();
		
	    SurveyDashBoardVO resultVO = new SurveyDashBoardVO();
		
		try
		{
			List<Long> completedConstituenciesCount = surveyCompletedLocationDetailsDAO
					.getSurveyCompletedLocationDetails(IConstants.CONSTITUENCY_SCOPE_ID);	
			
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
		    
		    List<Object[]> completdConstituencyDetails = constituencyDAO.getConstituencyInfoByConstituencyIdList(completedConstituenciesCount);
		    
		    Map<Long,List<Long>> completedConstnCountMap = new HashMap<Long, List<Long>>();
		    
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
		    		resultVO.getNotStarted().add(dashBoardVO);
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
			
			int cStartedCount = startedConstituenciesCount.size();
			int cProcessCount = startedConstituenciesCount.size() - completedConstituenciesCount.size();
			int cCompletdCount = completedConstituenciesCount.size();
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
	
	public String saveSurveyCompletionDetails(Long scopeId,Long locationValue)
	{
		LOG.info("Entered into the saveSurveyCompletionDetails service method");

		try
		{
			SurveyCompletedLocationsDetails surveyCompletedLocationsDetails = new SurveyCompletedLocationsDetails();
			
			surveyCompletedLocationsDetails.setLocationScopeId(scopeId);
			surveyCompletedLocationsDetails.setLocationValue(locationValue);
			surveyCompletedLocationsDetails.setIsCompleted("Y");
			
			surveyCompletedLocationDetailsDAO.save(surveyCompletedLocationsDetails);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in saveSurveyCompletionDetails service method");
			return null;
		}
		
		return "success";
	}

}
