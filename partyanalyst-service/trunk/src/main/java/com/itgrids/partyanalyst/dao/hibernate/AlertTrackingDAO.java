package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.IAlertTrackingDAO;
import com.itgrids.partyanalyst.model.AlertTracking;

public class AlertTrackingDAO extends GenericDaoHibernate<AlertTracking, Long>
		implements IAlertTrackingDAO {
	public AlertTrackingDAO() {
		super(AlertTracking.class);
	}
	public List<Object[]> getAlertTrackingDetails(Long alertId){
		StringBuilder queryStr  = new StringBuilder();
		queryStr.append("select alertStatus.alertStatusId,alertStatus.alertStatus,user.userId,user.firstName ");
		queryStr.append(",user.lastName,model.insertedTime,alertComment.alertCommentId,alertComment.comments,");
		queryStr.append(" user1.firstName,user1.lastName,alertComment.insertedTime  ");
		queryStr.append(" from AlertTracking model " +
				" 	left join  model.alertStatus alertStatus " +
				" 	left join model.user user " +
				" 	left join model.alertComment alertComment " +
				" 	left join alertComment.user user1  " +
				" ");
		queryStr.append(" where model.alertId = :alertId ");
		queryStr.append(" order by model.insertedTime desc");			
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("alertId", alertId);
		return query.list();
	}
	public List<Object[]> getAlertTrackingDetailsList(Long alertId){
		StringBuilder queryStr  = new StringBuilder();
		queryStr.append(" select distinct ALTT.alert_id as alert_id, ");
		queryStr.append(" ALTT.alert_status_id as alert_status_id, ");
		queryStr.append(" date(ALTT.inserted_time) as inserted_date, ");
		queryStr.append(" time(ALTT.inserted_time) as inserted_time, ");
		//queryStr.append(" ALTT.inserted_time as inserted_time, ");
		queryStr.append(" ALTC.alert_comment_id as alert_comment_id,");
		queryStr.append(" ALTC.comments as comments,");
		queryStr.append(" ALTCA.assign_tdp_cadre_id as assign_tdp_cadre_id,");
		queryStr.append(" TC.first_name as first_name,");
		queryStr.append(" U.firstname as firstname, ");
		queryStr.append(" ALTS.alert_status as alert_status ");
		queryStr.append(" from alert_tracking ALTT ");
		queryStr.append(" left join alert_status ALTS on ALTT.alert_status_id = ALTS.alert_status_id ");
		queryStr.append(" left join alert_comment ALTC on ALTC.alert_comment_id = ALTT.alert_comment_id and ALTC.is_deleted = 'N' ");
		queryStr.append(" left join alert_comment_assignee ALTCA on ALTT.alert_comment_id = ALTCA.alert_comment_id ");
		queryStr.append(" left join user U on ALTT.inserted_by = U.user_id, ");
		queryStr.append(" tdp_cadre TC ");
		queryStr.append(" where ");
		queryStr.append(" ALTCA.assign_tdp_cadre_id = TC.tdp_cadre_id ");
		queryStr.append(" and ALTT.alert_id = :alertId ");
		queryStr.append(" order by ALTT.alert_status_id, inserted_time,ALTC.alert_comment_id ;");
		SQLQuery query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alert_id", Hibernate.LONG)
				.addScalar("alert_status_id", Hibernate.LONG) 
				.addScalar("inserted_date", Hibernate.STRING)
				.addScalar("inserted_time", Hibernate.STRING)
				.addScalar("alert_comment_id", Hibernate.LONG)  
				.addScalar("comments", Hibernate.STRING)
				.addScalar("assign_tdp_cadre_id", Hibernate.LONG)
				.addScalar("first_name", Hibernate.STRING)
				.addScalar("firstname", Hibernate.STRING)
				.addScalar("alert_status", Hibernate.STRING);
		query.setParameter("alertId", alertId); 
		return query.list();
	}
}
