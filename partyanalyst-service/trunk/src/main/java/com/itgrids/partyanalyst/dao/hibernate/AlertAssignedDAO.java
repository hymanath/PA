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
	public List<Object[]> getAlertAssignedCandidate(Long alertId,Long stateId,Long alertTypeId,Date fromDate,Date toDate)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select " +
				   " model.tdpCadre.tdpCadreId, " +
				   " model.tdpCadre.firstname, " +
				   " count(distinct model.alert.alertId) "+
				   " from " +
				   " AlertAssigned model " +
				   " left join model.alert.userAddress.state state " +
				   " where " +
				   " model.alert.isDeleted ='N' and " +
				   " model.isDeleted ='N' and " +
				   " model.alert.alertType.alertTypeId = :alertTypeId and " +
				   " date(model.alert.createdTime) between :fromDate and :toDate and ");
		if(stateId != null && stateId.longValue() > 0L){
			if(stateId.longValue() == 1L){
				str.append(" state.stateId = 1 ");
			}else if(stateId.longValue() == 36L){
				str.append(" state.stateId = 36 ");
			}else{
				str.append(" state.stateId in (1,36) ");
			}
		}
		if(alertId != null && alertId > 0)
			str.append(" and  model.alert.alertId = :alertId");
		Query query = getSession().createQuery(str.toString() +" group by model.tdpCadre.tdpCadreId order by model.tdpCadre.firstname ");
		if(alertId != null && alertId > 0)
			query.setParameter("alertId", alertId); 
		query.setParameter("alertTypeId", alertTypeId); 
		query.setDate("fromDate", fromDate); 
		query.setDate("toDate", toDate);    
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
	//swadhin lenka
	public List<Object[]> getAssignedCandidateList(Long alertId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select  " +
						" alertAssigned.tdpCadre.tdpCadreId, " +//0
						" alertAssigned.tdpCadre.firstname," +//1
						" alertAssigned.tdpCadre.mobileNo, " +//2
						" constituency.name, " +//3
						" alertAssigned.tdpCadre.image " +//4
				     	" from " +
				     	" AlertAssigned alertAssigned " +
				     	" left join alertAssigned.tdpCadre.userAddress userAddress " +
				     	" left join userAddress.constituency constituency " +
				     	" where " +
				     	" alertAssigned.isDeleted = 'N' and " +
				     	" alertAssigned.alert.alertId = :alertId and " +
				     	" constituency.electionScope = 2 and " +
				     	" constituency.deformDate is null ");  
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("alertId", alertId);
		return query.list();  
	}

public List<Long> getAssignedTdpCadreIdsByAlertId(Long alertId){
	
	StringBuilder queryStr = new StringBuilder();
	
	queryStr.append("select distinct model.tdpCadreId from AlertAssigned model where model.alertId =:alertId and model.isDeleted = 'N'");
	
	Query qry = getSession().createQuery(queryStr.toString());
	
	if(alertId != null && alertId.longValue()>0l){
		qry.setParameter("alertId", alertId);
	}
	return qry.list();  
}


public int deleteAlertAssignedByExistingIds(Long tdpCadreId,Long alertId){
	
	StringBuilder sb = new StringBuilder();
	
	sb.append(" delete from AlertAssigned model " +
			  " where model.tdpCadreId = :cadreId " +
			  " and model.alertId = :alertId");
	
	Query qry = getSession().createQuery(sb.toString());
	
	if(tdpCadreId != null && tdpCadreId.longValue()>0l){
		qry.setParameter("cadreId", tdpCadreId);
	}
	if(alertId != null && alertId.longValue()>0l){
		qry.setParameter("alertId", alertId);
	}
	
	return qry.executeUpdate();
}
public List<Long> checkCadreAssignedForAlert(Long alertId)
{
	Query query = getSession().createQuery("select distinct model.tdpCadre.tdpCadreId from AlertAssigned model " +
			" where model.alert.alertId = :alertId and model.isDeleted ='N'");
	query.setParameter("alertId", alertId);
	return query.list();
}
public List<Long> getTotalAlertsRelatedToCadre(Long cadreId,Long stateId,Long alertTypeId,Date fromDate,Date toDate){
	StringBuilder str = new StringBuilder();  
	str.append("select distinct " +
			" model.alert.alertId " +
			" from AlertAssigned model " +
			" left join model.alert.userAddress.state state " +
			" where " +
			" model.tdpCadre.tdpCadreId = :cadreId and " +
			" model.isDeleted ='N' and " +
			" model.tdpCadre.isDeleted = 'N' and " +
			" model.alert.isDeleted ='N' and " +
			" model.alert.alertType.alertTypeId = :alertTypeId and " +
			" date(model.alert.createdTime) between :fromDate and :toDate and ");
	if(stateId != null && stateId.longValue() > 0L){
		if(stateId.longValue() == 1L){
			str.append(" state.stateId = 1 ");
		}else if(stateId.longValue() == 36L){
			str.append(" state.stateId = 36 ");
		}else{
			str.append(" state.stateId in (1,36) ");
		}
	}
	Query query = getSession().createQuery(str.toString());
	
	query.setParameter("cadreId", cadreId);
	query.setParameter("alertTypeId", alertTypeId); 
	query.setDate("fromDate", fromDate); 
	query.setDate("toDate", toDate);   
	return query.list();
}
public List<Object[]> getLeaderDtls(Long assignedId){
	Query query = getSession().createQuery("select distinct model.alert.alertId," +
			" model.alert.description," +
			" model.tdpCadre.mobileNo " +
			" from AlertAssigned model " +
			"  where model.alertAssignedId = :assignedId " +
			"  and model.isDeleted ='N' and model.smsStatus ='N' ");
	query.setParameter("assignedId", assignedId);
	return query.list();
	
}
public int updateAlertSmsStatus(Long assignedId){
	Query query = getSession().createQuery(" update AlertAssigned model set model.smsStatus ='Y' where " +
			"  model.alertAssignedId =:assignedId ");
	query.setParameter("assignedId", assignedId);
	return query.executeUpdate();
}
}
