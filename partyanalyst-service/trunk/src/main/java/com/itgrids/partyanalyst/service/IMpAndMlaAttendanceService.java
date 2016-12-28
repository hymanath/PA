package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ActivityAttendanceVO;
import com.itgrids.partyanalyst.dto.AttendanceQuestionnariWSVO;
import com.itgrids.partyanalyst.dto.AttendanceTabUserVO;
import com.itgrids.partyanalyst.dto.AttendanceVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.PartyMeetingInviteeVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserAttendanceDetailsVO;

public interface IMpAndMlaAttendanceService {
	public List<IdAndNameVO> getSessionWiseTopAbsentMembersInfo(Long sessionId,String startDateStr,String endDateStr);
}

