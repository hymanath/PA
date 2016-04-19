package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AppointmentLocVO;

public interface IAppointmentReportDashBoardService {
	
	public List<AppointmentLocVO> getCandiCountsByLocations(String startDateStr,String endDateStr,Long appointmentUserId,String locType,Long stateId,List<Long> candiTypeIds,List<String> requestedTypes);
}
