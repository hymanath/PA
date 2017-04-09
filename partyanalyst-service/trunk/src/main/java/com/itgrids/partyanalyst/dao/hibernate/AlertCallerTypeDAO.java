package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertCallerTypeDAO;
import com.itgrids.partyanalyst.model.AlertCallerType;

public class AlertCallerTypeDAO extends GenericDaoHibernate<AlertCallerType, Long> implements IAlertCallerTypeDAO{

	public AlertCallerTypeDAO() {
		super(AlertCallerType.class);
	}

	public List<Object[]> getAlertCallerTypesByOrder(){
		Query query = getSession().createQuery("select model.alertCallerTypeId,model.callerType from AlertCallerType model" +
										" order by model.orderNo asc");
		return query.list();
	}
}
