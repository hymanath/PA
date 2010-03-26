package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StaticUserGroup;

public interface IStaticUserGroupDAO extends GenericDao<StaticUserGroup ,Long> {

	@SuppressWarnings("unchecked")
	public List getGroupMembersCountForAGroup(Long groupId);

	
	@SuppressWarnings("unchecked")
	public List findMembersByUserId(Long registrationId, Long groupId);

	
	@SuppressWarnings("unchecked")
	public List getGroupMembersMobileNoForAGroup(Long groupId,Long userId);

}
