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
											" model1.alertDepartmentComment.comment," +
											" model1.alertDepartmentComment.insertedUser.userId," +
											" model1.alertDepartmentComment.insertedUser.userName," +
											" date(model1.alertDepartmentComment.insertedTime)," +
											" model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName" +
											" from AlertAssignedOfficerTracking model,AlertAssignedOfficerAction model1" +
											" where model.alertId = model1.alertId" +
											" and model.alertAssignedOfficerId = model1.alertAssignedOfficerId" +
											" and model.alertStatus.alertStatusId = model1.alertStatus.alertStatusId" +
											" and model.alertId = :alertId" +
											" and model1.isDeleted = 'N'" +
											" and model.alertAssignedOfficer.isDeleted = 'N'" +
											" group by model.alertStatusId,date(model1.alertDepartmentComment.insertedTime)" +
											" order by model.alertStatusId,date(model1.alertDepartmentComment.insertedTime)");
		query.setParameter("alertId", alertId);
		return query.list();
	}
	
	public List<Object[]> getStatusWiseTrackingCommentsNew(Long alertId){
		Query query = getSession().createQuery("select model.alertStatus.alertStatusId," +
											" model.alertStatus.alertStatus," +
											" model.alertDepartmentComment.alertDepartmentCommentId," +
											" model.alertDepartmentComment.comment," +
											" model.alertDepartmentComment.insertedUser.userId," +
											" model.alertDepartmentComment.insertedUser.userName," +
											" date(model.alertDepartmentComment.insertedTime)," +
											" model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName," +
											" ADD.alertDepartmentDocumentId," +
											" ADD.document," +
											" model.alertDepartmentComment.insertedTime" +
											" from AlertAssignedOfficerAction model" +
											" left join model.alertDepartmentDocument ADD" +
											" where model.alert.alertId = :alertId" +
											" and model.isDeleted = 'N'" +
											" and model.alertAssignedOfficer.isDeleted = 'N'" +
											//" group by model.alertStatusId,date(model.alertDepartmentComment.insertedTime)" +
											//" order by model.alertStatusId,date(model.alertDepartmentComment.insertedTime)" +
											" order by model.alertDepartmentComment.insertedTime");
		query.setParameter("alertId", alertId);
		return query.list();
	}
	
	public List<String> getLatestStatusForAlertTracking(Long alertId){
		Query query = getSession().createQuery("select model.alertStatus.alertStatus" +
												" from AlertAssignedOfficerAction model" +
												" where model.alert.alertId = :alertId" +
												" and model.isDeleted = 'N'" +
												" and model.alertAssignedOfficer.isDeleted = 'N'" +
												" order by model.alertAssignedOfficerActionId desc");
		query.setParameter("alertId", alertId);
		return query.list();
		
	}
}
