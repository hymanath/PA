package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.MobileAppUserWorkType;

public interface IMobileAppUserWorkTypeDAO extends GenericDao<MobileAppUserWorkType, Long>{
	
	public List<Object[]> getUsersGovtWorkTypes(Long userId);
}
