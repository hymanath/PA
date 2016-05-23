package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.MahanaduVisitVO;

public interface IMahanaduDashBoardService {
	public List<MahanaduVisitVO> getTodayTotalAndCurrentUsersInfo(Date startDate,Date endDate);
	public void getTodayTotalVisitorsForWeb();
	public void getTodayTotalVisitorsForSchedular();
	public List<MahanaduVisitVO> getTodayTotalAndCurrentUsersInfoList(Long eventId,String dateValues);
	public MahanaduVisitVO getTodayTotalAndCurrentUsersInfoListNew(Long eventId,String eventDate);
	public MahanaduEventVO getDistrictWiseTotalAndPresentCadre(Long eventId,List<Long> stateIds);
	public Long getTodayCount(Long eventId);
	public List<MahanaduEventVO> getHourWiseNowInCampusCadresCount(String dayCount,Long eventId);
	public List<IdNameVO> getEventDates(Long eventId);
}
