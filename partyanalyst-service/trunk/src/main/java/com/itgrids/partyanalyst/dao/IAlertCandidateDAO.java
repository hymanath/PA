package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertCandidate;

public interface IAlertCandidateDAO extends GenericDao<AlertCandidate, Long> {
	
	public List<Object[]> getAlertCandidateCount(List<Long> alertIds);
	public List<Object[]> getAlertCandidatesData(List<Long> alertIds);
	public List<Object[]> getAlertAssignedCandidates(List<Long> alertIds);
	public List<Object[]> getInvolvedCandidateDetailsOfAlert(List<Long> alertIds);
	public List<Object[]> getAlertNewsCandidateCount(List<Long> alertIds);
	public List<Long> getAlertCandidatesForUpdate(Long alertId);
	public int deleteAlertCandidatesForUpdate(List<Long> alertCandidateIds);
	public List<Object[]> getTdpCadreWiseInvoledAlertDetails(Long tdpCadreId,Date fromDate, Date toDate,Long alertTypeId);
}
