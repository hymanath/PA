package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertTracking;

public class AlertTrackingDAO extends GenericDaoHibernate<AlertTracking, Long>
		implements IAlertTrackingDAO {
	public AlertTrackingDAO() {
		super(AlertTracking.class);
	}

}
