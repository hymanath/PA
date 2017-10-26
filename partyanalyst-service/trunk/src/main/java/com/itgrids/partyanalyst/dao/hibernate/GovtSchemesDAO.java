package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtSchemesDAO;
import com.itgrids.partyanalyst.model.GovtSchemes;

public class GovtSchemesDAO extends GenericDaoHibernate<GovtSchemes, Long> implements IGovtSchemesDAO{

	public GovtSchemesDAO() {
		super(GovtSchemes.class);
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllSchemeDetails(){
		Query query = getSession().createQuery("select distinct model.govtSchemesId,model.schemeName from GovtSchemes model");
		
		return query.list();
	}
}