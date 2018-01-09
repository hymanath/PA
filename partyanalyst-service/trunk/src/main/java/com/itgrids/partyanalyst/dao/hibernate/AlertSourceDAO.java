package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.impl.IAlertSourceDAO;
import com.itgrids.partyanalyst.model.AlertSource;

public class AlertSourceDAO extends GenericDaoHibernate<AlertSource, Long>
		implements IAlertSourceDAO {

	public AlertSourceDAO() {
		super(AlertSource.class);
	}
	
	public List<Long> getIdOfName(String source){
		Query query = getSession().createQuery(" select model.alertSourceId from AlertSource model where model.source =:source ");
		query.setParameter("source", source);
		return query.list();
	}

}
