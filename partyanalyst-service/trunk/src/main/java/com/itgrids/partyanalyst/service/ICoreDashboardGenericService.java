package com.itgrids.partyanalyst.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.ActivityMemberVO;

public interface ICoreDashboardGenericService {
	
	public ActivityMemberVO getChildActivityMembersAndLocations(ActivityMemberVO activityMemberVO);
	public Map<Long,List<Long>> getParentUserTypesAndItsChildUserTypes();
	public Long getStateIdByState(String state);
	public List<Date> getDates(String startDateString,String endDateString,SimpleDateFormat sdf);
	public Double caclPercantage(Long subCount,Long totalCount);
}
