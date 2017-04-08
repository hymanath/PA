package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerTrackingNewDAO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTrackingNew;

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
}
