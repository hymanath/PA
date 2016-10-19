package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.IdNameVO;

public interface IDataMonitoringService {
	public IdNameVO getTotalRegCdrVendorWise(Long stateId, Long vendorId, Long distId, Long constId, String startDate, String endDate);

}
