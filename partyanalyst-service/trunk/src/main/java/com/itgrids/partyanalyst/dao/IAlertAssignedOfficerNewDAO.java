package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;

public interface IAlertAssignedOfficerNewDAO extends GenericDao<AlertAssignedOfficerNew, Long> {

	public List<Object[]> getAssignedStatuses();
}
