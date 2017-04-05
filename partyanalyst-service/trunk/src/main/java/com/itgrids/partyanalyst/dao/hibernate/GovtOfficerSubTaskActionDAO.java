package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtOfficerSubTaskActionDAO;
import com.itgrids.partyanalyst.model.GovtOfficerSubTaskAction;

public class GovtOfficerSubTaskActionDAO extends GenericDaoHibernate<GovtOfficerSubTaskAction, Long> implements IGovtOfficerSubTaskActionDAO{

	public GovtOfficerSubTaskActionDAO() {
		super(GovtOfficerSubTaskAction.class);
		
	}
	
}
