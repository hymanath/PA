package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LightsVendorUser;

public interface ILightsVendorUserDAO extends GenericDao<LightsVendorUser, Long>{
	
	public List<Object[]> getVendorIdByUserId(Long userId);

}
