package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtSubTaskActionTypeDAO;
import com.itgrids.partyanalyst.model.GovtSubTaskActionType;

public class GovtSubTaskActionTypeDAO extends GenericDaoHibernate<GovtSubTaskActionType, Long> implements IGovtSubTaskActionTypeDAO{

	public GovtSubTaskActionTypeDAO() {
		super(GovtSubTaskActionType.class);
		
	}

}
