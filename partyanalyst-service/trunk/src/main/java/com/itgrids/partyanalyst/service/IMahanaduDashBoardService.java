package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.MahanaduVisitVO;

public interface IMahanaduDashBoardService {
	public List<MahanaduVisitVO> getTodayTotalAndCurrentUsersInfo(Date startDate,Date endDate);
	public void getTodayTotalVisitorsForWeb();
	public void getTodayTotalVisitorsForSchedular();
	public List<MahanaduVisitVO> getTodayTotalAndCurrentUsersInfoList(Long eventId);
	public MahanaduVisitVO getTodayTotalAndCurrentUsersInfoListNew(Long eventId);
}
