package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreGovtDesignationDAO;
import com.itgrids.partyanalyst.model.CadreGovtDesignation;
import com.itgrids.partyanalyst.model.CadrePartyDesignation;

public class CadreGovtDesignationDAO extends GenericDaoHibernate<CadreGovtDesignation, Long> implements ICadreGovtDesignationDAO{

	public CadreGovtDesignationDAO() {
		super(CadreGovtDesignation.class);
	
	}
	public List<Object[]> findByCadreId(Long cadreId)
	{
		return getHibernateTemplate().find("select distinct model.govtDesignation.govtDesignationId,model.govtDesignation.govtDesignationName from CadreGovtDesignation model where model.cadre.cadreId = ?", cadreId);	
	}

	public void deleteExisting(Long cadreId)
	{
		 Query query = getSession().createQuery("delete from CadreGovtDesignation model where model.cadre.cadreId =:cadreId ");
		 query.setParameter("cadreId", cadreId);
		 query.executeUpdate();
	}
}
