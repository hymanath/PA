package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TabUserEnrollmentInfo;

public interface ITabUserEnrollmentInfoDAO extends GenericDao<TabUserEnrollmentInfo, Long>{
	
	public Long getTotalRecordSubmitedByTabUser(Date surveyTime, Long stateId);
	public Long getTotalTabUserWorkingInField(Long accessLvlId,List<Long> accessLvlValue,Long stateId,Date today);
	public Long getTodayInFieldList(Long stateId,Date lastOneHourTime);
	public Long getTodayPresentList(Long stateId,Date surveyTime);
	public int pushTabUserInfoIntoIntermediateTable();
	public List<Object[]> getTabUserFirstLastRecord(List<Long> tabUserInfoIds);
	public List<Object[]> getTabUserFirstLastRecordNew(Long cadreRegUserId,Long constituencyId,Long userId,Long districtId,Long stateId);
}
