package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyImportantDatesDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IUserEventActionPlanDAO;
import com.itgrids.partyanalyst.dao.IUserEventsDAO;
import com.itgrids.partyanalyst.dto.EventActionPlanVO;
import com.itgrids.partyanalyst.dto.PartyImportantDatesVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserEventVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.PartyImportantDates;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.UserEventActionPlan;
import com.itgrids.partyanalyst.model.UserEvents;
import com.itgrids.partyanalyst.service.IUserCalendarService;
import com.itgrids.partyanalyst.utils.IConstants;

/**
 * 
 * @author Narender Akula
 *
 */
public class UserCalendarService implements IUserCalendarService {

	private IRegistrationDAO registrationDAO;
	private IUserEventsDAO userEventsDAO;
	private IUserEventActionPlanDAO userEventActionPlanDAO;
	private IPartyDAO partyDAO;
	private ICadreDAO cadreDAO;
	private IPartyImportantDatesDAO partyImportantDatesDAO;
	private final static Logger log = Logger.getLogger(UserCalendarService.class);
	
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

	public void setPartyImportantDatesDAO(
			IPartyImportantDatesDAO partyImportantDatesDAO) {
		this.partyImportantDatesDAO = partyImportantDatesDAO;
	}

	
	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	public List<PartyImportantDatesVO> getUserImpDates(Long userID, Long partyId) {

		List<PartyImportantDatesVO> importantDates = new ArrayList<PartyImportantDatesVO>(0);
		Registration user = registrationDAO.get(userID);
		if("ALL".equals(user.getIncludePartyImpDateStatus())){
			List<PartyImportantDates> importantDatesByPartyId = partyImportantDatesDAO.findByPartyId(partyId);
			
			if(importantDatesByPartyId != null){
			
				for(PartyImportantDates dates:importantDatesByPartyId ){
				
					PartyImportantDatesVO importantDatesVO = new PartyImportantDatesVO();
				
					importantDatesVO.setPartyId(dates.getParty().getPartyId());
					importantDatesVO.setImportantDateId(dates.getPartyImportantDateId());
					importantDatesVO.setImportance(dates.getImportance());
					importantDatesVO.setDate(dates.getDate().toString());			
					importantDatesVO.setRecursive(dates.getRecursive());
					
					importantDates.add(importantDatesVO);
				}
			
			}
		}
		
		return importantDates;
	}

	public List<UserEventVO> getUserPlannedEvents(Long userID) {
		if(log.isDebugEnabled())
			log.debug("UserCalendar.getUserPlannedEvents() start");
		List<UserEvents> userEventsList = userEventsDAO.findEventsByUserId(userID);
		List<UserEventVO> userEventVOList = new ArrayList<UserEventVO>();
		for(UserEvents userEvent : userEventsList){
			UserEventVO userEventVO = convertUserEvents2DTO(userEvent);
			userEventVOList.add(userEventVO);
		}
		return userEventVOList;
	}
	
	public void deleteUserPlannedEvents(Long userEventID) {
		userEventsDAO.remove(userEventID);
	}

	public UserEventVO saveUserPlannedEvents(UserEventVO userPlannedEvents) {
		if(log.isDebugEnabled())
			log.debug("UserCalendar.saveuserPlannedEvents() start");
		UserEvents userEvent = convertDTO2UserEvents(userPlannedEvents);
		try{
			userEvent = userEventsDAO.save(userEvent);
			userPlannedEvents.setUserEventsId(userEvent.getUserEventsId());
			List<EventActionPlanVO> userEventActionPlanVOs = userPlannedEvents.getActionPlans();
			for(EventActionPlanVO userEventActionPlanVO : userEventActionPlanVOs){
				UserEventActionPlan userEventActionPlan = converDTO2UserEventActionPlan(userEventActionPlanVO);
				userEventActionPlan.setUserEvents(userEvent);
				userEventActionPlan=userEventActionPlanDAO.save(userEventActionPlan);
				userEventActionPlanVO.setEventActionPlanId(userEventActionPlan.getEventActionPlanId());
			}
		}catch (Exception e) {
			userPlannedEvents.setExceptionEncountered(e);
			userPlannedEvents.setResultCode(ResultCodeMapper.FAILURE);
		}
		return userPlannedEvents;
	}

	private UserEvents convertDTO2UserEvents(UserEventVO userPlannedEvents){
		UserEvents userEvents = new UserEvents();
		userEvents.setUserEventsId(userPlannedEvents.getUserEventsId());
		Registration user = registrationDAO.get(userPlannedEvents.getUserID());
		userEvents.setRegistration(user);
		userEvents.setDescription(userPlannedEvents.getDescription());
		userEvents.setLocationType(userPlannedEvents.getLocationType());
		userEvents.setLocationId(userPlannedEvents.getLocationId());
		userEvents.setStartDate(userPlannedEvents.getStartDate());
		userEvents.setEndDate(userPlannedEvents.getEndDate());
		userEvents.setTitle(userPlannedEvents.getTitle());
		List<SelectOptionVO> organizerIDs =userPlannedEvents.getOrganizers();
		if(organizerIDs!=null &&organizerIDs.size()>0){
			StringBuilder sb = new StringBuilder();
			for(SelectOptionVO obj : organizerIDs){
				sb.append(",").append(obj.getId());
			}
			List<Cadre> organizers = cadreDAO.findByCadreIDs(sb.substring(1));
			userEvents.setOrganizers(organizers);
		}
		return userEvents;
	}

	private UserEventActionPlan converDTO2UserEventActionPlan(EventActionPlanVO userEventActionPlanVO){
		UserEventActionPlan userEventActionPlan = new UserEventActionPlan();
		userEventActionPlan.setEventActionPlanId(userEventActionPlanVO.getEventActionPlanId());
		userEventActionPlan.setAction(userEventActionPlanVO.getAction());
		userEventActionPlan.setTargetDate(userEventActionPlanVO.getTargetDate());
		
		List<SelectOptionVO> eventActionOrganizers = userEventActionPlanVO.getActionPlanOrganizers();
		 
		if(eventActionOrganizers!=null &&eventActionOrganizers.size()>0){
			StringBuilder sb = new StringBuilder();
			for(SelectOptionVO obj : eventActionOrganizers){
				sb.append(",").append(obj.getId());
			}
			List<Cadre> organizers = cadreDAO.findByCadreIDs(sb.substring(1));
			userEventActionPlan.setOrganizers(organizers);
		}
		
		return userEventActionPlan;
	}
	private UserEventVO convertUserEvents2DTO(UserEvents userEvent){
		UserEventVO userEventVO = new UserEventVO();
		userEventVO.setUserEventsId(userEvent.getUserEventsId());
		userEventVO.setUserID(userEvent.getRegistration().getRegistrationId());
		userEventVO.setDescription(userEvent.getDescription());
		userEventVO.setLocationId(userEvent.getLocationId());
		userEventVO.setLocationType(userEvent.getLocationType());
		userEventVO.setStartDate(userEvent.getStartDate());
		userEventVO.setEndDate(userEvent.getEndDate());
		userEventVO.setTitle(userEvent.getTitle());
		List<Cadre> organizers = userEvent.getOrganizers();
		if(organizers!=null && organizers.size()>0){
			List<SelectOptionVO> organizersVO = new ArrayList<SelectOptionVO>();
			for(Cadre cadre : organizers){
				SelectOptionVO obj = new SelectOptionVO();
				obj.setId(cadre.getCadreId());
				StringBuilder name = new StringBuilder();
				name.append(cadre.getFirstName()).append(IConstants.SPACE).append(
						cadre.getMiddleName()).append(IConstants.SPACE).append(
						cadre.getLastName());
				obj.setName(name.toString());
				organizersVO.add(obj);
			}
			userEventVO.setOrganizers(organizersVO);
		}	

		List<UserEventActionPlan> userEventActionPlanList = userEvent.getUserEventActionPlans();
		if(userEventActionPlanList!=null && userEventActionPlanList.size()>0){
			List<EventActionPlanVO> eventActionPlanVOList = new ArrayList<EventActionPlanVO>();
			for(UserEventActionPlan userEventActionPlan : userEventActionPlanList){
				EventActionPlanVO eventActionPlanVO = convertUserEventActionPlan2DTO(userEventActionPlan);
				eventActionPlanVOList.add(eventActionPlanVO);
			}
			userEventVO.setActionPlans(eventActionPlanVOList);
		}
		
		return userEventVO;
	}
	
	private EventActionPlanVO convertUserEventActionPlan2DTO(UserEventActionPlan userEventActionPlan){
		EventActionPlanVO eventActionPlanVO = new EventActionPlanVO();
		eventActionPlanVO.setEventActionPlanId(userEventActionPlan.getEventActionPlanId());
		eventActionPlanVO.setAction(userEventActionPlan.getAction());
		eventActionPlanVO.setUserEventsId(userEventActionPlan.getUserEvents().getUserEventsId());
		eventActionPlanVO.setTargetDate(userEventActionPlan.getTargetDate());
		List<Cadre> organizers = userEventActionPlan.getOrganizers();
		if(organizers!=null && organizers.size()>0){
			List<SelectOptionVO> organizersVO = new ArrayList<SelectOptionVO>();
			for(Cadre cadre : organizers){
				SelectOptionVO obj = new SelectOptionVO();
				obj.setId(cadre.getCadreId());
				StringBuilder name = new StringBuilder();
				name.append(cadre.getFirstName()).append(IConstants.SPACE).append(
						cadre.getMiddleName()).append(IConstants.SPACE).append(
						cadre.getLastName());
				obj.setName(name.toString());
				organizersVO.add(obj);
			}
			eventActionPlanVO.setActionPlanOrganizers(organizersVO);
		}
		
		return eventActionPlanVO;
	}
	
}
