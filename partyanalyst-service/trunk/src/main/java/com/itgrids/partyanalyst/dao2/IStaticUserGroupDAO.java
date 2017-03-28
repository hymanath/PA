package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StaticUserGroup;

public interface IStaticUserGroupDAO extends GenericDao<StaticUserGroup ,Long> {

	@SuppressWarnings("unchecked")
	public List getGroupMembersCountForAGroup(Long groupId);

	
	@SuppressWarnings("unchecked")
	public List findMembersByUserId(Long userId, Long groupId);

	
	@SuppressWarnings("unchecked")
	public List getGroupMembersMobileNoForAGroup(Long groupId,Long userId);
		
	@SuppressWarnings("unchecked")
	public List getGroupMembersByName(Long groupId, String memberName);
	
	@SuppressWarnings("unchecked")
	public List getGroupMembersMobileNumbers(Long groupId);
	
	@SuppressWarnings("unchecked")
	public List getCompleteUserGroupMemberDetailsForAGroup(Long groupId);
	
	@SuppressWarnings("unchecked")
	public List getCompleteUserGroupMemberDetailsForAGroupCatgory(Long groupCategoryId,Long userId);
	
	@SuppressWarnings("unchecked")
	public List getCompleteUserGroupMemberDetailsForAUserOfSpecificDesignation(Long designationId, Long userId);
	
	@SuppressWarnings("unchecked")
	public List getCompleteUserGroupMemberDetailsForAUserOfSpecificDesignation(Long designationId, Long userId, Long groupCategoryId);
	
	@SuppressWarnings("unchecked")
	public List getMemberDetailsOfLocalUserGroup(Long localGroupId);
	
	public List<StaticUserGroup> getStaticUserGroupByStaticUserAndGroup(Long staticUserId,Long groupId);
	
	@SuppressWarnings("unchecked")
	public List getStaticUserIdsByLocalGroupId(Long localGroupId);
	
	public Integer deleteStaticUserGroupByStaticUserIds(List<Long> staticUserIds);
}
