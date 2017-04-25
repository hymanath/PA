package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertSubTaskStatusDAO;
import com.itgrids.partyanalyst.model.AlertSubTaskStatus;

public class AlertSubTaskStatusDAO extends GenericDaoHibernate<AlertSubTaskStatus, Long> implements IAlertSubTaskStatusDAO{

	public AlertSubTaskStatusDAO() {
		super(AlertSubTaskStatus.class);
		
	}
	
	public List<Object[]> getAlertStatusDtlsBasidOnAlertIds(List<Long> statusIds){
		Query query = getSession().createQuery(" SELECT model.alertSubTaskStatusId,model.status,model.color FROM AlertSubTaskStatus model" +
				" WHERE model.alertSubTaskStatusId in (:statusIds) ");
		query.setParameterList("statusIds", statusIds);  
		return query.list();
	}
	
}
