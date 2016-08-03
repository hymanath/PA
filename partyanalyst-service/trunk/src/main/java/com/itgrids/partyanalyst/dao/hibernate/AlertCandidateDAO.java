package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertCandidate;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertCandidateDAO extends
		GenericDaoHibernate<AlertCandidate, Long> implements IAlertCandidateDAO {
	public AlertCandidateDAO() {
		super(AlertCandidate.class);
	}
	public List<Object[]> getAlertCandidateCount(List<Long> alertIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.tdpCadreId),model.alert.alertId" +
				" from AlertCandidate model where model.alert.isDeleted ='N' ");
		if(alertIds != null && alertIds.size() > 0)
			str.append(" and model.alert.alertId in(:alertIds)");
		str.append(" group by model.alert.alertId");
		Query query = getSession().createQuery(str.toString());
		if(alertIds != null && alertIds.size() > 0)
		query.setParameterList("alertIds", alertIds);
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
		str.append(" where model.isDeleted ='N' and model.impactLevelId=:levelId");
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
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
	
	public List<Object[]> getAlertCandidatesData(List<Long> alertIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname");
		str.append(" ,tehsil.tehsilId,tehsil.tehsilName , panc.panchayatId, panc.panchayatName,localElectionBody.localElectionBodyId,localElectionBody.name, district.districtId,district.districtName, electionType.electionType ");
		str.append(" ,constituency.constituencyId,constituency.name");
		str.append(" ,state.stateId,state.stateName");
		str.append(" ,ward.constituencyId,ward.name");
		str.append(",model.alertImpact.alertImpactId,model.alertImpact.impact");
		str.append(" ,model.tdpCadre.image");
		str.append(" ,model.tdpCadre.mobileNo");
		str.append(" from AlertCandidate model left join model.tdpCadre.userAddress.panchayat panc ");
		str.append(" left join model.tdpCadre.userAddress.tehsil tehsil ");
		str.append(" left join model.tdpCadre.userAddress.constituency constituency ");
		str.append(" left join model.tdpCadre.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.tdpCadre.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.tdpCadre.userAddress.district district ");
		str.append(" left join model.tdpCadre.userAddress.state state ");
		str.append(" left join model.tdpCadre.userAddress.ward ward ");
	
		str.append(" where model.alert.isDeleted ='N' and model.alert.alertId in(:alertIds) and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=:enrollmentYear");
		str.append(" group by model.alertId,model.tdpCadre.tdpCadreId");
		Query query = getSession().createQuery(str.toString());
		if(alertIds != null && alertIds.size() > 0)
		query.setParameterList("alertIds", alertIds);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
		return query.list();
	}
	public List<Object[]> getAlertAssignedCandidates(List<Long> alertIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname");
		str.append(" ,tehsil.tehsilId,tehsil.tehsilName , panc.panchayatId, panc.panchayatName,localElectionBody.localElectionBodyId,localElectionBody.name, district.districtId,district.districtName, electionType.electionType ");
		str.append(" ,constituency.constituencyId,constituency.name");
		str.append(" ,state.stateId,state.stateName");
		str.append(" ,ward.constituencyId,ward.name");
		str.append(" ,model.tdpCadre.image,model.tdpCadre.mobileNo");
		str.append(" from AlertAssigned model left join model.tdpCadre.userAddress.panchayat panc ");
		str.append(" left join model.tdpCadre.userAddress.tehsil tehsil ");
		str.append(" left join model.tdpCadre.userAddress.constituency constituency ");
		str.append(" left join model.tdpCadre.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.tdpCadre.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.tdpCadre.userAddress.district district ");
		str.append(" left join model.tdpCadre.userAddress.state state ");
		str.append(" left join model.tdpCadre.userAddress.ward ward ");
	
		str.append(" where model.alert.isDeleted ='N' and model.isDeleted ='N' and model.alert.alertId in(:alertIds) and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=:enrollmentYear");
		str.append(" group by model.alertId,model.tdpCadre.tdpCadreId");
		Query query = getSession().createQuery(str.toString());
		if(alertIds != null && alertIds.size() > 0)
		query.setParameterList("alertIds", alertIds);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
		return query.list();
	}
	
}
