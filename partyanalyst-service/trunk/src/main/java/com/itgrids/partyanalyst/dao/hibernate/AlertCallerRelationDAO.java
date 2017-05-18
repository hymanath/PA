package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertCallerRelationDAO;
import com.itgrids.partyanalyst.model.AlertCallerRelation;

public class AlertCallerRelationDAO extends GenericDaoHibernate<AlertCallerRelation, Long> implements IAlertCallerRelationDAO{

	public AlertCallerRelationDAO() {
		super(AlertCallerRelation.class);
		
	}

	public List<Object[]> getAlertCallerDetailsByAlert(Long alertId){
		Query query = getSession().createQuery("select distinct model.alert.alertCallerType.alertCallerTypeId," +
												" model.alert.alertCallerType.callerType," +
												" model.alertCaller.alertCallerId," +
												" model.alertCaller.callerName," +
												" model.alertCaller.mobileNo," +
												" model.alertCaller.address" +
												" from AlertCallerRelation model" +
												" where model.alert.alertId = :alertId" +
												" and model.isDeleted = 'N'" +
												" and model.alert.isDeleted = 'N'");
		
		query.setParameter("alertId", alertId);
		return query.list();
	}
	
	public List<Object[]> getAlertCallerDetailsForAlerts(List<Long> alertIds){
		Query query = getSession().createQuery("select distinct model.alert.alertId," +
												" model.alertCaller.alertCallerId," +
												" model.alertCaller.callerName," +
												" model.alertCaller.mobileNo," +
												" model.alertCaller.address" +
												" from AlertCallerRelation model" +
												" where model.alert.alertId in (:alertIds)" +
												" and model.isDeleted = 'N'" +
												" and model.alert.isDeleted = 'N'");
		
		query.setParameterList("alertIds", alertIds);
		return query.list();
	}
	
	public Long getMaxCallerOrderForAlert(Long alertId){
		Query query = getSession().createQuery("select max(model.callerOrder)" +
												" from AlertCallerRelation model" +
												" where model.alert.alertId = :alertId" +
												" and model.isDeleted = 'N'" +
												" and model.alert.isDeleted = 'N'");
		query.setParameter("alertId", alertId);
		return (Long) query.uniqueResult();
	}
}
