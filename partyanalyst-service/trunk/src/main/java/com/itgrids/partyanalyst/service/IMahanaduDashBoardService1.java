package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.MahanaduEventVO;

public interface IMahanaduDashBoardService1 {
	
	public List<MahanaduEventVO> LocationWiseEventAttendeeCounts(String startDate,String endDate,Long parenteventId,List<Long> subEventIds,Long reportLevelId,List<Long> stateIds,String stateType);
}
