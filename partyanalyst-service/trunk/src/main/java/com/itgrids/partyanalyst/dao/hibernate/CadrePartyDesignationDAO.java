package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadrePartyDesignationDAO;
import com.itgrids.partyanalyst.model.CadrePartyDesignation;

public class CadrePartyDesignationDAO extends GenericDaoHibernate<CadrePartyDesignation, Long> implements ICadrePartyDesignationDAO{

	public CadrePartyDesignationDAO() {
		super(CadrePartyDesignation.class);
		// TODO Auto-generated constructor stub
	}

	
	
	public List<Object[]> getPartyDesignationsByCadreId(Long cadreId)
	{
		return getHibernateTemplate().find("select distinct model.partyDesignation.partyDesignationId,model.partyDesignation.partyDesignationName from CadrePartyDesignation model where model.cadre.cadreId = ?", cadreId);	
	}
	
	public void deleteExisting(Long cadreId)
	{
		 Query query = getSession().createQuery("delete from CadrePartyDesignation model where model.cadre.cadreId =:cadreId ");
		 query.executeUpdate();
	}
}
