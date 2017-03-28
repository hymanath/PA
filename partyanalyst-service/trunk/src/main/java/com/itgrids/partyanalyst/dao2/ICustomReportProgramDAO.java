package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CustomReportProgram;

public interface ICustomReportProgramDAO extends GenericDao<CustomReportProgram, Long>{
	public List<Object[]> getCustomReportPogram(Date fromDate,Date toDate);
	

}
