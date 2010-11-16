package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICustomMessageDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IMessageTypeDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IUserConnectedtoDAO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.model.MessageType;
import com.itgrids.partyanalyst.service.impl.AnanymousUserService;
import com.itgrids.partyanalyst.util.MockData;

public class AnanymousUserServiceServiceTest {

	private TransactionTemplate transactionTemplate = null;
	
	//DAO's
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ICustomMessageDAO customMessageDAO;
	private IMessageTypeDAO messageTypeDAO;
	private IAnanymousUserDAO ananymousUserDAO;
	private IUserConnectedtoDAO userConnectedtoDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	
	@Before
	public void init(){
		System.out.println("called init method..........");
		stateDAO = EasyMock.createMock(IStateDAO.class);
		districtDAO = EasyMock.createMock(IDistrictDAO.class);
		constituencyDAO = EasyMock.createMock(IConstituencyDAO.class);
		customMessageDAO = EasyMock.createMock(ICustomMessageDAO.class);
		messageTypeDAO = EasyMock.createMock(IMessageTypeDAO.class);
		ananymousUserDAO = EasyMock.createMock(IAnanymousUserDAO.class);
		userConnectedtoDAO = EasyMock.createMock(IUserConnectedtoDAO.class);
		delimitationConstituencyAssemblyDetailsDAO = EasyMock.createMock(IDelimitationConstituencyAssemblyDetailsDAO.class);
	}
	
	@Test
	public void testGetAllMessageTypes(){
		System.out.println("called testGetAllMessageTypes() method..........");
		AnanymousUserService service = new AnanymousUserService();
		 
		List<MessageType> types = MockData.getAllMessageTypes();
		
		service.setMessageTypeDAO(messageTypeDAO);
		
		EasyMock.expect(messageTypeDAO.getAll()).andReturn(types);
		EasyMock.replay(messageTypeDAO);
		NavigationVO navigationVO = service.getAllMessageTypes();
		
		Assert.assertEquals(3,navigationVO.getMessageTypes().size());
	}
	
	@Test
	public void testGetUserIds(){
		System.out.println("called testGetUserIds() method..........");
		AnanymousUserService service = new AnanymousUserService();
		 
		List types = MockData.getAnanymousUserIds();
		
		service.setUserConnectedtoDAO(userConnectedtoDAO);
		List<Long> userIds = new ArrayList<Long>(0);
		userIds.add(1l);
		
		List<Long> ids = new ArrayList<Long>(0);
		ids.add(1l);
		
		EasyMock.expect(userConnectedtoDAO.getAllConnectedPeopleForUser(userIds)).andReturn(types);
		EasyMock.replay(userConnectedtoDAO);
		List<Long> result = service.getUserIds(userIds,ids);
		
		Assert.assertEquals(3,result.size());
	}
	
}
