package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.NregaComponentStatus;

public interface INregaComponentStatusDAO extends GenericDao<NregaComponentStatus, Long> {
	public List<Object[]> getNregaComponentStatus();

}
