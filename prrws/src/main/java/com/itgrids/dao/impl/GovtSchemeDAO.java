package com.itgrids.dao.impl;

import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtSchemeDAO;
import com.itgrids.model.GovtScheme;

@Repository
public class GovtSchemeDAO extends GenericDaoHibernate<GovtScheme, Long> implements IGovtSchemeDAO {

	@Autowired
	SessionFactory sessionFactory;

	public GovtSchemeDAO() {
		super(GovtScheme.class);

	}
	
	public List<Object[]> getGovtSchemesDetails(){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.govtSchemeId,model.schemeName from GovtScheme model ");
	    Query query = getSession().createQuery(sb.toString());
	    return query.list(); 
	    
	  }

}
