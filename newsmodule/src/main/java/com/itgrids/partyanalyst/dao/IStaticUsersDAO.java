/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */

package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StaticUsers;

public interface IStaticUsersDAO extends GenericDao<StaticUsers, Long> {

	public List<StaticUsers> findByMobileNo(String mobileNumber);	
	
	@SuppressWarnings("unchecked")
	public List findGroupMembersMobileNosByMemberIds(List<Long> memberIds);
	@SuppressWarnings("unchecked")
	public List findGroupMembersMobileNosByMemberIds(String memberIds);
	
	@SuppressWarnings("unchecked")
	public List findGroupMembersNameAndMobileNosByMemberIds(List<Long> memberIds);
	@SuppressWarnings("unchecked")
	public List findGroupMembersNameAndMobileNosByMemberIds(String memberIds);
	
	public Integer deleteStaticUsersByStaticUserIds(List<Long> staticUserIds);
	
	public List<Long> checkForUserGroupMembers(Long id);
	
	public Integer deleteUserMembers(Long id);

}
