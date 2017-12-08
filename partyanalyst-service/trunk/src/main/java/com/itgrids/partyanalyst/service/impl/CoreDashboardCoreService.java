package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.service.ICoreDashboardCoreService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class CoreDashboardCoreService implements ICoreDashboardCoreService {
	private CommonMethodsUtilService commonMethodsUtilService;
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	public TrainingCampVO getTrainingCampFeedBackDetails(Long userAccessLevelId, List<Long> userAccessLevelValueList,Long stateId, String fromDateStr, String toDateStr, List<Long> enrollmentYearIdList, List<Long> programIdList){
		
		TrainingCampVO FinaltrainingCampVO = new TrainingCampVO();
		
		
		
		return null;
		
	}
	
	  

}

	
