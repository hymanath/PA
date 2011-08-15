package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreRoleDAO;
import com.itgrids.partyanalyst.model.CadreRole;

public class CadreRoleDAO extends GenericDaoHibernate<CadreRole,Long> implements ICadreRoleDAO{

	public CadreRoleDAO() {
		super(CadreRole.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCadreRoles()
	{
		return getHibernateTemplate().find("select model.cadreRoleId,model.role from CadreRole model");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CadreRole> findByRoleDesc(String roleDesc) {
		
		return getHibernateTemplate().find("from CadreRole model where model.role = ?",roleDesc);
	}

}
