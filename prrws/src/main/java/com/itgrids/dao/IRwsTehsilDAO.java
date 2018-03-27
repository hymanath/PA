package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.RwsTehsil;

public interface IRwsTehsilDAO extends GenericDao<RwsTehsil, Long> {

	public String getRwsCode(Long mandalId);

	public List<Object[]> getAllmandals();

}
