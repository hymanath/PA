package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertTracking;

public class AlertTrackingDAO extends GenericDaoHibernate<AlertTracking, Long>
		implements IAlertTrackingDAO {
	public AlertTrackingDAO() {
		super(AlertTracking.class);
	}
	public List<Object[]> getAlertTrackingDetails(Long alertId)
	{
		Query query = getSession().createQuery("select model.alertStatus.alertStatusId,"
				+ "model.alertStatus.alertStatus,model.user.userId,model.user.firstName,model.user.lastName,model.insertedTime,"
				+ "alertComment.alertCommentId,alertComment.comments,alertComment.user.firstName,alertComment.user.lastName,alertComment.insertedTime"
				+ " from AlertTracking model left join model.alertComment alertComment " +
				 " where model.alertId = :alertId and alertComment.isDeleted ='N' "
				+ " order by model.insertedTime desc");
		query.setParameter("alertId", alertId);
		return query.list();
	}
}
