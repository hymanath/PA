package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtRejoinderActionDAO;
import com.itgrids.partyanalyst.model.GovtRejoinderAction;

public class GovtRejoinderActionDAO extends GenericDaoHibernate<GovtRejoinderAction, Long> implements IGovtRejoinderActionDAO {

	public GovtRejoinderActionDAO() {
		super(GovtRejoinderAction.class);
	}
	public List<Object[]> getAllActions(){
		Query query = getSession().createQuery("select model.govtRejoinderActionId," +
				" model.action" +
				" from GovtRejoinderAction model");
		return query.list();
	}

}
