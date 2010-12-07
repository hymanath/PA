package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ModuleDetails;

public interface IModuleDetailsDAO extends GenericDao<ModuleDetails, Long>{
	@SuppressWarnings("unchecked")
	public List findModuleIdByModuleName(String module);
}
