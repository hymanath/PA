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
	public List getCompleteUserGroupDetailsForAGroup(Long groupId);
	
	@SuppressWarnings("unchecked")
	public List getGroupsByName(Long userId, String groupName);
	
	public List findAllGroupCategoriesInfoAndCountsOfLocationsByLocation(String countLocationInfo, Long userId, 
			Long locationId, String compareLocationInfo );
	
	public List<PersonalUserGroup> findGroupsInfoByCategoryAndUserIdByRegion(Long userId, Long categoryId, String regionInfo);
	
	public List<PersonalUserGroup> findGroupsInfoByCategoryAndUserId(Long userId, Long categoryId);
	
	// LOCAL USER GROUPS
	
	//for state
	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInState(Long userId,Long stateId,Long categoryId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInDistrictsByState(Long userId,Long stateId,Long categoryId);
	
	//for district
	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInDistrict(Long userId,Long districtId,Long categoryId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInConstituencysByDistrict(Long userId,Long districtId,Long categoryId);
	
	//for constituency
	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInConstituency(Long userId,Long constituencyId,Long categoryId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInTehsilsByConstituency(Long userId,Long constituencyId,Long categoryId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInLocalBodysByConstituency(Long userId,Long constituencyId,Long categoryId);
	
	//for tehsil
	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInTehsil(Long userId,Long tehsilId,Long categoryId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInVillagesByTehsil(Long userId,Long tehsilId,Long categoryId);
	
	//for local bodys
	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInLocalBodys(Long userId,Long localBodyId,Long categoryId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInWardsByLocalBodys(Long userId,Long localBodyId,Long categoryId);
	
	//for village
	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInVillage(Long userId,Long villageId,Long categoryId);
		
	//for ward
	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInWard(Long userId,Long wardId,Long categoryId);
	
	//By Scope
	@SuppressWarnings("unchecked")
	public List getTotalLocalUserGroupsByScope(Long userId);
	
	@SuppressWarnings("unchecked")
	public List getTotalLocalUserGroupsByScope(Long userId,String scope);
	
	@SuppressWarnings("unchecked")
	public List getDistinctLocalGroupCategorysForAUser(Long userId);
	
	
}
