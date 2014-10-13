package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRolesDAO;
import com.itgrids.partyanalyst.model.CadreRoles;

public class CadreRolesDAO extends GenericDaoHibernate<CadreRoles, Long> implements ICadreRolesDAO{

	public CadreRolesDAO()
	{
		super(CadreRoles.class);
	}
	
	public List<Object[]> getAllCadreRoles(){
		Query query = getSession().createQuery(" select model.cadreRolesId," +
				" model.role from CadreRoles model ");
		
		return query.list();
	}

}
