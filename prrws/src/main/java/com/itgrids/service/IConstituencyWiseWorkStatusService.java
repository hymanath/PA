package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;

public interface IConstituencyWiseWorkStatusService {
	public List<LocationVO> getLocationsNamesBySubLocation(InputVO inputVO);
	public LocationVO getFundManagementSystemWorkDetails(List<Long> financialYearIdsList, Long departmentId, String startDateStr,String endDateStr,Long locationId);
	public List<LocationVO> getDistrictNameAndMlaNameByConsitutency(InputVO inputVO);
}
