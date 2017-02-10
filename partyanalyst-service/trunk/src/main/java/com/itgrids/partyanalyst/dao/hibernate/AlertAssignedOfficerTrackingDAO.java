package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerTrackingDAO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTracking;

public class AlertAssignedOfficerTrackingDAO extends GenericDaoHibernate<AlertAssignedOfficerTracking, Long> implements IAlertAssignedOfficerTrackingDAO{

	public AlertAssignedOfficerTrackingDAO() {
		super(AlertAssignedOfficerTracking.class);
		
	}

	public List<Object[]> getStatusWiseTrackingComments(Long alertId){
		Query query = getSession().createQuery("select model.alertStatusId," +
											" model.alertStatus.alertStatus," +
											" model1.alertDepartmentComment.alertDepartmentCommentId," +
											" model1.alertDepartmentComment.comment" +
											" from AlertAssignedOfficerTracking model,AlertAssignedOfficerAction model1" +
											" where model.alertId = model1.alertId" +
											" and model.alertAssignedOfficerId = model1.alertAssignedOfficerId" +
											" and model.alertId = :alertId" +
											" and model1.isDeleted = 'N'" +
											" order by model.alertStatusId");
		query.setParameter("alertId", alertId);
		return query.list();
	}
}
