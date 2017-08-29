package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateReport;

public interface IDebateReportDAO extends GenericDao<DebateReport, Long>{

	public Long checkValidUserForReport(Long reportId,Long userId);

	public int deleteDebateReport(String key);
	
	public String getDebateDatils(Long userId,Long debateId,Long stateId);
}
