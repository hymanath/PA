package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.AttendanceTabUserVO;
import com.itgrids.partyanalyst.dto.AttendanceVO;
import com.itgrids.partyanalyst.dto.UserAttendanceDetailsVO;

public interface IAttendanceService {

	public AttendanceVO saveAttendance(AttendanceVO attendanceVO);
	
	public AttendanceTabUserVO loginAttendanceTabUser(AttendanceTabUserVO inputVO);
	
	public UserAttendanceDetailsVO getAttendanceMeetingAndCamps(AttendanceTabUserVO inputVo);
}
