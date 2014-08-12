package com.itgrids.partyanalyst.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyCallStatusDAO;
import com.itgrids.partyanalyst.dao.ISurveyCompletedConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDAO;
import com.itgrids.partyanalyst.dao.ISurveyConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveyFinalDataDAO;
import com.itgrids.partyanalyst.dao.ISurveyWmThirdPartyStatusDAO;
import com.itgrids.partyanalyst.dao.IWebMonitoringAssignedUsersDAO;
import com.itgrids.partyanalyst.dto.FinalSurveyReportVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.dto.SurveyThirdPartyReportVO;
import com.itgrids.partyanalyst.dto.ThirdPartyCompressionVO;
import com.itgrids.partyanalyst.dto.VerificationCompVO;
import com.itgrids.partyanalyst.model.SurveyCompletedConstituency;
import com.itgrids.partyanalyst.model.SurveyCompletedLocations;
import com.itgrids.partyanalyst.service.ISurveyCompletedDetailsService;
import com.itgrids.partyanalyst.service.ISurveyDashBoardService;
import com.itgrids.partyanalyst.service.ISurveyDataDetailsService;
import com.itgrids.partyanalyst.service.ISurveyDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;

public class SurveyCompletedDetailsService implements
		ISurveyCompletedDetailsService {
	
	 private static final Logger LOG = Logger.getLogger(SurveyCompletedDetailsService.class);
	
	@Autowired
	private ISurveyCompletedLocationsDAO surveyCompletedLocationsDAO;
	
	@Autowired
	private IBoothDAO boothDAO;
	
	@Autowired
	private ISurveyDetailsInfoDAO surveyDetailsInfoDAO;
	
	@Autowired
	private IConstituencyDAO constituencyDAO;
	
	@Autowired
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	@Autowired
	private ISurveyConstituencyDAO surveyConstituencyDAO;
	
	@Autowired
	private IWebMonitoringAssignedUsersDAO webMonitoringAssignedUsersDAO;
	
	@Autowired
	private ISurveyDashBoardService surveyDashBoardService ;
	
	@Autowired
	private ISurveyDataDetailsService surveyDataDetailsService;
	
	@Autowired
	private ISurveyDetailsService surveyDetailsService;
	
	@Autowired 
	private ISurveyCallStatusDAO surveyCallStatusDAO;
	
	@Autowired private ISurveyFinalDataDAO surveyFinalDataDAO;
	@Autowired private ISurveyWmThirdPartyStatusDAO surveyWmThirdPartyStatusDAO;
	
	@Autowired
	private ISurveyCompletedConstituencyDAO surveyCompletedConstituencyDAO;
	
	
	public List<SurveyReportVO> getSurveyCompletedLocationsDetailsForSurveyStartedConstituencies()
	{
		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();
		
		LOG.info("Entered into the getSurveyCompletedLocationsDetails service method");

		try
		{
			
			List<Long> constituencyIds = surveyDetailsInfoDAO.getSurveyStartedConstituenciesDetails();
			
			// total booths details start
			List<Object[]> boothDtls = boothDAO.getTotalBoothsCountByConstituencyIds(constituencyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
			
			Map<Long,Long> totalBoothsMap = new LinkedHashMap<Long, Long>();
			
			for(Object[] obj:boothDtls)
				totalBoothsMap.put((Long)obj[1], (Long)obj[0]);
			//total booths details end
			

			
			//completed booths details start
			
			List<Object[]> completedList = surveyCompletedLocationsDAO.getCompletedBoothsDetailsByConstituencyIds(constituencyIds);
			Map<Long,Long> completedBoothsMap = new LinkedHashMap<Long, Long>();
			
			for(Object[] obj:completedList)
				completedBoothsMap.put((Long)obj[1], (Long)obj[0]);
			
			//completed booths details end
			

			
			//processing booths details start
			
			List<Object[]> processedList = surveyDetailsInfoDAO.getStartedBoothsDetailsByConstituencyIds(constituencyIds);
			Map<Long,Long> processingBoothsMap = new LinkedHashMap<Long, Long>();
			
			for(Object[] obj:processedList)
				processingBoothsMap.put((Long)obj[1], (Long)obj[0]);
			
			//processing booths details end

			
			
			Map<Long,Long> datacollectedCountMap = new HashMap<Long, Long>();
			List<Object[]> list = surveyDetailsInfoDAO.getDataCollectedCountForConstituency(constituencyIds);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					datacollectedCountMap.put((Long)params[0],(Long) params[1]);
				}
			}

			List<Object[]> constnDtlsList = constituencyDAO.getConstituencyNameByConstituencyIdsList(constituencyIds);
			Map<Long,String> constituencyDetailsMap = new HashMap<Long, String>();

			
			for(Object[] obj:constnDtlsList)
				constituencyDetailsMap.put((Long)obj[0], obj[1].toString());
			
			List<Object[]> votersCountList = boothPublicationVoterDAO.getTotalVotersForAllConstituencies(constituencyIds);
			
			Map<Long,Long> votersCountMap = new HashMap<Long, Long>();
			
			for(Object[] obj:votersCountList)
				votersCountMap.put((Long)obj[0],(Long) obj[2]);
			
			List<Long> thitdPartyConstns = surveyDetailsInfoDAO.getThirdPartyStartedConstituencies();
			
			
			for(Long constituencyId:constituencyIds)
			{
				SurveyReportVO constituencyVO = new SurveyReportVO();
				constituencyVO.setId(constituencyId);
				constituencyVO.setName(constituencyDetailsMap.get(constituencyId));
				constituencyVO.setTotal(totalBoothsMap.get(constituencyId));
				constituencyVO.setCompletedCount(completedBoothsMap.get(constituencyId) != null ?completedBoothsMap.get(constituencyId):0L);				
				constituencyVO.setProcessingCount(processingBoothsMap.get(constituencyId) != null ?processingBoothsMap.get(constituencyId)-constituencyVO.getCompletedCount():0L);
				constituencyVO.setNotStartedCount(constituencyVO.getTotal() - (constituencyVO.getProcessingCount()+constituencyVO.getCompletedCount()));
				constituencyVO.setTotalVoters(votersCountMap.get(constituencyId));
				constituencyVO.setTotalCollectedCount(datacollectedCountMap.get(constituencyVO.getId()) != null ? datacollectedCountMap.get(constituencyVO.getId()) : 0);
				if(thitdPartyConstns != null)
				constituencyVO.setForThirdParty(thitdPartyConstns.contains(constituencyId) ? true:false);
		
				resultList.add(constituencyVO);
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getSurveyCompletedLocationsDetails service method");
		}
		return resultList;
	}
	
	/**
	 * This method will get the booths count for all statuses for a constituency
	 * @param constituencyId
	 */
	public SurveyReportVO getBoothsStatusByConstituencyId(Long constituencyId)
	{
		LOG.info("Entered into the getBoothsStatusByConstituencyIds service method");
		SurveyReportVO resultVO = new SurveyReportVO();

		try
		{
			
			//if the user is web monitor then we need to show the limited booths information
			
		//	List<Long> boothIds =webMonitoringAssignedUsersDAO.getAssignedUsersBoothsDetails(1L,constituencyId);
			
			
			List<Object[]> boothStatusDetails = surveyCompletedLocationsDAO.getBoothsStatusDetailsByConstituencyId(constituencyId);
			
			List<Long> processingIds =  surveyDetailsInfoDAO.getBoothsInProcessByConstituencyId(constituencyId);
			
			for(Object[] obj:boothStatusDetails)
			{
				
				/*if(((Long)obj[1]).equals(IConstants.DC_PROCESS_STATUS_ID))
				{
					processingIds = surveyDetailsInfoDAO.getBoothsInProcessByConstituencyId(constituencyId);
					
				}else*/ if(((Long)obj[1]).equals(IConstants.DC_COMPLETED_STATUS_ID))
				{
					resultVO.setCompletedCount((Long)obj[0]);
					
				}else if(((Long)obj[1]).equals(IConstants.WM_COMPLETED_STATUS_ID))
				{
					resultVO.setWmCompletedCount((Long)obj[0]);
					
				}else if(((Long)obj[1]).equals(IConstants.DV_PROCESS_STATUS_ID))
				{
					resultVO.setDvProcessingCount((Long)obj[0]);
					
				}else if(((Long)obj[1]).equals(IConstants.DV_COMPLETED_STATUS_ID))
				{
					resultVO.setDvCompletedCount((Long)obj[0]);
				}
				/*else if(((Long)obj[1]).equals(IConstants.TP_PROCESS_STATUS_ID))
				{
					resultVO.setThirdPartyProcessing(((Long)obj[0]));
				}*/
				else if(((Long)obj[1]).equals(IConstants.TP_COMPLETED_STATUS_ID))
				{
					resultVO.setThirdPartyCompleted((Long)obj[0]);
				}
				else if(((Long)obj[1]).equals(IConstants.TP_WM_PROCESS_STATUS_ID))
				{
					resultVO.setTpWebMonitoringProcessing((Long)obj[0]);
				}
				else if(((Long)obj[1]).equals(IConstants.TP_WM_COMPLETED_STATUS_ID))
				{
					resultVO.setTpWebMonitoringCompleted((Long)obj[0]);
				}
				else if(((Long)obj[1]).equals(IConstants.READY_FOR_REVIEW))
				{
					resultVO.setReadyForReviewCount((Long)obj[0]);
				}
			}
			
			List<Long> tdProcessingList = surveyCompletedLocationsDAO.getThirdPartyVerificationProcessingBoothsByConstituencyId(constituencyId);
			
			resultVO.setThirdPartyProcessing(new Long(tdProcessingList.size() - resultVO.getThirdPartyCompleted().intValue()) - resultVO.getTpWebMonitoringProcessing() - resultVO.getTpWebMonitoringCompleted() - resultVO.getReadyForReviewCount() );
			
			List<Long> boothIdsContainsStatus = surveyCompletedLocationsDAO.getCompletedStatusBoothsByBoothIds(processingIds);
			 
			resultVO.setProcessingCount(new Long(processingIds.size() - boothIdsContainsStatus.size()));
			
			List<Long> dvProcessBooths = surveyCompletedLocationsDAO.getVerificationProcessBoothsByConstituencyId(constituencyId);
			
			List<Long> dvCompletedBooths = surveyCompletedLocationsDAO.getCompletedBoothsDetailsByStatusIdAndConstituencyId(constituencyId,IConstants.DV_COMPLETED_STATUS_ID);
			
			dvProcessBooths.removeAll(dvCompletedBooths);
			
			resultVO.setActualProcessingCount(new Long(dvProcessBooths.size()));
			
			resultVO.setThirdpartyReady(resultVO.getDvCompletedCount() - resultVO.getThirdPartyProcessing()  - resultVO.getThirdPartyCompleted());
			
			
			
			//total booths by panchayat wise start
			
			List<Object[]> panchayatTotalBooths = boothDAO.getTotalBoothsCountForPanchayatisByConstituencyId(constituencyId);	
			
			Map<Long,Long> panchayatBoothCountMap = new HashMap<Long, Long>();
			
			
			for(Object[] obj:panchayatTotalBooths)
				panchayatBoothCountMap.put((Long)obj[1], (Long)obj[0]);
			
			//total booths by panchayat wise end
			
			//completed booths by panchayat wise start
			
			List<Object[]> completedPanchayatBoothDetails = surveyCompletedLocationsDAO.getCompletedBoothsCountForPanchayatisByConstituencyId(constituencyId);	
			
			Map<Long,Long> completedPanchayatBoothMap = new HashMap<Long, Long>();
			List<Long> panchayatIds = new ArrayList<Long>();
			panchayatIds.add(0L);
			
			for(Object[] obj:completedPanchayatBoothDetails)
			{
				completedPanchayatBoothMap.put((Long)obj[1], (Long)obj[0]);
				panchayatIds.add((Long)obj[1]);
			}
			
			//completed booths by panchayat wise end
			
			List<Long> processingPanchayatIds = surveyCompletedLocationsDAO.getProsessingBoothsCountForPanchayatisByConstituencyId(constituencyId,panchayatIds);
			
			SurveyReportVO panchayatCompletedDetails = new SurveyReportVO();
			
			//Here we are iterating  panchayat wise completed booth count map,and checking booths count in panchayat wise total count map.
			//If both are equal then the panchayat is completed.If no of booths in  completedPanchayatBoothMap count is less than 
			//panchayatBoothCountMap then that panchayat is in processing
			
			for(Entry<Long,Long> entry:completedPanchayatBoothMap.entrySet())
			{
				Long total = panchayatBoothCountMap.get(entry.getKey());
				
				if(entry.getValue().equals(total))
					panchayatCompletedDetails.setCount(panchayatCompletedDetails.getCount() +1L);
				else if(entry.getValue() < total)
					panchayatCompletedDetails.setProcessingCount(panchayatCompletedDetails.getProcessingCount() +1L);
				
			}
			
			panchayatCompletedDetails.setProcessingCount(panchayatCompletedDetails.getProcessingCount()+processingPanchayatIds.size());
			
			resultVO.setPanchayatDetails(panchayatCompletedDetails);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getBoothsStatusByConstituencyIds service method");
		}
		
		return resultVO;
	}
	
	/**
	 * This method will save the status details of a location i.e booth or constituency
	 * @param locationType, this may be booth or constituency
	 * @param locationValue,id of the location
	 * @param statusId , status of the booth i.e DC processing,DC completed,WM completed,DV processing,DV completed
	 */
	public String saveBoothStatusDetails(Long locationValue,Long statusId,String locationType)
	{
		LOG.info("Entered into the saveBoothStatusDetails service method");

		try
		{
			Long locationScopeId = 0L;
			
			if(locationType.equalsIgnoreCase("booth"))
				locationScopeId = 9L;
			
			List<Long> thirdPartySCopesList = new ArrayList<Long>();
			
			thirdPartySCopesList.add(IConstants.TP_PROCESS_STATUS_ID);
			thirdPartySCopesList.add(IConstants.TP_COMPLETED_STATUS_ID);
			thirdPartySCopesList.add(IConstants.TP_WM_PROCESS_STATUS_ID);
			thirdPartySCopesList.add(IConstants.TP_WM_COMPLETED_STATUS_ID);
			thirdPartySCopesList.add(IConstants.READY_FOR_REVIEW);
			
			if (!thirdPartySCopesList.contains(statusId))			{
				// First we are removing all the previous records rekated to that location
				surveyCompletedLocationsDAO.deleteSurveyCompletedDetailsByLocationValueAndScope(locationValue,locationScopeId);
			}else
			{
				
				surveyCompletedLocationsDAO.deleteSurveyCompletedDetailsByLocationValueAndScopeForThirdParty(locationValue,locationScopeId,thirdPartySCopesList);
			}
			
			if(!statusId.equals(IConstants.DC_PROCESS_STATUS_ID) && !statusId.equals(IConstants.TP_READY_STATUS_ID) &&!statusId.equals(IConstants.TP_PROCESS_STATUS_ID) )
			{
			
				SurveyCompletedLocations surveyCompletedLocationDetails = new SurveyCompletedLocations();
				
				surveyCompletedLocationDetails.setLocationValue(locationValue);
				surveyCompletedLocationDetails.setLocationScopeId(locationScopeId);
				
				/*if(statusId.equals(IConstants.TP_READY_STATUS_ID))
					surveyCompletedLocationDetails.setStatusId(IConstants.DV_COMPLETED_STATUS_ID);
				else*/
					surveyCompletedLocationDetails.setStatusId(statusId);
				
				surveyCompletedLocationsDAO.save(surveyCompletedLocationDetails);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in saveBoothStatusDetails service method");
		}
		return "success";
	}
	

	public SurveyDashBoardVO getCompletdConstituenciesDetails()
	{
		LOG.debug("entered into getCompletdConstituenciesDetails() in surveyCompletedDetails() service .");
		
		SurveyDashBoardVO resultVO = new SurveyDashBoardVO();

		try {
			
			List<Long> surveyConstituencyList = new ArrayList<Long>(0);
			
			List<Object[]> surveyConstituencyDetails = surveyConstituencyDAO.getSurveyConstituencies();
			
			if(surveyConstituencyDetails != null && surveyConstituencyDetails.size()>0)
			{
				for (Object[] surveyConstituency : surveyConstituencyDetails)
				{
					if(surveyConstituency[0] != null)
					{						
						surveyConstituencyList.add((Long)surveyConstituency[0]);
					}
				}
			}
			
			
			List<Object[]> districtWiseContiList = constituencyDAO.getDistictWiseConstituencyListByConstiIds(surveyConstituencyList);	
			List<SurveyDashBoardVO> locationsLsit = new ArrayList<SurveyDashBoardVO>();
			
			if(districtWiseContiList != null && districtWiseContiList.size()>0){
				for (Object[] param : districtWiseContiList) {
					SurveyDashBoardVO vo = new SurveyDashBoardVO();
					
					vo.setConstituencyId(param[0] != null ? (Long) param[0]:0L);
					vo.setName(param[1] != null ? param[1].toString():"");
					
					vo.setLocationId(param[2] != null ? (Long) param[2]:0L);
					vo.setLocationName(param[3] != null ? param[3].toString():"");
					
					locationsLsit.add(vo);
				}
			}
			
			Map<Long,Long> constiCount = new HashMap<Long,Long>(0);
			Map<Long,Long> constiCompletCount = new HashMap<Long,Long>(0);
			Map<Long,Long> processCompletCount = new HashMap<Long,Long>(0);
			
		    List<Object[]> constituencyInfo = surveyCompletedLocationsDAO.getSurveyCompletedLocations();
			List<Object[]> processconstituencyInfo = surveyDetailsInfoDAO.getProcessingConstituencyes();
			if(processconstituencyInfo != null && processconstituencyInfo.size()>0){
				for (Object[] locationInfo : processconstituencyInfo) {
					
					if(locationInfo[0] != null && locationInfo[2] != null)
						processCompletCount.put((Long) locationInfo[0], (Long) locationInfo[2]);
				}
			}
			
			
			if(constituencyInfo != null && constituencyInfo.size()>0){
				for (Object[] locationInfo : constituencyInfo) {
					
					if(locationInfo[0] != null && locationInfo[2] != null)
						constiCompletCount.put((Long) locationInfo[0], (Long) locationInfo[2]);
				}
			}
			List<Object[]> constiBoothCount =  boothDAO.getBoothCountInfoByConstiIds(surveyConstituencyList);
			
			if(constiBoothCount != null && constiBoothCount.size()>0){
				for (Object[] locationInf : constiBoothCount) {
					
					if(locationInf[1] != null && locationInf[0] != null)
						constiCount.put((Long) locationInf[1], (Long) locationInf[0]);
				}
			}
			
			List<SurveyDashBoardVO> compltedConstiList = new ArrayList<SurveyDashBoardVO>();
			List<SurveyDashBoardVO> processingConstiList = new ArrayList<SurveyDashBoardVO>();
			List<SurveyDashBoardVO> startedConstiList = new ArrayList<SurveyDashBoardVO>();
			List<SurveyDashBoardVO> notYetStartedConstiList = new ArrayList<SurveyDashBoardVO>();
			
			if(constiCount != null && constiCount.size()>0)
			{
				
				for (Long constituencyId : constiCount.keySet()) 
				{
					
					Long boothsCount = constiCount.get(constituencyId);
					Long boothCmpletdCount = constiCompletCount.get(constituencyId);
					Long boothsProcessing = processCompletCount.get(constituencyId);
					SurveyDashBoardVO constituencyVO = getMatchedDashBoardVOByConstituencyId(locationsLsit,constituencyId);

					if(boothCmpletdCount != null && boothCmpletdCount.longValue() > 0L)
					{						
							if(boothsCount.longValue() == boothCmpletdCount.longValue())
							{
								compltedConstiList.add(constituencyVO);
							}
							else if(boothsCount.longValue() > boothCmpletdCount.longValue())
							{
								processingConstiList.add(constituencyVO);
							}
					}
					else if(boothsProcessing != null && boothsProcessing.longValue() > 0l )
					{
						processingConstiList.add(constituencyVO);
					}
					else
					{							
						notYetStartedConstiList.add(constituencyVO);						
					}
				}
			}
			
			
			startedConstiList.addAll(processingConstiList); // started means which are in process state as well completed state;
			startedConstiList.addAll(compltedConstiList);
			
			
			resultVO.setProcessingCount(processingConstiList.size());			
			resultVO.setCompletedCount(compltedConstiList.size());			
			resultVO.setStartedCount(startedConstiList.size());			
			resultVO.setNotStartedCount(notYetStartedConstiList.size());	
			
			List<Long> completedDistrictList = new ArrayList<Long>(0);
			
			if(compltedConstiList != null && compltedConstiList.size()>0)
			{
				for (SurveyDashBoardVO dashBoardVO : compltedConstiList) 
				{
	
					if(!completedDistrictList.contains(dashBoardVO.getLocationId()))
					{
						completedDistrictList.add(dashBoardVO.getLocationId());
						
						SurveyDashBoardVO districtVO = new SurveyDashBoardVO();
						
						districtVO.setLocationId(dashBoardVO.getLocationId());
						districtVO.setLocationName(dashBoardVO.getLocationName());
						
						resultVO.getCompleted().add(districtVO);
					}
				}
			}
			
			
			List<Long> processingDistrictList = new ArrayList<Long>(0); 
			if(processingConstiList != null && processingConstiList.size()>0)
			{
				for (SurveyDashBoardVO dashBoardVO : processingConstiList) 
				{

					if(!completedDistrictList.contains(dashBoardVO.getLocationId()) && !processingDistrictList.contains(dashBoardVO.getLocationId()))
					{
						processingDistrictList.add( dashBoardVO.getLocationId());
						
						SurveyDashBoardVO districtVO = new SurveyDashBoardVO();
						
						districtVO.setLocationId(dashBoardVO.getLocationId());
						districtVO.setLocationName(dashBoardVO.getLocationName());
						
						resultVO.getProcess().add(districtVO);
					}
				}
			}
			
			List<Long> startedDistrictList = new ArrayList<Long>(0); 
			if(startedConstiList != null && startedConstiList.size()>0)
			{
				for (SurveyDashBoardVO dashBoardVO : startedConstiList) 
				{

					if(!completedDistrictList.contains(dashBoardVO.getLocationId()) && !startedDistrictList.contains(dashBoardVO.getLocationId()))
					{
						startedDistrictList.add(dashBoardVO.getLocationId());
						
						SurveyDashBoardVO districtVO = new SurveyDashBoardVO();
						
						districtVO.setLocationId(dashBoardVO.getLocationId());
						districtVO.setLocationName(dashBoardVO.getLocationName());
						
						resultVO.getStarted().add(districtVO);
					}
				}
			}
			
			List<Long> notYetStartedDistrictList = new ArrayList<Long>(0); 
			
			if(notYetStartedConstiList != null && notYetStartedConstiList.size()>0)
			{
				for (SurveyDashBoardVO dashBoardVO : notYetStartedConstiList) 
				{
					
					if(!completedDistrictList.contains(dashBoardVO.getLocationId()) && !notYetStartedDistrictList.contains(dashBoardVO.getLocationId()) &&
							!startedDistrictList.contains(dashBoardVO.getLocationId()) && !processingDistrictList.contains(dashBoardVO.getLocationId())  )
					{
						notYetStartedDistrictList.add(dashBoardVO.getLocationId());
						
						SurveyDashBoardVO districtVO = new SurveyDashBoardVO();
						
						districtVO.setLocationId(dashBoardVO.getLocationId());
						districtVO.setLocationName(dashBoardVO.getLocationName());
						
						resultVO.getNotStarted().add(districtVO);
					}
				}
			}
			
			
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("Exception raised in  getCompletdConstituenciesDetails() in surveyCompletedDetails() service .",e);
		}
		
		return resultVO;
	}
	
	private SurveyDashBoardVO getMatchedDashBoardVOByConstituencyId(List<SurveyDashBoardVO> resultList,Long ConstituencyId)
	{
		for(SurveyDashBoardVO resultVO:resultList)
			if(resultVO.getConstituencyId().equals(ConstituencyId))
				return resultVO;
		return null;
		
	}
	
	public List<FinalSurveyReportVO> finalDeselectionReport(Long constituencyId)
	{
		List<FinalSurveyReportVO> resultList = null;
		try 
		{
			Map<Long,String> boothPartNoMap = null;
			Map<Long,Long> boothWiseTotalMap = null;
			Map<Long,List<FinalSurveyReportVO>>  dcResultMap = null;
			//Map<Long,List<FinalSurveyReportVO>>  dvResultMap = null;
			
			Map<Long,FinalSurveyReportVO>  dvMatchedUnMatchedMap = null;
			Map<Long,GenericVO> wmDvCollectedMap = null;
			List<Object[]> boothWiseTotalVoters = boothPublicationVoterDAO.getTotalVotersForConstituencyByBoothWise(constituencyId);
			if(boothWiseTotalVoters != null && boothWiseTotalVoters.size() > 0)
			{
				boothPartNoMap = new HashMap<Long, String>();
				boothWiseTotalMap = new HashMap<Long, Long>();
				for (Object[] parms : boothWiseTotalVoters) 
				{
					if(parms[0] != null)
					{
						String parnNo = boothPartNoMap.get((Long)parms[0]);
						if(parnNo == null)
						{
							boothPartNoMap.put((Long)parms[0], parms[1].toString());
							boothWiseTotalMap.put((Long)parms[0], (Long)parms[2]);
						}
					}
					
				}
				dcResultMap = new HashMap<Long, List<FinalSurveyReportVO>>();
				getDcOrDvAndDcWmOrDvWmDetails(constituencyId,dcResultMap,1l);
				
				
				//dvResultMap = new HashMap<Long, List<FinalSurveyReportVO>>();
				//getDcOrDvAndDcWmOrDvWmDetails(constituencyId,dvResultMap,4l);
				
				dvMatchedUnMatchedMap = new HashMap<Long, FinalSurveyReportVO>();
				getVerifierMatchedUnMatchedDetails(constituencyId,dvMatchedUnMatchedMap);
				
				wmDvCollectedMap = new HashMap<Long, GenericVO>();
				getWmDvVerifiedDetailsByBoothWise(constituencyId,wmDvCollectedMap);
				
				if(boothPartNoMap != null && boothPartNoMap.size() > 0)
				{
					resultList = new ArrayList<FinalSurveyReportVO>();
					for(Long boothId : boothPartNoMap.keySet())
					{
						FinalSurveyReportVO finalSurveyReportVO = new FinalSurveyReportVO();
						finalSurveyReportVO.setBoothId(boothId);
						finalSurveyReportVO.setPartNo(boothPartNoMap.get(boothId));
						finalSurveyReportVO.setTotalVoters(boothWiseTotalMap.get(boothId));
						List<FinalSurveyReportVO> dcCollectedDetails = dcResultMap.get(boothId);
						if(dcCollectedDetails != null && dcCollectedDetails.size() > 0)
						for (FinalSurveyReportVO finalSurveyReport : dcCollectedDetails)
						{
							finalSurveyReportVO.setWmDcTotal(finalSurveyReport.getWmDcTotal());
							finalSurveyReportVO.setDcCasteMapped(finalSurveyReport.getDcCasteMapped());
							finalSurveyReportVO.setDcHamletMapped(finalSurveyReport.getDcHamletMapped());
							finalSurveyReportVO.setDcMobileMapped(finalSurveyReport.getDcMobileMapped());
							
							finalSurveyReportVO.setWmDcMobileMapped(finalSurveyReport.getWmDcMobileMapped());
							finalSurveyReportVO.setWmDcMobileMappedError(finalSurveyReport.getWmDcMobileMappedError());
							finalSurveyReportVO.setWmDcMobileMappedPerc(finalSurveyReport.getWmDcMobileMappedPerc());
							
							finalSurveyReportVO.setWmDcMobileUnMapped(finalSurveyReport.getWmDcMobileUnMapped());
							finalSurveyReportVO.setWmDcMobileUnMappedError(finalSurveyReport.getWmDcMobileUnMappedError());
							finalSurveyReportVO.setWmDcMobileUnMappedPerc(finalSurveyReport.getWmDcMobileUnMappedPerc());

							finalSurveyReportVO.setWmDcCasteMapped(finalSurveyReport.getWmDcCasteMapped());
							finalSurveyReportVO.setWmDcCasteMappedError(finalSurveyReport.getWmDcCasteMappedError());
							finalSurveyReportVO.setWmDcCasteMappedPerc(finalSurveyReport.getWmDcCasteMappedPerc());
							
							finalSurveyReportVO.setWmDcCasteUnMapped(finalSurveyReport.getWmDcCasteUnMapped());
							finalSurveyReportVO.setWmDcCasteUnMappedError(finalSurveyReport.getWmDcCasteUnMappedError());
							finalSurveyReportVO.setWmDcCasteUnMappedPerc(finalSurveyReport.getWmDcCasteUnMappedPerc());
						}
						
						FinalSurveyReportVO matchedUnMatchedVO = dvMatchedUnMatchedMap.get(boothId);
						if(matchedUnMatchedVO != null)
						{
							finalSurveyReportVO.setCollectedCount(matchedUnMatchedVO.getCollectedCount());
							finalSurveyReportVO.setUpdatedCount(matchedUnMatchedVO.getUpdatedCount());
							finalSurveyReportVO.setVerifiedCount(matchedUnMatchedVO.getVerifiedCount());
							
							finalSurveyReportVO.setMatchedCount(matchedUnMatchedVO.getMatchedCount());
							finalSurveyReportVO.setUnMatchedCount(matchedUnMatchedVO.getUnMatchedCount());
							finalSurveyReportVO.setNotIdentifedCount(matchedUnMatchedVO.getNotIdentifedCount());
						}
						GenericVO wmDvVO = wmDvCollectedMap.get(boothId);
						if(wmDvVO != null)
						{
							finalSurveyReportVO.setWmDvY(wmDvVO.getCount() != null ? wmDvVO.getCount() : 0l);
							finalSurveyReportVO.setWmDvN(wmDvVO.getId()  != null ? wmDvVO.getId() : 0l);
							if(finalSurveyReportVO.getUnMatchedCount()  != null && finalSurveyReportVO.getWmDvY() != null && finalSurveyReportVO.getWmDvN() != null)
							{
								finalSurveyReportVO.setWmDvEmpty(finalSurveyReportVO.getUnMatchedCount().longValue() - (finalSurveyReportVO.getWmDvY().longValue()+ finalSurveyReportVO.getWmDvN().longValue()));
							}
							
							
						}
						if(finalSurveyReportVO.getWmDvEmpty() == null)
						{
							finalSurveyReportVO.setWmDvEmpty(0l);
						}
						resultList.add(finalSurveyReportVO);
					}
				}
				
			}
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in  finalDeselectionReport() in surveyCompletedDetails() service .",e);
		}
		return resultList;
	}
	
	public void getWmDvVerifiedDetailsByBoothWise(Long constituencyId,Map<Long,GenericVO> wmDvCollectedMap)
	{
		List<Object[]> wmDvVeriferDetails = surveyCallStatusDAO.getWmDvMappedUnMappedDetailsBoothWise(constituencyId);
		if(wmDvVeriferDetails != null && wmDvVeriferDetails.size() > 0)
		{
			for (Object[] objects : wmDvVeriferDetails)
			{
				if(objects[0] != null)
				{
					GenericVO VO = wmDvCollectedMap.get((Long)objects[0]);
					if(VO == null)
					{
						VO = new GenericVO();
						wmDvCollectedMap.put((Long)objects[0], VO);
					}
					if(objects[1] != null)
					{
						if(objects[1].toString().equalsIgnoreCase("Y"))
						{
							VO.setCount((Long)objects[2]);// WM-DV Y Count
						}
						else
						{
							VO.setId((Long)objects[2]);// WM-DV N Count
						}
					}
				}
					
			}
		}
	}
	public void getVerifierMatchedUnMatchedDetails(Long constituencyId,Map<Long,FinalSurveyReportVO> dvMatchedUnMatchedMap)
	{

		List<Long> boothIds = null;
		List<Object[]> boothsList = surveyDetailsInfoDAO.getBooths(constituencyId,4l);
		if(boothsList != null && boothsList.size() > 0)
		{
			boothIds = new ArrayList<Long>();
			for (Object[] objects : boothsList)
			{
				if(objects[0] != null)
				boothIds.add((Long)objects[0]);
			}
		}
		
		if(boothIds != null && boothIds.size() > 0)
		{
			List<VerificationCompVO> dvVerifiersDetails = surveyDetailsService.checkForVerifierDataForWM(boothIds);
			if(dvVerifiersDetails != null && dvVerifiersDetails.size() > 0)
			{
				for (VerificationCompVO verificationCompVO : dvVerifiersDetails) 
				{
					FinalSurveyReportVO matchedUnMatcedVO = dvMatchedUnMatchedMap.get(verificationCompVO.getBoothId());
					if(matchedUnMatcedVO == null)
					{
						matchedUnMatcedVO = new FinalSurveyReportVO();
						dvMatchedUnMatchedMap.put(verificationCompVO.getBoothId(), matchedUnMatcedVO);
					}
					matchedUnMatcedVO.setCollectedCount(verificationCompVO.getCollectedCount());
					matchedUnMatcedVO.setUpdatedCount(verificationCompVO.getUpdatedCount());
					matchedUnMatcedVO.setVerifiedCount(verificationCompVO.getVerifieCount());
					
					matchedUnMatcedVO.setMatchedCount(verificationCompVO.getMatchedCount());
					matchedUnMatcedVO.setUnMatchedCount(verificationCompVO.getUnMatchedCount());
					matchedUnMatcedVO.setNotIdentifedCount(verificationCompVO.getNotIdentifedCount());
				}
			}
		}
	}
	
	/**
	 * This Method is used for get DC OR DV And WM-DC OR WM_DV Details
	 * @param constituencyId
	 * @param dcResultMap
	 */
	public void getDcOrDvAndDcWmOrDvWmDetails(Long constituencyId,Map<Long,List<FinalSurveyReportVO>>  dcResultMap,Long surveyUserType)
	{
		String fromDate = null;
		String toDate = null;
		List<String> surveyDates = surveyDashBoardService.getCasteCollecteddatesByConstituencyId(constituencyId);
		if(surveyDates != null && surveyDates.size() > 0)
		{
			fromDate = surveyDates.get(0);
			toDate = surveyDates.get(surveyDates.size()-1);
		}
		
		if(fromDate != null && toDate != null)
		{
			List<SurveyReportVO> dcDetails = surveyDataDetailsService.getSurveyDetailsForConstituency(constituencyId, surveyUserType, fromDate, null, toDate);
			if(dcDetails != null && dcDetails.size() > 0)
			{
				for (SurveyReportVO surveyReportVO : dcDetails) 
				{
					if(surveyReportVO.getSubList() != null && surveyReportVO.getSubList().size() > 0)
					{
						List<SurveyReportVO> dcWIseDetailsList = surveyReportVO.getSubList() ;
						if(dcWIseDetailsList != null && dcWIseDetailsList.size() > 0)
						{
							for (SurveyReportVO surveyReportVO2 : dcWIseDetailsList) 
							{
								List<FinalSurveyReportVO> dcMainVOList = dcResultMap.get(surveyReportVO2.getBoothId());
								if(dcMainVOList == null)
								{
									dcMainVOList = new ArrayList<FinalSurveyReportVO>();
									dcResultMap.put(surveyReportVO2.getBoothId(), dcMainVOList);
								}
								FinalSurveyReportVO dcMainVO = new FinalSurveyReportVO();
								dcMainVO.setDcCasteMapped(surveyReportVO2.getCasteCount());
								dcMainVO.setDcHamletMapped(surveyReportVO2.getHamletCount());
								dcMainVO.setDcMobileMapped(surveyReportVO2.getMobileNoCount());
								
								dcMainVO.setWmDcTotal(surveyReportVO2.getCount());
								
								dcMainVO.setWmDcCasteMapped(surveyReportVO2.getCasteMatchedCount());
								//dcMainVO.setWmDcCasteMappedPerc(new BigDecimal(surveyReportVO2.getCasteMatchedCount()*(100.0)/surveyReportVO2.getTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								
								dcMainVO.setWmDcCasteUnMapped(surveyReportVO2.getCasteNotMatchedCount());
								//dcMainVO.setWmDcCasteUnMappedPerc(new BigDecimal(surveyReportVO2.getCasteNotMatchedCount()*(100.0)/surveyReportVO2.getTotal()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								
								//dcMainVO.setWmDcCasteMappedError(new BigDecimal(surveyReportVO2.getCasteNotMatchedCount()*(100.0)/(surveyReportVO2.getCasteNotMatchedCount().longValue() + surveyReportVO2.getCasteMatchedCount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								
								dcMainVO.setWmDcMobileMapped(surveyReportVO2.getMobileMatchedCount());							
								dcMainVO.setWmDcMobileUnMapped(surveyReportVO2.getMobileNotMatchedCount());
								//dcMainVO.setWmDcMobileMappedError(new BigDecimal(surveyReportVO2.getMobileNotMatchedCount()*(100.0)/(surveyReportVO2.getMobileNotMatchedCount().longValue() + surveyReportVO2.getMobileMatchedCount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								
								dcMainVOList.add(dcMainVO);
							}
						}
						
					}
					
				}
			}
		}
	}
	
	
	public List<SurveyThirdPartyReportVO> finalReportWithThirdParty(Long constituencyId){
		LOG.debug("In finalReportWithThirdParty");
		List<SurveyThirdPartyReportVO> resultList = new ArrayList<SurveyThirdPartyReportVO>();
		try 
		{
			Map<Long,String> boothPartNoMap = null;
			Map<Long,Long> boothWiseTotalMap = null;
			Map<Long,String> boothsTypeMap = null;
			
			//GETTING TOTALVOTERS AND PARTNO'S OF BOOTHS IN CONSTIUTENY
			List<Object[]> boothWiseTotalVoters = boothPublicationVoterDAO.getTotalVotersAndBoothTypeForConstituencyByBoothWise(constituencyId);
			if(boothWiseTotalVoters != null && boothWiseTotalVoters.size() > 0){
				boothPartNoMap = new HashMap<Long, String>();
				boothWiseTotalMap = new HashMap<Long, Long>();
				boothsTypeMap = new HashMap<Long, String>();
				for (Object[] parms : boothWiseTotalVoters) {
					if(parms[0] != null){
						String parnNo = boothPartNoMap.get((Long)parms[0]);
						if(parnNo == null){
							boothPartNoMap.put((Long)parms[0], parms[1].toString());
							boothWiseTotalMap.put((Long)parms[0], (Long)parms[2]);
							boothsTypeMap.put((Long)parms[0], "-");
							if(parms[3]!=null){
								boothsTypeMap.put((Long)parms[0], "RURAL");
							}
							if(parms[4]!=null){
								boothsTypeMap.put((Long)parms[0], "URBAN");
							}
							
							
						}
					}
					
				}
			}
			
			
			//STATUS TYPES
			List<Object[]> statusList = surveyWmThirdPartyStatusDAO.getStatusTypes();
			
			// BOOTH WISE USERS SAMPLE COUNT
			List<SurveyThirdPartyReportVO> usersSamples = new ArrayList<SurveyThirdPartyReportVO>();
			//List<SurveyThirdPartyReportVO> users = new ArrayList<SurveyThirdPartyReportVO>();
			List<Object[]> thirdPartySamples = surveyDetailsInfoDAO.getTotalSamplesInBoothsOfUserType(constituencyId, 10l);
			if(thirdPartySamples != null && thirdPartySamples.size() > 0){
				for(Object[] obj:thirdPartySamples){
					boolean isNew = false;
					SurveyThirdPartyReportVO sv = getMatchedBooth(usersSamples, Long.valueOf(obj[0].toString()));
					if(sv==null){
						isNew = true;
						sv = new SurveyThirdPartyReportVO();
						sv.setBoothId(Long.valueOf(obj[0].toString()));
					}
					
					List<SurveyThirdPartyReportVO> usersList = sv.getUsersList();
					if(usersList==null){
						usersList = new ArrayList<SurveyThirdPartyReportVO>();
					}
					
					SurveyThirdPartyReportVO usr = getMatchedUser(usersList,Long.valueOf(obj[3].toString()));
					
					SurveyThirdPartyReportVO user = new SurveyThirdPartyReportVO();
					user.setUserCollected(Long.valueOf(obj[2].toString()));
					user.setUserName(obj[4].toString());
					user.setUserId(Long.valueOf(obj[3].toString()));
					user.setMobileNo(obj[5].toString());
					user = setStatusTypesForBoothsList(statusList,user);
					
					usersList.add(user);
					sv.setUsersList(usersList);
					
					if(isNew){
						usersSamples.add(sv);
					}
					
					
				}
			}
			
			
			//	GETTING THIRD PARTY USERS INVOLVED BOOTHS
			List<Object[]> boothsList = surveyDetailsInfoDAO.getBooths(constituencyId, 10l);
			List<Long> boothIds = new ArrayList<Long>();
			
			if(boothsList!=null && boothsList.size()>0){
				for(Object[] temp:boothsList){
					boothIds.add(Long.valueOf(temp[0].toString()));
				} 
			}
			
			
			
			
			if(boothIds!=null && boothIds.size()>0){
				for(Long boothId:boothIds){
					SurveyThirdPartyReportVO boothVO = getMatchedBooth(resultList,boothId);
					if(boothVO==null){
						boothVO = new SurveyThirdPartyReportVO();
						boothVO.setBoothId(boothId);
						if(boothPartNoMap!=null){
							boothVO.setPartNo(boothPartNoMap.get(boothId));
						}
						if(boothWiseTotalMap!=null){
							boothVO.setTotalVoters(boothWiseTotalMap.get(boothId));
						}
						if(boothsTypeMap!=null){
							boothVO.setBoothType(boothsTypeMap.get(boothId));
						}
						SurveyThirdPartyReportVO usrsList = getMatchedUsersList(usersSamples,boothId);
						boothVO.setUsers(usrsList);
						//boothVO.setUserCollected(boothWiseTPCounts.get(boothId));
						boothVO = setStatusTypesForBoothsList(statusList,boothVO);
					}
					
					resultList.add(boothVO);
				}
			}
			
			
			List<Object[]> statusCountsList = surveyFinalDataDAO.getThirdPartyStatusWithBooths(boothIds);
			if(statusCountsList!=null && statusCountsList.size()>0){
				for(Object[] obj:statusCountsList){
					SurveyThirdPartyReportVO boothVO = getMatchedBooth(resultList, Long.valueOf(obj[0].toString()));
					if(boothVO!=null){
						
						SurveyThirdPartyReportVO usersClcted = boothVO.getUsers();
						if(usersClcted!=null){
							List<SurveyThirdPartyReportVO> tempList = usersClcted.getUsersList();
							if(tempList!=null && tempList.size()>0){
								for(SurveyThirdPartyReportVO sv:tempList){
									
									sv.setBoothId(boothVO.getBoothId());
									sv.setTotalVoters(boothVO.getTotalVoters());
									sv.setPartNo(boothVO.getPartNo());
									
									SurveyThirdPartyReportVO statusVO = getMatchedThirdPartyStatusVO(sv.getStatusList(), Long.valueOf(obj[1].toString()));
									if(statusVO!=null){
										statusVO.setStatusCount(Long.valueOf(obj[2].toString()));
										statusVO.setStatusPercentage(statusVO.getStatusCount() != null &&statusVO.getStatusCount() !=0 ? roundTo2DigitsFloatValue((float) statusVO.getStatusCount() * 100f / sv.getUserCollected())
												: "0.00");
									}
								}
							}
						}
						
						
					}
				}
			}
			
			
		}catch (Exception e) {
			LOG.error("Exception Raised In finalReportWithThirdParty"+e);
		}
		return resultList;
	}
	
	
	public SurveyThirdPartyReportVO getMatchedBooth(List<SurveyThirdPartyReportVO> list,Long boothId){
		if(list!=null && list.size()>0 && boothId!=null){
			for(SurveyThirdPartyReportVO sv:list){
				if(sv.getBoothId().equals(boothId)){
					return sv;
				}
			}
		}
		return null;
	}
	
	public SurveyThirdPartyReportVO getMatchedThirdPartyStatusVO(List<SurveyThirdPartyReportVO> list,Long statusId){
		if(list!=null && list.size()>0 && statusId!=null){
			for(SurveyThirdPartyReportVO sv:list){
				if(sv.getStatusId().equals(statusId)){
					return sv;
				}
			}
		}
		return null;
	}
	
	public SurveyThirdPartyReportVO setStatusTypesForBoothsList(List<Object[]> statusList,SurveyThirdPartyReportVO boothVO){
		
		List<SurveyThirdPartyReportVO> statsLst= new ArrayList<SurveyThirdPartyReportVO>();
		if(statusList!=null && statusList.size()>0){
			for(Object[] obj:statusList){
				SurveyThirdPartyReportVO sv = new SurveyThirdPartyReportVO();
				sv.setStatusId(Long.valueOf(obj[0].toString()));
				sv.setStatusName(obj[1].toString());
				sv.setStatusCount(0l);
				sv.setStatusPercentage("0.0");
				statsLst.add(sv);
			}
		}
		
		boothVO.setStatusList(statsLst);
		return boothVO;
	}
	
	public String roundTo2DigitsFloatValue(Float number){
		  
		  LOG.debug("Entered into the roundTo2DigitsFloatValue service method");
		  
		  String result = "";
		  try
		  {
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e)
		  {
			  LOG.error("Exception raised in roundTo2DigitsFloatValue service method");
			  e.printStackTrace();
		  }
		  return result;
	  }
	
	public SurveyThirdPartyReportVO getMatchedUsersList(List<SurveyThirdPartyReportVO> list, Long boothId){
		if(list!=null && list.size()>0 && boothId!=null){
			for(SurveyThirdPartyReportVO sv:list){
				if(sv.getBoothId().equals(boothId)){
					return sv;
				}
			}
		}
		return null;
	}
	
	public SurveyThirdPartyReportVO getMatchedUser(List<SurveyThirdPartyReportVO> list, Long userId){
		if(list!=null && list.size()>0 && userId!=null){
			for(SurveyThirdPartyReportVO sv:list){
				if(sv.getUserId().equals(userId)){
					return sv;
				}
			}
		}
		return null;
	}
	
	public SurveyThirdPartyReportVO  getTPCompleteBoothsDetails(Long constituencyId,List<SurveyThirdPartyReportVO> thirdPartyList){
		LOG.debug("In getTPCompleteBoothsDetails");
		
		List<Long> boothForReady = null;
		SurveyThirdPartyReportVO finalVO = new SurveyThirdPartyReportVO();
		List<SurveyThirdPartyReportVO> finalList = new ArrayList<SurveyThirdPartyReportVO>();
		
		
		List<SurveyThirdPartyReportVO> bthTypeList = new ArrayList<SurveyThirdPartyReportVO>();
		
		
		
		
		try{
		
		
		//GETTING BOOTH IDS WITH READY FOR REVIEW FROM SURVEY COMPLETED LOCATIONS
		if(constituencyId!=null  && thirdPartyList != null && thirdPartyList.size()>0){
			boothForReady = surveyCompletedLocationsDAO.getBoothsOfTPWithStatus(constituencyId, IConstants.READY_FOR_REVIEW);
		}
		
		//PROCESSING THIRD PARTY RESULT WITH BOOTHS FROM SURVEY COMPLETED LOCATIONS
		for(SurveyThirdPartyReportVO sv:thirdPartyList){
			if(boothForReady.contains(sv.getBoothId())){
				finalList.add(sv);
			}
		}
		
		SurveyThirdPartyReportVO stprVO = null;
		List<Object[]> statusList = surveyWmThirdPartyStatusDAO.getStatusTypes();
		
		stprVO = new SurveyThirdPartyReportVO();
		stprVO.setBoothType("ALL");
		stprVO = setStatusTypesForBoothsList(statusList,stprVO);
		//stprVO.setStatusList(finalList.get(0).getStatusList());
		bthTypeList.add(stprVO);
		
		stprVO = new SurveyThirdPartyReportVO();
		stprVO.setBoothType("RURAL");
		stprVO = setStatusTypesForBoothsList(statusList,stprVO);
		//stprVO.setStatusList(finalList.get(0).getStatusList());
		bthTypeList.add(stprVO);
		
		stprVO = new SurveyThirdPartyReportVO();
		stprVO.setBoothType("URBAN");
		stprVO = setStatusTypesForBoothsList(statusList,stprVO);
		//stprVO.setStatusList(finalList.get(0).getStatusList());
		bthTypeList.add(stprVO);
		
		finalVO.setBoothTypeSummaryList(bthTypeList);
		
		if(finalList!=null && finalList.size()>0){
		finalVO.setFinalList(finalList);
		finalVO.setStatusList(finalList.get(0).getStatusList());
			//PROCESSING FINAL LIST FOR SUMMARY TABLE
				SurveyThirdPartyReportVO allVO = getMatchedBoothType(bthTypeList,"ALL");
				
				if(allVO == null){
					allVO = new SurveyThirdPartyReportVO();
				}
				
				allVO.setUserCollected(0l);
				allVO.setTotalVoters(0l);
				
			for(SurveyThirdPartyReportVO sv:finalList){
				SurveyThirdPartyReportVO temp = getMatchedBoothType(bthTypeList,sv.getBoothType());
				
				if(temp.getFinalList()==null){
					List<SurveyThirdPartyReportVO> fnlList = new ArrayList<SurveyThirdPartyReportVO>();
					temp.setFinalList(fnlList);
				}
				if(allVO.getFinalList()==null){
					List<SurveyThirdPartyReportVO> fnlList = new ArrayList<SurveyThirdPartyReportVO>();
					allVO.setFinalList(fnlList);
				}
				
				temp.getFinalList().add(sv);
				allVO.getFinalList().add(sv);
				
				if(temp==null){
					temp = new SurveyThirdPartyReportVO();
				}
				if(temp.getTotalVoters()==null){
					temp.setTotalVoters(0l);
				}
				
					temp.setTotalVoters(temp.getTotalVoters()+sv.getTotalVoters());
					allVO.setTotalVoters(allVO.getTotalVoters()+sv.getTotalVoters());
				
				
				
				
				if(temp.getUserCollected()==null){
					temp.setUserCollected(0l);
				}
					
				if(sv.getUsers()!=null){
						List<SurveyThirdPartyReportVO> users = sv.getUsers().getUsersList();
						if(users!=null){
							SurveyThirdPartyReportVO user = users.get(0);
							List<SurveyThirdPartyReportVO> stList = user.getStatusList();
							
							temp.setUserCollected(user.getUserCollected()+temp.getUserCollected());
							allVO.setUserCollected(user.getUserCollected()+allVO.getUserCollected());
							
							if(stList!=null && stList.size()>0){
								for(SurveyThirdPartyReportVO tmp:stList){
									SurveyThirdPartyReportVO sn = getMatchedThirdPartyStatusVO(temp.getStatusList(),tmp.getStatusId());
									if(sn!=null){
										sn.setStatusCount(sn.getStatusCount()+tmp.getStatusCount());
										sn.setStatusPercentage(sn.getStatusCount() != null &&sn.getStatusCount() !=0 ? roundTo2DigitsFloatValue((float) sn.getStatusCount() * 100f / temp.getUserCollected())
												: "0.00");
									}
									
									SurveyThirdPartyReportVO all = getMatchedThirdPartyStatusVO(allVO.getStatusList(),tmp.getStatusId());
									if(all!=null){
										all.setStatusCount(all.getStatusCount()+tmp.getStatusCount());
										all.setStatusPercentage(all.getStatusCount() != null &&all.getStatusCount() !=0 ? roundTo2DigitsFloatValue((float) all.getStatusCount() * 100f / allVO.getUserCollected())
												: "0.00");
									}
								}
							}
						}
				}
				
				
				
			}
		}
		}catch (Exception e) {
			LOG.error("Exception Raised in getTPCompleteBoothsDetails" + e);
		}
		return finalVO;
		
		
	}
	
	public SurveyThirdPartyReportVO getMatchedBoothType(List<SurveyThirdPartyReportVO> svList, String type){
		if(svList!=null && svList.size()>0 && type!=null && type.trim().length()>0){
			for(SurveyThirdPartyReportVO sv:svList){
				if(sv.getBoothType().equalsIgnoreCase(type)){
					return sv;
				}
			}
		}
		return null;
	}
	
	
	public String saveSurveyCompletedConstituencyDetails(Long statusId,Long constituencyId,String comment)
	{
		LOG.info("Entered into the saveSurveyCompletedConstituencyDetails service method");

		try
		{
			SurveyCompletedConstituency surveyCompletedConstituency = new SurveyCompletedConstituency();
			
			surveyCompletedConstituency.setConstituencyId(constituencyId);
			surveyCompletedConstituency.setSurveyCompletedConstituencyStatusId(statusId);
			
			if(statusId.equals(IConstants.QUERY_STATUS_ID))
				surveyCompletedConstituency.setQueryComment(comment);
			
			surveyCompletedConstituencyDAO.save(surveyCompletedConstituency);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in saveSurveyCompletedConstituencyDetails service method");
			return null;
		}
		return "success";
	}
	
	public List<SurveyReportVO> getSurveyCompletedConstituencyDetails()
	{
		LOG.info("Entered into the getSurveyCompletedConstituencyDetails service method");
		List<SurveyReportVO> resultList = new ArrayList<SurveyReportVO>();

		try
		{
			List<Object[]> completedDetailsList = surveyCompletedConstituencyDAO.getSurveyCompletedConstituencyDetails();
			
			for(Object[] obj:completedDetailsList)
			{
				SurveyReportVO completionVO = new SurveyReportVO();
				
				completionVO.setId((Long)obj[0]);
				completionVO.setName(obj[1].toString());
                completionVO.setStatus(obj[3].toString());
                completionVO.setStatusId((Long)obj[2]);
                
                if(obj[4] != null)
                 completionVO.setComment(obj[4].toString());
                else
                 completionVO.setComment("---");
				
                resultList.add(completionVO);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getSurveyCompletedConstituencyDetails service method");
			return null;
		}
		return resultList;
	}
	
	public List<SurveyThirdPartyReportVO> thirdPartyReadyForReviewConstBooths(){
		LOG.debug("In thirdPartyReadyForReviewConstBooths");
		List<SurveyThirdPartyReportVO> finalList = new ArrayList<SurveyThirdPartyReportVO>();
		try{
			List<Object[]> list = surveyCompletedLocationsDAO.getBoothsAndConstituenciesOfTPWithStatus(IConstants.READY_FOR_REVIEW);
			if(list!=null && list.size()>0){
				for(Object[] obj:list){
					
					SurveyThirdPartyReportVO sv = getMatchedConstituency(finalList,Long.valueOf(obj[2].toString()));
					if(sv==null){
						sv = new SurveyThirdPartyReportVO();
						sv.setConstituency(obj[1].toString());
						sv.setConstituencyId(Long.valueOf(obj[2].toString()));
					}
					
					
					List<Long> booths = sv.getBooths();
					if(booths==null){
						booths = new ArrayList<Long>();
					}
					booths.add(Long.valueOf(obj[0].toString()));
					sv.setBooths(booths);
					
					finalList.add(sv);
				}
			}
		}catch (Exception e) {
			LOG.error("Exception Raised in thirdPartyReadyForReviewConstBooths " +e );
		}
		
		return finalList;
		
	}
	
	public String getConstituencyCompletionStatusByConstituencyId(Long constituencyId)
	{
		try
		{
			List<Object[]> list = surveyCompletedConstituencyDAO.getConstituencyCompletionStatusByConstituencyId(constituencyId);
			
			if(list != null && list.size() >0)
			{
				Object[] obj = list.get(0);
				
				String resultString = obj[0].toString();
				if(obj[0] != null)
					resultString = resultString.concat("-"+obj[1].toString());
				 return resultString;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public SurveyThirdPartyReportVO getMatchedConstituency(List<SurveyThirdPartyReportVO> list,Long constituencyId){
		if(list!=null && list.size()>0 && constituencyId!=null){
			for(SurveyThirdPartyReportVO sv:list){
				if(sv.getConstituencyId().equals(constituencyId)){
					return sv;
				}
			}
		}
		return null;
	}
}
