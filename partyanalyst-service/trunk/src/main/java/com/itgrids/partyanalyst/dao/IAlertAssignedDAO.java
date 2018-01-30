package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertAssigned;

public interface IAlertAssignedDAO extends GenericDao<AlertAssigned, Long> {
	public List<Long> checkCadreExistsForAlert(List<Long> tdpCadreIds,Long alertId);
	public List<Long> getDeleteAlertAssignedCandidates(Long alertId,Long tdpCadreId);
	public List<Object[]> getAlertAssignedCandidate(Long alertId);
	public List<Object[]> getTdpCadreWiseAssignedAlertDetails(Long tdpCadreId,Date fromDate, Date toDate,Long alertTypeId);
	public List<Object[]> getCandidateAlertDetailsBySearch(Long tdpCadreId,Date fromDate, Date toDate,Long alertTypeId,Long categoryId,Long statusId);
	public List<Object[]> getAssignedCandidateList(Long alertId);
	public List<Long> getAssignedTdpCadreIdsByAlertId(Long alertId);
	public int deleteAlertAssignedByExistingIds(Long tdpCadreId,Long alertId);
	public List<Long> checkCadreAssignedForAlert(Long alertId);
	public List<Long> getTotalAlertsRelatedToCadre(Long cadreId,Long stateId,Long alertTypeId,Date fromDate,Date toDate);
	public List<Object[]> getLeaderDtls(Long assignedId);
	public int updateAlertSmsStatus(Long assignedId);
	public List<Object[]> getAlertAssignedCandidateForDashBoard(Long alertId,Long stateId,Long alertTypeId,Date fromDate,Date toDate);
	public List<Object[]> getAssignedAlertsSummary(List<Long> cadreIds);
	public List<Object[]> getSmsTdpCadreDetails();
	public List<Object[]> getDesignationWiseAssignedAlertys(Date fromDate ,Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year);
	public List<Object[]> getDesignationWiseAssignedAlerts(Date fromDate , Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year,String type,List<Long> statusIdsList,Long designationId);
	public List<Long> getAlertAssignedCandidateInfo(Long alertId);
	public void flushAndclearSession();
}
