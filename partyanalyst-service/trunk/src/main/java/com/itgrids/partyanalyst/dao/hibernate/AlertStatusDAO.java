package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.model.AlertStatus;

public class AlertStatusDAO extends GenericDaoHibernate<AlertStatus, Long>
		implements IAlertStatusDAO {
	public AlertStatusDAO() {
		super(AlertStatus.class);
	}
	public List<Object[]> getAllStatus(){
		Query query = getSession().createQuery("select model.alertStatusId, model.alertStatus, model.color,model.shortName from AlertStatus model order by model.statusOrder asc ");
		return query.list();
	}

}
