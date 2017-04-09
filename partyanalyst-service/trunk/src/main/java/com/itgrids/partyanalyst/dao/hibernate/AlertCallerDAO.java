package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.AlertCaller;

import com.itgrids.partyanalyst.dao.IAlertCallerDAO;

public class AlertCallerDAO extends GenericDaoHibernate<AlertCaller, Long> implements IAlertCallerDAO{

	public AlertCallerDAO() {
		super(AlertCaller.class);
		
	}

}
