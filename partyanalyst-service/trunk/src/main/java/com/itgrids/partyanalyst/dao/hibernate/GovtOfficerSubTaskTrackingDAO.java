package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtOfficerSubTaskTrackingDAO;
import com.itgrids.partyanalyst.model.GovtOfficerSubTaskTracking;

public class GovtOfficerSubTaskTrackingDAO extends GenericDaoHibernate<GovtOfficerSubTaskTracking, Long> implements IGovtOfficerSubTaskTrackingDAO{

	public GovtOfficerSubTaskTrackingDAO() {
		super(GovtOfficerSubTaskTracking.class);
		
	}
	
	public List<GovtOfficerSubTaskTracking> getModelForSubTask(Long subTaskId){
		Query query = getSession().createQuery(" select model from GovtOfficerSubTaskTracking model where model.govtAlertSubTaskId=:subTaskId and model.isDeleted='N' ");
		query.setParameter("subTaskId", subTaskId);
		return query.list();
	}
	
	public List<Object[]> getSubTaskStatusHistory(Long subTaskId){
		//0-status,1-comment,2-date,3-officerName,4-mobileNo,5-designationName,6-departmentName
    	Query query = getSession().createQuery(" select model.alertSubTaskStatus.status,comment.comment,"
    			+ " model.insertedTime,model.govtAlertSubTask.subTaskGovtOfficer.officerName,model.govtAlertSubTask.subTaskGovtOfficer.mobileNo,"
    			+ " model.govtAlertSubTask.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName"
    			+ " ,model.govtAlertSubTask.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName  "
    			+ " from GovtOfficerSubTaskTracking model "
    			+ " left join model.alertDepartmentComment comment "
    			+ " where model.govtAlertSubTaskId=:subTaskId and comment is not null order by model.insertedTime desc ");
    	query.setParameter("subTaskId", subTaskId);
    	return query.list();
	}
	
	public List<Object[]> getSubTasksStatusHistory(List<Long> subTaskIdsList){
		//0-status,1-comment,2-date,3-officerName,4-mobileNo,5-designationName,6-departmentName
    	Query query = getSession().createQuery(" select  distinct model.govtAlertSubTaskId, model.alertSubTaskStatus.status,comment.comment,"
    			+ " model.insertedTime,model.govtAlertSubTask.subTaskGovtOfficer.officerName,model.govtAlertSubTask.subTaskGovtOfficer.mobileNo,"
    			+ " model.govtAlertSubTask.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName"
    			+ " ,model.govtAlertSubTask.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName , " +
    			" 	alertDepartmentDocument.document  "
    			+ " from GovtOfficerSubTaskTracking model "
    			+ " left join model.alertDepartmentComment comment " +
    			"   left join model.alertDepartmentDocument alertDepartmentDocument "
    			+ " where model.govtAlertSubTaskId in (:subTaskIdsList) ");
    	query.setParameterList("subTaskIdsList", subTaskIdsList);
    	return query.list();
	}
	
	public List<Object[]> getCommentsForSubTasks(List<Long> subTaskIds){
		Query query = getSession().createQuery(" select model.govtAlertSubTaskId,count(model.alertDepartmentCommentId) "
				+ " from GovtOfficerSubTaskTracking model "
				+ " where model.govtAlertSubTaskId in (:subTaskIds) and model.isDeleted='N' and model.govtAlertSubTask.isDeleted='N' and model.alertDepartmentCommentId is not null "
				+ " group by model.govtAlertSubTaskId ");
		query.setParameterList("subTaskIds", subTaskIds);
		return query.list();
	}
	
	public List<Object[]> getCommentDetialsForSubTasks(Long subTaskId){
		Query query = getSession().createQuery(" select model.alertDepartmentCommentId,model.alertDepartmentComment.comment,date(model.alertDepartmentComment.insertedTime) "
				+ " from GovtOfficerSubTaskTracking model "
				+ " where model.govtAlertSubTaskId = :subTaskId and model.isDeleted='N' and model.govtAlertSubTask.isDeleted='N' ");
		query.setParameter("subTaskId", subTaskId);
		return query.list();
	}
}
