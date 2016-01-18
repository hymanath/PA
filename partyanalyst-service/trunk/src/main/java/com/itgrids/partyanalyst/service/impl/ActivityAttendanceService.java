package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationAttendanceDAO;
import com.itgrids.partyanalyst.dao.IActivityScopeDAO;
import com.itgrids.partyanalyst.dto.ActivityAttendanceInfoVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityScope;
import com.itgrids.partyanalyst.service.IActivityAttendanceService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActivityAttendanceService implements IActivityAttendanceService {

	private IActivityInfoDocumentDAO activityInfoDocumentDAO;
	private IActivityLocationAttendanceDAO activityLocationAttendanceDAO;
	private IActivityScopeDAO activityScopeDAO;
	private IRegionServiceData regionServiceDataImp;
	
	
	
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public IActivityScopeDAO getActivityScopeDAO() {
		return activityScopeDAO;
	}

	public void setActivityScopeDAO(IActivityScopeDAO activityScopeDAO) {
		this.activityScopeDAO = activityScopeDAO;
	}

	public IActivityInfoDocumentDAO getActivityInfoDocumentDAO() {
		return activityInfoDocumentDAO;
	}

	public void setActivityInfoDocumentDAO(
			IActivityInfoDocumentDAO activityInfoDocumentDAO) {
		this.activityInfoDocumentDAO = activityInfoDocumentDAO;
	}

	public IActivityLocationAttendanceDAO getActivityLocationAttendanceDAO() {
		return activityLocationAttendanceDAO;
	}

	public void setActivityLocationAttendanceDAO(
			IActivityLocationAttendanceDAO activityLocationAttendanceDAO) {
		this.activityLocationAttendanceDAO = activityLocationAttendanceDAO;
	}

	public ActivityAttendanceInfoVO getLocationWiseActivityDetails(SearchAttributeVO searchVO)
	{
		ActivityAttendanceInfoVO returnVO = new ActivityAttendanceInfoVO();
		List<ActivityAttendanceInfoVO> returnList = new ArrayList<ActivityAttendanceInfoVO>();
		List<ActivityAttendanceInfoVO> returnList1 = new ArrayList<ActivityAttendanceInfoVO>();
		returnVO.setSubList(returnList);
		returnVO.setLocalBodyList(returnList1);
		List<Long> levelIds = new ArrayList<Long>();
		Long activityLevelId =null;
		ActivityScope activityScope = activityScopeDAO.get(searchVO.getAttributesIdsList().get(0));
		searchVO.setScopeId(activityScope.getScopeId());
		searchVO.setScopeValue(activityScope.getScopeValue());
		activityLevelId = activityScope.getActivityLevel().getActivityLevelId();
		
		if(activityLevelId != null && activityLevelId.longValue()>0L)
		{
			if(activityLevelId.longValue() == 1L)
			{
				searchVO.getLocationTypeIdsList().add(6L);
				searchVO.getLocationTypeIdsList().add(8L);
			}
			else if(activityLevelId.longValue() == 2L)
			{
				searchVO.getLocationTypeIdsList().add(5L);
				searchVO.getLocationTypeIdsList().add(7L);
				searchVO.getLocationTypeIdsList().add(9L);
			}
			else if(activityLevelId.longValue() == 3L)
			{
				searchVO.getLocationTypeIdsList().add(11L);
			}
			else if(activityLevelId.longValue() == 4L)
			{
				searchVO.getLocationTypeIdsList().add(10L);
			}
			else if(activityLevelId.longValue() == 5L)
			{
				searchVO.getLocationTypeIdsList().add(13L);
			}
		}
		
		
		getCadreAttendanceCount(searchVO,returnVO);
		getPublicAttendanceCount(searchVO,returnVO);
		getPhotosCount(searchVO,returnVO);
		
		Long totalAreasCount =0L;
		Long totalPlannedCount =0L;
		SearchAttributeVO searchVO1 = searchVO;
		if(activityLevelId != null && activityLevelId.longValue()>0L)
		{
			if(activityLevelId.longValue() == 1L)
			{
				searchVO1.getLocationTypeIdsList().add(6L);
				searchVO1.getLocationTypeIdsList().add(8L);
			}
			else if(activityLevelId.longValue() == 2L)
			{
				searchVO1.getLocationTypeIdsList().add(5L);
				searchVO1.getLocationTypeIdsList().add(7L);
				//searchVO1.getLocationTypeIdsList().add(9L);
			}
			else if(activityLevelId.longValue() == 3L)
			{
				searchVO1.getLocationTypeIdsList().add(3L);
			}
			else if(activityLevelId.longValue() == 5L)
			{
				searchVO1.getLocationTypeIdsList().add(4L);
			}
		}
		
		if(returnVO.getSubList() != null && returnVO.getSubList().size() > 0)
		{
				for(ActivityAttendanceInfoVO mainVO : returnVO.getSubList())
				{
					totalAreasCount =0L;
					
					if(searchVO1.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
						searchVO1.setScopeId(3L);
						searchVO1.setScopeValue(mainVO.getId());
						//activityVO.setName(activityVO.getName()+" District");
						List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchVO1);
						if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
						{
							totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
							mainVO.setTotalLocations(totalAreasCount);
						}
						
					}
					
					if(searchVO1.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
						searchVO1.setScopeId(4L);
						searchVO1.setScopeValue(mainVO.getId());
						//activityVO.setName(activityVO.getName()+" District");
						List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchVO1);
						if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
						{
							totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
							mainVO.setTotalLocations(totalAreasCount);
						}
						
					}
					
					
					if(searchVO1.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
						searchVO1.setScopeId(5L);
						searchVO1.setScopeValue(mainVO.getId());
						//activityVO.setName(activityVO.getName()+" District");
						List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchVO1);
						if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
						{
							totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
							mainVO.setTotalLocations(totalAreasCount);
						}
						
					}
					if(searchVO1.getSearchType().equalsIgnoreCase(IConstants.VILLAGE) || searchVO1.getSearchType().equalsIgnoreCase(IConstants.WARD)){
						mainVO.setTotalLocations(1L);
					}
					
				}	
				
				
				for(ActivityAttendanceInfoVO localbodyVO : returnVO.getLocalBodyList())
				{
						totalAreasCount =0L;
						if(searchVO1.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
						searchVO1.setScopeId(7L);
						searchVO1.setScopeValue(localbodyVO.getId());
						//activityVO.setName(activityVO.getName()+" District");
						List<BasicVO> areaWiseCountLsit = regionServiceDataImp.areaCountListByAreaIdsOnScope(searchVO1);
						if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
						{
							totalAreasCount = totalAreasCount+getTotalAreaCountByList(areaWiseCountLsit);
							localbodyVO.setTotalLocations(totalAreasCount);
						}
						
					}
					
				}	
				
			}
	
		if(returnVO != null && returnVO.getSubList() != null && returnVO.getSubList().size() > 0){
			Collections.sort(returnVO.getSubList(),comparedCountSort);
		}
		
		if(returnVO != null && returnVO.getLocalBodyList() != null && returnVO.getLocalBodyList().size() > 0){
			Collections.sort(returnVO.getLocalBodyList(),comparedCountSort);
		}
		
		return returnVO;
	}
	
	public static Comparator<ActivityAttendanceInfoVO> comparedCountSort = new Comparator<ActivityAttendanceInfoVO>()
	{

		public int compare(ActivityAttendanceInfoVO cstVO1, ActivityAttendanceInfoVO cstVO2)
		{
			return (cstVO2.getTotalMembers().intValue()) - (cstVO1.getTotalMembers().intValue());
		}
	};
	
	public Long getTotalAreaCountByList(List<BasicVO> areaWiseCountLsit)
	{
		Long totalAreasCount = 0L;
		try {
			if(areaWiseCountLsit != null && areaWiseCountLsit.size()>0)
			{
				totalAreasCount = 0L;
				for (BasicVO basicVO : areaWiseCountLsit) {
					totalAreasCount = totalAreasCount+ basicVO.getTotalVoters().longValue();
				}
			}
		} catch (Exception e) {
			// LOG.error("Exception Occured in getActivityLocationDetails() method, Exception - ",e);
		}
		return totalAreasCount;
	}
	
	
	public void getCadreAttendanceCount(SearchAttributeVO searchVO,ActivityAttendanceInfoVO returnVO)
	{
		try{
			 List<Object[]> list = null;
			 List<Object[]> localbodyList = null;
			 if(searchVO.getLocationValue() > 0)
				{
					List<Long> locationIds = new ArrayList<Long>();
					locationIds.add(searchVO.getLocationValue());
					searchVO.setLocationIdsList(locationIds);
				}
			 
			 if(searchVO.getSearchType().equalsIgnoreCase("mandal"))
			 {
				 list = activityLocationAttendanceDAO.getActivityAttendanceCount(searchVO,"cadre");
				 searchVO.setSearchType(IConstants.URBAN);
				 localbodyList = activityLocationAttendanceDAO.getActivityAttendanceCount(searchVO,"cadre");
				 searchVO.setSearchType("mandal");
			 }
			 else
			 list = activityLocationAttendanceDAO.getActivityAttendanceCount(searchVO,"cadre");
			 if(list != null && list.size() > 0)
			 {
				
				 for(Object[] params : list)
				 {
					 ActivityAttendanceInfoVO VO = getMatchedVO(returnVO.getSubList(),(Long)params[0]);
					 if(VO == null)
					 {
						 VO = new ActivityAttendanceInfoVO();
						 VO.setId((Long)params[0]);
						 VO.setName(params[1].toString());
						 returnVO.getSubList().add(VO);
					 }
					 if(params[3] != null && params[3].toString().equalsIgnoreCase("WS"))
					 {
						 VO.setTotalInfoCellCadreAttendance((Long)params[2]);
					
					 }
					 else
					 {	
						 VO.setTotalWebCadreAttendance((Long)params[2]);
						
					 }
					 VO.setTotalMembers((Long)params[2] + VO.getTotalMembers());
				 }
			 }
			 
			 if(localbodyList != null && localbodyList.size() > 0)
			 {
				
				 for(Object[] params : localbodyList)
				 {
					 ActivityAttendanceInfoVO VO = getMatchedVO(returnVO.getLocalBodyList(),(Long)params[0]);
					 if(VO == null)
					 {
						 VO = new ActivityAttendanceInfoVO();
						 VO.setId((Long)params[0]);
						 VO.setName(params[1].toString());
						 returnVO.getSubList().add(VO);
					 }
					 if(params[3] != null && params[3].toString().equalsIgnoreCase("WS"))
					 {
						 VO.setTotalInfoCellCadreAttendance((Long)params[2]);
						
					 }
					 else
					 {
						 VO.setTotalWebCadreAttendance((Long)params[2]);
						
					 }
					VO.setTotalMembers((Long)params[2] + VO.getTotalMembers());
				 }
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void getPublicAttendanceCount(SearchAttributeVO searchVO,ActivityAttendanceInfoVO returnVO)
	{
		try{
			 List<Object[]> list = null;
			 List<Object[]> localbodyList = null;
			 if(searchVO.getLocationValue() > 0)
				{
					List<Long> locationIds = new ArrayList<Long>();
					locationIds.add(searchVO.getLocationValue());
					searchVO.setLocationIdsList(locationIds);
				}
			 
			 if(searchVO.getSearchType().equalsIgnoreCase("mandal"))
			 {
				 list = activityLocationAttendanceDAO.getActivityAttendanceCount(searchVO,"public");
				 searchVO.setSearchType(IConstants.URBAN);
				 localbodyList = activityLocationAttendanceDAO.getActivityAttendanceCount(searchVO,"public");
				 searchVO.setSearchType("mandal");
			 }
			 else
			 list = activityLocationAttendanceDAO.getActivityAttendanceCount(searchVO,"public");
			 
			 
			 if(list != null && list.size() > 0)
			 {
				
				 for(Object[] params : list)
				 {
					 ActivityAttendanceInfoVO VO = getMatchedVO(returnVO.getSubList(),(Long)params[0]);
					 if(VO == null)
					 {
						 VO = new ActivityAttendanceInfoVO();
						 VO.setId((Long)params[0]);
						 VO.setName(params[1].toString());
						 returnVO.getSubList().add(VO);
					 }
					 if(params[3] != null && params[3].toString().equalsIgnoreCase("WS"))
					 {
						
							 VO.setTotalInfoCellPublicAttendance((Long)params[2]);
					 }
					 else
					 {
						 VO.setTotalWebPublicAttendance((Long)params[2]);
					 }
					 VO.setTotalMembers((Long)params[2] + VO.getTotalMembers());
				 }
			 }
			 
			 if(localbodyList != null && localbodyList.size() > 0)
			 {
				
				 for(Object[] params : localbodyList)
				 {
					 ActivityAttendanceInfoVO VO = getMatchedVO(returnVO.getLocalBodyList(),(Long)params[0]);
					 if(VO == null)
					 {
						 VO = new ActivityAttendanceInfoVO();
						 VO.setId((Long)params[0]);
						 VO.setName(params[1].toString());
						 returnVO.getSubList().add(VO);
					 }
					 if(params[3] != null && params[3].toString().equalsIgnoreCase("WS"))
					 {
						
							 VO.setTotalInfoCellPublicAttendance((Long)params[2]);
					 }
					 else
					 {
						 VO.setTotalWebPublicAttendance((Long)params[2]);
					 }
					VO.setTotalMembers((Long)params[2] + VO.getTotalMembers());
				 }
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
	
	public void getPhotosCount(SearchAttributeVO searchVO,ActivityAttendanceInfoVO returnVO)
	{
		try{
			 List<Object[]> list = null;
			 List<Object[]> localbodyList = null;
			 if(searchVO.getLocationValue() > 0)
				{
					List<Long> locationIds = new ArrayList<Long>();
					locationIds.add(searchVO.getLocationValue());
					searchVO.setLocationIdsList(locationIds);
				}
			 
			 if(searchVO.getSearchType().equalsIgnoreCase("mandal"))
			 {
				 
				 list = activityInfoDocumentDAO.getActivityInfoImagesCount(searchVO);
				 searchVO.setSearchType(IConstants.URBAN);
				 localbodyList = activityInfoDocumentDAO.getActivityInfoImagesCount(searchVO);
			 }
			 
			 else
			 list = activityInfoDocumentDAO.getActivityInfoImagesCount(searchVO);
			 if(list != null && list.size() > 0)
			 {
				
				 for(Object[] params : list)
				 {
					 ActivityAttendanceInfoVO VO = getMatchedVO(returnVO.getSubList(),(Long)params[0]);
					 if(VO == null)
					 {
						 VO = new ActivityAttendanceInfoVO();
						 VO.setId((Long)params[0]);
						 VO.setName(params[1].toString());
						 returnVO.getSubList().add(VO);
					 }
					 if(params[3] != null && params[3].toString().equalsIgnoreCase("WS"))
						 VO.setTotalInfoCellPhotosAttendance((Long)params[2]);
					 else
						 VO.setTotalWebPhotosAttendance((Long)params[2]);
				 }
			 }
			 
			 if(localbodyList != null && localbodyList.size() > 0)
			 {
				
				 for(Object[] params : localbodyList)
				 {
					 ActivityAttendanceInfoVO VO = getMatchedVO(returnVO.getLocalBodyList(),(Long)params[0]);
					 if(VO == null)
					 {
						 VO = new ActivityAttendanceInfoVO();
						 VO.setId((Long)params[0]);
						 VO.setName(params[1].toString());
						 returnVO.getLocalBodyList().add(VO);
					 }
					 if(params[3] != null && params[3].toString().equalsIgnoreCase("WS"))
						 VO.setTotalInfoCellPhotosAttendance((Long)params[2]);
					 else
						 VO.setTotalWebPhotosAttendance((Long)params[2]);
				 }
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
	
	
	public ActivityAttendanceInfoVO getMatchedVO(List<ActivityAttendanceInfoVO> resultList,Long id)
	{
		if(resultList == null || resultList.size() == 0)
			return null;
		for(ActivityAttendanceInfoVO vo : resultList)
			if(vo.getId().longValue() == id.longValue())
				return vo;
		return null;
		
		
	}
}
