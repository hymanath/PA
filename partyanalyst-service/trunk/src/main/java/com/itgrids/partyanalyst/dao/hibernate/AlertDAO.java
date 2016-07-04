package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.model.Alert;

public class AlertDAO extends GenericDaoHibernate<Alert, Long> implements
		IAlertDAO {
	public AlertDAO() {
		super(Alert.class);
	}
	
	
	public List<Object[]> getLocationLevelWiseAlerts(List<Long> userTypeIds,Date startDate,Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.alertId),model.regionScopes.regionScopesId,model.regionScopes.scope," +
				" model.alertStatus.alertStatusId,model.alertStatus.alertStatus" +
				" from Alert model where model.isDeleted ='N'and model.alertUserType.alertUserTypeId in(:userTypeIds)");
		if(startDate != null)
		{
			str.append(" and model.createdTime >=:startDate and model.createdTime <=:endDate " );
		}
		str.append(" group by model.regionScopes.regionScopesId,model.alertStatus.alertStatusId");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("userTypeIds", userTypeIds);
		if(startDate!=null){
			query.setDate("startDate", startDate);	
		}
		if(endDate!=null){
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	
	public List<Object[]> getLocationLevelWiseAlertsData(List<Long> userTypeIds,Date fromDate,Date toDate,Long levelId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId,model.description,date(model.createdTime)," +
				" model.alertType.alertType,model.alertUserType.userType,model.alertSeverity.severity,model.alertStatus.alertStatus" +
				" from Alert model where model.isDeleted ='N' and model.impactLevelId=:levelId");
		if(userTypeIds != null && userTypeIds.size() > 0)
			str.append(" and model.alertUserType.alertUserTypeId in(:userTypeIds)");
		if(fromDate != null)
			str.append(" and date(model.createdTime) >=:fromDate and date(model.createdTime) <=:toDate");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("userTypeIds", userTypeIds);
		if(fromDate != null)
		{
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(userTypeIds != null && userTypeIds.size() > 0)
			query.setParameterList("userTypeIds", userTypeIds);
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
}
