package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserDistrictAccessInfo;

public interface IUserDistrictAccessInfoDAO extends GenericDao<UserDistrictAccessInfo, Long>{
	
	public List findByUser(Long userId);
	
	public List findByUserAndState(Long stateId,Long userId);

	public Integer deleteAllDistrictAccessByStateIdUserId(Long userId,Long stateId);
	
	public void deleteDistrictAccessByUserIdStateId(Long userId,Long stateId);
	
	public void deleteAllDistrictAccess(Long userId);
	
	public List<Object[]> getAllUserAccessStateList(Long userId);
	
	public List<Object[]> getLocationIdList(Long userId);
	
	public List<Long> getDistrictIdsByUsrId(Long userId);
	
	public List<Long> getDistrictIdsForUser(List<Long> userIds);
	
	
}
