package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DemoRequestActions;

public interface IDemoRequestActionsDAO extends GenericDao<DemoRequestActions, Long>{

	public List<Object[]> getDemoRequestActionsByDemoRequestId(Long demoRequestId);
	
}
