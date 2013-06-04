package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IUserCalendarService;

/**
 * 
 * @author Narender Akula
 *
 */
public class UserCalendarService implements IUserCalendarService {/*

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
	private IPartyImportantDatesDAO partyImportantDatesDAO;
	private IUserImpDatesDAO userImpDatesDAO;
	private TransactionTemplate transactionTemplate;
	private IUserDAO userDAO;
    private ICandidateDetailsService candidateDetailsService;
	private final static Logger log = Logger.getLogger(UserCalendarService.class);
	
	
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
	
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}


	private Long userID;
	private String partySubscribeImpDates;
	public void userSubscribePartyImpDates(Long userID, String partySubscribeImpDates){
		this.userID = userID;
		this.partySubscribeImpDates = partySubscribeImpDates;
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus txStatus) {
				try{
					User user = userDAO.get(UserCalendarService.this.userID);
					user.setIncludePartyImpDateStatus(UserCalendarService.this.partySubscribeImpDates);
					user = userDAO.save(user);
					
				}catch(Exception ex){
					
				}
			}
			}
		);
	}
	
	public List<ImportantDatesVO> getUserImpDates(RegistrationVO user, Calendar inputDate) {
		log.debug("UserCalenderService.getUserImpDates() Start...");
		log.debug("input Date Year123 ::"+inputDate.get(1)+"input Date Month123::"+inputDate.get(2)+"input Date Date123::"+inputDate.get(5));
		
		Long userID = user.getRegistrationID();
		Long partyId = user.getParty();
		List<ImportantDatesVO> importantDates = new ArrayList<ImportantDatesVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN); 
		if("ALL".equals(user.getSubscribePartyImpDate())){
			List<PartyImportantDates> partyImportantDates = partyImportantDatesDAO.findByPartyId(partyId);
			
			if(partyImportantDates != null){
			
				for(PartyImportantDates impDate:partyImportantDates ){
					List<ImportantDatesVO> importantDatesVOs =convertPartyImpDateModel2DTO(impDate, inputDate);
					for(ImportantDatesVO importantDatesVO : importantDatesVOs){
						importantDates.add(importantDatesVO);
					}
				}
			
			}
		}
		List<UserImpDate> userImpDates = userImpDatesDAO.findByUserId(userID, inputDate);
		log.debug("UserCalenderService.getUserImpDates() userImpDates.size()"+userImpDates.size());
		if(userImpDates != null){
			for(UserImpDate userImpDate : userImpDates){				
				List<ImportantDatesVO> userImpDateVOs = convertUserImpDateModel2DTO(userImpDate,inputDate);
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

	public List<ImportantDatesVO> getUserTodaysImportantEvents(RegistrationVO user) {
	
		List<ImportantDatesVO> importantDates = new ArrayList<ImportantDatesVO>(0);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN); 
		if("ALL".equals(user.getSubscribePartyImpDate())){
			List<PartyImportantDates> partyImportantDates = partyImportantDatesDAO.findTodaysPartyImportantDates(user.getParty());
			
			if(partyImportantDates != null){
				for(PartyImportantDates impDate:partyImportantDates ){
					ImportantDatesVO importantDateVO = createImportantDatesVOForParty(calendar, impDate);
					importantDates.add(importantDateVO);
				}
			
			}
		}
		List<UserImpDate> userImpDates = userImpDatesDAO.findTodayImportantEvents(user.getRegistrationID());
		log.debug("UserCalenderService.getUserImpDates() userImpDates.size()"+userImpDates.size());
		if(userImpDates != null){
			for(UserImpDate userImpDate : userImpDates){				
				ImportantDatesVO userImpDateVO = createImportantDatesVOForUser(calendar, userImpDate);
				log.debug("User Today's Imp Dates title::"+ userImpDateVO.getTitle());
					importantDates.add(userImpDateVO);
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
		importantDatesVO.setEventId(impDate.getUserId());  
		importantDatesVO.setTitle(impDate.getTitle());
		importantDatesVO.setImportance(impDate.getDescription());
		importantDatesVO.setStartDate(impDate.getEffectiveDate());
		importantDatesVO.setEndDate(impDate.getTillDate());
		importantDatesVO.setFrequency(impDate.getRecFreqType());
		
		Date iDate=(Date)(impDate.getEffectiveDate()).clone();
		iDate.setYear(calendar.get(Calendar.YEAR) - 1900);
		iDate.setMonth(calendar.get(Calendar.MONTH));
		iDate.setDate(calendar.get(Calendar.DAY_OF_MONTH));
		importantDatesVO.setImpDate(iDate);

		//importantDatesVO.setImpDate(calendar.getTime());
		
		return importantDatesVO;
	}
	private List<ImportantDatesVO> convertUserImpDateModel2DTO(UserImpDate impDate, Calendar inputDate){
		log.debug("input Date Year ::"+inputDate.get(1)+"input Date Month::"+inputDate.get(2)+"input Date Date::"+inputDate.get(5));
		List<ImportantDatesVO> importantDatesVOs = new ArrayList<ImportantDatesVO>();
		Calendar startCalendar = (Calendar) inputDate.clone();
		int dayOfWeek1 = startCalendar.get(Calendar.DAY_OF_WEEK);
		
		Calendar endCalendar = (Calendar) startCalendar.clone();
		endCalendar.add(Calendar.DATE, 60);
		
		Date startDate = impDate.getEffectiveDate();		
		int sMonth = startDate.getMonth();
		int sDay = startDate.getDate();

		Calendar dbStartDate = Calendar.getInstance(); 
		dbStartDate.setTime(startDate);
		dbStartDate.add(Calendar.DAY_OF_MONTH, -1);
		
		Date endDate = impDate.getTillDate();		
		int eMonth = startDate.getMonth();
		int eDay = startDate.getDate();
		Calendar dbEndDate = Calendar.getInstance(); 
		dbEndDate.setTime(endDate);
		dbEndDate.add(Calendar.DAY_OF_MONTH, 1);
		Calendar calendar = (Calendar) inputDate.clone();
		int currentYear =calendar.get(Calendar.YEAR);
		int currentMonth =calendar.get(Calendar.MONTH);
		int currentDay =calendar.get(Calendar.DAY_OF_MONTH);
		log.debug("Imp Recursive Date Type:::"+impDate.getRecFreqType());
		if("YEARLY".equalsIgnoreCase(impDate.getRecFreqType())){
			if(calendar.get(Calendar.MONTH)==11 && sMonth <=1)
				++currentYear;
			calendar.set(currentYear,sMonth,sDay);
			if((calendar.after(startCalendar) || calendar.equals(startCalendar)) &&  calendar.before(endCalendar)
					&& calendar.after(dbStartDate) &&  calendar.before(dbEndDate)){				
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
			
			//months[0]=startCalendar.get(Calendar.MONTH);
			//months[1]=endCalendar.get(Calendar.MONTH);
			log.debug("updated code Date 1st March 2010"); 
			log.debug("inputDate Year ::"+inputDate.get(1)+" inputDate Month::"+inputDate.get(2)+" inputDate Date::"+inputDate.get(5));
			log.debug("startCalendar Year ::"+startCalendar.get(1)+" startCalendar Month::"+startCalendar.get(2)+" startCalendar Date::"+startCalendar.get(5));
			log.debug("endCalendar Year ::"+endCalendar.get(1)+" endCalendar Month::"+endCalendar.get(2)+" startCalendar Date::"+endCalendar.get(5)); 
			Calendar monthlyCal1 = (Calendar) startCalendar.clone(); monthlyCal1.set(5, sDay);monthlyCal1.set(Calendar.MONTH, months[0]);
			Calendar monthlyCal2 = (Calendar) monthlyCal1.clone(); 
			monthlyCal2 = (Calendar) monthlyCal2.clone(); 

			monthlyCal2.add(Calendar.MONTH, 1);
			
			dbEndDate.add(Calendar.DAY_OF_MONTH, 1);  
			if((monthlyCal1.after(startCalendar) || monthlyCal1.equals(startCalendar)) && (monthlyCal1.before(endCalendar)) && monthlyCal1.before(dbEndDate) && monthlyCal1.after(dbStartDate) ){
				ImportantDatesVO importantDatesVO = createImportantDatesVOForUser(monthlyCal1,impDate);
				importantDatesVOs.add(importantDatesVO);
			}
			else if((monthlyCal2.after(startCalendar)) && (monthlyCal2.before(endCalendar)) && monthlyCal2.before(dbEndDate) && monthlyCal2.after(dbStartDate)  ){
				ImportantDatesVO importantDatesVO = createImportantDatesVOForUser(monthlyCal2,impDate);
				importantDatesVOs.add(importantDatesVO);
			}
		} else if("WEEKLY".equalsIgnoreCase(impDate.getRecFreqType())){
			//sun -1, mon-2, tue - 3,.... sat-7
			calendar.setTime(startDate);
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			
			Calendar calCalendar = (Calendar) inputDate.clone();
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
				Calendar cal = (Calendar) inputDate.clone();
				cal.set(currentYear,currentMonth,calDay);
				if(cal.before(startCalendar) || cal.after(endCalendar)
						|| cal.before(dbStartDate) || cal.after(dbEndDate)){
					continue;
				}
				ImportantDatesVO importantDatesVO = createImportantDatesVOForUser(cal,impDate);
				importantDatesVOs.add(importantDatesVO);				
			}
		}
		else{
			ImportantDatesVO importantDatesVO;
			calendar.setTime(startDate);
			
			if(!(calendar.before(startCalendar)) && (!calendar.after(endCalendar))){				
				importantDatesVO = createImportantDatesVOForUser(calendar,impDate);			 
				importantDatesVOs.add(importantDatesVO);
			}else{
				importantDatesVO = createImportantDatesVOForUser(calendar,impDate);	
				importantDatesVOs.add(importantDatesVO);
			}
		}
		return importantDatesVOs;
	}
	private List<ImportantDatesVO> convertPartyImpDateModel2DTO(PartyImportantDates impDate,Calendar inputDate){

		List<ImportantDatesVO> importantDatesVOs = new ArrayList<ImportantDatesVO>();
		//SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
		Calendar startCalendar = (Calendar) inputDate.clone();
		//int year1 = startCalendar.get(Calendar.YEAR);
		//int month1 = startCalendar.get(Calendar.MONTH);
		//int day1 = startCalendar.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek1 = startCalendar.get(Calendar.DAY_OF_WEEK);
		
		Calendar endCalendar = (Calendar) inputDate.clone();
		endCalendar.add(Calendar.DATE, 60);
		//int year2 = endCalendar.get(Calendar.YEAR);
		//int month2 = endCalendar.get(Calendar.MONTH);
		//int day2 = endCalendar.get(Calendar.DAY_OF_MONTH);
		//int dayOfWeek2 = endCalendar.get(Calendar.DAY_OF_WEEK);
		
		Date startDate = impDate.getImportantDate();		
		int month = startDate.getMonth();
		int day = startDate.getDate();
		
		Calendar calendar = (Calendar) inputDate.clone();
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
				Calendar currentCalendar = (Calendar) inputDate.clone();
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
			
			Calendar calCalendar = (Calendar) inputDate.clone();
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
		} else{
			calendar.setTime(startDate);
			if(!(calendar.before(startCalendar)) && (!calendar.after(endCalendar))){				
				ImportantDatesVO importantDatesVO = createImportantDatesVOForParty(calendar,impDate);			 
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
		importantDatesVO.setFrequency(impDate.getRecursiveFrequency());
		Date iDate = (Date)(impDate.getImportantDate()).clone();
		iDate.setYear(calendar.get(Calendar.YEAR)-1900);
		iDate.setMonth(calendar.get(Calendar.MONTH));
		iDate.setDate(calendar.get(Calendar.DAY_OF_MONTH));
		importantDatesVO.setImpDate(iDate);	
		importantDatesVO.setStartDate(impDate.getImportantDate());
		return importantDatesVO;
	}

	public List<UserEventVO> getUserPlannedEvents(Long userID, Calendar inputDate) {
		if(log.isDebugEnabled())
			log.debug("UserCalendar.getUserPlannedEvents() start");
		
		Calendar startCalendar = (Calendar)inputDate.clone();
		
		Calendar endCalendar = (Calendar)inputDate.clone();
		endCalendar.add(Calendar.DATE, 60);
		
		List<UserEvents> userEventsList = userEventsDAO.findEventsByUserId(userID);
		List<UserEventVO> userEventVOList = new ArrayList<UserEventVO>();
		for(UserEvents userEvent : userEventsList){
			Date startDate= userEvent.getStartDate();
			Calendar dbCalendar = (Calendar)inputDate.clone();
			dbCalendar.setTime(startDate);
			if(dbCalendar.before(startCalendar) || dbCalendar.after(endCalendar))
				continue;
			UserEventVO userEventVO = convertUserEvents2DTO(userEvent);
			userEventVOList.add(userEventVO);
		}
		Collections.sort(userEventVOList);
		return userEventVOList;
	}
	
	// Method to get today's user events for index page
	public List<UserEventVO> getTodaysUserPlannedEvents(Long userID) {
			
		SimpleDateFormat frmat = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = frmat.format(new Date());
		
		List<UserEvents> userEventsList = null;
		try {
			userEventsList = userEventsDAO.findEventsByUserIdAndStartDate(userID, frmat.parse(startDate));
		} catch (ParseException e) {
			log.error("Error while parsing date:" + e.getMessage());
		}
		frmat = new SimpleDateFormat("HH:mm a");
		List<UserEventVO> userEventVOList = new ArrayList<UserEventVO>();
		//System.out.print("locationIdList: " + userEventsList.get(0).getLocationId());
		for(UserEvents userEvent : userEventsList){ 
			log.debug(userEvent.getLocationId());
			System.out.print("locationId: " + userEvent.getLocationId());
			UserEventVO userEventVO = convertUserEvents2DTO(userEvent);
			System.out.print("locationIdVO: " + userEventVO.getLocationId());
			String location = getEventLocation(userEvent.getLocationType(), userEvent.getLocationId());
			String eventDisplayTitle = userEvent.getTitle() + "  @  " + frmat.format(userEvent.getStartDate()) + "   at  " + location;
			userEventVO.setEventDisplayTitle(eventDisplayTitle);
			userEventVO.setLocation(location);
			userEventVOList.add(userEventVO);
		}
		Collections.sort(userEventVOList);
		return userEventVOList;
	}

	private String getEventLocation(String locationType, Long locationId) {
		if(IConstants.MANDAL.equals(locationType)) {
			return tehsilDAO.get(locationId).getTehsilName();
		} else if (IConstants.DISTRICT_LEVEL.equals(locationType)) {
			return districtDAO.get(locationId).getDistrictName();
		} else if(IConstants.STATE_LEVEL.equals(locationType)) {
			return stateDAO.get(locationId).getStateName();
		} else if(IConstants.CONSTITUENCY_LEVEL.equals(locationType)) {
			return constituencyDAO.get(locationId).getName();
		} else if(IConstants.HAMLET_LEVEL.equals(locationType)) {
			return hamletDAO.get(locationId).getHamletName();
		} else if(IConstants.CENSUS_VILLAGE_LEVEL.equals(locationType)) {
			return townshipDAO.get(locationId).getTownshipName();
		} 
		return "";
	}

	public void deleteUserPlannedEvents(Long userEventID) {
		UserEvents event = userEventsDAO.get(userEventID);
		event.setIsDeleted("YES");
		userEventsDAO.save(event);
	}

	public void deleteUserImpDate(Long impDateID) {
		UserImpDate impDate = userImpDatesDAO.get(impDateID);
		impDate.setIsDeleted("YES");
		userImpDatesDAO.save(impDate);
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
					if(userEventActionPlanVOs != null){
						if(userEventActionPlanVOs.size() > 0){
							List<Long> eventActionPlanIds = new ArrayList<Long>();
							for(EventActionPlanVO userEventActionPlanVO : userEventActionPlanVOs){
								UserEventActionPlan userEventActionPlan = converDTO2UserEventActionPlan(userEventActionPlanVO);
								userEventActionPlan.setUserEvents(userEvent);
								userEventActionPlan=userEventActionPlanDAO.save(userEventActionPlan);
								eventActionPlanIds.add(userEventActionPlan.getEventActionPlanId());
								userEventActionPlanVO.setEventActionPlanId(userEventActionPlan.getEventActionPlanId());
							}
							List<Long> eventActPlanIds = userEventActionPlanDAO.getEventActionPlanIds(userEvent.getUserEventsId(),eventActionPlanIds);
							removeOrganisers(eventActPlanIds);
							
					
						}else{
							List<Long> eventActPlanIds = userEventActionPlanDAO.getEventActionPlanIds(userEvent.getUserEventsId());
							removeOrganisers(eventActPlanIds);
							
						}
					}else{
						List<Long> eventActPlanIds = userEventActionPlanDAO.getEventActionPlanIds(userEvent.getUserEventsId());
						removeOrganisers(eventActPlanIds);
					
						
					}
				}catch (Exception e) {
					UserCalendarService.this.saveUserPlannedEvents.setExceptionEncountered(e);
					UserCalendarService.this.saveUserPlannedEvents.setResultCode(ResultCodeMapper.FAILURE);
					txStatus.setRollbackOnly();
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				return UserCalendarService.this.saveUserPlannedEvents;
			}
		});
		userPlannedEvents = saveUserPlannedEvents;
		saveUserPlannedEvents = null;

		try{
			if(userPlannedEvents.getUserEventsId()!= null && userPlannedEvents.getUserEventsId().longValue() > 0l){
				List<EventActionPlanVO> userEventActionPlanVOs = userPlannedEvents.getActionPlans();
				if(!userEventActionPlanVOs.isEmpty()){
					List<Long> eventActionPlanIds = new ArrayList<Long>();
					eventActionPlanIds.add(0l);
					for(EventActionPlanVO userEventActionPlanVO : userEventActionPlanVOs){
						eventActionPlanIds.add(userEventActionPlanVO.getEventActionPlanId());
					}
					userEventActionPlanDAO.removeDeletedEventActionPlans(userPlannedEvents.getUserEventsId(),eventActionPlanIds);
				}else{
					userEventActionPlanDAO.removeEventActionPlans(userPlannedEvents.getUserEventsId());
				}
			}
		
		}catch (Exception e) {
			log.error(e);
		}
		
		
		
		
		return userPlannedEvents;
	}
    private void removeOrganisers(List<Long> eventActPlanIds){
    	try{
    		if(!eventActPlanIds.isEmpty()){	
				for(Long id:eventActPlanIds){
					UserEventActionPlan userEventActionPlan = userEventActionPlanDAO.get(id);
					userEventActionPlan.setOrganizers(null);
					userEventActionPlanDAO.save(userEventActionPlan);
				}
			}
    	}catch(Exception e){
    		log.error(e);
    	}
    }
	private UserEvents convertDTO2UserEvents(UserEventVO userPlannedEvents){
		UserEvents userEvents = new UserEvents();
		userEvents.setUserEventsId(userPlannedEvents.getUserEventsId());
		User user = userDAO.get(userPlannedEvents.getUserID());
		userEvents.setUser(user);
		userEvents.setTitle(userPlannedEvents.getTitle());
		userEvents.setDescription(userPlannedEvents.getDescription());
		userEvents.setLocationType(userPlannedEvents.getLocationType());
		userEvents.setLocationId(userPlannedEvents.getLocationId());
		userEvents.setIsDeleted(userPlannedEvents.getIsDeleted());
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
		userEventVO.setUserID(userEvent.getUser().getUserId());
		try{
			Long id = candidateDetailsService.getLocationScopeValue(getLocationId(userEvent.getLocationType()),userEvent.getLocationId().toString());
		  userEventVO.setLocation(candidateDetailsService.getLocationDetails(getLocationId(userEvent.getLocationType()),id));
		}catch(Exception e){
			
		}
		userEventVO.setDescription(userEvent.getDescription());
		userEventVO.setLocationId(userEvent.getLocationId());
		userEventVO.setLocationType(userEvent.getLocationType());
		userEventVO.setTitle(userEvent.getTitle());
		//SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
		log.debug("In service start date: " + userEvent.getStartDate());
		userEventVO.setStartDate(userEvent.getStartDate());
		log.debug("In service end date: " + userEvent.getEndDate());
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
			cadres = cadreDAO.findCadresByVillage(regionID, userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
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
	
	public List<ImportantDatesVO> saveUserImpDate(ImportantDatesVO importantDatesVO){
		this.saveImportantDatesVO = importantDatesVO;
		log.debug("UserCalendarService.saveUserImpDate()......");
		
		UserImpDate userImpDate = (UserImpDate)transactionTemplate.execute(new TransactionCallback() {

				public Object doInTransaction(TransactionStatus txStatus) {
					UserImpDate userImpDate = new UserImpDate();
					try{
						userImpDate.setUserId(UserCalendarService.this.saveImportantDatesVO.getEventId());
						userImpDate.setUserImpDateID(UserCalendarService.this.saveImportantDatesVO.getImportantDateId());
						userImpDate.setTitle(UserCalendarService.this.saveImportantDatesVO.getTitle());
						userImpDate.setDescription(UserCalendarService.this.saveImportantDatesVO.getImportance());
						//SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
						log.debug("SDate::::::::"+UserCalendarService.this.saveImportantDatesVO.getStartDate());
						userImpDate.setEffectiveDate(UserCalendarService.this.saveImportantDatesVO.getStartDate());
						userImpDate.setTillDate(UserCalendarService.this.saveImportantDatesVO.getEndDate());
						userImpDate.setRecFreqType(UserCalendarService.this.saveImportantDatesVO.getFrequency());
						userImpDate.setIsDeleted(UserCalendarService.this.saveImportantDatesVO.getIsDeleted());
						log.debug("EDate::::::::"+UserCalendarService.this.saveImportantDatesVO.getEndDate());
						

						log.debug("sDate::::::::"+userImpDate.getEffectiveDate());
						log.debug("eDate::::::::"+userImpDate.getTillDate());
						userImpDate = userImpDatesDAO.save(userImpDate);
						return userImpDate;
					}catch (Exception ex) {
						txStatus.setRollbackOnly();
						ex.printStackTrace();

						UserCalendarService.this.saveImportantDatesVO.setExceptionEncountered(ex);
					}
					return userImpDate;
				}
			
			
		});
		saveImportantDatesVO = null;
		List<ImportantDatesVO> importantDatesVOs = convertUserImpDateModel2DTO(userImpDate,Calendar.getInstance());
		//importantDatesVO.setImportantDateId(userImpDate.getUserImpDateID());
		return importantDatesVOs;
	}
	

	public UserEventVO getUserPlannedEvent(Long eventID,Long userId){
		if(log.isDebugEnabled())
			log.debug("UserCalendar.getUserPlannedEvents() start");
		UserEventVO userEventVO = new UserEventVO();
		List<Long> count = userEventsDAO.checkEventBelongsToUser(userId,eventID);
		if(!count.isEmpty() && count.get(0).longValue() > 0){
		   UserEvents userEvent = userEventsDAO.get(eventID);
		    userEventVO = convertUserEvents2DTO(userEvent);
		    userEventVO.setValid(true);
		}
		return userEventVO;
	}
	
	public List<ImportantDatesVO> getUserImpDate(Long impDateID, String dateType, Calendar calendar) {
		log.debug("UserCalenderService.getUserImpDate() Start...");
		//ImportantDatesVO importantDatesVO = new ImportantDatesVO();
		List<ImportantDatesVO> importantDatesVOs = new ArrayList<ImportantDatesVO>();
		if("Party".equalsIgnoreCase(dateType)){
			PartyImportantDates partyImportantDates = partyImportantDatesDAO.get(impDateID);
			
			if(partyImportantDates != null){
			
				importantDatesVOs =convertPartyImpDateModel2DTO(partyImportantDates,calendar);
				if(importantDatesVOs.size()>1)
					Collections.sort(importantDatesVOs);
				//importantDatesVO = importantDatesVOs.get(0);
			}
		} else {
			UserImpDate userImpDate = userImpDatesDAO.get(impDateID);
			log.debug("UserCalenderService.getUserImpDate() userImpDates.size()"+userImpDate);
			if(userImpDate != null){
				importantDatesVOs = convertUserImpDateModel2DTO(userImpDate,calendar);
				log.debug("User Imp Dates Recurssive Type size::"+ importantDatesVOs.size());
				if(importantDatesVOs.size()>1)
					Collections.sort(importantDatesVOs);
				//importantDatesVO = userImpDateVOs.get(0);
			}
		}
		
		return importantDatesVOs;
	}
	
	public CadreManagementVO getUserImpDateAndEvent(RegistrationVO user, Calendar calendar) {
		log.debug("UserCalendarService.getUserImpDataAndEvent()::::started");
		CadreManagementVO cadreManagementVO = new CadreManagementVO();

		Long userID = user.getRegistrationID();
		try{
			List<UserEventVO> userPlannedEvents =getUserPlannedEvents(userID,calendar);
			cadreManagementVO.setUserEvents(userPlannedEvents);
			List<ImportantDatesVO> userImpDatesList = getUserImpDates(user,calendar);
			cadreManagementVO.setUserImpDates(userImpDatesList);
		}catch (Exception exceptionEncountered) {
			cadreManagementVO.setExceptionEncountered(exceptionEncountered);
			
		}
		return cadreManagementVO;
	}
    private Long getLocationId(String locationType){
    	   if(locationType != null && locationType.trim().toUpperCase().equals("STATE"))
		    return 2l;
		   else if(locationType != null && locationType.trim().toUpperCase().equals("DISTRICT"))
		    return 3l;
		   else if(locationType != null && locationType.trim().toUpperCase().equals("ASSEMBLY CONSTITUENCY"))
		    return 4l;
		   else if(locationType != null && locationType.trim().toUpperCase().equals("PARLIAMENT CONSTITUENCY"))
			return 4l;
		   else if(locationType != null && locationType.trim().toUpperCase().equals("MANDAL/TEHSIL"))
		    return 5l;
		   else if(locationType != null && locationType.trim().toUpperCase().equals("VILLAGE"))
		    return 6l;
		   else if(locationType != null && locationType.trim().toUpperCase().equals("MUNICIPAL-CORP-GMC"))
		    return 7l;
		   else if(locationType != null && locationType.trim().toUpperCase().equals("WARD"))
		    return 8l;
		   else if(locationType != null && locationType.trim().toUpperCase().equals("BOOTH"))
		    return 9l;
    	return 0l;
    }
*/}
