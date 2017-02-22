package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertRegionScopesDAO;
import com.itgrids.partyanalyst.model.AlertRegionScopes;

public class AlertRegionScopesDAO extends GenericDaoHibernate<AlertRegionScopes, Long> implements IAlertRegionScopesDAO{

	public AlertRegionScopesDAO() {
		super(AlertRegionScopes.class);
		
	}

}
