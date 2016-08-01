package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityInfoFile;
import com.itgrids.partyanalyst.model.AlertAssigned;

public interface IAlertAssignedDAO extends GenericDao<AlertAssigned, Long> {
	public List<Long> checkCadreExistsForAlert(List<Long> tdpCadreIds,Long alertId);

}
