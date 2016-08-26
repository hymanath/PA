package com.itgrids.partyanalyst.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.UserDataVO;

public interface ICoreDashboardGenericService {
	
	public ActivityMemberVO getChildActivityMembersAndLocations(ActivityMemberVO activityMemberVO);
	public Map<Long,List<Long>> getParentUserTypesAndItsChildUserTypes();
	public Long getStateIdByState(String state);
	public List<Date> getDates(String startDateString,String endDateString,SimpleDateFormat sdf);
	public Double caclPercantage(Long subCount,Long totalCount);
	public List<UserDataVO> getChildUserTypesByItsParentUserType(Long parentUserTypeId);
	public ActivityMemberVO getSelectedChildUserTypeMembers(Long parentActivityMemberId,Long childUSerTypeId);
	public void setAppropriateLocationLevelInputsToBO(Long userAccessLevelId,List<Long> userAccessLevelValues,CommitteeInputVO inputVO);
	public ActivityMemberVO getDirectChildActivityMemberCommitteeDetails(Long activityMemberId,Long userTypeId);
	public  Map<String,String>  getLocationNamesByLocationIds( Map<Long,Set<Long>> locationLevelIdsMap);
}
