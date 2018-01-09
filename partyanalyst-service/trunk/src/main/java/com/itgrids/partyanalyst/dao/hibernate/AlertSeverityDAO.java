package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertSeverityDAO;
import com.itgrids.partyanalyst.model.AlertSeverity;

public class AlertSeverityDAO extends GenericDaoHibernate<AlertSeverity, Long>
		implements IAlertSeverityDAO {
	public AlertSeverityDAO() {
		super(AlertSeverity.class);
	}
	public List<Object[]> getFilterSectionDetailsOnSeverity(){
   		Query query = getSession().createQuery(" select " +
   				" model.alertSeverityId," +
   				" model.severity," +
   				" model.severityColor " +
   				" from AlertSeverity model ");
   		return query.list();
     }
	public List<Object[]> getAlertSeverity(){
   		Query query = getSession().createQuery(" select " +
   				" model.alertSeverityId," +
   				"  model.severity" +
   				" from AlertSeverity model ");
   		return query.list();
     }
	public List<Long> getIdOfName(String seviority){
   		Query query = getSession().createQuery(" select " +
   				" model.alertSeverityId " +
   				" from AlertSeverity model where model.severity=:seviority");
   		query.setParameter("seviority", seviority);
   		return query.list();
     }
	

}
