package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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
		Query query = getSession().createQuery(" select model.alertStatusId,model.alertStatus,model.color from AlertStatus model" +
				" where model.alertStatusId in (:statusIds) ");
		query.setParameterList("statusIds", statusIds);  
		return query.list();
	}

}
