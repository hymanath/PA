package com.itgrids.cardprint.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.cardprint.model.Constituency;

public interface IConstituencyDAO extends GenericDao<Constituency, Long> {
	
	public List<Object[]> getAllAssemblyConstituencies();
}
