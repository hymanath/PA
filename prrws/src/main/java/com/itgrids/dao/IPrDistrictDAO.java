package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PrDistrict;

public interface IPrDistrictDAO extends GenericDao<PrDistrict, Long> {
	
	public List<Object[]> getAllDistrictsFrState();

}
