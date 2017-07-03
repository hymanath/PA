package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.ParliamentAssembly;

public interface IParliamentAssemblyDAO extends GenericDao<ParliamentAssembly,Long>{
	public List<Object[]> getParliamentIdAndName(Long districtId);
	public List<Object[]> getParliamentByConstIdAndName(Long parliamentId);
	
}
