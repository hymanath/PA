package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.NregsFmsWorksVO;
import com.itgrids.dto.ResponseVO;

public interface IConstituencyWiseWorkStatusService {
	public List<LocationVO> getLocationsNamesBySubLocation(InputVO inputVO);
	public LocationVO getFundManagementSystemWorkDetails(List<Long> financialYearIdsList, List<Long> departmentIdList, String startDateStr,String endDateStr,Long locationId);
	public List<LocationVO> getDistrictNameAndMlaNameByConsitutency(InputVO inputVO);
	
	public List<NregsFmsWorksVO> getConstituencyWiseNregsWorksDetails(InputVO inputVO);
	public NregsFmsWorksVO getConstituencyWiseNregsWorksOverview(InputVO inputVO);
	public List<LocationVO> getDepartmentNames();
	
	public ResponseVO savingLabourBudgetRangeWiseExpenditureDetails(InputVO inputVO);
	public ResponseVO savingLabourBudgetRangeWiseExpenditureDetailsEveryDay(InputVO inputVO);
}
