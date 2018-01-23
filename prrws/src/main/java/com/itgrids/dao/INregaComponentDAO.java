package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.NregaComponent;

public interface INregaComponentDAO extends GenericDao<NregaComponent, Long>{
	
	public List<Object[]> getComponentByConvergType(Long convergenceTypeId);
	public Long getComponentIdByComponentName(String componentName);
}
