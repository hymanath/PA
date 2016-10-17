package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.IdAndNameVO;

public interface IFieldMonitoringService {
	
	public List<IdAndNameVO> getVendors(Long stateId);
	public List<IdAndNameVO> getVendorDistricts(Long stateId,Long vendorId);
	public List<IdAndNameVO> getVendorConstituencies(Long vendorId,Long districtId);
	public List<IdAndNameVO> getCadreRegIssueType();
	
}
