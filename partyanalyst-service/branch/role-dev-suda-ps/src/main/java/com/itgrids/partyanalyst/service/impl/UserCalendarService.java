package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyImportantDatesDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IUserEventActionPlanDAO;
import com.itgrids.partyanalyst.dao.IUserEventsDAO;
import com.itgrids.partyanalyst.dao.IUserImpDatesDAO;
import com.itgrids.partyanalyst.dto.EventActionPlanVO;
import com.itgrids.partyanalyst.dto.ImportantDatesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserEventVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.PartyImportantDates;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.UserEventActionPlan;
import com.itgrids.partyanalyst.model.UserEvents;
import com.itgrids.partyanalyst.model.UserImpDate;
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
	private IUserImpDatesDAO userImpDatesDAO;
	private TransactionTemplate transactionTemplate;

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

	public void setUserImpDatesDAO(IUserImpDatesDAO userImpDatesDAO) {
		this.userImpDatesDAO = userImpDatesDAO;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate){
		this.transactionTemplate = transactionTemplate;
	}
	private Long userID;
	private String partySubscribeImpDates;
	public void userSubscribePartyImpDates(Long userID, String partySubscribeImpDates){
		this.userID = userID;
		this.partySubscribeImpDates = partySubscribeImpDates;
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus txStatus) {
				try{
					Registration user = registrationDAO.get(UserCalendarService.this.userID);
					user.setIncludePartyImpDateStatus(UserCalendarService.this.partySubscribeImpDates);
					user = registrationDAO.save(user);
				}catch(Exception ex){
					
				}
			}
			}
		);
	}
	public List<ImportantDatesVO> getUserImpDates(RegistrationVO user) {
		log.debug("UserCalenderService.getUserImpDates() Start...");

		Long userID = user.getRegistrationID();
		Long partyId = user.getParty();
		List<ImportantDatesVO> importantDates = new ArrayList<ImportantDatesVO>(0);
		//Registration user = registrationDAO.get(userID);
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
		if("ALL".equals(user.getSubscribePartyImpDate())){
			List<PartyImportantDates> partyImportantDates = partyImportantDatesDAO.findByPartyId(partyId);
			
			if(partyImportantDates != null){
			
				for(PartyImportantDates impDate:partyImportantDates ){
					List<ImportantDatesVO> importantDatesVOs =convertPartyImpDateModel2DTO(impDate);
					for(ImportantDatesVO importantDatesVO : importantDatesVOs){
						importantDates.add(importantDatesVO);
					}
				}
			
			}
		}
		List<UserImpDate> userImpDates = userImpDatesDAO.findByUserId(userID);
		log.debug("UserCalenderService.getUserImpDates() userImpDates.size()"+userImpDates.size());
		if(userImpDates != null){
			for(UserImpDate userImpDate : userImpDates){				
				List<ImportantDatesVO> userImpDateVOs = convertUserImpDateModel2DTO(userImpDate);
				log.debug("User Imp Dates Recurssive Type size::"+ userImpDateVOs.size());
				for(ImportantDatesVO userImpDateVO : userImpDateVOs){
					importantDates.add(userImpDateVO);
				}
			}
		}
		if(importantDates.size()>1)
			Collections.sort(importantDates);
		log.debug("userImportant date size:::"+importantDates.size());
		return importantDates;
	}

	private ImportantDatesVO createImportantDatesVOForUser(Calendar calendar,UserImpDate impDate){
		//SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
		ImportantDatesVO importantDatesVO = new ImportantDatesVO();
		importantDatesVO.setEventType("User");
		importantDatesVO.setImportantDateId(impDate.getUserImpDateID());
		importantDatesVO.setEventId(impDate.getUser().getRegistrationId());
		importantDatesVO.setTitle(impDate.getTitle());
		importantDatesVO.setImportance(impDate.getDescription());
		importantDatesVO.setStartDate(impDate.getEffectiveDate());
		importantDatesVO.setEndDate(impDate.getTillDate());
		importantDatesVO.setImpDate(calendar.getTime());
		
		return importantDatesVO;
	}
	private List<ImportantDatesVO> convertUserImpDateModel2DTO(UserImpDate impDate){
		List<ImportantDatesVO> importantDatesVOs = new ArrayList<ImportantDatesVO>();
		Calendar startCalendar = Calendar.getInstance();
		int dayOfWeek1 = startCalendar.get(Calendar.DAY_OF_WEEK);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.add(Calendar.DATE, 60);
		
		Date startDate = impDate.getEffectiveDate();		
		int sMonth = startDate.getMonth();
		int sDay = startDate.getDate();
		
		Date endDate = impDate.getTillDate();		
		int eMonth = startDate.getMonth();
		int eDay = startDate.getDate();
		
		Calendar calendar = Calendar.getInstance();
		int currentYear =calendar.get(Calendar.YEAR);
		int currentMonth =calendar.get(Calendar.MONTH);
		int currentDay =calendar.get(Calendar.DAY_OF_MONTH);
		log.debug("Imp Recursive Date Type:::"+impDate.getRecFreqType());
		if("YEARLY".equalsIgnoreCase(impDate.getRecFreqType())){
			if(calendar.get(Calendar.MONTH)==11 && sMonth <=1)
				++currentYear;
			calendar.set(currentYear,sMonth,sDay);
			if(!(calendar.before(startCalendar)) && (!calendar.after(endCalendar))){				
				ImportantDatesVO importantDatesVO = createImportantDatesVOForUser(calendar,impDate);			 
				importantDatesVOs.add(importantDatesVO);
			}
		} else if("MONTHLY".equalsIgnoreCase(impDate.getRecFreqType())){
			int months[] = new int[2];
			if(currentDay<=sDay){
				months[0]=currentMonth;
				months[1]=currentMonth+1;
			}else {
				months[0]=currentMonth+1;
				months[1]=currentMonth+2;
			}
			for(int m : months){
				Calendar currentCalendar = Calendar.getInstance();
				currentCalendar.set(currentYear,m,sDay);
				if(!(currentCalendar.before(startCalendar)) && (!currentCalendar.after(endCalendar))){
					ImportantDatesVO importantDatesVO = createImportantDatesVOForUser(currentCalendar,impDate);
					importantDatesVOs.add(importantDatesVO);
				}
				
			}
		} else if("WEEKLY".equalsIgnoreCase(impDate.getRecFreqType())){
			//sun -1, mon-2, tue - 3,.... sat-7
			calendar.setTime(startDate);
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			
			Calendar calCalendar = Calendar.getInstance();
			//int difference = dayOfWeek1 - dayOfWeek;
			int nextWeekDay = 0;
			while(true){
				int updatedDOW =calCalendar.get(Calendar.DAY_OF_WEEK);
				if(dayOfWeek == updatedDOW)
					break;
				calCalendar.add(Calendar.DATE, 1);
				nextWeekDay++;
			}
			for(int i=nextWeekDay; i<61; i+=7){
				int calDay = currentDay +i;
				Calendar cal = Calendar.getInstance();
				cal.set(currentYear,currentMonth,calDay);
				if(cal.before(startCalendar) || cal.after(endCalendar)){
					break;
				}
				ImportantDatesVO importantDatesVO = createImportantDatesVOForUser(cal,impDate);
				importantDatesVOs.add(importantDatesVO);				
			}
		}
		return importantDatesVOs;
	}
	private List<ImportantDatesVO> convertPartyImpDateModel2DTO(PartyImportantDates impDate){

		List<ImportantDatesVO> importantDatesVOs = new ArrayList<ImportantDatesVO>();
		//SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
		Calendar startCalendar = Calendar.getInstance();
		//int year1 = startCalendar.get(Calendar.YEAR);
		//int month1 = startCalendar.get(Calendar.MONTH);
		//int day1 = startCalendar.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek1 = startCalendar.get(Calendar.DAY_OF_WEEK);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.add(Calendar.DATE, 60);
		//int year2 = endCalendar.get(Calendar.YEAR);
		//int month2 = endCalendar.get(Calendar.MONTH);
		//int day2 = endCalendar.get(Calendar.DAY_OF_MONTH);
		//int dayOfWeek2 = endCalendar.get(Calendar.DAY_OF_WEEK);
		
		Date startDate = impDate.getImportantDate();		
		int month = startDate.getMonth();
		int day = startDate.getDate();
		
		Calendar calendar = Calendar.getInstance();
		int currentYear =calendar.get(Calendar.YEAR);
		int currentMonth =calendar.get(Calendar.MONTH);
		int currentDay =calendar.get(Calendar.YEAR);
		
		if("YEARLY".equalsIgnoreCase(impDate.getRecursiveFrequency())){
			if(calendar.get(Calendar.MONTH)==11 && month <=1)
				++currentYear;
			calendar.set(currentYear,month,day);
			if(!(calendar.before(startCalendar)) && (!calendar.after(endCalendar))){				
				ImportantDatesVO importantDatesVO = createImportantDatesVOForParty(calendar,impDate);			 
				importantDatesVOs.add(importantDatesVO);
			}
		} else if("MONTHLY".equalsIgnoreCase(impDate.getRecursiveFrequency())){
			int months[] = new int[2];
			if(currentDay<=day){
				months[0]=currentMonth;
				months[1]=currentMonth+1;
			}else {
				months[0]=currentMonth+1;
				months[1]=currentMonth+2;
			}
			for(int m : months){
				Calendar currentCalendar = Calendar.getInstance();
				currentCalendar.set(currentYear,m,day);
				if(!(currentCalendar.before(startCalendar)) && (!currentCalendar.after(endCalendar))){
					ImportantDatesVO importantDatesVO = createImportantDatesVOForParty(currentCalendar,impDate);
					importantDatesVOs.add(importantDatesVO);
				}
				
			}
		} else if("WEEKLY".equalsIgnoreCase(impDate.getRecursiveFrequency())){
			//sun -1, mon-2, tue - 3,.... sat-7
			calendar.setTime(startDate);
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			
			Calendar calCalendar = Calendar.getInstance();
			//int difference = dayOfWeek1 - dayOfWeek;
			int nextWeekDay = 0;
			while(true){
				int updatedDOW =calCalendar.get(Calendar.DAY_OF_WEEK);
				if(dayOfWeek == updatedDOW)
					break;
				calCalendar.add(Calendar.DATE, 1);
				nextWeekDay++;
			}
			for(int i=nextWeekDay; i<61; i+=7){
				int calDay = currentDay +i;
				Calendar cal = Calendar.getInstance();
				cal.set(currentYear,currentMonth,calDay);
				if(cal.before(startCalendar) || cal.after(endCalendar)){
					break;
				}
				ImportantDatesVO importantDatesVO = createImportantDatesVOForParty(cal,impDate);
				importantDatesVOs.add(importantDatesVO);				
			}
		}
		return importantDatesVOs;
	}
	
	private ImportantDatesVO createImportantDatesVOForParty(Calendar calendar,PartyImportantDates impDate){
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
		ImportantDatesVO importantDatesVO = new ImportantDatesVO();
		importantDatesVO.setEventType("Party");
		importantDatesVO.setImportantDateId(impDate.getPartyImportantDateId());
		importantDatesVO.setEventId(impDate.getParty().getPartyId());
		importantDatesVO.setTitle(impDate.getTitle());
		importantDatesVO.setImportance(impDate.getImportance());
		importantDatesVO.setImpDate(calendar.getTime());	
		importantDatesVO.setStartDate(impDate.getImportantDate());
		return importantDatesVO;
	}

	public List<UserEventVO> getUserPlannedEvents(Long userID) {
		if(log.isDebugEnabled())
			log.debug("UserCalendar.getUserPlannedEvents() start");
		
		Calendar startCalendar = Calendar.getInstance();
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.add(Calendar.DATE, 60);
		
		List<UserEvents> userEventsList = userEventsDAO.findEventsByUserId(userID);
		List<UserEventVO> userEventVOList = new ArrayList<UserEventVO>();
		for(UserEvents userEvent : userEventsList){
			Date startDate= userEvent.getStartDate();
			Calendar dbCalendar = Calendar.getInstance();
			dbCalendar.setTime(startDate);
			if(dbCalendar.before(startCalendar) || dbCalendar.after(endCalendar))
				continue;
			UserEventVO userEventVO = convertUserEvents2DTO(userEvent);
			userEventVOList.add(userEventVO);
		}
		Collections.sort(userEventVOList);
		return userEventVOList;
	}
	
	public void deleteUserPlannedEvents(Long userEventID) {
		userEventsDAO.remove(userEventID);
	}
private UserEventVO saveUserPlannedEvents;
	public UserEventVO saveUserPlannedEvents(UserEventVO userPlannedEvents) {
		saveUserPlannedEvents = userPlannedEvents;
		if(log.isDebugEnabled())
			log.debug("UserCalendar.saveuserPlannedEvents() start");

		transactionTemplate.execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus txStatus) {
				try{
					UserEvents userEvent = convertDTO2UserEvents(UserCalendarService.this.saveUserPlannedEvents);
					userEvent = userEventsDAO.save(userEvent);
					UserCalendarService.this.saveUserPlannedEvents.setUserEventsId(userEvent.getUserEventsId());
					List<EventActionPlanVO> userEventActionPlanVOs = UserCalendarService.this.saveUserPlannedEvents.getActionPlans();
					for(EventActionPlanVO userEventActionPlanVO : userEventActionPlanVOs){
						UserEventActionPlan userEventActionPlan = converDTO2UserEventActionPlan(userEventActionPlanVO);
						userEventActionPlan.setUserEvents(userEvent);
						userEventActionPlan=userEventActionPlanDAO.save(userEventActionPlan);
						userEventActionPlanVO.setEventActionPlanId(userEventActionPlan.getEventActionPlanId());
					}
				}catch (Exception e) {
					UserCalendarService.this.saveUserPlannedEvents.setExceptionEncountered(e);
					UserCalendarService.this.saveUserPlannedEvents.setResultCode(ResultCodeMapper.FAILURE);
					txStatus.setRollbackOnly();
				}
				return UserCalendarService.this.saveUserPlannedEvents;
			}
		});
		userPlannedEvents = saveUserPlannedEvents;
		saveUserPlannedEvents = null;
		return userPlannedEvents;
	}

	private UserEvents convertDTO2UserEvents(UserEventVO userPlannedEvents){
		UserEvents userEvents = new UserEvents();
		userEvents.setUserEventsId(userPlannedEvents.getUserEventsId());
		Registration user = registrationDAO.get(userPlannedEvents.getUserID());
		userEvents.setRegistration(user);
		userEvents.setTitle(userPlannedEvents.getTitle());
		userEvents.setDescription(userPlannedEvents.getDescription());
		userEvents.setLocationType(userPlannedEvents.getLocationType());
		userEvents.setLocationId(userPlannedEvents.getLocationId());
		//SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
		try{
			userEvents.setStartDate(userPlannedEvents.getStartDate());
			userEvents.setEndDate(userPlannedEvents.getEndDate());
		}catch(Exception e){
			
		}
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
		userEventVO.setTitle(userEvent.getTitle());
		//SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
		userEventVO.setStartDate(userEvent.getStartDate());
		userEventVO.setEndDate(userEvent.getEndDate());
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
			Collections.sort(eventActionPlanVOList);
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
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getCadresByRegionType(Long userID, String regionType, Long regionID){
		List<SelectOptionVO> results = new ArrayList<SelectOptionVO>();
		List<Cadre> cadres = null;
		if("STATE".equals(regionType)){
			cadres = cadreDAO.findCadresByState(regionID, userID);
		}else if("DISTRICT".equals(regionType)){
			cadres = cadreDAO.findCadresByDistrict(regionID, userID);
		}else if("CONSTITUENCY".equals(regionType)){
			cadres = cadreDAO.findCadresByConstituency(regionID, userID);
		}else if("MANDAL".equals(regionType)){
			cadres = cadreDAO.findCadresByMandal(regionID, userID);
		}else if("VILLAGE".equals(regionType)){
			cadres = cadreDAO.findCadresByVillage(regionID, userID);
		}
		for(Cadre cadre : cadres){
			SelectOptionVO obj = new SelectOptionVO();
			obj.setId(cadre.getCadreId());
			StringBuilder sb = new StringBuilder();
			sb.append(cadre.getFirstName()).append(IConstants.SPACE).append(cadre.getMiddleName()).append(IConstants.SPACE).append(cadre.getLastName());
			obj.setName(sb.toString());
			results.add(obj);
		}
		return results;
	}
	
	private ImportantDatesVO saveImportantDatesVO;
	
	public ImportantDatesVO saveUserImpDate(ImportantDatesVO importantDatesVO){
		this.saveImportantDatesVO = importantDatesVO;
		log.debug("UserCalendarService.saveUserImpDate()......");
		
		UserImpDate userImpDate = (UserImpDate)
			transactionTemplate.execute(new TransactionCallback() {

				public Object doInTransaction(TransactionStatus txStatus) {
					UserImpDate userImpDate = new UserImpDate();
					try{
						Registration user = registrationDAO.get(UserCalendarService.this.saveImportantDatesVO.getEventId());
						userImpDate.setUser(user);
						userImpDate.setTitle(UserCalendarService.this.saveImportantDatesVO.getTitle());
						userImpDate.setDescription(UserCalendarService.this.saveImportantDatesVO.getImportance());
						//SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
						log.debug("SDate::::::::"+UserCalendarService.this.saveImportantDatesVO.getStartDate());
						userImpDate.setEffectiveDate(UserCalendarService.this.saveImportantDatesVO.getStartDate());
						userImpDate.setTillDate(UserCalendarService.this.saveImportantDatesVO.getEndDate());
						userImpDate.setRecFreqType(UserCalendarService.this.saveImportantDatesVO.getFrequency());
						log.debug("EDate::::::::"+UserCalendarService.this.saveImportantDatesVO.getEndDate());
						

						log.debug("sDate::::::::"+userImpDate.getEffectiveDate());
						log.debug("eDate::::::::"+userImpDate.getTillDate());
						userImpDate = userImpDatesDAO.save(userImpDate);
						return userImpDate;
					}catch (Exception ex) {
						txStatus.setRollbackOnly();

						UserCalendarService.this.saveImportantDatesVO.setExceptionEncountered(ex);
					}
					return userImpDate;
				}
			
			
		});
		saveImportantDatesVO = null;
		importantDatesVO.setImportantDateId(userImpDate.getUserImpDateID());
		return importantDatesVO;
	}
	

	public UserEventVO getUserPlannedEvent(Long eventID){
		if(log.isDebugEnabled())
			log.debug("UserCalendar.getUserPlannedEvents() start");
		UserEvents userEvent = userEventsDAO.get(eventID);
		UserEventVO userEventVO = convertUserEvents2DTO(userEvent);
		return userEventVO;
	}
	
	public ImportantDatesVO getUserImpDate(Long impDateID, String dateType) {
		log.debug("UserCalenderService.getUserImpDate() Start...");
		ImportantDatesVO importantDatesVO = new ImportantDatesVO();
		
		if("Party".equalsIgnoreCase(dateType)){
			PartyImportantDates partyImportantDates = partyImportantDatesDAO.get(impDateID);
			
			if(partyImportantDates != null){
			
				List<ImportantDatesVO> importantDatesVOs =convertPartyImpDateModel2DTO(partyImportantDates);
				if(importantDatesVOs.size()>1)
					Collections.sort(importantDatesVOs);
				importantDatesVO = importantDatesVOs.get(0);
			}
		} else {
			UserImpDate userImpDate = userImpDatesDAO.get(impDateID);
			log.debug("UserCalenderService.getUserImpDate() userImpDates.size()"+userImpDate);
			if(userImpDate != null){
				List<ImportantDatesVO> userImpDateVOs = convertUserImpDateModel2DTO(userImpDate);
				log.debug("User Imp Dates Recurssive Type size::"+ userImpDateVOs.size());
				if(userImpDateVOs.size()>1)
					Collections.sort(userImpDateVOs);
				importantDatesVO = userImpDateVOs.get(0);
			}
		}
		
		return importantDatesVO;
	}
}
