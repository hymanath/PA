package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.MahanaduVisitVO;

public interface IMahanaduDashBoardService {
	public List<MahanaduVisitVO> getTodayTotalAndCurrentUsersInfo(Date startDate,Date endDate);
	public void getTodayTotalVisitorsForWeb();
	public void getTodayTotalVisitorsForSchedular();
	public List<MahanaduVisitVO> getTodayTotalAndCurrentUsersInfoList(Long eventId,String dateValues,List<Long> enrollmentIdsList);
	public MahanaduVisitVO getTodayTotalAndCurrentUsersInfoListNew(Long eventId,String eventDate,List<Long> enrollmentIdsList);
	public MahanaduEventVO getDistrictWiseTotalAndPresentCadre(Long eventId,List<Long> stateIds,String date,List<Long> enrollmentIdsList);
	public Long getTodayCount(Long eventId);
	public List<MahanaduEventVO> getHourWiseNowInCampusCadresCount(String dayCount,Long eventId,List<Long> enrollmentIdsList);
	public List<IdNameVO> getEventDates(Long eventId);
	public MahanaduEventVO getConstituencyWiseMembersCountInCampus(Long eventId,List<Long> stateIds,String date,List<Long> enrollmentIds);
	//public List<CandidateDetailsVO> getCandidateDetails(Long designationId,String inviteeType,Long eventId,String day);
	public List<CandidateDetailsVO> getCandidateDetails(Long designationId,String inviteeType,Long eventId,String day,String roleType,String level);
}
