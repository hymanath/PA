package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertAssignedDAO;
import com.itgrids.partyanalyst.model.AlertAssigned;

public class AlertAssignedDAO extends GenericDaoHibernate<AlertAssigned, Long> implements IAlertAssignedDAO{

	public AlertAssignedDAO() {
		super(AlertAssigned.class);
		
	}
	
	
	public List<Long> checkCadreExistsForAlert(List<Long> tdpCadreIds,Long alertId)
	{
		Query query = getSession().createQuery("select distinct model.tdpCadre.tdpCadreId from AlertAssigned model" +
				" where model.alert.alertId = :alertId and model.tdpCadre.tdpCadreId in(:tdpCadreIds) and model.isDeleted ='N'");
		query.setParameter("alertId", alertId);
		query.setParameterList("tdpCadreIds", tdpCadreIds);
		return query.list();
	}
	public List<Long> getDeleteAlertAssignedCandidates(Long alertId,Long tdpCadreId)
	{
		Query query = getSession().createQuery("select  model.alertAssignedId from AlertAssigned model" +
				" where model.isDeleted ='N' and  model.alert.alertId = :alertId and model.tdpCadre.tdpCadreId =:tdpCadreId ");
		query.setParameter("alertId", alertId);
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
	public List<Object[]> getAlertAssignedCandidate(Long alertId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.tdpCadre.tdpCadreId, model.tdpCadre.firstname"+
				" from AlertAssigned model where model.alert.isDeleted ='N' and model.isDeleted ='N' ");
		if(alertId != null && alertId > 0)
			str.append(" and  model.alert.alertId = :alertId");
		Query query = getSession().createQuery(str.toString() +" order by model.tdpCadre.firstname ");
		if(alertId != null && alertId > 0)
		query.setParameter("alertId", alertId);
		return query.list();
	}

	/*
	 * Author 	: 	Srishailam Pittala
	 * Date 	:	29th Dec,2016
	 * Description : to get tdpCadre Wise assigned alert details
	 * */
	
	public List<Object[]> getTdpCadreWiseAssignedAlertDetails(Long tdpCadreId,Date fromDate, Date toDate,Long alertTypeId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.alertCategoryId,model.alertTypeId,model.alertStatusId,count(distinct model.alertId) from Alert model,AlertAssigned model1 " +
				" where  model.alertId = model1.alertId and " +
				" model.isDeleted ='N' and model1.isDeleted ='N' and " +
				" model1.tdpCadreId = :tdpCadreId  ");
		
		if(alertTypeId != null && alertTypeId.longValue()>0L)
			queryStr.append(" and model.alertTypeId =:alertTypeId");
		
		if(fromDate != null && toDate != null ){
			queryStr.append(" and ( date(model.createdTime) between :fromDate and :toDate) ");
		}
		queryStr.append("  group by model.alertCategoryId,model.alertTypeId,model.alertStatusId ");
		queryStr.append("  order by model.alertCategoryId,model.alertTypeId,model.alertStatusId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameter("tdpCadreId", tdpCadreId);
		
		if(alertTypeId != null && alertTypeId.longValue()>0L)
			query.setParameter("alertTypeId", alertTypeId);
		if(fromDate != null && toDate != null ){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
	
	/*
	 * Author 	: 	Srishailam Pittala
	 * Date 	:	30th Dec,2016
	 * Description : to get tdpCadre Wise assigned alert details
	 * */
	
public List<Object[]> getCandidateAlertDetailsBySearch(Long tdpCadreId,Date fromDate, Date toDate,Long alertTypeId,Long categoryId,Long statusId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.alertId,model.description,date(model.createdTime),date(model.updatedTime), " +
				" model.alertStatus.alertStatusId,model.alertStatus.alertStatus,model.alertImpactScope.alertImpactScopeId,model.alertImpactScope.impactScope   " +
				" from Alert model,AlertAssigned model1 " +
				" where  model.alertId = model1.alertId and " +
				" model.isDeleted ='N' and model1.isDeleted='N' and  " +
				" model1.tdpCadreId = :tdpCadreId  ");
		
		if(alertTypeId != null && alertTypeId.longValue()>0L)
			queryStr.append(" and model.alertTypeId =:alertTypeId");
		if(categoryId != null && categoryId.longValue()>0L)
			queryStr.append(" and model.alertCategoryId =:categoryId ");
		if(statusId != null && statusId.longValue()>0L)
			queryStr.append(" and model.alertStatusId =:statusId ");
		
		if(fromDate != null && toDate != null ){
			queryStr.append(" and ( date(model.createdTime) between :fromDate and :toDate) ");
		}
		queryStr.append("  order by model.alertCategoryId,model.alertTypeId,model.alertStatusId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameter("tdpCadreId", tdpCadreId);
		
		if(alertTypeId != null && alertTypeId.longValue()>0L)
			query.setParameter("alertTypeId", alertTypeId);
		if(categoryId != null && categoryId.longValue()>0L)
			query.setParameter("categoryId", categoryId);
		if(statusId != null && statusId.longValue()>0L)
			query.setParameter("statusId", statusId);
		
		if(fromDate != null && toDate != null ){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}

}

