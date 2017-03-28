package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDesignationDAO;
import com.itgrids.partyanalyst.model.GovtDesignation;

public class GovtDesignationDAO extends GenericDaoHibernate<GovtDesignation, Long> implements IGovtDesignationDAO{

	public GovtDesignationDAO() {
		super(GovtDesignation.class);
		
	}
	 public List<Object[]> getAllGovtDesignation(){
		   Query query = getSession().createQuery("select model.govtDesignationId , model.govtDesignationName from GovtDesignation model order by model.govtDesignationName");
		
			return query.list();
			
	   }
}
