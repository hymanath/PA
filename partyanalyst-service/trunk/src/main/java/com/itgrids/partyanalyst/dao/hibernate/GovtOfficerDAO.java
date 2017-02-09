package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.GovtOfficer;

public class GovtOfficerDAO extends GenericDaoHibernate<GovtOfficer, Long>{

	public GovtOfficerDAO() {
		super(GovtOfficer.class);
		
	}

}
