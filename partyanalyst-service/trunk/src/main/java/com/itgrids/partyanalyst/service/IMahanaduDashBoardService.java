package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.MahanaduVisitVO;

public interface IMahanaduDashBoardService {
	public MahanaduVisitVO getTodayTotalAndCurrentUsersInfo();
	public void getTodayTotalVisitors();
}
