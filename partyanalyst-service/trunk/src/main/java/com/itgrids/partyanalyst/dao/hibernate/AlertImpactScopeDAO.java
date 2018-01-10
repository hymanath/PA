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
											   "  where model.alertImpactScopeId not in (4,10,11) order by model.orderNo  ");
		return query.list();
	}
	
	public List<Object[]> getAlertImpactScope()
	{
		Query query = getSession().createQuery("select model.alertImpactScopeId,model.impactScope from AlertImpactScope model order by model.orderNo ");
		return query.list();
	}	
	public List<Object[]> getAlertImpactScopeByImpactId(List<Long> alertImpactLevelIds)
	{
		Query query = getSession().createQuery(" select model.alertImpactScopeId,model.impactScope " +
											  " from AlertImpactScope model where model.alertImpactScopeId in(:alertImpactLevelIds)" +
											  " order by model.orderNo ");
		query.setParameterList("alertImpactLevelIds", alertImpactLevelIds);
		return query.list();
	}	
	public List<Long> getIdOfName(String impactScope){
   		Query query = getSession().createQuery(" select " +
   				" model.alertImpactScopeId " +
   				" from AlertImpactScope model where model.impactScope=:impactScope");
   		query.setParameter("impactScope", impactScope);
   		return query.list();
     }
	
}
