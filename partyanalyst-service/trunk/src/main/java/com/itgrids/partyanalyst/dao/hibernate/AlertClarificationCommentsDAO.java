package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertClarificationCommentsDAO;
import com.itgrids.partyanalyst.model.AlertClarificationComments;

public class AlertClarificationCommentsDAO extends GenericDaoHibernate<AlertClarificationComments, Long> implements IAlertClarificationCommentsDAO{

	public AlertClarificationCommentsDAO() {
		super(AlertClarificationComments.class);
	}
	public List<Object[]> getClarificationComments(Long alertId){
		Query query = getSession().createQuery("select model.alertClarificationCommentsId,model.comments " +
				" from AlertClarificationComments model" +
				" where model.alert.alertId = :alertId" +
				" and model.isDeleted = 'N' and model.alert.isDeleted ='N' ");
		query.setParameter("alertId", alertId);
		return query.list();
	}
	
	public Integer updateCommentStatus(Long commentId){
		Query query = getSession().createQuery(" update AlertClarificationComments model " +
				" set model.isDeleted='Y' where model.alertClarificationCommentsId = :commentId ");
		query.setParameter("commentId",commentId);
		 return query.executeUpdate();
	}
}
