package com.itgrids.partyanalyst.service;

import java.util.Calendar;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IUserEventActionPlanDAO;
import com.itgrids.partyanalyst.dao.IUserEventsDAO;
import com.itgrids.partyanalyst.dto.UserEventVO;
import com.itgrids.partyanalyst.model.UserEvents;
import com.itgrids.partyanalyst.service.impl.UserCalendarService;
import com.itgrids.partyanalyst.util.MockData;

public class UserCalendarServiceTest {
	private IRegistrationDAO registrationDAO;
	private IUserEventsDAO userEventsDAO;
	private IUserEventActionPlanDAO userEventActionPlanDAO;
	private IPartyDAO partyDAO;
	private ICadreDAO cadreDAO; 
	
	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}
	
	public void setUserEventsDAO(IUserEventsDAO userEventsDAO) {
		this.userEventsDAO = userEventsDAO;
	}

	public void setUserEventActionPlanDAO(
			IUserEventActionPlanDAO userEventActionPlanDAO) {
		this.userEventActionPlanDAO = userEventActionPlanDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	@Before
	public void init(){
		registrationDAO = EasyMock.createMock(IRegistrationDAO.class);
		userEventsDAO = EasyMock.createMock(IUserEventsDAO.class);
		userEventActionPlanDAO = EasyMock.createMock(IUserEventActionPlanDAO.class);
		partyDAO = EasyMock.createMock(IPartyDAO.class);
		cadreDAO = EasyMock.createMock(ICadreDAO.class);
	}	

	/*@Test
	public void testSaveUserEvents(){
		UserCalendarService service = new UserCalendarService();
		UserEventVO userPlannedEvents = MockData.getUserEventVO();
		UserEvents userEvents = new UserEvents();
		userEvents.setUserEventsId(userPlannedEvents.getUserEventsId());
		Registration user = new Registration();//registrationDAO.get(userPlannedEvents.getUserID());
		user.setRegistrationId(1L);
		userEvents.setRegistration(user);
		userEvents.setDescription(userPlannedEvents.getDescription());
		userEvents.setLocationType(userPlannedEvents.getLocationType());
		userEvents.setLocationId(userPlannedEvents.getLocationId());
		userEvents.setStartDate(userPlannedEvents.getStartDate());
		userEvents.setEndDate(userPlannedEvents.getEndDate());
		List<Cadre> organizers = new ArrayList<Cadre>();//cadreDAO.findByCadreIDs(sb.substring(1));
		Cadre cadre1 = new Cadre(); cadre1.setCadreId(1L);
		organizers.add(cadre1);
		userEvents.setOrganizers(organizers);
		
		
		
		List<EventActionPlanVO> actionPlanList = userPlannedEvents.getActionPlans();
		EventActionPlanVO userEventActionPlanVO = actionPlanList.get(0);

		UserEventActionPlan userEventActionPlan = new UserEventActionPlan();
		userEventActionPlan.setEventActionPlanId(userEventActionPlanVO.getEventActionPlanId());
		userEventActionPlan.setAction(userEventActionPlanVO.getAction());
		userEventActionPlan.setTargetDate(userEventActionPlanVO.getTargetDate());
		
		List<SelectOptionVO> eventActionOrganizers = userEventActionPlanVO.getActionPlanOrganizers();
		List<Cadre> cadres = new ArrayList<Cadre>();
		Cadre cadre = new Cadre();cadre.setCadreId(eventActionOrganizers.get(0).getId());
		cadres.add(cadre);
		userEventActionPlan.setOrganizers(cadres);
		List<UserEventActionPlan> userPlans = new ArrayList<UserEventActionPlan>();
		userPlans.add(userEventActionPlan);
		userEvents.setUserEventActionPlans(userPlans);

		EasyMock.expect(registrationDAO.get(1L)).andReturn(user);		
		EasyMock.expect(cadreDAO.findByCadreIDs("1")).andReturn(organizers);		
		EasyMock.expect(userEventsDAO.save(userEvents)).andReturn(userEvents);
		EasyMock.expect(cadreDAO.findByCadreIDs("4")).andReturn(cadres); 
		EasyMock.expect(userEventActionPlanDAO.save(userEventActionPlan)).andReturn(userEventActionPlan); 
		
		EasyMock.replay(registrationDAO); 
		service.setRegistrationDAO(registrationDAO);
		
		EasyMock.replay(userEventsDAO); 
		service.setUserEventsDAO(userEventsDAO);
		
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		
		EasyMock.replay(userEventActionPlanDAO); 
		service.setUserEventActionPlanDAO(userEventActionPlanDAO);
		
		UserEventVO actualResult = service.saveUserPlannedEvents(userPlannedEvents);
		Assert.assertEquals(null, actualResult.getExceptionEncountered());
		EasyMock.verify(userEventsDAO);
		EasyMock.verify(userEventActionPlanDAO);
	}*/
	
	@Test
	public void testGetUserPlannedEvents(){
		UserCalendarService service = new UserCalendarService();
		List<UserEvents> userEvents = MockData.getUserPlannedEvents();
		EasyMock.expect(userEventsDAO.findEventsByUserId(new Long(1))).andReturn(userEvents);
		EasyMock.replay(userEventsDAO);
		service.setUserEventsDAO(userEventsDAO);
		
		List<UserEventVO> userEventVOList = service.getUserPlannedEvents(1L,Calendar.getInstance());
		Assert.assertEquals(2, userEventVOList.size());
		Assert.assertEquals(2, userEventVOList.get(0).getActionPlans().size());
	}
}
