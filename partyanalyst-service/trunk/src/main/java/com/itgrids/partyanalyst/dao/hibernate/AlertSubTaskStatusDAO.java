package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertSubTaskStatusDAO;
import com.itgrids.partyanalyst.model.AlertSubTaskStatus;

public class AlertSubTaskStatusDAO extends GenericDaoHibernate<AlertSubTaskStatus, Long> implements IAlertSubTaskStatusDAO{

	public AlertSubTaskStatusDAO() {
		super(AlertSubTaskStatus.class);
		
	}
	
}
