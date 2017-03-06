package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ActivityAttendanceVO;
import com.itgrids.partyanalyst.dto.AttendanceQuestionnariWSVO;
import com.itgrids.partyanalyst.dto.AttendanceTabUserVO;
import com.itgrids.partyanalyst.dto.AttendanceVO;
import com.itgrids.partyanalyst.dto.ManualAttendanceVO;
import com.itgrids.partyanalyst.dto.PartyMeetingInviteeVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserAttendanceDetailsVO;

public interface IAttendanceService {

	public AttendanceVO saveAttendance(AttendanceVO attendanceVO);
	
	public AttendanceTabUserVO loginAttendanceTabUser(AttendanceTabUserVO inputVO);
	
	public UserAttendanceDetailsVO getAttendanceMeetingAndCamps(AttendanceTabUserVO inputVo);
	
	public PartyMeetingInviteeVO getPartyMeetingInvittees(Long partyMeetingId);
	
	public PartyMeetingInviteeVO getTrainingCampBatchInvittees(Long trainingCampBatchId);
	public ResultStatus saveCadreActivityAttendance(final ActivityAttendanceVO inputVO,final Long userId);

	public ResultStatus savePublicActivityAttendance(final ActivityAttendanceVO inputVO,final Long userId);
	public ResultStatus saveActivityQuestionAnswer(final AttendanceQuestionnariWSVO aqWSVO);
	
	public String updateManualAttendanceDetails(final ManualAttendanceVO inputvo);
	public String savingPartyMeetingImages(final ManualAttendanceVO inputvo);
}
