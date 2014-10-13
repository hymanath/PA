package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreRolesDAO;
import com.itgrids.partyanalyst.model.CadreRoles;

public class CadreRolesDAO extends GenericDaoHibernate<CadreRoles, Long> implements ICadreRolesDAO{

	public CadreRolesDAO()
	{
		super(CadreRoles.class);
	}

}
