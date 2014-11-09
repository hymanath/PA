package com.itgrids.partyanalyst.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICasteInsertTypeDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.dao.ISurveyConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveyFinalDataDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserRelationDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IWebMonitorCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyCompletionDetailsVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.dto.ThirdPartyCompressionVO;
import com.itgrids.partyanalyst.model.SurveyCompletedLocationsDetails;
import com.itgrids.partyanalyst.model.SurveyFinalData;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.WebMonitorCompletedLocationsDetails;
import com.itgrids.partyanalyst.service.ISurveyDashBoardService;
import com.itgrids.partyanalyst.service.ISurveyDetailsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
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
	private TransactionTemplate transactionTemplate;
	@Autowired
	private ISurveyFinalDataDAO surveyFinalDataDAO;
	@Autowired 
	private DateUtilService dateUtilService;
	@Autowired
	private ICasteStateDAO casteStateDAO;
	
	@Autowired
	private IHamletDAO hamletDAO;
	@Autowired
	private ISurveyDetailsService surveyDetaisService;
	
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IUserDAO userDAO;
	private IVoterDAO voterDAO;
	private ICasteInsertTypeDAO casteInsertTypeDAO;
	
	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	
	public void setCasteInsertTypeDAO(ICasteInsertTypeDAO casteInsertTypeDAO) {
		this.casteInsertTypeDAO = casteInsertTypeDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

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
			
			DateFormat dateFormat = new SimpleDateFormat("hh:mm a"); 

			
			for(Object[] obj:startEndTimes)
			{
				SurveyReportVO userVO = getMatchedUserVO(resultList,(Long)obj[2]);
				
				if(userVO != null)
				{
				 userVO.setStartTime(dateFormat.format(obj[0]));
				 userVO.setEndTime(dateFormat.format(obj[1]));
				}
			}
			
			
			for(Object[] obj:usersDetails)
			{
				SurveyReportVO userVO = getMatchedUserVO(resultList,(Long)obj[1]);
				
				userVO.setName(obj[5].toString());
				
				userVO.setCount(userVO.getCount() +1);
				
				if(obj[3].toString().equalsIgnoreCase("1"))
					userVO.setCasteCollectedCount(userVO.getCasteCollectedCount()+1);
				
				if(obj[4].toString().equalsIgnoreCase("1"))
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
            	
            	
				if(obj[3].toString().equalsIgnoreCase("1"))
					boothVO.setCasteCollectedCount(boothVO.getCasteCollectedCount()+1);
				
				if(obj[4].toString().equalsIgnoreCase("1"))
					boothVO.setHamletCollectedCount(boothVO.getHamletCollectedCount() +1);
				
				if(obj[2] != null && !obj[2].toString().trim().equalsIgnoreCase(""))
					boothVO.setMobileNumberCollectedCount(boothVO.getMobileNumberCollectedCount()+1);
            	
            }
          
			
		}catch(Exception e)
		{
			//e.printStackTrace();
			 LOG.error("Exception raised in getUserReportForADate service method",e);

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
				
				resultMap.put((Long)obj[0],obj[2].toString() +"-"+obj[3].toString());
				
			}
			
		}catch(Exception e)
		{
		  // e.printStackTrace();
		  LOG.error("Exception raised in getLeadersDetailsByUserIds service method",e);
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
			 LOG.error("Exception raised in getVerifiedBoothsDetails service method",e);
		}
		
		return resultList;
	}
	
	public List<String> getCasteCollectedDatesByUserId(Long userId){
		List<String> surveyDates = new ArrayList<String>();
		try
		{
			 surveyDates = surveyDetailsInfoDAO.getCasteCollectedDatesByUserId(userId);
			
		}catch(Exception e)
		{
			LOG.error("Exception raised in getCasteCollectedDatesByUserId service method",e);
		}
		
		return surveyDates;
	}
	
	
	public List<String> getCasteCollectedDates()
	{
		List<String> surveyDates = new ArrayList<String>();
		try
		{
			 surveyDates = surveyDetailsInfoDAO.getCasteCollectedDates();
			
		}catch(Exception e)
		{
			LOG.error("Exception raised in getCasteCollectedDates service method",e);
		}
		
		return surveyDates;
	}
	
	/**
	 * This Service is used for saving third party details
	 * @param boothId
	 * @return resultStatus
	 */
	public ResultStatus saveThirdPartyDetails(final Long bootId)
	{
		final ResultStatus resultStatus = new ResultStatus();
		try
		{
			final List<SurveyResponceVO> thirdPartyDetails = surveyDetaisService.getThirdPartyVerificationDetails(bootId,null);
			if(thirdPartyDetails != null && thirdPartyDetails.size() > 0)
			{
				
				transactionTemplate.execute(new TransactionCallbackWithoutResult() 
				{

					protected void doInTransactionWithoutResult(TransactionStatus arg0)
					{
						surveyFinalDataDAO.deleteExistingBoothDetails(bootId);
						for (SurveyResponceVO surveyResponceVO : thirdPartyDetails)
						{
							
							if(surveyResponceVO.getVoterId() != null)
							{
								SurveyFinalData surveyFinalData = new SurveyFinalData();
								surveyFinalData.setVoterId(surveyResponceVO.getVoterId());
								surveyFinalData.setMobileNo(surveyResponceVO.getMobileNo());
								surveyFinalData.setIsCadre(surveyResponceVO.getIsCadre());
								surveyFinalData.setIsInfluencingPeople(surveyResponceVO.getIsInfluencingPeople());
								surveyFinalData.setCasteStateId(surveyResponceVO.getCasteId());
								surveyFinalData.setCasteName(surveyResponceVO.getCasteName());
								
								surveyFinalData.setHamletId(surveyResponceVO.getHamletId());
								surveyFinalData.setHamletName(surveyResponceVO.getHamletName());
								
								surveyFinalData.setWardId(surveyResponceVO.getWardId());
								surveyFinalData.setLocalArea(surveyResponceVO.getLocalArea());
								
								surveyFinalData.setBoothId(surveyResponceVO.getBoothId());
								
								surveyFinalData.setSurveyUserId(surveyResponceVO.getSurveyUserId());
								surveyFinalData.setUuid(surveyResponceVO.getUuid());
								surveyFinalData.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								surveyFinalData.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								SurveyFinalData saveStatus = surveyFinalDataDAO.save(surveyFinalData);
								if(saveStatus != null)
								{
									resultStatus.setResultCode(2);
									resultStatus.setMessage("Succuss");
								}
								else
								{
									resultStatus.setResultCode(4);
									resultStatus.setMessage("Error Occured");
								}
							}
							
						}
						
					}
				});
				voterDAO.flushAndclearSession();
			}
			else
			{
				resultStatus.setResultCode(1);
				resultStatus.setMessage("No Data Avaliable");
			}
				
			
		} 
		catch (Exception e) 
		{
			resultStatus.setResultCode(0);
			resultStatus.setMessage("Exception");
			LOG.error("Exception raised in ResultStatus service method",e);
		}
		return resultStatus;
	}
	
	
	/**
	 * This Service is used for providing third party details
	 * @param boothId
	 * @return returnList
	 */
	public  List<SurveyResponceVO> getThirdPartyFinalDetails1(Long boothId)
	{
		List<SurveyResponceVO> returnList = null;
		try
		{
			List<Object[]> resultList = surveyFinalDataDAO.getBoothWiseVoterDetails(boothId);
			if(resultList != null && resultList.size() > 0)
			{
				returnList = new ArrayList<SurveyResponceVO>();
				fillSurveyResponceVO1(resultList,returnList);
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getThirdPartyFinalDetails service method",e);
		}
		return returnList;
	}
	/**
	 * This Service is used for providing third party details
	 * @param boothId
	 * @return returnList
	 */
	public  List<SurveyResponceVO> getThirdPartyFinalDetails(Long boothId)
	{
		List<SurveyResponceVO> returnList = null;
		try
		{
			List<Object[]> resultList = surveyFinalDataDAO.getBoothWiseVoterDetails(boothId);
			if(resultList != null && resultList.size() > 0)
			{
				returnList = new ArrayList<SurveyResponceVO>();
				fillSurveyResponceVO2(resultList,returnList);
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getThirdPartyFinalDetails service method",e);
		}
		return returnList;
	}
	
	
	public void fillSurveyResponceVO(List<Object[]> resultList,List<SurveyResponceVO> returnList)
	{
		try 
		{
			for (Object[] parms : resultList) 
			{
				
				boolean casteExisist=false;
				boolean hamletExisist=false;
				SurveyResponceVO surveyResponceVO = new SurveyResponceVO();
				surveyResponceVO.setDataTypeId("2");
				surveyResponceVO.setBoothId((Long)parms[0]);
				if(parms[1] != null)
				{
					surveyResponceVO.setVoterId(parms[1] != null ? Long.valueOf(parms[1].toString()) :0l);
					surveyResponceVO.setVoterCardNo(parms[2] != null ? parms[2].toString() : "-");
					surveyResponceVO.setVoterName(parms[3] != null ? parms[3].toString() : "-");
					surveyResponceVO.setGender(parms[4] != null ? parms[4].toString() : "-");
					surveyResponceVO.setAge(parms[5] != null ? Long.valueOf(parms[5].toString()) : 0l);
					surveyResponceVO.setHouseNo(parms[6] != null ? parms[6].toString() : "-");
					surveyResponceVO.setRelativeName(parms[16] != null ? parms[16].toString() : "-");
				//	surveyResponceVO.setUuid(parms[18] != null ? parms[17].toString() : "");
				}
				surveyResponceVO.setMobileNo(parms[7] != null ? parms[7].toString() : "-");
				surveyResponceVO.setIsCadre(parms[8] != null ? parms[8].toString() : "-");
				surveyResponceVO.setIsInfluencingPeople(parms[9] != null ? parms[9].toString() : "-");
				if(parms.length > 18 && parms[18] != null)
				{
					surveyResponceVO.setUuid(parms[18] != null ? parms[18].toString() : "-");
				}
				

				if(parms[10] != null)
				{
					casteExisist=true;
					if(parms[11] != null && parms[11].toString().trim().length() > 0)
					{
						surveyResponceVO.setCasteName(parms[11] != null ? parms[11].toString() : "-");
					}
					else
					{
						surveyResponceVO.setCasteName(casteStateDAO.get(Long.valueOf(parms[10].toString())).getCaste().getCasteName());
					}
					surveyResponceVO.setCasteId(parms[10] != null ? Long.valueOf(parms[10].toString()) :0l);
				}
				else
				{
					if(parms[11] != null && parms[11].toString().trim().length() > 0)
					{
						surveyResponceVO.setCasteName(parms[11] != null ? parms[11].toString() : "-");
					}
				}
				if(parms[12] != null)
				{
					hamletExisist=true;
					if(parms[13] != null && parms[13].toString().trim().length() > 0)
					{
						surveyResponceVO.setHamletName(parms[13] != null ? parms[13].toString() : "-");
					}
					else
					{
						surveyResponceVO.setHamletName(hamletDAO.get(Long.valueOf(parms[12].toString())).getHamletName());
					}
					surveyResponceVO.setHamletId(parms[12] != null ? Long.valueOf(parms[12].toString()) :0l);
				}
				else
				{
					if(parms[13] != null && parms[13].toString().trim().length() > 0)
					{
						surveyResponceVO.setHamletName(parms[13] != null ? parms[13].toString() : "-");
					}
					if(parms[14] != null)
					{
						hamletExisist=true;
					}
				}
				if(!hamletExisist && !casteExisist)
					surveyResponceVO.setDataTypeId("1");
				
				surveyResponceVO.setWardId(parms[14] != null ? Long.valueOf(parms[14].toString()) :0l);
				surveyResponceVO.setLocalArea(parms[15] != null ? parms[15].toString() : "-");
				if(parms.length > 17 && parms[17] != null){
					surveyResponceVO.setStatusId(parms[17].toString());
				}
				returnList.add(surveyResponceVO);
			}
		} 
		catch (Exception e) {
			LOG.error("Exception raised in fillSurveyResponceVO service method",e);
		}
	}
	
	/**
	 * This Service is used for comparing booth Third party Collected and Third party providers 
	 * @param boothId
	 * @param surveyUserId
	 * @return
	 */
	public List<ThirdPartyCompressionVO> getCompressionReportForThirdParty(Long boothId,Long surveyUserId)
	{
		List<ThirdPartyCompressionVO> returnList = null;
		try 
		{
			List<SurveyResponceVO> thirdPartyDetails = getThirdPartyFinalDetails1(boothId);
			if(thirdPartyDetails != null && thirdPartyDetails.size() > 0)
			{
				Map<Long,SurveyResponceVO> tpProvidedMap = new HashMap<Long, SurveyResponceVO>();
				for (SurveyResponceVO surveyResponceVO : thirdPartyDetails)
				{
					if(surveyResponceVO.getVoterId() != null)
					{
						tpProvidedMap.put(surveyResponceVO.getVoterId(), surveyResponceVO);
					}
					
				}
				
				Map<Long,SurveyResponceVO> tpCollectedMap = getThirdPartyCollectedDetails(boothId,surveyUserId);
				
				if(tpProvidedMap != null && tpProvidedMap.size() > 0)
				{
					Long matchedCount = 0l;
					Long unMatchedCount = 0l;
					Long notIdentifedCount = 0l;
					Long newCasteCollected = 0l;
					returnList = new ArrayList<ThirdPartyCompressionVO>();
					for(Long voterId : tpProvidedMap.keySet())
					{
						SurveyResponceVO tpProvidedVO = tpProvidedMap.get(voterId);
						SurveyResponceVO tpCollectedVO = null;
						if(tpCollectedMap != null && tpCollectedMap.size() > 0)
						{
							tpCollectedVO = tpCollectedMap.get(voterId);
						}
						ThirdPartyCompressionVO thirdPartyCompressionVO = new ThirdPartyCompressionVO();
						if(tpProvidedVO != null)
						{
							thirdPartyCompressionVO.setVoterId(tpProvidedVO.getVoterId());
							thirdPartyCompressionVO.setVoterCardNo(tpProvidedVO.getVoterCardNo());
							thirdPartyCompressionVO.setVoterName(tpProvidedVO.getVoterName());
							thirdPartyCompressionVO.setRelativeName(tpProvidedVO.getRelativeName());
							thirdPartyCompressionVO.setGender(tpProvidedVO.getGender());
							thirdPartyCompressionVO.setAge(tpProvidedVO.getAge());
							thirdPartyCompressionVO.setHouseNo(tpProvidedVO.getHouseNo());
							thirdPartyCompressionVO.setIsCadre(tpProvidedVO.getIsCadre());
							thirdPartyCompressionVO.setIsInfluencingPeople(tpProvidedVO.getIsInfluencingPeople());
							thirdPartyCompressionVO.setTpCaste(tpProvidedVO.getCasteName() != null ?tpProvidedVO.getCasteName() :"-");
							thirdPartyCompressionVO.setTpHamlet(tpProvidedVO.getHamletName());
							thirdPartyCompressionVO.setTpWard(tpProvidedVO.getWardId());
							thirdPartyCompressionVO.setTpCasteStateId(tpProvidedVO.getCasteId());
							thirdPartyCompressionVO.setTphamletId(tpProvidedVO.getHamletId());
							thirdPartyCompressionVO.setStatus(tpProvidedVO.getStatusId());
							thirdPartyCompressionVO.setSerialNo(tpProvidedVO.getSerialNo());
							thirdPartyCompressionVO.setMobileNo(tpProvidedVO.getMobileNo());
							if(tpProvidedVO.getComment() != null){
								thirdPartyCompressionVO.setComment(tpProvidedVO.getComment());
							}else{
							    thirdPartyCompressionVO.setComment("");
							}
							if(tpCollectedVO != null)
							{
								thirdPartyCompressionVO.setWardId(tpCollectedVO.getWardId());
								thirdPartyCompressionVO.setWmCaste(tpCollectedVO.getCasteName() != null ? tpCollectedVO.getCasteName() :"-");
								thirdPartyCompressionVO.setWmCasteStateId(tpCollectedVO.getCasteId());
								thirdPartyCompressionVO.setWmHamlet(tpCollectedVO.getHamletName());
								thirdPartyCompressionVO.setWmHamletId(tpCollectedVO.getHamletId());
								if(thirdPartyCompressionVO.getMobileNo() != null && thirdPartyCompressionVO.getMobileNo().trim().length() > 3)
								{
									if(tpCollectedVO.getMobileNo() != null && tpCollectedVO.getMobileNo().trim().length() > 3)
									thirdPartyCompressionVO.setMobileNo(thirdPartyCompressionVO.getMobileNo() +","+ tpCollectedVO.getMobileNo());
								}
								else
								{
									thirdPartyCompressionVO.setMobileNo(tpCollectedVO.getMobileNo());
								}
								
								if(tpCollectedVO.getCasteId() != null && tpProvidedVO.getCasteId() != null)
								{
									if(tpCollectedVO.getCasteId().longValue() == tpProvidedVO.getCasteId().longValue())
									{
										matchedCount = matchedCount + 1;
										thirdPartyCompressionVO.setMatchedStatus("MATCHED");
									}
									else
									{
										unMatchedCount = unMatchedCount + 1;
										thirdPartyCompressionVO.setMatchedStatus("UNMATCHED");
									}
								}
								else 
								{
									if(tpCollectedVO.getCasteId() == null && tpProvidedVO.getCasteId() == null)
									{
										notIdentifedCount = notIdentifedCount + 1;
										thirdPartyCompressionVO.setMatchedStatus("NOT IDENTIFED");
									}
									else if(tpProvidedVO.getCasteId() != null && tpCollectedVO.getCasteId() == null)
									{
										notIdentifedCount = notIdentifedCount + 1;
										thirdPartyCompressionVO.setMatchedStatus("NOT IDENTIFED");
									}
									else if(tpProvidedVO.getCasteId() == null && tpCollectedVO.getCasteId() != null)
									{
										newCasteCollected = newCasteCollected + 1;
										thirdPartyCompressionVO.setMatchedStatus("NEW CASTE");
									}
									else
									{
										unMatchedCount = unMatchedCount + 1;
										thirdPartyCompressionVO.setMatchedStatus("UNMATCHED");
									}
										
									
								}
								
								
							}
							else
							{
								notIdentifedCount = notIdentifedCount + 1;
								thirdPartyCompressionVO.setMatchedStatus("NOT IDENTIFED");
							}
							returnList.add(thirdPartyCompressionVO);
						}
						
						
					}
					
					if(returnList != null && returnList.size() > 0){
						returnList.get(0).setSurveyResponceVO(thirdPartyCollectedBasicData(boothId,surveyUserId,false));
						returnList.get(0).setMatchedCount(matchedCount);
						returnList.get(0).setUnMatchedCount(unMatchedCount);
						returnList.get(0).setNotIdentifedCount(notIdentifedCount);
						returnList.get(0).setNewCasteCount(newCasteCollected);
					}
				}
				
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getCompressionReportForThirdParty service method",e);
		}
		return returnList;
	}
	
	/**
	 * This Service is used for getting third party collected details by booth and user wise
	 * @param boothId
	 * @param surveyUserId
	 * @return returnMap
	 */ 
	public Map<Long,SurveyResponceVO> getThirdPartyCollectedDetails(Long boothId,Long surveyUserId)
	{
		Map<Long,SurveyResponceVO> returnMap = null;
		try
		{
			List<Object[]> tpCollectedDetails = surveyDetailsInfoDAO.getThirdPartyCollectedDetails(boothId, surveyUserId);
			if(tpCollectedDetails != null && tpCollectedDetails.size() > 0)
			{
				List<SurveyResponceVO> returnList = new ArrayList<SurveyResponceVO>();
				fillSurveyResponceVO(tpCollectedDetails,returnList);
				if(returnList != null && returnList.size() > 0)
				{
					returnMap = new HashMap<Long, SurveyResponceVO>();
					for (SurveyResponceVO surveyResponceVO : returnList)
					{
						if(surveyResponceVO.getVoterId() != null)
						{
							returnMap.put(surveyResponceVO.getVoterId(), surveyResponceVO);
						}
						
					}
				}
				
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in getThirdPartyCollectedDetails service method",e);
		}
		return returnMap;
	}
	
	public List<GenericVO> getConstituencyListForThirdPartyReport(){
		List<GenericVO> resultList = new ArrayList<GenericVO>();
		try {
			List<Object[]> constituencies = surveyFinalDataDAO.getSurveyFinalConstituencyInfo();	
			if(constituencies != null && constituencies.size() > 0)
			{
				for(Object[] constituency : constituencies)
				{
					resultList.add(new GenericVO(constituency[0] != null ? (Long)constituency[0] : 0L, WordUtils.capitalize(constituency[1] != null ? constituency[1].toString().toLowerCase() : "")));
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getConstituencyListForThirdPartyReport()",e);
		}		
		return resultList;
	}
	
	/**
	 * This Service is used for updating third party voter status
	 * @param voterId
	 * @param statusId
	 * @return resultStatus
	 */
	public ResultStatus updateThirdPartyStatus(Long voterId,Long statusId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			List<Long> voterIds = new ArrayList<Long>();
			voterIds.add(voterId);
			if(statusId.longValue() == 0l)
			{
				statusId = null;
			}
			int resultCount = surveyFinalDataDAO.updatedThirdPartyStatus(voterIds, statusId);
			if(resultCount >= 0)
			{
				resultStatus.setResultCode(0);
				resultStatus.setMessage("SUCCUSS");
			}
			else
			{
				resultStatus.setResultCode(1);
				resultStatus.setMessage("ERROR");
			}
		}
		catch (Exception e)
		{
			resultStatus.setResultCode(2);
			resultStatus.setMessage("EXCEPTION");
			LOG.error("Exception raised in getConstituencyListForThirdPartyReport()",e);
		}
		return resultStatus;
	}
	
	public ResultStatus updateThirdPartyComment(List<Long> voterIds,String comment,Long statusId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			
			int resultCount = surveyFinalDataDAO.updatedThirdPartyComment(voterIds, comment);
			if(statusId != null){
				if(statusId.longValue() == 0l){
					statusId = null;
				}
				surveyFinalDataDAO.updatedThirdPartyStatus(voterIds, statusId);
			}
			if(resultCount >= 0)
			{
				resultStatus.setResultCode(0);
				resultStatus.setMessage("SUCCUSS");
			}
			else
			{
				resultStatus.setResultCode(1);
				resultStatus.setMessage("ERROR");
			}
		}
		catch (Exception e)
		{
			resultStatus.setResultCode(2);
			resultStatus.setMessage("EXCEPTION");
			LOG.error("Exception raised in getConstituencyListForThirdPartyReport()",e);
		}
		return resultStatus;
	}
	
	public void fillSurveyResponceVO1(List<Object[]> resultList,List<SurveyResponceVO> returnList)
	{
		try 
		{
			for (Object[] parms : resultList) 
			{
				SurveyResponceVO surveyResponceVO = new SurveyResponceVO();
				surveyResponceVO.setDataTypeId("1");
				surveyResponceVO.setBoothId((Long)parms[0]);
				if(parms[1] != null)
				{
					surveyResponceVO.setVoterId(parms[1] != null ? Long.valueOf(parms[1].toString()) :0l);
					surveyResponceVO.setVoterCardNo(parms[2] != null ? parms[2].toString() : "-");
					surveyResponceVO.setVoterName(parms[3] != null ? parms[3].toString() : "-");
					surveyResponceVO.setGender(parms[4] != null ? parms[4].toString() : "-");
					surveyResponceVO.setAge(parms[5] != null ? Long.valueOf(parms[5].toString()) : 0l);
					surveyResponceVO.setHouseNo(parms[6] != null ? parms[6].toString() : "-");
					surveyResponceVO.setRelativeName(parms[16] != null ? parms[16].toString() : "-");
				}
				surveyResponceVO.setMobileNo(parms[7] != null ? parms[7].toString() : "-");
				surveyResponceVO.setIsCadre(parms[8] != null ? parms[8].toString() : "-");
				surveyResponceVO.setIsInfluencingPeople(parms[9] != null ? parms[9].toString() : "-");
				if(parms[10] != null)
				{
					if(parms[11] != null && parms[11].toString().trim().length() > 0)
					{
						surveyResponceVO.setCasteName(parms[11] != null ? parms[11].toString() : "-");
					}
					else
					{
						surveyResponceVO.setCasteName(casteStateDAO.get(Long.valueOf(parms[10].toString())).getCaste().getCasteName());
					}
					surveyResponceVO.setCasteId(parms[10] != null ? Long.valueOf(parms[10].toString()) :0l);
				}
				else
				{
					if(parms[11] != null && parms[11].toString().trim().length() > 0)
					{
						surveyResponceVO.setCasteName(parms[11] != null ? parms[11].toString() : "-");
					}
				}
				if(parms[12] != null)
				{
					if(parms[13] != null && parms[13].toString().trim().length() > 0)
					{
						surveyResponceVO.setHamletName(parms[13] != null ? parms[13].toString() : "-");
					}
					else
					{
						surveyResponceVO.setHamletName(hamletDAO.get(Long.valueOf(parms[12].toString())).getHamletName());
					}
					surveyResponceVO.setHamletId(parms[12] != null ? Long.valueOf(parms[12].toString()) :0l);
				}
				else
				{
					if(parms[13] != null && parms[13].toString().trim().length() > 0)
					{
						surveyResponceVO.setHamletName(parms[13] != null ? parms[13].toString() : "-");
					}
				}
				surveyResponceVO.setWardId(parms[14] != null ? Long.valueOf(parms[14].toString()) :0l);
				surveyResponceVO.setLocalArea(parms[15] != null ? parms[15].toString() : "-");
				surveyResponceVO.setUuid(parms[18] != null ? parms[18].toString() : "-");
				if(parms[17] != null){
					surveyResponceVO.setStatusId(parms[17].toString());
				}
				
					surveyResponceVO.setSerialNo((Long)parms[19]);
					if(parms[20] != null){
					  surveyResponceVO.setComment(parms[20].toString());
					}
				
				returnList.add(surveyResponceVO);
			}
		} 
		catch (Exception e) {
			LOG.error("Exception raised in fillSurveyResponceVO service method",e);
		}
	}
	
	
	public void fillSurveyResponceVO2(List<Object[]> resultList,List<SurveyResponceVO> returnList)
	{
		try 
		{
			for (Object[] parms : resultList) 
			{
				SurveyResponceVO surveyResponceVO = new SurveyResponceVO();
				surveyResponceVO.setDataTypeId("1");
				surveyResponceVO.setBoothId((Long)parms[0]);
				if(parms[1] != null)
				{
					surveyResponceVO.setVoterId(parms[1] != null ? Long.valueOf(parms[1].toString()) :0l);
					surveyResponceVO.setVoterCardNo(parms[2] != null ? parms[2].toString() : "-");
					surveyResponceVO.setVoterName(parms[3] != null ? parms[3].toString() : "-");
					surveyResponceVO.setGender(parms[4] != null ? parms[4].toString() : "-");
					surveyResponceVO.setAge(parms[5] != null ? Long.valueOf(parms[5].toString()) : 0l);
					surveyResponceVO.setHouseNo(parms[6] != null ? parms[6].toString() : "-");
					surveyResponceVO.setRelativeName(parms[16] != null ? parms[16].toString() : "-");
				}
				//surveyResponceVO.setMobileNo(parms[7] != null ? parms[7].toString() : "-");
				//surveyResponceVO.setIsCadre(parms[8] != null ? parms[8].toString() : "-");
				//surveyResponceVO.setIsInfluencingPeople(parms[9] != null ? parms[9].toString() : "-");
				/*if(parms[10] != null)
				{
					if(parms[11] != null && parms[11].toString().trim().length() > 0)
					{
						surveyResponceVO.setCasteName(parms[11] != null ? parms[11].toString() : "-");
					}
					else
					{
						surveyResponceVO.setCasteName(casteStateDAO.get(Long.valueOf(parms[10].toString())).getCaste().getCasteName());
					}
					surveyResponceVO.setCasteId(parms[10] != null ? Long.valueOf(parms[10].toString()) :0l);
				}
				else
				{
					if(parms[11] != null && parms[11].toString().trim().length() > 0)
					{
						surveyResponceVO.setCasteName(parms[11] != null ? parms[11].toString() : "-");
					}
				}*/
				/*if(parms[12] != null)
				{
					if(parms[13] != null && parms[13].toString().trim().length() > 0)
					{
						surveyResponceVO.setHamletName(parms[13] != null ? parms[13].toString() : "-");
					}
					else
					{
						surveyResponceVO.setHamletName(hamletDAO.get(Long.valueOf(parms[12].toString())).getHamletName());
					}
					surveyResponceVO.setHamletId(parms[12] != null ? Long.valueOf(parms[12].toString()) :0l);
				}
				else
				{
					if(parms[13] != null && parms[13].toString().trim().length() > 0)
					{
						surveyResponceVO.setHamletName(parms[13] != null ? parms[13].toString() : "-");
					}
				}*/
				/*surveyResponceVO.setWardId(parms[14] != null ? Long.valueOf(parms[14].toString()) :0l);
				surveyResponceVO.setLocalArea(parms[15] != null ? parms[15].toString() : "-");
				surveyResponceVO.setUuid(parms[18] != null ? parms[18].toString() : "-");*/
				/*if(parms[17] != null){
					surveyResponceVO.setStatusId(parms[17].toString());
				}*/
				
					surveyResponceVO.setSerialNo((Long)parms[19]);
					/*if(parms[20] != null){
					  surveyResponceVO.setComment(parms[20].toString());
					}*/
				
				returnList.add(surveyResponceVO);
			}
		} 
		catch (Exception e) {
			LOG.error("Exception raised in fillSurveyResponceVO service method",e);
		}
	}
	/**
	 * This Service is used for getting updated comments from WM 
	 * @param boothId
	 * @return returnList
	 */
	public List<GenericVO> getUpdatedCommentsFromWmForTP(Long boothId)
	{
		List<GenericVO> returnList = null;
		try 
		{
			List<Object[]> castesList = casteStateDAO.getAllCasteDetailsForVoters(1l);
			if(castesList != null && castesList.size() > 0)
			{
				Map<Long,String> casteMap = new HashMap<Long, String>();
				Map<Long,String> tpCollectedMap = null;
				Map<Long,GenericVO> wmUpdatedMap = null;
				for (Object[] parms : castesList)
				{
					casteMap.put((Long)parms[0], parms[1].toString());
				}
				List<Object[]> tpCollectedDetails = surveyDetailsInfoDAO.getThirdPartyCollectedInfo(boothId);
				if(tpCollectedDetails != null && tpCollectedDetails.size() > 0)
				{
					tpCollectedMap = new HashMap<Long, String>();
					for (Object[] objects : tpCollectedDetails)
					{
						if(casteMap.get((Long)objects[1]) != null)
						tpCollectedMap.put((Long)objects[0],casteMap.get((Long)objects[1]));
					}
				}
				
				List<Object[]> wmUpdatedDetails = surveyFinalDataDAO.getWmCommentedDetails(boothId);
				if(wmUpdatedDetails != null && wmUpdatedDetails.size() > 0)
				{
					wmUpdatedMap = new HashMap<Long, GenericVO>();
					for (Object[] parms : wmUpdatedDetails)
					{
						GenericVO genericVO = new GenericVO();
						genericVO.setId((Long)parms[0]);
						genericVO.setName(parms[1] != null ? parms[1].toString() :"-");//voter name
						genericVO.setDesc(parms[2] != null ? casteMap.get((Long)parms[2]) :"-");// Wm Updated caste
						genericVO.setPercent(parms[3] != null ? parms[3].toString() :"-");// comment
						genericVO.setCount(parms[4] != null ? (Long)parms[4] :0l);// correctd type
						wmUpdatedMap.put((Long)parms[0], genericVO);
					}
				}
				
				if(wmUpdatedMap != null && wmUpdatedMap.size() > 0)
				{
					returnList = new ArrayList<GenericVO>();
					for (Long voterId : wmUpdatedMap.keySet())
					{
						GenericVO finalVO =wmUpdatedMap.get(voterId);
						if(tpCollectedMap != null && tpCollectedMap.size() > 0)
						finalVO.setMobileNo(tpCollectedMap.get(voterId) != null ? tpCollectedMap.get(voterId) : "-") ;// Tp Collected caste
						
						if(finalVO.getCount().longValue() == 2)
						{
							finalVO.setCaste("WM Wrong");
						}
						else if(finalVO.getCount().longValue() == 3)
						{
							finalVO.setCaste("TP Wrong");
						}
						else if(finalVO.getCount().longValue() == 4)
						{
							finalVO.setCaste("New Caste Data");
						}
						else if(finalVO.getCount().longValue() == 5)
						{
							finalVO.setCaste("Same Caste Different Name");
						}
						returnList.add(finalVO);
					}
				}
			}
			
		} 
		catch (Exception e) 
		{
			LOG.error("Exception raised in getUpdatedCommentsFromWmForTP service method",e);
		}
		return returnList;
	}
	
	/**
	 * This Service is used for getting not runned third party details
	 * @param constituencyId
	 * @return returnList
	 */
	public List<GenericVO> getThirdPartyAvaliableBooths(Long constituencyId)
	{
		List<GenericVO> returnList = null;
		try 
		{
			List<Object[]> totalBooths = boothDAO.getBoothsInAConstituencyByPublication(constituencyId, 11l);
			if(totalBooths != null && totalBooths.size() > 0)
			{
				Map<Long,String> boothsMap = new HashMap<Long, String>();
				Map<Long,String> tpAvaliableBoothsMap = null;
				for (Object[] parms : totalBooths)
				{
					boothsMap.put((Long)parms[0], parms[1].toString());
				}
				
				List<Object[]> tpAvaliableBooths = surveyFinalDataDAO.getThirdPartyBooths(constituencyId);
				if(tpAvaliableBooths != null && tpAvaliableBooths.size() > 0)
				{
					tpAvaliableBoothsMap = new HashMap<Long, String>();
					for (Object[] parms : tpAvaliableBooths)
					{
						tpAvaliableBoothsMap.put((Long)parms[0], parms[1].toString());
					}
				}
				
				if(boothsMap != null && boothsMap.size() > 0)
				{
					returnList = new ArrayList<GenericVO>();
					if(tpAvaliableBoothsMap != null && tpAvaliableBoothsMap.size() > 0)
					{
						for(Long boothId : boothsMap.keySet())
						{
								String partNo =  tpAvaliableBoothsMap.get(boothId);
								if(partNo == null)
								{
									GenericVO genericVO = new GenericVO();
									genericVO.setId(boothId);
									genericVO.setName("BOOTH" +" - "+ boothsMap.get(boothId));
									returnList.add(genericVO);
								}
						}
						
					}
					else
					{
						for(Long boothId : boothsMap.keySet())
						{
							GenericVO genericVO = new GenericVO();
							genericVO.setId(boothId);
							genericVO.setName("BOOTH" +" - "+ boothsMap.get(boothId));
							returnList.add(genericVO);
						}
					}
						
					Collections.sort(returnList,arraySort1);
					}
				}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getThirdPartyAvaliableBooths service method",e);
		}
		return returnList;
	}
	
	/**
	 * This Service is used for grtting all third party data avaliable booths
	 * @param constituencyId
	 * @return returnList
	 */
	public List<GenericVO> getThirdRaprtyBooths(Long constituencyId)
	{
		List<GenericVO> returnList = null;
		try 
		{
			List<Object[]> tpAvaliableBooths = surveyFinalDataDAO.getThirdPartyBooths(constituencyId);
			if(tpAvaliableBooths != null && tpAvaliableBooths.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Object[] objects : tpAvaliableBooths)
				{
					GenericVO genericVO = new GenericVO();
					genericVO.setId((Long)objects[0]);
					genericVO.setName("BOOTH" +" - "+ objects[1].toString());
					returnList.add(genericVO);
				}
				Collections.sort(returnList,arraySort1);
			}
		} 
		catch (Exception e) 
		{
			LOG.error("Exception raised in getThirdRaprtyBooths service method",e);
		}
		
		return returnList;
	}
	
	public String deleteThirdPartyData(Long boothId)
	{
		String status = null;
		try
		{
			int count = surveyFinalDataDAO.deleteExistingBoothDetails(boothId);
			if(count > 0)
			{
				status = "SUCCESS";
			}
			else
			{
				status = "ERROR";
			}
		} 
		catch (Exception e) 
		{
			status = "EXCEPTION";
			LOG.error("Exception raised in deleteThirdPartyData service method",e);
		}
		
		return status;
	}
	
	
	public static Comparator<GenericVO> arraySort1 = new Comparator<GenericVO>()
			{	  
					  public int compare(GenericVO arg1,GenericVO arg2)
						{
						  return arg1.getId().compareTo(arg2.getId());
						}
			};	
	//this method will return basic thirdParty(TP) user Details and tp collected details
	/** @return Tp Name ,mobile No
	 *  @return Tp Lead Name ,mobile No
	 *  @return booth No
	 *  @return Total Voters in Booth
	 *  @return Same Caste Count,Different Caste Count,TP Wrong Count,Wm Wrong Count,New Caste Data Collected Count
	 */
	public SurveyResponceVO thirdPartyCollectedBasicData(Long boothId,Long userId,boolean onlyStatus){
		SurveyResponceVO statusVO = new SurveyResponceVO();
	  try{
		//0 count,1 statusId
		 /* statusVO.setMatchedCount(matchedCount);
		  statusVO.setUnMatchedCount(unMatchedCount);
		  statusVO.setNotIdentifedCount(notIdentifedCount);*/
		List<Object[]> statusList = surveyFinalDataDAO.getWMUpdatedStatusOnThirdPartyDataByBooth(userId, boothId);
		for(Object[] params : statusList)
		 {	
			 if(((Long)params[1]).longValue() == 1l){
				 statusVO.setSameCount((Long)params[0]);
			 }else if(((Long)params[1]).longValue() == 2l){
				 statusVO.setWmWrong((Long)params[0]);
			 }else if(((Long)params[1]).longValue() == 3l){
				 statusVO.setTpWrong((Long)params[0]);
			 }else if(((Long)params[1]).longValue() == 4l){
				 statusVO.setNewCaste((Long)params[0]);
			 }
			
		 }
		 if(statusVO.getSameCount() == null){
			 statusVO.setSameCount(0l);
		 }
		 if(statusVO.getWmWrong() == null){
			 statusVO.setWmWrong(0l);
		 }
		 if(statusVO.getTpWrong() == null){
			 statusVO.setTpWrong(0l);
		 }
		 if(statusVO.getNewCaste() == null){
			 statusVO.setNewCaste(0l);
		 }
       if(!onlyStatus){
			//0 userId,1 userName,2 userMobile,3 leaderId,4 leaderName,5 leaderMobile
			 List<Object[]>  userDetailsList = surveyUserRelationDAO.getSurveyUserAndLeaderInfo(userId);
			 
			for(Object[] userDetails : userDetailsList)
			 {	
				statusVO.setSurveyUserId((Long)userDetails[0]);
				statusVO.setVoterName(userDetails[1].toString());
				if(userDetails[2] != null){
				   statusVO.setLongitude(userDetails[2].toString());
				}else{
				  statusVO.setLongitude("");
				}
				
				statusVO.setSurveyorId((Long)userDetails[3]);
				statusVO.setRelativeName(userDetails[4].toString());
				if(userDetails[5] != null){
				   statusVO.setLatitude(userDetails[5].toString());
				}else{
				   statusVO.setLatitude("");
				}
			 }
			//0 count,1 partNo
			List<Object[]>  boothDataList = surveyFinalDataDAO.getBoothDetails(boothId);
			for(Object[] boothData:boothDataList){
				statusVO.setVoterId((Long)boothData[0]);
				statusVO.setCasteName(boothData[1].toString());
			}
         }
	  }catch(Exception e){
		  LOG.error("Exception raised in thirdPartyCollectedBasicData ",e);
	  }
		return statusVO;
	}

	
	public ResultStatus saveVoterFinalCasteOfAConstituency(Long constituencyId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			LOG.error("Enter into saveVoterFinalCasteOfAConstituency Method - "+new Date());
			List<GenericVO> list = getThirdPartyAvaliableBooths(constituencyId);
			
			LOG.error("getThirdPartyAvaliableBooths Completed - "+new Date());
			
			if(list != null && list.size() > 0)
			{
				for(GenericVO genericVO : list)
				{
					try{
						LOG.error(genericVO.getId()+" Started - "+new Date());
						ResultStatus rs = saveThirdPartyDetails(genericVO.getId());
						LOG.error(genericVO.getId()+" Ended - "+new Date());
						if(rs.getResultCode() == ResultCodeMapper.FAILURE)
							LOG.error(rs.getMessage());
					}catch(Exception e)
					{
						LOG.error(e);
					}
				}
			}
			LOG.error("Existing from saveVoterFinalCasteOfAConstituency Method - "+new Date());
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch(Exception e)
		{
			LOG.error("Exception raised in saveVoterFinalCasteOfAConstituency() Method",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	public ResultStatus saveVoterFinalCasteToMainTableOfAConstituency(Long constituencyId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			Map<Long,Long> casteMap = new HashMap<Long, Long>(0);
			List<Object[]> list1 = surveyFinalDataDAO.getVoterFinalCasteDataFromSurveyFinalData(constituencyId);
			List<Object[]> list2 = surveyFinalDataDAO.getVoterFinalCasteDataFromSurveyDetailsInfo(constituencyId);
			
			if(list1!= null && list1.size() > 0)
			{
				for(Object[] params : list1)
					casteMap.put((Long)params[0],(Long)params[1]);
			}
			if(list2!= null && list2.size() > 0)
			{
				for(Object[] params : list2)
					if(casteMap.get((Long)params[0]) == null)
					{
						casteMap.put((Long)params[0],(Long)params[1]);
					}
			}
			
			int index = 0;
			for(Map.Entry<Long,Long> entry : casteMap.entrySet())
			{
				try{
					Long voterId = entry.getKey();
					Long casteStateId = entry.getValue();
					++index;
					
					if(index % 1000 == 0)
					{
						LOG.error("Index at -->"+index+" At Time --> "+new Date());
						LOG.error("Voter Id -->"+voterId+"\tCasteStateId -->"+casteStateId);
					}
					
					UserVoterDetails userVoterDetails = userVoterDetailsDAO.getUserVoterDetailsByUserIdAndVoterId(IConstants.ADMIN_USER_ID,voterId);
					
					if(userVoterDetails == null)
						userVoterDetails = new UserVoterDetails();
					
					userVoterDetails.setUser(userDAO.get(IConstants.ADMIN_USER_ID));
					userVoterDetails.setVoter(voterDAO.get(voterId));
					userVoterDetails.setCasteState(casteStateDAO.get(casteStateId));
					userVoterDetails.setCasteInsertType(casteInsertTypeDAO.get(5L));
					userVoterDetailsDAO.save(userVoterDetails);
					
					if(index % 1000 == 0)
					{
						voterDAO.flushAndclearSession();
					}
				}catch(Exception e)
				{
					LOG.error(e);
				}
			}
			
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch(Exception e)
		{
			LOG.error("Exception raised in saveVoterFinalCasteOfAConstituency() Method",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
}
