package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertImpactScopeDAO;
import com.itgrids.partyanalyst.model.AlertImpactScope;

public class AlertImpactScopeDAO extends GenericDaoHibernate<AlertImpactScope, Long> implements IAlertImpactScopeDAO {
	public AlertImpactScopeDAO(){
		super(AlertImpactScope.class);
	}
	public List<Object[]> getAllAlertImpactLevel(){
		Query query = getSession().createQuery(" select model.alertImpactScopeId,model.impactScope from AlertImpactScope model " +
											   "  where model.alertImpactScopeId not in (4,6,10,11) order by model.orderNo  ");
		return query.list();
	}
}
