package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PrConstituency;

public interface IPrConstituencyDAO extends GenericDao<PrConstituency, Long> {
	
	public List<Object[]> getAllConstutiensFrDistrict(String districtId);

}
