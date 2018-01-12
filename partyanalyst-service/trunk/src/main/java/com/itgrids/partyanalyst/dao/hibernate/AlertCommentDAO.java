package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDAO;
import com.itgrids.partyanalyst.model.AlertComment;
import com.itgrids.partyanalyst.model.AlertTracking;

public class AlertCommentDAO extends GenericDaoHibernate<AlertComment, Long>
		implements IAlertCommentDAO {
	public AlertCommentDAO() {
		super(AlertComment.class);
	}

	public List<Object[]> getCommentsOfAlert(Long alertId){
		Query query = getSession().createQuery("select model.alertCommentId,model.comments " +
				" from AlertComment model " +
				" where model.alertId=:alertId and model.isDeleted='N' ");
		query.setParameter("alertId", alertId);
		return query.list();
	}
}
