package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ActivityAttendanceInfoVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;

public interface IActivityAttendanceService {
	public ActivityAttendanceInfoVO getLocationWiseActivityDetails(SearchAttributeVO searchVO);

}
