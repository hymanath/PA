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
	public List<Object[]> getAlertTrackingDetailsList(Long alertId,boolean hasTrue){
		StringBuilder queryStr  = new StringBuilder();
		queryStr.append(" select distinct ALTT.alert_id as alert_id, ");
		queryStr.append(" ALTT.alert_status_id as alert_status_id, ");
		queryStr.append(" date(ALTT.inserted_time) as inserted_date, ");
		queryStr.append(" time(ALTT.inserted_time) as inserted_time, ");
		queryStr.append(" ALTC.alert_comment_id as alert_comment_id,");
		queryStr.append(" ALTC.comments as comments,");
		
		if(hasTrue){
			queryStr.append(" ALTCA.assign_tdp_cadre_id as assign_tdp_cadre_id,");
			queryStr.append(" 'Program Committee' as first_name,");
		}else{
			queryStr.append(" 0 as assign_tdp_cadre_id,");
			queryStr.append(" '' as first_name,");
		}
		
		queryStr.append(" U.firstname as firstname, ");
		queryStr.append(" ALTS.alert_status as alert_status ,");
		queryStr.append(" ALTS.status_order as status_order, ");
		queryStr.append(" ALTT.alert_tracking_id as alert_tracking_id ");
		queryStr.append(" from alert_tracking ALTT ");
		queryStr.append(" left join alert_status ALTS on ALTT.alert_status_id = ALTS.alert_status_id ");
		queryStr.append(" left join alert_comment ALTC on ALTC.alert_comment_id = ALTT.alert_comment_id and ALTC.is_deleted = 'N' ");
		
		if(hasTrue)
			queryStr.append(" left join alert_comment_assignee ALTCA on ALTT.alert_comment_id = ALTCA.alert_comment_id ");
		
		queryStr.append(" left join user U on ALTT.inserted_by = U.user_id ");
		queryStr.append(" where ");
		queryStr.append(" ALTT.alert_id = :alertId ");
		queryStr.append(" order by ALTS.status_order, date(ALTT.inserted_time) desc ,time(ALTT.inserted_time) desc ,ALTC.alert_comment_id ;");   
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
				.addScalar("alert_status", Hibernate.STRING)
				.addScalar("status_order", Hibernate.LONG)
				.addScalar("alert_tracking_id", Hibernate.LONG);
		query.setParameter("alertId", alertId); 
		return query.list();
	}
	public List<Long> lastUpdatedstatus(Long alertId){
		StringBuilder queryStr  = new StringBuilder();
		queryStr.append(" select model.alertStatus.alertStatusId from AlertTracking model " +
						" where  model.alertId = :alertId order by model.insertedTime desc limit 1 ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("alertId", alertId);
		return query.list();   
	}
}
