package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertCommentAssigneeDAO;

import com.itgrids.partyanalyst.model.AlertCommentAssignee;

public class AlertCommentAssigneeDAO extends
		GenericDaoHibernate<AlertCommentAssignee, Long> implements
		IAlertCommentAssigneeDAO {
	public AlertCommentAssigneeDAO() {
		super(AlertCommentAssignee.class);
	}

}
