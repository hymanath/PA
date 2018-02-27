package com.itgrids.partyanalyst.service;

import java.util.Date;

import com.itgrids.partyanalyst.dto.AttendanceLogTrackVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IBiometricAttendanceService {

	public AttendanceLogTrackVO getBiometricLogDetails(Date logDate);
	
	public ResultStatus saveBiometricLogDetails(Date logDate);
}
