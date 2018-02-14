package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
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
	@SuppressWarnings("unchecked")
	public Long totalCallCenterCallForRwsDept(Date fromDate,Date toDate){
		StringBuilder str = new StringBuilder();
		str.append(" SELECT COUNT(AC.alert_caller_id) as count" +
				" FROM alert A " +
				" LEFT JOIN alert_caller_relation ACR ON A.alert_id = ACR.alert_id" +
				" LEFT  JOIN alert_caller AC ON ACR.alert_caller_id = AC.alert_caller_id" +
				" LEFT OUTER JOIN alert_category CG ON A.alert_category_id = CG.alert_category_id" +
				" WHERE" +
				" A.is_deleted = 'N' AND ACR.is_deleted = 'N' AND A.govt_department_id =:govtDeptId " +
				" and CG.alert_category_id =:categoryId ");
	 		
	 		if(fromDate !=null && toDate !=null){
	 			str.append(" and date(A.created_time) between :fromDate and :toDate ");
	 		}
	 	Query query = getSession().createSQLQuery(str.toString())
	 			.addScalar("count",Hibernate.LONG);
	 		query.setParameter("govtDeptId",49l);
	 		query.setParameter("categoryId",4l);
	 		if(fromDate !=null && toDate !=null){
	 			query.setParameter("fromDate",fromDate);
	 			query.setParameter("toDate",toDate);
	 		}
	 	return (Long) query.uniqueResult();
	 }
}
