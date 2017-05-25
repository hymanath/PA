package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICadreMahanaduVisitDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreMahanaduVisitInfoDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEntryExitInfoDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.MahanaduVisitVO;
import com.itgrids.partyanalyst.model.CadreMahanaduVisitDetails;
import com.itgrids.partyanalyst.model.CadreMahanaduVisitInfo;
import com.itgrids.partyanalyst.model.EntryExitInfo;
import com.itgrids.partyanalyst.service.ICadreDetailsUtils;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class MahanaduDashBoardService implements IMahanaduDashBoardService {

	private IEventAttendeeDAO eventAttendeeDAO;
	private ICadreMahanaduVisitDetailsDAO cadreMahanaduVisitDetailsDAO;
	private ICadreMahanaduVisitInfoDAO cadreMahanaduVisitInfoDAO;
	private TransactionTemplate transactionTemplate;
	private IEntryExitInfoDAO entryExitInfoDAO;
	private IEventDAO	eventDAO;
	private IDistrictDAO districtDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	private IConstituencyDAO constituencyDAO;
	private IEventInviteeDAO eventInviteeDAO;
	private ITdpCadreCandidateDAO tdpCadreCandidateDAO;
	private ICadreDetailsUtils cadreDetailsUtils;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IStateDAO stateDAO;
	
	private static final Logger LOG = Logger.getLogger(MahanaduDashBoardService.class);
	private DateUtilService dateUtilService = new DateUtilService();
	
	
	
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public ICadreDetailsUtils getCadreDetailsUtils() {
		return cadreDetailsUtils;
	}

	public void setCadreDetailsUtils(ICadreDetailsUtils cadreDetailsUtils) {
		this.cadreDetailsUtils = cadreDetailsUtils;
	}

	public void setEventAttendeeDAO(IEventAttendeeDAO eventAttendeeDAO) {
		this.eventAttendeeDAO = eventAttendeeDAO;
	}

	public void setCadreMahanaduVisitDetailsDAO(
			ICadreMahanaduVisitDetailsDAO cadreMahanaduVisitDetailsDAO) {
		this.cadreMahanaduVisitDetailsDAO = cadreMahanaduVisitDetailsDAO;
	}

	public void setCadreMahanaduVisitInfoDAO(
			ICadreMahanaduVisitInfoDAO cadreMahanaduVisitInfoDAO) {
		this.cadreMahanaduVisitInfoDAO = cadreMahanaduVisitInfoDAO;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setEntryExitInfoDAO(IEntryExitInfoDAO entryExitInfoDAO) {
		this.entryExitInfoDAO = entryExitInfoDAO;
	}

	
	public void setEventDAO(IEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public IEventInviteeDAO getEventInviteeDAO() {
		return eventInviteeDAO;
	}

	public void setEventInviteeDAO(IEventInviteeDAO eventInviteeDAO) {
		this.eventInviteeDAO = eventInviteeDAO;
	}
	
	public ITdpCadreCandidateDAO getTdpCadreCandidateDAO() {
		return tdpCadreCandidateDAO;
	}

	public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
		this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
	}

	public void getTodayTotalVisitorsForWeb(){
		 try{
		  EntryExitInfo entryExitInfo = entryExitInfoDAO.getAll().get(0);
    	  getTodayTotalVisitors(entryExitInfo.getEntryId(),entryExitInfo.getExitId(),entryExitInfo.getParentEventId());
		 }catch(Exception e){
			 LOG.error("Exception rised in getTodayTotalVisitorsForWeb()",e);
		 }
	}
	
    public void getTodayTotalVisitorsForSchedular(){
    	
    	if(!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver")){
			return;
		}
    	
    	try{
    	EntryExitInfo entryExitInfo = entryExitInfoDAO.getAll().get(1);
    	if(entryExitInfo.getServiceRunReq().equalsIgnoreCase("true")){
    	  getTodayTotalVisitors(entryExitInfo.getEntryId(),entryExitInfo.getExitId(),entryExitInfo.getParentEventId());
    	}
      }catch(Exception e){
		 LOG.error("Exception rised in getTodayTotalVisitorsForSchedular()",e);
      }
    	
	}

	public void getTodayTotalVisitors(Long entryEventId,Long exitEventId,final Long parentEventId){
		//Long totalVisitors = 0l;
		//Long currentVisitors = 0l;
		
	try{	
		Date today = dateUtilService.getCurrentDateAndTime();
		
		//0eventId, 1attendedTime, 2tdpCadreId,3invitee tdpCadreId
		List<Object[]> cadreEventList = eventAttendeeDAO.getCadreVisitInfo(today, entryEventId, exitEventId);
		
		final Date todayDate = dateUtilService.getCurrentDateAndTime();
		
		long hours = 0;
		long minutes = 0;
		long seconds = 0;
		boolean invite = false;
		
		Long above8hrs =0l;
		Long seventoeight =0l;
		Long sixtoseven =0l;
		Long fivetosix =0l;
		Long fourtofive =0l;
		Long threetofour =0l;
		Long twotothree =0l;
		Long onetotwo =0l;
		Long halfanhour =0l;
		Long belowhalfanhour = 0l;
		
		
		Long above8hrsInv =0l;
		Long seventoeightInv =0l;
		Long sixtosevenInv =0l;
		Long fivetosixInv =0l;
		Long fourtofiveInv =0l;
		Long threetofourInv =0l;
		Long twotothreeInv =0l;
		Long onetotwoInv =0l;
		Long halfanhourInv =0l;
		Long belowhalfanhourInv = 0l;
		
		
		final int ONE_DAY = 1000 * 60 * 60 * 24;
	    final int ONE_HOUR = ONE_DAY / 24;
	    final int ONE_MINUTE = ONE_HOUR / 60;
	    final int ONE_SECOND = ONE_MINUTE / 60;
	    int totalRecordsSize = cadreEventList.size();
	       
	    if(cadreEventList.size() > 0){
	    	Long currentCadreId = null;
	    	boolean isCalc = false;
			for(int i=0;i<totalRecordsSize;i++){
				isCalc = false;
				Date startTime = null;
				Date endTime = null;
					//first time
					if(currentCadreId == null){
						currentCadreId = (Long.valueOf(cadreEventList.get(i)[2].toString())).longValue();
						if(cadreEventList.get(i)[3] != null){
						    invite = true;
						}
					}
					//if current record not belongs to previous record user then updating time wise count.
					if(currentCadreId.longValue() != (Long.valueOf(cadreEventList.get(i)[2].toString())).longValue()){
						if(hours >= 8){
							above8hrs=above8hrs+1;
							if(invite){
							  above8hrsInv=above8hrsInv+1;
							}
						}else if(hours >= 7 && hours < 8){
							seventoeight=seventoeight+1;
							if(invite){
							  seventoeightInv=seventoeightInv+1;
							}
						}else if(hours >= 6 && hours < 7){
							sixtoseven=sixtoseven+1;
							if(invite){
							  sixtosevenInv=sixtosevenInv+1;
							}
						}else if(hours >= 5 && hours < 6){
							fivetosix=fivetosix+1;
							if(invite){
							  fivetosixInv=fivetosixInv+1;
							}
						}else if(hours >= 4 && hours < 5){
							fourtofive=fourtofive+1;
							if(invite){
							  fourtofiveInv=fourtofiveInv+1;
							}
						}else if(hours >= 3 && hours < 4){
							threetofour=threetofour+1;
							if(invite){
							  threetofourInv=threetofourInv+1;
							}
						}else if(hours >= 2 && hours < 3){
							twotothree=twotothree+1;
							if(invite){
							  twotothreeInv=twotothreeInv+1;
							}
						}else if(hours >= 1 && hours < 2){
							onetotwo=onetotwo+1;
							if(invite){
							  onetotwoInv=onetotwoInv+1;
							}
						}else{
							if(minutes >= 30){
								halfanhour=halfanhour+1;
								if(invite){
								  halfanhourInv=halfanhourInv+1;
								}
							}else{
								belowhalfanhour=belowhalfanhour+1;
								if(invite){
								  belowhalfanhourInv=belowhalfanhourInv+1;
								}
							}
						}
						 hours = 0;
						 minutes = 0;
						 seconds = 0;
						 invite = false;
						 //changing current user
						 currentCadreId = (Long.valueOf(cadreEventList.get(i)[2].toString())).longValue();
						 if(cadreEventList.get(i)[3] != null){
							    invite = true;
							}
					}
					
					int nextRecordIndex = i+1;
					//checking middle record or not
					if(nextRecordIndex <= (totalRecordsSize-1)){
						//if next record belongs to user
						if(currentCadreId.longValue() == (Long.valueOf(cadreEventList.get(nextRecordIndex)[2].toString())).longValue()){
							if((Long.valueOf(cadreEventList.get(i)[0].toString())).longValue() == entryEventId.longValue() && 
									  (Long.valueOf(cadreEventList.get(nextRecordIndex)[0].toString())).longValue() == exitEventId.longValue()){
							  startTime = (Date)cadreEventList.get(i)[1];
							  endTime = (Date)cadreEventList.get(nextRecordIndex)[1];
							}
						}else{//next record belongs to other user
							//if record is exit
							if((Long.valueOf(cadreEventList.get(i)[0].toString())).longValue() == exitEventId.longValue()){
								continue;
							}else{//considering current time as exit
								startTime = (Date)cadreEventList.get(i)[1];
								endTime = todayDate;
							}
						}
					}else{//if last record
						//if last record is exit
						if((Long.valueOf(cadreEventList.get(i)[0].toString())).longValue() == exitEventId.longValue()){
							continue;
						}else{//considering current time as exit
							startTime = (Date)cadreEventList.get(i)[1];
							endTime = todayDate;
						}
					}
					
					if(startTime!=null && endTime!=null ){
						isCalc = true;
						Calendar cal = Calendar.getInstance();
				        cal.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
				        cal.setTime(startTime);
				        long t1 = cal.getTimeInMillis();
				        cal.setTime(endTime);
					    
				        long diff = Math.abs(cal.getTimeInMillis() - t1);
				        
		
				        long d = diff / ONE_DAY;
				        diff %= ONE_DAY;
		
				        long h = diff / ONE_HOUR;
				        diff %= ONE_HOUR;
		
				        long m = diff / ONE_MINUTE;
				        diff %= ONE_MINUTE;
		
				        long s = diff / ONE_SECOND;
				        
				        hours=hours+h;
				        minutes=minutes+m;
				        seconds=seconds+s;
					}
					if(seconds > 59){
						minutes = minutes+seconds/60;
						seconds=seconds%60;
					}
					if(minutes > 59){
						hours = hours+minutes/60;
						minutes=minutes%60;
					}
			}
			if(isCalc){
				if(hours >= 8){
					above8hrs=above8hrs+1;
				}else if(hours >= 7 && hours < 8){
					seventoeight=seventoeight+1;
				}else if(hours >= 6 && hours < 7){
					sixtoseven=sixtoseven+1;
				}else if(hours >= 5 && hours < 6){
					fivetosix=fivetosix+1;
				}else if(hours >= 4 && hours < 5){
					fourtofive=fourtofive+1;
				}else if(hours >= 3 && hours < 4){
					threetofour=threetofour+1;
				}else if(hours >= 2 && hours < 3){
					twotothree=twotothree+1;
				}else if(hours >= 1 && hours < 2){
					onetotwo=onetotwo+1;
				}else{
					if(minutes >= 30){
						halfanhour=halfanhour+1;
					}else{
						belowhalfanhour=belowhalfanhour+1;
					}
				}
			}
	    }
	    final Long above8hrs1 =   above8hrs;
	    final Long seventoeight1 = seventoeight;
	    final Long sixtoseven1 =  sixtoseven;
	    final Long fivetosix1 =   fivetosix;
	    final Long fourtofive1 =  fourtofive;
	    final Long threetofour1 = threetofour;
	    final Long twotothree1 = twotothree;
	    final Long onetotwo1 =  onetotwo;
	    final Long halfanhour1 =  halfanhour;
	    final Long belowhalfanhour1 = belowhalfanhour;
	    
	    final Long  above8hrsInv1= above8hrsInv;
	    final Long  seventoeightInv1= seventoeightInv; 
	    final Long  sixtosevenInv1=sixtosevenInv;
	    final Long  fivetosixInv1= fivetosixInv; 
	    final Long  fourtofiveInv1=fourtofiveInv; 
	    final Long  threetofourInv1=  threetofourInv; 
	    final Long  twotothreeInv1=twotothreeInv;
	    final Long  onetotwoInv1=  onetotwoInv;
	    final Long  halfanhourInv1=halfanhourInv; 
	    final Long  belowhalfanhourInv1= belowhalfanhourInv;
		
		
	    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				CadreMahanaduVisitInfo info = new CadreMahanaduVisitInfo();
				info.setInsertedTime(todayDate);
				info.setParentEventId(parentEventId);
				info = cadreMahanaduVisitInfoDAO.save(info);
				
				saveDetails(info,"above8hrs",above8hrs1,above8hrsInv1);
				saveDetails(info,"seventoeight",seventoeight1,seventoeightInv1);
				saveDetails(info,"sixtoseven",sixtoseven1,sixtosevenInv1);
				saveDetails(info,"fivetosix",fivetosix1,fivetosixInv1);
				saveDetails(info,"fourtofive",fourtofive1,fourtofiveInv1);
				saveDetails(info,"threetofour",threetofour1,threetofourInv1);
				saveDetails(info,"twotothree",twotothree1,twotothreeInv1);
				saveDetails(info,"onetotwo",onetotwo1,onetotwoInv1);
				saveDetails(info,"halfanhour",halfanhour1,halfanhourInv1);
				saveDetails(info,"belowhalfanhour",belowhalfanhour1,belowhalfanhourInv1);
			}
		});
	    cadreMahanaduVisitInfoDAO.flushAndclearSession();
	 }catch(Exception e){
		 LOG.error("Exception rised in getTodayTotalVisitors()",e);
	 }
	}
	
	private void saveDetails(CadreMahanaduVisitInfo info,String type,Long count,Long inviteCount){
		CadreMahanaduVisitDetails details = new CadreMahanaduVisitDetails();
		details.setCadreMahanaduVisitInfo(info);
		details.setType(type);
		details.setCount(count);
		details.setInviteCount(inviteCount);
		cadreMahanaduVisitDetailsDAO.save(details);
	}
	public List<MahanaduVisitVO> getTodayTotalAndCurrentUsersInfo(Date fromDate,Date toDate){
		List<MahanaduVisitVO> returnList = new ArrayList<MahanaduVisitVO>();
		Long totalVisitors = 0l;
		Long currentVisitors = 0l;
		Long currentInviteeVisitors = 0l;
		Long reqParentEventId = null;
		try{
			
		EntryExitInfo entryExitInfo = entryExitInfoDAO.getAll().get(0);
		Long entryEventId = entryExitInfo.getEntryId();
		Long exitEventId = entryExitInfo.getExitId();
		Long parentEventId = entryExitInfo.getParentEventId();
		reqParentEventId = parentEventId;
		
		Calendar fromDateCal = Calendar.getInstance();
		fromDateCal.setTime(fromDate);
		
		Calendar toDateCal = Calendar.getInstance();
		toDateCal.setTime(toDate);
		
		Date todayDate = dateUtilService.getCurrentDateAndTime();
		totalVisitors = eventAttendeeDAO.getTodayTotalVisitors(todayDate,parentEventId,entryEventId,null);
		currentVisitors = (eventAttendeeDAO.getCurrentVisitors(todayDate, entryEventId, exitEventId,null)).longValue();
		currentInviteeVisitors = (eventAttendeeDAO.getCurrentInviteeVisitors(todayDate, entryEventId, exitEventId,null)).longValue();
		
		
			if(!(toDate.before(fromDate))){
			    int from = fromDateCal.get(Calendar.DAY_OF_MONTH);
			    int to   = toDateCal.get(Calendar.DAY_OF_MONTH);
			    Map<Integer,Date> reqDates = new HashMap<Integer,Date>();
			    List<Integer> keys = new ArrayList<Integer>();
			    for(int i=from;i<=to;i++){
			    	Calendar cal = Calendar.getInstance();
			    	cal.set(2015, 4, i);
			    	reqDates.put(i, cal.getTime());
			    	if(!keys.contains(i)){
			    	    keys.add(i);
			    	}
			    }
			   for(Integer key:keys){ 
				//0id,1date
				List<Object[]> latestRecord = cadreMahanaduVisitDetailsDAO.getLatestInfoRecord(reqDates.get(key),parentEventId);
				if(latestRecord.size() > 0 && latestRecord.get(0)[0] != null){
					//0type 1count
					List<Object[]> visitDetailsList = cadreMahanaduVisitDetailsDAO.getLatestRecords((Long)latestRecord.get(0)[0]);
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
					MahanaduVisitVO returnVo = new MahanaduVisitVO();
					returnVo.setLastUpdated(sdf.format((Date)latestRecord.get(0)[1]));
					for(Object[] visitDetail:visitDetailsList){
						if(visitDetail[0].toString().equalsIgnoreCase("above8hrs")){
							returnVo.setAbove8hrs((Long)visitDetail[1]);
							returnVo.setAbove8hrsInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("seventoeight")){
							returnVo.setSeventoeight((Long)visitDetail[1]);
							returnVo.setSeventoeightInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("sixtoseven")){
							returnVo.setSixtoseven((Long)visitDetail[1]);
							returnVo.setSixtosevenInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("fivetosix")){
							returnVo.setFivetosix((Long)visitDetail[1]);
							returnVo.setFivetosixInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("fourtofive")){
							returnVo.setFourtofive((Long)visitDetail[1]);
							returnVo.setFourtofiveInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("threetofour")){
							returnVo.setThreetofour((Long)visitDetail[1]);
							returnVo.setThreetofourInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("twotothree")){
							returnVo.setTwotothree((Long)visitDetail[1]);
							returnVo.setTwotothreeInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("onetotwo")){
							returnVo.setOnetotwo((Long)visitDetail[1]);
							returnVo.setOnetotwoInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("halfanhour")){
							returnVo.setHalfanhour((Long)visitDetail[1]);
							returnVo.setHalfanhourInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("belowhalfanhour")){
							returnVo.setBelowhalfanhour((Long)visitDetail[1]);
							returnVo.setBelowhalfanhourInv((Long)visitDetail[2]);
						}
						
					}
					returnList.add(returnVo);
				}
			 }
			}
			

		}catch(Exception e){
			LOG.error("Exception rised in getTodayTotalAndCurrentUsersInfo()",e);
		}
		if(returnList.size() > 0){
			returnList.get(0).setTotalVisitors(totalVisitors);
			returnList.get(0).setCurrentVisitors(currentVisitors);
			returnList.get(0).setCurrentInviteeVisitors(currentInviteeVisitors);
			returnList.get(0).setParentEventId(reqParentEventId);
		}else{
			MahanaduVisitVO returnVo = new MahanaduVisitVO();
			returnList.add(returnVo);
			returnList.get(0).setTotalVisitors(totalVisitors);
			returnList.get(0).setCurrentVisitors(currentVisitors);
			returnList.get(0).setCurrentInviteeVisitors(currentInviteeVisitors);
			returnList.get(0).setParentEventId(reqParentEventId);
		}
		return returnList;
	}
	
	public List<MahanaduVisitVO> getTodayTotalAndCurrentUsersInfoList(Long eventId,String dateValues,List<Long> enrollmentIdsList){
		List<MahanaduVisitVO> returnList = new ArrayList<MahanaduVisitVO>();
		Long totalVisitors = 0l;
		Long currentVisitors = 0l;
		Long currentInviteeVisitors = 0l;
		Long reqParentEventId = null;
		try{
			
			Date fromDate = null;
			Date toDate	  = null;
			Object[] datesList =  eventDAO.getEventDates(eventId);
			
			int year = 0;
			int month = 0;
			if(datesList !=null && datesList.length>0){
				fromDate = datesList[0] !=null ? (Date)datesList[0]:null;
				toDate	 = datesList[1] !=null ? (Date)datesList[1]:null;
				
				year = Integer.parseInt(datesList[0].toString().split("-")[0]);
				month = Integer.parseInt(datesList[0].toString().split("-")[1]);
			}
			
		
		Calendar fromDateCal = Calendar.getInstance();
		fromDateCal.setTime(fromDate);
		
		Calendar toDateCal = Calendar.getInstance();
		toDateCal.setTime(toDate);
		
		Date todayDate = dateUtilService.getCurrentDateAndTime();
		Long parentEventId = 0L;
		EntryExitInfo entryExitInfo = entryExitInfoDAO.getEntryDetails(eventId);
		if(entryExitInfo != null){
			Long entryEventId = entryExitInfo.getEntryId();
			Long exitEventId = entryExitInfo.getExitId();
			parentEventId = entryExitInfo.getParentEventId();
			reqParentEventId = parentEventId;
			totalVisitors = eventAttendeeDAO.getTodayTotalVisitors(todayDate,parentEventId,entryEventId,enrollmentIdsList);
			currentVisitors = (eventAttendeeDAO.getCurrentVisitors(todayDate, entryEventId, exitEventId,enrollmentIdsList)).longValue();
			currentInviteeVisitors = (eventAttendeeDAO.getCurrentInviteeVisitors(todayDate, entryEventId, exitEventId,enrollmentIdsList)).longValue();
		}
			if(!(toDate.before(fromDate))){
			    int from = fromDateCal.get(Calendar.DAY_OF_MONTH);
			    int to   = toDateCal.get(Calendar.DAY_OF_MONTH);
			    Map<Integer,Date> reqDates = new HashMap<Integer,Date>();
			    List<Integer> keys = new ArrayList<Integer>();
			    for(int i=from;i<=to;i++){
			    	Calendar cal = Calendar.getInstance();
			    	if(year >0 && month>0){
			    		cal.set(year, month-1, i);
			    	}	
			    	reqDates.put(i, cal.getTime());
			    	if(!keys.contains(i)){
			    	    keys.add(i);
			    	}
			    }
			   for(Integer key:keys){ 
				//0id,1date
				List<Object[]> latestRecord = cadreMahanaduVisitDetailsDAO.getLatestInfoRecord(reqDates.get(key),parentEventId);
				if(latestRecord.size() > 0 && latestRecord.get(0)[0] != null){
					//0type 1count
					List<Object[]> visitDetailsList = cadreMahanaduVisitDetailsDAO.getLatestRecords((Long)latestRecord.get(0)[0]);
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
					MahanaduVisitVO returnVo = new MahanaduVisitVO();
					returnVo.setLastUpdated(sdf.format((Date)latestRecord.get(0)[1]));
					for(Object[] visitDetail:visitDetailsList){
						if(visitDetail[0].toString().equalsIgnoreCase("above8hrs")){
							returnVo.setAbove8hrs((Long)visitDetail[1]);
							returnVo.setAbove8hrsInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("seventoeight")){
							returnVo.setSeventoeight((Long)visitDetail[1]);
							returnVo.setSeventoeightInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("sixtoseven")){
							returnVo.setSixtoseven((Long)visitDetail[1]);
							returnVo.setSixtosevenInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("fivetosix")){
							returnVo.setFivetosix((Long)visitDetail[1]);
							returnVo.setFivetosixInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("fourtofive")){
							returnVo.setFourtofive((Long)visitDetail[1]);
							returnVo.setFourtofiveInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("threetofour")){
							returnVo.setThreetofour((Long)visitDetail[1]);
							returnVo.setThreetofourInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("twotothree")){
							returnVo.setTwotothree((Long)visitDetail[1]);
							returnVo.setTwotothreeInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("onetotwo")){
							returnVo.setOnetotwo((Long)visitDetail[1]);
							returnVo.setOnetotwoInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("halfanhour")){
							returnVo.setHalfanhour((Long)visitDetail[1]);
							returnVo.setHalfanhourInv((Long)visitDetail[2]);
						}else if(visitDetail[0].toString().equalsIgnoreCase("belowhalfanhour")){
							returnVo.setBelowhalfanhour((Long)visitDetail[1]);
							returnVo.setBelowhalfanhourInv((Long)visitDetail[2]);
						}
						
					}
					returnList.add(returnVo);
				}
			 }
			}
			
			List<MahanaduVisitVO> finalList = new ArrayList<MahanaduVisitVO>();			
			if(dateValues !=null && !dateValues.isEmpty()){				
				int lnth = dateValues.split(",").length;				
				if(commonMethodsUtilService.isListOrSetValid(returnList)){					
					for(int i=0;i<lnth;i++){
						if((i+1)<returnList.size() || (i+1)==returnList.size()){
							finalList.add(returnList.get(i));
						}
					}					
					returnList.clear();					
					returnList = finalList;					
				}				
			}

		}catch(Exception e){
			LOG.error("Exception rised in getTodayTotalAndCurrentUsersInfoList()",e);
		}
		if(returnList.size() > 0){
			returnList.get(0).setTotalVisitors(totalVisitors);
			returnList.get(0).setCurrentVisitors(currentVisitors);
			returnList.get(0).setCurrentInviteeVisitors(currentInviteeVisitors);
			returnList.get(0).setParentEventId(reqParentEventId);
		}else{
			MahanaduVisitVO returnVo = new MahanaduVisitVO();
			returnList.add(returnVo);
			returnList.get(0).setTotalVisitors(totalVisitors);
			returnList.get(0).setCurrentVisitors(currentVisitors);
			returnList.get(0).setCurrentInviteeVisitors(currentInviteeVisitors);
			returnList.get(0).setParentEventId(reqParentEventId);
		}
		return returnList;
	}
	
	public MahanaduVisitVO getTodayTotalAndCurrentUsersInfoListNew(Long eventId,String eventCurrentDate,List<Long> enrollmentIdsList){		
		
		MahanaduVisitVO finalVo = new MahanaduVisitVO();
		
		try{
						
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Long toDaytotalVisitors = 0l;
			Long toDaytotalInviteeVisitors = 0l;
			Long currentVisitors = 0l;
			Long currentInviteeVisitors = 0l;			
			Long entryEventId = 0L;
			Long exitEventId = 0L;
			Long parentEventId = 0L;
			
			EntryExitInfo entryExitInfo = entryExitInfoDAO.getEntryDetails(eventId);
			if(entryExitInfo != null){
				entryEventId = entryExitInfo.getEntryId();
				exitEventId = entryExitInfo.getExitId();
				parentEventId = entryExitInfo.getParentEventId();
			}
			
			Date todayDate =null;
			if(eventCurrentDate !=null && !eventCurrentDate.isEmpty()){
				todayDate = sdf.parse(eventCurrentDate); 
			}else{
				todayDate = dateUtilService.getCurrentDateAndTime();
			}
			
			
			toDaytotalVisitors = eventAttendeeDAO.getTodayTotalVisitors(todayDate,parentEventId,entryEventId,enrollmentIdsList);			
			toDaytotalInviteeVisitors=eventAttendeeDAO.getTodayTotalInviteeVisitors(todayDate,parentEventId,entryEventId,enrollmentIdsList);			
			currentVisitors = (eventAttendeeDAO.getCurrentVisitors(todayDate, entryEventId, exitEventId,enrollmentIdsList)).longValue();
			currentInviteeVisitors = (eventAttendeeDAO.getCurrentInviteeVisitors(todayDate, entryEventId, exitEventId,enrollmentIdsList)).longValue();
			
			
			finalVo.setTotalVisitors(toDaytotalVisitors);
			finalVo.setTotalInviteeVisitors(toDaytotalInviteeVisitors);
			if(toDaytotalVisitors !=null && toDaytotalInviteeVisitors !=null){
				finalVo.setTotalNonInviteeVisitors(toDaytotalVisitors - toDaytotalInviteeVisitors);
			}
				
			finalVo.setCurrentVisitors(currentVisitors);
			finalVo.setCurrentInviteeVisitors(currentInviteeVisitors);
			if(currentVisitors !=null && currentInviteeVisitors !=null){
				finalVo.setCurrentNonInviteeVisitors(currentVisitors - currentInviteeVisitors);
			}
			
			List<MahanaduEventVO>  totalHourVisitors= getHourWiseTotalVisitorsCount(parentEventId,todayDate,null,enrollmentIdsList);//total Visitors Hours wise For Today
			//getHourWiseTotalVisitorsCount(parentEventId,todayDate,"Invitee");//total Invited Visitors Hours wise Flow For Today
			
			//getHourWiseCurrentVisitorsCount(todayDate,entryEventId,exitEventId,null);
			//getHourWiseCurrentVisitorsCount(todayDate,entryEventId,exitEventId,"Invitee");
			
			finalVo.setEventVOList(totalHourVisitors);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return finalVo;
		
	}
	public List<MahanaduEventVO> getHourWiseTotalVisitorsCount(Long parentEventId,Date startDate,String type,List<Long> enrollmentIdsList )
	 {	
		 try{
			 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			// if(startDate != null && !startDate.isEmpty())
			// eventStrDate = format.parse(startDate);
			 List<Long> eventIds = new ArrayList<Long>(0);
			 List<Long> subEventIds = new ArrayList<Long>(0);
			 
			 //0.total,1.invited count,2.non-invited count,3.hour
			 List<Object[]> hourWiseResult = eventAttendeeDAO.getHourWiseTotalVisitorsCount(parentEventId,startDate,subEventIds,type,enrollmentIdsList);
			 List<MahanaduEventVO> defaultHoursList =  setHoursList();
			 if(hourWiseResult != null && hourWiseResult.size() > 0){
				 for(Object[] obj :hourWiseResult){														
					MahanaduEventVO resultVO = getMatchedVO(defaultHoursList,(Long)obj[3]);								
					if(resultVO !=null)
					{
						resultVO.setTotal((Long)obj[0]);
						resultVO.setInvitees((Long)obj[1]);
						resultVO.setNonInvitees((Long)obj[2]);
					}											
				 }
				 
			 }	 
			 return defaultHoursList;
		 }
		 catch(Exception e)
		 {
			 //LOG.error("Exception Occured in getHourWiseSubEventsCount()", e);
			 e.printStackTrace();
		 }
		return null;
	 }
	
	public List<MahanaduEventVO> setHoursList(){
	 	 
	  	 List<MahanaduEventVO> hoursList = new ArrayList<MahanaduEventVO>();
		 for(int i=8;i<=20;i++){
			 	MahanaduEventVO vo = new MahanaduEventVO();
				vo.setId(new Long(i));
				if(i<12){
					if(i==0)
						vo.setName(12 +"AM");
					else
						vo.setName(i +"AM");					
				}else if(i >= 12){
					if(i==12)
						vo.setName(12 +"PM");
					else
						vo.setName(i - 12 +"PM");
				}
				hoursList.add(vo);			
		}
	    return hoursList;
	}
	
	 public MahanaduEventVO getMatchedVO(List<MahanaduEventVO> list,Long id)
	 {		
			if(list != null && list.size()>0)
			{
				for (MahanaduEventVO vo : list)
				{	
					if(vo.getId() != null && vo.getId().longValue() == id.longValue())
					{
						return vo;
					}
				}
			}

		return null;
	 }
	 
	public MahanaduEventVO getDistrictWiseTotalAndPresentCadre(Long eventId,List<Long> stateIds,String date,List<Long> enrollmentIdsList){
		
		MahanaduEventVO finalVO = new MahanaduEventVO();
		
		try{
			boolean otherStatesExist = false;
			
			List<Long> twoStateIds = new ArrayList<Long>(0);
			twoStateIds.add(1l);
			twoStateIds.add(36l);
			
			StringBuilder districtQueryStr = new StringBuilder();
			
			if(stateIds.containsAll(twoStateIds)){				
				districtQueryStr.append(" and d.district_id between 1 and 23 ");				
			}else if(stateIds.contains(1l)){				
				districtQueryStr.append(" and d.district_id between 11 and 23 ");				
			}else if(stateIds.contains(36l)){
				districtQueryStr.append(" and d.district_id between 1 and 10 ");
			}
			
			StringBuilder districtQueryStr1 = new StringBuilder();
			
			if(stateIds.containsAll(twoStateIds)){				
				districtQueryStr1.append(" and model.tdpCadre.userAddress.constituency.district.districtId in (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,517,518) ");				
			}else if(stateIds.contains(1l)){				
				districtQueryStr1.append(" and model.tdpCadre.userAddress.constituency.district.districtId in (11,12,13,14,15,16,17,18,19,20,21,22,23,517)  ");				
			}else if(stateIds.contains(36l)){
				districtQueryStr1.append(" and model.tdpCadre.userAddress.constituency.district.districtId in (1,2,3,4,5,6,7,8,9,10,518) ");
			}
			
			StringBuilder otherStatesLocationQueryString = new StringBuilder();
			if(stateIds.contains(0l)){//for other states
				otherStatesExist = true;
				otherStatesLocationQueryString.append(" and UA.state_id not in (1) and UA.state_id is not null ");
			}
			
			StringBuilder otherStatesLocationQueryString1 = new StringBuilder();
			if(stateIds.contains(0l)){//for other states
				otherStatesExist = true;
				otherStatesLocationQueryString1.append(" and model.tdpCadre.userAddress.state.stateId not in (1) and model.tdpCadre.userAddress.state.stateId is not null ");
			}
			
			
			Long entryEventId = 0L;
			Long exitEventId = 0L;
			
			EntryExitInfo entryExitInfo = entryExitInfoDAO.getEntryDetails(eventId);
			if(entryExitInfo != null){
				entryEventId = entryExitInfo.getEntryId();
				exitEventId = entryExitInfo.getExitId();
			}		
			//Date todayDate = dateUtilService.getCurrentDateAndTime();
			Date todayDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			
			
			//Default District Values
			
			List<MahanaduEventVO> defaultDistList = new ArrayList<MahanaduEventVO>(0);
			
			List<Object[]> allDistrictList = null;
			
			if(stateIds.containsAll(twoStateIds)){				
				allDistrictList = districtDAO.getDistrictIdAndNameByStateForRegion(1l,"Both");				
			}else if(stateIds.contains(1l)){				
				allDistrictList = districtDAO.getDistrictIdAndNameByStateForRegion(1l,"Andhra Pradesh");			
			}else if(stateIds.contains(36l)){
				allDistrictList = districtDAO.getDistrictIdAndNameByStateForRegion(1l,"Telangana");
			}
			
					
			if(allDistrictList !=null && allDistrictList.size()>0){
				
				for (Object[] objects : allDistrictList) {
					MahanaduEventVO VO = new MahanaduEventVO();
					
					VO.setId(objects[0] !=null ? (Long)objects[0]:0l);
					VO.setName(objects[1] !=null ? objects[1].toString():"");
					defaultDistList.add(VO);
				}
			}
			
			/*Current Cadre In Campus*/
				
			//0.current Cadre Count,1.districtId
			  List<Object[]> currentCountList = eventAttendeeDAO.getDistrictWiseCurrentCadreInCampus(todayDate,entryEventId,exitEventId,districtQueryStr.toString(),enrollmentIdsList);
			  
			  if(currentCountList !=null && currentCountList.size()>0){
				  for(Object[] objects : currentCountList) {				
					  MahanaduEventVO VO = getMatchedVO(defaultDistList,(Long)objects[1]);				  
					  if(VO !=null){					  
						  VO.setCadreCount(objects[0] !=null ? (Long)objects[0]:0l);//current in Campus					  
					  }else{
						  MahanaduEventVO vo = new MahanaduEventVO();
						  defaultDistList.add(vo);
					  }
				  }
			  }
			  
			  
			  /*Total,Invited and Non invited Cadre*/		  
			  //0.total,1.districtId,2.districtName
			  List<Object[]> totalCountList = eventAttendeeDAO.getDistrictWiseTotalInvitedAndNonInvitedCount(entryEventId,districtQueryStr1.toString(),todayDate,enrollmentIdsList);
			  
			  if(totalCountList !=null && totalCountList.size()>0){				  
				  for(Object[] obj : totalCountList) {							  
					  MahanaduEventVO VO  = getMatchedVO(defaultDistList,(Long)obj[1]);
					  if(VO !=null){					  
						  VO.setTotal(obj[0] !=null ? (Long)obj[0]:0l);//current in Campus	
						 // VO.setInvitees(obj[1] !=null ? (Long)obj[1]:0l);
						  //VO.setNonInvitees(obj[2] !=null ? (Long)obj[2]:0l);
					  }else{
						  MahanaduEventVO vo = new MahanaduEventVO();
						  defaultDistList.add(vo);
					  }					  
				  }
			  }
			  
			  if(defaultDistList !=null && defaultDistList.size()>0){
				  finalVO.setSubList(defaultDistList);
			  }
			  
			  if(otherStatesExist){
				  List<MahanaduEventVO> otherStatesfinalRslt = new ArrayList<MahanaduEventVO>(0);
				  
				  Map<Long,Long> distWiseCountsMap = new HashMap<Long, Long>(0);
				  List<Object[]> otherStateRslt = eventAttendeeDAO.getOtherStateDistrictWiseCurrentCadreInCampus(todayDate,entryEventId,exitEventId,otherStatesLocationQueryString.toString(),enrollmentIdsList);
				  
				  if(otherStateRslt != null && otherStateRslt.size() > 0){
					  for (Object[] objects : otherStateRslt) {
						  distWiseCountsMap.put((Long)objects[1], (Long)objects[0]);
					  }
				  }
				  
				  /*Total,Invited and Non invited Cadre for other states*/		  
				  //0.total,1.districtId,2.districtName
				  List<Object[]>  otherStatesTotalCountList = eventAttendeeDAO.getOtherStatesDistrictWiseTotalInvitedAndNonInvitedCount(entryEventId,otherStatesLocationQueryString1.toString(),todayDate,enrollmentIdsList);
				  
				  if(otherStatesTotalCountList != null && otherStatesTotalCountList.size() > 0){
					  for (Object[] obj : otherStatesTotalCountList) {
						  MahanaduEventVO vo = new MahanaduEventVO();
						  vo.setId((Long)obj[1]);
						  vo.setName(obj[2].toString());
						  vo.setCadreCount(distWiseCountsMap.get((Long)obj[1]) != null ? distWiseCountsMap.get((Long)obj[1]) : 0l);
						  vo.setTotal((Long)obj[0]);
						 // vo.setInvitees(obj[1] !=null ? (Long)obj[1]:0l);
						  //vo.setNonInvitees(obj[2] !=null ? (Long)obj[2]:0l);
						  otherStatesfinalRslt.add(vo);
					  }
				  }
				  
				  finalVO.getSubList().addAll(otherStatesfinalRslt);
			  }
			  
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return finalVO;
		
	}
	
	public MahanaduEventVO getConstituencyWiseMembersCountInCampus(Long eventId,List<Long> stateIds,String date,List<Long> enrollmentIds){
		MahanaduEventVO finalVO = new MahanaduEventVO();
		try{
			boolean otherStatesExist = false;
			
			List<Long> twoStateIds = new ArrayList<Long>(0);
			twoStateIds.add(1l);
			twoStateIds.add(36l);
			
			StringBuilder districtQueryStr = new StringBuilder();
			
			if(stateIds.containsAll(twoStateIds)){				
				districtQueryStr.append(" and c.district_id between 1 and 23 ");				
			}else if(stateIds.contains(1l)){				
				districtQueryStr.append(" and c.district_id between 11 and 23 ");				
			}else if(stateIds.contains(36l)){
				districtQueryStr.append(" and c.district_id between 1 and 10 ");
			}
			
			StringBuilder constituencyQueryStr1 = new StringBuilder();
			
			if(stateIds.containsAll(twoStateIds)){				
				constituencyQueryStr1.append(" and model.tdpCadre.userAddress.constituency.district.districtId between 1 and 23 ");				
			}else if(stateIds.contains(1l)){				
				constituencyQueryStr1.append(" and model.tdpCadre.userAddress.constituency.district.districtId between 11 and 23 ");				
			}else if(stateIds.contains(36l)){
				constituencyQueryStr1.append(" and model.tdpCadre.userAddress.constituency.district.districtId between 1 and 10 ");
			}
			
			StringBuilder otherStatesLocationQueryString = new StringBuilder();
			if(stateIds.contains(0l)){//for other states
				otherStatesExist = true;
				otherStatesLocationQueryString.append(" and UA.state_id not in (1) and UA.state_id is not null ");
			}
			
			StringBuilder otherStatesLocationQueryString1 = new StringBuilder();
			if(stateIds.contains(0l)){//for other states
				otherStatesExist = true;
				otherStatesLocationQueryString1.append(" and model.tdpCadre.userAddress.state.stateId not in (1) and model.tdpCadre.userAddress.state.stateId is not null ");
			}
			
			
			Long entryEventId = 0L;
			Long exitEventId = 0L;
			
			EntryExitInfo entryExitInfo = entryExitInfoDAO.getEntryDetails(eventId);
			if(entryExitInfo != null){
				entryEventId = entryExitInfo.getEntryId();
				exitEventId = entryExitInfo.getExitId();
			}		
			//Date todayDate = dateUtilService.getCurrentDateAndTime();
			Date todayDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			
			//Default Constituency Values
			
			List<MahanaduEventVO> defaultConstituencyList = new ArrayList<MahanaduEventVO>(0);
			
			List<Object[]> allConstituencyList = null;
			
			if(stateIds.containsAll(twoStateIds)){				
				allConstituencyList = constituencyDAO.getConstituencyIdAndNameByStateForRegion(1l,"Both");
			}else if(stateIds.contains(1l)){				
				allConstituencyList = constituencyDAO.getConstituencyIdAndNameByStateForRegion(1l,"Andhra Pradesh");			
			}else if(stateIds.contains(36l)){
				allConstituencyList = constituencyDAO.getConstituencyIdAndNameByStateForRegion(1l,"Telangana");
			}
			
			if(allConstituencyList !=null && allConstituencyList.size()>0){
				for (Object[] objects : allConstituencyList) {
					MahanaduEventVO VO = new MahanaduEventVO();
					
					VO.setId(objects[0] !=null ? (Long)objects[0]:0l);
					VO.setName(objects[1] !=null ? objects[1].toString():"");
					defaultConstituencyList.add(VO);
				}
			}
			
			/*Current Cadre In Campus*/
			
			//0.current Cadre Count,1.constituencyId
			  List<Object[]> currentCountList = eventAttendeeDAO.getConstituencyWiseCurrentCadreInCampus(todayDate,entryEventId,exitEventId,districtQueryStr.toString(),enrollmentIds);
			  
			  if(currentCountList !=null && currentCountList.size()>0){
				  for(Object[] objects : currentCountList) {				
					  MahanaduEventVO VO = getMatchedVO(defaultConstituencyList,(Long)objects[1]);				  
					  if(VO !=null){					  
						  VO.setCadreCount(objects[0] !=null ? (Long)objects[0]:0l);//current in Campus					  
					  }else{
						  MahanaduEventVO vo = new MahanaduEventVO();
						  defaultConstituencyList.add(vo);
					  }
				  }
			  }
			  
			  /*Total,Invited and Non invited Cadre*/		  
			  //0.total,1.contId,2.constName
			  List<Object[]> totalCountList = eventAttendeeDAO.getConstituencyWiseTotalInvitedAndNonInvitedCount(entryEventId,constituencyQueryStr1.toString(),todayDate,enrollmentIds);
			  
			  if(totalCountList !=null && totalCountList.size()>0){				  
				  for(Object[] obj : totalCountList) {							  
					  MahanaduEventVO VO  = getMatchedVO(defaultConstituencyList,(Long)obj[1]);
					  if(VO !=null){					  
						  VO.setTotal(obj[0] !=null ? (Long)obj[0]:0l);//current in Campus	
					  }else{
						  MahanaduEventVO vo = new MahanaduEventVO();
						  defaultConstituencyList.add(vo);
					  }					  
				  }
			  }
			  
			  if(defaultConstituencyList !=null && defaultConstituencyList.size()>0){
				  finalVO.setSubList(defaultConstituencyList);
			  }
			  
			  if(otherStatesExist){
				  List<MahanaduEventVO> otherStatesfinalRslt = new ArrayList<MahanaduEventVO>(0);
				  
				  Map<Long,Long> constWiseCountsMap = new HashMap<Long, Long>(0);
				  List<Object[]> otherStateRslt = eventAttendeeDAO.getOtherStateConstituencyWiseCurrentCadreInCampus(todayDate,entryEventId,exitEventId,otherStatesLocationQueryString.toString(),enrollmentIds);
				  
				  if(otherStateRslt != null && otherStateRslt.size() > 0){
					  for (Object[] objects : otherStateRslt) {
						  constWiseCountsMap.put((Long)objects[1], (Long)objects[0]);
					  }
				  }
				  
				  /*Total,Invited and Non invited Cadre for other states*/		  
				  //0.total,1.districtId,2.districtName
				  List<Object[]>  otherStatesTotalCountList = eventAttendeeDAO.getOtherStatesConstituencyWiseTotalInvitedAndNonInvitedCount(entryEventId,otherStatesLocationQueryString1.toString(),todayDate,enrollmentIds);
				  
				  if(otherStatesTotalCountList != null && otherStatesTotalCountList.size() > 0){
					  for (Object[] obj : otherStatesTotalCountList) {
						  MahanaduEventVO vo = new MahanaduEventVO();
						  vo.setId((Long)obj[1]);
						  vo.setName(obj[2].toString());
						  vo.setCadreCount(constWiseCountsMap.get((Long)obj[1]) != null ? constWiseCountsMap.get((Long)obj[1]) : 0l);
						  vo.setTotal((Long)obj[0]);
						 // vo.setInvitees(obj[1] !=null ? (Long)obj[1]:0l);
						  //vo.setNonInvitees(obj[2] !=null ? (Long)obj[2]:0l);
						  otherStatesfinalRslt.add(vo);
					  }
				  }
				  
				  finalVO.getSubList().addAll(otherStatesfinalRslt);
			
			  }
		}catch (Exception e) {
			LOG.error("Exception riased at getConstituencyWiseMembersCountInCampus", e);
		}
		return finalVO;
	}
	
	public Long getTodayCount(Long eventId){
		int day = 0;
		try {
			Object[] dateObj = eventDAO.getEventDates(eventId);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			 List<Date> betweenDates= commonMethodsUtilService.getBetweenDates(format.parse(dateObj[0].toString()),format.parse(dateObj[1].toString()));
			 
			 if(betweenDates != null && betweenDates.size() > 0){
				 for (int i = 0; i < betweenDates.size(); i++) {
					 if(format.format(betweenDates.get(i)).equals(format.format(dateUtilService.getCurrentDateAndTime()))){
						 day = i+1;
					 }
				 }	
			 }
			 
		} catch (Exception e) {
			LOG.error("Exception riased at getTodayCount", e);
		}
		return Long.parseLong(day+"");
	}
	
	public List<MahanaduEventVO> getHourWiseNowInCampusCadresCount(String dayCount,Long eventId,List<Long> enrollmentIdsList){
		List<MahanaduEventVO> defaultHoursList =  setHoursList();
		try {
			Object[] dateObj = eventDAO.getEventDates(eventId);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			/*List<Date> betweenDates= commonMethodsUtilService.getBetweenDates(format.parse(dateObj[0].toString()),format.parse(dateObj[1].toString()));
			
			Date date = betweenDates.get(Integer.parseInt(dayCount.toString())-1);*/
			Date date = format.parse(dayCount);
			Long entryEventId = 0L;
			Long exitEventId = 0L;
			EntryExitInfo entryExitInfo = entryExitInfoDAO.getEntryDetails(eventId);
			if(entryExitInfo != null){
				entryEventId = entryExitInfo.getEntryId();
				exitEventId = entryExitInfo.getExitId();
			}
			
			//get now in campus counts
			//0-cadreids count,1-hour
			List<Object[]> currentInCampusObjList = eventAttendeeDAO.getHourWiseCurrentVisitorsCount(date,entryEventId,exitEventId,null,enrollmentIdsList);
			
			MahanaduEventVO vo = new MahanaduEventVO();
			
			Map<Long,Long> curentInCampusMap = new HashMap<Long, Long>(0);
			if(currentInCampusObjList != null && currentInCampusObjList.size() > 0){
				for (Object[] objects : currentInCampusObjList) {
					if((Long)objects[1]<=8){
						vo.setEightam(vo.getEightam()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo.setNineam(vo.getEightam());
						vo.setTenam(vo.getEightam());
						vo.setElevenam(vo.getEightam());
						vo.setTwelvpm(vo.getEightam());
						vo.setOnepm(vo.getEightam());
						vo.setTwopm(vo.getEightam());
						vo.setThreepm(vo.getEightam());
						vo.setFourpm(vo.getEightam());
						vo.setFivepm(vo.getEightam());
						vo.setSixpm(vo.getEightam());
						vo.setSevenpm(vo.getEightam());
						vo.setEightpm(vo.getEightam());*/
					}
					if((Long)objects[1]==9){
						vo.setNineam(vo.getNineam()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo.setNineam(vo.getEightam()+(Long)objects[0]);
						vo.setTenam(vo.getNineam());
						vo.setElevenam(vo.getNineam());
						vo.setTwelvpm(vo.getNineam());
						vo.setOnepm(vo.getNineam());
						vo.setTwopm(vo.getNineam());
						vo.setThreepm(vo.getNineam());
						vo.setFourpm(vo.getNineam());
						vo.setFivepm(vo.getNineam());
						vo.setSixpm(vo.getNineam());
						vo.setSevenpm(vo.getNineam());
						vo.setEightpm(vo.getNineam());*/
					}
					if((Long)objects[1]==10){
						vo.setTenam(vo.getTenam()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo.setTenam(vo.getNineam()+(Long)objects[0]);
						vo.setElevenam(vo.getTenam());
						vo.setTwelvpm(vo.getTenam());
						vo.setOnepm(vo.getTenam());
						vo.setTwopm(vo.getTenam());
						vo.setThreepm(vo.getTenam());
						vo.setFourpm(vo.getTenam());
						vo.setFivepm(vo.getTenam());
						vo.setSixpm(vo.getTenam());
						vo.setSevenpm(vo.getTenam());
						vo.setEightpm(vo.getTenam());*/
					}
					if((Long)objects[1]==11){
						vo.setElevenam(vo.getElevenam()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo.setElevenam(vo.getTenam()+(Long)objects[0]);
						vo.setTwelvpm(vo.getElevenam());
						vo.setOnepm(vo.getElevenam());
						vo.setTwopm(vo.getElevenam());
						vo.setThreepm(vo.getElevenam());
						vo.setFourpm(vo.getElevenam());
						vo.setFivepm(vo.getElevenam());
						vo.setSixpm(vo.getElevenam());
						vo.setSevenpm(vo.getElevenam());
						vo.setEightpm(vo.getElevenam());*/
					}
					if((Long)objects[1]==12){
						vo.setTwelvpm(vo.getTwelvpm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo.setTwelvpm(vo.getElevenam()+(Long)objects[0]);
						vo.setOnepm(vo.getTwelvpm());
						vo.setTwopm(vo.getTwelvpm());
						vo.setThreepm(vo.getTwelvpm());
						vo.setFourpm(vo.getTwelvpm());
						vo.setFivepm(vo.getTwelvpm());
						vo.setSixpm(vo.getTwelvpm());
						vo.setSevenpm(vo.getTwelvpm());
						vo.setEightpm(vo.getTwelvpm());*/
					}
					if((Long)objects[1]==13){
						vo.setOnepm(vo.getOnepm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo.setOnepm(vo.getTwelvpm()+(Long)objects[0]);
						vo.setTwopm(vo.getOnepm());
						vo.setThreepm(vo.getOnepm());
						vo.setFourpm(vo.getOnepm());
						vo.setFivepm(vo.getOnepm());
						vo.setSixpm(vo.getOnepm());
						vo.setSevenpm(vo.getOnepm());
						vo.setEightpm(vo.getOnepm());*/
					}
					if((Long)objects[1]==14){
						vo.setTwopm(vo.getTwopm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo.setTwopm(vo.getOnepm()+(Long)objects[0]);
						vo.setThreepm(vo.getTwopm());
						vo.setFourpm(vo.getTwopm());
						vo.setFivepm(vo.getTwopm());
						vo.setSixpm(vo.getTwopm());
						vo.setSevenpm(vo.getTwopm());
						vo.setEightpm(vo.getTwopm());*/
					}
					if((Long)objects[1]==15){
						vo.setThreepm(vo.getThreepm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo.setThreepm(vo.getTwopm()+(Long)objects[0]);
						vo.setFourpm(vo.getThreepm());
						vo.setFivepm(vo.getThreepm());
						vo.setSixpm(vo.getThreepm());
						vo.setSevenpm(vo.getThreepm());
						vo.setEightpm(vo.getThreepm());*/
					}
					if((Long)objects[1]==16){
						vo.setFourpm(vo.getFourpm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo.setFourpm(vo.getThreepm()+(Long)objects[0]);
						vo.setFivepm(vo.getFourpm());
						vo.setSixpm(vo.getFourpm());
						vo.setSevenpm(vo.getFourpm());
						vo.setEightpm(vo.getFourpm());*/
					}
					if((Long)objects[1]==17){
						vo.setFivepm(vo.getFivepm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo.setFivepm(vo.getFourpm()+(Long)objects[0]);
						vo.setSixpm(vo.getFivepm());
						vo.setSevenpm(vo.getFivepm());
						vo.setEightpm(vo.getFivepm());*/
					}
					if((Long)objects[1]==18){
						vo.setSixpm(vo.getSixpm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo.setSixpm(vo.getFivepm()+(Long)objects[0]);
						vo.setSevenpm(vo.getSixpm());
						vo.setEightpm(vo.getSixpm());*/
					}
					if((Long)objects[1]==19){
						vo.setSevenpm(vo.getSevenpm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo.setSevenpm(vo.getSixpm()+(Long)objects[0]);
						vo.setEightpm(vo.getSevenpm());*/
					}
					if((Long)objects[1]>=20 && (Long)objects[1]<=24){
						vo.setEightpm(vo.getEightpm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						//vo.setEightpm(vo.getSevenpm()+(Long)objects[0]);
					}
				}
			}		
			
			
			//get total counts
			//0-total,1-hour
			List<Object[]> totalCountsObjList = eventAttendeeDAO.getHourWiseTotalVisitorsCount(entryEventId,date,null,null,enrollmentIdsList);
			MahanaduEventVO vo1 = new MahanaduEventVO();
			if(totalCountsObjList != null && totalCountsObjList.size() > 0){
				for (Object[] objects : totalCountsObjList) {
					if((Long)objects[1]<=8){
						vo1.setEightam(vo.getEightam()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo1.setEightam(vo1.getEightam()+(Long)objects[0]);
						vo1.setNineam(vo1.getEightam());
						vo1.setTenam(vo1.getEightam());
						vo1.setElevenam(vo1.getEightam());
						vo1.setTwelvpm(vo1.getEightam());
						vo1.setOnepm(vo1.getEightam());
						vo1.setTwopm(vo1.getEightam());
						vo1.setThreepm(vo1.getEightam());
						vo1.setFourpm(vo1.getEightam());
						vo1.setFivepm(vo1.getEightam());
						vo1.setSixpm(vo1.getEightam());
						vo1.setSevenpm(vo1.getEightam());
						vo1.setEightpm(vo1.getEightam());*/
					}
					if((Long)objects[1]==9){
						vo1.setNineam(vo.getNineam()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo1.setNineam(vo1.getEightam()+(Long)objects[0]);
						vo1.setTenam(vo1.getNineam());
						vo1.setElevenam(vo1.getNineam());
						vo1.setTwelvpm(vo1.getNineam());
						vo1.setOnepm(vo1.getNineam());
						vo1.setTwopm(vo1.getNineam());
						vo1.setThreepm(vo1.getNineam());
						vo1.setFourpm(vo1.getNineam());
						vo1.setFivepm(vo1.getNineam());
						vo1.setSixpm(vo1.getNineam());
						vo1.setSevenpm(vo1.getNineam());
						vo1.setEightpm(vo1.getNineam());*/
					}
					if((Long)objects[1]==10){
						vo1.setTenam(vo.getTenam()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo1.setTenam(vo1.getNineam()+(Long)objects[0]);
						vo1.setElevenam(vo1.getTenam());
						vo1.setTwelvpm(vo1.getTenam());
						vo1.setOnepm(vo1.getTenam());
						vo1.setTwopm(vo1.getTenam());
						vo1.setThreepm(vo1.getTenam());
						vo1.setFourpm(vo1.getTenam());
						vo1.setFivepm(vo1.getTenam());
						vo1.setSixpm(vo1.getTenam());
						vo1.setSevenpm(vo1.getTenam());
						vo1.setEightpm(vo1.getTenam());*/
					}
					if((Long)objects[1]==11){
						vo1.setElevenam(vo.getElevenam()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo1.setElevenam(vo1.getTenam()+(Long)objects[0]);
						vo1.setTwelvpm(vo1.getElevenam());
						vo1.setOnepm(vo1.getElevenam());
						vo1.setTwopm(vo1.getElevenam());
						vo1.setThreepm(vo1.getElevenam());
						vo1.setFourpm(vo1.getElevenam());
						vo1.setFivepm(vo1.getElevenam());
						vo1.setSixpm(vo1.getElevenam());
						vo1.setSevenpm(vo1.getElevenam());
						vo1.setEightpm(vo1.getElevenam());*/
					}
					if((Long)objects[1]==12){
						vo1.setTwelvpm(vo.getTwelvpm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo1.setTwelvpm(vo1.getElevenam()+(Long)objects[0]);
						vo1.setOnepm(vo1.getTwelvpm());
						vo1.setTwopm(vo1.getTwelvpm());
						vo1.setThreepm(vo1.getTwelvpm());
						vo1.setFourpm(vo1.getTwelvpm());
						vo1.setFivepm(vo1.getTwelvpm());
						vo1.setSixpm(vo1.getTwelvpm());
						vo1.setSevenpm(vo1.getTwelvpm());
						vo1.setEightpm(vo1.getTwelvpm());*/
					}
					if((Long)objects[1]==13){
						vo1.setOnepm(vo.getOnepm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo1.setOnepm(vo1.getTwelvpm()+(Long)objects[0]);
						vo1.setTwopm(vo1.getOnepm());
						vo1.setThreepm(vo1.getOnepm());
						vo1.setFourpm(vo1.getOnepm());
						vo1.setFivepm(vo1.getOnepm());
						vo1.setSixpm(vo1.getOnepm());
						vo1.setSevenpm(vo1.getOnepm());
						vo1.setEightpm(vo1.getOnepm());*/
					}
					if((Long)objects[1]==14){
						vo1.setTwopm(vo.getTwopm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo1.setTwopm(vo1.getOnepm()+(Long)objects[0]);
						vo1.setThreepm(vo1.getTwopm());
						vo1.setFourpm(vo1.getTwopm());
						vo1.setFivepm(vo1.getTwopm());
						vo1.setSixpm(vo1.getTwopm());
						vo1.setSevenpm(vo1.getTwopm());
						vo1.setEightpm(vo1.getTwopm());*/
					}
					if((Long)objects[1]==15){
						vo1.setThreepm(vo.getThreepm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo1.setThreepm(vo1.getTwopm()+(Long)objects[0]);
						vo1.setFourpm(vo1.getThreepm());
						vo1.setFivepm(vo1.getThreepm());
						vo1.setSixpm(vo1.getThreepm());
						vo1.setSevenpm(vo1.getThreepm());
						vo1.setEightpm(vo1.getThreepm());*/
					}
					if((Long)objects[1]==16){
						vo1.setFourpm(vo.getFourpm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo1.setFourpm(vo1.getThreepm()+(Long)objects[0]);
						vo1.setFivepm(vo1.getFourpm());
						vo1.setSixpm(vo1.getFourpm());
						vo1.setSevenpm(vo1.getFourpm());
						vo1.setEightpm(vo1.getFourpm());*/
					}
					if((Long)objects[1]==17){
						vo1.setFivepm(vo.getFivepm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo1.setFivepm(vo1.getFourpm()+(Long)objects[0]);
						vo1.setSixpm(vo1.getFivepm());
						vo1.setSevenpm(vo1.getFivepm());
						vo1.setEightpm(vo1.getFivepm());*/
					}
					if((Long)objects[1]==18){
						vo1.setSixpm(vo.getSixpm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo1.setSixpm(vo1.getFivepm()+(Long)objects[0]);
						vo1.setSevenpm(vo1.getSixpm());
						vo1.setEightpm(vo1.getSixpm());*/
						
					}
					if((Long)objects[1]==19){
						vo1.setSevenpm(vo.getSevenpm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						/*vo1.setSevenpm(vo1.getSixpm()+(Long)objects[0]);
						vo1.setEightpm(vo1.getSevenpm());*/
					}
					if((Long)objects[1]>=20 && (Long)objects[1]<=24){
						vo1.setEightpm(vo.getEightpm()+Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
						//vo1.setEightpm(vo1.getSevenpm()+(Long)objects[0]);
					}
					/*vo.setTotal((Long)objects[0]);
					vo.setCadreCount(curentInCampusMap.get((Long)objects[3]) != null ? curentInCampusMap.get((Long)objects[3]) : 0l);
					vo.setInvitees((Long)objects[3]);
					mahanaduEventVOList.add(vo);*/
				}
			}
			
			//set final data to default hours list
			if(defaultHoursList != null && defaultHoursList.size() > 0){
				for (MahanaduEventVO mevo : defaultHoursList) {
					if(mevo.getId()==8){
						mevo.setTotal(vo1.getEightam());
						mevo.setCadreCount(vo.getEightam());
					}else if(mevo.getId()==9){
						mevo.setTotal(vo1.getNineam());
						mevo.setCadreCount(vo.getNineam());
					}else if(mevo.getId()==10){
						mevo.setTotal(vo1.getTenam());
						mevo.setCadreCount(vo.getTenam());
					}else if(mevo.getId()==11){
						mevo.setTotal(vo1.getElevenam());
						mevo.setCadreCount(vo.getElevenam());
					}else if(mevo.getId()==12){
						mevo.setTotal(vo1.getTwelvpm());
						mevo.setCadreCount(vo.getTwelvpm());
					}else if(mevo.getId()==13){
						mevo.setTotal(vo1.getOnepm());
						mevo.setCadreCount(vo.getOnepm());
					}else if(mevo.getId()==14){
						mevo.setTotal(vo1.getTwopm());
						mevo.setCadreCount(vo.getTwopm());
					}else if(mevo.getId()==15){
						mevo.setTotal(vo1.getThreepm());
						mevo.setCadreCount(vo.getThreepm());
					}else if(mevo.getId()==16){
						mevo.setTotal(vo1.getFourpm());
						mevo.setCadreCount(vo.getFourpm());
					}else if(mevo.getId()==17){
						mevo.setTotal(vo1.getFivepm());
						mevo.setCadreCount(vo.getFivepm());
					}else if(mevo.getId()==18){
						mevo.setTotal(vo1.getSixpm());
						mevo.setCadreCount(vo.getSixpm());
					}else if(mevo.getId()==19){
						mevo.setTotal(vo1.getSevenpm());
						mevo.setCadreCount(vo.getSevenpm());
					}else if(mevo.getId()==20){
						mevo.setTotal(vo1.getEightpm());
						mevo.setCadreCount(vo.getEightpm());
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getHourWiseNowInCampusCadresCount", e);
		}
		
		return defaultHoursList;
	}
	
	public List<IdNameVO> getEventDates(Long eventId){
		List<IdNameVO> voList = new ArrayList<IdNameVO>(0);	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Object[] objArr = eventDAO.getEventDates(eventId);
			if(objArr != null && objArr.length > 0){
				List<Date> datesList = commonMethodsUtilService.getBetweenDates((Date)objArr[0],(Date)objArr[1]);
				if(datesList != null && datesList.size() > 0){
					StringBuilder sb = new StringBuilder();
					for (int i=0;i<datesList.size();i++) { 
						if(sdf.format(datesList.get(i)).split("-")[0].equalsIgnoreCase(sdf.format(dateUtilService.getCurrentDateAndTime()).split("-")[0]) ){
							if(sdf.format(datesList.get(i)).split("-")[2].equalsIgnoreCase(sdf.format(dateUtilService.getCurrentDateAndTime()).split("-")[2])){
								IdNameVO vo = new IdNameVO();
								vo.setName(sb.append(sdf.format(datesList.get(i))+",").toString());
								vo.setPercentage("toDay");
								voList.add(vo);
							} 
							else{
								IdNameVO vo = new IdNameVO();
								vo.setName(sb.append(sdf.format(datesList.get(i))+",").toString());
								voList.add(vo);
							}
						}else if(Long.parseLong(sdf.format(datesList.get(i)).split("-")[0])<Long.parseLong(sdf.format(dateUtilService.getCurrentDateAndTime()).split("-")[0])){
							IdNameVO vo = new IdNameVO();
							vo.setName(sb.append(sdf.format(datesList.get(i))+",").toString());
							if(i==0){
								vo.setPercentage("toDay");
							}
							voList.add(vo);
						}
					}
				}
							
			}			
		} catch (Exception e) {
			LOG.error("Exception riased at getEventDates", e);
		}
		
		return voList;
	}
	
	public List<CandidateDetailsVO> getCandidateDetails(Long designationId,String inviteeType,Long eventId,String day,String roleType,String level,List<Long> enrollmentYearIds){
		List<CandidateDetailsVO> resultVOList = new ArrayList<CandidateDetailsVO>(0);
		try {
			//get candidate details for invitees
			List<Long> cadreIds = null;
			if(inviteeType.equalsIgnoreCase("total")){
				//0-tdpCadreId,1-name,2-publicRepresentativeTypeId,3-type,4-levelId,5-levelValue
				if(roleType.equalsIgnoreCase("PR"))
			    cadreIds = eventInviteeDAO.getCandidateTdpCadreIds(eventId,designationId,enrollmentYearIds);
				if(roleType.equalsIgnoreCase("CommitteeLevel"))
					cadreIds = eventInviteeDAO.getCandidateTdpCadreIdsForCommitteeLevel(eventId,designationId,enrollmentYearIds);
				if(roleType.equalsIgnoreCase("CommitteeRole"))
					cadreIds = eventInviteeDAO.getCandidateTdpCadreIdsForCommitteeRole(eventId,designationId,level,enrollmentYearIds);
				if(roleType.equalsIgnoreCase("affliatedCommittee"))
					cadreIds = eventInviteeDAO.getCandidateTdpCadreIdsForAffliatedCommitteeRole(eventId,designationId,level,enrollmentYearIds);
				
				if(cadreIds != null && cadreIds.size() > 0){
					getCandidateDetailsByCadreIds(cadreIds,resultVOList,null,eventId,roleType);
				}
			}else if(inviteeType.equalsIgnoreCase("attendee")){//get details for attended members
				
				Date date = null;
				if(day != null && !day.isEmpty())
				 date = new SimpleDateFormat("yyyy-MM-dd").parse(day);
				if(roleType.equalsIgnoreCase("PR"))
					cadreIds = eventAttendeeDAO.getCadreIdsForAttendees(eventId,date,designationId,enrollmentYearIds);
				if(roleType.equalsIgnoreCase("CommitteeLevel"))
					cadreIds =eventAttendeeDAO.getCadreIdsForAttendeesForCommitteeLevel(eventId,date,designationId,enrollmentYearIds);	
				if(roleType.equalsIgnoreCase("CommitteeRole"))
					cadreIds = eventAttendeeDAO.getCadreIdsForAttendeesForCommitteeRole(eventId,date,designationId,level,enrollmentYearIds);
				if(roleType.equalsIgnoreCase("affliatedCommittee"))	
					cadreIds = eventAttendeeDAO.getCadreIdsForAttendeesForAffliatedCommitteeRole(eventId,date,designationId,level,enrollmentYearIds);
				
					if(cadreIds != null && cadreIds.size() > 0){
					getCandidateDetailsByCadreIds(cadreIds,resultVOList,date,eventId,roleType);
				}
			}else if(inviteeType.equalsIgnoreCase("notAttendee")){//get details for not attended members
				List<Long> totalCadreIds = null;
				List<Long> attendedCadreIds =  new ArrayList<Long>(0);
				Date date = null;
				if(day != null && !day.isEmpty())
				 date = new SimpleDateFormat("yyyy-MM-dd").parse(day);
				if(roleType.equalsIgnoreCase("PR"))
					{
					
						 totalCadreIds = eventInviteeDAO.getCandidateTdpCadreIds(eventId,designationId,enrollmentYearIds);
						 attendedCadreIds = eventAttendeeDAO.getCadreIdsForAttendees(eventId,date,designationId,enrollmentYearIds);
					}
				if(roleType.equalsIgnoreCase("CommitteeLevel"))
				{
				
					 totalCadreIds = eventInviteeDAO.getCandidateTdpCadreIdsForCommitteeLevel(eventId,designationId,enrollmentYearIds);
					 attendedCadreIds = eventAttendeeDAO.getCadreIdsForAttendeesForCommitteeLevel(eventId,date,designationId,enrollmentYearIds);	
				}
				
				if(roleType.equalsIgnoreCase("CommitteeRole"))
				{
				
					 totalCadreIds = eventInviteeDAO.getCandidateTdpCadreIdsForCommitteeRole(eventId,designationId,level,enrollmentYearIds);
					 attendedCadreIds = eventAttendeeDAO.getCadreIdsForAttendeesForCommitteeRole(eventId,date,designationId,level,enrollmentYearIds);
				}
				
				if(roleType.equalsIgnoreCase("affliatedCommittee"))
				{
				
					 totalCadreIds = eventInviteeDAO.getCandidateTdpCadreIdsForAffliatedCommitteeRole(eventId,designationId,level,enrollmentYearIds);
					 attendedCadreIds = eventAttendeeDAO.getCadreIdsForAttendeesForAffliatedCommitteeRole(eventId,date,designationId,level,enrollmentYearIds);
				}
				if(totalCadreIds != null && attendedCadreIds != null){
					totalCadreIds.removeAll(attendedCadreIds);
					getCandidateDetailsByCadreIds(totalCadreIds,resultVOList,date,eventId,roleType);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCandidateDetails", e);
		}
		return resultVOList;
	}
	
	public void getCandidateDetailsByCadreIds(List<Long> cadreIds,List<CandidateDetailsVO> resultVOList,Date date,Long eventId,String roleType){
		if(cadreIds != null && cadreIds.size() >0){
			//get attendence details
			List<Long> attendedCadreIds = eventAttendeeDAO.getAttendenceDetails(cadreIds,date,eventId);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Map<Long,Map<String,Boolean>> cadreAttendedMap = new HashMap<Long, Map<String,Boolean>>();
			List<Object[]> attendedListForCadre = eventAttendeeDAO.getAttendenceDetailsForCadre(cadreIds,eventId);
			if(attendedListForCadre != null && attendedListForCadre.size() > 0)
			{
				for(Object[] obj : attendedListForCadre)
				{
					Map<String,Boolean> dayMap = cadreAttendedMap.get((Long)obj[0]);
					if(dayMap == null)
					{
						dayMap = new HashMap<String, Boolean>();
						cadreAttendedMap.put((Long)obj[0], dayMap);
					}
					if(obj[1] != null)
					dayMap.put(obj[1].toString(), true);
				}
			}
			Map<Long,String> parliamentAssemblyNamesMap = new HashMap<Long, String>(0);
			Map<Long,String> districtNamesMap = new HashMap<Long, String>(0);
			Map<Long,String> tehsilNamesMap = new HashMap<Long, String>(0);
			Map<Long,String> localEleNamesMap = new HashMap<Long, String>(0);
			Map<Long,String> stateNamesMap = new HashMap<Long, String>(0);
			if(cadreIds != null && cadreIds.size() > 0){
				//tdpCadreId,name,publicRepresentativeTypeId,type,levelId,levelValue,image
				List<Object[]> candidateDetailsList = null;
				if(roleType.equalsIgnoreCase("PR"))
				candidateDetailsList = tdpCadreCandidateDAO.getCandidateDetails(cadreIds);
				else
				candidateDetailsList = tdpCadreCandidateDAO.getCandidateDetailsForCommittee(cadreIds);
					
				List<Long> parliamentAssemblyIds = new ArrayList<Long>(0);
				List<Long> districtIds = new ArrayList<Long>(0);
				List<Long> tehsilIds = new ArrayList<Long>(0);
				List<Long> localBodyIds = new ArrayList<Long>(0);
				List<Long> stateIds = new ArrayList<Long>(0);
				//get location details
				if(roleType.equalsIgnoreCase("PR"))
				{
				for (Object[] objects : candidateDetailsList) {
					if(objects[4] != null && ((Long)objects[4] == 1l || (Long)objects[4] == 2l || (Long)objects[4] == 3l)){
						if(!parliamentAssemblyIds.contains((Long)objects[5]))
							parliamentAssemblyIds.add((Long)objects[5]);
					}else if(objects[4] != null && (Long)objects[4] == 5l ){//
						if(!districtIds.contains((Long)objects[5]))
							districtIds.add((Long)objects[5]);
					}
					else if(objects[4] != null && ((Long)objects[4] == 4l ||  (Long)objects[4] == 7l)){//
						if(!tehsilIds.contains((Long)objects[5]))
							tehsilIds.add((Long)objects[5]);
					}
					else if(objects[4] != null && (Long)objects[4] == 8l){//
						if(!localBodyIds.contains((Long)objects[5]))
							localBodyIds.add((Long)objects[5]);
					}
					else if(objects[4] != null && (Long)objects[4] == 6l)
						stateIds.add((Long)objects[5]);
					
				}
				if(parliamentAssemblyIds != null && parliamentAssemblyIds.size() > 0){
					List<Object[]> constDetails = constituencyDAO.getConstituencyInfoByConstituencyIdList(parliamentAssemblyIds);
					if(constDetails != null && constDetails.size() >0){
						for (Object[] objects : constDetails) {
							parliamentAssemblyNamesMap.put((Long)objects[0], objects[1].toString());
						}
					}
				}
				
				if(districtIds != null && districtIds.size() > 0){
					List<Object[]> objectsList = districtDAO.getDistrictDetailsByDistrictIds(districtIds);
					if(objectsList != null && objectsList.size() > 0){
						for (Object[] object : objectsList) {
							districtNamesMap.put((Long)object[0], object[1].toString());
						}
					}
				}
				
				
				if(tehsilIds != null && tehsilIds.size() > 0){
					List<Object[]> objectsList = tehsilDAO.getTehsilNameByTehsilIdsList(tehsilIds);
					if(objectsList != null && objectsList.size() > 0){
						for (Object[] object : objectsList) {
							tehsilNamesMap.put((Long)object[0], object[1].toString());
						}
					}
				}
				if(localBodyIds != null && localBodyIds.size() > 0)
				{
					List<Object[]> objectsList = localElectionBodyDAO.findByLocalElecBodyIds(localBodyIds);
					if(objectsList != null && objectsList.size() > 0){
						for (Object[] object : objectsList) {
							localEleNamesMap.put((Long)object[0], object[1].toString() + object[2].toString());
						}
					}
				}
				if(stateIds != null && stateIds.size() > 0)
				{
					for(Long stateId : stateIds)
					{
						if(stateId == 1l)
							stateNamesMap.put(stateId, "Andhra Pradesh");
						else
							stateNamesMap.put(stateId, "Telangana");	
					}
				}
				
				}
				//set data to return list
				//0-tdpCadreId,1-name,2-publicRepresentativeTypeId,3-type,4-levelId,5-levelValue,6-image
				for (Object[] objects : candidateDetailsList) {
					CandidateDetailsVO matechedVo = getMatchedVO1(resultVOList, (Long)objects[0]);
					if(matechedVo == null){
						CandidateDetailsVO vo = new CandidateDetailsVO();
						vo.setCadreId(objects[0] != null? (Long)objects[0] : 0l);
						vo.setCandidateName(objects[1] != null? objects[1].toString() : "");
						vo.setDatesList(getEventDatesByEventId(eventId,vo.getCadreId(),cadreAttendedMap));
						vo.setDesignationId(objects[2] != null? (Long)objects[2] : 0l);
						vo.setDesignation(objects[3] != null? objects[3].toString() : "");
						if(roleType.equalsIgnoreCase("PR"))
						{
							if(objects[4] != null ){//&& ((Long)objects[4] == 1l || (Long)objects[4] == 2l || 
								if(parliamentAssemblyNamesMap.get((Long)objects[5]) != null){
									if((Long)objects[4] == 1l){
										vo.setStateName(parliamentAssemblyNamesMap.get((Long)objects[5])+" PARLIAMENT");
									}else if((Long)objects[4] == 2l){
										vo.setStateName(parliamentAssemblyNamesMap.get((Long)objects[5])+" ASSEMBLY");
									}
									else if((Long)objects[4] == 3l){
										vo.setStateName(parliamentAssemblyNamesMap.get((Long)objects[5])+" MPTC");
									}
									
									
								}
								
								else if(districtNamesMap.get((Long)objects[5]) != null && (Long)objects[4] == 5l){
									vo.setStateName(districtNamesMap.get((Long)objects[5])+" DISTRICT");
								}
								else if(tehsilNamesMap.get((Long)objects[5]) != null && ((Long)objects[4] == 3l || (Long)objects[4] == 4l ||  (Long)objects[4] == 7l)){
									vo.setStateName(tehsilNamesMap.get((Long)objects[5])+" MANDAL");
								}
								
								else if(localEleNamesMap.get((Long)objects[5]) != null && (Long)objects[4] == 8l){
									vo.setStateName(localEleNamesMap.get((Long)objects[5]));
								}
								else if(stateNamesMap.get((Long)objects[5]) != null && (Long)objects[4] == 6l){
									vo.setStateName(stateNamesMap.get((Long)objects[5]));
								}
								else{
									vo.setStateName("");
								}
								
							}/*else if(objects[4] != null && (Long)objects[4] == 5l){
								if(districtNamesMap.get((Long)objects[5]) != null){
									vo.setStateName(districtNamesMap.get((Long)objects[5])+" DISTRICT");
								}else{
									vo.setStateName("");
								}
								
							}*/
						}	
						
						else
						{
							vo.setStateName(cadreDetailsUtils.getCommitteeLocationNameByLocationTypeAndId((Long)objects[4], (Long)objects[5]));
						}
						if(objects[0] != null && attendedCadreIds.contains((Long)objects[0])){
							vo.setStatus(true);
						}else{
							vo.setStatus(false);
						}
						vo.setImage(objects[6] != null? "images/cadre_images/"+objects[6].toString():"");
						resultVOList.add(vo);
					}else{
						matechedVo.setDesignation(objects[3] != null? matechedVo.getDesignation()+" , "+objects[3].toString() : "");
					}
					
				}
				
			}
			
			
		}
	}
	
	public List<MahanaduEventVO> getEventDatesByEventId(Long eventId,Long cadreId,Map<Long,Map<String,Boolean>> cadreAttendedMap)
	{
		List<MahanaduEventVO> datesList = new ArrayList<MahanaduEventVO>();
		try{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Object[] obj = eventDAO.getEventDates(eventId);
		List<Date>  betweenDates=new CommonMethodsUtilService().getBetweenDates((Date)obj[0],(Date)obj[1]);
		Map<String,Boolean> dayMap = cadreAttendedMap.get(cadreId);
			if(betweenDates != null && betweenDates.size() > 0)
			{
				  for( int i=0;i<betweenDates.size();i++){
					  MahanaduEventVO dayVO = new MahanaduEventVO();
					  dayVO.setName("Day"+(i+1));
					  if(dayMap != null && dayMap.get(format.format(betweenDates.get(i))) != null)
					  dayVO.setTotalDaydataExist(true);
					  else
					  dayVO.setTotalDaydataExist(false);
					  dayVO.setDateStr(format.format(betweenDates.get(i)));
					  datesList.add(dayVO);
				  }
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return datesList;
	}
	public CandidateDetailsVO getMatchedVO1(List<CandidateDetailsVO> voList,Long cadreId){
		if(voList != null && voList.size() > 0 && cadreId != null && cadreId > 0){
			for (CandidateDetailsVO candidateDetailsVO : voList) {
				if(candidateDetailsVO.getCadreId().equals(cadreId)){
					return candidateDetailsVO;
				}
			}
		}
		return null;
	}

}
