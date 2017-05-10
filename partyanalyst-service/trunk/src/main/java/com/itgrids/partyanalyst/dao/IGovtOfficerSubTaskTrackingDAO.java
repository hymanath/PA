package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtOfficerSubTaskTracking;

public interface IGovtOfficerSubTaskTrackingDAO extends GenericDao<GovtOfficerSubTaskTracking,Long>{
	public List<GovtOfficerSubTaskTracking> getModelForSubTask(Long subTaskId);
	public List<Object[]> getSubTaskStatusHistory(Long subTaskId);
	public List<Object[]> getCommentsForSubTasks(List<Long> subTaskIds);
	public List<Object[]> getCommentDetialsForSubTasks(Long subTaskId);
	public List<Object[]> getSubTasksStatusHistory(List<Long> subTaskIdsList);
	public List<Object[]> getSubTasksCommentsAndStatusHistory(List<Long> subTaskIdsList);
	public List<Object[]> getSubTaskAlertTrackingDtls(Long govtAlertSubTaskId);
}
