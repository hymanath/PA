package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtRejoinderActionDAO;
import com.itgrids.partyanalyst.model.GovtRejoinderAction;

public class GovtRejoinderActionDAO extends GenericDaoHibernate<GovtRejoinderAction, Long> implements IGovtRejoinderActionDAO {

	public GovtRejoinderActionDAO() {
		super(GovtRejoinderAction.class);
	}

}
