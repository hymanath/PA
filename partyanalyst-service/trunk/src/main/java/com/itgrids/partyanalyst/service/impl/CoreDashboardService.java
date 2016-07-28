package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IDashboardUserAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IDashboardUserAccessTypeDAO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.service.ICoreDashboardService;

public class CoreDashboardService implements ICoreDashboardService{
	
	private final static Logger LOG = Logger.getLogger(CoreDashboardService.class);
	
	//Attributes
	private IDashboardUserAccessLevelDAO dashboardUserAccessLevelDAO;
	private IDashboardUserAccessTypeDAO  dashboardUserAccessTypeDAO;
	
	//setters
	public void setDashboardUserAccessLevelDAO(
			IDashboardUserAccessLevelDAO dashboardUserAccessLevelDAO) {
		this.dashboardUserAccessLevelDAO = dashboardUserAccessLevelDAO;
	}
	public void setDashboardUserAccessTypeDAO(
			IDashboardUserAccessTypeDAO dashboardUserAccessTypeDAO) {
		this.dashboardUserAccessTypeDAO = dashboardUserAccessTypeDAO;
	}
	
	//business methods.
	
	public List<UserDataVO> getUserAccessLevelAndValues(Long userId){
		
		LOG.info(" entered in to getUserAccessLevelAndValues()");
		List<UserDataVO> finalList = null;
		try{
			
			List<Object[]> userLevelsList = dashboardUserAccessLevelDAO.getUserAccessLevelAndValues(userId);
			if( userLevelsList != null && userLevelsList.size()>0){
				finalList = new ArrayList<UserDataVO>();
				for (Object[] obj : userLevelsList) {
					UserDataVO VO = new UserDataVO();
					VO.setUserId(userId);
					VO.setUserLevelId( obj[0]!= null ? (Long)obj[0] : 0l);
					VO.setLevel(obj[1]!= null ? obj[1].toString() : "");
					VO.setLevelValue(obj[2]!= null ? (Long)obj[2] : 0l);
					finalList.add(VO);
				}
			}
		}catch(Exception e){
			LOG.error("error occurred in getUserAccessLevelAndValues() of CoreDashboardService class",e);
		}
		return finalList;
	}
	
	public UserDataVO getUserTypeByUserId(Long userId){
		
		LOG.info(" entered in to getUserTypeByUserId() ");
		 UserDataVO finalVO = null;
		try{
			
			List<Object[]> userTypeList = dashboardUserAccessTypeDAO.getUserTypeByUserId(userId);
			if( userTypeList != null && userTypeList.size()>0){
			    Object[] obj = userTypeList.get(0);
				finalVO = new UserDataVO();
				finalVO.setUserId(userId);
				finalVO.setUserTypeId( obj[0]!= null ? (Long)obj[0] : 0l);
				finalVO.setUserType(obj[1]!= null ? obj[1].toString() : "");
			}
		}catch(Exception e){
			LOG.error("error occurred in getUserTypeByUserId() of CoreDashboardService class",e);
		}
		return finalVO;
	}
	
}
