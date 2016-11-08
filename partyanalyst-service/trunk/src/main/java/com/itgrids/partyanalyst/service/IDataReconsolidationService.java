package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreTabRecordsStatusVO;

public interface IDataReconsolidationService {
	public List<CadreTabRecordsStatusVO> dataReConsalationOverView(Long constistuencyId,String fromDateStr,String toDateStr,Long districtId);
	public CadreTabRecordsStatusVO dataReConsalationTotalOverView(Long constistuencyId,String fromDateStr,String toDateStr,Long districtId);
	public List<CadreTabRecordsStatusVO> getCadreSurveyUserWiseRegistrations(Long cadreSrveyUserId,Long constituencyId,String startDate,String endDate);
}
