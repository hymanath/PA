package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtSmsActionTypeDAO;
import com.itgrids.partyanalyst.model.GovtSmsActionType;


public class GovtSmsActionTypeDAO extends GenericDaoHibernate<GovtSmsActionType, Long> implements
IGovtSmsActionTypeDAO {

	public GovtSmsActionTypeDAO() {
		super(GovtSmsActionType.class);
	}

}
