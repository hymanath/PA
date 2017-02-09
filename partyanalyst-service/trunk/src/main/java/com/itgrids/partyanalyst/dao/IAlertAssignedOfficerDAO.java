package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertAssignedOfficer;

public interface IAlertAssignedOfficerDAO extends GenericDao<AlertAssignedOfficer, Long>{

	public List<Object[]> getAssignedOfficersForAlert(Long alertId);
}
