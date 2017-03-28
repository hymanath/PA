package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertClarificationDAO;
import com.itgrids.partyanalyst.model.AlertClarification;

public class AlertClarificationDAO extends GenericDaoHibernate<AlertClarification, Long> implements IAlertClarificationDAO {

	public AlertClarificationDAO() {
		super(AlertClarification.class);
	}
	public List<Object[]> getAlertClarificationStatus(Long alertId){
		Query query = getSession().createQuery("select model.alertClarificationStatus.alertClarificationStatusId," +
				" model.alertClarificationStatus.status" +
				" from AlertClarification model" +
				" where model.alert.alertId = :alertId" +
				" and model.isDeleted = 'N'" +
				" and model.alert.isDeleted = 'N'");
		query.setParameter("alertId", alertId);
		return query.list();
	}
	public Long getAlertClarfStatusRecord(Long alertId){
		Query query = getSession().createQuery("select model.alertClarificationId " +
				" from AlertClarification model" +
				" where model.alert.alertId = :alertId" +
				" and model.isDeleted = 'N'" +
				" and model.alert.isDeleted = 'N'");
		query.setParameter("alertId", alertId);
		return (Long) query.uniqueResult();
	}
	
	public Integer updateAlertStatus(Long alertId,Long statusId){
	   	StringBuilder str = new StringBuilder();
	   	str.append(" update AlertClarification model set " +
	    			" model.alertClarificationStatus.alertClarificationStatusId = :statusId " +
	    			" where model.alert.alertId = :alertId " +
	    			" and model.isDeleted = 'N'" +
				" and model.alert.isDeleted = 'N'");
	   	Query query = getSession().createQuery(str.toString());
	   	 query.setParameter("statusId", statusId);
	   	 query.setParameter("alertId", alertId);
	   	 Integer c = query.executeUpdate();
	   	return c; 
	}
	 
	 public Integer updateStatusForOld(Long userId,Long alertId,Date date){
		 Query query = getSession().createQuery(" update AlertClarification model " +
		 		" set model.isDeleted='Y',model.updatedBy=:userId,model.updatedTime=:date " +
		 		" where model.alert.alertId=:alertId and model.isDeleted='N' ");
		 
		 query.setParameter("userId", userId);
		 query.setParameter("date", date);
		 query.setParameter("alertId", alertId);
		 return query.executeUpdate();
	 }
}
