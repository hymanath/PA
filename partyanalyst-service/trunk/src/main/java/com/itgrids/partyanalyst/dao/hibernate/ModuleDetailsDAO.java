package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IModuleDetailsDAO;
import com.itgrids.partyanalyst.model.ModuleDetails;

public class ModuleDetailsDAO extends GenericDaoHibernate<ModuleDetails, Long> implements IModuleDetailsDAO  {

	public ModuleDetailsDAO() {
		super(ModuleDetails.class);		
	}

	@SuppressWarnings("unchecked")
	public List findModuleIdByModuleName(String module) {
		
		return getHibernateTemplate().find("select model.moduleId from ModuleDetails model where model.module = ?", module);
	}
}
