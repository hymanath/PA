package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertImpactScope;

public interface IAlertImpactScopeDAO extends GenericDao<AlertImpactScope, Long> {
	public List<Object[]> getAllAlertImpactLevel();
}
