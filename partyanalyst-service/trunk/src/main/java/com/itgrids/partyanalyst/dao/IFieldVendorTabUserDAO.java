package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FieldVendorTabUser;

public interface IFieldVendorTabUserDAO extends GenericDao<FieldVendorTabUser, Long>{

	public Long getTotalDataCollectorsCount(Date startDate,Date endDate,Long stateId);
	public Long getActiveDataCollectorsCount(Date lastHourTime,Date today,Long stateId);
	public List<Object[]> getStatusWiseIssuesDetails(Long issueTypeId,Long statusTypeId,Date fromDate,Date toDate);
	public List<Object[]> getUserWiseIssuesCounts(Date fromDate,Date toDate);
	public List<Object[]> getStatusWiseIssuesDetailsNew(Long issueTypeId,Long statusTypeId,Date fromDate,Date toDate);
	public String getVendorNameByCadreSurveyUserId(Long cadreSurveyUserId);
}
