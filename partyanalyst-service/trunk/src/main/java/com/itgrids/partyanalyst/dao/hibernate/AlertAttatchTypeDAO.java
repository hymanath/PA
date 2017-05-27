package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertAttatchTypeDAO;
import com.itgrids.partyanalyst.model.AlertAttatchType;

public class AlertAttatchTypeDAO extends
		GenericDaoHibernate<AlertAttatchType, Long> implements
		IAlertAttatchTypeDAO {
	public AlertAttatchTypeDAO() {
		super(AlertAttatchType.class);
		
	}
}
