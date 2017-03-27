package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.hibernate.ActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dto.CoreDashboardInsuranceVO;
import com.itgrids.partyanalyst.service.ICoreDashboardInsuranceService;

public class CoreDashboardInsuranceService implements ICoreDashboardInsuranceService{

	private final static Logger LOG = Logger.getLogger(CoreDashboardPartyMeetingService.class);

	private ActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	
	
	public ActivityMemberAccessLevelDAO getActivityMemberAccessLevelDAO() {
		return activityMemberAccessLevelDAO;
	}
	public void setActivityMemberAccessLevelDAO(
			ActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}


	public List<CoreDashboardInsuranceVO> getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails(Long activityMemberId,Long cadreYearId,Long stateId,
						String fromDateStr,String toDateStr){
		List<CoreDashboardInsuranceVO> returnList = new ArrayList<CoreDashboardInsuranceVO>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDate = null;
			Date toDate = null;
			if(fromDateStr != null && toDateStr != null){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			Set<Long> locationValuesSet = new java.util.HashSet<Long>();  
			Long locationId = 0L;
			List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
		    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
			   for (Object[] obj : rtrnUsrAccssLvlIdAndVlusObjLst) {
				   locationId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				   locationValuesSet.add(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
			   }
		    }
		    
		    
		    
		} catch (Exception e) {
			LOG.error("Exception Occured in getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails on CoreDashboardInsuranceService", e);
		}
		return returnList;
	}
}
