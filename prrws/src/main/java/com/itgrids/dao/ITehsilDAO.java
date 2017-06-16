package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.Tehsil;

public interface ITehsilDAO extends GenericDao<Tehsil,Long>  {

	public List<Object[]> getTehsilIdAndNameByIds(List<Long> tehsilIds);
}
