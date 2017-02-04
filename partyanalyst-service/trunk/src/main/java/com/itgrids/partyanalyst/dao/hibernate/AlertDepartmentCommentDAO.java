package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDepartmentCommentDAO;
import com.itgrids.partyanalyst.model.AlertDepartmentComment;

public class AlertDepartmentCommentDAO extends GenericDaoHibernate<AlertDepartmentComment, Long> implements IAlertDepartmentCommentDAO{

	public AlertDepartmentCommentDAO() {
		super(AlertDepartmentComment.class);
		
	}

}
