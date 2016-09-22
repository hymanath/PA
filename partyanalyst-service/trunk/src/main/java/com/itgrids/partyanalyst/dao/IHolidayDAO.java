package com.itgrids.partyanalyst.dao;

import java.util.Date;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Holiday;

public interface IHolidayDAO extends GenericDao<Holiday, Long> {
	public Long getHolidayCount(Date fromDate, Date toDate);
}
