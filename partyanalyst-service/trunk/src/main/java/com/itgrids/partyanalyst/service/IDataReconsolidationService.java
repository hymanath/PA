package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreTabRecordsStatusVO;

public interface IDataReconsolidationService {
	public List<CadreTabRecordsStatusVO> dataReConsalationOverView(Long constistuencyId,String fromDateStr,String toDateStr);
	public List<CadreTabRecordsStatusVO> dataReConsalationTotalOverView(Long constistuencyId,String fromDateStr,String toDateStr);

}
