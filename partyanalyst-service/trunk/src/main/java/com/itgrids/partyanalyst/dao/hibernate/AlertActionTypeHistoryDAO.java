package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertActionTypeHistoryDAO;
import com.itgrids.partyanalyst.model.AlertActionTypeHistory;

public class AlertActionTypeHistoryDAO extends GenericDaoHibernate<AlertActionTypeHistory, Long> implements IAlertActionTypeHistoryDAO {

	public AlertActionTypeHistoryDAO(){
		super(AlertActionTypeHistory.class);
	}
}
