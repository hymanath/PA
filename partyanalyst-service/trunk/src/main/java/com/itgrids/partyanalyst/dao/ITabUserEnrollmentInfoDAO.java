package com.itgrids.partyanalyst.dao;

import java.util.Date;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TabUserEnrollmentInfo;

public interface ITabUserEnrollmentInfoDAO extends GenericDao<TabUserEnrollmentInfo, Long>{
	
	public Long getTotalRecordSubmitedByTabUser(Date surveyTime, Long stateId);
}
