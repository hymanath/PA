package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertCommentAssignee;

public interface IAlertCommentAssigneeDAO extends
		GenericDao<AlertCommentAssignee, Long> {
	public List<Object[]> getAlertCommentAssignedCandidates(Long alertId);
	public List<Object[]> getAssignedCandidateAlertComment(Long alertId,List<Long> tdpCadreIds);
}
