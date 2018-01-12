package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.model.AlertStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertStatusDAO extends GenericDaoHibernate<AlertStatus, Long>
		implements IAlertStatusDAO {
	public AlertStatusDAO() {
		super(AlertStatus.class);
	}
	public List<Object[]> getAllStatus(){
		Query query = getSession().createQuery("select model.alertStatusId, model.alertStatus, model.color,model.shortName from AlertStatus model order by model.statusOrder asc ");
		return query.list();
	}
	public List<Object[]> getAlertStatusInfoForReOpen(){
		Query query = getSession().createQuery("select model.alertStatusId, model.alertStatus,model.color " +
				" from AlertStatus model " +
				" where model.alertStatusId in (:alertStatusIds) ");
		
		query.setParameterList("alertStatusIds", IConstants.AMS_REOPEN_ALERT_STATUS);
		return query.list();
	}
	public List<Object[]> getAlertStatusDtlsBasidOnAlertIds(List<Long> statusIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.alertStatusId,model.alertStatus,model.color from AlertStatus model  ");
		
		if(statusIds != null && statusIds.size() >0)
			sb.append(" where model.alertStatusId in (:statusIds) ");
		Query query = getSession().createQuery(sb.toString());
		
		if(statusIds != null && statusIds.size() >0)
		query.setParameterList("statusIds", statusIds);  
		return query.list();    
	}
	  public List<String> getMonthAndYear(Date fromDate,Date toDate){
	        StringBuilder queryStr = new StringBuilder();
	        
	        queryStr.append(" select CONCAT(DATE_FORMAT(m1, '%M'),'-',year(m1)) as monthYear " +
	                           "    from ( " +
	                               "    select " +
	                               "    (:fromDate - INTERVAL DAYOFMONTH(:fromDate)-1 DAY)  " +
	                               "        +INTERVAL m MONTH as m1 " +
	                               "    from ( " +
	                                   "    select @rownum /*'*/:=/*'*/ @rownum+1 as m from " +
	                                   "    (select 1 union select 2 union select 3 union select 4 union select 5) t1, " +
	                                   "     (select 1 union select 2 union select 3 union select 4 union select 5) t2, " +
	                                   "     (select 1 union select 2 union select 3 union select 4 union select 5) t3,  " +
	                                   "     (select 1 union select 2 union select 3 union select 4 union select 5) t4, " +
	                                   "     (select @rownum /*'*/:=/*'*/ -1) t0  " +
	                                   "      ) d1 " +
	                               " ) d2 " +
	                               "  where m1<=:toDate " +
	                               " order by m1");
	        
	          Session session = getSession();
	          SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
	          sqlQuery.addScalar("monthYear",Hibernate.STRING);
	          sqlQuery.setDate("fromDate", fromDate);
	          sqlQuery.setDate("toDate", toDate);
	          return sqlQuery.list();
	   }
	  public Long getIdOfName(String status){
			Query query = getSession().createQuery(" select model.alertStatusId from AlertStatus model " +
					"  where model.alertStatus =:status ");
			query.setParameter("status", status.trim());  
			return (Long) query.uniqueResult();   
	  }

}
