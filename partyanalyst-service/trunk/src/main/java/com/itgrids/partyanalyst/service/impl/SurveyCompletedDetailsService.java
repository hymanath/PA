package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.model.SurveyCompletedLocations;
import com.itgrids.partyanalyst.service.ISurveyCompletedDetailsService;
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
			
			
			for(Long constituencyId:constituencyIds)
			{
				SurveyReportVO constituencyVO = new SurveyReportVO();
				constituencyVO.setId(constituencyId);
				constituencyVO.setName(constituencyDAO.get(constituencyId).getName());
				constituencyVO.setTotal(totalBoothsMap.get(constituencyId));
				constituencyVO.setCompletedCount(completedBoothsMap.get(constituencyId));
				constituencyVO.setProcessingCount(constituencyVO.getTotal()-constituencyVO.getCompletedCount());
				constituencyVO.setNotStartedCount(constituencyVO.getTotal() - constituencyVO.getProcessingCount());
				
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
			List<Object[]> boothStatusDetails = surveyCompletedLocationsDAO.getBoothsStatusDetailsByConstituencyId(constituencyId);
			
			for(Object[] obj:boothStatusDetails)
			{
				
				if(((Long)obj[1]).equals(IConstants.DC_PROCESS_STATUS_ID))
				{
					resultVO.setProcessingCount((Long)obj[0]);
					
				}else if(((Long)obj[1]).equals(IConstants.DC_COMPLETED_STATUS_ID))
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
			}
			
			
			//total booths by panchayat wise start
			
			List<Object[]> panchayatTotalBooths = boothDAO.getTotalBoothsCountForPanchayatisByConstituencyId(constituencyId);	
			
			Map<Long,Long> panchayatBoothCountMap = new HashMap<Long, Long>();
			
			
			for(Object[] obj:panchayatTotalBooths)
				panchayatBoothCountMap.put((Long)obj[1], (Long)obj[0]);
			
			//total booths by panchayat wise end
			
			//completed booths by panchayat wise start
			
			List<Object[]> completedPanchayatBoothDetails = surveyCompletedLocationsDAO.getCompletedBoothsCountForPanchayatisByConstituencyId(constituencyId);	
			
			Map<Long,Long> completedPanchayatBoothMap = new HashMap<Long, Long>();
			
			for(Object[] obj:completedPanchayatBoothDetails)
				completedPanchayatBoothMap.put((Long)obj[1], (Long)obj[0]);
			
			//completed booths by panchayat wise end
			
			SurveyReportVO panchayatCompletedDetails = new SurveyReportVO();
			
			//Here we are iterating  panchayat wise completed booth count map,and checking booths count in panchayat wise total count map.
			//If both are equal then the panchayat is completed.If no of booths in  completedPanchayatBoothMap count is less than 
			//panchayatBoothCountMap then that panchayat is in processing
			
			for(Entry<Long,Long> entry:completedPanchayatBoothMap.entrySet())
			{
				Long total = panchayatBoothCountMap.get(entry.getKey());
				
				if(entry.getValue().equals(total))
					panchayatCompletedDetails.setCount(panchayatCompletedDetails.getCompletedCount() +1L);
				else if(entry.getValue() < total)
					panchayatCompletedDetails.setProcessingCount(panchayatCompletedDetails.getProcessingCount() +1L);
				
			}
			
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
			
			// First we are removing all the previous records rekated to that location
			surveyCompletedLocationsDAO.deleteSurveyCompletedDetailsByLocationValueAndScope(locationValue,statusId);
			
			SurveyCompletedLocations surveyCompletedLocationDetails = new SurveyCompletedLocations();
			
			surveyCompletedLocationDetails.setLocationValue(locationValue);
			surveyCompletedLocationDetails.setLocationScopeId(locationScopeId);
			surveyCompletedLocationDetails.setStatusId(statusId);
			
			surveyCompletedLocationsDAO.save(surveyCompletedLocationDetails);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in saveBoothStatusDetails service method");
		}
		return "success";
	}
	
}
