package com.itgrids.electoralconnect.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.electoralconnect.dao.IRolesDAO;
import com.itgrids.electoralconnect.model.Roles;



public class RolesDAO extends GenericDaoHibernate<Roles, Long> implements IRolesDAO{

	public RolesDAO() {
		super(Roles.class);
	}

}
