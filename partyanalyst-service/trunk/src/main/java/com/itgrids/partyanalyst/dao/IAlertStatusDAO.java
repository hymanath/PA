package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertStatus;

public interface IAlertStatusDAO extends GenericDao<AlertStatus, Long> {
	public List<Object[]> getAllStatus();
	public List<Object[]> getAlertStatusInfoForReOpen();

}
