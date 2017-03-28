package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserAccessLevelValue;

public interface IUserAccessLevelValueDAO extends GenericDao<UserAccessLevelValue,Long>{
	public List<Object[]> getAccessValuesOfUserId(Long userId);
	public List<Object[]> getAccessValuesByUser(Long userId,String type);
	public List<Object[]> getAssignedWardsByUser(Long userId);
}
