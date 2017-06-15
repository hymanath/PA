package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.Constituency;

public interface IConstituencyDAO extends GenericDao<Constituency,Long>{
	public List<Object[]> getConstituencies(Long districtId);
	public List<Long> getConstituencyList(String districtIds);
}
