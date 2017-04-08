package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtAlertSubTask;

public interface IGovtAlertSubTaskDAO extends GenericDao<GovtAlertSubTask, Long> {
	
	public List<Object[]> getDistrictOfficerAlertsSubTasksCount(Long govtDepDesigOffcrId,Long govtOffcrId,String countType,String type);
 	public List<Object[]> getSubTaskAlertAssignCountsForDeptWiseDetails(Date fromDate, Date toDate,int startIndex,int maxIndex);
}
