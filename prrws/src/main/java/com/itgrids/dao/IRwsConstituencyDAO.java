package com.itgrids.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.RwsConstituency;

public interface IRwsConstituencyDAO extends GenericDao<RwsConstituency, Long> {

	public String getRwsCode(Long constituencyId);
}
