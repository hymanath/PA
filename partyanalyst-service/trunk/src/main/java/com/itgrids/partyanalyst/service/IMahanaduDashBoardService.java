package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.MahanaduVisitVO;

public interface IMahanaduDashBoardService {
	public List<MahanaduVisitVO> getTodayTotalAndCurrentUsersInfo(Date fromDate,Date toDate);
	public void getTodayTotalVisitorsForWeb();
	public void getTodayTotalVisitorsForSchedular();
}
