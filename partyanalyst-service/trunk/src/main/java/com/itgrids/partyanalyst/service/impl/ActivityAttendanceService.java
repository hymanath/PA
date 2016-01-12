package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationAttendanceDAO;
import com.itgrids.partyanalyst.dao.IActivityScopeDAO;
import com.itgrids.partyanalyst.dto.ActivityAttendanceInfoVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityScope;
import com.itgrids.partyanalyst.service.IActivityAttendanceService;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActivityAttendanceService implements IActivityAttendanceService {

	private IActivityInfoDocumentDAO activityInfoDocumentDAO;
	private IActivityLocationAttendanceDAO activityLocationAttendanceDAO;
	private IActivityScopeDAO activityScopeDAO;
	
	
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
				searchVO.getLocationTypeIdsList().add(10L);
			}
			else if(activityLevelId.longValue() == 4L)
			{
				searchVO.getLocationTypeIdsList().add(11L);
			}
		}
		
		
		getCadreAttendanceCount(searchVO,returnVO);
		getPublicAttendanceCount(searchVO,returnVO);
		getPhotosCount(searchVO,returnVO);
		return returnVO;
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
