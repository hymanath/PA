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

import com.itgrids.partyanalyst.dto.GroupsDetailsForUserVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;

public interface IUserGroupService {

	public List<SelectOptionVO> getAllStaticGroupNames();
	public List<SelectOptionVO> getAllMyGroupsCreatedByUser(Long userId);
    
    
    public UserGroupMembersVO addMemberToGroup(Long groupId, UserGroupMembersVO userGroupMembersToSave);
    public UserGroupDetailsVO createGroupForUser(UserGroupDetailsVO userGroupDetailsToSave);
    
    public List <GroupsDetailsForUserVO> subGrpsCountInSystemGrpsForUser(Long userId);
   public List<GroupsDetailsForUserVO> subGroupsCountInMyGroupsForUser(Long userId);
}
