package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDesignationDAO;
import com.itgrids.partyanalyst.model.GovtDesignation;

public class GovtDesignationDAO extends GenericDaoHibernate<GovtDesignation, Long> implements IGovtDesignationDAO{

	public GovtDesignationDAO() {
		super(GovtDesignation.class);
		
	}

}
