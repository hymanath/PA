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
	
	
	public List<Object[]> getLocationLevelWiseAlerts(List<Long> sourceIds,Date startDate,Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.alertId),model.regionScopes.regionScopesId,model.regionScopes.scope," +
				" model.alertStatus.alertStatusId,model.alertStatus.alertStatus" +
				" from Alert model where model.isDeleted ='N'and model.alertSource.alertSourceId in(:sourceIds)");
		if(startDate != null)
		{
			str.append(" and date(model.createdTime) >=:startDate and date(model.createdTime) <=:endDate " );
		}
		str.append(" group by model.regionScopes.regionScopesId,model.alertStatus.alertStatusId");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("sourceIds", sourceIds);
		if(startDate!=null){
			query.setDate("startDate", startDate);	
		}
		if(endDate!=null){
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	
	public List<Object[]> getLocationLevelWiseAlertsData(List<Long> sourceIds,Date fromDate,Date toDate,Long levelId,Long statusId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId,model.description,date(model.createdTime)," +
				" model.alertType.alertType,model.alertSource.source,model.alertSeverity.severity,model.regionScopes.regionScopesId,model.regionScopes.scope," +
				" model.alertStatus.alertStatusId,model.alertStatus.alertStatus");
		str.append(" ,tehsil.tehsilId,tehsil.tehsilName , panc.panchayatId, panc.panchayatName,localElectionBody.localElectionBodyId,localElectionBody.name, district.districtId,district.districtName, electionType.electionType ");
		str.append(" ,constituency.constituencyId,constituency.name");
		str.append(" ,state.stateId,state.stateName");
		str.append(" ,ward.constituencyId,ward.name");
		str.append(" from Alert model left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
		str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.userAddress.district district ");
		str.append(" left join model.userAddress.state state ");
		str.append(" left join model.userAddress.ward ward ");
		str.append(" where model.isDeleted ='N'");
		if(levelId != null && levelId > 0)
		str.append(" and model.impactLevelId=:levelId");
		if(sourceIds != null && sourceIds.size() > 0)
			str.append(" and model.alertSource.alertSourceId in(:sourceIds)");
		if(fromDate != null)
			str.append(" and date(model.createdTime) >=:fromDate and date(model.createdTime) <=:toDate");
		if(statusId != null && statusId > 0)
			str.append(" and model.alertStatus.alertStatusId = :statusId");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("sourceIds", sourceIds);
		if(fromDate != null)
		{
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(sourceIds != null && sourceIds.size() > 0)
			query.setParameterList("sourceIds", sourceIds);
		if(statusId != null && statusId > 0)
			query.setParameter("statusId", statusId);
		if(levelId != null && levelId > 0)
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
	public List<Object[]> getAlertsData(Long alertId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId,model.description,date(model.createdTime)," +
				" model.alertType.alertType,model.alertSource.source,model.alertSeverity.severity,model.regionScopes.regionScopesId,model.regionScopes.scope," +
				" model.alertStatus.alertStatusId,model.alertStatus.alertStatus");
		str.append(" ,tehsil.tehsilId,tehsil.tehsilName , panc.panchayatId, panc.panchayatName,localElectionBody.localElectionBodyId,localElectionBody.name, district.districtId,district.districtName, electionType.electionType ");
		str.append(" ,constituency.constituencyId,constituency.name");
		str.append(" ,state.stateId,state.stateName");
		str.append(" ,ward.constituencyId,ward.name");
		str.append(" from Alert model left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
		str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.userAddress.district district ");
		str.append(" left join model.userAddress.state state ");
		str.append(" left join model.userAddress.ward ward ");
		str.append(" where model.isDeleted ='N' and model.alertId=:alertId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("alertId", alertId);
		return query.list();
	}
	
}
