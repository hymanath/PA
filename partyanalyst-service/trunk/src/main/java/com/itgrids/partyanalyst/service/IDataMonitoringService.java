package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DataMonitoringOverviewVO;

import com.itgrids.partyanalyst.dto.IdNameVO;

public interface IDataMonitoringService {
	public IdNameVO getTotalRegCdrVendorWise(Long stateId, Long vendorId, Long distId, Long constId, String startDate, String endDate);
	public List<IdNameVO> getTotalRegCdrVendorAndTabUserWise(Long stateId, Long vendorId, Long distId, Long constId, String startDate, String endDate);
	public DataMonitoringOverviewVO getDataMonitoringOverViewDetails(String fromDateStr,String toDateStr);
	public List<DataMonitoringOverviewVO> getRegistrationDetailsUserWise(String fromDateStr,String toDateStr,String dataSourceType,String verificationStatus);
}
