package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.NregaComponentService;

public interface INregaComponentServiceDAO extends GenericDao<NregaComponentService, Long>{

	public List<Object[]> getComponentUrlsByComponentIds(List<Long> componentIds,String serviceType);
}
