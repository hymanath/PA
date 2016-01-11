package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dto.ActivityAttendanceInfoVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.service.IActivityAttendanceService;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActivityAttendanceService implements IActivityAttendanceService {

	private IActivityInfoDocumentDAO activityInfoDocumentDAO;
	
	
	
	public IActivityInfoDocumentDAO getActivityInfoDocumentDAO() {
		return activityInfoDocumentDAO;
	}

	public void setActivityInfoDocumentDAO(
			IActivityInfoDocumentDAO activityInfoDocumentDAO) {
		this.activityInfoDocumentDAO = activityInfoDocumentDAO;
	}

	public ActivityAttendanceInfoVO getLocationWiseActivityDetails(SearchAttributeVO searchVO)
	{
		ActivityAttendanceInfoVO returnVO = new ActivityAttendanceInfoVO();
		List<ActivityAttendanceInfoVO> returnList = new ArrayList<ActivityAttendanceInfoVO>();
		returnVO.setSubList(returnList);
			getPhotosCount(searchVO,returnVO);
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
