package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertImpactDAO;
import com.itgrids.partyanalyst.model.AlertImpact;

public class AlertImpactDAO extends GenericDaoHibernate<AlertImpact, Long>
		implements IAlertImpactDAO {
	public AlertImpactDAO() {
		super(AlertImpact.class);
	}
}
