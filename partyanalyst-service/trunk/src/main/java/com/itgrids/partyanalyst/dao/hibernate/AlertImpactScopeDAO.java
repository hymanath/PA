package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertImpactScopeDAO;
import com.itgrids.partyanalyst.model.AlertImpactScope;

public class AlertImpactScopeDAO extends GenericDaoHibernate<AlertImpactScope, Long> implements IAlertImpactScopeDAO {
	public AlertImpactScopeDAO(){
		super(AlertImpactScope.class);
	}
}
