package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserDistrictAccessInfo;

public interface IUserDistrictAccessInfoDAO extends GenericDao<UserDistrictAccessInfo, Long>{
	
	public List findByUser(Long userId);

}
