/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February, 2010
 * Author Saikrishna.g
 */

package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MyGroup;
import com.itgrids.partyanalyst.model.PersonalUserGroup;

public interface IPersonalUserGroupDAO extends GenericDao<PersonalUserGroup, Long> {

	
	public List<PersonalUserGroup> getAllMyGroupsByUserId(Long userId);
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCountInMyGroupsByUserId(Long userId, Long myGroupId);
	
	@SuppressWarnings("unchecked")
	public List findSubGroupsCountInSystemGroupsByUserId(Long userId);	
	
	public List<MyGroup> getMyGroupObjFromPersonalUserGroup(Long personalUserGroupId);
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCountForASystemGroup(Long groupId,Long userId);
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCountForASystemGroupFromPersonalUserGroup(Long groupId,Long userId);
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCompleteDetailsForASystemGroup(Long groupId,Long userId);
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCompleteDetailsForASystemGroupFromPersonalUserGroup(Long groupId,Long userId);
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsDetailsForMyGroupFromPersonalUserGroup(Long groupId,Long userId);
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCountForMyGroupFromPersonalUserGroup(Long groupId,Long userId);
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCompleteDetailsForMyGroup(Long groupId,Long userId);
	
	@SuppressWarnings("unchecked")
	public List getGroupsByName(Long userId, String groupName);
	
	public List findAllGroupCategoriesInfoAndCountsOfLocationsByLocation(String countLocationInfo, Long userId, 
			Long locationId, String compareLocationInfo );
	
	public List<PersonalUserGroup> findGroupsInfoByCategoryAndUserIdByRegion(Long userId, Long categoryId, String regionInfo);
	
	public List<PersonalUserGroup> findGroupsInfoByCategoryAndUserId(Long userId, Long categoryId);
	
}
