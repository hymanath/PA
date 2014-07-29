package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
import com.itgrids.partyanalyst.dao.ISurveyConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserRelationDAO;
import com.itgrids.partyanalyst.dao.IWebMonitorCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.dto.SurveyCompletionDetailsVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.model.SurveyCompletedLocationsDetails;
import com.itgrids.partyanalyst.model.WebMonitorCompletedLocationsDetails;
import com.itgrids.partyanalyst.service.ISurveyDashBoardService;
import com.itgrids.partyanalyst.service.ISurveyDetailsService;
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
	@Autowired
	private ISurveyConstituencyDAO surveyConstituencyDAO;
	
	@Autowired
	private IWebMonitorCompletedLocationsDetailsDAO webMonitorCompletedLocationsDetailsDAO; 
	
	@Autowired
	private ISurveyUserRelationDAO surveyUserRelationDAO;
	
	
	@Autowired
	private ISurveyDetailsService surveyDetaisService;

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
						SurveyDashBoardVO constituencyVO = getMatchedLocationVO1(list, (Long)obj[0]);
						
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
		   // List<Object[]> constituencyDetails = constituencyDAO.getAllAssemblyConstituenciesByStateId(1L);
			List<Object[]> constituencyDetails = surveyConstituencyDAO.getSurveyConstituencies();
		    
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
           // resultVO.setNotStartedCount(cNotStartedCount);
            resultVO.setNotStartedCount(cTotalCount - (resultVO.getCompletedCount() + resultVO.getStartedCount()));
            
            
			
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
			List<Long> ids = new ArrayList<Long>();
			for(Object[] obj:constnDtls)
			{
				SurveyDashBoardVO constituency = new SurveyDashBoardVO();
				
				constituency.setLocationId((Long)obj[0]);
				constituency.setLocationName(obj[1].toString());
				if(!ids.contains((Long)obj[0]))
				ids.add((Long)obj[0]);
				resultList.add(constituency);
			}
			//setting all constituency details end
			
			
			// complete booths details start
			List<Object[]> completeBoothsDetails = surveyCompletedLocationsDetailsDAO
					.getSurveyCompletedBoothsDetails(null);
			Set<Long> boothIds = new HashSet<Long>();
			Set<Long> completedBoothIds = new HashSet<Long>();

			
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
							SurveyDashBoardVO constituencyVO = getMatchedLocationVO1(resultList,completedBooth.getConstituencyId());
							constituencyVO.setCompletedCount(constituencyVO.getCompletedCount() +1);
							completedBoothIds.add(completedBooth.getBoothId());
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
		
			//surveyBoothIds.removeAll(boothIds);
			surveyBoothIds.removeAll(completedBoothIds);
			
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
				
				Map<Long,Long> datacollectedCountMap = new HashMap<Long, Long>();
				List<Object[]> list = surveyDetailsInfoDAO.getDataCollectedCountForConstituency(ids);
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					{
						datacollectedCountMap.put((Long)params[0],(Long) params[1]);
					}
				}
				for(SurveyDashBoardVO constituency:resultList)
				{
					constituency.setTotalCount(totalBoothCountMap.get(constituency.getLocationId()) != null ?totalBoothCountMap.get(constituency.getLocationId()).intValue():0);
					constituency.setProcessingCount(processedBoothsCount.get(constituency.getLocationId()) !=null ?processedBoothsCount.get(constituency.getLocationId()).intValue():0);
					constituency.setStartedCount(constituency.getCompletedCount() + constituency.getProcessingCount());
					constituency.setNotStartedCount(constituency.getTotalCount() - constituency.getStartedCount());
					constituency.setTotalVoters(boothPublicationVoterDAO.getTotalVotersForConstituency(constituency.getLocationId()));
					constituency.setTotalCollectedCount(datacollectedCountMap.get(constituency.getLocationId()) != null ? datacollectedCountMap.get(constituency.getLocationId()) : 0);
				}

			}
			
			// total voters 
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getConstituencyWiseCompletionReport service method");
		}
		
		return resultList;
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
	
	
	public String saveBoothCompletionStatus(Long boothId,Long statusId)
	{
		LOG.info("Entered into the saveBoothCompletionStatus service method");
		
		try
		{
			if(statusId.equals(IConstants.BOOTH_PROCESS_DC_STATUS_ID))
			{
			    surveyCompletedLocationsDetailsDAO.deleteBoothCompletedLocationDetailsByBoothId(boothId,IConstants.DATA_COLLECTOR_ROLE_ID);
			    webMonitorCompletedLocationsDetailsDAO.deleteBoothCompletedLocationDetailsByBoothId(boothId);
			    
			}else if(statusId.equals(IConstants.BOOTH_COMPLETED_DC_STATUS_ID))
			{
					webMonitorCompletedLocationsDetailsDAO.deleteBoothCompletedLocationDetailsByBoothId(boothId);
				 
				 
				  	SurveyCompletedLocationsDetails surveyCompletedLocationDetails = new SurveyCompletedLocationsDetails();
				    
				    surveyCompletedLocationDetails.setLocationScopeId(IConstants.BOOTH_SCOPE_ID);
				    surveyCompletedLocationDetails.setLocationValue(boothId);
				    surveyCompletedLocationDetails.setSurveyUserTypeId(IConstants.DATA_COLLECTOR_ROLE_ID);
				    
				    surveyCompletedLocationsDetailsDAO.save(surveyCompletedLocationDetails);
				
			}else if(statusId.equals(IConstants.VERIFICATION_PROCESS_STATUS_ID))
			{
			 
			    surveyCompletedLocationsDetailsDAO.deleteBoothCompletedLocationDetailsByBoothId(boothId,IConstants.VERIFIER_ROLE_ID);
			
			}
			else if(statusId.equals(IConstants.VERIFICATION_COMPLETD_STATUS_ID))
			{
				 
			  	SurveyCompletedLocationsDetails surveyCompletedLocationDetails = new SurveyCompletedLocationsDetails();
			    
			    surveyCompletedLocationDetails.setLocationScopeId(IConstants.BOOTH_SCOPE_ID);
			    surveyCompletedLocationDetails.setLocationValue(boothId);
			    surveyCompletedLocationDetails.setSurveyUserTypeId(IConstants.VERIFIER_ROLE_ID);
			    
			    surveyCompletedLocationsDetailsDAO.save(surveyCompletedLocationDetails);
			
			}
			else
			{
			    surveyCompletedLocationsDetailsDAO.deleteBoothCompletedLocationDetailsByBoothId(boothId,IConstants.DATA_COLLECTOR_ROLE_ID);
			    
				WebMonitorCompletedLocationsDetails webMonitorCompletionDetails = new WebMonitorCompletedLocationsDetails();
			    
				webMonitorCompletionDetails.setLocationScopeId(IConstants.BOOTH_SCOPE_ID);
				webMonitorCompletionDetails.setLocationValue(boothId);
			    
			    webMonitorCompletedLocationsDetailsDAO.save(webMonitorCompletionDetails);
				
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in saveBoothCompletionStatus service method");
			return null;
		}
		return "success";
	}
	
	public List<String> getCasteCollecteddatesByConstituencyId(Long constituencyId)
	{
		List<String> surveyDates = new ArrayList<String>();
		try
		{
			 surveyDates = surveyDetailsInfoDAO.getCasteCollectedDatesByConstituencyId(constituencyId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return surveyDates;
	}
	
	
	public List<SurveyReportVO> getUsersCompleteReportByStartAndEndDates(String startDate,String endDate)
	{
		List<SurveyReportVO> resultList  = new ArrayList<SurveyReportVO>();
		
		LOG.info("Entered into the getUsersCompleteReportByStartAndEndDates service method");
		try
		{
			
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd" );
			Date startDt;
			Date endDt;
			startDt = originalFormat.parse(startDate);
			Date convertedstrdate = targetFormat.parse(targetFormat.format(startDt));			
			
			endDt = originalFormat.parse(endDate);
			Date convertedenddate = targetFormat.parse(targetFormat.format(endDt));
			
			if(!startDate.toString().equalsIgnoreCase(endDate.toString()))
				return getUsersReportDetailsForDifferentDates(convertedstrdate,convertedenddate);
			
			List<Object[]> usersDetails = surveyDetailsInfoDAO.getUsersCompleteReportForSameDate(convertedstrdate,convertedenddate);
			
			List<Object[]> startEndTimes = surveyDetailsInfoDAO.getStartAndEndTimesByUserIds(convertedstrdate,convertedenddate);
			
			
			
			List<Long> userIds = new ArrayList<Long>();
			
			for(Object[] obj:usersDetails)
				if(!userIds.contains((Long)obj[1]))
				userIds.add((Long)obj[1]);
			
			Map<Long,String> leadersMap = getLeadersDetailsByUserIds(userIds);
			
			for(Long userId:userIds)
			{
				SurveyReportVO userVO = new SurveyReportVO();
				userVO.setUserid(userId);
				if(leadersMap.get(userId) != null)
				{
					userVO.setLeaderName(leadersMap.get(userId).split("-")[0]);
					userVO.setMobileNo(leadersMap.get(userId).split("-")[1]);
				}
				resultList.add(userVO);
			}
			
			for(Object[] obj:startEndTimes)
			{
				SurveyReportVO userVO = getMatchedUserVO(resultList,(Long)obj[2]);
				
				if(userVO != null)
				{
				 userVO.setStartTime(obj[0].toString());
				 userVO.setEndTime(obj[1].toString());
				}
			}
			
			
			for(Object[] obj:usersDetails)
			{
				SurveyReportVO userVO = getMatchedUserVO(resultList,(Long)obj[1]);
				
				userVO.setName(obj[5].toString());
				
				userVO.setCount(userVO.getCount() +1);
				
				if(obj[3] != null)
					userVO.setCasteCollectedCount(userVO.getCasteCollectedCount()+1);
				
				if(obj[4] != null)
					userVO.setHamletCollectedCount(userVO.getHamletCollectedCount() +1);
				
				if(obj[2] != null && !obj[2].toString().trim().equalsIgnoreCase(""))
					userVO.setMobileNumberCollectedCount(userVO.getMobileNumberCollectedCount()+1);
				
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getUsersCompleteReportByStartAndEndDates service method");
		}
		
		return resultList;
	}
	
	public SurveyReportVO getMatchedUserVO(List<SurveyReportVO> resultList,Long userId)
	{
		
		for(SurveyReportVO user:resultList)
			if(user.getUserid().equals(userId))
				return user;
		return null;
	}
	

	public List<SurveyReportVO> getUsersReportDetailsForDifferentDates(Date startDate,Date endDate)
	{
		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		LOG.debug("Entered into the getUsersReportDetailsForDifferentDates service method ");
		try
		{
			List<Object[]> usersCountDtls = surveyDetailsInfoDAO.getUsersReportDetailsForBetweenDates(startDate,endDate);
			
			List<Long> userIds = new ArrayList<Long>();
			List<String> datesList = new ArrayList<String>();
			
			
			for(Object[] obj:usersCountDtls)
			{
				if(!userIds.contains((Long)obj[1]))
					userIds.add((Long)obj[1]);
				
				if(!datesList.contains(obj[3].toString()))
					datesList.add(obj[3].toString());
			}
			
			Map<Long,String> leadersMap = getLeadersDetailsByUserIds(userIds);
			
			
			for(Long userId:userIds)
			{
				SurveyReportVO userVO = new SurveyReportVO();
				userVO.setUserid(userId);
				
				if(leadersMap.get(userId) != null)
				{
				 userVO.setLeaderName(leadersMap.get(userId).split("-")[1]);
				 userVO.setMobileNo(leadersMap.get(userId).split("-")[0]);
				}
				
				for(String date:datesList)
				{
					SurveyReportVO dateVO = new SurveyReportVO();
					dateVO.setSurveyDate(date);
					userVO.getSubList().add(dateVO);
				}
				
				resultList.add(userVO);
			}
			
			
			for(Object[] obj:usersCountDtls)
			{
				SurveyReportVO userVO = getMatchedUserVO(resultList, (Long)obj[1]);
				userVO.setName(obj[2].toString());
				SurveyReportVO dateVO = getMatchedDateVO(userVO.getSubList(),obj[3].toString());
				dateVO.setCount((Long)obj[0]);
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getUsersReportDetailsForDifferentDates service method ");

		}
		return resultList;
	}
	
	public SurveyReportVO getMatchedDateVO(List<SurveyReportVO> resultList,String date)
	{
		
		for(SurveyReportVO dateVO:resultList)
			if(dateVO.getSurveyDate().equalsIgnoreCase(date))
				return dateVO;
		return null;
	}
	
	public List<SurveyReportVO> getUserReportForADate(Long userId,String surveyDate)
	{
		 List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		 LOG.info("Entered into the getUserReportForADate service method");
		try
		{
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd" );
			
			Date surveyDt = targetFormat.parse(surveyDate);
			
			List<Object[]> userReportList = surveyDetailsInfoDAO.getUserReportForADate(userId,surveyDt);
			
			 /*SDI.voter.voterId, SDI.surveyUser.surveyUserId, SDI.mobileNumber, SDI.caste ,SDI.hamlet,SDI.surveyUser.userName," +
				"SDI.booth.boothId,SDI.booth.partNo,SDI.booth.constituency.constituencyId ,SDI.booth.constituency.name "*/
			
            Map<Long,List<Long>> constnBoothIdsMap = new LinkedHashMap<Long, List<Long>>();	
            
            
            for(Object[] obj:userReportList)
            {
            	List<Long> boothIds = null;
            	if(constnBoothIdsMap.get((Long)obj[8]) != null)
            	{
            		boothIds = constnBoothIdsMap.get((Long)obj[8]);
            		
            	}else
            	{
            		boothIds = new ArrayList<Long>();
            	}
            	
            	if(!boothIds.contains((Long)obj[6]))
            	boothIds.add((Long)obj[6]);
            	constnBoothIdsMap.put((Long)obj[8], boothIds);
            }
			
            
            for(Entry<Long,List<Long>> entry:constnBoothIdsMap.entrySet())
            {
                 SurveyReportVO constituencyVO = new SurveyReportVO();
                 
                 constituencyVO.setId(entry.getKey());
                 
                 for(Long boothId:entry.getValue())
                 {
                	 SurveyReportVO boothVO = new SurveyReportVO();
                	 
                	 boothVO.setId(boothId);
                	 
                	 constituencyVO.getSubList().add(boothVO);
                 }
                 
                 resultList.add(constituencyVO);
                 
            }

            
            for(Object[] obj:userReportList)
            {
            	SurveyReportVO constituencyVO = getMatchedLocationVO(resultList, (Long)obj[8]);
            	constituencyVO.setName(obj[9].toString());
            	SurveyReportVO boothVO = getMatchedLocationVO(constituencyVO.getSubList(),(Long)obj[6]);
            	boothVO.setName(obj[7].toString());
            	
            	
				if(obj[3] != null)
					boothVO.setCasteCollectedCount(boothVO.getCasteCollectedCount()+1);
				
				if(obj[4] != null)
					boothVO.setHamletCollectedCount(boothVO.getHamletCollectedCount() +1);
				
				if(obj[2] != null && !obj[2].toString().trim().equalsIgnoreCase(""))
					boothVO.setMobileNumberCollectedCount(boothVO.getMobileNumberCollectedCount()+1);
            	
            }
          
			
		}catch(Exception e)
		{
			e.printStackTrace();
			 LOG.error("Exception raised in getUserReportForADate service method");

		}
		
		return resultList;
	}
	
	public SurveyReportVO getMatchedLocationVO(List<SurveyReportVO> resultList,Long locationId)
	{
		
		for(SurveyReportVO constituency:resultList)
			if(constituency.getId().equals(locationId))
				return constituency;
		return null;
	}
	
	private SurveyDashBoardVO getMatchedLocationVO1(List<SurveyDashBoardVO> resultList,Long locationId)
	{
		for(SurveyDashBoardVO boothVO:resultList)
			if(boothVO.getLocationId().equals(locationId))
				return boothVO;
		return null;
	
		
	}
	public Map<Long,String> getLeadersDetailsByUserIds(List<Long> userIds)
	{
		 LOG.info("Entered into the getLeadersDetailsByUserIds service method");

		 Map<Long,String> resultMap = new HashMap<Long, String>();
		try
		{
			List<Object[]> leadersDetails = surveyUserRelationDAO.getLeadersByForUsers(userIds);
			
			
			for(Object[] obj:leadersDetails)
			{
				
				resultMap.put((Long)obj[0],obj[1].toString() +"-"+obj[3].toString());
				
			}
			
		}catch(Exception e)
		{
		   e.printStackTrace();
		  LOG.error("Exception raised in getLeadersDetailsByUserIds service method");
		}
		return resultMap;
	}
	
	public List<SurveyReportVO> getVerifiedBoothsDetails(String status,Long constituencyId)
	{
		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();

		try
		{
			
			List<Long> verificationBoothsList = surveyDetailsInfoDAO.getVerificationStartedBoothsDetailsByConstituencyId(constituencyId);
			List<Long> verificationCompletionList = surveyCompletedLocationsDetailsDAO.getVerificationCompletedBoothsDetailsByConstituencyId(constituencyId);
			List<Long> verificationProcessList = new ArrayList<Long>();

		
			for(Long processId:verificationBoothsList)
				if(!verificationCompletionList.contains(processId))
					verificationProcessList.add(processId);
			
			
			if(status.equalsIgnoreCase("process"))
			{
				resultList = surveyDetaisService.getSurveyDetailsByBoothIds(verificationProcessList);
				
			}else
			{
				resultList = surveyDetaisService.getSurveyDetailsByBoothIds(verificationCompletionList);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultList;
	}
}
