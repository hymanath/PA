package com.itgrids.partyanalyst.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyImportantDatesDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IUserEventActionPlanDAO;
import com.itgrids.partyanalyst.dao.IUserEventsDAO;
import com.itgrids.partyanalyst.dao.IUserImpDatesDAO;
import com.itgrids.partyanalyst.dao.hibernate.UserImpDatesDAO;
import com.itgrids.partyanalyst.dto.ImportantDatesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserEventVO;
import com.itgrids.partyanalyst.model.PartyImportantDates;
import com.itgrids.partyanalyst.model.UserEvents;
import com.itgrids.partyanalyst.model.UserImpDate;
import com.itgrids.partyanalyst.service.impl.UserCalendarService;
import com.itgrids.partyanalyst.util.MockData;
import common.Logger;

public class UserCalendarServiceTest {
	private static Logger log = Logger.getLogger(UserCalendarServiceTest.class);
	
	private IRegistrationDAO registrationDAO;
	private IUserEventsDAO userEventsDAO;
	private IUserEventActionPlanDAO userEventActionPlanDAO;
	private IPartyDAO partyDAO;
	private ICadreDAO cadreDAO; 
	private ITehsilDAO tehsilDAO;
	private IDistrictDAO districtDAO;
	private IStateDAO stateDAO;
	private IConstituencyDAO constituencyDAO;
	private ITownshipDAO townshipDAO;
	private IHamletDAO hamletDAO;
	private IUserImpDatesDAO userImpDatesDAO;
	private IPartyImportantDatesDAO partyImportantDatesDAO;

	public void setUserImpDatesDAO(IUserImpDatesDAO userImpDatesDAO) {
		this.userImpDatesDAO = userImpDatesDAO;
	}

	public void setPartyImportantDatesDAO(
			IPartyImportantDatesDAO partyImportantDatesDAO) {
		this.partyImportantDatesDAO = partyImportantDatesDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}
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
		tehsilDAO = EasyMock.createMock(ITehsilDAO.class);
		partyImportantDatesDAO = EasyMock.createMock(IPartyImportantDatesDAO.class);
		userImpDatesDAO = EasyMock.createMock(IUserImpDatesDAO.class);
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
	public void testGetUserImportantDates() {
		UserCalendarService service = new UserCalendarService();
		RegistrationVO user = MockData.getRegistrationVO();
		List<UserImpDate> impDateVOs = MockData.getTodaysUserImpDates();
		List<PartyImportantDates> partyImpDates = MockData.getPartyImpdates();
		EasyMock.expect(partyImportantDatesDAO.findTodaysPartyImportantDates(62L)).andReturn(partyImpDates);
		EasyMock.expect(userImpDatesDAO.findTodayImportantEvents(user.getRegistrationID())).andReturn(impDateVOs);
		EasyMock.replay(partyImportantDatesDAO);
		EasyMock.replay(userImpDatesDAO);
		service.setPartyImportantDatesDAO(partyImportantDatesDAO);
		service.setUserImpDatesDAO(userImpDatesDAO);
		List<ImportantDatesVO> impDatesList = service.getUserTodaysImportantEvents(user);
		Assert.assertEquals(2, impDatesList.size());
		Assert.assertEquals("My Birthday as party candidate", impDatesList.get(0).getTitle());
		Assert.assertEquals("User", impDatesList.get(0).getEventType());
		Assert.assertEquals("Party", impDatesList.get(1).getEventType());
		Assert.assertEquals("PARTY PRESIDENT'S BIRTHDAY", impDatesList.get(1).getTitle());
		EasyMock.verify(partyImportantDatesDAO);
		EasyMock.verify(userImpDatesDAO);
		System.out.println(impDatesList.size());
	}
	
	@Test
	public void testGetUserPlannedEvents(){
		UserCalendarService service = new UserCalendarService();
		List<UserEvents> userEvents = MockData.getUserPlannedEvents();
		EasyMock.expect(userEventsDAO.findEventsByUserId(new Long(1))).andReturn(userEvents);
		EasyMock.replay(userEventsDAO);
		service.setUserEventsDAO(userEventsDAO);
		
		List<UserEventVO> userEventVOList = service.getUserPlannedEvents(1L,Calendar.getInstance());
		Assert.assertEquals(1, userEventVOList.size());
		//Assert.assertEquals(0, userEventVOList.get(0).getActionPlans().size());
	}
	
	@Test
	public void testGetTodaysUserEvents() {
		UserCalendarService service = new UserCalendarService();
		List<UserEvents> userEvents = MockData.getTodaysUserEvents();
		SimpleDateFormat frmat = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = frmat.format(new Date());
		
		
		try {
			EasyMock.expect(userEventsDAO.findEventsByUserIdAndStartDate(4L, frmat.parse(startDate) )).andReturn(userEvents);
			EasyMock.expect(tehsilDAO.get(833L)).andReturn(MockData.getTehsils().get(0));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EasyMock.replay(userEventsDAO);
		EasyMock.replay(tehsilDAO);
		service.setUserEventsDAO(userEventsDAO);
		service.setTehsilDAO(tehsilDAO);
		List<UserEventVO> userEventVOList = service.getTodaysUserPlannedEvents(4L);
	
		Assert.assertEquals(1, userEventVOList.size());
		Assert.assertEquals("Village level meeting with party candidates", userEventVOList.get(0).getDescription());
		Assert.assertEquals(new Long(833), userEventVOList.get(0).getLocationId());
		Assert.assertEquals("Kondapuram", userEventVOList.get(0).getLocation());
		EasyMock.verify(userEventsDAO);
	}
}
