package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
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
}
