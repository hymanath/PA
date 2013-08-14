package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreLevelDAO;
import com.itgrids.partyanalyst.model.CadreLevel;


public class CadreLevelDAO extends GenericDaoHibernate<CadreLevel , Long> implements ICadreLevelDAO  {

	public CadreLevelDAO() {
		super(CadreLevel.class);		 
	}

	@SuppressWarnings("unchecked")
	public List<CadreLevel> findByCadreLevel(String cadreLevel) {
		
		return getHibernateTemplate().find("from CadreLevel model where model.level = ?",cadreLevel);
	}
	
	@SuppressWarnings("unchecked")
	public List<CadreLevel> getCadreLevelList()
	{
		return getHibernateTemplate().find(" from CadreLevel model ");
	}

}
