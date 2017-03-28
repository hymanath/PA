package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertActionTypeCommentDAO;
import com.itgrids.partyanalyst.model.AlertActionTypeComment;

public class AlertActionTypeCommentDAO extends GenericDaoHibernate<AlertActionTypeComment, Long> implements IAlertActionTypeCommentDAO {

	public AlertActionTypeCommentDAO(){
		super(AlertActionTypeComment.class);
	}
}
