package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtSchemesDAO;
import com.itgrids.partyanalyst.model.GovtSchemes;

public class GovtSchemesDAO extends GenericDaoHibernate<GovtSchemes, Long> implements IGovtSchemesDAO{

	public GovtSchemesDAO() {
		super(GovtSchemes.class);
		
	}
	
}