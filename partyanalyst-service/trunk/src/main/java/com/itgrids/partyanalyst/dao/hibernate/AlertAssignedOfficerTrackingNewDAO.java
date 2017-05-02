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
    
    public List<Object[]> getAlertStatusForAdminHistory(Long alertId,Long trackTypeId){
    	//0-status,1-comment,2-date,3-officerName,4-mobileNo,5-designationName,6-departmentName
    	Query query = getSession().createQuery(" select model.alertStatus.alertStatus,comment.comment,"
    			+ " model.insertedTime,model.updatedUser.userName, '', ''  ,'',''  "
    			+ " from  AlertAssignedOfficerTrackingNew model "
    			+ " left join model.alertDepartmentComment comment "
    			+ " where model.alertId=:alertId  order by model.insertedTime desc ");
    	query.setParameter("alertId", alertId);
    //	query.setParameter("trackTypeId", trackTypeId);
    	return query.list();
    }
    
    public List<Object[]> getAlertStatusHistory(Long alertId,Long trackTypeId){
    	//0-status,1-comment,2-date,3-officerName,4-mobileNo,5-designationName,6-departmentName
    	Query query = getSession().createQuery(" select model.alertStatus.alertStatus,comment.comment,"
    			+ " model.insertedTime,model.updatedUser.userName, model2.govtOfficer.mobileNo , model2.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName "
    			+ " ,model2.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName,model2.govtOfficer.govtOfficerId  "
    			+ " from GovtDepartmentDesignationOfficerDetailsNew model2, AlertAssignedOfficerTrackingNew model "
    			+ " left join model.alertDepartmentComment comment "
    			+ " where model.alertId=:alertId  and " +
    			"  model.updatedBy = model2.userId  order by model.insertedTime desc ");
    	query.setParameter("alertId", alertId);
    //	query.setParameter("trackTypeId", trackTypeId);
    	return query.list();
    }
    
    /*
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
    */
    public List<Object[]> getCommentsForAlert(Long alertId){//0														//1
    	Query query = getSession().createQuery(" select distinct model.alertDepartmentComment.alertDepartmentCommentId,model.alertDepartmentComment.comment, " +
    	 		" model.alertDepartmentComment.insertedTime, model.updatedUser.userName , model2.govtOfficer.mobileNo , model2.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName "
			+ " ,model2.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
			" model2.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationName  "
    	 		+ " from AlertAssignedOfficerTrackingNew model , GovtDepartmentDesignationOfficerDetailsNew model2  "
    	 		+ " where model.alertId=:alertId and model.govtAlertActionType.govtAlertActionTypeId = 7l and model.updatedBy = model2.userId order by model.insertedTime desc ");
    	 query.setParameter("alertId", alertId);
    	 return query.list();
    }
    
    public List<Object[]> getCommentsForAdminCommentsAlert(Long alertId){//0														//1
    	
    	 Query query = getSession().createQuery(" select distinct alertDepartmentComment.alertDepartmentCommentId,alertDepartmentComment.comment, " +
     	 		" alertDepartmentComment.insertedTime, model.updatedUser.userName , '' ,'' ,''," +
     	 		" model1.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationName "
     	 		+ " from AlertAssignedOfficerTrackingNew model,GovtDepartmentDesignationOfficerDetailsNew model1" +
     	 		" left join  model.alertDepartmentComment alertDepartmentComment "
     	 		+ " where model.updatedBy = model1.userId and" +
     	 		" model.alertId=:alertId and model.govtAlertActionType.govtAlertActionTypeId = 7l" +
     	 		"  and alertDepartmentComment.alertDepartmentCommentId is not null order by model.insertedTime desc ");
     	 		
    	 query.setParameter("alertId", alertId);
    	 return query.list();
    }
    
    public List<Object[]> getDocumentsForAlert(Long alertId){
    	Query query = getSession().createQuery(" select distinct model.alertDepartmentDocument.alertDepartmentDocumentId,model.alertDepartmentDocument.document "
    			+ " from AlertAssignedOfficerTrackingNew model "
    			+ " where model.alertId=:alertId ");
    	query.setParameter("alertId", alertId);
    	return query.list();
    }
    public List<String> getAlertDueDate (Long alertId){
  	  Query query = getSession().createSQLQuery("select distinct date(model.due_date) as dateStr from alert_assigned_officer_tracking_new model where model.alert_id=:alertId and " +
  	  		" model.due_date is not null order by model.updated_time desc ").addScalar("dateStr", Hibernate.STRING);
  	  query.setParameter("alertId", alertId);
  	  return query.list();
  			  
    }
    public List<Object[]> getAlertStatusForAdminHistory1(Long alertId,Long trackTypeId){
    	//0-status,1-comment,2-date,3-officerName,4-mobileNo,5-designationName,6-departmentName
    	Query query = getSession().createQuery(" select model.alertStatus.alertStatus,comment.comment,"
    			+ " model.insertedTime,model.updatedUser.userName, '', ''  ,'',''," +
    			" model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationName  "
    			+ " from  AlertAssignedOfficerTrackingNew model "
    			+ " left join model.alertDepartmentComment comment "
    			+ " where model.alertId=:alertId  " +
    			" and model.govtAlertActionType.govtAlertActionTypeId = :trackTypeId order by model.insertedTime desc ");
    	query.setParameter("alertId", alertId);
        query.setParameter("trackTypeId", trackTypeId);
    	return query.list();
    }
    public List<Object[]> getAlertStatusHistory1(Long alertId,Long trackTypeId){
    	//0-status,1-comment,2-date,3-officerName,4-mobileNo,5-designationName,6-departmentName
    	Query query = getSession().createQuery(" select model.alertStatus.alertStatus,comment.comment,"
    			+ " model.insertedTime,model.updatedUser.userName, model2.govtOfficer.mobileNo , model2.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName "
    			+ " ,model2.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName,model2.govtOfficer.govtOfficerId," +
    			" model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationName "
    			+ " from GovtDepartmentDesignationOfficerDetailsNew model2, AlertAssignedOfficerTrackingNew model "
    			+ " left join model.alertDepartmentComment comment "
    			+ " where model.alertId=:alertId  and " +
    			"  model.govtAlertActionType.govtAlertActionTypeId = :trackTypeId and model.updatedBy = model2.userId  order by model.insertedTime desc ");
    	query.setParameter("alertId", alertId);
    	query.setParameter("trackTypeId", trackTypeId);
    	return query.list();
    }
}
