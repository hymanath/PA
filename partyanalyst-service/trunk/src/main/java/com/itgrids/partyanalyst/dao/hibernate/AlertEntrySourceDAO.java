package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertEntrySourceDAO;
import com.itgrids.partyanalyst.model.AlertEntrySource;

public class AlertEntrySourceDAO extends GenericDaoHibernate<AlertEntrySource, Long> implements IAlertEntrySourceDAO{

	public AlertEntrySourceDAO() {
		super(AlertEntrySource.class);
		
	}

}
