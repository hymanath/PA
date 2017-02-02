package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityMemberAccessType;

public interface IActivityMemberAccessTypeDAO extends GenericDao<ActivityMemberAccessType,Long>{
	public List<Object[]> getActivityMemberUserAccessTypeByUserId(Long userId);
	public List<Object[]> getAllActivityMembersOfGSAndDistAndMpUserTypes(List<Long> childUserTypeIds);
	public Object[] getUserAccessTypeAndActivityMemberIdByUserId(Long userId);
	public List<Object[]> getDesignation(List<Long> activityMemberIdList);
	public List<Object[]> getMemberIdMemberLvlAndLocationValueByTdpCadre(Long tdpCadreId);
}
