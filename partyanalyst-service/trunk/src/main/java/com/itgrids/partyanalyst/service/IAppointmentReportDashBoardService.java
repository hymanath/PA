package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.AppointmentFieldsVO;
import com.itgrids.partyanalyst.dto.AppointmentLocVO;

public interface IAppointmentReportDashBoardService {
	
	public List<AppointmentLocVO> getCandiCountsByLocations(String startDateStr,String endDateStr,Long appointmentUserId,String locType,Long stateId,List<Long> candiTypeIds,List<String> requestedTypes);
	
	public Map<Long,String> getPartyPositionDesignationsforTdpcadreIds(List<Long> cadreIds);
	public Map<Long,String> getConstituenciesforTdpcadreIds(List<Long> cadreIds);
	public AppointmentFieldsVO getTotalAppointmentDetails(Long appointmentId);
}
