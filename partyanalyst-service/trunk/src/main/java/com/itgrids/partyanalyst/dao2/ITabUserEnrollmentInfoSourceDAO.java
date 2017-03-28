package com.itgrids.partyanalyst.dao;

import java.util.Date;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TabUserEnrollmentInfoSource;

public interface ITabUserEnrollmentInfoSourceDAO extends GenericDao<TabUserEnrollmentInfoSource, Long> {  
	public Long getTotalTabUserWorkingInField(Long stateId,Date lastOneHourTime,Date today);
}
