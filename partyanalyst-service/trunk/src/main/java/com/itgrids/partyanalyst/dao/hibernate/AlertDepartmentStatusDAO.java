package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertDepartmentStatusDAO;
import com.itgrids.partyanalyst.model.AlertDepartmentStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertDepartmentStatusDAO extends GenericDaoHibernate<AlertDepartmentStatus, Long> implements IAlertDepartmentStatusDAO{

	public AlertDepartmentStatusDAO() {
		super(AlertDepartmentStatus.class);
		
	}
	public List<Object[]> getAllStatus(){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct" +
						" alertDepartmentStatus.alertStatus.alertStatusId, " +
						" alertDepartmentStatus.alertStatus.alertStatus " +
						" from " +
						" AlertDepartmentStatus alertDepartmentStatus " +
						" where " +
						" alertDepartmentStatus.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		//queryStr.append(" and alertDepartmentStatus.govtDepartment.govtDepartmentId in (:govtDepartmentId)");
		
		queryStr.append(" order by alertDepartmentStatus.alertStatus.statusOrder ");
		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
	public List<Object[]> getAllStatusForDepartment(Long govtDepartmentId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" alertDepartmentStatus.alertStatus.alertStatusId, " +
						" alertDepartmentStatus.alertStatus.alertStatus " +
						" from " +
						" AlertDepartmentStatus alertDepartmentStatus " +
						" where " +
						" alertDepartmentStatus.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		queryStr.append(" and alertDepartmentStatus.govtDepartment.govtDepartmentId =:govtDepartmentId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("govtDepartmentId", govtDepartmentId);
		return query.list();
	}
		
   public List<Object[]> getAlertStatusByAlertType(Long alertTypeId){
	     StringBuilder queryStr = new StringBuilder();
	     queryStr.append(" select distinct model.alertStatus.alertStatusId,model.alertStatus.alertStatus from AlertDepartmentStatus model " +
	     				"  where model.alertType.alertTypeId in (:alertTypeId)  ");
	     Query query = getSession().createQuery(queryStr.toString());
	      query.setParameter("alertTypeId", alertTypeId);
	      return query.list();
	    
   }
   
   public List<Object[]> getStatusForDepartments(List<Long> deptIds){
	   StringBuilder sb = new StringBuilder();
	   sb.append("select distinct model.alertStatus.alertStatusId," +
	   				" model.alertStatus.alertStatus" +
	   				" from AlertDepartmentStatus model" +
	   				" where model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+")");
	   if(deptIds != null && !deptIds.isEmpty())
		   sb.append(" and model.govtDepartment.govtDepartmentId in (:deptIds)");
	   
	   Query query = getSession().createQuery(sb.toString());
	   if(deptIds != null && !deptIds.isEmpty())
		   query.setParameterList("deptIds", deptIds);
	   return query.list();
   }
   
   public List<Object[]> getStatusWithoutPending(){
	   Query query = getSession().createQuery("select distinct model.alertStatus.alertStatusId," +
	   											" model.alertStatus.alertStatus," +
	   											" model.alertStatus.color" +
	   											" from AlertDepartmentStatus model" +
	   											" where model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+")" +
	   											" and model.alertStatus.alertStatusId > 1");
	   return query.list();
   }
   public List<Object[]> getAlertStatusByAlertTypeId(Long alertTypeId){
	   StringBuilder queryStr = new StringBuilder();
	       queryStr.append(" select distinct model.alertStatus.alertStatusId,model.alertStatus.alertStatus,model.alertStatus.color," +
	       		" model.alertStatus.shortName from AlertDepartmentStatus model ");
	     if(alertTypeId != null && alertTypeId.longValue() > 0l){
	        queryStr.append(" where model.alertType.alertTypeId=:alertTypeId ");	 
	     }
	      Query query = getSession().createQuery(queryStr.toString());
	      if(alertTypeId != null && alertTypeId.longValue() > 0l){
	    	  query.setParameter("alertTypeId", alertTypeId);  
	      }
	      return query.list();   
   }
}
