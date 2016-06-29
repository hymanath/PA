package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDAO;
import com.itgrids.partyanalyst.model.AlertComment;
import com.itgrids.partyanalyst.model.AlertTracking;

public class AlertCommentDAO extends GenericDaoHibernate<AlertComment, Long>
		implements IAlertCommentDAO {
	public AlertCommentDAO() {
		super(AlertComment.class);
	}

}
