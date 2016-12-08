package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
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
	
	public List<Object[]> getAssignedCandidateAlertComment(Long alertId,List<Long> tdpCadreIds){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select distinct ACA.assign_tdp_cadre_id as cadreId,AC.comments as comments from alert_comment_assignee ACA,alert_comment AC " +
				   " where ACA.alert_comment_id = AC.alert_comment_id " +
				    " and ACA.assign_tdp_cadre_id in (:tdpCadreIds) " +
				    " and AC.alert_id = :alertId " +
				    " and AC.comments is not null " );
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("cadreId", Hibernate.LONG)
				.addScalar("comments", Hibernate.STRING);
		
		query.setParameter("alertId", alertId);
		query.setParameterList("tdpCadreIds", tdpCadreIds);
		
		return query.list();
		
	}


}
