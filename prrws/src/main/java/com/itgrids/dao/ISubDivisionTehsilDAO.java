package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.SubDivisionTehsil;

public interface ISubDivisionTehsilDAO extends GenericDao<SubDivisionTehsil, Long> {
	public List<Object[]> getTehsilsOfSubDivision(Long subDivisonId);
}
