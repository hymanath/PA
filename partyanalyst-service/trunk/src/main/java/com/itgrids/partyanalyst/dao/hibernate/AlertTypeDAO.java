package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertTypeDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertType;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertTypeDAO extends GenericDaoHibernate<AlertType, Long>
		implements IAlertTypeDAO {
	public AlertTypeDAO() {
		super(AlertType.class);
	}
	public List<Object[]> getAlertType()
	{
		Query query = getSession().createQuery("select model.alertTypeId,model.alertType from AlertType model");
		return query.list();
	}
	
	public List<Object[]> getAlertTypeForOrganization()
	{
		Query query = getSession().createQuery("select model.alertTypeId,model.alertType from AlertType model where model.alertTypeId = :alertTypeId");
		
		query.setParameter("alertTypeId", IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID);
		return query.list();
	}
	public List<Long> getIdOfName(String type){
   		Query query = getSession().createQuery(" select " +
   				" model.alertTypeId " +
   				" from AlertType model where model.alertType=:type");
   		query.setParameter("type", type);
   		return query.list();
     }
}
