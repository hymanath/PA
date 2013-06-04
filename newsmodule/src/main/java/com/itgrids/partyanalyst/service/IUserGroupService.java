/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 17, 2010
 * Author Saikrishna.g
 */

package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.dto.GroupsDetailsForUserVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsResultVO;
import com.itgrids.partyanalyst.dto.UserGroupBasicDetails;
import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersInfoVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;

public interface IUserGroupService {/*

	public List<SelectOptionVO> getAllStaticGroupNames();
	public List<SelectOptionVO> getAllMyGroupsCreatedByUser(Long userId);
    
	public UserGroupMembersVO addMemberToGroup(Long groupId, UserGroupMembersVO userGroupMembersToSave);
    public UserGroupDetailsVO createGroupForUser(UserGroupDetailsVO userGroupDetailsToSave);
    
    public List <GroupsDetailsForUserVO> subGrpsCountInSystemGrpsForUser(Long userId);
    public List<GroupsDetailsForUserVO> subGroupsCountInMyGroupsForUser(Long userId);
    
    public UserGroupBasicDetails getUserGroupDetailsForAUserForSystemGroups(String categoryType,Long groupId,Long userId);
    
    public UserGroupBasicDetails getUserGroupDetailsForAUserForMyGroups(String categoryType,Long groupId,Long userId);
    
    public SmsResultVO sendSMStoGroup(String message,String[] groupMembersMobileNos,Long userId,String moduleName);
    
    public List<UserGroupMembersVO> getAllMembersIntheGroup(Long userId, Long groupId);
    
    public List<SelectOptionVO> getSubGroupsListInSystemGroups(Long groupId ,Long userId);
    public List<SelectOptionVO> getSubGroupsListInMyGroups(Long groupId ,Long userId);
    public List<SelectOptionVO> getSubGroupsOfStaticGroupParents(Long groupId ,Long userId);
    public boolean checkForAvailability(Long userId, String groupName);
    public boolean checkForExistingGroupMemeberByName(Long groupId, String memberName);
   
    public Boolean checkForGroupMembersAvailability(Long userId,Long groupId);
    public List<UserGroupMembersInfoVO> getCompleteUserGroupMemberDetailsForAGroup(Long groupId);
    public List<UserGroupMembersInfoVO> getCompleteUserGroupMemberDetailsForAGroupCatgory(Long groupCategoryId,Long userId);
    public List<UserGroupMembersInfoVO> getCompleteUserGroupMemberDetailsForAUserOfSpecificDesignation(Long designationId,Long userId);
    public List<UserGroupMembersInfoVO> getCompleteUserGroupMemberDetailsForAUserOfSpecificDesignation(Long designationId,Long userId,Long groupCategoryId);
    
 */}
