package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertComment;

public interface IAlertCommentDAO extends GenericDao<AlertComment, Long> {
	public List<Object[]> getCommentsOfAlert(Long alertId);
}
