package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityInfoFile;
import com.itgrids.partyanalyst.model.AlertAssigned;

public interface IAlertAssignedDAO extends GenericDao<AlertAssigned, Long> {
	public List<Long> checkCadreExistsForAlert(List<Long> tdpCadreIds,Long alertId);
	public List<Long> getDeleteAlertAssignedCandidates(Long alertId,Long tdpCadreId);
	public List<Object[]> getAlertAssignedCandidate(Long alertId);
	public List<Object[]> getTdpCadreWiseAssignedAlertDetails(Long tdpCadreId,Date fromDate, Date toDate,Long alertTypeId);
}
