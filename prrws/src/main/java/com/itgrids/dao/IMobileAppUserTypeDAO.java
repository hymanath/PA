package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.MobileAppUserType;

public interface IMobileAppUserTypeDAO extends GenericDao<MobileAppUserType,Long>{
	
	public List<MobileAppUserType> getUserSubUserTypes(Long userTypeId);
}
