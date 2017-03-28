package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FieldVendorTabUser;

public interface IFieldVendorTabUserDAO extends GenericDao<FieldVendorTabUser, Long>{

	public Long getTotalDataCollectorsCount(Date startDate,Date endDate,Long stateId);
	public Long getActiveDataCollectorsCount(Date lastHourTime,Date today,Long stateId);
	public List<Object[]> getStatusWiseIssuesDetails(Long issueTypeId,Long statusTypeId,Date fromDate,Date toDate);
	public List<Object[]> getUserWiseIssuesCounts(Date fromDate,Date toDate,Long issueTypeId,Long issueStatusId);
	public List<Object[]> getStatusWiseIssuesDetailsNew(Long issueTypeId,Long statusTypeId,Date fromDate,Date toDate,Long stateId);
	public String getVendorNameByCadreSurveyUserId(Long cadreSurveyUserId);
	public Long getOverAllTotalDataCollectorsCount(Long stateId);
	public List<Object[]> getVendorNameByCadreSurveyUserId(List<Long> cadreSurveyUserIds);
	public Long getPassiveDataCollectorsCount(Date today,Long stateId);
	public Long getTotalDataCollectorsCountFrMnrtg(Long stateId,Long districtId,Long constituencyId,Long cadreRegUserId,Long userId);
	public Long getTotalRegisteredCount(Long stateId,Long districtId,Long constituencyId,Long cadreRegUserId,Long userId);
	public Long getTodayActiveMbrsCount(Long stateId,Long districtId,Long constituencyId,Long cadreRegUserId,Long userId,Date today);
	public Long getOneHourActiveUsersCount(Long stateId,Long districtId,Long constituencyId,Long cadreRegUserId,Long userId,Date lastHourTime,Date today);
	public Long getAssignedUsersCountForRegUser(Long cadreRegUserId);
	public List<Object[]> getConstituencyIssueWiseOverAllDetails(Long issueTypeId,Long statusTypeId,Date fromDate,Date toDate,Long stateId);
	public List<Object[]> getDistrictWiseIssuesCount(Long issueTypeId,Long issueStatusId,Long stateId,Date fromDate,Date toDate);
	public List<Object[]> getConstituencyWiseIssuesCount(Long issueTypeId,Long issueStatusId,Long stateId,Date fromDate,Date toDate);
}
