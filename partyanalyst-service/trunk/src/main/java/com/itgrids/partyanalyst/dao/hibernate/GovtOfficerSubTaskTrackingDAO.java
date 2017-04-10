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
    			+ " date(model.insertedTime),model.govtAlertSubTask.subTaskGovtOfficer.officerName,model.govtAlertSubTask.subTaskGovtOfficer.mobileNo,"
    			+ " model.govtAlertSubTask.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName"
    			+ " ,model.govtAlertSubTask.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName  "
    			+ " from GovtOfficerSubTaskTracking model "
    			+ " left join model.alertDepartmentComment comment "
    			+ " where model.govtAlertSubTaskId=:subTaskId");
    	query.setParameter("subTaskId", subTaskId);
    	return query.list();
	}
	
}
