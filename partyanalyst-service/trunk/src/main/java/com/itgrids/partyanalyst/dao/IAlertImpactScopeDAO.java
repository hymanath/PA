package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertImpactScope;

public interface IAlertImpactScopeDAO extends GenericDao<AlertImpactScope, Long> {
	public List<Object[]> getAllAlertImpactLevel();
	public List<Object[]> getAlertImpactScope();
	public List<Object[]> getAlertImpactScopeByImpactId(List<Long> alertImpactLevelIds);
	public List<Long> getIdOfName(String impactScope);
}
