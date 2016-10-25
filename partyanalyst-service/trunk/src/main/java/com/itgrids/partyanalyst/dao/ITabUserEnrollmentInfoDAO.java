package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TabUserEnrollmentInfo;

public interface ITabUserEnrollmentInfoDAO extends GenericDao<TabUserEnrollmentInfo, Long>{
	
	public Long getTotalRecordSubmitedByTabUser(Date surveyTime, Long stateId);
	public Long getTotalTabUserWorkingInField(Long accessLvlId,List<Long> accessLvlValue,Long stateId,Date today);
}
