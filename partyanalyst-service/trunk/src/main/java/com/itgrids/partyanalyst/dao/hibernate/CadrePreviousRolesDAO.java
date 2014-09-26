package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadrePreviousRolesDAO;
import com.itgrids.partyanalyst.model.CadrePreviousRoles;

public class CadrePreviousRolesDAO extends GenericDaoHibernate<CadrePreviousRoles , Long> implements ICadrePreviousRolesDAO{

	public CadrePreviousRolesDAO() {
		super(CadrePreviousRoles.class);

	}

}
