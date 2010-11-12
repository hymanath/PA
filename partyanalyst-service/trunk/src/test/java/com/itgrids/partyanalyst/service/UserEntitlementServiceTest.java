package com.itgrids.partyanalyst.service;

import java.util.List;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IEntitlementDAO;
import com.itgrids.partyanalyst.dao.IGroupEntitlementDAO;
import com.itgrids.partyanalyst.dao.IGroupEntitlementRelationDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IUserGroupEntitlementDAO;
import com.itgrids.partyanalyst.dao.IUserGroupRelationDAO;
import com.itgrids.partyanalyst.dao.IUserGroupsDAO;
import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.service.impl.UserEntitlementService;
import com.itgrids.partyanalyst.util.MockData;

public class UserEntitlementServiceTest {

	private IEntitlementDAO entitlementDAO;
	private IGroupEntitlementDAO groupEntitlementDAO;
	private IGroupEntitlementRelationDAO groupEntitlementRelationDAO;
	private IUserGroupEntitlementDAO userGroupEntitlementDAO;
	private IUserGroupsDAO userGroupsDAO;
	private IUserGroupRelationDAO userGroupRelationDAO;
	private IRegistrationDAO registrationDAO;

	
	@Before
	public void init(){
		System.out.println("called init method..........");
		entitlementDAO = EasyMock.createMock(IEntitlementDAO.class);
		groupEntitlementDAO = EasyMock.createMock(IGroupEntitlementDAO.class);
		groupEntitlementRelationDAO = EasyMock.createMock(IGroupEntitlementRelationDAO.class);
		userGroupEntitlementDAO = EasyMock.createMock(IUserGroupEntitlementDAO.class);
		userGroupsDAO = EasyMock.createMock(IUserGroupsDAO.class);
		userGroupRelationDAO = EasyMock.createMock(IUserGroupRelationDAO.class);
		registrationDAO = EasyMock.createMock(IRegistrationDAO.class);
	}
	
	@Test
	public void testGetAllGroups(){
		System.out.println("called tesGetAllGroups() method..........");
		UserEntitlementService service = new UserEntitlementService();
		List mockResult = MockData.getAllGroups();
		service.setGroupEntitlementDAO(groupEntitlementDAO);		
		EasyMock.expect(groupEntitlementDAO.getAllGroups()).andReturn(mockResult);
		EasyMock.replay(groupEntitlementDAO);
		EntitlementVO s = service.getAllGroups();
		Assert.assertEquals(1, s.getSetOfGroups());
	}
	
	@Test
	public void testGetAllEntitlements(){
		System.out.println("called GetAllEntitlements() method..........");
		UserEntitlementService service = new UserEntitlementService();
		List mockResult = MockData.getAllEntitlements();
		service.setEntitlementDAO(entitlementDAO);		
		EasyMock.expect(entitlementDAO.getAllEntitlements()).andReturn(mockResult);
		EasyMock.replay(entitlementDAO);
		EntitlementVO s = service.getAllEntitlements();
		Assert.assertEquals(1, s.getListOfEntitlements());
	}
	
	@Test
	public void testCreatingAGroup(){
		System.out.println("called CreatingAGroup() method..........");
		UserEntitlementService service = new UserEntitlementService();
		service.setGroupEntitlementDAO(groupEntitlementDAO);
		service.setEntitlementDAO(entitlementDAO);
		service.setGroupEntitlementRelationDAO(groupEntitlementRelationDAO);
		
		List mockResult = MockData.getAllGroups();
		EasyMock.expect(groupEntitlementDAO.checkForGroupNameAvailability("PRIVILEGED_ENTITLEMENTS")).andReturn(mockResult);		
		EasyMock.replay(groupEntitlementDAO);
				
		EntitlementVO s = service.creatingAGroup("PRIVILEGED_ENTITLEMENTS","1,2,3,");
		
		Assert.assertEquals("CROSS_VOTING_REPORT", s.getName());
	}
	
	
	@Test
	public void testCheckAvailabilityOfGroup(){
		System.out.println("called CheckAvailabilityOfGroup() method..........");
		UserEntitlementService service = new UserEntitlementService();
		List mockResult = MockData.getAllGroups();
		service.setGroupEntitlementDAO(groupEntitlementDAO);		
		EasyMock.expect(groupEntitlementDAO.checkForGroupNameAvailability("DEFAULT_ENTITLEMENTS")).andReturn(mockResult);
		EasyMock.replay(groupEntitlementDAO);
		EntitlementVO s = service.checkAvailabilityOfGroup("DEFAULT_ENTITLEMENTS");
		Assert.assertEquals("DEFAULT_ENTITLEMENTS", s.getName());
	}
	
	@Test
	public void testCheckAvailabilityOfEntitlement(){
		System.out.println("called CheckAvailabilityOfEntitlement() method..........");
		UserEntitlementService service = new UserEntitlementService();
		List mockResult = MockData.getAllEntitlements();
		service.setEntitlementDAO(entitlementDAO);		
		EasyMock.expect(entitlementDAO.checkForEntitlementAvailability("PARTY_INFLUENCE_REPORT")).andReturn(mockResult);
		EasyMock.replay(entitlementDAO);
		EntitlementVO s = service.checkAvailabilityOfEntitlement("PARTY_INFLUENCE_REPORT");
		Assert.assertEquals("PARTY_INFLUENCE_REPORT",s.getName());
	}
	
	@Test
	public void testCheckForUserGroupNameAvailability(){
		System.out.println("called CheckForUserGroupNameAvailability() method..........");
		UserEntitlementService service = new UserEntitlementService();
		List mockResult = MockData.getAllUserGroups();
		service.setUserGroupsDAO(userGroupsDAO);		
		EasyMock.expect(userGroupsDAO.checkForUserAvailability("NORMAL_USER")).andReturn(mockResult);
		EasyMock.replay(userGroupsDAO);
		EntitlementVO s = service.checkForUserGroupNameAvailability("NORMAL_USER");
		Assert.assertEquals("NORMAL_USER",s.getName());
	}
	
	
	
}
