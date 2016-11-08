package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreTabRecordsStatusVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;

public interface IDataReconsolidationService {
	public List<CadreTabRecordsStatusVO> dataReConsalationOverView(Long stateId,Long constistuencyId,String fromDateStr,String toDateStr,Long districtId);
	public CadreTabRecordsStatusVO dataReConsalationTotalOverView(Long stateId,Long constistuencyId,String fromDateStr,String toDateStr,Long districtId);
	public List<CadreTabRecordsStatusVO> getCadreSurveyUserWiseRegistrations(Long cadreSrveyUserId,Long constituencyId,String startDate,String endDate);
	public List<IdAndNameVO> getLocationWiseSmartDevicesCount(Long stateId,Long districtId,Long constituencyId,String startDate,String endDate);
}
