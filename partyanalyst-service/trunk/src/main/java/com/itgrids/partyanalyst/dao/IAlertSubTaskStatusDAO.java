package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertSubTaskStatus;

public interface IAlertSubTaskStatusDAO extends GenericDao<AlertSubTaskStatus, Long> {
	public List<Object[]> getAlertStatusDtlsBasidOnAlertIds(List<Long> statusIds);
}
