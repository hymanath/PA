package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAbusedCommentsDAO;
import com.itgrids.partyanalyst.dao.impl.IAlertSourceDAO;
import com.itgrids.partyanalyst.model.AbusedComments;
import com.itgrids.partyanalyst.model.AlertSource;

public class AlertSourceDAO extends GenericDaoHibernate<AlertSource, Long>
		implements IAlertSourceDAO {

	public AlertSourceDAO() {
		super(AlertSource.class);
	}

}
