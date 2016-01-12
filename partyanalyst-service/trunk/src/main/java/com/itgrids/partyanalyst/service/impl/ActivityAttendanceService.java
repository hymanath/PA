package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationAttendanceDAO;
import com.itgrids.partyanalyst.dto.ActivityAttendanceInfoVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.service.IActivityAttendanceService;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActivityAttendanceService implements IActivityAttendanceService {

	private IActivityInfoDocumentDAO activityInfoDocumentDAO;
	private IActivityLocationAttendanceDAO activityLocationAttendanceDAO;
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
		returnVO.setSubList(returnList);
		getCadreAttendanceCount(searchVO,returnVO);
		getPublicAttendanceCount(searchVO,returnVO);
		getPhotosCount(searchVO,returnVO);
		return returnVO;
	}
	
	public ActivityAttendanceInfoVO getCadreAttendanceCount(SearchAttributeVO searchVO,ActivityAttendanceInfoVO returnVO)
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
		return returnVO;
		
	}
	
	
	public ActivityAttendanceInfoVO getPublicAttendanceCount(SearchAttributeVO searchVO,ActivityAttendanceInfoVO returnVO)
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
		return returnVO;
		
	}
	
	public ActivityAttendanceInfoVO getPhotosCount(SearchAttributeVO searchVO,ActivityAttendanceInfoVO returnVO)
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
		return returnVO;
		
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
