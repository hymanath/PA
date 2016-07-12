package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAbusedCommentsDAO;
import com.itgrids.partyanalyst.dao.impl.IAlertSourceUserDAO;
import com.itgrids.partyanalyst.model.AbusedComments;
import com.itgrids.partyanalyst.model.AlertSourceUser;

public class AlertSourceUserDAO extends
		GenericDaoHibernate<AlertSourceUser, Long> implements
		IAlertSourceUserDAO {

	public AlertSourceUserDAO() {
		super(AlertSourceUser.class);
	}
}
