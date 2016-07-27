package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ReportEmail;

public interface IReportEmailDAO extends GenericDao<ReportEmail, Long> {
	public List<Object[]> getEmailList(Long emailReportId);
	public List<Object[]> getDeptList(Long emailReportId);
}
