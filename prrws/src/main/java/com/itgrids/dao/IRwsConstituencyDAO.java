package com.itgrids.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.RwsConstituency;

public interface IRwsConstituencyDAO extends GenericDao<RwsConstituency, Long> {

	public String getRwsCode(Long constituencyId);
	public List<Object[]> getConstituencyList(Set<String> constituencyIdList);
}
