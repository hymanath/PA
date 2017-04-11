package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerTrackingNewDAO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTrackingNew;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerNew;

public class AlertAssignedOfficerTrackingNewDAO extends GenericDaoHibernate<AlertAssignedOfficerTrackingNew, Long> implements
		IAlertAssignedOfficerTrackingNewDAO {
    public  AlertAssignedOfficerTrackingNewDAO(){
       super(AlertAssignedOfficerTrackingNew.class);
    }
    
    public List<AlertAssignedOfficerTrackingNew> getAlertHistory(Long alertId){
    	Query query = getSession().createQuery(" select model from AlertAssignedOfficerTrackingNew model where model.alertId=:alertId ");
    	query.setParameter("alertId", alertId);
    	return query.list();
    }
    
    public List<Object[]> getAlertStatusHistory(Long alertId){
    	//0-status,1-comment,2-date,3-officerName,4-mobileNo,5-designationName,6-departmentName
    	Query query = getSession().createQuery(" select model.alertStatus.alertStatus,comment.comment,"
    			+ " date(model.insertedTime),model.govtOfficer.officerName,model.govtOfficer.mobileNo,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName"
    			+ " ,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName  "
    			+ " from AlertAssignedOfficerTrackingNew model "
    			+ " left join model.alertDepartmentComment comment "
    			+ " where model.alertId=:alertId");
    	query.setParameter("alertId", alertId);
    	return query.list();
    }
    
    public List<Object[]> getCommentsForAlert(Long alertId){
    	 Query query = getSession().createQuery(" select model.alertDepartmentComment.alertDepartmentCommentId,model.alertDepartmentComment.comment,date(model.alertDepartmentComment.insertedTime) "
    	 		+ " from AlertAssignedOfficerTrackingNew model "
    	 		+ " where model.alertId=:alertId ");
    	 query.setParameter("alertId", alertId);
    	 return query.list();
    }
    
    public List<Object[]> getDocumentsForAlert(Long alertId){
    	Query query = getSession().createQuery(" select model.alertDepartmentDocument.alertDepartmentDocumentId,model.alertDepartmentDocument.document "
    			+ " from AlertAssignedOfficerTrackingNew model "
    			+ " where model.alertId=:alertId ");
    	query.setParameter("alertId", alertId);
    	return query.list();
    }
    public List<String> getAlertDueDate (Long alertId){
  	  Query query = getSession().createSQLQuery("select distinct date(model.due_date) as dateStr from alert_assigned_officer_tracking_new model where model.alert_id=:alertId and " +
  	  		" model.due_date is not null").addScalar("dateStr", Hibernate.STRING);
  	  query.setParameter("alertId", alertId);
  	  return query.list();
  			  
    }
}
