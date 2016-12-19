package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TabUserEnrollmentInfo;

public interface ITabUserEnrollmentInfoDAO extends GenericDao<TabUserEnrollmentInfo, Long>{
	
	public Long getTotalRecordSubmitedByTabUser(Date surveyTime, Long stateId);
	public Long getTotalTabUserWorkingInField(Long accessLvlId,List<Long> accessLvlValue,Long stateId,Date today);
	public Long getTodayInFieldList(Long stateId,Date lastOneHourTime);
	public Long getTodayPresentList(Long stateId,Date surveyTime);
	public int pushTabUserInfoIntoIntermediateTable();
	public List<Object[]> getTabUserFirstLastRecord(List<Long> tabUserInfoIds);
	public List<Object[]> getTabUserFirstLastRecordNew(Long cadreRegUserId,Long constituencyId,Long userId,Long districtId,Long stateId,Date startDate,Date endDate);
	public List<Object[]> getTabUserWiseTotalRegistrationDetails(Long stateId,Date date);
	public List<Object[]> getActiveTabUserDtls(Long stateId,Date lastOneHourTime);
	public List<Object[]> getTabUserDtlsList(Long constituencyId,Date fromDate,Date toDate);
	public List<Object[]> getActualCountOfCadreSurveyUserWiseCount(Set<Long> cadreSurveyUsers,Date fromDate,Date toDate);
	public List<Object[]> getActualCountOfCadreSurveyUser(Set<Long> cadreSurveyUsers,Date fromDate,Date toDate);
	public List<Object[]> getActualCountOfCadreSurveyUser(Long districtId,Long stateId,Date startDate,Date endDate,Long constituencyId,Set<Long> cadreSurveyUsers);
}
