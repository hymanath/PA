package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.EntitlementVO;

public interface IUserEntitlementService {
	
	public EntitlementVO getAllGroups();
	
	public EntitlementVO checkAvailabilityOfGroup(String name);
	
	public EntitlementVO getAllEntitlements();	
	
	public EntitlementVO checkAvailabilityOfEntitlement(String name);
	
	public EntitlementVO creatingAnEntitlement(String name);
	
	public EntitlementVO getAllDetailsOfAGroup(Long groupId,String name);
	
	public EntitlementVO creatingAGroup(String groupName,String assignedEntitlementIds);
	
	public EntitlementVO getAllUserGroups();
	
	public EntitlementVO saveUserGroupsRelation(Long userId,String groupIds);
	
	public EntitlementVO getAllEntitlementsForAUserGroup(Long userGroupId);
}