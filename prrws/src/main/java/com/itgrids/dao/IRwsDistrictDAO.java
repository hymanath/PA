package com.itgrids.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.RwsDistrict;

public interface IRwsDistrictDAO extends GenericDao<RwsDistrict, Long> {

	public String getRwsCode(Long districtId);

}
