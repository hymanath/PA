package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ActivityAttendanceInfoVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;

public interface IActivityAttendanceService {
	public ActivityAttendanceInfoVO getLocationWiseActivityDetails(SearchAttributeVO searchVO,Long stateId);
	public void getCadreAttendanceCount(SearchAttributeVO searchVO,ActivityAttendanceInfoVO returnVO,Long stateId);
	public void getPublicAttendanceCount(SearchAttributeVO searchVO,ActivityAttendanceInfoVO returnVO,Long stateId);
	public void getPhotosCount(SearchAttributeVO searchVO,ActivityAttendanceInfoVO returnVO,Long stateId);
}
