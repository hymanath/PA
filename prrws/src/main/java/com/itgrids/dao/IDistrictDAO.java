package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.District;

public interface IDistrictDAO extends GenericDao<District,Long>{
	public List<Object[]> getDistrictIdName(Long stateId);
}
