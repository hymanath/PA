package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertCommentAssigneeDAO;

import com.itgrids.partyanalyst.model.AlertCommentAssignee;

public class AlertCommentAssigneeDAO extends
		GenericDaoHibernate<AlertCommentAssignee, Long> implements
		IAlertCommentAssigneeDAO {
	public AlertCommentAssigneeDAO() {
		super(AlertCommentAssignee.class);
	}
	
	public List<Object[]> getAlertCommentAssignedCandidates(Long alertId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.alertCommentId,model.assignTdpCadre.tdpCadreId, model.assignTdpCadre.firstname"+
				" from AlertCommentAssignee model where model.alertComment.alert.isDeleted ='N'  ");
		if(alertId != null && alertId > 0)
			str.append(" and  model.alertComment.alert.alertId = :alertId");
		Query query = getSession().createQuery(str.toString());
		if(alertId != null && alertId > 0)
		query.setParameter("alertId", alertId);
		return query.list();
	}


}
