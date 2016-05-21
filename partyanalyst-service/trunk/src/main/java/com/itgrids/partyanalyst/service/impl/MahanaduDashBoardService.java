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
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEntryExitInfoDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.MahanaduVisitVO;
import com.itgrids.partyanalyst.model.CadreMahanaduVisitDetails;
import com.itgrids.partyanalyst.model.CadreMahanaduVisitInfo;
import com.itgrids.partyanalyst.model.EntryExitInfo;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class MahanaduDashBoardService implements IMahanaduDashBoardService {

	private IEventAttendeeDAO eventAttendeeDAO;
	private ICadreMahanaduVisitDetailsDAO cadreMahanaduVisitDetailsDAO;
	private ICadreMahanaduVisitInfoDAO cadreMahanaduVisitInfoDAO;
	private TransactionTemplate transactionTemplate;
	private IEntryExitInfoDAO entryExitInfoDAO;
	private IEventDAO	eventDAO;
	private IDistrictDAO districtDAO;
	private CommonMethodsUtilService commonMethodsUtilService; 
	
	private static final Logger LOG = Logger.getLogger(MahanaduDashBoardService.class);
	private DateUtilService dateUtilService = new DateUtilService();
	
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

	public void getTodayTotalVisitorsForWeb(){
		 try{
		  EntryExitInfo entryExitInfo = entryExitInfoDAO.getAll().get(0);
    	  getTodayTotalVisitors(entryExitInfo.getEntryId(),entryExitInfo.getExitId(),entryExitInfo.getParentEventId());
		 }catch(Exception e){
			 LOG.error("Exception rised in getTodayTotalVisitorsForWeb()",e);
		 }
	}
	
    public void getTodayTotalVisitorsForSchedular(){
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
				        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
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
		totalVisitors = eventAttendeeDAO.getTodayTotalVisitors(todayDate,parentEventId);
		currentVisitors = (eventAttendeeDAO.getCurrentVisitors(todayDate, entryEventId, exitEventId)).longValue();
		currentInviteeVisitors = (eventAttendeeDAO.getCurrentInviteeVisitors(todayDate, entryEventId, exitEventId)).longValue();
		
		
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
	
	public List<MahanaduVisitVO> getTodayTotalAndCurrentUsersInfoList(Long eventId){
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
			
			
		EntryExitInfo entryExitInfo = entryExitInfoDAO.getEntryDetails(eventId);
		Long entryEventId = entryExitInfo.getEntryId();
		Long exitEventId = entryExitInfo.getExitId();
		Long parentEventId = entryExitInfo.getParentEventId();
		reqParentEventId = parentEventId;
		
		Calendar fromDateCal = Calendar.getInstance();
		fromDateCal.setTime(fromDate);
		
		Calendar toDateCal = Calendar.getInstance();
		toDateCal.setTime(toDate);
		
		Date todayDate = dateUtilService.getCurrentDateAndTime();
		totalVisitors = eventAttendeeDAO.getTodayTotalVisitors(todayDate,parentEventId);
		currentVisitors = (eventAttendeeDAO.getCurrentVisitors(todayDate, entryEventId, exitEventId)).longValue();
		currentInviteeVisitors = (eventAttendeeDAO.getCurrentInviteeVisitors(todayDate, entryEventId, exitEventId)).longValue();
		
		
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
	
	public MahanaduVisitVO getTodayTotalAndCurrentUsersInfoListNew(Long eventId){		
		
		MahanaduVisitVO finalVo = new MahanaduVisitVO();
		
		try{
						
			Long toDaytotalVisitors = 0l;
			Long toDaytotalInviteeVisitors = 0l;
			Long currentVisitors = 0l;
			Long currentInviteeVisitors = 0l;			
												
			EntryExitInfo entryExitInfo = entryExitInfoDAO.getEntryDetails(eventId);
			Long entryEventId = entryExitInfo.getEntryId();
			Long exitEventId = entryExitInfo.getExitId();
			Long parentEventId = entryExitInfo.getParentEventId();
			
			Date todayDate = dateUtilService.getCurrentDateAndTime();
			
			toDaytotalVisitors = eventAttendeeDAO.getTodayTotalVisitors(todayDate,parentEventId);			
			toDaytotalInviteeVisitors=eventAttendeeDAO.getTodayTotalInviteeVisitors(todayDate,parentEventId);			
			currentVisitors = (eventAttendeeDAO.getCurrentVisitors(todayDate, entryEventId, exitEventId)).longValue();
			currentInviteeVisitors = (eventAttendeeDAO.getCurrentInviteeVisitors(todayDate, entryEventId, exitEventId)).longValue();
			
			
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
			
			List<MahanaduEventVO>  totalHourVisitors= getHourWiseTotalVisitorsCount(parentEventId,todayDate,null);//total Visitors Hours wise For Today
			//getHourWiseTotalVisitorsCount(parentEventId,todayDate,"Invitee");//total Invited Visitors Hours wise Flow For Today
			
			//getHourWiseCurrentVisitorsCount(todayDate,entryEventId,exitEventId,null);
			//getHourWiseCurrentVisitorsCount(todayDate,entryEventId,exitEventId,"Invitee");
			
			finalVo.setEventVOList(totalHourVisitors);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return finalVo;
		
	}
	public List<MahanaduEventVO> getHourWiseTotalVisitorsCount(Long parentEventId,Date startDate,String type )
	 {	
		 try{
			 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			// if(startDate != null && !startDate.isEmpty())
			// eventStrDate = format.parse(startDate);
			 List<Long> eventIds = new ArrayList<Long>(0);
			 List<Long> subEventIds = new ArrayList<Long>(0);
			 
			 //0.total,1.invited count,2.non-invited count,3.hour
			 List<Object[]> hourWiseResult = eventAttendeeDAO.getHourWiseTotalVisitorsCount(parentEventId,startDate,subEventIds,type);
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
		 for(int i=8;i<=21;i++){
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
	 
	public MahanaduEventVO getDistrictWiseTotalAndPresentCadre(Long eventId,List<Long> stateIds){
		
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
			
			StringBuilder otherStatesLocationQueryString = new StringBuilder();
			if(stateIds.contains(0l)){//for other states
				otherStatesExist = true;
				otherStatesLocationQueryString.append(" and UA.state_id not in (1) and UA.state_id is not null ");
			}
			
			
			EntryExitInfo entryExitInfo = entryExitInfoDAO.getEntryDetails(eventId);
			Long entryEventId = entryExitInfo.getEntryId();
			Long exitEventId = entryExitInfo.getExitId();		
			Date todayDate = dateUtilService.getCurrentDateAndTime();
			
			
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
			  List<Object[]> currentCountList = eventAttendeeDAO.getDistrictWiseCurrentCadreInCampus(todayDate,entryEventId,exitEventId,districtQueryStr.toString());
			  
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
			  List<Object[]> totalCountList = eventAttendeeDAO.getDistrictWiseTotalInvitedAndNonInvitedCount(eventId,districtQueryStr.toString(),todayDate);
			  
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
				  List<Object[]> otherStateRslt = eventAttendeeDAO.getOtherStateDistrictWiseCurrentCadreInCampus(todayDate,entryEventId,exitEventId,otherStatesLocationQueryString.toString());
				  
				  if(otherStateRslt != null && otherStateRslt.size() > 0){
					  for (Object[] objects : otherStateRslt) {
						  distWiseCountsMap.put((Long)objects[1], (Long)objects[0]);
					  }
				  }
				  
				  /*Total,Invited and Non invited Cadre for other states*/		  
				  //0.total,1.districtId,2.districtName
				  List<Object[]>  otherStatesTotalCountList = eventAttendeeDAO.getOtherStatesDistrictWiseTotalInvitedAndNonInvitedCount(eventId,otherStatesLocationQueryString.toString(),todayDate);
				  
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
	
	public List<MahanaduEventVO> getHourWiseNowInCampusCadresCount(Long dayCount,Long eventId){
		List<MahanaduEventVO> mahanaduEventVOList = new ArrayList<MahanaduEventVO>(0);
		try {
			Object[] dateObj = eventDAO.getEventDates(eventId);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			List<Date> betweenDates= commonMethodsUtilService.getBetweenDates(format.parse(dateObj[0].toString()),format.parse(dateObj[1].toString()));
			
			Date date = betweenDates.get(Integer.parseInt(dayCount.toString())-1);
			
			EntryExitInfo entryExitInfo = entryExitInfoDAO.getEntryDetails(eventId);
			Long entryEventId = entryExitInfo.getEntryId();
			Long exitEventId = entryExitInfo.getExitId();
			
			//get now in campus counts
			//0-cadreids count,1-hour
			List<Object[]> currentInCampusObjList = eventAttendeeDAO.getHourWiseCurrentVisitorsCount(date,entryEventId,exitEventId,null);
			
			Map<Long,Long> curentInCampusMap = new HashMap<Long, Long>(0);
			if(currentInCampusObjList != null && currentInCampusObjList.size() > 0){
				for (Object[] objects : currentInCampusObjList) {
					curentInCampusMap.put((Long)objects[1],(Long)objects[0]);
				}
			}
			
			//get total counts
			//0-total,1-invitees,2-non invitees,3-hour
			List<Object[]> totalCountsObjList = eventAttendeeDAO.getHourWiseTotalVisitorsCount(eventId,date,null,null);
			if(totalCountsObjList != null && totalCountsObjList.size() > 0){
				for (Object[] objects : totalCountsObjList) {
					MahanaduEventVO vo = new MahanaduEventVO();
					vo.setTotal((Long)objects[0]);
					vo.setCadreCount(curentInCampusMap.get((Long)objects[3]) != null ? curentInCampusMap.get((Long)objects[3]) : 0l);
					vo.setInvitees((Long)objects[3]);
					mahanaduEventVOList.add(vo);
				}
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception raised at getHourWiseNowInCampusCadresCount", e);
		}
		
		return mahanaduEventVOList;
	}
}
